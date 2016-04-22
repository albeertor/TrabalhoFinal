CREATE DATABASE  IF NOT EXISTS `baluarte` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `baluarte`;
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
-- Table structure for table `cidade`
--

DROP TABLE IF EXISTS `cidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cidade` (
  `cdcidade` int(11) NOT NULL AUTO_INCREMENT,
  `nmcidade` varchar(45) NOT NULL,
  `sgestado` varchar(2) NOT NULL,
  PRIMARY KEY (`cdcidade`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cidade`
--

LOCK TABLES `cidade` WRITE;
/*!40000 ALTER TABLE `cidade` DISABLE KEYS */;
INSERT INTO `cidade` VALUES (1,'IPATINGA','MG'),(2,'TIMOTEO','MG'),(3,'CEL FABRICIANO','MG'),(4,'RIO DE JANEIRO','RJ'),(5,'BELO HORIZONTE','MG'),(6,'IPABA','MG'),(7,'JUDESCITY','JC');
/*!40000 ALTER TABLE `cidade` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `cliente` VALUES (1,'m16716081 ','123.864.266-70','1999-11-20','Rua Guaranis, 170',1,'Juliana','(31) 3825-6237','35162-070'),(2,'m16795633 ','228.276.365-30','2002-12-02','Rua 12, 22',4,'Rodrigo','(11) 4002-8922','12312-313'),(4,'m12346578 ','664.661.636-10','1998-09-21','Rua China',5,'Lucas','(31) 9999-9999','35162-876'),(5,'m1525665  ','313.416.393-47','2012-12-12','Rua Inglaterra',3,'José','(31) 8745-6265','12312-332'),(6,'m1268698  ','062.191.475-45','1999-03-28','Rua Cava Grande, 679',2,'André Luiz','(31) 8620-8033','32165-191'),(7,'m5166123  ','289.467.703-09','1999-02-07','Rua Silas',1,'Robson','(15) 4568-7955','12315-644'),(8,'m5498153  ','278.394.136-84','2015-12-01','Rua Jaspe, 15',5,'Gabriela','(31) 8496-8646','21368-796'),(10,'m55646546 ','123.864.266-70','1999-02-14','Rua Inglaterra',4,'Pedro','(31) 9878-9495','35142-414'),(11,'1231132123','123.864.266-70','0416-04-02','Rua Jacruysbor',7,'Judesmar','(64) 7285-4919','35486-159'),(12,'m16716081 ','123.864.266-70','1999-11-20','Rua Guaranis, 170',1,'Juliana','(31) 3825-6237','35162-070');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compra`
--

DROP TABLE IF EXISTS `compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compra` (
  `cdcompra` int(11) NOT NULL AUTO_INCREMENT,
  `dtcompra` date DEFAULT NULL,
  `vlcompra` double DEFAULT NULL,
  `cdfornecedor` int(11) DEFAULT NULL,
  PRIMARY KEY (`cdcompra`),
  KEY `cdforn_idx` (`cdfornecedor`),
  CONSTRAINT `cdforn` FOREIGN KEY (`cdfornecedor`) REFERENCES `fornecedor` (`cdfornecedor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compra`
--

LOCK TABLES `compra` WRITE;
/*!40000 ALTER TABLE `compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compra_produto`
--

DROP TABLE IF EXISTS `compra_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compra_produto` (
  `cdcompra` int(11) NOT NULL,
  `cdproduto` int(11) NOT NULL,
  `qtd` varchar(45) NOT NULL,
  KEY `cdcomp_idx` (`cdcompra`),
  KEY `cdprod_idx` (`cdproduto`),
  CONSTRAINT `cdcomp` FOREIGN KEY (`cdcompra`) REFERENCES `compra` (`cdcompra`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `cdprod` FOREIGN KEY (`cdproduto`) REFERENCES `produto` (`cdproduto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compra_produto`
--

LOCK TABLES `compra_produto` WRITE;
/*!40000 ALTER TABLE `compra_produto` DISABLE KEYS */;
/*!40000 ALTER TABLE `compra_produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornecedor`
--

DROP TABLE IF EXISTS `fornecedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fornecedor` (
  `cdfornecedor` int(11) NOT NULL AUTO_INCREMENT,
  `nmfornecedor` varchar(40) NOT NULL,
  `cdcidade` int(11) NOT NULL,
  `fone` varchar(11) NOT NULL,
  PRIMARY KEY (`cdfornecedor`),
  KEY `cdcid_idx` (`cdcidade`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedor`
--

LOCK TABLES `fornecedor` WRITE;
/*!40000 ALTER TABLE `fornecedor` DISABLE KEYS */;
INSERT INTO `fornecedor` VALUES (2,'Lucas',2,''),(3,'Luiz',3,''),(4,'Carlos',4,'');
/*!40000 ALTER TABLE `fornecedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcionario` (
  `Salario` double NOT NULL,
  `dtadmissao` date NOT NULL,
  `cdfunc` int(11) NOT NULL AUTO_INCREMENT,
  `nmPessoa` varchar(20) NOT NULL,
  `rg` varchar(8) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  `dtnasc` date NOT NULL,
  `endereco` varchar(45) NOT NULL,
  `cdCidade` int(11) NOT NULL,
  `fone` int(11) DEFAULT NULL,
  KEY `cdfunc_idx` (`cdfunc`),
  KEY `cdcidade_idx` (`cdCidade`),
  CONSTRAINT `cdcidade` FOREIGN KEY (`cdCidade`) REFERENCES `cidade` (`cdcidade`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` VALUES (2550,'2014-05-06',1,'Pedro','132','123','1999-07-02','Rua PK',2,NULL);
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto` (
  `cdproduto` int(11) NOT NULL AUTO_INCREMENT,
  `nmproduto` varchar(40) NOT NULL,
  `descricao` varchar(40) NOT NULL,
  PRIMARY KEY (`cdproduto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venda`
--

DROP TABLE IF EXISTS `venda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `venda` (
  `cdvenda` int(11) NOT NULL AUTO_INCREMENT,
  `situacao` varchar(40) DEFAULT NULL,
  `vltotal` double NOT NULL,
  `dtvenda` date NOT NULL,
  `formaDePagamento` varchar(40) NOT NULL,
  `cdcliente` int(11) NOT NULL,
  PRIMARY KEY (`cdvenda`),
  KEY `cdcliente_idx` (`cdcliente`),
  CONSTRAINT `cdcliente` FOREIGN KEY (`cdcliente`) REFERENCES `cliente` (`cdcliente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venda`
--

LOCK TABLES `venda` WRITE;
/*!40000 ALTER TABLE `venda` DISABLE KEYS */;
/*!40000 ALTER TABLE `venda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venda_produto`
--

DROP TABLE IF EXISTS `venda_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `venda_produto` (
  `cdvenda` int(11) NOT NULL,
  `cdproduto` int(11) NOT NULL,
  `qtd` int(11) NOT NULL,
  `vlunid` int(11) NOT NULL,
  KEY `cdproduto_idx` (`cdproduto`),
  KEY `cdvenda_idx` (`cdvenda`),
  CONSTRAINT `cdproduto` FOREIGN KEY (`cdproduto`) REFERENCES `produto` (`cdproduto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `cdvenda` FOREIGN KEY (`cdvenda`) REFERENCES `venda` (`cdvenda`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venda_produto`
--

LOCK TABLES `venda_produto` WRITE;
/*!40000 ALTER TABLE `venda_produto` DISABLE KEYS */;
/*!40000 ALTER TABLE `venda_produto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-21 23:56:11
