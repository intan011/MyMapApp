<!-- staff register.php -->
<?php
    include("db.php");
    $conn;
    mysqli_select_db($conn, "perlishospital");

    if (isset($_POST['staffregister'])){
        $staff_name = $_POST['staff_name'];
        $staff_username = $_POST['staff_username'];
        $staff_password = $_POST['staff_password'];

        // Use prepared statement to prevent SQL injection
        $sql = "INSERT INTO staff (staff_name, staff_username, staff_password) VALUES (?, ?, ?)";
        
        $stmt = mysqli_prepare($conn, $sql);

        if ($stmt) {
            // Bind parameters to the prepared statement
            mysqli_stmt_bind_param($stmt, "sss", $staff_name, $staff_username, $staff_password);

            // Execute the prepared statement
            $query_run = mysqli_stmt_execute($stmt);

            if ($query_run){
                echo "<script> alert('Successfully Registered! Thank You. Please Login');
                    window.location= 'index.php'</script>";
            } else {
                echo "<script> alert('SQL Error. Failed to Register!');
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
        margin-top: -500px;
        margin-right: -380px;
    }
    .container {
        display: flex;
        flex-direction: column;
        align-items: center;
    }
    button {
        background-color: #2c3e50;
        color: #fff;
        padding: 10px 15px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        margin-bottom: 10px;
    }

    button:hover {
        background-color: #34495e;
    }
    </style>
</head>
<body>
<h1>Server Side Web Application</h1>
    <div class="container">
        <h2>Register</h2>
        <form action="staffregister.php" method="post">
            <label for="staff_name">Full Name:</label>
            <input type="text" id="staff_name" name="staff_name" required>
            <br>
            <label for="staff_username">Username:</label>
            <input type="text" id="staff_username" name="staff_username" required>
            <br>
            <label for="staff_password">Password:</label>
            <input type="password" id="staff_password" name="staff_password" required>
            <br>
            <button type="submit" name="staffregister">Register</button>
        </form>
        <button onclick="window.location.href='index.php'">Back</button>
    </div>
</body>
</html>
