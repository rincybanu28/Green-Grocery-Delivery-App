-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: greengrocer_delivery_application
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `cart_table`
--

DROP TABLE IF EXISTS `cart_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_table` (
  `mrp_price` double NOT NULL,
  `quantity` int NOT NULL,
  `cart_id` bigint NOT NULL,
  `customer_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`cart_id`),
  KEY `FKmds17phn1qjqfpvfeavvjdf27` (`customer_id`),
  KEY `FK5lx7n9l55as34h32k1vu7ts60` (`product_id`),
  CONSTRAINT `FK5lx7n9l55as34h32k1vu7ts60` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `FKmds17phn1qjqfpvfeavvjdf27` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_table`
--

LOCK TABLES `cart_table` WRITE;
/*!40000 ALTER TABLE `cart_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customer_id` bigint NOT NULL,
  `customer_address` varchar(255) DEFAULT NULL,
  `customer_email` varchar(255) DEFAULT NULL,
  `customer_first_name` varchar(255) DEFAULT NULL,
  `customer_last_name` varchar(255) DEFAULT NULL,
  `customer_password` varchar(255) DEFAULT NULL,
  `customer_phone_number` varchar(255) DEFAULT NULL,
  `pincode` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (200,'MG Road','admin@gmail.com','Admin','Admin','admin1234','9990909090','425590','admin'),(201,'123 block','mary@gmail.com','Mary','jane','mary123','9878987678','347656','customer');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery_boy`
--

DROP TABLE IF EXISTS `delivery_boy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `delivery_boy` (
  `delivery_boy_id` bigint NOT NULL,
  `delivery_boy_address` varchar(255) DEFAULT NULL,
  `delivery_boy_city` varchar(255) DEFAULT NULL,
  `delivery_boy_email` varchar(255) DEFAULT NULL,
  `delivery_boy_name` varchar(255) DEFAULT NULL,
  `delivery_boy_password` varchar(255) DEFAULT NULL,
  `delivey_boy_contact` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`delivery_boy_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_boy`
--

LOCK TABLES `delivery_boy` WRITE;
/*!40000 ALTER TABLE `delivery_boy` DISABLE KEYS */;
INSERT INTO `delivery_boy` VALUES (1,'main 123, old layout','bangalore','manoj@gmail.com','Manoj raj','manoj1234','9876787656');
/*!40000 ALTER TABLE `delivery_boy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery_table`
--

DROP TABLE IF EXISTS `delivery_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `delivery_table` (
  `delivery_boy_id` bigint DEFAULT NULL,
  `delivery_id` bigint NOT NULL,
  `order_id` bigint DEFAULT NULL,
  `delivery_date` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`delivery_id`),
  KEY `FKag630sgeswl3d47v2h2o4qy24` (`delivery_boy_id`),
  KEY `FK7qywga3x1juymvb6pm56covcb` (`order_id`),
  CONSTRAINT `FK7qywga3x1juymvb6pm56covcb` FOREIGN KEY (`order_id`) REFERENCES `order_table` (`order_id`),
  CONSTRAINT `FKag630sgeswl3d47v2h2o4qy24` FOREIGN KEY (`delivery_boy_id`) REFERENCES `delivery_boy` (`delivery_boy_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_table`
--

LOCK TABLES `delivery_table` WRITE;
/*!40000 ALTER TABLE `delivery_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `delivery_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `generator4`
--

DROP TABLE IF EXISTS `generator4`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `generator4` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `generator4`
--

LOCK TABLES `generator4` WRITE;
/*!40000 ALTER TABLE `generator4` DISABLE KEYS */;
INSERT INTO `generator4` VALUES (51);
/*!40000 ALTER TABLE `generator4` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_table`
--

DROP TABLE IF EXISTS `order_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_table` (
  `mrp_price` double NOT NULL,
  `ordered_date` date DEFAULT NULL,
  `quantity` float NOT NULL,
  `total_price` double NOT NULL,
  `total_quantity` float NOT NULL,
  `cart_id` bigint DEFAULT NULL,
  `customer_id` bigint NOT NULL,
  `order_id` bigint NOT NULL,
  `order_status` varchar(255) DEFAULT NULL,
  `payment_status` varchar(255) DEFAULT NULL,
  `product_image` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  UNIQUE KEY `UK_6xkx6cvee1rbuc2u5yyvm3blf` (`cart_id`),
  KEY `FKdit09e676nqbguvt1k1w8mlj2` (`customer_id`),
  CONSTRAINT `FKdit09e676nqbguvt1k1w8mlj2` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  CONSTRAINT `FKg5uc8bwi6khvvjdnfjw9340pb` FOREIGN KEY (`cart_id`) REFERENCES `cart_table` (`cart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_table`
--

LOCK TABLES `order_table` WRITE;
/*!40000 ALTER TABLE `order_table` DISABLE KEYS */;
INSERT INTO `order_table` VALUES (95,'2024-03-18',2,610,0,NULL,201,500,'Delivered','PAID','../../../assets/Images/orange1.png','Orange');
/*!40000 ALTER TABLE `order_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_table_product`
--

DROP TABLE IF EXISTS `order_table_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_table_product` (
  `order_order_id` bigint NOT NULL,
  `product_product_id` bigint NOT NULL,
  KEY `FK1iba44anfp5eov77ad3hujfud` (`product_product_id`),
  KEY `FKcofxwxfduwidkroqek8ad8c25` (`order_order_id`),
  CONSTRAINT `FK1iba44anfp5eov77ad3hujfud` FOREIGN KEY (`product_product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `FKcofxwxfduwidkroqek8ad8c25` FOREIGN KEY (`order_order_id`) REFERENCES `order_table` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_table_product`
--

LOCK TABLES `order_table_product` WRITE;
/*!40000 ALTER TABLE `order_table_product` DISABLE KEYS */;
INSERT INTO `order_table_product` VALUES (500,600),(500,603),(500,607);
/*!40000 ALTER TABLE `order_table_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `paid_amount` double NOT NULL,
  `paid_date` date DEFAULT NULL,
  `total_price` double NOT NULL,
  `customer_id` bigint DEFAULT NULL,
  `order_id` bigint NOT NULL,
  `payment_id` bigint NOT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `FKby2skjf3ov608yb6nm16b49lg` (`customer_id`),
  CONSTRAINT `FKby2skjf3ov608yb6nm16b49lg` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (610,'2024-03-19',610,201,500,800);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `category` tinyint DEFAULT NULL,
  `price` double NOT NULL,
  `product_quantity` int NOT NULL,
  `buy_date` datetime(6) NOT NULL,
  `product_id` bigint NOT NULL,
  `supplier_id` bigint DEFAULT NULL,
  `product_name` varchar(30) NOT NULL,
  `product_image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `FK2kxvbr72tmtscjvyp9yqb12by` (`supplier_id`),
  CONSTRAINT `FK2kxvbr72tmtscjvyp9yqb12by` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (0,120,29,'2024-03-18 05:30:00.000000',600,700,'Apple','../../../assets/Images/apple1.png'),(0,45,50,'2024-03-18 05:30:00.000000',601,700,'Banana','../../../assets/Images/banana.png'),(1,100,47,'2024-03-18 05:30:00.000000',603,700,'Onion','../../../assets/Images/onion.png'),(1,500,6,'2024-03-18 05:30:00.000000',604,700,'Tomato','../../../assets/Images/tomato.png'),(0,150,40,'2024-03-18 05:30:00.000000',605,700,'Pomegranate','../../../assets/Images/pomegranate.png'),(1,80,59,'2024-03-18 05:30:00.000000',606,700,'Potato','../../../assets/Images/potato.png'),(0,95,48,'2024-03-18 05:30:00.000000',607,700,'Orange','../../../assets/Images/orange1.png'),(1,110,60,'2024-03-18 05:30:00.000000',608,700,'Broccoli','../../../assets/Images/broccoli.png');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seq11`
--

DROP TABLE IF EXISTS `seq11`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seq11` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seq11`
--

LOCK TABLES `seq11` WRITE;
/*!40000 ALTER TABLE `seq11` DISABLE KEYS */;
INSERT INTO `seq11` VALUES (1600);
/*!40000 ALTER TABLE `seq11` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seq2`
--

DROP TABLE IF EXISTS `seq2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seq2` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seq2`
--

LOCK TABLES `seq2` WRITE;
/*!40000 ALTER TABLE `seq2` DISABLE KEYS */;
INSERT INTO `seq2` VALUES (300);
/*!40000 ALTER TABLE `seq2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seq3`
--

DROP TABLE IF EXISTS `seq3`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seq3` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seq3`
--

LOCK TABLES `seq3` WRITE;
/*!40000 ALTER TABLE `seq3` DISABLE KEYS */;
INSERT INTO `seq3` VALUES (350);
/*!40000 ALTER TABLE `seq3` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seq5`
--

DROP TABLE IF EXISTS `seq5`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seq5` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seq5`
--

LOCK TABLES `seq5` WRITE;
/*!40000 ALTER TABLE `seq5` DISABLE KEYS */;
INSERT INTO `seq5` VALUES (550);
/*!40000 ALTER TABLE `seq5` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seq6`
--

DROP TABLE IF EXISTS `seq6`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seq6` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seq6`
--

LOCK TABLES `seq6` WRITE;
/*!40000 ALTER TABLE `seq6` DISABLE KEYS */;
INSERT INTO `seq6` VALUES (700);
/*!40000 ALTER TABLE `seq6` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seq7`
--

DROP TABLE IF EXISTS `seq7`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seq7` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seq7`
--

LOCK TABLES `seq7` WRITE;
/*!40000 ALTER TABLE `seq7` DISABLE KEYS */;
INSERT INTO `seq7` VALUES (800);
/*!40000 ALTER TABLE `seq7` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seq8`
--

DROP TABLE IF EXISTS `seq8`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seq8` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seq8`
--

LOCK TABLES `seq8` WRITE;
/*!40000 ALTER TABLE `seq8` DISABLE KEYS */;
INSERT INTO `seq8` VALUES (850);
/*!40000 ALTER TABLE `seq8` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `supplier_id` bigint NOT NULL,
  `supplier_name` varchar(30) NOT NULL,
  `supplier_city` varchar(50) NOT NULL,
  `supplier_email` varchar(50) NOT NULL,
  `supplier_state` varchar(50) NOT NULL,
  `supplier_address` varchar(150) NOT NULL,
  `supplier_phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (700,'Rincy Banu','bangalore','rincybanu663@gmail.com','karnataka','Block b, 123 layout','9562644221'),(702,'Anu thomas','Mumbai','anuthomas@gmail.com','Maharashtra','BLOCK 1234','7656789876');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-19  9:08:40
