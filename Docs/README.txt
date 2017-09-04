Arya Atighehchian
Nick Kirschke

INTRODUCTION
———————————-

The Social College Forum Mobile App is a forum for CNU college students to share information. The purpose of this application is to let students stay connected and share information publicly for everyone to see. 

REQUIREMENTS
———————————-

-Required software is a working MySQL database. MAMP (Mac Apache MySQL PHP) is what was used to create the database. 

-Android Studio IDE is required to run Android Applications. Minimum SDK of 19 but we initialized on 24. 

INSTALLATION
———————————-

-Install Android Studio as you would any other IDE. 

-Install MAMP (or appropriate version for your OS) from the official MAMP website. 

CONFIGURATION
————————————-

-In the AppConfig file, change the IP addresses in the links to your machines IP address that will allow you to connect to the database.

-For the PHP documents, go to folder where MAMP is installed.Then open the HTDOCS folder. Inside, create a folder called “Android_Login_API”. copy the “Source Files” folder from the zip and paste it directly inside the Android_Login_API folder. Inside your “Config.php” file, make sure the fields DB_DATABASE and DB_PASSWORD match the password for your MAMP database name and password. NOTE- Default is root and root for username/password.

TROUBLESHOOTING
——————————————-

-Inside MAMP folder, go to logs folder and check error logs for php, MySQL, and Apache errors.

MAINTAINERS
——————————-

-No current maintainers right now since it is not live.  