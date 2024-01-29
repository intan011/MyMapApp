<!-- index.php -->

<?php
    include("db.php");
    $conn;
    mysqli_select_db($conn, "perlishospital");

    // Check for a logout message
    $logoutMessage = isset($_GET['logout']) ? "You have been successfully logged out." : "";

    if (isset($_POST['login'])) {
        $username = $_POST['username'];
        $password = $_POST['password'];

        // Use prepared statement to prevent SQL injection
        $sql = "SELECT * FROM staff WHERE staff_username = ? AND staff_password = ?";
        $stmt = mysqli_prepare($conn, $sql);

        if ($stmt) {
            // Bind parameters to the prepared statement
            mysqli_stmt_bind_param($stmt, "ss", $username, $password);

            // Execute the prepared statement
            mysqli_stmt_execute($stmt);

            // Store the result
            mysqli_stmt_store_result($stmt);

            // Check if the user exists
            if (mysqli_stmt_num_rows($stmt) > 0) {
                echo "<script> alert('Login successful!');
                    window.location= 'mainpage.php'</script>";
            } else {
                echo "<script> alert('Login failed. Invalid username or password.');
                    window.location= 'index.php'</script>";
            }

            // Close the statement
            mysqli_stmt_close($stmt);
        } else {
            echo "SQL Statement Error";
        }
    }
?>

<!DOCTYPE html>
<html lang="en">
<link href="style.css" rel="stylesheet" />
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login and Registration</title>
    <style>
    h1 {
        text-align: center;
        margin-top: -400px;
        margin-right: -380px;
    }
    button {
        background-color: #2c3e50;
        color: #fff;
        padding: 10px 15px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    button:hover {
        background-color: #34495e;
    }
    </style>
</head>
<body>
    <h1>Server Side Web Application</h1>
    <div class="container">
        <h2>Login</h2>
        <form action="index.php" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
            <br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            <br>
            <button type="submit" name="login">Login</button>
        </form>

        <form action="staffregister.php">
            <button type="submit">Register</button>
        </form>
    </div>
</body>
</html>
