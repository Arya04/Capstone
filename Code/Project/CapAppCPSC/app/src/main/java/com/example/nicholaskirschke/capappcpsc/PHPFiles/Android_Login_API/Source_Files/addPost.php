<?php
require_once 'include/DB_Functions.php';
$db = new DB_Functions();
 
// json response array
// grab the information passed in
$response = array("error" => FALSE);
$idThread = $_POST['idThread'];
$uid = $_POST['uId'];
$post = $_POST['post'];
 // Execute the insert
$idUser = $db->grabId($uid);
$insert = $db->insertPost($idThread, $idUser["id"], $post);
 // Check to see if it was false or not
if ($insert != false) {
    $response["error"] = FALSE;
    echo json_encode($response);
} else {
    // Executing insertion did not work
    $response["error"] = TRUE;
    $response["error_msg"] = "Post was not created";
    echo json_encode($response);
    }
 
?>