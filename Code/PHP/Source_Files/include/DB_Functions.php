<?php
 
 
class DB_Functions {
 
    private $conn;
 
    // constructor
    function __construct() {
        require_once 'DB_Connect.php';
        // connecting to database
        $db = new Db_Connect();
        $this->conn = $db->connect();
    }
 
    // destructor
    function __destruct() {
         
    }

    /**
    * Change user's password
    */
    public function changePassword($uid, $password) {
        $hash = $this->hashSSHA($password);
        $encrypted_password = $hash["encrypted"]; // encrypted password
        $salt = $hash["salt"]; // salt

        $stmt = $this->conn->prepare("UPDATE user SET encrypted_password = ?, salt = ? WHERE unique_id = ? ");
        $stmt->bind_param("sss", $encrypted_password, $salt, $uid);
        $result = $stmt->execute();
        $stmt->close();

        return $result;
    }
/**
    * Updates the bio and class year of the user
    */
    public function updateProfile($uid, $bio, $classYear) {
        
        $stmt = $this->conn->prepare("UPDATE user SET bio = ?, classYear = ? WHERE unique_id = ? ");
        $stmt->bind_param("sss", $bio, $classYear, $uid);
        $result = $stmt->execute();
        $stmt->close();

        return $result;
    }
    /**
    * Insert image in the user table
    */
    public function insertImage($photo, $uid) {
        $decodedImage = base64_decode($photo);
        $stmt = $this->conn->prepare("UPDATE user SET image = ? WHERE unique_id = ? ");
        $stmt->bind_param("bs", $decodedImage, $uid);
        $result = $stmt->execute();
        $stmt->close();

        return $result;
    }
/**
    * Insert image in the user table
    */
    public function grabImage($uid) {
        
        $stmt = $this->conn->prepare("SELECT * from user WHERE unique_id = ? ");
        $stmt->bind_param("s", $uid);
        $stmt->execute();
        $result = $stmt->get_result()->fetch_assoc();
        $stmt->close();

        return $result;
    }


    /**
    * Insert post into the forumPost table
    */
    public function insertPost($idThread, $idUser, $post) {
        $stmt = $this->conn->prepare("INSERT INTO forumPost(idThread, idUser, post, created_at) VALUES(?, ?, ?, NOW())");
        $stmt->bind_param("sss",$idThread, $idUser, $post);
        $result = $stmt->execute();
        $stmt->close();

        return $result;
    }
    /**
    * Insert thread into the forumThread table
    */
    public function insertThread($idCategory, $title, $description) {
        $stmt = $this->conn->prepare("INSERT INTO forumThread(idCategory, title, description, created_at) VALUES(?, ?, ?, NOW())");
        $stmt->bind_param("sss",$idCategory, $title, $description);
        $result = $stmt->execute();
        $stmt->close();

        return $result;

    }
/**
     * Grab Bio and classYear 
     * 
     */
    public function populateProfile($uid) {
        $stmt = $this->conn->prepare("SELECT bio, classYear FROM user WHERE unique_id = ?");
        $stmt->bind_param("s", $uid);
        $stmt->execute();
        $info = $stmt->get_result()->fetch_assoc();
        $stmt->close();
        return $info;
    }

    /**
     * Grab user id 
     * 
     */
    public function grabId($uid) {
        $stmt = $this->conn->prepare("SELECT id FROM user WHERE unique_id = ?");
        $stmt->bind_param("s", $uid);
        $stmt->execute();
        $userId = $stmt->get_result()->fetch_assoc();
        $stmt->close();
        return $userId;
    }

    /**
     * Grab user name 
     * 
     */
    public function grabInfo($id) {
        $stmt = $this->conn->prepare("SELECT name, bio, classYear FROM user WHERE id = ?");
        $stmt->bind_param("s", $id);
        $stmt->execute();
        $userName = $stmt->get_result()->fetch_assoc();
        $stmt->close();
        return $userName;
    }

    /**
     * Storing new user
     * returns user details
     */
    public function storeUser($name, $email, $password) {
        $uuid = uniqid('', true);
        $hash = $this->hashSSHA($password);
        $encrypted_password = $hash["encrypted"]; // encrypted password
        $salt = $hash["salt"]; // salt
 
        $stmt = $this->conn->prepare("INSERT INTO user(unique_id, name, email, encrypted_password, salt, created_at) VALUES(?, ?, ?, ?, ?, NOW())");
        $stmt->bind_param("sssss", $uuid, $name, $email, $encrypted_password, $salt);
        $result = $stmt->execute();
        $stmt->close();
 
        // check for successful store
        if ($result) {
            $stmt = $this->conn->prepare("SELECT * FROM user WHERE email = ?");
            $stmt->bind_param("s", $email);
            $stmt->execute();
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();
 
            return $user;
        } else {
            return false;
        }
    }
 
    /**
     * Get user by email and password
     */
    public function getUserByEmailAndPassword($email, $password) {
 
        $stmt = $this->conn->prepare("SELECT * FROM user WHERE email = ?");
 
        $stmt->bind_param("s", $email);
 
        if ($stmt->execute()) {
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();
 
            // verifying user password
            $salt = $user['salt'];
            $encrypted_password = $user['encrypted_password'];
            $hash = $this->checkhashSSHA($salt, $password);
            // check for password equality
            if ($encrypted_password == $hash) {
                // user authentication details are correct
                return $user;
            }
        } else {
            return NULL;
        }
    }
    /**
    * Get the forum main list
    */
    public function grabForumList() {
        if($stmt = $this->conn->query("SELECT id, title, description FROM forum")) {
            return $stmt;
        } else {
            return NULL;
        }
    }
    /**
    * Get the forumCategory list
    */

    public function grabCategoryList() {
        if($stmt = $this->conn->query("SELECT id, idForum, title, description FROM forumCategory")) {
            return $stmt;
        } else {
            return NULL;
        }
        
    }
    /**
    * Get the forumThread list
    */

    public function grabThreadList() {
        if($stmt = $this->conn->query("SELECT id, idCategory, title, description FROM forumThread")) {
            return $stmt;
        } else {
            return NULL;
        }
        
    }

    /**
    * Get the forumPost list
    */

    public function grabPostList() {
        if($stmt = $this->conn->query("SELECT * FROM forumPost")) {
            return $stmt;
        } else {
            return NULL;
        }
        
    }
    /**
     * Check user is existed or not
     */
    public function isUserExisted($email) {
        $stmt = $this->conn->prepare("SELECT email from user WHERE email = ?");
 
        $stmt->bind_param("s", $email);
 
        $stmt->execute();
 
        $stmt->store_result();
 
        if ($stmt->num_rows > 0) {
            // user existed 
            $stmt->close();
            return true;
        } else {
            // user not existed
            $stmt->close();
            return false;
        }
    }
 
    /**
     * Encrypting password
     * @param password
     * returns salt and encrypted password
     */
    public function hashSSHA($password) {
 
        $salt = sha1(rand());
        $salt = substr($salt, 0, 10);
        $encrypted = base64_encode(sha1($password . $salt, true) . $salt);
        $hash = array("salt" => $salt, "encrypted" => $encrypted);
        return $hash;
    }
 
    /**
     * Decrypting password
     * @param salt, password
     * returns hash string
     */
    public function checkhashSSHA($salt, $password) {
 
        $hash = base64_encode(sha1($password . $salt, true) . $salt);
 
        return $hash;
    }
 
}
 
?>