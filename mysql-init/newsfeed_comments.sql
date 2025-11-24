-- MySQL dump 10.13  Distrib 8.0.44, for macos15 (x86_64)
--
-- Host: 127.0.0.1    Database: newsfeed
-- ------------------------------------------------------
-- Server version	8.4.7

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `newsfeed_id` bigint NOT NULL,
  `parent_comment_id` bigint DEFAULT NULL,
  `content` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `depth` int NOT NULL DEFAULT '0',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `modified_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `newsfeed_id` (`newsfeed_id`),
  KEY `parent_comment_id` (`parent_comment_id`),
  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`newsfeed_id`) REFERENCES `newsfeeds` (`id`) ON DELETE CASCADE,
  CONSTRAINT `comments_ibfk_3` FOREIGN KEY (`parent_comment_id`) REFERENCES `comments` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (1,1,1,NULL,'Comment 1',0,'2025-11-24 15:32:23','2025-11-24 15:32:23'),(2,2,2,NULL,'Comment 2',0,'2025-11-24 15:32:23','2025-11-24 15:32:23'),(3,3,3,NULL,'Comment 3',0,'2025-11-24 15:32:23','2025-11-24 15:32:23'),(4,4,4,NULL,'Comment 4',0,'2025-11-24 15:32:23','2025-11-24 15:32:23'),(5,5,5,NULL,'Comment 5',0,'2025-11-24 15:32:23','2025-11-24 15:32:23'),(6,6,6,NULL,'Comment 6',0,'2025-11-24 15:32:23','2025-11-24 15:32:23'),(7,7,7,NULL,'Comment 7',0,'2025-11-24 15:32:23','2025-11-24 15:32:23'),(8,8,8,NULL,'Comment 8',0,'2025-11-24 15:32:23','2025-11-24 15:32:23'),(9,9,9,NULL,'Comment 9',0,'2025-11-24 15:32:23','2025-11-24 15:32:23'),(10,10,10,NULL,'Comment 10',0,'2025-11-24 15:32:23','2025-11-24 15:32:23'),(11,11,11,NULL,'Comment 11',0,'2025-11-24 15:32:23','2025-11-24 15:32:23'),(12,12,12,NULL,'Comment 12',0,'2025-11-24 15:32:23','2025-11-24 15:32:23'),(13,13,13,NULL,'Comment 13',0,'2025-11-24 15:32:23','2025-11-24 15:32:23'),(14,14,14,NULL,'Comment 14',0,'2025-11-24 15:32:23','2025-11-24 15:32:23'),(15,15,15,NULL,'Comment 15',0,'2025-11-24 15:32:23','2025-11-24 15:32:23'),(16,16,16,NULL,'Comment 16',0,'2025-11-24 15:32:23','2025-11-24 15:32:23'),(17,17,17,NULL,'Comment 17',0,'2025-11-24 15:32:23','2025-11-24 15:32:23'),(18,18,18,NULL,'Comment 18',0,'2025-11-24 15:32:23','2025-11-24 15:32:23'),(19,19,19,NULL,'Comment 19',0,'2025-11-24 15:32:23','2025-11-24 15:32:23'),(20,20,20,NULL,'Comment 20',0,'2025-11-24 15:32:23','2025-11-24 15:32:23');
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-24 16:24:48
