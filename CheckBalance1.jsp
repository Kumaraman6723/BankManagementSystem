<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SEVA BANK - Check Balance</title>
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

        h1, h2 {
            text-align: center;
            color: white;
        }

        .wrapper {
            display: flex;
            align-items: center;
            flex-direction: column;
            justify-content: center;
            width: 100%;
            min-height: 100%;
            padding: 20px;
        }


        .balance-container {
             border-radius: 15px;
            overflow: hidden;
            background: rgba(0, 26, 51, 0.8); /* Darker background color with transparency */
            padding: 30px;
            width: 90%;
            max-width: 600px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
            color: white;
        }

        ul {
            list-style: none;
            padding: 0;
        }

        li {
            margin-bottom: 10px;
        }

        .non-editable {
            background-color: #003366;
            color: white;
            border: none;
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 10px;
            pointer-events: none;
        }

        .img1 {
            opacity: 0.75;
            max-width: 500px;
        }
    </style>
</head>

<body>
    <header>
        <h1>SEVA BANK</h1>
    </header>
    <div class="wrapper">
        <div class="balance-container">
            <h2>Account Details for <%= request.getAttribute("username") %></h2>
            <ul>
                <li><strong>Account Number:</strong> <%= request.getAttribute("accountNumber") %></li>
                <li><strong>Balance:</strong> <%= request.getAttribute("balance") %></li>
            </ul>
            <a href=Home.jsp><button type="submit">GO BACK TO HOME</button></a>
        </div>
        
    </div>
    <footer>
        &copy; 2023 SEVA BANK. All rights reserved.
    </footer>
</body>

</html>
