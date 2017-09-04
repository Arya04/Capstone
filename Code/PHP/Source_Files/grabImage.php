<?php
require_once 'include/DB_Functions.php';
$db = new DB_Functions();
 
// json response array
// grab the information passed in
$response = array("error" => FALSE);
$id = $_POST['uid'];
 
 // Execute the insert
$grab = $db->grabImage($id);
$response["id"] = $grab["id"];
$response["image"] = base64_encode($grab["image"]);

 // Check to see if it was false or not
if ($grab != false) {
    $response["error"] = FALSE;
    echo json_encode($response);
} else {
    // Executing insertion did not work
    $response["error"] = TRUE;
    $response["error_msg"] = "Image was not stored";
    echo json_encode($response);
    }
 
?>