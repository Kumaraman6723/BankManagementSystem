package bank;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/CheckBalanceServlet")
public class CheckBalanceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bankmanagement";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Jadoo@12";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Retrieve account number based on the username stored in cookies
        String username = getUsernameFromCookies(request);

        if (username != null) {
            int accountNumber = getAccountNumberByUsername(username);

            if (accountNumber != -1) {
                float balance = retrieveBalance(accountNumber);

                // Set attributes in the request scope
                request.setAttribute("username", username);
                request.setAttribute("accountNumber", accountNumber);
                request.setAttribute("balance", balance);

                // Forward the request to the JSP page
                request.getRequestDispatcher("CheckBalance1.jsp").forward(request, response);
            } else {
                out.println("Unable to retrieve account number for the given username.");
            }
        } else {
            out.println("Username not found in cookies.");
        }
    }

    private String getUsernameFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    private int getAccountNumberByUsername(String username) {
        int accountNumber = -1;

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
                // Retrieve account number based on the username
                String query = "SELECT accountnumber FROM users WHERE username = ?";
                try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                    preparedStatement.setString(1, username);

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            accountNumber = resultSet.getInt("accountnumber");
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        }

        return accountNumber;
    }

    private float retrieveBalance(int accountNumber) {
        float balance = 0.0f;

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
                // Retrieve balance from the account_details table
                String query = "SELECT balance FROM account" + accountNumber + " WHERE accountNumber = ?";
                try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                    preparedStatement.setInt(1, accountNumber);

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            balance = resultSet.getFloat("balance");
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        }

        return balance;
    }
}
