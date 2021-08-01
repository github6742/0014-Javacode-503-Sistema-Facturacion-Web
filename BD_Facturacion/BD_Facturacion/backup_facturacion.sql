-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: facturacion
-- ------------------------------------------------------
-- Server version	5.6.31-log

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `codCliente` int(11) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(40) COLLATE utf8_spanish_ci NOT NULL,
  `apellidos` varchar(40) COLLATE utf8_spanish_ci NOT NULL,
  `direccion` varchar(150) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`codCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (2,'CARLOS ANTONIO','SERMEÑO AGUILAR','SANTA ANA'),(3,'SANDRA','BONILLA','SAN SALVADOR'),(4,'JOSUE','COREAS','SAN MIGUEL'),(5,'MARIA ','ARTERO GOMEZ','SAN SALVADOR'),(6,'juan carlos','aguirre','col. el palmar');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detallefactura`
--

DROP TABLE IF EXISTS `detallefactura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detallefactura` (
  `codDetalle` int(11) NOT NULL AUTO_INCREMENT,
  `codFactura` int(11) NOT NULL,
  `codProducto` int(11) NOT NULL,
  `codBarra` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `nombreProducto` varchar(75) COLLATE utf8_spanish_ci NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precioVenta` decimal(10,2) NOT NULL,
  `total` decimal(10,2) NOT NULL,
  PRIMARY KEY (`codDetalle`),
  KEY `FK_detallefactura_factura` (`codFactura`),
  KEY `FK_detallefactura_producto` (`codBarra`),
  KEY `FK_detallefactura_prodcto` (`codProducto`),
  CONSTRAINT `FK_detallefactura_factura` FOREIGN KEY (`codFactura`) REFERENCES `factura` (`codFactura`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_detallefactura_prodcto` FOREIGN KEY (`codProducto`) REFERENCES `producto` (`codProducto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detallefactura`
--

LOCK TABLES `detallefactura` WRITE;
/*!40000 ALTER TABLE `detallefactura` DISABLE KEYS */;
INSERT INTO `detallefactura` VALUES (1,39,1,'10101010','TECLADO USB',10,6.55,65.50),(2,40,1,'10101010','TECLADO USB',5,6.55,32.75),(3,40,3,'10101012','PANTALLA LED 24 °',2,250.30,500.60),(4,40,1,'10101010','TECLADO USB',2,6.55,13.10),(5,41,1,'10101010','TECLADO USB',3,6.55,19.65),(6,42,1,'10101010','TECLADO USB',5,6.55,32.75),(7,43,1,'10101010','TECLADO USB',5,6.55,32.75),(8,43,3,'10101012','PANTALLA LED 24 °',5,250.30,1251.50),(9,44,1,'10101010','TECLADO USB',2,6.55,13.10),(10,45,1,'10101010','TECLADO USB',3,6.55,19.65),(11,46,3,'10101012','PANTALLA LED 24 °',1,250.30,250.30),(12,47,1,'10101010','TECLADO USB',1,6.55,6.55),(13,48,1,'10101010','TECLADO USB',2,6.55,13.10),(14,48,3,'10101012','PANTALLA LED 24 °',1,250.30,250.30),(15,49,3,'10101012','PANTALLA LED 24 °',3,250.30,750.90),(16,49,1,'10101010','TECLADO USB',3,6.55,19.65),(17,50,1,'10101010','TECLADO USB',1,6.55,6.55),(18,51,3,'10101012','PANTALLA LED 24 °',1,250.30,250.30),(19,52,3,'10101012','PANTALLA LED 24 °',1,250.30,250.30),(20,53,1,'10101010','TECLADO USB',2,6.55,13.10),(21,53,3,'10101012','PANTALLA LED 24 °',1,250.30,250.30),(22,54,1,'10101010','TECLADO USB',1,6.55,6.55),(23,55,3,'10101012','PANTALLA LED 24 °',1,250.30,250.30),(24,56,1,'10101010','TECLADO USB',2,6.55,13.10),(25,56,3,'10101012','PANTALLA LED 24 °',3,250.30,750.90),(26,57,3,'10101012','PANTALLA LED 24 °',1,250.30,250.30),(27,58,5,'10101014','LAMPARA CF',2,75.00,150.00),(28,59,6,'10101015','MEMORIA RAM DDR3',2,20.00,40.00),(29,60,5,'10101014','LAMPARA CF',3,75.00,225.00),(30,61,4,'10101013','CASE ATX',2,20.50,41.00),(31,62,6,'10101015','MEMORIA RAM DDR3',2,20.00,40.00),(32,62,7,'10101016','DICO DURO 300GB',3,50.00,150.00),(33,62,6,'10101015','MEMORIA RAM DDR3',2,20.00,40.00),(34,62,3,'10101012','PANTALLA LED 24 °',2,250.30,500.60),(35,63,1,'10101010','TECLADO USB',2,6.55,13.10),(36,63,4,'10101013','CASE ATX',2,20.50,41.00),(37,64,5,'10101014','LAMPARA CF',5,75.00,375.00),(38,65,6,'10101015','MEMORIA RAM DDR3',2,20.00,40.00),(39,66,6,'10101015','MEMORIA RAM DDR3',2,20.00,40.00),(40,66,7,'10101016','DICO DURO 300GB',2,50.00,100.00),(41,66,3,'10101012','PANTALLA LED 24 °',2,250.30,500.60),(42,67,3,'10101012','PANTALLA LED 24 °',2,250.30,500.60),(43,67,7,'10101016','DICO DURO 300GB',2,50.00,100.00),(44,67,5,'10101014','LAMPARA CF',4,75.00,300.00),(45,68,1,'10101010','TECLADO USB',2,6.55,13.10),(46,68,5,'10101014','LAMPARA CF',1,75.00,75.00),(47,68,6,'10101015','MEMORIA RAM DDR3',1,20.00,20.00),(48,68,7,'10101016','DICO DURO 300GB',2,50.00,100.00),(49,69,7,'10101016','DICO DURO 300GB',10,50.00,500.00),(50,70,7,'10101016','DICO DURO 300GB',10,50.00,500.00),(51,71,3,'10101012','PANTALLA LED 24 °',10,250.30,2503.00),(52,72,3,'10101012','PANTALLA LED 24 °',2,250.30,500.60),(53,73,7,'10101016','DICO DURO 300GB',6,50.00,300.00),(54,74,6,'10101015','MEMORIA RAM DDR3',3,20.00,60.00),(55,74,1,'10101010','TECLADO USB',1,6.55,6.55),(56,75,3,'10101012','PANTALLA LED 24 °',4,250.30,1001.20),(57,76,3,'10101012','PANTALLA LED 24 °',1,250.30,250.30),(58,76,7,'10101016','DICO DURO 300GB',2,50.00,100.00),(59,77,1,'10101010','TECLADO USB',3,6.55,19.65);
/*!40000 ALTER TABLE `detallefactura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factura`
--

DROP TABLE IF EXISTS `factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `factura` (
  `codFactura` int(11) NOT NULL AUTO_INCREMENT,
  `numeroFactura` int(11) NOT NULL,
  `codVendedor` int(11) NOT NULL,
  `codCliente` int(11) NOT NULL,
  `totalVenta` decimal(10,2) NOT NULL,
  `fechaRegistro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`codFactura`),
  KEY `FK_factura_vendedor` (`codVendedor`),
  KEY `FK_factura_cliente` (`codCliente`),
  CONSTRAINT `FK_factura_cliente` FOREIGN KEY (`codCliente`) REFERENCES `cliente` (`codCliente`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_factura_vendedor` FOREIGN KEY (`codVendedor`) REFERENCES `vendedor` (`codVendedor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura`
--

LOCK TABLES `factura` WRITE;
/*!40000 ALTER TABLE `factura` DISABLE KEYS */;
INSERT INTO `factura` VALUES (39,1,2,2,65.50,'2015-11-28 22:35:57'),(40,2,2,3,546.45,'2015-11-28 22:35:57'),(41,3,2,2,19.65,'2015-11-28 22:35:57'),(42,4,2,2,32.75,'2015-11-28 22:35:57'),(43,5,2,3,1284.25,'2015-11-28 22:35:57'),(44,6,2,3,13.10,'2015-11-28 22:35:57'),(45,7,1,2,19.65,'2015-11-28 22:35:57'),(46,8,1,3,250.30,'2015-11-28 22:35:57'),(47,9,2,2,6.55,'2015-11-28 22:35:57'),(48,10,1,2,263.40,'2015-11-28 22:35:57'),(49,11,2,3,770.55,'2015-11-28 22:35:57'),(50,12,1,2,6.55,'2015-11-28 22:35:57'),(51,13,1,3,250.30,'2015-11-28 22:35:57'),(52,14,1,2,250.30,'2015-11-28 22:35:57'),(53,15,2,3,263.40,'2015-11-28 22:35:57'),(54,16,2,2,6.55,'2015-11-28 22:35:57'),(55,17,2,3,250.30,'2015-11-28 22:35:57'),(56,18,2,2,764.00,'2015-11-28 22:35:57'),(57,19,2,3,250.30,'2015-11-28 22:35:57'),(58,20,2,4,150.00,'2015-11-28 22:35:57'),(59,21,2,5,40.00,'2015-11-28 22:35:57'),(60,22,2,3,225.00,'2015-11-28 22:35:57'),(61,23,1,2,41.00,'2015-11-28 22:35:57'),(62,24,1,5,730.60,'2015-11-28 22:35:57'),(63,25,1,3,54.10,'2015-11-28 22:35:57'),(64,26,2,5,375.00,'2015-11-28 22:35:57'),(65,27,2,4,40.00,'2015-11-28 22:35:57'),(66,28,2,2,640.60,'2015-11-28 22:35:57'),(67,29,2,2,900.60,'2015-11-28 22:35:57'),(68,30,2,4,208.10,'2015-11-28 22:35:57'),(69,31,2,4,500.00,'2015-11-28 22:35:57'),(70,32,1,2,500.00,'2015-11-28 22:35:57'),(71,33,1,5,2503.00,'2015-11-28 22:35:57'),(72,34,1,3,500.60,'2015-11-28 22:35:57'),(73,35,1,4,300.00,'2015-11-28 22:35:57'),(74,36,1,2,66.55,'2015-11-28 22:35:57'),(75,37,1,3,1001.20,'2015-11-28 22:35:57'),(76,38,1,6,350.30,'2015-11-28 22:35:57'),(77,39,1,3,19.65,'2019-09-06 21:44:26');
/*!40000 ALTER TABLE `factura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producto` (
  `codProducto` int(11) NOT NULL AUTO_INCREMENT,
  `nombreProducto` varchar(75) COLLATE utf8_spanish_ci NOT NULL,
  `precioVenta` decimal(10,2) NOT NULL,
  `stockMinimo` int(11) NOT NULL,
  `stockActual` int(11) NOT NULL,
  `codBarra` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`codProducto`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'TECLADO USB',6.55,10,43,'10101010'),(3,'PANTALLA LED 24 °',250.30,10,94,'10101012'),(4,'CASE ATX',20.50,10,46,'10101013'),(5,'LAMPARA CF',75.00,5,5,'10101014'),(6,'MEMORIA RAM DDR3',20.00,15,39,'10101015'),(7,'DICO DURO 300GB',50.00,25,126,'10101016');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `codUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombreUsuario` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `codVendedor` int(11) NOT NULL,
  PRIMARY KEY (`codUsuario`),
  KEY `FK_usuario_vendedor` (`codVendedor`),
  CONSTRAINT `FK_usuario_vendedor` FOREIGN KEY (`codVendedor`) REFERENCES `vendedor` (`codVendedor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'BCAGUILAR','3627909a29c31381a071ec27f7c9ca97726182aed29a7ddd2e54353322cfb30abb9e3a6df2ac2c20fe23436311d678564d0c8d305930575f60e2d3d048184d79',1),(2,'PMLEMUS','878ae65a92e86cac011a570d4c30a7eaec442b85ce8eca0c2952b5e3cc0628c2e79d889ad4d5c7c626986d452dd86374b6ffaa7cd8b67665bef2289a5c70b0a1',2),(3,'CEAGUIRRE','e16d6b316f3bef1794c548b7a98b969a6aacb02f6ae5138efc1c443ae6643a6a77d92a0e33e382d6cbb7758f9ab25ab0f97504554d1904620a41fed463796fc2',3),(4,'LEMUSP','81369ddc64ebb26e7ff2d0b2cc3db8add14e13754114f45a297d3f18684ccc93b4b66725ac43e9d6ed4309d5e6e1b9b33473025e8e343110f515e0bf1e7332cd',2);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendedor`
--

DROP TABLE IF EXISTS `vendedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendedor` (
  `codVendedor` int(11) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(40) COLLATE utf8_spanish_ci NOT NULL,
  `apellidos` varchar(40) COLLATE utf8_spanish_ci NOT NULL,
  `dui` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `celular` varchar(8) COLLATE utf8_spanish_ci NOT NULL,
  `direccion` varchar(150) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`codVendedor`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendedor`
--

LOCK TABLES `vendedor` WRITE;
/*!40000 ALTER TABLE `vendedor` DISABLE KEYS */;
INSERT INTO `vendedor` VALUES (1,'BLANCA CAROLINA','AGUILAR','012451454','78455124','SAN SALVADOR'),(2,'PEDRO MIGUEL','LEMUS ROJAS','45126398','78451253','SAN SALVADOR'),(3,'CECILIA','AGUIRRE','15231315','7894566','SAN MIGUEL');
/*!40000 ALTER TABLE `vendedor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-06 16:36:40
