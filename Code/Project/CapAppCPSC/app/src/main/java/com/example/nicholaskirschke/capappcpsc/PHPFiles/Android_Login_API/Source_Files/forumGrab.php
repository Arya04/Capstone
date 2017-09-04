<?php
require_once 'include/DB_Functions.php';
$db = new DB_Functions();
$response = $db->grabForumList();
 // print_r($response);
if($response != FALSE) {
	// $strin = "NOT null";
	// print_r($strin);
	$rowArray = array("id" => 0, "title" => "", "description" => "");
	$forumArray = array("error" => FALSE);
	// print_r("\n");
	while($row = $response->fetch_object()) {
		$tmp = $rowArray;
		$tmp["id"] = $row->id;
		$tmp["title"] = $row->title;
		$tmp["description"] = $row->description;
		$forumArray[] = $tmp;
		// print_r($tmp);
	}
	echo json_encode($forumArray, JSON_FORCE_OBJECT);
	$response->close();
} else {
	
	$forumArray["error"] = TRUE;
    $forumArray["error_msg"] = "Forum information did not get retrieved. Please try again!";
    echo json_encode($forumArray);
    $response->close();
}
?>