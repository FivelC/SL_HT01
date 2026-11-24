-- Complete PostgreSQL database schema and test data for 25 SQL exercises.
-- Run in pgAdmin Query Tool in the selected database.
-- CAUTION: existing Product, PC, Laptop, Printer tables are recreated!

-- PostgreSQL: run this script in pgAdmin Query Tool inside your chosen database.
BEGIN;

-- WARNING: recreates the four tables and deletes their previous rows.
DROP TABLE IF EXISTS Printer;
DROP TABLE IF EXISTS Laptop;
DROP TABLE IF EXISTS PC;
DROP TABLE IF EXISTS Product;

CREATE TABLE Product (
    maker VARCHAR(10),
    model VARCHAR(50) PRIMARY KEY,
    type VARCHAR(50)
);

CREATE TABLE PC (
    code INT PRIMARY KEY,
    model VARCHAR(50) REFERENCES Product(model),
    speed SMALLINT,
    ram SMALLINT,
    hd REAL,
    cd VARCHAR(10),
    price MONEY
);

CREATE TABLE Laptop (
    code INT PRIMARY KEY,
    model VARCHAR(50) REFERENCES Product(model),
    speed SMALLINT,
    ram SMALLINT,
    hd REAL,
    price MONEY,
    screen SMALLINT
);

CREATE TABLE Printer (
    code INT PRIMARY KEY,
    model VARCHAR(50) REFERENCES Product(model),
    color CHAR(1),
    type VARCHAR(10),
    price MONEY
);

INSERT INTO Product (maker, model, type) VALUES
('A', '1001', 'PC'),
('A', '1002', 'PC'),
('B', '2001', 'Laptop'),
('B', '2002', 'Laptop'),
('C', '3001', 'Printer'),
('D', '3002', 'Printer');

INSERT INTO PC (code, model, speed, ram, hd, cd, price) VALUES
(1, '1001', 2400, 8192, 500, '16x', 45000),
(2, '1001', 3200, 16384, 1000, '24x', 65000),
(3, '1002', 2800, 8192, 512, '16x', 52000);

INSERT INTO Laptop (code, model, speed, ram, hd, price, screen) VALUES
(1, '2001', 2200, 8192, 256, 55000, 15),
(2, '2001', 2600, 16384, 512, 75000, 15),
(3, '2002', 3000, 16384, 1000, 95000, 17);

INSERT INTO Printer (code, model, color, type, price) VALUES
(1, '3001', 'y', 'Laser', 15000),
(2, '3001', 'n', 'Laser', 10000),
(3, '3002', 'y', 'Jet', 8000);



-- PostgreSQL / pgAdmin. Additional data for the 25 SQL exercises.
-- Run against the existing database. Existing rows are not deleted.
-- Re-running is safe: existing product models and device codes are skipped.


INSERT INTO Product (maker, model, type) VALUES
('A','1101','PC'),('A','1102','PC'),('A','1103','PC'),('A','1104','PC'),
('B','1201','PC'),('B','1202','PC'),('B','1203','PC'),
('C','1301','PC'),('C','1302','PC'),
('D','1401','PC'),('D','1402','PC'),
('E','1501','PC'),('E','1502','PC'),
('A','2101','Laptop'),('A','2102','Laptop'),
('B','2201','Laptop'),('B','2202','Laptop'),
('C','2301','Laptop'),('C','2302','Laptop'),
('E','2501','Laptop'),
('A','3101','Printer'),('B','3201','Printer'),
('C','3301','Printer'),('D','3401','Printer'),
('E','3501','Printer'),('E','3502','Printer')
ON CONFLICT (model) DO NOTHING;

-- RAM values deliberately include a shared minimum (128 MB).
-- Models 1101/1102 and 1201/1202 share CPU speed and RAM.
-- HD 20 and 40 occur repeatedly; several CD speeds and price bands are present.
INSERT INTO PC (code, model, speed, ram, hd, cd, price) VALUES
(101,'1101',500,128,20,'12x',350),
(102,'1102',500,128,40,'24x',450),
(103,'1103',800,256,40,'12x',550),
(104,'1104',900,128,80,'24x',590),
(105,'1201',500,128,20,'12x',400),
(106,'1202',500,128,40,'24x',480),
(107,'1203',1000,512,120,'48x',1200),
(108,'1301',700,256,80,'16x',650),
(109,'1302',850,512,120,'24x',580),
(110,'1401',900,128,20,'12x',490),
(111,'1402',1200,1024,250,'48x',1500),
(112,'1501',900,128,40,'24x',580),
(113,'1502',750,256,100,'16x',700)
ON CONFLICT (code) DO NOTHING;

-- Both low-speed and high-speed laptops, some above $1000,
-- and manufacturers shared with PC models.
INSERT INTO Laptop (code, model, speed, ram, hd, price, screen) VALUES
(101,'2101',350,128,20,850,12),
(102,'2102',800,512,120,1300,15),
(103,'2201',400,256,40,950,13),
(104,'2202',900,1024,160,1600,17),
(105,'2301',700,512,100,1100,14),
(106,'2302',1100,1024,250,1900,17),
(107,'2501',1000,512,120,1250,15)
ON CONFLICT (code) DO NOTHING;

-- Cheapest color printers at $150: makers A and E.
-- Makers A/B/C/D/E also manufacture PCs, supporting join/subquery tasks.
INSERT INTO Printer (code, model, color, type, price) VALUES
(101,'3101','y','Jet',150),
(102,'3201','n','Laser',220),
(103,'3301','y','Laser',450),
(104,'3401','n','Matrix',180),
(105,'3501','y','Jet',150),
(106,'3502','y','Laser',700)
ON CONFLICT (code) DO NOTHING;


-- Quick verification:
-- SELECT * FROM Product ORDER BY maker, model;
-- SELECT * FROM PC ORDER BY code;
-- SELECT * FROM Laptop ORDER BY code;
-- SELECT * FROM Printer ORDER BY code;


COMMIT;
