<?php
require_once 'include/DB_Functions.php';
$db = new DB_Functions();
 
// json response array
// grab the information passed in
$response = array("error" => FALSE);
$uid = $_POST['uid'];
 
 // Execute the insert
$info = $db->populateProfile($uid);
 // Check to see if it was false or not
if ($info != false) {
    $response["error"] = FALSE;
    $response["classYear"] = $info["classYear"];
    $response["bio"] = $info["bio"];
    echo json_encode($response);
} else {
    // Executing insertion did not work
    $response["error"] = TRUE;
    $response["error_msg"] = "Profile did not populate";
    echo json_encode($response);
    }
 
?>