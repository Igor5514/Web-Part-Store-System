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
-- Table structure for table `parts`
--

DROP TABLE IF EXISTS `parts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parts` (
  `part_id` int NOT NULL AUTO_INCREMENT,
  `brand` varchar(255) DEFAULT NULL,
  `date_added` datetime(6) DEFAULT NULL,
  `description` text,
  `dimensions` varchar(255) DEFAULT NULL,
  `image` blob,
  `last_updated` datetime(6) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `part_number` varchar(255) NOT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `stock_quantity` int DEFAULT NULL,
  `vehicle_type` varchar(255) DEFAULT NULL,
  `weight` decimal(6,2) DEFAULT NULL,
  PRIMARY KEY (`part_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parts`
--

LOCK TABLES `parts` WRITE;
/*!40000 ALTER TABLE `parts` DISABLE KEYS */;
INSERT INTO `parts` VALUES (1,'Bosch','2025-06-15 15:43:18.000000','High quality air filter for better performance.','20x15x5 cm',NULL,'2025-06-15 15:43:18.000000','Air Filter','AF-12345',25.50,50,'Passenger',0.75),(2,'Brembo','2025-06-15 15:43:18.000000','Set of 4 ceramic brake pads.','18x12x6 cm',NULL,'2025-06-15 15:43:18.000000','Brake Pads','BP-54321',45.00,30,'Passenger',1.20),(3,'MANN','2025-06-15 15:43:18.000000','Durable oil filter for long service intervals.','10x8x8 cm',NULL,'2025-06-15 15:43:18.000000','Oil Filter','OF-67890',15.99,100,'Passenger',0.50),(4,'MANN','2025-06-16 00:30:46.000000','Premium air filter ensuring maximum airflow.','21x14x5 cm',NULL,'2025-06-16 00:30:46.000000','Air Filter','AF-98765',23.90,40,'Passenger',0.70),(5,'K&N','2025-06-16 00:30:46.000000','Performance air filter with washable element.','22x16x5 cm',NULL,'2025-06-16 00:30:46.000000','Air Filter','AF-87654',34.75,25,'Passenger',0.85),(6,'Mahle','2025-06-16 00:30:46.000000','Reliable OEM air filter replacement.','20x15x4.5 cm',NULL,'2025-06-16 00:30:46.000000','Air Filter','AF-76543',19.80,60,'Passenger',0.65);
/*!40000 ALTER TABLE `parts` ENABLE KEYS */;
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
