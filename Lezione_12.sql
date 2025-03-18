-- MySQL dump 10.13  Distrib 9.2.0, for macos15.2 (arm64)
--
-- Host: localhost    Database: music
-- ------------------------------------------------------
-- Server version	9.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `album`
--

DROP TABLE IF EXISTS `album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `album` (
  `id_album` int NOT NULL AUTO_INCREMENT,
  `nome_album` varchar(100) NOT NULL,
  `data_uscita` date NOT NULL,
  `genere` varchar(20) NOT NULL,
  `id_artista` int NOT NULL,
  `data_inserimento` timestamp NOT NULL,
  `data_aggiornamento` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id_album`),
  KEY `album_fk` (`id_artista`),
  CONSTRAINT `album_fk` FOREIGN KEY (`id_artista`) REFERENCES `artisti` (`id_artista`) ON DELETE CASCADE,
  CONSTRAINT `album_chk` CHECK ((`genere` in (_utf8mb4'ROCK',_utf8mb4'POP',_utf8mb4'BLUES',_utf8mb4'JAZZ',_utf8mb4'CLASSICA')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `artisti`
--

DROP TABLE IF EXISTS `artisti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artisti` (
  `id_artista` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `nazione` varchar(100) NOT NULL,
  `anno_inizio` int NOT NULL,
  `data_inserimento` timestamp NOT NULL,
  `data_aggiornamento` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id_artista`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tracce`
--

DROP TABLE IF EXISTS `tracce`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tracce` (
  `id_traccia` int NOT NULL AUTO_INCREMENT,
  `nome_traccia` varchar(100) NOT NULL,
  `id_album` int NOT NULL,
  `id_artista` int NOT NULL,
  `data_inserimento` timestamp NOT NULL,
  `data_aggiornamento` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id_traccia`),
  KEY `tracce_artisti_fk` (`id_artista`),
  KEY `tracce_album_fk` (`id_album`),
  CONSTRAINT `tracce_album_fk` FOREIGN KEY (`id_album`) REFERENCES `album` (`id_album`) ON DELETE CASCADE,
  CONSTRAINT `tracce_artisti_fk` FOREIGN KEY (`id_artista`) REFERENCES `artisti` (`id_artista`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'music'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-18 16:50:56
