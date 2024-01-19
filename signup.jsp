<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

        * {
            box-sizing: border-box;
        }
    </style>
</head>

<body>
    <header>
        <h1>SEVA BANK</h1>
    </header>
    <div class="login-container">
        <div class="wrapper">
            <div id="formContent">
                <!-- Tabs Titles -->
                <h2 class="inactive underlineHover"><a href="login.jsp">Sign In</a> </h2>
                <h2 class="active"> Sign Up </h2><br>
                <h2>Create Your SEVA BANK Account</h2>
                <!-- Icon -->
                <div class="fadeIn first">
                    <img src="SevaBankLogo.jpg - Copy.png" id="icon" alt="SEVA BANK Logo" />
                </div><br>
                <!-- Signup Form -->
                <form class="login-form" action="SignUpServlet" method="post">
                    <input type="text" id="username" class="fadeIn second" name="username" placeholder="Enter Username" required>
                    <input type="text" id="fullName" class="fadeIn second" name="fullName" placeholder="Enter Full Name" required>
                    <input type="text" id="fatherName" class="fadeIn second" name="fatherName" placeholder="Enter Father's Name" required>
                    <input type="text" id="address" class="fadeIn second" name="address" placeholder="Enter Address" required>
                    <input type="text" id="state" class="fadeIn second" name="state" placeholder="Enter State" required>
                    <input type="text" id="city" class="fadeIn second" name="city" placeholder="Enter City" required>
                    <input type="text" id="email" class="fadeIn second" name="email" placeholder="Enter Email" required>
                    <input type="text" id="dob" class="fadeIn second" name="dob" placeholder="Enter Date of Birth" required>
                    <input type="text" id="religion" class="fadeIn second" name="religion" placeholder="Enter Religion" required>
                    <input type="text" id="category" class="fadeIn second" name="category" placeholder="Enter Category" required>
                    <input type="text" id="accountType" class="fadeIn second" name="accountType" placeholder="Enter Account Type" required>
                    <input type="password" id="password" class="fadeIn third" name="password" placeholder="Enter Password" required>
                    <input type="password" id="confirmPassword" class="fadeIn third" name="confirmPassword" placeholder="Confirm Password" required>
                    <input type="submit" class="fadeIn fourth" value="Sign Up">
                </form>
            </div>
        </div>
    </div>
    <footer>
        &copy; 2023 SEVA BANK. All rights reserved.
    </footer>
</body>

</html>