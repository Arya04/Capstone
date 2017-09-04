<?php
require_once 'include/DB_Functions.php';
$db = new DB_Functions();
 
// json response array
// grab the information passed in
$response = array("error" => FALSE);
$idCategory = $_POST['idCategory'];
$title = $_POST['title'];
$description = $_POST['description'];
 
 // Execute the insert
$insert = $db->insertThread($idCategory, $title, $description);
 // Check to see if it was false or not
if ($insert != false) {
    $response["error"] = FALSE;
    echo json_encode($response);
} else {
    // Executing insertion did not work
    $response["error"] = TRUE;
    $response["error_msg"] = "Thread was not created";
    echo json_encode($response);
    }
 
?>