-- MySQL dump 10.13  Distrib 5.7.20, for Linux (x86_64)
--
-- Host: localhost    Database: Farmacia
-- ------------------------------------------------------
-- Server version	5.7.20-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Customer`
--

DROP TABLE IF EXISTS `Customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `createdAt` datetime DEFAULT NULL,
  `cpf` varchar(11) DEFAULT NULL,
  `birthDate` date DEFAULT NULL,
  `address` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer`
--

LOCK TABLES `Customer` WRITE;
/*!40000 ALTER TABLE `Customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `Customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Employee`
--

DROP TABLE IF EXISTS `Employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `createdAt` datetime DEFAULT NULL,
  `registration` varchar(20) NOT NULL,
  `token` varchar(32) DEFAULT NULL,
  `username` varchar(20) NOT NULL,
  `authPassword` varchar(32) DEFAULT NULL,
  `admissionDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OrderProduct`
--

DROP TABLE IF EXISTS `OrderProduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OrderProduct` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_order_id` int(11) NOT NULL,
  `fk_stock_id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `price` int(11) DEFAULT '0',
  `quantity` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_product_order` (`fk_order_id`),
  KEY `fk_product_stock` (`fk_stock_id`),
  CONSTRAINT `fk_product_order` FOREIGN KEY (`fk_order_id`) REFERENCES `Orders` (`id`),
  CONSTRAINT `fk_product_stock` FOREIGN KEY (`fk_stock_id`) REFERENCES `StockProduct` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OrderProduct`
--

LOCK TABLES `OrderProduct` WRITE;
/*!40000 ALTER TABLE `OrderProduct` DISABLE KEYS */;
/*!40000 ALTER TABLE `OrderProduct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Orders`
--

DROP TABLE IF EXISTS `Orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_employee_id` int(11) DEFAULT NULL,
  `fk_customer_id` int(11) DEFAULT NULL,
  `status` int(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_employee` (`fk_employee_id`),
  KEY `fk_order_customer` (`fk_customer_id`),
  CONSTRAINT `fk_order_customer` FOREIGN KEY (`fk_customer_id`) REFERENCES `Customer` (`id`),
  CONSTRAINT `fk_order_employee` FOREIGN KEY (`fk_employee_id`) REFERENCES `Employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Orders`
--

LOCK TABLES `Orders` WRITE;
/*!40000 ALTER TABLE `Orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `Orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `StockProduct`
--

DROP TABLE IF EXISTS `StockProduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `StockProduct` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `price` int(11) DEFAULT '0',
  `quantity` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `StockProduct`
--

LOCK TABLES `StockProduct` WRITE;
/*!40000 ALTER TABLE `StockProduct` DISABLE KEYS */;
/*!40000 ALTER TABLE `StockProduct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'Farmacia'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-12 18:16:12
