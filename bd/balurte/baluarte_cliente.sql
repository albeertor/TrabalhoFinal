-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: baluarte
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.10-MariaDB

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
  `cdcliente` int(11) NOT NULL AUTO_INCREMENT,
  `rg` varchar(15) NOT NULL,
  `cpf` varchar(15) NOT NULL,
  `dtnasc` date NOT NULL,
  `endereco` varchar(45) NOT NULL,
  `cdCidade` int(11) NOT NULL,
  `nmCliente` varchar(20) NOT NULL,
  `fone` varchar(15) NOT NULL,
  `cep` varchar(15) NOT NULL,
  PRIMARY KEY (`cdcliente`),
  KEY `cdcliente_idx` (`cdcliente`),
  KEY `cdcid_idx` (`cdCidade`),
  CONSTRAINT `cdcid` FOREIGN KEY (`cdCidade`) REFERENCES `cidade` (`cdcidade`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'m16716081 ','123.864.266-70','1999-11-20','Rua Guaranis, 170',1,'Peixe','(31) 3825-6237','35162-070'),(2,'m16795633 ','228.276.365-30','2002-12-09','Rua 12, 22',4,'Rodrigo','(11) 4002-8922','12312-313'),(4,'m12346578 ','664.661.636-10','1998-09-21','Rua China',5,'Lucas','(31) 9999-9999','35162-876'),(5,'m1525665  ','313.416.393-47','2012-12-12','Rua Inglaterra',3,'José','(31) 8745-6265','12312-332'),(6,'m1268698  ','062.191.475-45','1999-03-28','Rua Cava Grande, 679',2,'André Luiz','(31) 8620-8033','32165-191'),(7,'m5166123  ','289.467.703-09','1999-02-07','Rua Silas',1,'Robson','(15) 4568-7955','12315-644'),(8,'m5498153  ','278.394.136-84','2015-12-01','Rua Jaspe, 15',5,'Gabriela','(31) 8496-8646','21368-796'),(10,'m55646546 ','123.864.266-70','1999-02-14','Rua Inglaterra',4,'Pedro','(31) 9878-9495','35142-414'),(11,'1231132123','123.864.266-70','0416-04-02','Rua Jacruysbor',7,'Judesmar','(64) 7285-4919','35486-159'),(12,'1233456','123.864.266-70','2016-04-04','Rua Jamaica',6,'Antonio','31988428792','35162-070');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-22 21:18:02
