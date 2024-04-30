-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: company_report
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Temporary view structure for view `order_view`
--

DROP TABLE IF EXISTS `order_view`;
/*!50001 DROP VIEW IF EXISTS `order_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `order_view` AS SELECT 
 1 AS `HEADER_ID`,
 1 AS `ORDER_NUMBER`,
 1 AS `CUSTOMER_ID`,
 1 AS `ORDER_DATE`,
 1 AS `ORDER_STATUS`,
 1 AS `DESCRIPTION`,
 1 AS `CREATED_BY`,
 1 AS `CREATION_DATE`,
 1 AS `LAST_UPDATED_BY`,
 1 AS `LAST_UPDATE_DATE`,
 1 AS `LAST_UPDATE_LOGIN`,
 1 AS `ATTRIBUTE_CATEGORY`,
 1 AS `ATTRIBUTE1`,
 1 AS `ATTRIBUTE2`,
 1 AS `ATTRIBUTE3`,
 1 AS `ATTRIBUTE4`,
 1 AS `ATTRIBUTE5`,
 1 AS `ATTRIBUTE6`,
 1 AS `ATTRIBUTE7`,
 1 AS `ATTRIBUTE8`,
 1 AS `ATTRIBUTE9`,
 1 AS `ATTRIBUTE10`,
 1 AS `ATTRIBUTE11`,
 1 AS `ATTRIBUTE12`,
 1 AS `ATTRIBUTE13`,
 1 AS `ATTRIBUTE14`,
 1 AS `ATTRIBUTE15`,
 1 AS `CUSTOMER_NUMBER`,
 1 AS `CUSTOMER_NAME`,
 1 AS `TELEPHONE`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `order_view`
--

/*!50001 DROP VIEW IF EXISTS `order_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `order_view` AS select `oh`.`HEADER_ID` AS `HEADER_ID`,`oh`.`ORDER_NUMBER` AS `ORDER_NUMBER`,`oh`.`CUSTOMER_ID` AS `CUSTOMER_ID`,`oh`.`ORDER_DATE` AS `ORDER_DATE`,`oh`.`ORDER_STATUS` AS `ORDER_STATUS`,`oh`.`DESCRIPTION` AS `DESCRIPTION`,`oh`.`CREATED_BY` AS `CREATED_BY`,`oh`.`CREATION_DATE` AS `CREATION_DATE`,`oh`.`LAST_UPDATED_BY` AS `LAST_UPDATED_BY`,`oh`.`LAST_UPDATE_DATE` AS `LAST_UPDATE_DATE`,`oh`.`LAST_UPDATE_LOGIN` AS `LAST_UPDATE_LOGIN`,`oh`.`ATTRIBUTE_CATEGORY` AS `ATTRIBUTE_CATEGORY`,`oh`.`ATTRIBUTE1` AS `ATTRIBUTE1`,`oh`.`ATTRIBUTE2` AS `ATTRIBUTE2`,`oh`.`ATTRIBUTE3` AS `ATTRIBUTE3`,`oh`.`ATTRIBUTE4` AS `ATTRIBUTE4`,`oh`.`ATTRIBUTE5` AS `ATTRIBUTE5`,`oh`.`ATTRIBUTE6` AS `ATTRIBUTE6`,`oh`.`ATTRIBUTE7` AS `ATTRIBUTE7`,`oh`.`ATTRIBUTE8` AS `ATTRIBUTE8`,`oh`.`ATTRIBUTE9` AS `ATTRIBUTE9`,`oh`.`ATTRIBUTE10` AS `ATTRIBUTE10`,`oh`.`ATTRIBUTE11` AS `ATTRIBUTE11`,`oh`.`ATTRIBUTE12` AS `ATTRIBUTE12`,`oh`.`ATTRIBUTE13` AS `ATTRIBUTE13`,`oh`.`ATTRIBUTE14` AS `ATTRIBUTE14`,`oh`.`ATTRIBUTE15` AS `ATTRIBUTE15`,`c`.`CUSTOMER_NUMBER` AS `CUSTOMER_NUMBER`,`c`.`CUSTOMER_NAME` AS `CUSTOMER_NAME`,`c`.`TELEPHONE` AS `TELEPHONE` from (`order_header` `oh` join `customer` `c` on((`oh`.`CUSTOMER_ID` = `c`.`CUSTOMER_ID`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-26 14:27:34
