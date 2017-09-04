package com.example.nicholaskirschke.capappcpsc;


public class AppConfig {
    // Insert a post into the db
    public static final String URL_ADD_POST = "http://10.129.58.186/Android_Login_API/Source_Files/addPost.php";;
    // Insert a thread into the DB
    public static final String URL_ADD_THREAD = "http://10.129.58.186/Android_Login_API/Source_Files/addThread.php";
    // Change password php
    public static final String URL_CHANGE_PASSWORD = "http://10.129.58.186/Android_Login_API/Source_Files/changePassword.php";
    // Server user login url
    public static final String URL_LOGIN = "http://10.129.58.186/Android_Login_API/Source_Files/login.php";
    // Server user register url
    public static final String URL_REGISTER = "http://10.129.58.186/Android_Login_API/Source_Files/register.php";
    // Server Forum grab
    public static final String URL_FORUMS = "http://10.129.58.186/Android_Login_API/Source_Files/forumGrab.php";
    // Server ForumCategory grab
    public static final String URL_FORUM_CATEGORIES = "http://10.129.58.186/Android_Login_API/Source_Files/categoryGrab.php";
    // Server ForumThreads grab
    public static final String URL_FORUM_THREADS = "http://10.129.58.186/Android_Login_API/Source_Files/threadGrab.php";
    // Server ForumPosts grab
    public static final String URL_FORUM_POSTS = "http://10.129.58.186/Android_Login_API/Source_Files/postGrab.php";
    // Inserts and image into the DB, never fully worked
    public static final String URL_FORUM_INSERTIMAGE = "http://10.129.58.186/Android_Login_API/Source_Files/insertImage.php";
    // Grabs image for user profile, never fully worked
    public static final String URL_FORUM_GRABIMAGE = "http://10.129.58.186/Android_Login_API/Source_Files/grabImage.php";
    // Updates the user's profile
    public static final String URL_UPDATEPROFILE = "http://10.129.58.186/Android_Login_API/Source_Files/updateProfile.php";
    // Populates the user profiles
    public static final String URL_POPPROFILE = "http://10.129.58.186/Android_Login_API/Source_Files/populateProfile.php";
}
