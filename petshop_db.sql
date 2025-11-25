-- Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS petshop_db;
USE petshop_db;

-- Drop existing tables in the correct order to avoid foreign key issues
DROP TABLE IF EXISTS order_details;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS pets;
DROP TABLE IF EXISTS services;
DROP TABLE IF EXISTS admins;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS accounts;

--
-- Table: accounts
--
CREATE TABLE accounts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL, -- 'admin' or 'customer'
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--
-- Table: customers
--
CREATE TABLE customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(100),
    address VARCHAR(255),
    total_spent DECIMAL(10, 2) DEFAULT 0,
    account_id INT UNIQUE, -- Link to the accounts table
    FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE SET NULL
);

--
-- Table: pets
--
CREATE TABLE pets (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT,
    price DECIMAL(10, 2) NOT NULL,
    type VARCHAR(50), -- 'dog' or 'cat' (used by Pet.java)
    species VARCHAR(50), -- e.g., 'Canine', 'Feline'
    breed VARCHAR(50), -- e.g., 'Golden Retriever', 'Persian'
    status VARCHAR(20) DEFAULT 'Available', -- e.g., 'Available', 'Sold'
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--
-- Table: products
--
CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    quantity INT DEFAULT 0,
    category VARCHAR(50), -- e.g., 'Food', 'Accessory', 'Grooming'
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--
-- Table: orders
--
CREATE TABLE orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    product_id INT, -- Denormalized field for Order.java
    quantity INT,     -- Denormalized field for Order.java
    total DECIMAL(10, 2), -- Denormalized field for Order.java
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'Pending', -- e.g., 'Pending', 'Completed', 'Cancelled'
    FOREIGN KEY (customer_id) REFERENCES customers(id),
    FOREIGN KEY (product_id) REFERENCES products(id) -- Used by the simple Java app
);

--
-- Table: order_details
--
CREATE TABLE order_details (
    detail_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    item_id INT NOT NULL, -- Would be product_id or service_id
    item_type VARCHAR(20) NOT NULL, -- 'PRODUCT' or 'SERVICE'
    quantity INT DEFAULT 1,
    price_per_unit DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE
);

--
-- Table: services
--
CREATE TABLE services (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    duration_minutes INT,
    description TEXT
);

--
-- Table: admins
--
CREATE TABLE admins (
    id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(20),
    department VARCHAR(50),
    account_id INT UNIQUE,
    FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE SET NULL
);


--
-- INSERT SAMPLE DATA (ENGLISH)
--

-- 1. Admin Account (for LogonUI.java)
INSERT INTO accounts (username, password, role) 
VALUES ('admin', 'admin123', 'admin');

-- 2. Sample Customer
INSERT INTO accounts (username, password, role) 
VALUES ('customer1', 'pass123', 'customer');

INSERT INTO customers (name, phone, email, address, account_id) VALUES 
('John Doe', '0909123456', 'john.doe@email.com', '123 Main St', 2),
('Jane Smith', '0909654321', 'jane.smith@email.com', '456 Oak Ave', NULL);

-- 3. Sample Products
INSERT INTO products (name, price, quantity, category, description) VALUES 
('Premium Dog Food 5kg', 350000, 50, 'Food', 'High-quality dry food for adult dogs.'),
('Premium Cat Food 3kg', 280000, 40, 'Food', 'Nutritious dry food for all cat breeds.'),
('Gentle Pet Shampoo', 120000, 100, 'Grooming', 'Hypoallergenic shampoo for sensitive skin.');

-- 4. Sample Pets
INSERT INTO pets (name, age, price, type, species, breed, status) VALUES 
('Max', 2, 15000000, 'dog', 'Canine', 'Golden Retriever', 'Available'),
('Luna', 1, 8000000, 'cat', 'Feline', 'Persian', 'Available'),
('Charlie', 3, 18000000, 'dog', 'Canine', 'Siberian Husky', 'Available');

-- 5. Sample Services
INSERT INTO services (name, price, duration_minutes, description) VALUES
('Standard Grooming', 200000, 60, 'Includes bath, haircut, and nail trimming.'),
('Basic Health Checkup', 300000, 45, 'Complete health examination by a vet.');

USE petshop_db;

-- Thêm cột pet_id, cho phép NULL
ALTER TABLE orders
ADD COLUMN pet_id INT NULL,
ADD FOREIGN KEY (pet_id) REFERENCES pets(id);

-- Thêm cột item_type để phân biệt
ALTER TABLE orders
ADD COLUMN item_type VARCHAR(20) NOT NULL DEFAULT 'product';

DELETE FROM accounts WHERE username = 'hao';
DELETE FROM accounts WHERE username = 'hao_moi';