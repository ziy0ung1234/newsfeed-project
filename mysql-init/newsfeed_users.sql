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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `username` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `cellPhoneNumber` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `modified_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `cellPhoneNumber` (`cellPhoneNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'user1@example.com','user1','pass1','01011110001','2025-11-24 15:31:46','2025-11-24 15:31:46'),(2,'user2@example.com','user2','pass2','01011110002','2025-11-24 15:31:46','2025-11-24 15:31:46'),(3,'user3@example.com','user3','pass3','01011110003','2025-11-24 15:31:46','2025-11-24 15:31:46'),(4,'user4@example.com','user4','pass4','01011110004','2025-11-24 15:31:46','2025-11-24 15:31:46'),(5,'user5@example.com','user5','pass5','01011110005','2025-11-24 15:31:46','2025-11-24 15:31:46'),(6,'user6@example.com','user6','pass6','01011110006','2025-11-24 15:31:46','2025-11-24 15:31:46'),(7,'user7@example.com','user7','pass7','01011110007','2025-11-24 15:31:46','2025-11-24 15:31:46'),(8,'user8@example.com','user8','pass8','01011110008','2025-11-24 15:31:46','2025-11-24 15:31:46'),(9,'user9@example.com','user9','pass9','01011110009','2025-11-24 15:31:46','2025-11-24 15:31:46'),(10,'user10@example.com','user10','pass10','01011110010','2025-11-24 15:31:46','2025-11-24 15:31:46'),(11,'user11@example.com','user11','pass11','01011110011','2025-11-24 15:31:46','2025-11-24 15:31:46'),(12,'user12@example.com','user12','pass12','01011110012','2025-11-24 15:31:46','2025-11-24 15:31:46'),(13,'user13@example.com','user13','pass13','01011110013','2025-11-24 15:31:46','2025-11-24 15:31:46'),(14,'user14@example.com','user14','pass14','01011110014','2025-11-24 15:31:46','2025-11-24 15:31:46'),(15,'user15@example.com','user15','pass15','01011110015','2025-11-24 15:31:46','2025-11-24 15:31:46'),(16,'user16@example.com','user16','pass16','01011110016','2025-11-24 15:31:46','2025-11-24 15:31:46'),(17,'user17@example.com','user17','pass17','01011110017','2025-11-24 15:31:46','2025-11-24 15:31:46'),(18,'user18@example.com','user18','pass18','01011110018','2025-11-24 15:31:46','2025-11-24 15:31:46'),(19,'user19@example.com','user19','pass19','01011110019','2025-11-24 15:31:46','2025-11-24 15:31:46'),(20,'user20@example.com','user20','pass20','01011110020','2025-11-24 15:31:46','2025-11-24 15:31:46');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-24 16:24:49
