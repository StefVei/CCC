-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jan 17, 2022 at 08:46 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ccc`
--

-- --------------------------------------------------------

--
-- Table structure for table `bought_products`
--

CREATE TABLE `bought_products` (
  `TRANSACTION_ID` int(10) NOT NULL,
  `PRODUCT_ID` int(10) NOT NULL,
  `MERCHANT_USERID` int(10) NOT NULL,
  `TOTAL` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `citizens`
--

CREATE TABLE `citizens` (
  `USERID` int(10) NOT NULL,
  `AMKA` varchar(11) NOT NULL,
  `VAT` varchar(9) NOT NULL,
  `FIRST_NAME` varchar(20) NOT NULL,
  `LAST_NAME` varchar(20) NOT NULL,
  `BIRTH_DATE` date NOT NULL,
  `GENDER` char(1) NOT NULL DEFAULT 'O',
  `USERNAME` varchar(20) NOT NULL,
  `PASSWORD` varchar(20) NOT NULL,
  `EMAIL` varchar(50) NOT NULL,
  `ADDRESS` varchar(50) NOT NULL,
  `PHONE` varchar(10) NOT NULL,
  `AMOUNT_DUE` double NOT NULL DEFAULT 0,
  `ACCOUNT_NUMBER` varchar(27) NOT NULL,
  `CREDIT_LIMIT` double NOT NULL DEFAULT 1000,
  `CREDIT_BALANCE` double NOT NULL DEFAULT 1000,
  `ACCOUNT_DUE_DATE` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `citizens`
--

INSERT INTO `citizens` (`USERID`, `AMKA`, `VAT`, `FIRST_NAME`, `LAST_NAME`, `BIRTH_DATE`, `GENDER`, `USERNAME`, `PASSWORD`, `EMAIL`, `ADDRESS`, `PHONE`, `AMOUNT_DUE`, `ACCOUNT_NUMBER`, `CREDIT_LIMIT`, `CREDIT_BALANCE`, `ACCOUNT_DUE_DATE`) VALUES
(1, '11111111111', '111111111', 'George', 'Katsa', '1999-03-31', 'M', 'geoka', 'geoka3440', 'geoka@example.com', 'georgiou papandreou, Archagelos rhodes 851 02', '2273011111', 0, 'GR0101010101010101010101010', 1000, 1000, '2025-09-09'),
(2, '22222222222', '222222222', 'Selina', 'Moon', '1999-03-31', 'F', 'selmo', 'selmo3440', 'selmo@example.com', 'georgiou papandreou, Archagelos rhodes 851 02', '2273022222', 0, 'GR0202020202020202020202020', 1000, 1000, '2025-09-09'),
(3, '33333333333', '333333333', 'Victor', 'Xalm', '1999-03-31', 'M', 'vixa', 'vixa3440', 'vixa@example.com', 'georgiou papandreou, Archagelos rhodes 851 02', '2273033333', 0, 'GR0303030303030303030303030', 1000, 1000, '2025-09-09'),
(4, '44444444444', '444444444', 'Beth', 'Harmon', '1999-03-31', 'F', 'beha', 'beha3440', 'beha@example.com', 'georgiou papandreou, Archagelos rhodes 851 02', '2273044444', 0, 'GR0404040404040404040404040', 1000, 1000, '2025-09-09'),
(5, '55555555555', '555555555', 'Benny', 'Watts', '1999-03-31', 'M', 'bewa', 'bewa3440', 'bewa@example.com', 'georgiou papandreou, Archagelos rhodes 851 02', '2273055555', 0, 'GR0505050505050505050505050', 1000, 1000, '2025-09-09');

-- --------------------------------------------------------

--
-- Table structure for table `cm_trades`
--

CREATE TABLE `cm_trades` (
  `CITIZEN_USERID` int(10) NOT NULL,
  `MERCHANT_USERID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `cm_traffics`
--

CREATE TABLE `cm_traffics` (
  `EMPLOYEE_ID` int(10) NOT NULL,
  `COMPANY_USERID` int(10) NOT NULL,
  `MERCHANT_USERID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `companies`
--

CREATE TABLE `companies` (
  `USERID` int(10) NOT NULL,
  `NAME` varchar(20) NOT NULL,
  `ESTABLISHMENT_DATE` date NOT NULL,
  `USERNAME` varchar(20) NOT NULL,
  `PASSWORD` varchar(20) NOT NULL,
  `EMAIL` varchar(50) NOT NULL,
  `ADDRESS` varchar(50) NOT NULL,
  `PHONE` varchar(10) NOT NULL,
  `AMOUNT_DUE` double NOT NULL DEFAULT 0,
  `CREDIT_LIMIT` double NOT NULL DEFAULT 1000,
  `CREDIT_BALANCE` double NOT NULL DEFAULT 1000,
  `ACCOUNT_DUE_DATE` date NOT NULL,
  `ACCOUNT_NUMBER` varchar(27) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `companies`
--

INSERT INTO `companies` (`USERID`, `NAME`, `ESTABLISHMENT_DATE`, `USERNAME`, `PASSWORD`, `EMAIL`, `ADDRESS`, `PHONE`, `AMOUNT_DUE`, `CREDIT_LIMIT`, `CREDIT_BALANCE`, `ACCOUNT_DUE_DATE`, `ACCOUNT_NUMBER`) VALUES
(1, 'Epignosis', '2003-03-03', 'epi', 'epi3440', 'epi@example.com', '160 Piccadilly, St. James\'s, London W1J 9EB, Unite', '2810111111', 0, 2000, 2000, '2025-09-09', 'BR0101010101010101010101010'),
(2, 'Stonewave', '1999-03-20', 'stone', 'stone3440', 'stone@example.com', '160 Piccadilly, St. James\'s, London W1J 9EB, Unite', '2810222222', 0, 1500, 1500, '2025-09-09', 'BR0202020202020202020202020'),
(3, 'OTE', '2008-03-20', 'ote', 'ote3440', 'ote@example.com', '160 Piccadilly, St. James\'s, London W1J 9EB, Unite', '2810333333', 0, 1500, 1500, '2025-09-09', 'BR0303030303030303030303030'),
(4, 'CSD', '2004-03-20', 'csd', 'csd3440', 'csd@example.com', '160 Piccadilly, St. James\'s, London W1J 9EB, Unite', '2810444444', 0, 1500, 1500, '2025-09-09', 'BR0404040404040404040404040'),
(5, 'Alphabank', '1990-03-20', 'alpha', 'alpha3440', 'alpha@example.com', '160 Piccadilly, St. James\'s, London W1J 9EB, Unite', '2810555555', 0, 1500, 1500, '2025-09-09', 'BR0505050505050505050505050');

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `EMPLOYEE_ID` int(10) NOT NULL,
  `FIRST_NAME` varchar(20) NOT NULL,
  `LAST_NAME` varchar(20) NOT NULL,
  `BIRTH_DATE` date NOT NULL,
  `GENDER` char(1) NOT NULL DEFAULT 'O',
  `EMAIL` varchar(50) NOT NULL,
  `ADDRESS` varchar(50) NOT NULL,
  `PHONE` varchar(10) NOT NULL,
  `USERNAME` varchar(100) NOT NULL,
  `PASSWORD` varchar(100) NOT NULL,
  `COMPANY_USERID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`EMPLOYEE_ID`, `FIRST_NAME`, `LAST_NAME`, `BIRTH_DATE`, `GENDER`, `EMAIL`, `ADDRESS`, `PHONE`, `USERNAME`, `PASSWORD`, `COMPANY_USERID`) VALUES
(1, 'Mahesh', 'Pegasus', '2011-11-30', 'M', 'mape@example.com', '(804) 462-5266 98 Lakemont Rd Lancaster, Virginia(', '2101111111', 'NIKOS123', 'XYZ!!', 1),
(3, 'Anna', 'Derilla', '2001-11-30', 'F', 'ande@example.com', '(804) 462-5266 98 Lakemont Rd Lancaster, Virginia(', '2102222222', 'TEO123', 'XYZ222', 1),
(4, 'Helena', 'Burt', '1996-03-31', 'F', 'hebu@example.com', '(804) 462-5266 98 Lakemont Rd Lancaster, Virginia(', '2103333333', 'TT124', 'XYZ333', 2),
(5, 'Pedro', 'Lema', '2004-11-01', 'O', 'pele@example.com', '(804) 462-5266 98 Lakemont Rd Lancaster, Virginia(', '2104444444', 'TT232', 'XYZ123', 3),
(6, 'Vigga', 'Stegga', '2001-09-13', 'O', 'viste@example.com', '(804) 462-5266 98 Lakemont Rd Lancaster, Virginia(', '2105555555', 'TT324', 'XYZ111', 4),
(7, 'Wilma', 'Lorenza', '2003-09-14', 'F', 'wilo@example.com', '(804) 462-5266 98 Lakemont Rd Lancaster, Virginia(', '2106666666', 'TTTT', 'XYZ212', 5);

-- --------------------------------------------------------

--
-- Table structure for table `merchants`
--

CREATE TABLE `merchants` (
  `USERID` int(10) NOT NULL,
  `FIRST_NAME` varchar(20) NOT NULL,
  `LAST_NAME` varchar(20) NOT NULL,
  `BIRTH_DATE` date NOT NULL,
  `GENDER` char(1) NOT NULL,
  `SUPPLY` double NOT NULL DEFAULT 0.15,
  `GAIN` double NOT NULL DEFAULT 0,
  `PURCHASES_TOTAL` int(10) NOT NULL DEFAULT 0,
  `USERNAME` varchar(20) NOT NULL,
  `PASSWORD` varchar(20) NOT NULL,
  `EMAIL` varchar(50) NOT NULL,
  `ADDRESS` varchar(50) NOT NULL,
  `PHONE` varchar(10) NOT NULL,
  `AMOUNT_DUE` double NOT NULL DEFAULT 0,
  `ACCOUNT_NUMBER` varchar(27) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `merchants`
--

INSERT INTO `merchants` (`USERID`, `FIRST_NAME`, `LAST_NAME`, `BIRTH_DATE`, `GENDER`, `SUPPLY`, `GAIN`, `PURCHASES_TOTAL`, `USERNAME`, `PASSWORD`, `EMAIL`, `ADDRESS`, `PHONE`, `AMOUNT_DUE`, `ACCOUNT_NUMBER`) VALUES
(1, 'Lydia', 'Xamm', '2006-03-31', 'O', 0.17, 0, 0, 'lyxa', 'lyxa3440', 'lyxa@example.com', '30 Berkeley St, London W1J 8EH, United Kingdom', '6972231111', 0, 'GB0101010101010101010101010'),
(2, 'Stephen', 'Taylor', '2006-10-09', 'M', 0.16, 0, 0, 'steta', 'steta3440', 'steta@example.com', '30 Berkeley St, London W1J 8EH, United Kingdom', '6972232222', 0, 'GB0202020202020202020202020'),
(3, 'Zinos', 'Koukou', '1999-03-31', 'M', 0.16, 0, 0, 'zikou', 'Zikou3440', 'zikou@example.com', '30 Berkeley St, London W1J 8EH, United Kingdom', '6972233333', 0, 'GB0303030303030303030303030'),
(4, 'Diva', 'Stodio', '1998-03-07', 'F', 0.16, 0, 0, 'disto', 'disto3440', 'disto@example.com', '30 Berkeley St, London W1J 8EH, United Kingdom', '6972234444', 0, 'GB0404040404040404040404040'),
(5, 'Spiros', 'Tridos', '2004-03-07', 'M', 0.17, 0, 0, 'spitri', 'spitri3440', 'spitri@example.com', '30 Berkeley St, London W1J 8EH, United Kingdom', '6972235555', 0, 'GB0505050505050505050505050');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `PRODUCT_ID` int(10) NOT NULL,
  `NAME` varchar(20) NOT NULL,
  `PRICE` double NOT NULL,
  `QUANTITY` int(10) NOT NULL DEFAULT 0,
  `MERCHANT_USERID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`PRODUCT_ID`, `NAME`, `PRICE`, `QUANTITY`, `MERCHANT_USERID`) VALUES
(1, 'PC Antivirus', 30.5, 20, 1),
(3, 'PC Games', 360.5, 210, 1),
(4, 'PC Gadgets', 240.5, 50, 2),
(5, 'PC Monitor', 120.5, 40, 3),
(6, 'Laptops', 650.99, 140, 4),
(7, 'Movies', 10.5, 297, 5),
(8, 'PC Desktops', 1249.99, 34, 5);

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `TRANSACTION_ID` int(10) NOT NULL,
  `PENDING` char(1) NOT NULL DEFAULT 'T',
  `TRANSACTION_TYPE` char(1) NOT NULL DEFAULT 'A',
  `AMOUNT` double NOT NULL DEFAULT 0,
  `DATE` date NOT NULL,
  `CITIZEN_USERID` int(10) DEFAULT NULL,
  `MERCHANT_TRADE` int(10) DEFAULT NULL,
  `EMPLOYEE_ID` int(10) DEFAULT NULL,
  `COMPANY_USERID` int(10) DEFAULT NULL,
  `MERCHANT_TRAFFIC` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bought_products`
--
ALTER TABLE `bought_products`
  ADD PRIMARY KEY (`TRANSACTION_ID`,`PRODUCT_ID`,`MERCHANT_USERID`),
  ADD KEY `TRANSACTION_ID` (`TRANSACTION_ID`),
  ADD KEY `PRODUCT_ID` (`PRODUCT_ID`),
  ADD KEY `MERCHANT_USERID` (`MERCHANT_USERID`),
  ADD KEY `PRODUCT_ID_2` (`PRODUCT_ID`,`MERCHANT_USERID`);

--
-- Indexes for table `citizens`
--
ALTER TABLE `citizens`
  ADD PRIMARY KEY (`USERID`),
  ADD UNIQUE KEY `AMKA` (`AMKA`),
  ADD UNIQUE KEY `VAT` (`VAT`),
  ADD UNIQUE KEY `USERNAME` (`USERNAME`) USING BTREE,
  ADD UNIQUE KEY `ACCOUNT_NUMBER` (`ACCOUNT_NUMBER`);

--
-- Indexes for table `cm_trades`
--
ALTER TABLE `cm_trades`
  ADD PRIMARY KEY (`CITIZEN_USERID`,`MERCHANT_USERID`),
  ADD KEY `CITIZEN_USERID` (`CITIZEN_USERID`),
  ADD KEY `MERCHANT_USERID` (`MERCHANT_USERID`);

--
-- Indexes for table `cm_traffics`
--
ALTER TABLE `cm_traffics`
  ADD PRIMARY KEY (`EMPLOYEE_ID`,`COMPANY_USERID`,`MERCHANT_USERID`),
  ADD KEY `EMPLOYEE_ID` (`EMPLOYEE_ID`),
  ADD KEY `COMPANY_USERID` (`COMPANY_USERID`),
  ADD KEY `MERCHANT_USERID` (`MERCHANT_USERID`);

--
-- Indexes for table `companies`
--
ALTER TABLE `companies`
  ADD PRIMARY KEY (`USERID`),
  ADD UNIQUE KEY `NAME` (`NAME`),
  ADD UNIQUE KEY `USERNAME` (`USERNAME`),
  ADD UNIQUE KEY `ACCOUNT_NUMBER` (`ACCOUNT_NUMBER`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`EMPLOYEE_ID`,`COMPANY_USERID`),
  ADD UNIQUE KEY `USERNAME` (`USERNAME`),
  ADD KEY `COMPANY_USERID` (`COMPANY_USERID`) USING BTREE;

--
-- Indexes for table `merchants`
--
ALTER TABLE `merchants`
  ADD PRIMARY KEY (`USERID`),
  ADD UNIQUE KEY `ACCOUNT_NUMBER` (`ACCOUNT_NUMBER`),
  ADD UNIQUE KEY `USERNAME` (`USERNAME`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`PRODUCT_ID`,`MERCHANT_USERID`),
  ADD KEY `MERCHANT_USERID` (`MERCHANT_USERID`) USING BTREE;

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`TRANSACTION_ID`),
  ADD KEY `CITIZEN_USERID` (`CITIZEN_USERID`),
  ADD KEY `MERCHANT_TRADE` (`MERCHANT_TRADE`),
  ADD KEY `EMPLOYEE_ID` (`EMPLOYEE_ID`),
  ADD KEY `COMPANY_USERID` (`COMPANY_USERID`),
  ADD KEY `MERCHANT_TRAFFIC` (`MERCHANT_TRAFFIC`),
  ADD KEY `EMPLOYEE_ID_2` (`EMPLOYEE_ID`,`COMPANY_USERID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `citizens`
--
ALTER TABLE `citizens`
  MODIFY `USERID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `companies`
--
ALTER TABLE `companies`
  MODIFY `USERID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `EMPLOYEE_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `merchants`
--
ALTER TABLE `merchants`
  MODIFY `USERID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `PRODUCT_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
  MODIFY `TRANSACTION_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bought_products`
--
ALTER TABLE `bought_products`
  ADD CONSTRAINT `bought_products_ibfk_1` FOREIGN KEY (`TRANSACTION_ID`) REFERENCES `transactions` (`TRANSACTION_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `bought_products_ibfk_2` FOREIGN KEY (`PRODUCT_ID`,`MERCHANT_USERID`) REFERENCES `products` (`PRODUCT_ID`, `MERCHANT_USERID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `cm_trades`
--
ALTER TABLE `cm_trades`
  ADD CONSTRAINT `cm_trades_ibfk_1` FOREIGN KEY (`CITIZEN_USERID`) REFERENCES `citizens` (`USERID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cm_trades_ibfk_2` FOREIGN KEY (`MERCHANT_USERID`) REFERENCES `merchants` (`USERID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `cm_traffics`
--
ALTER TABLE `cm_traffics`
  ADD CONSTRAINT `cm_traffics_ibfk_1` FOREIGN KEY (`EMPLOYEE_ID`,`COMPANY_USERID`) REFERENCES `employees` (`EMPLOYEE_ID`, `COMPANY_USERID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cm_traffics_ibfk_2` FOREIGN KEY (`MERCHANT_USERID`) REFERENCES `merchants` (`USERID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `employees`
--
ALTER TABLE `employees`
  ADD CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`COMPANY_USERID`) REFERENCES `companies` (`USERID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`MERCHANT_USERID`) REFERENCES `merchants` (`USERID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`CITIZEN_USERID`) REFERENCES `citizens` (`USERID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `transactions_ibfk_2` FOREIGN KEY (`MERCHANT_TRADE`) REFERENCES `merchants` (`USERID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `transactions_ibfk_3` FOREIGN KEY (`EMPLOYEE_ID`,`COMPANY_USERID`) REFERENCES `employees` (`EMPLOYEE_ID`, `COMPANY_USERID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `transactions_ibfk_4` FOREIGN KEY (`MERCHANT_TRAFFIC`) REFERENCES `merchants` (`USERID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
