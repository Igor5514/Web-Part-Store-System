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
-- Table structure for table `part_category`
--

DROP TABLE IF EXISTS `part_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `part_category` (
  `part_id` int NOT NULL,
  `model_id` int NOT NULL,
  `generation_id` int NOT NULL,
  `engine_id` int NOT NULL,
  PRIMARY KEY (`part_id`,`model_id`,`generation_id`,`engine_id`),
  KEY `part_category_ibfk_2` (`model_id`),
  KEY `part_category_ibfk_3` (`generation_id`),
  KEY `part_category_ibfk_4` (`engine_id`),
  CONSTRAINT `part_category_ibfk_1` FOREIGN KEY (`part_id`) REFERENCES `parts` (`part_id`),
  CONSTRAINT `part_category_ibfk_2` FOREIGN KEY (`model_id`) REFERENCES `model` (`model_id`),
  CONSTRAINT `part_category_ibfk_3` FOREIGN KEY (`generation_id`) REFERENCES `generation` (`generation_id`),
  CONSTRAINT `part_category_ibfk_4` FOREIGN KEY (`engine_id`) REFERENCES `engine` (`engine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `part_category`
--

LOCK TABLES `part_category` WRITE;
/*!40000 ALTER TABLE `part_category` DISABLE KEYS */;
INSERT INTO `part_category` VALUES (1,120,240,1440),(4,120,240,1440),(5,120,240,1440),(6,120,240,1440);
/*!40000 ALTER TABLE `part_category` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-17 12:52:29
