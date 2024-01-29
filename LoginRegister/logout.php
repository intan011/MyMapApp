<?php
if (!empty($_POST['username']) && !empty($_POST['apiKey'])) {
    $username = $_POST['username'];
    $apiKey = $_POST['apiKey'];
    $con = mysqli_connect("localhost", "root", "", "perlishospital");
    if ($con) {
        $sql = "SELECT * FROM user WHERE username = '$username' AND apiKey = '$apiKey'";
        $res = mysqli_query($con, $sql);
        if (mysqli_num_rows($res) != 0) {
            $row = mysqli_fetch_assoc($res);
            $sqlUpdate = "UPDATE user SET apiKey = '' WHERE username = '$username'";
            if (mysqli_query($con, $sqlUpdate)) {
                echo "success";
            } else {
                echo "Logout failed";
            }
        } else {
            echo "Unauthorized to access";
        }
    } else {
        echo "Database connection failed";
    }
} else {
    echo "All fields are required";
}
?>
