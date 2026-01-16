-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-01-2026 a las 09:50:04
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gestionofertas_bd`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administrador`
--

CREATE TABLE `administrador` (
  `id_administrador` bigint(20) NOT NULL,
  `contrasena_admin` varchar(255) NOT NULL,
  `nombre_admin` varchar(255) NOT NULL,
  `id_usuario` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciudadano`
--

CREATE TABLE `ciudadano` (
  `id_ciudadano` bigint(20) NOT NULL,
  `apellidos` varchar(255) NOT NULL,
  `calle` varchar(255) NOT NULL,
  `codigo_postal` int(11) NOT NULL,
  `curriculum` longblob DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `localidad` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `numero` int(11) NOT NULL,
  `profesion` varchar(255) NOT NULL,
  `telefono` varchar(255) NOT NULL,
  `id_usuario` bigint(20) DEFAULT NULL,
  `curriculumurl` varchar(255) DEFAULT NULL,
  `validado` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ciudadano`
--

INSERT INTO `ciudadano` (`id_ciudadano`, `apellidos`, `calle`, `codigo_postal`, `curriculum`, `email`, `fecha_nacimiento`, `localidad`, `nombre`, `numero`, `profesion`, `telefono`, `id_usuario`, `curriculumurl`, `validado`) VALUES
(5, 'Tejero', 'Segovia', 41730, NULL, 'correo@gmail.com', '2025-10-02', 'Las Cabezas De San Juan', 'Jaime', 41730, 'Desarrollador Web', '681672137', 18, NULL, b'1'),
(6, 'Roman Tejero', 'Segovia', 11111, NULL, 'correo@gmail.com', '2025-10-01', 'Las Cabezas De San Juan', 'Jaime', 9, 'Desarrollador Web', '681672137', 25, NULL, b'1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa`
--

CREATE TABLE `empresa` (
  `id_empresa` bigint(20) NOT NULL,
  `actividad_empresa` varchar(255) NOT NULL,
  `calle` varchar(255) NOT NULL,
  `cif` varchar(255) NOT NULL,
  `codigo_empresa` varchar(255) DEFAULT NULL,
  `codigo_postal` int(11) NOT NULL,
  `direccion_web` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `localidad` varchar(255) NOT NULL,
  `numero` int(11) NOT NULL,
  `poligono` varchar(255) NOT NULL,
  `telefono` varchar(255) NOT NULL,
  `validado` bit(1) NOT NULL,
  `id_usuario` bigint(20) DEFAULT NULL,
  `nombre_completo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `empresa`
--

INSERT INTO `empresa` (`id_empresa`, `actividad_empresa`, `calle`, `cif`, `codigo_empresa`, `codigo_postal`, `direccion_web`, `email`, `localidad`, `numero`, `poligono`, `telefono`, `validado`, `id_usuario`, `nombre_completo`) VALUES
(10, 'Servicios de software', 'Calle Falsa', 'B12345678', 'EESA.B12345678', 28000, 'https://www.empresa.com', 'contacto@empresa.com', 'Madrid', 123, 'Polígono Industrial Norte', '912345678', b'1', 16, 'Empresa Ejemplo S.A.'),
(11, 'Mecanica', 'Segovia', '123166666', 'TALLERESLOSPALACIOS123166666', 41730, 'Segovia 41730', 'empresa@gmail.com', 'Las Cabezas De San Juan', 41730, 'Industrial', '681672137', b'0', 22, 'TalleresLosPalacios'),
(12, 'Informatica', 'Segovia', 'A500', 'EMPRESARIOA500', 41730, 'Segovia 41730', 'EMPRESRIO@gmail.com', 'Las Cabezas De San Juan', 91, 'Industrial', '681672137', b'0', 91, 'Empresario');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `oferta`
--

CREATE TABLE `oferta` (
  `id_oferta` bigint(20) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `estado` enum('ABIERTA','FINALIZADA') NOT NULL,
  `fecha_publicacion` date NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `id_empresa` bigint(20) DEFAULT NULL,
  `codigo_oferta` varchar(255) NOT NULL,
  `duracion` varchar(255) DEFAULT NULL,
  `perfil_profesional` varchar(255) NOT NULL,
  `tipo_oferta` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `oferta`
--

INSERT INTO `oferta` (`id_oferta`, `descripcion`, `estado`, `fecha_publicacion`, `titulo`, `id_empresa`, `codigo_oferta`, `duracion`, `perfil_profesional`, `tipo_oferta`) VALUES
(7, 'Soporte técnico, reparación de equipos y mantenimiento de redes.', 'ABIERTA', '2025-11-17', 'Técnico Informático Junior', 12, 'EMPRESARIOA500001', 'Indefinido', 'Informática / Soporte Técnico', 'CONTRATO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `postulacion`
--

CREATE TABLE `postulacion` (
  `id_postulacion` bigint(20) NOT NULL,
  `fecha_postulacion` datetime(6) NOT NULL,
  `id_ciudadano` bigint(20) DEFAULT NULL,
  `id_oferta` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` bigint(20) NOT NULL,
  `contrasena_usuario` varchar(255) NOT NULL,
  `nombre_usuario` varchar(255) NOT NULL,
  `perfil` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `contrasena_usuario`, `nombre_usuario`, `perfil`) VALUES
(1, '$2a$10$a2cma2cVS3pn.TXooV0CP.dggU9Vq3XbQy6SN4i3k5awVlvlaVa6a', 'usuario', 'ADMIN'),
(16, '$2a$10$EUx/19mrqLvK6U4DZVonXOzb26C8bscIgNna0jSD4NIt7JTRoercy', 'empresaUsuario', 'EMPRESA'),
(18, '$2a$10$3lPN8VZOGYFXpo6o4qCT/OxxZqoeNp.w./7DInYqQ1OXSN3V/rcM.', 'JaimeRT', 'CIUDADANO'),
(19, '$2a$10$P/JDSORH9nAt3Agw4mzZl.xpCLPCKSrQkKo9rxK2fD7NT3XgR5JQy', 'eMPRESA1', 'EMPRESA'),
(22, '$2a$10$sQCmb0O9B8wASe9kONilx.kYblblVlPcpZcntqwbbpFTJvtAHGiuG', 'TalleresLP', 'EMPRESA'),
(25, '$2a$10$BIE1byap5cVimMQkkYrMmuiN3iJIc7Qq6B7jhk95x9F0cylnUTegi', 'Jaimere27', 'CIUDADANO'),
(91, '$2a$10$W4IvKC5JE2GNxgKUcydXQO7rZ5vjpSQuFBJD98RsKj2ST1imoTbgu', 'Empresario', 'EMPRESA');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `administrador`
--
ALTER TABLE `administrador`
  ADD PRIMARY KEY (`id_administrador`),
  ADD UNIQUE KEY `UKferp3xx2iyuy3qltd4ey5pf7l` (`id_usuario`);

--
-- Indices de la tabla `ciudadano`
--
ALTER TABLE `ciudadano`
  ADD PRIMARY KEY (`id_ciudadano`),
  ADD UNIQUE KEY `UK22579biqs2sfmoycusvg8dugj` (`id_usuario`);

--
-- Indices de la tabla `empresa`
--
ALTER TABLE `empresa`
  ADD PRIMARY KEY (`id_empresa`),
  ADD UNIQUE KEY `UKpmcu2qpiwd6rjgb33vt0p7imc` (`id_usuario`);

--
-- Indices de la tabla `oferta`
--
ALTER TABLE `oferta`
  ADD PRIMARY KEY (`id_oferta`),
  ADD UNIQUE KEY `UKtf02inyo401oeu6oc53pok7m` (`codigo_oferta`),
  ADD KEY `FKa23jg27doqxfvfl6lumoc03r2` (`id_empresa`);

--
-- Indices de la tabla `postulacion`
--
ALTER TABLE `postulacion`
  ADD PRIMARY KEY (`id_postulacion`),
  ADD KEY `FKinca3st4x2yt3svyarjclw7o8` (`id_ciudadano`),
  ADD KEY `FKk4whumipm7c1pdci0uvhxcofg` (`id_oferta`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `UKpuhr3k3l7bj71hb7hk7ktpxn0` (`nombre_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `administrador`
--
ALTER TABLE `administrador`
  MODIFY `id_administrador` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `ciudadano`
--
ALTER TABLE `ciudadano`
  MODIFY `id_ciudadano` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `empresa`
--
ALTER TABLE `empresa`
  MODIFY `id_empresa` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `oferta`
--
ALTER TABLE `oferta`
  MODIFY `id_oferta` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `postulacion`
--
ALTER TABLE `postulacion`
  MODIFY `id_postulacion` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=92;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `administrador`
--
ALTER TABLE `administrador`
  ADD CONSTRAINT `FKpt2bj0l5q4npigarogy7p1834` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `ciudadano`
--
ALTER TABLE `ciudadano`
  ADD CONSTRAINT `FK49y4t75e3kmoueoxhnuktdum9` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `empresa`
--
ALTER TABLE `empresa`
  ADD CONSTRAINT `FKry3txwk27nut54r5wwd80mob2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `oferta`
--
ALTER TABLE `oferta`
  ADD CONSTRAINT `FKa23jg27doqxfvfl6lumoc03r2` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id_empresa`);

--
-- Filtros para la tabla `postulacion`
--
ALTER TABLE `postulacion`
  ADD CONSTRAINT `FKinca3st4x2yt3svyarjclw7o8` FOREIGN KEY (`id_ciudadano`) REFERENCES `ciudadano` (`id_ciudadano`),
  ADD CONSTRAINT `FKk4whumipm7c1pdci0uvhxcofg` FOREIGN KEY (`id_oferta`) REFERENCES `oferta` (`id_oferta`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
