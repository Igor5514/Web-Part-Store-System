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
-- Table structure for table `make`
--

DROP TABLE IF EXISTS `make`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `make` (
  `make_id` int NOT NULL AUTO_INCREMENT,
  `make` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`make_id`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `make`
--

LOCK TABLES `make` WRITE;
/*!40000 ALTER TABLE `make` DISABLE KEYS */;
INSERT INTO `make` VALUES (1,'Seat'),(2,'Think'),(3,'Lexus'),(4,'Innocenti'),(5,'Ssangyong'),(6,'Subaru'),(7,'Chevrolet'),(8,'Talbot'),(9,'Triumph'),(10,'Austin'),(11,'Chrysler'),(12,'Carver'),(13,'Yugo'),(14,'DS'),(15,'Landwind'),(16,'Rover'),(17,'TVR'),(18,'Maserati'),(19,'Infiniti'),(20,'Dacia'),(21,'Suzuki'),(22,'Fisker'),(23,'Lancia'),(24,'MG'),(25,'Morris'),(26,'Lincoln'),(27,'Hyundai'),(28,'Aston Martin'),(29,'Iveco'),(30,'Kia'),(31,'FSO'),(32,'Noble'),(33,'Pontiac'),(34,'Mazda'),(35,'Saab'),(36,'Alpine'),(37,'Cupra'),(38,'Peugeot'),(39,'Maybach'),(40,'Toyota'),(41,'Mega'),(42,'Jaguar'),(43,'Tesla'),(44,'Mercedes-Benz'),(45,'PGO'),(46,'Daewoo'),(47,'Volkswagen'),(48,'Volvo'),(49,'Princess'),(50,'Asia Motors'),(51,'Dodge'),(52,'Aiways'),(53,'Audi'),(54,'Josse'),(55,'Corvette'),(56,'Alfa Romeo'),(57,'Opel'),(58,'Donkervoort'),(59,'Honda'),(60,'Daimler'),(61,'Ferrari'),(62,'Citroen'),(63,'Morgan'),(64,'Mini'),(65,'Porsche'),(66,'Abarth'),(67,'Autobianchi'),(68,'Rolls-Royce'),(69,'Datsun'),(70,'Renault'),(71,'Jeep'),(72,'Buick'),(73,'Bugatti'),(74,'Polestar'),(75,'Galloper'),(76,'Mitsubishi'),(77,'Lotus'),(78,'BMW'),(79,'Smart'),(80,'Bentley'),(81,'Spectre'),(82,'Cadillac'),(83,'Land Rover'),(84,'Daihatsu'),(85,'Lamborghini'),(86,'Lada'),(87,'Fiat'),(88,'Skoda'),(89,'Marcos'),(90,'Ford'),(91,'Hummer'),(92,'McLaren'),(93,'Mercury'),(94,'Nissan');
/*!40000 ALTER TABLE `make` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-17 12:52:28
