-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-04-2025 a las 04:44:57
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
-- Base de datos: `veterinarybd`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clinica`
--

CREATE TABLE `clinica` (
  `clinica_id` bigint(20) NOT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp(),
  `consultation` varchar(250) NOT NULL,
  `syntomatology` varchar(250) NOT NULL,
  `diagnostic` varchar(250) NOT NULL,
  `treatment` varchar(250) NOT NULL,
  `orden_id` bigint(20) NOT NULL,
  `vacum_history` varchar(250) NOT NULL,
  `allergy_medicines` varchar(250) NOT NULL,
  `details_treatement` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `clinica`
--

INSERT INTO `clinica` (`clinica_id`, `date`, `consultation`, `syntomatology`, `diagnostic`, `treatment`, `orden_id`, `vacum_history`, `allergy_medicines`, `details_treatement`) VALUES
(1, '2025-03-20 02:43:15', 'consulta', 'sintomatologia', 'diagnostico', 'tratamiento', 1, 'vacuna 1, vacuna 2', 'alergia 1, alergia 2', 'detalles del tratamiento'),
(2, '2025-03-20 03:32:16', 'consulta por vacunacion', 'vacunacion', 'vacunacion', 'vacunacion', 2, 'Vacuna 1, Vacunacion 2', 'Alergia', 'Vacunacion'),
(3, '2025-03-20 03:35:27', 'EDITADA', 'EDITADA', 'EDITADA', 'EDITADA', 3, 'No aplica', 'EDITADA', 'EDITADA'),
(5, '2025-04-01 02:41:38', 'Revision', 'N/A', 'Sano', 'N/a', 5, 'N/A', 'N/A', 'N/A'),
(6, '2025-04-01 02:43:55', 'Rutina', 'N/A', 'Sano', 'N/A', 6, 'N/A', 'N/A', 'N/A');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `invoiced`
--

CREATE TABLE `invoiced` (
  `invoiced_id` bigint(20) NOT NULL,
  `orden_id` bigint(20) NOT NULL,
  `product` varchar(250) NOT NULL,
  `amount` double NOT NULL,
  `medication_quantity` bigint(20) NOT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `invoiced`
--

INSERT INTO `invoiced` (`invoiced_id`, `orden_id`, `product`, `amount`, `medication_quantity`, `date`) VALUES
(3, 2, 'otro producto 1 otro producto 2', 200000, 34, '2025-03-21 01:07:06');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `orden`
--

CREATE TABLE `orden` (
  `orden_id` bigint(20) NOT NULL,
  `pet_id` bigint(20) NOT NULL,
  `document` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `medication_name` varchar(250) NOT NULL,
  `medication_dosis` double NOT NULL,
  `orden_status` varchar(250) NOT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `orden`
--

INSERT INTO `orden` (`orden_id`, `pet_id`, `document`, `user_id`, `medication_name`, `medication_dosis`, `orden_status`, `date`) VALUES
(1, 1, 1, 4, 'dosis', 1, 'Anulada', '2025-03-13 21:28:19'),
(2, 2, 2, 4, 'Medicamento', 1.4, 'Activa', '2025-03-20 03:28:03'),
(3, 3, 2, 4, 'NOVACUNA', 12, 'Activa', '2025-03-20 03:34:52'),
(4, 2, 123, 8, '123', 123, 'Activa', '2025-04-01 01:26:07'),
(5, 6, 100411, 6, 'N/A', 0, 'Activa', '2025-04-01 02:40:52'),
(6, 7, 100411, 4, 'N/A', 0, 'Activa', '2025-04-01 02:43:12');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `person`
--

CREATE TABLE `person` (
  `document` bigint(20) NOT NULL,
  `name` varchar(250) NOT NULL,
  `age` bigint(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `person`
--

INSERT INTO `person` (`document`, `name`, `age`) VALUES
(1, 'adminName', 0),
(2, 'Prueba', 1),
(3, 'vetprueba', 12),
(4, 'vendedorprueba', 1),
(5, 'vet', 5),
(6, 'seller', 6),
(123, '123', 123),
(100411, 'Emmanuel Cardenas', 23);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pet`
--

CREATE TABLE `pet` (
  `document` bigint(20) NOT NULL,
  `pet_id` bigint(20) NOT NULL,
  `pet_name` varchar(250) NOT NULL,
  `pet_age` bigint(3) NOT NULL,
  `species` varchar(250) NOT NULL,
  `pet_race` varchar(250) NOT NULL,
  `pet_weight` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pet`
--

INSERT INTO `pet` (`document`, `pet_id`, `pet_name`, `pet_age`, `species`, `pet_race`, `pet_weight`) VALUES
(1, 1, 'Prueba', 1, 'ES', 'RA', 1.2),
(2, 2, 'MascotaDePrueba2', 2, 'Perro', 'Chihuaha', 1.5),
(2, 3, 'MascotaDePrueba3', 3, 'Ave', 'pelicano', 15.23),
(1, 4, 'Prueba3', 3, 'Gato', 'siames', 15),
(123, 5, '123', 123, 'Perro', '1233', 123),
(100411, 6, 'Dino', 1, 'Perro', 'San Bernardo', 60),
(100411, 7, 'Doki', 1, 'Perro', 'San bernardo', 50);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL,
  `document` bigint(20) NOT NULL,
  `user_name` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL,
  `role` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`user_id`, `document`, `user_name`, `password`, `role`) VALUES
(1, 1, 'admin', 'admin', 'admin'),
(4, 3, 'vetuser', 'vetpass', 'veterinary'),
(5, 4, 'vendedoruser', 'vendedorpass', 'seller'),
(6, 5, 'vet', 'vet', 'veterinary'),
(7, 6, 'seller', 'seller', 'seller'),
(8, 123, '123', '123', 'veterinary');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clinica`
--
ALTER TABLE `clinica`
  ADD PRIMARY KEY (`clinica_id`);

--
-- Indices de la tabla `invoiced`
--
ALTER TABLE `invoiced`
  ADD PRIMARY KEY (`invoiced_id`);

--
-- Indices de la tabla `orden`
--
ALTER TABLE `orden`
  ADD PRIMARY KEY (`orden_id`);

--
-- Indices de la tabla `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`document`);

--
-- Indices de la tabla `pet`
--
ALTER TABLE `pet`
  ADD PRIMARY KEY (`pet_id`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clinica`
--
ALTER TABLE `clinica`
  MODIFY `clinica_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `invoiced`
--
ALTER TABLE `invoiced`
  MODIFY `invoiced_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `orden`
--
ALTER TABLE `orden`
  MODIFY `orden_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `pet`
--
ALTER TABLE `pet`
  MODIFY `pet_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `user_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
