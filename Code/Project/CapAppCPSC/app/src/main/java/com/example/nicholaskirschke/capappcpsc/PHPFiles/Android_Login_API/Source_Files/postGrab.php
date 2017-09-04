<?php
require_once 'include/DB_Functions.php';
$db = new DB_Functions();
$selectedId = $_POST['id'];
$response = $db->grabPostList();

if ($response != NULL) {

	$rowArray = array("id" => 0, "idThread" => 0, "idUser" => "", "post" => "", "created_at" => "","classYear" => "", "bio" => "");
	$forumArray = array("error" => FALSE);
	while($row = $response->fetch_object()) {
		if ($row->idThread == $selectedId) {
			$userInfo = $db->grabInfo($row->idUser);
			$tmp = $rowArray;   
			$tmp["id"] = $row->id;
			$tmp["idThread"] = $row->idThread;
			$tmp["idUser"] = $row->idUser;
			$tmp["post"] = $row->post;
			$tmp["created_at"] = $row->created_at;
			$tmp["classYear"] = $userInfo["classYear"];
			$tmp["bio"] = $userInfo["bio"];
			$tmp["name"] = $userInfo["name"];

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