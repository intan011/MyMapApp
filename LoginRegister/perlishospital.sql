-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 28, 2024 at 11:07 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `perlishospital`
--

-- --------------------------------------------------------

--
-- Table structure for table `gpslocation`
--

CREATE TABLE `gpslocation` (
  `id` int(11) NOT NULL,
  `user_name` varchar(100) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `user_agent` varchar(256) NOT NULL,
  `lat` double NOT NULL,
  `lng` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `gpslocation`
--

INSERT INTO `gpslocation` (`id`, `user_name`, `timestamp`, `user_agent`, `lat`, `lng`) VALUES
(1, 'Muhammad Ikhwan Bin Mohd Hafidz', '2024-01-28 06:46:37', 'Dalvik/2.1.0 (Linux; U; Android 14; sdk_gphone64_x86_64 Build/UE1A.230829.019)', 6.488193, 100.272558),
(2, 'Muhammad Ikhwan Bin Mohd Hafidz', '2024-01-28 06:49:38', 'Dalvik/2.1.0 (Linux; U; Android 14; sdk_gphone64_x86_64 Build/UE1A.230829.019)', 5.697108, 100.542508),
(3, 'Muhammad Ikhwan Bin Mohd Hafidz', '2024-01-28 09:51:38', 'Dalvik/2.1.0 (Linux; U; Android 14; sdk_gphone64_x86_64 Build/UE1A.230829.019)', 6.452138, 100.277798);

-- --------------------------------------------------------

--
-- Table structure for table `pshospital`
--

CREATE TABLE `pshospital` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `lat` decimal(10,4) NOT NULL,
  `lng` decimal(10,4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pshospital`
--

INSERT INTO `pshospital` (`id`, `name`, `description`, `lat`, `lng`) VALUES
(1, 'Hospital Tuanku Fauziah', 'Kangar', 6.4412, 100.1913),
(2, 'Hospital Pakar KPJ', 'Kangar', 6.4335, 100.1864),
(3, 'MEDIKLINIK RAKYAT DR NAIM AHMAD', 'Kangar', 6.4161, 100.1825),
(4, 'Unit Kesihatan Klinik UiTM', 'Arau', 6.4467, 100.2799),
(5, 'Klinik Kesihatan Kampung Gial', 'Kangar', 6.4656, 100.2737),
(6, 'Klinik Kesihatan Arau', 'Arau', 6.4326, 100.2707),
(7, 'U.n.i KLINIK Mata Ayer Perlis', 'Kangar', 6.4772, 100.2586),
(8, 'Klinik Kesihatan Jejawi', 'Kangar', 6.4456, 100.2381),
(9, 'Poliklinik DrAzhar Dan Rakan-rakan Cawangan Jejawi', 'Kangar', 6.4452, 100.2359),
(10, 'Klinik Megah', 'Kangar', 6.4448, 100.2353),
(11, 'Poliklinik Perubatan Dr. Suraya', 'Kangar', 6.4528, 100.2060);

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `staffID` int(11) NOT NULL,
  `staff_name` varchar(100) NOT NULL,
  `staff_username` varchar(50) NOT NULL,
  `staff_password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`staffID`, `staff_name`, `staff_username`, `staff_password`) VALUES
(6, 'Muhammad Ikhwan', 'yuki', 'yuki123');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userID` int(11) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `username` varchar(50) NOT NULL,
  `user_password` text NOT NULL,
  `apiKey` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userID`, `full_name`, `username`, `user_password`, `apiKey`) VALUES
(1, 'Kamil Abdullah', 'kamil02', '$2y$10$Qfzf0NI0/SXRQI/7gNxT7OsH4TMd9GBFWSDD6ISb.80Oa7qXJW8Ji', ''),
(4, 'Muhammad Ikhwan Bin Mohd Hafidz', 'yuki', '$2y$10$1sVY36Wk9wAVitoiyunw3OUsgUfACXuEk81xrzTxMeJEuZnquLg2y', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `gpslocation`
--
ALTER TABLE `gpslocation`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pshospital`
--
ALTER TABLE `pshospital`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`staffID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userID`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `gpslocation`
--
ALTER TABLE `gpslocation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `pshospital`
--
ALTER TABLE `pshospital`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `staff`
--
ALTER TABLE `staff`
  MODIFY `staffID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
