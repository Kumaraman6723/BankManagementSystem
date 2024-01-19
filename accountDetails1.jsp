<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SEVA BANK - Account Details</title>
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

        .greeting {
            text-align: center;
            color: white;
            margin-bottom: 20px;
        }

        .account-details-container {
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

        button {
            background-color: #56baed;
            border: none;
            color: white;
            padding: 15px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            text-transform: uppercase;
            font-size: 13px;
            box-shadow: 0 10px 30px 0 rgba(95, 186, 233, 0.4);
            border-radius: 5px;
            margin: 20px 0 40px;
            transition: background-color 0.3s ease-in-out;
            cursor: pointer;
        }

        button:hover {
            background-color: #39ace7;
        }
    </style>
</head>

<body>
    <header>
        <h1>SEVA BANK</h1>
    </header>

    <div class="wrapper">
        <div class="account-details-container">
            <h2>Account Details for <%= request.getAttribute("username") %></h2>
            <ul>
                <li><strong>Account Number:</strong> <%= request.getAttribute("accountNumber") %></li>
                <li><strong>Full Name:</strong> <%= request.getAttribute("fullname") %></li>
                <li><strong>Father's Name:</strong> <%= request.getAttribute("fatherName") %></li>
                <li><strong>Address:</strong> <%= request.getAttribute("address") %></li>
                <li><strong>State:</strong> <%= request.getAttribute("state") %></li>
                <li><strong>City:</strong> <%= request.getAttribute("city") %></li>
                <li><strong>Email:</strong> <%= request.getAttribute("email") %></li>
                <li><strong>Date of Birth:</strong> <%= request.getAttribute("dob") %></li>
                <li><strong>Religion:</strong> <%= request.getAttribute("religion") %></li>
                <li><strong>Category:</strong> <%= request.getAttribute("category") %></li>
                <li><strong>Account Type:</strong> <%= request.getAttribute("accountType") %></li>
                <li><strong>Password:</strong> <%= request.getAttribute("password") %></li>
            </ul>
          
        </div>
         <a href=Home.jsp><button type="submit">GO BACK TO HOME</button></a>
    </div>
      

    <footer>
        &copy; 2023 SEVA BANK. All rights reserved.
    </footer>
</body>

</html>
