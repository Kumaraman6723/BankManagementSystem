package bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bankmanagement";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Jadoo@12";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String fullName = request.getParameter("fullName");
        String fatherName = request.getParameter("fatherName");
        String address = request.getParameter("address");
        String state = request.getParameter("state");
        String city = request.getParameter("city");
        String email = request.getParameter("email");
        String dob = request.getParameter("dob");
        String religion = request.getParameter("religion");
        String category = request.getParameter("category");
        String accountType = request.getParameter("accountType");
        String password = request.getParameter("password");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
                // Get the latest account number from the database
                int latestAccountNumber = getLatestAccountNumber(con);

                // Generate a new account number for the current entry
                int newAccountNumber = latestAccountNumber + 1;

                // Use PreparedStatement to avoid SQL injection
                try (PreparedStatement preparedStatement = con.prepareStatement(
                        "INSERT INTO users (accountnumber, username, fullname, fatherName, address, state, city, email, dob, religion, category, accountType, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                    preparedStatement.setInt(1, newAccountNumber);
                    preparedStatement.setString(2, username);
                    preparedStatement.setString(3, fullName);
                    preparedStatement.setString(4, fatherName);
                    preparedStatement.setString(5, address);
                    preparedStatement.setString(6, state);
                    preparedStatement.setString(7, city);
                    preparedStatement.setString(8, email);
                    preparedStatement.setString(9, dob);
                    preparedStatement.setString(10, religion);
                    preparedStatement.setString(11, category);
                    preparedStatement.setString(12, accountType);
                    preparedStatement.setString(13, password);

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        createAccountDetailsTable(con, newAccountNumber,username);
                        Cookie usernameCookie = new Cookie("username", username);
                        response.addCookie(usernameCookie);

                        // Forward to Home.jsp upon successful registration
                        RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        out.println("<a href=signup.jsp><h2>Registration failed. Please try again.</h2> </a>");
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            out.println("<h2>Error in database connection or query.</h2>");
            Logger.getLogger(SignUpServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }


private void createAccountDetailsTable(Connection con, int accountNumber, String username) throws SQLException {
    String createTableQuery = "CREATE TABLE IF NOT EXISTS account" + accountNumber +
            " (username VARCHAR(40), accountNumber INT PRIMARY KEY, balance DECIMAL(10,2) DEFAULT 2000.0, withdraw DECIMAL(10,2) DEFAULT 0.0, deposit DECIMAL(10,2) DEFAULT 0.0, time TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
    try (PreparedStatement createTableStatement = con.prepareStatement(createTableQuery)) {
        createTableStatement.executeUpdate();

        String insertQuery = "INSERT INTO account" + accountNumber + "(username, accountNumber, balance, withdraw, deposit) VALUES (?, ?, 2000, 0, 0)";
        try (PreparedStatement insertStatement = con.prepareStatement(insertQuery)) {
            insertStatement.setString(1, username);
            insertStatement.setInt(2, accountNumber);
            insertStatement.executeUpdate();
        
        
    }}
}
    

    private int getLatestAccountNumber(Connection con) throws SQLException {
        int latestAccountNumber = 54000;
        String query = "SELECT MAX(accountnumber) FROM users";
        try (PreparedStatement preparedStatement = con.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                latestAccountNumber = resultSet.getInt(1);
            }
        }
        return latestAccountNumber;
    }
}
