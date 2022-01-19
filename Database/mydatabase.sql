-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Εξυπηρετητής: 127.0.0.1
-- Χρόνος δημιουργίας: 18 Ιαν 2022 στις 20:24:45
-- Έκδοση διακομιστή: 10.4.22-MariaDB
-- Έκδοση PHP: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Βάση δεδομένων: `mydatabase`
--

DELIMITER $$
--
-- Διαδικασίες
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_product` (IN `name` VARCHAR(20), IN `price` DOUBLE, IN `proquan` INT(10), IN `merid` INT(10))  BEGIN
DECLARE msup double DEFAULT 0;
INSERT INTO products VALUES(NULL, name, price, proquan, merid);
SELECT SUPPLY INTO msup FROM merchants WHERE USERID = merid;
UPDATE merchants SET SUPPLY = (msup + 0.01) WHERE USERID = merid;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `citizenPurchase` (IN `citid` INT(10), IN `proid` INT(10), IN `merid` INT(10), IN `totalp` INT(10))  BEGIN
DECLARE cbalance double DEFAULT 0;
DECLARE proprice double DEFAULT 0;
DECLARE proquan int(10) DEFAULT 0;
DECLARE mergain double DEFAULT 0;
DECLARE mersupp double DEFAULT 0;
DECLARE mertotal int(10) DEFAULT 0;


SELECT PRICE, QUANTITY INTO proprice, proquan FROM products WHERE (PRODUCT_ID = proid AND MERCHANT_USERID = merid);

SELECT CREDIT_BALANCE INTO cbalance FROM citizens WHERE USERID = citid;
	
IF (proprice*totalp) < cbalance THEN

UPDATE products SET QUANTITY = (proquan-totalp) WHERE (PRODUCT_ID = proid AND MERCHANT_USERID = merid);

UPDATE citizens SET AMOUNT_DUE = (proprice*totalp) WHERE USERID = citid;
UPDATE citizens SET CREDIT_BALANCE = (cbalance - (proprice*totalp)) WHERE USERID = citid;

UPDATE merchants SET GAIN = (proprice*totalp) WHERE USERID = merid;
SELECT GAIN, SUPPLY, PURCHASES_TOTAL INTO mergain, mersupp, mertotal FROM merchants WHERE USERID = merid;
UPDATE merchants SET AMOUNT_DUE = (mergain*mersupp) WHERE USERID = merid;
UPDATE merchants SET PURCHASES_TOTAL = (mertotal+1) WHERE USERID = merid;

INSERT INTO transactions VALUES(NULL, DEFAULT, DEFAULT, (proprice*totalp), curdate(), citid, merid, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO bought_products VALUES (LAST_INSERT_ID(), proid, merid, totalp);

INSERT INTO cm_trades VALUES(citid, merid);
END IF;	
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `citizen_insert` (IN `amka` VARCHAR(11), IN `vat` VARCHAR(9), IN `fname` VARCHAR(20), IN `lname` VARCHAR(20), IN `birth` DATE, IN `gen` CHAR(1), IN `usern` VARCHAR(20), IN `pwd` VARCHAR(20), IN `em` VARCHAR(50), IN `addr` VARCHAR(50), IN `pnumber` VARCHAR(10), IN `acnumber` VARCHAR(27), IN `acdate` DATE)  BEGIN
INSERT INTO citizens VALUES(NULL, amka, vat, fname, lname, birth, gen, usern, pwd, em, addr, pnumber, DEFAULT, acnumber, DEFAULT, DEFAULT, acdate);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `company_insert` (IN `name` VARCHAR(20), IN `estdate` DATE, IN `usern` VARCHAR(20), IN `pwd` VARCHAR(20), IN `em` VARCHAR(50), IN `addr` VARCHAR(50), IN `pnumber` VARCHAR(10), IN `acdate` DATE, IN `acnumber` VARCHAR(27))  BEGIN
INSERT INTO companies VALUES(NULL, name, estdate, usern, pwd, em, addr, pnumber, DEFAULT, DEFAULT, DEFAULT, acDate, acNumber);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_citizen` (IN `ciid` INT(10))  BEGIN
DELETE FROM citizens WHERE USERID = ciid AND AMOUNT_DUE = 0;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_company` (IN `coid` INT(10))  BEGIN
DELETE FROM companies WHERE USERID = coid AND AMOUNT_DUE = 0;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_merchant` (IN `merid` INT(10))  BEGIN
DELETE FROM merchants WHERE USERID = merid AND AMOUNT_DUE = 0;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `employee_insert` (IN `fname` VARCHAR(20), IN `lname` VARCHAR(20), IN `birth` DATE, IN `gen` CHAR(1), IN `em` VARCHAR(50), IN `addr` VARCHAR(50), IN `pnumber` VARCHAR(10), IN `comid` INT(10))  BEGIN
DECLARE Climit double DEFAULT 0;
INSERT INTO employees VALUES(NULL, fname, lname, birth, gen, em, addr, pnumber, comid);
SELECT CREDIT_LIMIT INTO Climit FROM companies WHERE USERID = comid;
UPDATE companies SET CREDIT_LIMIT = (Climit + 500) WHERE USERID = comid;
UPDATE companies SET CREDIT_BALANCE = (Climit + 500) WHERE USERID = comid;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `merchant_insert` (IN `fname` VARCHAR(20), IN `lname` VARCHAR(20), IN `birth` DATE, IN `gen` CHAR(1), IN `usern` VARCHAR(20), IN `pwd` VARCHAR(20), IN `em` VARCHAR(50), IN `addr` VARCHAR(50), IN `pnumber` VARCHAR(10), IN `acnumber` VARCHAR(27))  BEGIN
INSERT INTO merchants VALUES(NULL, fname, lname, birth, gen, DEFAULT, DEFAULT, DEFAULT, usern, pwd, em, addr, pnumber, DEFAULT, acNumber);
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `bought_products`
--

CREATE TABLE `bought_products` (
  `TRANSACTION_ID` int(10) NOT NULL,
  `PRODUCT_ID` int(10) NOT NULL,
  `MERCHANT_USERID` int(10) NOT NULL,
  `TOTAL` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `citizens`
--

CREATE TABLE `citizens` (
  `USERID` int(10) NOT NULL,
  `AMKA` varchar(11) NOT NULL,
  `VAT` varchar(9) NOT NULL,
  `FIRST_NAME` varchar(20) NOT NULL,
  `LAST_NAME` varchar(20) NOT NULL,
  `BIRTH_DATE` date NOT NULL,
  `GENDER` char(1) NOT NULL DEFAULT 'O',
  `EMAIL` varchar(50) NOT NULL,
  `ADDRESS` varchar(50) NOT NULL,
  `PHONE` varchar(10) NOT NULL,
  `AMOUNT_DUE` double NOT NULL DEFAULT 0,
  `CREDIT_LIMIT` double NOT NULL DEFAULT 1000,
  `CREDIT_BALANCE` double NOT NULL DEFAULT 1000,
  `ACCOUNT_DUE_DATE` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `citizens`
--

INSERT INTO `citizens` (`USERID`, `AMKA`, `VAT`, `FIRST_NAME`, `LAST_NAME`, `BIRTH_DATE`, `GENDER`, `EMAIL`, `ADDRESS`, `PHONE`, `AMOUNT_DUE`, `CREDIT_LIMIT`, `CREDIT_BALANCE`, `ACCOUNT_DUE_DATE`) VALUES
(1, '11111111111', '111111111', 'George', 'Katsa', '1999-03-31', 'M', 'geoka@example.com', 'georgiou papandreou, Archagelos rhodes 851 02', '2273011111', 0, 1000, 1000, '2025-09-09'),
(4, '22222222222', '222222222', 'Selina', 'Moon', '1999-03-31', 'F', 'selmo@example.com', 'georgiou papandreou, Archagelos rhodes 851 02', '2273022222', 0, 1000, 1000, '2025-09-09'),
(9, '33333333333', '333333333', 'Victor', 'Xalm', '1999-03-31', 'M', 'vixa@example.com', 'georgiou papandreou, Archagelos rhodes 851 02', '2273033333', 0, 1000, 1000, '2025-09-09'),
(13, '44444444444', '444444444', 'Beth', 'Harmon', '1999-03-31', 'F', 'beha@example.com', 'georgiou papandreou, Archagelos rhodes 851 02', '2273044444', 0, 1000, 1000, '2025-09-09'),
(14, '55555555555', '555555555', 'Benny', 'Watts', '1999-03-31', 'M', 'bewa@example.com', 'georgiou papandreou, Archagelos rhodes 851 02', '2273055555', 0, 1000, 1000, '2025-09-09');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `cm_trades`
--

CREATE TABLE `cm_trades` (
  `CITIZEN_USERID` int(10) NOT NULL,
  `MERCHANT_USERID` int(10) NOT NULL,
  `TRANSACTION_ID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `cm_traffics`
--

CREATE TABLE `cm_traffics` (
  `EMPLOYEE_ID` int(10) NOT NULL,
  `COMPANY_USERID` int(10) NOT NULL,
  `MERCHANT_USERID` int(10) NOT NULL,
  `TRANSACTION_ID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `companies`
--

CREATE TABLE `companies` (
  `USERID` int(10) NOT NULL,
  `NAME` varchar(20) NOT NULL,
  `ESTABLISHMENT_DATE` date NOT NULL,
  `EMAIL` varchar(50) NOT NULL,
  `ADDRESS` varchar(50) NOT NULL,
  `PHONE` varchar(10) NOT NULL,
  `AMOUNT_DUE` double NOT NULL DEFAULT 0,
  `CREDIT_LIMIT` double NOT NULL DEFAULT 1000,
  `CREDIT_BALANCE` double NOT NULL DEFAULT 1000,
  `ACCOUNT_DUE_DATE` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `companies`
--

INSERT INTO `companies` (`USERID`, `NAME`, `ESTABLISHMENT_DATE`, `EMAIL`, `ADDRESS`, `PHONE`, `AMOUNT_DUE`, `CREDIT_LIMIT`, `CREDIT_BALANCE`, `ACCOUNT_DUE_DATE`) VALUES
(2, 'Epignosis', '2003-03-03', 'epi@example.com', '160 Piccadilly, St. James\\\'s, London W1J 9EB, Unit', '2810111111', 0, 2000, 2000, '2025-09-09'),
(3, 'Stonewave', '1999-03-20', 'stone@example.com', '160 Piccadilly, St. James\\\'s, London W1J 9EB, Unit', '2810222222', 0, 1500, 1500, '2025-09-09'),
(6, 'OTE', '2008-03-20', 'ote@example.com', '160 Piccadilly, St. James\\\'s, London W1J 9EB, Unit', '2810333333', 0, 1500, 1500, '2025-09-09'),
(12, 'CSD', '2004-03-20', 'csd@example.com', '160 Piccadilly, St. James\\\'s, London W1J 9EB, Unit', '2810444444', 0, 1500, 1500, '2025-09-09'),
(15, 'Alphabank', '1990-03-20', 'alpha@example.com', '160 Piccadilly, St. James\\\'s, London W1J 9EB, Unit', '2810555555', 0, 1500, 1500, '2025-09-09');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `employees`
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
  `COMPANY_USERID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `employees`
--

INSERT INTO `employees` (`EMPLOYEE_ID`, `FIRST_NAME`, `LAST_NAME`, `BIRTH_DATE`, `GENDER`, `EMAIL`, `ADDRESS`, `PHONE`, `COMPANY_USERID`) VALUES
(1, 'Mahesh', 'Pegasus', '2011-11-30', 'M', 'mape@example.com', '(804) 462-5266 98 Lakemont Rd Lancaster, Virginia(', '2101111111', 2),
(3, 'Anna', 'Derilla', '2001-11-30', 'F', 'ande@example.com', '(804) 462-5266 98 Lakemont Rd Lancaster, Virginia(', '2102222222', 2),
(4, 'Helena', 'Burt', '1996-03-31', 'F', 'hebu@example.com', '(804) 462-5266 98 Lakemont Rd Lancaster, Virginia(', '2103333333', 3),
(5, 'Pedro', 'Lema', '2004-11-01', 'O', 'pele@example.com', '(804) 462-5266 98 Lakemont Rd Lancaster, Virginia(', '2104444444', 6),
(6, 'Vigga', 'Stegga', '2001-09-13', 'O', 'viste@example.com', '(804) 462-5266 98 Lakemont Rd Lancaster, Virginia(', '2105555555', 12),
(7, 'Wilma', 'Lorenza', '2003-09-14', 'F', 'wilo@example.com', '(804) 462-5266 98 Lakemont Rd Lancaster, Virginia(', '2106666666', 15);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `merchants`
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
  `EMAIL` varchar(50) NOT NULL,
  `ADDRESS` varchar(50) NOT NULL,
  `PHONE` varchar(10) NOT NULL,
  `AMOUNT_DUE` double NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `merchants`
--

INSERT INTO `merchants` (`USERID`, `FIRST_NAME`, `LAST_NAME`, `BIRTH_DATE`, `GENDER`, `SUPPLY`, `GAIN`, `PURCHASES_TOTAL`, `EMAIL`, `ADDRESS`, `PHONE`, `AMOUNT_DUE`) VALUES
(5, 'Lydia', 'Xamm', '2006-03-31', 'O', 0.17, 0, 0, 'lyxa@example.com', '30 Berkeley St, London W1J 8EH, United Kingdom', '6972231111', 0),
(7, 'Stephen', 'Taylor', '2006-10-09', 'M', 0.16, 0, 0, 'steta@example.com', '30 Berkeley St, London W1J 8EH, United Kingdom', '6972232222', 0),
(8, 'Zinos', 'Koukou', '1999-03-31', 'M', 0.16, 0, 0, 'zikou@example.com', '30 Berkeley St, London W1J 8EH, United Kingdom', '6972233333', 0),
(10, 'Diva', 'Stodio', '1998-03-07', 'F', 0.16, 0, 0, 'disto@example.com', '30 Berkeley St, London W1J 8EH, United Kingdom', '6972234444', 0),
(11, 'Spiros', 'Tridos', '2004-03-07', 'M', 0.17, 0, 0, 'spitri@example.com', '30 Berkeley St, London W1J 8EH, United Kingdom', '6972235555', 0);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `products`
--

CREATE TABLE `products` (
  `PRODUCT_ID` int(10) NOT NULL,
  `NAME` varchar(20) NOT NULL,
  `PRICE` double NOT NULL,
  `QUANTITY` int(10) NOT NULL DEFAULT 0,
  `MERCHANT_USERID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `products`
--

INSERT INTO `products` (`PRODUCT_ID`, `NAME`, `PRICE`, `QUANTITY`, `MERCHANT_USERID`) VALUES
(1, 'PC Antivirus', 30.5, 20, 5),
(3, 'PC Games', 360.5, 210, 5),
(4, 'PC Gadgets', 240.5, 50, 7),
(5, 'PC Monitor', 120.5, 40, 8),
(6, 'Laptops', 650.99, 140, 10),
(7, 'Movies', 10.5, 297, 11),
(8, 'PC Desktops', 1249.99, 34, 11);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `transactions`
--

CREATE TABLE `transactions` (
  `TRANSACTION_ID` int(10) NOT NULL,
  `PENDING` char(1) NOT NULL DEFAULT 'Y',
  `TRANSACTION_TYPE` char(1) NOT NULL DEFAULT 'A',
  `AMOUNT` double NOT NULL DEFAULT 0,
  `DATE` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `users`
--

CREATE TABLE `users` (
  `USERID` int(10) NOT NULL,
  `USERNAME` varchar(20) NOT NULL,
  `PASSWORD` varchar(20) NOT NULL,
  `ACCOUNT_NUMBER` varchar(27) NOT NULL,
  `USER_TYPE` char(1) NOT NULL DEFAULT 'U'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `users`
--

INSERT INTO `users` (`USERID`, `USERNAME`, `PASSWORD`, `ACCOUNT_NUMBER`, `USER_TYPE`) VALUES
(1, 'geoka', 'geoka3440', 'GR0101010101010101010101010', 'I'),
(2, 'epi', 'epi3440', 'BR0101010101010101010101010', 'C'),
(3, 'stone', 'geoka3440', 'BR0202020202020202020202020', 'C'),
(4, 'selmo', 'selmo3440', 'GR0202020202020202020202020', 'I'),
(5, 'lyxa', 'lyxa3440', 'GB0101010101010101010101010', 'M'),
(6, 'ote', 'ote3440', 'BR0303030303030303030303030', 'C'),
(7, 'steta', 'steta3440', 'GB0202020202020202020202020', 'M'),
(8, 'zikou', 'zikou3440', 'GB0303030303030303030303030', 'M'),
(9, 'vixa', 'vixa3440', 'GR0303030303030303030303030', 'I'),
(10, 'distro', 'distro3440', 'GB0404040404040404040404040', 'M'),
(11, 'spitri', 'spitri3440', 'GB0505050505050505050505050', 'M'),
(12, 'csd', 'csd3440', 'BR0404040404040404040404040', 'C'),
(13, 'beha', 'beha3440', 'GR0404040404040404040404040', 'I'),
(14, 'bewa', 'bewa3440', 'GR0505050505050505050505050', 'I'),
(15, 'alpha', 'alpha3440', 'BR0505050505050505050505050', 'C');

--
-- Ευρετήρια για άχρηστους πίνακες
--

--
-- Ευρετήρια για πίνακα `bought_products`
--
ALTER TABLE `bought_products`
  ADD PRIMARY KEY (`TRANSACTION_ID`,`PRODUCT_ID`,`MERCHANT_USERID`),
  ADD KEY `TRANSACTION_ID` (`TRANSACTION_ID`),
  ADD KEY `PRODUCT_ID` (`PRODUCT_ID`),
  ADD KEY `MERCHANT_USERID` (`MERCHANT_USERID`),
  ADD KEY `PRODUCT_ID_2` (`PRODUCT_ID`,`MERCHANT_USERID`);

--
-- Ευρετήρια για πίνακα `citizens`
--
ALTER TABLE `citizens`
  ADD PRIMARY KEY (`USERID`),
  ADD UNIQUE KEY `AMKA` (`AMKA`),
  ADD UNIQUE KEY `VAT` (`VAT`),
  ADD KEY `USERID` (`USERID`) USING BTREE;

--
-- Ευρετήρια για πίνακα `cm_trades`
--
ALTER TABLE `cm_trades`
  ADD PRIMARY KEY (`CITIZEN_USERID`,`MERCHANT_USERID`,`TRANSACTION_ID`),
  ADD KEY `CITIZEN_USERID` (`CITIZEN_USERID`),
  ADD KEY `MERCHANT_USERID` (`MERCHANT_USERID`),
  ADD KEY `TRANSACTION_ID` (`TRANSACTION_ID`);

--
-- Ευρετήρια για πίνακα `cm_traffics`
--
ALTER TABLE `cm_traffics`
  ADD PRIMARY KEY (`EMPLOYEE_ID`,`COMPANY_USERID`,`MERCHANT_USERID`,`TRANSACTION_ID`),
  ADD KEY `EMPLOYEE_ID` (`EMPLOYEE_ID`),
  ADD KEY `COMPANY_USERID` (`COMPANY_USERID`),
  ADD KEY `MERCHANT_USERID` (`MERCHANT_USERID`),
  ADD KEY `TRANSACTION_ID` (`TRANSACTION_ID`);

--
-- Ευρετήρια για πίνακα `companies`
--
ALTER TABLE `companies`
  ADD PRIMARY KEY (`USERID`),
  ADD UNIQUE KEY `NAME` (`NAME`),
  ADD KEY `USERID` (`USERID`) USING BTREE;

--
-- Ευρετήρια για πίνακα `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`EMPLOYEE_ID`,`COMPANY_USERID`),
  ADD KEY `COMPANY_USERID` (`COMPANY_USERID`) USING BTREE;

--
-- Ευρετήρια για πίνακα `merchants`
--
ALTER TABLE `merchants`
  ADD PRIMARY KEY (`USERID`),
  ADD UNIQUE KEY `USERID` (`USERID`) USING BTREE;

--
-- Ευρετήρια για πίνακα `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`PRODUCT_ID`,`MERCHANT_USERID`),
  ADD UNIQUE KEY `NAME` (`NAME`),
  ADD KEY `MERCHANT_USERID` (`MERCHANT_USERID`) USING BTREE;

--
-- Ευρετήρια για πίνακα `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`TRANSACTION_ID`);

--
-- Ευρετήρια για πίνακα `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`USERID`),
  ADD UNIQUE KEY `ACCOUNT_NUMBER` (`ACCOUNT_NUMBER`),
  ADD UNIQUE KEY `USERNAME` (`USERNAME`);

--
-- AUTO_INCREMENT για άχρηστους πίνακες
--

--
-- AUTO_INCREMENT για πίνακα `employees`
--
ALTER TABLE `employees`
  MODIFY `EMPLOYEE_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT για πίνακα `products`
--
ALTER TABLE `products`
  MODIFY `PRODUCT_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT για πίνακα `transactions`
--
ALTER TABLE `transactions`
  MODIFY `TRANSACTION_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT για πίνακα `users`
--
ALTER TABLE `users`
  MODIFY `USERID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Περιορισμοί για άχρηστους πίνακες
--

--
-- Περιορισμοί για πίνακα `bought_products`
--
ALTER TABLE `bought_products`
  ADD CONSTRAINT `bought_products_ibfk_1` FOREIGN KEY (`TRANSACTION_ID`) REFERENCES `transactions` (`TRANSACTION_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `bought_products_ibfk_2` FOREIGN KEY (`PRODUCT_ID`,`MERCHANT_USERID`) REFERENCES `products` (`PRODUCT_ID`, `MERCHANT_USERID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Περιορισμοί για πίνακα `citizens`
--
ALTER TABLE `citizens`
  ADD CONSTRAINT `citizens_ibfk_1` FOREIGN KEY (`USERID`) REFERENCES `users` (`USERID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Περιορισμοί για πίνακα `cm_trades`
--
ALTER TABLE `cm_trades`
  ADD CONSTRAINT `cm_trades_ibfk_1` FOREIGN KEY (`CITIZEN_USERID`) REFERENCES `citizens` (`USERID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cm_trades_ibfk_2` FOREIGN KEY (`MERCHANT_USERID`) REFERENCES `merchants` (`USERID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cm_trades_ibfk_3` FOREIGN KEY (`TRANSACTION_ID`) REFERENCES `transactions` (`TRANSACTION_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Περιορισμοί για πίνακα `cm_traffics`
--
ALTER TABLE `cm_traffics`
  ADD CONSTRAINT `cm_traffics_ibfk_1` FOREIGN KEY (`EMPLOYEE_ID`,`COMPANY_USERID`) REFERENCES `employees` (`EMPLOYEE_ID`, `COMPANY_USERID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cm_traffics_ibfk_2` FOREIGN KEY (`MERCHANT_USERID`) REFERENCES `merchants` (`USERID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Περιορισμοί για πίνακα `companies`
--
ALTER TABLE `companies`
  ADD CONSTRAINT `companies_ibfk_1` FOREIGN KEY (`USERID`) REFERENCES `users` (`USERID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Περιορισμοί για πίνακα `employees`
--
ALTER TABLE `employees`
  ADD CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`COMPANY_USERID`) REFERENCES `companies` (`USERID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Περιορισμοί για πίνακα `merchants`
--
ALTER TABLE `merchants`
  ADD CONSTRAINT `merchants_ibfk_1` FOREIGN KEY (`USERID`) REFERENCES `users` (`USERID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Περιορισμοί για πίνακα `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`MERCHANT_USERID`) REFERENCES `merchants` (`USERID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
