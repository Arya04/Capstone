<?php
require_once 'include/DB_Functions.php';
$db = new DB_Functions();
 
// json response array
$response = array("error" => FALSE);
$uid = $_POST['uid'];
$password = $_POST['password'];
 
 // get the user by email and password
$user = $db->changePassword($uid, $password);
 
if ($user != false) {
    $response["error"] = FALSE;
    echo json_encode($response);
} else {
    // user is not found with the credentials
    $response["error"] = TRUE;
    $response["error_msg"] = "Login credentials are wrong. Please try again!";
    echo json_encode($response);
    }
 
?>