<?php
require_once 'include/DB_Functions.php';
$db = new DB_Functions();
 
// json response array
// grab the information passed in
$response = array("error" => FALSE);
$id = $_POST['uid'];
$image = $_POST['image'];
 
 // Execute the insert
$insert = $db->insertImage($image, $id);
 // Check to see if it was false or not
if ($insert != false) {
    $response["error"] = FALSE;
    echo json_encode($response);
} else {
    // Executing insertion did not work
    $response["error"] = TRUE;
    $response["error_msg"] = "Image was not stored";
    echo json_encode($response);
    }
 
?>