-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mechanic_car_dealerhip
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `service_request`
--

DROP TABLE IF EXISTS `service_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_request` (
  `service_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `is_done` bit(1) NOT NULL,
  `problem_description` varchar(255) DEFAULT NULL,
  `problem_type` varchar(255) DEFAULT NULL,
  `mech_email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`service_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_request`
--

LOCK TABLES `service_request` WRITE;
/*!40000 ALTER TABLE `service_request` DISABLE KEYS */;
INSERT INTO `service_request` VALUES (15,'jeremicigor2003@gmail.com','aaaaaaaaaaaaaaaaaaaaa',_binary '\0','bbbbbbbbbbbbbbbbbbbbbbbbbbb','ttttttttttttttttttttttttttttt','igorjeremic128@gmail.com'),(16,'jeremicigor2003@gmail.com','aaaaaaaaaaaaaaaaaaaaa',_binary '\0','bbbbbbbbbbbbbbbbbbbbbbbbbbb','ttttttttttttttttttttttttttttt','igorjeremic128@gmail.com'),(17,'jeremicigor2003@gmail.com','aaaaaaaaaaaaaaaaaaa',_binary '\0','aaaaaaaaaaaaaaaaaaaaaaa','aaaaaaaaaaaaaaaaaaa','igorjeremic128@gmail.com'),(18,'jeremicigor2003@gmail.com','aaaaaaaaaaaaaaaaaaaaa',_binary '\0','cccccccccccccccccccccccccccccccc','ttttttttttttttttttttttttttttt','igorjeremic128@gmail.com'),(19,'jeremicigor2003@gmail.com','aaaaaaaaaaaaaaaaaaaaa',_binary '\0','bbbbbbbbbbbbbbbbbbbbbbbbb','ttttttttttttttttttttttttttttt','igorjeremic128@gmail.com'),(20,'jeremicigor2003@gmail.com','Igor Jeremic',_binary '','headlights dont work','light issue','johndoe@example.com'),(21,'jeremicigor2003@gmail.com','Igor Jeremic',_binary '','handbreak wont engage at all','handbreak issue','johndoe@example.com'),(22,'jeremicigor2003@gmail.com','Igor Jeremic',_binary '\0','car wont lock at all','lock issue','johndoe@example.com'),(23,'jeremicigor2003@gamil.com','Igor jeremic',_binary '','problem','problem','johndoe@example.com'),(24,NULL,NULL,_binary '\0',NULL,NULL,NULL),(25,NULL,NULL,_binary '\0',NULL,NULL,NULL),(26,'igorjeremic128@gmail.com','aaaaaaaaaaaaaaaaaaaaaa',_binary '\0','aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa','aaaaaaaaaaaaaaaaaa','\'mikejohnson@example.com\''),(27,'igor12@gmail.com','Igor Jeremic',_binary '\0','aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa','brake problem','janesmith@example.com'),(28,'shomi@gmail.com','Milos Obelic',_binary '\0','asaaaaaaaaaaaaaaaaaaaaaaaaaaaa','asdasdas','johndoe@example.com');
/*!40000 ALTER TABLE `service_request` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-16 20:01:22
