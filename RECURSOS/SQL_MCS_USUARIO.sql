CREATE DATABASE  IF NOT EXISTS `ms_ecc_usuarios` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ms_ecc_usuarios`;
-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: ms_ecc_usuarios
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


-- Tabla permiso
DROP TABLE IF EXISTS `permiso`;
CREATE TABLE `permiso` (
  `idPermiso` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `ruta` text,
  `tipo` smallint DEFAULT NULL,
  PRIMARY KEY (`idPermiso`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb3;

-- Datos para permiso
LOCK TABLES `permiso` WRITE;
INSERT INTO `permiso` VALUES 
(1, 'Registro Alumno', '1', 'verRegistroAlumno', 1),
(2, 'Registro Libro', '1', 'verRegistroLibro', 1),
(3, 'Registro Tesis', '1', 'verRegistroTesis', 1);
UNLOCK TABLES;

-- Tabla rol
DROP TABLE IF EXISTS `rol`;
CREATE TABLE `rol` (
  `idRol` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idRol`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- Datos para rol
LOCK TABLES `rol` WRITE;
INSERT INTO `rol` VALUES 
(1, 'Administrador General', '1'),
(2, 'Administrador', '1'),
(3, 'Bibliotecólogo', '1');
UNLOCK TABLES;

-- Tabla rol_has_permiso
DROP TABLE IF EXISTS `rol_has_permiso`;
CREATE TABLE `rol_has_permiso` (
  `idRol` int NOT NULL,
  `idPermiso` int NOT NULL,
  PRIMARY KEY (`idRol`, `idPermiso`),
  KEY `fk_rol_has_permiso_permiso1_idx` (`idPermiso`),
  KEY `fk_rol_has_permiso_rol1_idx` (`idRol`),
  CONSTRAINT `fk_rol_has_permiso_permiso1` FOREIGN KEY (`idPermiso`) REFERENCES `permiso` (`idPermiso`),
  CONSTRAINT `fk_rol_has_permiso_rol1` FOREIGN KEY (`idRol`) REFERENCES `rol` (`idRol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Datos para rol_has_permiso
LOCK TABLES `rol_has_permiso` WRITE;
INSERT INTO `rol_has_permiso` VALUES 
(1, 1), (2, 1), 
(1, 2), (2, 2), 
(1, 3), (2, 3);
UNLOCK TABLES;

-- Tabla usuario
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `nombres` varchar(100) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `dni` varchar(8) NOT NULL,
  `login` varchar(15) NOT NULL,
  `password` varchar(200) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `fechaRegistro` datetime NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `direccion` text NOT NULL,
  `idRol` int NOT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `idUsuario_UNIQUE` (`idUsuario`),
  KEY `fk_usuario_rol_idx` (`idRol`),
  CONSTRAINT `fk_usuario_rol` FOREIGN KEY (`idRol`) REFERENCES `rol` (`idRol`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

-- Datos para usuario
LOCK TABLES `usuario` WRITE;
INSERT INTO `usuario` VALUES 
(1, 'Luis Alberto', 'Sanchez Quispe', '74747474', 'luis', '$2a$10$Z7/zwEFEV3L14ghsKapFj.tZKLiBvqCR84PwN9jG/bZA8ePDIUqru', 'luis@gmail.com', '2022-04-04 10:59:07', '2000-10-10', 'Av Lima 123'),
(2, 'Ana Elba', 'Flores Enero', '87485965', 'ana', '$2a$10$LKANug7Towq30DMm/GsCNOqJ6ybfWjqC/8FKZX78Fp3r4ZPvVfHSO', 'ana@gmail.com', '2022-04-04 10:59:07', '1997-10-10', 'Av Lince 787'),
(3, 'Juana', 'Arcos Gutierrez', '85747478', 'juana', '$2a$10$KNzO4mDKYpKnSmkvPTUzM.zos8iga4l5VTZX81R6tbru4j2aUajCu', 'juana@gmail.com', '2022-04-04 10:59:07', '1997-10-10', 'Av Mangomarca 987');
UNLOCK TABLES;





