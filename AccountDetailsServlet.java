package bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AccountDetailsServlet")
public class AccountDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve username from cookies
        Cookie[] cookies = request.getCookies();
        String username = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = URLDecoder.decode(cookie.getValue(), "UTF-8");
                    break;
                }
            }
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagement", "root",
                    "Jadoo@12");

            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM users WHERE username=?");
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String accountNumber = resultSet.getString("accountNumber");
                // Retrieve user details from the result set
                String fullname = resultSet.getString("fullname");
                String fatherName = resultSet.getString("fatherName");
                String address = resultSet.getString("address");
                String state = resultSet.getString("state");
                String city = resultSet.getString("city");
                String email = resultSet.getString("email");
                String dob = resultSet.getString("dob");
                String religion = resultSet.getString("religion");
                String category = resultSet.getString("category");
                String accountType = resultSet.getString("accountType");
                String password = resultSet.getString("password");

                // Forward the details to the JSP page
                request.setAttribute("accountNumber", accountNumber);
                request.setAttribute("username", username);
                request.setAttribute("fullname", fullname);
                request.setAttribute("fatherName", fatherName);
                request.setAttribute("address", address);
                request.setAttribute("state", state);
                request.setAttribute("city", city);
                request.setAttribute("email", email);
                request.setAttribute("dob", dob);
                request.setAttribute("religion", religion);
                request.setAttribute("category", category);
                request.setAttribute("accountType", accountType);
                request.setAttribute("password", password);

                // Forward to the JSP page to display the details
                request.getRequestDispatcher("accountDetails1.jsp").forward(request, response);
            } else {
                out.println("User not found!");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
