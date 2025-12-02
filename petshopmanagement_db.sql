-- 1. DATABASE CREATION
CREATE DATABASE petshopmanagement_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE petshopmanagement_db;

-- 2. TABLE: ACCOUNTS (Matches Account.java)
-- Stores login credentials for both Admins and Customers
CREATE TABLE accounts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role ENUM('admin', 'customer') NOT NULL DEFAULT 'customer',
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 3. TABLE: CUSTOMERS (Matches Customer.java)
-- One-to-One relationship with Accounts
CREATE TABLE customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(100),
    address VARCHAR(255),
    account_id INT UNIQUE, -- Links to the accounts table
    FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE SET NULL
);

-- 4. TABLE: PETS (Matches Pet.java)
CREATE TABLE pets (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT,
    price DECIMAL(10, 2) NOT NULL,
    type ENUM('dog', 'cat') NOT NULL, -- Matches 'type' in Pet.java
    species VARCHAR(50), -- e.g., 'Canine', 'Feline'
    breed VARCHAR(50),   -- e.g., 'Golden Retriever'
    status ENUM('Available', 'Sold') DEFAULT 'Available',
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 5. TABLE: PRODUCTS (Matches Product.java)
CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    quantity INT DEFAULT 0,
    category VARCHAR(50), -- e.g., 'Food', 'Toys'
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 6. TABLE: ORDERS (Matches Order.java logic)
-- Supports buying either a Pet OR a Product in a single order record
CREATE TABLE orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    
    -- Denormalized fields to match Order.java structure
    pet_id INT NULL,       -- NULL if buying a Product
    product_id INT NULL,   -- NULL if buying a Pet
    item_type ENUM('pet', 'product') NOT NULL, 
    
    quantity INT DEFAULT 1,
    total DECIMAL(10, 2) NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('Pending', 'Completed', 'Cancelled') DEFAULT 'Pending',

    -- Foreign Keys
    FOREIGN KEY (customer_id) REFERENCES customers(id),
    FOREIGN KEY (pet_id) REFERENCES pets(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

-- 1. Accounts
INSERT INTO accounts (username, password, role) VALUES 
('Bionic', '330044', 'admin'),
('Nefer', '10000', 'customer');

-- 2. Customers
INSERT INTO customers (name, phone, email, address, account_id) VALUES 
('Nefer', '010280001199', 'nefer@curatorium.org', 'Nasha Town, Lempo Isle, Nod-Krai', 2);

-- 3. Pets
INSERT INTO pets (name, age, price, type, species, breed, status) VALUES 
('Max', 2, 500.00, 'dog', 'Canine', 'Golden Retriever', 'Available'),
('Luna', 1, 300.00, 'cat', 'Feline', 'British Shorthair', 'Available'),
('Rocky', 3, 700.00, 'dog', 'Canine', 'German Shepherd', 'Sold'),
('Bella', 1, 1200.00, 'dog', 'Canine', 'Poodle', 'Available');

-- 4. Products
INSERT INTO products (name, price, quantity, category, description) VALUES 
('Royal Canin Adult Dog Food', 50.00, 50, 'Food', 'Nutritious food for adult dogs'),
('Scented Cat Litter', 20.00, 100, 'Accessories', 'Lemon scent, high clumping'),
('Durable Dog Leash', 15.00, 20, 'Accessories', '2 meters long, red color'),
('Cat Tree House', 120.00, 10, 'Toys', 'Multi-level play area for cats');
