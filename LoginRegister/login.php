<?php
if(!empty($_POST['username']) && !empty($_POST['user_password'])){
    $username =$_POST['username'];
    $user_password = $_POST['user_password'];
    
    $con = mysqli_connect("localhost", "root", "", "perlishospital");
    $result = array();
    if ($con) {
        $sql = "SELECT * FROM user WHERE username = '".$username."'";
        $res = mysqli_query($con, $sql);
        if(mysqli_num_rows($res) != 0){
            $row = mysqli_fetch_assoc($res);
            if($username == $row['username'] && password_verify($user_password, $row['user_password'])){
                    try{
                        $apiKey = bin2hex(random_bytes(23));
                    }catch (Exception $e) {
                        $apiKey = bin2hex(uniqid($username, true));
                    }
                    $sqlUpdate = "update user set apiKey = '".$apiKey."' where username = '".$username."'";
                    if (mysqli_query($con, $sqlUpdate)){
                        $result = array("status" => "success", "message" => "Login successful",
                            "full_name" => $row['full_name'], "username" => $row['username'], "apiKey" => $apiKey);

                    }else $result = array("status" => "failed", "message" => "Login failed try again");
            }else $result = array("status" => "failed", "message" => "Retry with correct username and user_password");
        }else $result = array("status" => "failed", "message" => "Retry with correct username and user_password");
    }else $result = array("status" => "failed", "message" => "Database connection  failed");
}else $result = array("status" => "failed", "message" => "All fields are required");

echo json_encode($result, JSON_PRETTY_PRINT);
?>
