-- MariaDB dump 10.19  Distrib 10.4.24-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: ick_app
-- ------------------------------------------------------
-- Server version	10.4.24-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `jednostki`
--

DROP TABLE IF EXISTS `jednostki`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jednostki` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` text NOT NULL,
  `skrot` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jednostki`
--

LOCK TABLES `jednostki` WRITE;
/*!40000 ALTER TABLE `jednostki` DISABLE KEYS */;
INSERT INTO `jednostki` VALUES (1,'gramy','g'),(2,'kilogramy','kg'),(3,'sztuki','szt'),(4,'mililitry','ml');
/*!40000 ALTER TABLE `jednostki` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `potrawy`
--

DROP TABLE IF EXISTS `potrawy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `potrawy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` text NOT NULL,
  `kalorycznosc` int(11) NOT NULL,
  `jednostka` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `jednostka` (`jednostka`),
  CONSTRAINT `potrawy_ibfk_1` FOREIGN KEY (`jednostka`) REFERENCES `jednostki` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `potrawy`
--

LOCK TABLES `potrawy` WRITE;
/*!40000 ALTER TABLE `potrawy` DISABLE KEYS */;
INSERT INTO `potrawy` VALUES (1,'ziemniaki',77,1),(2,'marchewka',42,1),(3,'czekolada gorzka',546,1),(4,'sok pomarańczowy',45,4),(5,'pizza margherita',230,1),(6,'jabłka',52,1),(7,'ser żółty',400,1),(8,'łosoś surowy',182,1);
/*!40000 ALTER TABLE `potrawy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` text NOT NULL,
  `password` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','1234'),(2,'user','1234');
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

-- Dump completed on 2023-01-08 18:33:57
