<?php
require_once 'include/DB_Functions.php';
$db = new DB_Functions();
 
// json response array
// grab the information passed in
$response = array("error" => FALSE);
$uid = $_POST['uid'];
$bio = $_POST['bio'];
$classYear = $_POST['classYear'];
 
 // Execute the insert
$insert = $db->updateProfile($uid, $bio, $classYear);
 // Check to see if it was false or not
if ($insert != false) {
    $response["error"] = FALSE;
    echo json_encode($response);
} else {
    // Executing insertion did not work
    $response["error"] = TRUE;
    $response["error_msg"] = "Profile was not updated";
    echo json_encode($response);
    }
 
?>