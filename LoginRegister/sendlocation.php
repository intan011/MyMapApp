<?php
    $con = mysqli_connect("localhost", "root", "", "perlishospital");
    $user_name = $_POST['user_name'];
    $latitude = $_POST['lat'];
    $longitude = $_POST['lng'];
    $agent = $_SERVER['HTTP_USER_AGENT'];



    if ($con) {
        $sql = "INSERT INTO gpslocation (user_name, user_agent, lat, lng) VALUES ('".$user_name."','".$agent."', '".$latitude."', '".$longitude."')";
        if(mysqli_query($con, $sql)){
            echo "success";
        } else echo "Location Send failed";
    }else echo "Database connection failed";

?>
