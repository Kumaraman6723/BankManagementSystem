package bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish database connection
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagement", "root",
                    "Jadoo@12")) {
                // Get username and password from the request
                String username = request.getParameter("accountNumber");
                String password = request.getParameter("password");

                // Hash the password (for illustration purposes, use a secure password hashing algorithm in a real application)
                // Note: This is just an example; in a real-world scenario, you should use a secure password hashing algorithm
                // and compare the hashed values.
                // For example, using Java's MessageDigest:
                // String hashedPassword = getHashedPassword(password);

                // Prepare SQL statement to check login credentials
                try (PreparedStatement ps = con
                        .prepareStatement("select username from users where username=? and password=?")) {
                    ps.setString(1, username);
                    ps.setString(2, password);

                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                           
                            Cookie usernameCookie = new Cookie("username", username);

                            // Add the cookie to the response
                            response.addCookie(usernameCookie);

                            // Forward to Home.jsp upon successful login
                            RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
                            rd.forward(request, response);
                        } else {
                            out.println("<font color=red size=18> Login Failed!! <br>");
                            out.println("<a href=login.jsp>Try AGAIN!!</a>");
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Log the exception and provide a user-friendly error message
            e.printStackTrace(); // Log to console for now; use a logging framework in production
            out.println("<font color=red size=18> An error occurred. Please try again later. </font>");
        }
    }
}