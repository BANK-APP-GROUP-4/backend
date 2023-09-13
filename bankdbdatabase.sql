-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: bankdb
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `date_became_customer` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `mobile_number` bigint DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'abc',22,NULL,'sharanprasath@gmail.com','sharan','adada',NULL,NULL,'1234'),(2,'abc',22,'2023-09-11','example@gamil.com','sharan','male','prasath',1234,'12345'),(3,'banglore',22,'2023-09-13','sharanprasath@gmail.com','Sharanprasath','male','S',4,'hello'),(4,'chennai',22,'2023-09-12','udhay@gmail.com','udhay','male','kumar',678543578,'87546689'),(5,'chennai',22,'2023-09-12','sharan@gmail.com','sharan','male','kumar',67854357812,'87546689'),(6,NULL,22,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,'abc',22,'2023-09-12','sharan1234@gmail.com','sharan','male','prasath',90873862,'16789'),(8,NULL,0,'2023-09-12','hritick@gmail.com','hritick',NULL,NULL,98767890,'7654321'),(9,NULL,0,'2023-09-12','aravind@gmail.com','aravind',NULL,NULL,67897545,'7654321'),(10,NULL,0,'2023-09-12','prasanna@gmail.com','prasanna',NULL,NULL,9994245678,'prasanna'),(11,NULL,0,'2023-09-13','yeggi@gmail.com','Yegyanathan',NULL,NULL,87529,'67890'),(12,NULL,0,'2023-09-13','naNDY@gmail.com','nanda',NULL,NULL,875291,'67890'),(13,NULL,0,'2023-09-13','lupi@gmail.com','lupi',NULL,NULL,696969,'67890'),(14,NULL,0,'2023-09-13','shri@gmail.com','shrinath',NULL,NULL,5565555,'67890'),(15,NULL,0,'2023-09-13','pra@gmail.com','Praveena',NULL,NULL,9940323212,'semma'),(16,NULL,0,'2023-09-13','praveen@gmail.com','Praveen',NULL,NULL,99403232123,'semma'),(17,NULL,0,'2023-09-13','sambathk@gmail.com','sambu',NULL,NULL,34567890,'654321'),(18,NULL,0,'2023-09-13','sambat@gmail.com','sambu',NULL,NULL,3456789010,'654321'),(19,'bcd',11,'2023-09-13','samb@gmail.com','sambu','male','kumer',345678901023,'654321'),(20,'banglore',22,'2023-09-13','prasath@gmail.com','Sharanprasath','male','S',7,'hello');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fd_account`
--

DROP TABLE IF EXISTS `fd_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fd_account` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `activation_date` date DEFAULT NULL,
  `maturity_period` int DEFAULT NULL,
  `principal_amount` bigint DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  `account_no` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2v3iu2suhekmy0o17xb5xwn40` (`customer_id`),
  CONSTRAINT `FK2v3iu2suhekmy0o17xb5xwn40` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fd_account`
--

LOCK TABLES `fd_account` WRITE;
/*!40000 ALTER TABLE `fd_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `fd_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `savings_account`
--

DROP TABLE IF EXISTS `savings_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `savings_account` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `activation_date` date DEFAULT NULL,
  `balance` bigint DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  `account_no` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsthrgyb2xrr13lysrrursciyt` (`customer_id`),
  CONSTRAINT `FKsthrgyb2xrr13lysrrursciyt` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `savings_account`
--

LOCK TABLES `savings_account` WRITE;
/*!40000 ALTER TABLE `savings_account` DISABLE KEYS */;
INSERT INTO `savings_account` VALUES (1,NULL,5000,1,NULL),(2,'2023-09-11',5000,2,NULL),(3,'2023-09-12',5000,3,NULL),(4,'2023-09-12',5000,4,NULL),(5,'2023-09-12',5000,5,NULL),(6,NULL,5000,6,NULL),(7,'2023-09-12',5000,7,NULL),(8,NULL,NULL,NULL,NULL),(9,NULL,NULL,NULL,NULL),(10,NULL,NULL,NULL,NULL),(11,'2023-09-13',5000,11,NULL),(12,'2023-09-13',5000,12,NULL),(13,'2023-09-13',5000,13,NULL),(14,'2023-09-13',5000,14,NULL),(15,'2023-09-13',5000,15,NULL),(16,'2023-09-13',5000,16,NULL),(17,'2023-09-13',5000,17,NULL),(18,'2023-09-13',5000,18,NULL),(19,'2023-09-13',5000,19,NULL);
/*!40000 ALTER TABLE `savings_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transactions` (
  `transaction_id` int NOT NULL AUTO_INCREMENT,
  `amount` int DEFAULT NULL,
  `date_of_transaction` date DEFAULT NULL,
  `receiver_acc` varchar(255) DEFAULT NULL,
  `account_detail` bigint DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `FKkfeynjffud7eie0y67iaog5di` (`account_detail`),
  CONSTRAINT `FKkfeynjffud7eie0y67iaog5di` FOREIGN KEY (`account_detail`) REFERENCES `savings_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-13 22:53:03
