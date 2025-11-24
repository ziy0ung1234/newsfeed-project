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
-- Table structure for table `newsfeeds`
--

DROP TABLE IF EXISTS `newsfeeds`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `newsfeeds` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `title` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `modified_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `newsfeeds_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `newsfeeds`
--

LOCK TABLES `newsfeeds` WRITE;
/*!40000 ALTER TABLE `newsfeeds` DISABLE KEYS */;
INSERT INTO `newsfeeds` VALUES (1,1,'Title 1','Content for newsfeed 1','2025-11-24 15:32:00','2025-11-24 15:32:00'),(2,2,'Title 2','Content for newsfeed 2','2025-11-24 15:32:00','2025-11-24 15:32:00'),(3,3,'Title 3','Content for newsfeed 3','2025-11-24 15:32:00','2025-11-24 15:32:00'),(4,4,'Title 4','Content for newsfeed 4','2025-11-24 15:32:00','2025-11-24 15:32:00'),(5,5,'Title 5','Content for newsfeed 5','2025-11-24 15:32:00','2025-11-24 15:32:00'),(6,6,'Title 6','Content for newsfeed 6','2025-11-24 15:32:00','2025-11-24 15:32:00'),(7,7,'Title 7','Content for newsfeed 7','2025-11-24 15:32:00','2025-11-24 15:32:00'),(8,8,'Title 8','Content for newsfeed 8','2025-11-24 15:32:00','2025-11-24 15:32:00'),(9,9,'Title 9','Content for newsfeed 9','2025-11-24 15:32:00','2025-11-24 15:32:00'),(10,10,'Title 10','Content for newsfeed 10','2025-11-24 15:32:00','2025-11-24 15:32:00'),(11,11,'Title 11','Content for newsfeed 11','2025-11-24 15:32:00','2025-11-24 15:32:00'),(12,12,'Title 12','Content for newsfeed 12','2025-11-24 15:32:00','2025-11-24 15:32:00'),(13,13,'Title 13','Content for newsfeed 13','2025-11-24 15:32:00','2025-11-24 15:32:00'),(14,14,'Title 14','Content for newsfeed 14','2025-11-24 15:32:00','2025-11-24 15:32:00'),(15,15,'Title 15','Content for newsfeed 15','2025-11-24 15:32:00','2025-11-24 15:32:00'),(16,16,'Title 16','Content for newsfeed 16','2025-11-24 15:32:00','2025-11-24 15:32:00'),(17,17,'Title 17','Content for newsfeed 17','2025-11-24 15:32:00','2025-11-24 15:32:00'),(18,18,'Title 18','Content for newsfeed 18','2025-11-24 15:32:00','2025-11-24 15:32:00'),(19,19,'Title 19','Content for newsfeed 19','2025-11-24 15:32:00','2025-11-24 15:32:00'),(20,20,'Title 20','Content for newsfeed 20','2025-11-24 15:32:00','2025-11-24 15:32:00');
/*!40000 ALTER TABLE `newsfeeds` ENABLE KEYS */;
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
