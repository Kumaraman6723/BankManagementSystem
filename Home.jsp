<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="jakarta.servlet.http.Cookie" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SEVA BANK</title>
    <style>
        /* BASIC */
        html {
            background: url('bank.jpg') no-repeat center center fixed;
            background-size: cover;
            height: 100%;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: black;
            color: white;
            text-align: center;
            padding: 1em 0;
            border-bottom: 4px solid #001a33;
        }

        footer {
            background-color: black;
            color: white;
            text-align: center;
            padding: 1em 0;
            position: fixed;
            bottom: 0;
            width: 100%;
        }

        a {
            color: #ff6600;
            display: inline-block;
            text-decoration: none;
            font-weight: 400;
        }

        h2 {
            text-align: center;
            font-size: 16px;
            font-weight: 600;
            text-transform: uppercase;
            display: inline-block;
            margin: 40px 8px 10px 8px;
            color: white;
        }

        /* STRUCTURE */
        .wrapper {
            display: flex;
            align-items: center;
            flex-direction: column;
            justify-content: center;
            width: 100%;
            min-height: 100%;
            padding: 20px;
        }

        #formContent {
            border-radius: 15px;
            overflow: hidden;
            background: #001a33; /* Darker background color */
            padding: 30px;
            width: 90%;
            max-width: 450px;
            position: relative;
            padding: 0px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
            color: white;
        }

        /* TABS */
        h2.inactive {
            color: #003366;
        }

        h2.active {
            color: #ff6600;
            border-bottom: 2px solid #ff6600;
        }

        /* FORM TYPOGRAPHY*/
        input[type=button],
        input[type=submit],
        input[type=reset] {
            background-color: #00509e;
            border: none;
            color: white;
            padding: 15px 80px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            text-transform: uppercase;
            font-size: 13px;
            box-shadow: 0 10px 30px 0 rgba(0, 80, 158, 0.4);
            border-radius: 5px;
            margin: 5px 20px 40px 20px;
            transition: all 0.3s ease-in-out;
        }

        input[type=button]:hover,
        input[type=submit]:hover,
        input[type=reset]:hover {
            background-color: #003366;
        }

        input[type=button]:active,
        input[type=submit]:active,
        input[type=reset]:active {
            transform: scale(0.95);
        }

        input[type=text],
        input[type=password] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            box-sizing: border-box;
            border: none;
            border-bottom: 2px solid white;
            background-color: transparent;
            color: white;
        }

        input[type=text]:focus,
        input[type=password]:focus {
            background-color: #001a33;
            border-bottom: 2px solid #ff6600;
        }

        input[type=text]::placeholder,
        input[type=password]::placeholder {
            color: #cccccc;
        }

        /* OTHERS */
        *:focus {
            outline: none;
        }

        #icon {
            width: 40%; /* Smaller logo */
        }

        /* Additional Styles for new content */
        .options-container {
            display: flex;
            justify-content: space-between;
            padding: 20px;
            background-color: #001a33;
        }

        .options-container a {
            color: white;
            padding: 12px 24px;
            background-color: #001a33;
            color: white;
            border-radius: 50px;
            transition: background-color 0.3s;
            font-size: 1.1em;
        }

        .options:hover {
            background-color: #1DA1F2;
        }

        .welcome-message {
            color: white;
            text-align: center;
            margin: 30px 0;
        }

        .articles-container {
            display: flex;
            justify-content: space-around;
            flex-wrap: wrap;
            margin: 0 auto;
        }

        .article {
            width: 30%;
            background-color: #001a33;
            color: white;
            padding: 15px;
            margin: 10px;
            border-radius: 10px;
            text-align: center;
        }

        .article img.article-image {
            width: 80px;
            height: 80px;
            border-radius: 50%;
            margin-bottom: 10px;
        }

        .article:hover {
            background-color: rgb(3, 124, 204);
        }

        .bank-departments {
            text-align: center;
            background-color: #001a33;
            color: white;
            padding: 20px;
            margin-top: 20px;
        }

        /* Updated styling for the button */
        .bank-departments button {
            background-color: #00509e;
            border: none;
            color: white;
            padding: 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            text-transform: uppercase;
            font-size: 1.5em;
            border-radius: 10px;
            transition: background-color 0.3s;
        }

        .bank-departments button:hover {
            background-color: #003366;
        }
    </style>
</head>
  <%
        Cookie[] cookies = request.getCookies();
        String username = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = URLDecoder.decode(cookie.getValue(), "UTF-8");
                    break;
                }
            }
        }%>
<body>
    <header>
        <h1>SEVA BANK</h1>
        <div class="options-container">
            <a class="options" href="#">Hey <%=username %></a>
            <a class="options" href="accoutdetails.jsp">Account Details</a>
            <a class="options" href="withdraw.jsp">Withdraw</a>
            <a class="options" href="deposit.jsp">Deposit</a>
            <a class="options" href="checkbalance.jsp">Get Balance</a>
            <a class="options" href="transfer.jsp">Transfer Amount</a>
            <a class="options" href="lougout.jsp">Log Out</a>
        </div>
    </header>

    <div class="welcome-message">
        <h1>Welcome to SEVA BANK</h1>
       
    </div>

    <div class="articles-container">
        <div class="article">
            <img class="article-image" src="accounts.png" alt="accounts list" />
            <h2>Manage Accounts</h2>
            <p>
                Get all your account-related information on the go with SEVA BANK.
                It is your one-point access to manage your major account-related activities.

                You can now:
                • Check account balance
                • Transactions record
            </p>
        </div>
        <div class="article">
            <img class="article-image" src="funds transfer.png" alt="funds" />
            <h2>Funds Transfer</h2>
            <p>
                Transfer funds to own Seva Bank account, other Seva Bank account, and non-Seva Bank account using NEFT service from anywhere using sevabank.com. Fund transfer can be done, 24 x7, even on holiday, using IMPS service of sevabank.com.

                Transfer funds from anywhere, on the go using savabank.com.

                It is a secure and easy way to transfer funds to own Seva Bank account, other Seva Bank account, and non-Seva Bank account using NEFT mode of fund transfer
            </p>
        </div>
        <div class="article">
            <img class="article-image" src="withdrawl.png" alt="withdrawal" />
            <h2>Cardless Cash Withdrawal</h2>
            <p>
                Cardless Cash Withdrawal service is a simple and safe mode for you to send cash, 24x7, to any mobile number in India.

                All you need to do is login to sevabank.com with your Internet Banking user ID and password and initiate a Cardless Cash Withdrawal transaction. The recipient need not have any bank account or an ATM card,
            </p>
        </div>
    </div>
    <div class="bank-departments">
        <h2>Bank Departments</h2>
        <br>
        <!-- Button to Bank Departments Page -->
        <a href="Aboutus.jsp"><button>Visit Bank Departments</button></a>
    </div>
    <br>
    <footer>
        &copy; 2023 SEVA BANK. All rights reserved.
    </footer>
</body>

</html>
