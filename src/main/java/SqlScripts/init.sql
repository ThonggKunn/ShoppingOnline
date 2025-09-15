CREATE SCHEMA shopping_online;

USE shopping_online;

-- Create users table
CREATE TABLE users
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    email    VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(500)        NOT NULL,
    fullname VARCHAR(500)        NOT NULL,
    status   VARCHAR(500)        NOT NULL,
    role     VARCHAR(50)         NOT NULL
);

-- Create products table
CREATE TABLE products
(
    id             INT AUTO_INCREMENT PRIMARY KEY,
    name           VARCHAR(500)   NOT NULL,
    description    TEXT,
    price          NUMERIC(18, 3) NOT NULL,
    stock          INT,
    original_price NUMERIC(18, 3) NOT NULL,
    category       VARCHAR(255)
);

-- Create carts table
CREATE TABLE carts
(
    id      INTEGER(10) PRIMARY KEY,
    user_id INTEGER(10),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

-- Create cart_items table
CREATE TABLE cart_items
(
    id         INTEGER(10) PRIMARY KEY,
    product_id INTEGER(10),
    cart_id    INTEGER(10),
    quantity   INTEGER(10),
    FOREIGN KEY (product_id) REFERENCES products (id),
    FOREIGN KEY (cart_id) REFERENCES carts (id)
);

-- Create orders table
CREATE TABLE orders
(
    id           INTEGER(10) PRIMARY KEY,
    description  VARCHAR(500) NULL,
    status       VARCHAR(255),
    total_amount NUMERIC(19, 3),
    user_id      INTEGER(10),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

-- Create order_items table
CREATE TABLE order_items
(
    id             INTEGER(10) PRIMARY KEY,
    product_id     INTEGER(10),
    order_id       INTEGER(10),
    original_price NUMERIC(19, 3),
    price          NUMERIC(19, 3),
    FOREIGN KEY (product_id) REFERENCES products (id),
    FOREIGN KEY (order_id) REFERENCES orders (id)
);