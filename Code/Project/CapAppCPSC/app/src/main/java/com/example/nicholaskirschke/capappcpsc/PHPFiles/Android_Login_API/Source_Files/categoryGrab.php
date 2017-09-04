<?php
require_once 'include/DB_Functions.php';
$db = new DB_Functions();
$selectedId = $_POST['id'];
$response = $db->grabCategoryList();

if ($response != NULL) {

	$rowArray = array("id" => 0, "idCategory" => 0, "title" => "", "description" => "");
	$forumArray = array("error" => FALSE);
	while($row = $response->fetch_object()) {
		if ($row->idForum == $selectedId) {
			$tmp = $rowArray;
			$tmp["id"] = $row->id;
			$tmp["idForum"] = $row->idForum;
			$tmp["title"] = $row->title;
			$tmp["description"] = $row->description;
			$forumArray[] = $tmp;
		}
		// print_r($tmp);
	}
	echo json_encode($forumArray, JSON_FORCE_OBJECT);
	$response->close();
} else {
	
	$forumArray["error"] = TRUE;
    $forumArray["error_msg"] = "ForumThread information did not get retrieved. Please try again!";
    echo json_encode($forumArray);
    $response->close();
}
?>