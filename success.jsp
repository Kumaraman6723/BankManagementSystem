<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Deposit Successful</title>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap" rel="stylesheet">
    <style>
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

        .logo img {
            width: 300px;
            display: block;
            margin: 20px auto;
            border-radius: 15px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
        }

        label {
            display: block;
            margin: 10px 0;
            color: white;
        }
        .showbalance{
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

        input {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            box-sizing: border-box;
            border: none;
            border-bottom: 2px solid white;
            background-color: transparent;
            color: white;
        }

        input:focus {
            background-color: #001a33;
            border-bottom: 2px solid #ff6600;
        }

        .btn {
            margin: 20px auto;
            display: block;
            width: 200px;
            height: 50px;
            border: none;
            border-radius: 5px;
            background: #20e0fa;
            font-size: 16px;
            color: #2C3E50;
            letter-spacing: 0.1em;
            text-decoration: none;
            font-weight: 600;
            text-transform: uppercase;
            text-align: center;
            transition: background .4s, color .4s, transform .2s;
            cursor: pointer;
        }

        .btn:hover {
            background: #00b6f3;
            transform: scale(1.05);
        }
    </style>
</head>

<body>
    <header>
        <h1>SEVA BANK</h1>
    </header>
    <div class="wrapper">
    <div class="showbalance">
        <h2>Deposit Successful</h2>
        <p>New Balance: ${newBalance}</p>
        <!-- You can customize this page as needed -->
    </div>
    <br>
    <br>
     <a href=Home.jsp><button type="submit">GO BACK TO HOME</button></a>
    </div>
    <footer>
        &copy; 2023 SEVA BANK. All rights reserved.
    </footer>
</body>

</html>
