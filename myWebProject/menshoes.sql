-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: eshoeshop
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `menshoes`
--

DROP TABLE IF EXISTS `menshoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `menshoes` (
  `id` int(11) NOT NULL,
  `brand` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `colour` varchar(50) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menshoes`
--

LOCK TABLES `menshoes` WRITE;
/*!40000 ALTER TABLE `menshoes` DISABLE KEYS */;
INSERT INTO `menshoes` VALUES (1,'Nike','Racers','Sports','Rainbow',8,250,5),(2,'Nike','Racers','Sports','Rainbow',9,250,5),(3,'Nike','Racers','Sports','Rainbow',10,250,5),(4,'Nike','Racers','Sports','Rainbow',11,250,5),(5,'Nike','Racers','Sports','White',8,250,5),(6,'Nike','Racers','Sports','White',9,250,5),(7,'Nike','Racers','Sports','White',10,250,5),(8,'Nike','Racers','Sports','White',11,250,5),(9,'Nike','AirMax','Lifestyle','Black',8,109,5),(10,'Nike','AirMax','Lifestyle','Black',9,109,5),(11,'Nike','AirMax','Lifestyle','Black',10,109,5),(12,'Nike','AirMax','Lifestyle','Black',11,109,5),(13,'Nike','AirMax','Lifestyle','Red',8,109,5),(14,'Nike','AirMax','Lifestyle','Red',9,109,5),(15,'Nike','AirMax','Lifestyle','Red',10,109,5),(16,'Nike','AirMax','Lifestyle','Red',11,109,5),(17,'Adidas','NMD','Lifestyle','Green',8,250,5),(18,'Adidas','NMD','Lifestyle','Green',9,175,5),(19,'Adidas','NMD','Lifestyle','Green',10,175,5),(20,'Adidas','NMD','Lifestyle','Green',11,175,5),(21,'Adidas','Swift','Sports','Black',8,112,5),(22,'Adidas','Swift','Sports','Black',9,112,5),(23,'Adidas','Swift','Sports','Black',10,112,5),(24,'Adidas','Swift','Sports','Black',11,112,5),(25,'Adidas','Swift','Sports','Blue',8,112,5),(26,'Adidas','Swift','Sports','Blue',9,112,5),(27,'Adidas','Swift','Sports','Blue',10,112,5),(28,'Adidas','Swift','Sports','Blue',11,112,5),(29,'Adidas','NMD','Lifestyle','Grey',8,175,5),(30,'Adidas','NMD','Lifestyle','Grey',9,175,5),(31,'Adidas','NMD','Lifestyle','Grey',10,175,5),(32,'Adidas','NMD','Lifestyle','Grey',11,175,5);
/*!40000 ALTER TABLE `menshoes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-08 22:15:12
