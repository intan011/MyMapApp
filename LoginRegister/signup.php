<?php
if(!empty($_POST['full_name']) && !empty($_POST['username']) && !empty($_POST['user_password'])) {
    $con = mysqli_connect("localhost", "root", "", "perlishospital");
    $full_name = $_POST['full_name'];
    $username = $_POST['username'];
    $user_password = password_hash($_POST['user_password'], PASSWORD_DEFAULT);


    if ($con) {
        $sql = "INSERT INTO user (full_name, username, user_password) VALUES ('".$full_name."', '".$username."', '".$user_password."')";
        if(mysqli_query($con, $sql)){
            echo "success";
        } else echo "Registration failed";
    }else echo "Database connection failed";
}else echo "All fields are required";

?>
