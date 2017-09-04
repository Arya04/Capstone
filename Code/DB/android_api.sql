-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Dec 10, 2016 at 10:45 PM
-- Server version: 5.6.33
-- PHP Version: 7.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `android_api`
--

-- --------------------------------------------------------

--
-- Table structure for table `forum`
--

CREATE TABLE `forum` (
  `id` int(11) UNSIGNED NOT NULL,
  `title` varchar(64) NOT NULL,
  `description` varchar(64) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `forum`
--

INSERT INTO `forum` (`id`, `title`, `description`, `created_at`) VALUES
(1, 'Sports', 'Men\'s and Women\'s Varsity Sports', '2016-12-01 20:46:01'),
(2, 'Club Sports', 'Competitive sports on campus', '2016-12-01 20:50:07'),
(4, 'Clubs', 'Fishing, table tennis, sky diving, video game ', '2016-11-30 18:22:57'),
(5, 'Fraternities', 'Male greek organizations', '2016-12-01 19:46:02'),
(6, 'Sororities', 'Female greek organizations', '2016-12-01 19:46:29'),
(7, 'General Discussion', 'Miscellaneous Topics', '2016-12-01 21:06:19');

-- --------------------------------------------------------

--
-- Table structure for table `forumCategory`
--

CREATE TABLE `forumCategory` (
  `id` int(11) UNSIGNED NOT NULL,
  `idForum` int(10) UNSIGNED DEFAULT NULL,
  `title` varchar(64) DEFAULT NULL,
  `description` text,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `forumCategory`
--

INSERT INTO `forumCategory` (`id`, `idForum`, `title`, `description`, `created_at`) VALUES
(1, 1, 'Men\'s Lacrosse', 'Varsity Men\'s Lacrosse Program 2016 Fall Ball', '2016-12-01 20:21:30'),
(2, 2, 'Ultimate Frisbee', 'Practice schedule', '2016-11-26 19:34:22'),
(3, 1, 'Football', 'Games, practice schedule', '2016-11-26 19:35:03'),
(5, 5, 'Alpha Phi Alpha', 'Upsilon Beta Chapter', '2016-12-01 19:54:02'),
(6, 1, 'Baseball', '2017 Varsity Baseball team', '2016-12-01 20:08:05'),
(7, 1, 'Men\'s Basketball', '2016 Varsity Basketball team', '2016-12-01 20:24:29'),
(8, 1, 'Men\'s Soccer', '2016 Varsity Soccer team', '2016-12-01 20:24:29'),
(9, 1, 'Men\'s Golf', '2017 Varsity Golf team', '2016-12-01 20:24:29'),
(10, 1, 'Men\'s Cross Country', '2016 Varsity Cross Country team', '2016-12-01 20:24:29'),
(11, 1, 'Men\'s Sailing', '2016 Varsity Sailing team', '2016-12-01 20:24:29'),
(12, 1, 'Men\'s Tennis', '2016 Varsity Tennis team', '2016-12-01 20:24:29'),
(13, 1, 'Men\'s Track & Field', '2016-2017 Varsity Track & Field', '2016-12-01 20:24:29'),
(14, 5, 'Delta Upsilon', 'CNU Chapter', '2016-12-01 20:30:04'),
(15, 5, 'Kappa Delta Rho', 'Beta Gamma Chapter', '2016-12-01 20:31:15'),
(16, 5, 'Kappa Sigma', 'Sigma Lambda Chapter', '2016-12-01 20:31:52'),
(17, 5, 'Pi Kappa Phi', 'Eta Iota Chapter', '2016-12-01 20:32:09'),
(18, 5, 'Pi Lambda Phi', 'Alpha Psi Chapter', '2016-12-01 20:32:09'),
(19, 5, 'Sigma Phi Epsilon', 'Pi Chapter', '2016-12-01 20:32:09'),
(20, 5, 'Sigma Tau Gamma', 'Delta Omicron Chapter', '2016-12-01 20:32:09'),
(21, 5, 'Psi Upsilon', 'Phi Nu Chapter', '2016-12-01 20:32:09'),
(22, 6, 'Alpha Delta Pi', 'Theta Nu Chapter', '2016-12-01 20:32:30'),
(23, 6, 'Alpha Kappa Alpha', 'Nu Epsilon Chapter', '2016-12-01 20:32:30'),
(24, 6, 'Alpha Sigma Alpha', 'Theta Gamma Chapter', '2016-12-01 20:32:30'),
(25, 6, 'Alpha Phi', 'Theta Phi Chapter', '2016-12-01 20:32:30'),
(26, 6, 'Gamma Phi Beta', 'Epsilon Iota Chapter', '2016-12-01 20:32:30'),
(27, 6, 'Delta Gamma', 'Eta Tau Chapter', '2016-12-01 20:32:30'),
(29, 6, 'Delta Sigma Theta', 'Omicron Tau Chapter', '2016-12-01 20:32:30'),
(30, 6, 'Phi Mu', 'Lambda Epsilon Chapter', '2016-12-01 20:32:30'),
(31, 6, 'Zeta Tau Alpha', 'Kappa Phi Chapter', '2016-12-01 20:32:30'),
(32, 1, 'Women’s Basketball', '2016-2017 Varsity Basketball Team', '2016-12-01 20:38:57'),
(33, 1, 'Cheerleading', '2016-2017 Varsity Cheer Team', '2016-12-01 20:38:57'),
(34, 1, 'CNU Storm Dance Team', '2016-2017 Varsity Dance Team', '2016-12-01 20:38:57'),
(35, 1, 'Women’s Basketball', '2016-2017 Varsity Basketball Team', '2016-12-01 20:38:57'),
(36, 1, 'Women’s Cross Country', '2016 Varsity Cross Country Team', '2016-12-01 20:38:57'),
(37, 1, 'Women’s Basketball', '2016-2017 Varsity Basketball Team', '2016-12-01 20:38:57'),
(38, 1, 'Women’s Field Hockey', '2016 Varsity Field Hockey Team', '2016-12-01 20:38:57'),
(39, 1, 'Women’s Basketball', '2016-2017 Varsity Basketball Team', '2016-12-01 20:38:57'),
(40, 1, 'Women’s Golf', '2017 Varsity Golf Team', '2016-12-01 20:38:57'),
(41, 1, 'Women’s Lacrosse', '2017 Varsity Lacrosse Team', '2016-12-01 20:38:57'),
(42, 1, 'Women’s Sailing', '2016 Varsity Sailing Team', '2016-12-01 20:38:57'),
(43, 1, 'Women’s Soccer', '2016 Varsity Soccer Team', '2016-12-01 20:38:57'),
(44, 1, 'Women’s Softball', '2017 Varsity Softball Team', '2016-12-01 20:38:57'),
(45, 1, 'Women’s Tennis', '2017 Varsity Tennis Team', '2016-12-01 20:38:57'),
(46, 1, 'Women’s Track & Field', '2017 Varsity Track & Field Team', '2016-12-01 20:38:57'),
(47, 1, 'Women’s Volleyball', '2016 Varsity Volleyball Team', '2016-12-01 20:38:57'),
(48, 4, 'Airsoft and Paintball Club', 'High packed action sport!', '2016-12-01 21:01:08'),
(49, 4, 'Anime Club', 'Foster enjoyment of the animated medium of storytelling!', '2016-12-01 21:01:08'),
(50, 4, 'Cigar Club', 'Students who focus on spreading the culture of cigars', '2016-12-01 21:01:08'),
(51, 4, 'Crochet for a Cause', 'Destressing environment to help the needy', '2016-12-01 21:01:08'),
(52, 4, 'Flow Club', 'Self expression through hooping poi and other forms of flow art', '2016-12-01 21:01:08'),
(53, 4, 'Gymnastics Club', 'For anyone who loves gymnastics!', '2016-12-01 21:01:08'),
(54, 4, 'Hypnotic Control', 'Hip hop dance club for any skill level!', '2016-12-01 21:01:08'),
(55, 2, 'Women’s Basketball', 'Contact Marcia Altman', '2016-12-01 21:03:44'),
(56, 2, 'Boxing', 'Contact Steve Alton', '2016-12-01 21:03:44'),
(57, 2, 'Crew', 'Contact Daniel Scherbenske', '2016-12-01 21:03:44'),
(58, 2, 'Equestrian', 'Contact Madison Crane', '2016-12-01 21:03:44'),
(59, 2, 'Field Hockey', 'Contact Lauren Cosner', '2016-12-01 21:03:44'),
(60, 2, 'Fishing', 'Contact Taylor Godsey', '2016-12-01 21:03:44'),
(61, 2, 'Gymnastics', 'Contact Brittney Spaulding', '2016-12-01 21:03:44'),
(62, 2, 'Men’s Ice Hockey', 'Contact Brenten Hurt', '2016-12-01 21:03:44'),
(63, 2, 'Men’s Lacrosse', 'Contact Connor Vincent', '2016-12-01 21:03:44'),
(64, 2, 'Women’s Lacrosse', 'Contact Alexie Hazen', '2016-12-01 21:03:45'),
(65, 2, 'Martial Arts', 'Contact Jeremiah Brannan', '2016-12-01 21:03:45'),
(66, 2, 'Quidditch', 'Contact Mitch Mahoney', '2016-12-01 21:03:45'),
(67, 2, 'Men’s Rugby', 'Contact Jake Walker', '2016-12-01 21:03:45'),
(68, 2, 'Running', 'Contact Carl Bauer', '2016-12-01 21:03:45'),
(69, 2, 'Men’s Soccer', 'Contact Nolan Bonner', '2016-12-01 21:03:45'),
(70, 2, 'Women’s Soccer', 'Contact Megan McLean', '2016-12-01 21:03:45'),
(71, 2, 'Swimming', 'Contact Iliana Lisann', '2016-12-01 21:03:45'),
(72, 2, 'Tennis', 'Contact Micah Coffman', '2016-12-01 21:03:45'),
(73, 2, 'Ultimate Frisbee', 'Contact Cory Williams', '2016-12-01 21:03:45'),
(74, 2, 'Men’s Volleyball', 'Contact David Pitts', '2016-12-01 21:03:45'),
(75, 2, 'Women’s Volleyball', 'Contact Kelly Bowers', '2016-12-01 21:03:45'),
(76, 7, 'Technology', 'Discuss upcoming technology and events in the cyber world', '2016-12-01 21:06:56'),
(77, 7, 'Politics', 'Discuss the recent election and what it may mean for this country', '2016-12-01 21:07:25'),
(78, 7, 'Food', 'Discuss food and your favorite recipes!', '2016-12-01 21:08:01'),
(79, 7, 'Restaurants', 'Talk about your favorite places to eat around CNU', '2016-12-01 21:08:21');

-- --------------------------------------------------------

--
-- Table structure for table `forumPost`
--

CREATE TABLE `forumPost` (
  `id` int(11) UNSIGNED NOT NULL,
  `idThread` int(10) UNSIGNED DEFAULT NULL,
  `idUser` int(10) UNSIGNED DEFAULT NULL,
  `post` text,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `forumPost`
--

INSERT INTO `forumPost` (`id`, `idThread`, `idUser`, `post`, `created_at`) VALUES
(1, 2, 5, 'My favorite color combo is the "blue berries". Strictly all blue with the white helmets.', '2016-11-27 15:35:44'),
(2, 2, 6, 'My favorite is the oreos, with the black shorts and helmets with the white jersey!', '2016-11-27 15:37:04'),
(4, 4, 6, 'All white for sure!', '2016-11-28 18:44:25'),
(5, 5, 6, 'PURPLE', '2016-11-28 19:01:00'),
(11, 2, 9, 'I wish we had purple', '2016-12-01 21:11:56'),
(12, 2, 11, 'all white', '2016-12-01 21:13:13'),
(13, 1, 5, 'Tryouts are during the first week of September. Talk to the head coach if interested!', '2016-12-01 21:09:52'),
(14, 8, 15, 'First Tryout is at 10am SHARP, Feb 20th.', '2016-12-01 23:36:36'),
(15, 9, 5, 'I hear Dr. Flores rocks!!!', '2016-12-02 11:30:54'),
(16, 9, 17, 'PCSE Capstone Fair - 12 pm Luter 322', '2016-12-02 12:11:22'),
(17, 9, 5, 'Haley we will be there!', '2016-12-02 12:51:30'),
(18, 2, 5, 'the blue tops with white shorts', '2016-12-02 13:01:19');

--
-- Dumping data for table `forumThread`
--

INSERT INTO `forumThread` (`id`, `idCategory`, `title`, `description`, `created_at`) VALUES
(1, 1, 'Lacrosse Tryouts', 'Here are the dates for those willing to try out.', '2016-11-27 14:21:09'),
(2, 1, 'Favorite Uniforms?', 'Discuss your favorite uniform', '2016-11-27 14:26:59'),
(4, 3, 'Football Uniforms', 'Favorite combo?', '2016-11-28 18:43:54'),
(5, 2, 'Frisbee Colors?', 'Which colors should we get?', '2016-11-28 19:00:24'),
(6, 1, 'Wall ball routines?', 'I\'m looking to change things up, post your routines!', '2016-11-30 20:12:57'),
(7, 3, 'Football Tryouts?', 'When are this upcoming years tryouts?', '2016-11-30 20:14:20'),
(8, 43, 'Spring Tryouts!', 'Times will be posted here for upcoming tryouts!', '2016-12-01 23:36:15'),
(9, 76, 'cnu pcse department', 'I hear the department is very up and coming!!!', '2016-12-02 11:30:19'),
(10, 49, 'fhfh', 'cbcn', '2016-12-02 12:06:48');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) UNSIGNED NOT NULL,
  `unique_id` varchar(23) NOT NULL,
  `name` varchar(50) NOT NULL,
  `bio` text NOT NULL,
  `classYear` varchar(15) NOT NULL,
  `email` varchar(100) NOT NULL,
  `encrypted_password` varchar(80) NOT NULL,
  `salt` varchar(10) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `image` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `unique_id`, `name`, `bio`, `classYear`, `email`, `encrypted_password`, `salt`, `created_at`, `image`) VALUES
(3, '5805a4fc71e752.86151460', 'Test User', '', '', 'test@test', 'PyvFQ8wksRUztotjIvEiG4JZdLtlNmE0NTdlMTlm', 'e6a457e19f', '2016-10-18 00:28:44', NULL),
(5, '580676c03781c9.84598202', 'Nick Kirschke', 'Captain of the men\'s lacrosse team', 'Senior', 'nick@cnu.edu', 'rS2yNSfKLxic6OlAE/RIT9dOkB4xMzY1MmVjNjI3', '13652ec627', '2016-10-18 15:23:44', ''),
(6, '581fa313db6b60.50350061', 'Billy Bob', 'Just happy to be here', 'Super Senior', 'bob@cnu.edu', 'JKpTGprBJQNzJkMWsvQ/ce5tDPZmN2M3N2YxMjA5', 'f7c77f1209', '2016-11-06 16:39:31', NULL),
(7, '583ceb99548cc6.62415053', 'Will gingerich', 'Total beast, I love lacrosse', 'Junior', 'william.gingerich.14@cnu.edu', 'u0pQULShHgUOayhV2+Eesq86q8BkM2NlNDFiOWEy', 'd3ce41b9a2', '2016-11-28 21:44:41', NULL),
(8, '583d1837d00c59.20000859', 'Alexandra Miller', '', '', 'Alexandra.miller.14@cnu.edu', 'nOT0Ttxyek7oy0SmHqicBoGkdzZhZTgzNDA0MWIw', 'ae834041b0', '2016-11-29 00:55:03', NULL),
(9, '583d947ebb8018.03675449', 'keith roberts', 'I like to do fun things, like read.', 'Junior', 'keith.roberts.14@cnu.edu', 'zBGMjhbqH6K/xSUlknCF2HSpmg1jOGJjYzIwZTFl', 'c8bcc20e1e', '2016-11-29 09:45:18', NULL),
(10, '583e206b485400.76621415', 'Troy Lyden', '', '', 'troy.lyden.13@cnu.edu', 'gN8QU/Y5GLV+viYULAUniN0WA3k3OThmNDFhMTg0', '798f41a184', '2016-11-29 19:42:19', NULL),
(11, '583f623bb3d4c3.88933584', 'John McAndrew', 'History is my life', 'Senior', 'John.mcandrew.13@cnu.edu', '+JkJcz6W+eIxnaVejZXOMgQVs1E1ZDM1MjBmZWE0', '5d3520fea4', '2016-11-30 18:35:23', NULL),
(12, '583f6248479545.35186944', 'Randy Foster', '', '', 'Randall.Foster.13@cnu.edu', 'OXzC+Y4fTMK7AWoPn+SbUk3G7LY3ODk1YjM4NjJh', '7895b3862a', '2016-11-30 18:35:36', NULL),
(13, '583f6254c22685.18849822', 'Brooks Billings', '', '', 'brooks.billings.13@cnu.edu', 'h/o0SvkVN8/nAxe/FGh9A+IkG/s0Zjc1MjUzNWRl', '4f752535de', '2016-11-30 18:35:48', NULL),
(14, '583f80a225abc1.73161915', 'Victoria Perry', '', '', 'victoria.perry.13@cnu.edu', 'PR1mwEStC5O4NG4X+JC27E8ekVlkNmYwYjAwMGY2', 'd6f0b000f6', '2016-11-30 20:45:06', NULL),
(15, '583f813cd83bb4.84509255', 'MickeyBlueShoes', 'I LOVE SOCCER!!!', 'Senior', 'allie.mcwilliams.13@cnu.edu', 'OE/8UjQ+DAYLa4K9u8Ej4WLSXgQ5ZWY0MWQxMDAy', '9ef41d1002', '2016-11-30 20:47:40', NULL),
(16, '5841a77491f397.56014149', 'Talia Hicks', '', '', 'talia.hicks.13@cnu.edu', 'kq/noReB05RbVcXg5I7V/TxbV41hOWYxZmE1ODI0', 'a9f1fa5824', '2016-12-02 11:55:16', NULL),
(17, '5841aa94091621.41886485', 'Haley Currence', '', '', 'haley.currence.15@cnu.edu', 'ReW+6IErHufl66XTkPrgQm/BoXMwNzRlYThlOGM1', '074ea8e8c5', '2016-12-02 12:08:36', NULL),
(18, '5841af9a8ea9e9.37706729', 'blair', '', '', 'cameron.stinson.12@cnu.edu', '0vhxUPHCVbY8cr11J/shh3JSNiY2ZmNlYjRjMjA3', '6fceb4c207', '2016-12-02 12:30:02', NULL),
(19, '5841b221637ce5.10535512', 'Nathan Gorman', '', '', 'Nathan.Gorman.15@cnu.edu', 'du9bzaoOcD7ZqQWhXbRDLjZKXopmNjdkOWY3NWRh', 'f67d9f75da', '2016-12-02 12:40:49', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `forum`
--
ALTER TABLE `forum`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `forumCategory`
--
ALTER TABLE `forumCategory`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idCategory` (`idForum`);

--
-- Indexes for table `forumPost`
--
ALTER TABLE `forumPost`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idForumThread` (`idThread`),
  ADD KEY `idUser` (`idUser`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_id` (`unique_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `forum`
--
ALTER TABLE `forum`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `forumCategory`
--
ALTER TABLE `forumCategory`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=80;
--
-- AUTO_INCREMENT for table `forumPost`
--
ALTER TABLE `forumPost`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `forumCategory`
--
ALTER TABLE `forumCategory`
  ADD CONSTRAINT `forumcategory_ibfk_1` FOREIGN KEY (`idForum`) REFERENCES `forum` (`id`);

--
-- Constraints for table `forumPost`
--
ALTER TABLE `forumPost`
  ADD CONSTRAINT `forumpost_ibfk_1` FOREIGN KEY (`idThread`) REFERENCES `forumThread` (`id`),
  ADD CONSTRAINT `forumpost_ibfk_2` FOREIGN KEY (`idUser`) REFERENCES `user` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
