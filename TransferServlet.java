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

@WebServlet("/TransferServlet")
public class TransferServlet extends HttpServlet {
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
            int senderAccountNumber = getAccountNumberByUsername(username);

            if (senderAccountNumber != -1) {
                // Get values from the form
                int recipientAccountNumber =  Integer.parseInt(request.getParameter("recipientAccountNumber"));
                String transferAmountStr = request.getParameter("transferAmount");

                try {
                    // Validate transfer amount
                    double transferAmount = Double.parseDouble(transferAmountStr);

                    // Handle the transfer logic directly (update database)
                    boolean success = handleTransfer(request, senderAccountNumber, recipientAccountNumber, transferAmount, response);

                    // Inside handleTransfer method
                    if (success) {
                        double newBalance = getBalance(senderAccountNumber);

                        // Set attributes in the request scope
                        request.setAttribute("senderAccountNumber", senderAccountNumber);
                        request.setAttribute("newBalance", newBalance);

                        // Redirect to the success JSP page
                        request.getRequestDispatcher("success2.jsp").forward(request, response);
                    } else {
                        // Set attributes in the request scope
                        request.setAttribute("errorMessage", "Transfer failed. Check your information and try again.");

                        // Redirect to the error JSP page
                        request.getRequestDispatcher("error3.jsp").forward(request, response);
                    }

                } catch (NumberFormatException e) {
                    out.println("<html><body><script>alert('Invalid transfer amount. Please enter a valid number.');</script></body></html>");
                }
            } else {
                out.println("Unable to retrieve account number for the given username.");
            }
        } else {
            out.println("Username not found in cookies.");
        }
    }

    private boolean handleTransfer(HttpServletRequest request, int senderAccountNumber, int recipientAccountNumber,
            double transferAmount, HttpServletResponse response) {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
                // Check if there is enough balance for the transfer
                double senderBalance = getBalance(senderAccountNumber);

                if (senderBalance >= transferAmount) {
                    // Get receiver's account number
                    

                    if (recipientAccountNumber != -1) {
                        // Update the balance in the sender's account
                        String updateSenderQuery = "UPDATE account" + senderAccountNumber
                                + " SET balance = balance - ? WHERE accountNumber = ?";
                        try (PreparedStatement updateSenderStatement = con.prepareStatement(updateSenderQuery)) {
                            updateSenderStatement.setDouble(1, transferAmount);
                            updateSenderStatement.setInt(2, senderAccountNumber);

                            int rowsUpdatedSender = updateSenderStatement.executeUpdate();

                            // Update the balance in the receiver's account
                            String updateReceiverQuery = "UPDATE account" + recipientAccountNumber
                                    + " SET balance = balance + ? WHERE accountNumber = ?";
                            try (PreparedStatement updateReceiverStatement = con
                                    .prepareStatement(updateReceiverQuery)) {
                                updateReceiverStatement.setDouble(1, transferAmount);
                                updateReceiverStatement.setInt(2, recipientAccountNumber);

                                int rowsUpdatedReceiver = updateReceiverStatement.executeUpdate();

                                // Insert a record into the transaction_history table
                                // (You can add transaction history logic here if needed)

                                return rowsUpdatedSender > 0 && rowsUpdatedReceiver > 0;
                            }
                        }
                    } else {
                        // Receiver account not found
                        request.setAttribute("errorMessage", "Receiver account not found.");
                        return false;
                    }
                } else {
                    // Insufficient funds, handle accordingly (e.g., show an error message)
                    request.setAttribute("errorMessage", "Insufficient funds for transfer.");
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
