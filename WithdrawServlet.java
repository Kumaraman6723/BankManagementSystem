package bank;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bankmanagement";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Jadoo@12";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Retrieve account number based on the username stored in cookies
        String username = getUsernameFromCookies(request);

        if (username != null) {
            int accountNumber = getAccountNumberByUsername(username);

            if (accountNumber != -1) {
                // Get values from the form
                String withdrawAmountStr = request.getParameter("withdrawAmount");

                try {
                    // Validate withdraw amount
                    double withdrawAmount = Double.parseDouble(withdrawAmountStr);

                    // Handle the withdraw logic directly (update database)
                    boolean success = handleWithdraw(request, accountNumber, withdrawAmount, response);

                 // Inside handleWithdraw method
                    if (success) {
                        double newBalance = getBalance(accountNumber);

                        // Set attributes in the request scope
                        request.setAttribute("accountNumber", accountNumber);
                        request.setAttribute("newBalance", newBalance);

                        // Redirect to the success JSP page
                        request.getRequestDispatcher("success1.jsp").forward(request, response);
                    } else {
                        // Set attributes in the request scope
                        request.setAttribute("errorMessage", "Insufficient funds for withdrawal.");

                        // Redirect to the error JSP page
                        request.getRequestDispatcher("error1.jsp").forward(request, response);
                    }

                } catch (NumberFormatException e) {
                    out.println("<html><body><script>alert('Invalid withdraw amount. Please enter a valid number.');</script></body></html>");
                }
            } else {
                out.println("Unable to retrieve account number for the given username.");
            }
        } else {
            out.println("Username not found in cookies.");
        }
    }

    // ... (Other methods remain the same)

    private boolean handleWithdraw(HttpServletRequest request, int accountNumber, double withdrawAmount, HttpServletResponse response) {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
                // Check if there is enough balance for the withdrawal
                double currentBalance = getBalance(accountNumber);

                if (currentBalance >= withdrawAmount) {
                    // Update the balance in the account_details table
                    String updateQuery = "UPDATE account" + accountNumber + " SET balance = balance - ? WHERE accountNumber = ?";
                    try (PreparedStatement updateStatement = con.prepareStatement(updateQuery)) {
                        updateStatement.setDouble(1, withdrawAmount);
                        updateStatement.setInt(2, accountNumber);

                        int rowsUpdated = updateStatement.executeUpdate();

                        // Insert a record into the transaction_history table

                        return rowsUpdated > 0;
                    }
                } else {
                    // Insufficient funds, handle accordingly (e.g., show an error message)
                    request.setAttribute("errorMessage", "Insufficient funds for withdrawal.");
                    return false;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        }

        return false;
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
    private double getBalance(int accountNumber) {
        double balance = 0.0;

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
                            balance = resultSet.getDouble("balance");
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
