DROP DATABASE IF EXISTS pizzisalle;
CREATE DATABASE pizzisalle;
USE pizzisalle;

DROP TABLE IF EXISTS Settings;
CREATE TABLE Settings(
    did_populate BOOLEAN
);

DROP TABLE IF EXISTS Address CASCADE;
CREATE TABLE Address(
    id_address INT NOT NULL AUTO_INCREMENT,
    street_name VARCHAR(255),
    street_number INT,
    postal_code VARCHAR(255),
    city VARCHAR(255),
    country VARCHAR(255),
    PRIMARY KEY (id_address)
);

DROP TABLE IF EXISTS User CASCADE;
CREATE TABLE User(
    id_user INT NOT NULL AUTO_INCREMENT,
    id_address INT,
    name VARCHAR(255) NOT NULL,
    middle_name VARCHAR(255),
    last_name VARCHAR(255),
    age INT NOT NULL,
    phone_number VARCHAR(255),
    email VARCHAR(255),
    created_at DATETIME,
    is_active BOOLEAN,
    PRIMARY KEY (id_user),
    FOREIGN KEY (id_address) REFERENCES Address(id_address)
);

DROP TABLE IF EXISTS Worker CASCADE;
CREATE TABLE Worker(
    id_worker INT NOT NULL,
    password VARCHAR(255),
    last_access DATETIME,
    permission_level INT DEFAULT 0,
    PRIMARY KEY (id_worker),
    FOREIGN KEY (id_worker) REFERENCES User(id_user)
);

DROP TABLE IF EXISTS Delegation CASCADE;
CREATE TABLE Delegation(
    id_delegation INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id_delegation)
);

DROP TABLE IF EXISTS Ingredient CASCADE;
CREATE TABLE Ingredient(
    id_ingredient INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    PRIMARY KEY (id_ingredient)
);

DROP TABLE IF EXISTS Mass CASCADE;
CREATE TABLE Mass(
    id_mass INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id_mass)
);

DROP TABLE IF EXISTS Drink CASCADE;
CREATE TABLE Drink(
    id_drink INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id_drink)
);

DROP TABLE IF EXISTS Pizza CASCADE;
CREATE TABLE Pizza(
    id_pizza INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id_pizza)
);

DROP TABLE IF EXISTS PizzaIngredient CASCADE;
CREATE TABLE PizzaIngredient(
    id_pizza INT NOT NULL,
    id_ingredient INT NOT NULL,
    quantity INT,
    PRIMARY KEY (id_pizza, id_ingredient),
    FOREIGN KEY(id_ingredient) REFERENCES Ingredient(id_ingredient),
    FOREIGN KEY(id_pizza) REFERENCES Pizza(id_pizza)
);

DROP TABLE IF EXISTS Extra CASCADE;
CREATE TABLE Extra(
    id_extra INT NOT NULL AUTO_INCREMENT,
    id_ingredient INT NOT NULL,
    quantity INT,
    PRIMARY KEY (id_extra),
    FOREIGN KEY (id_ingredient) REFERENCES Ingredient(id_ingredient)
);

DROP TABLE IF EXISTS PizzaExtra CASCADE;
CREATE TABLE PizzaExtra(
    id_extra INT NOT NULL,
    id_pizza INT NOT NULL,
    PRIMARY KEY (id_pizza, id_extra),
    FOREIGN KEY (id_pizza) REFERENCES Pizza(id_pizza),
    FOREIGN KEY (id_extra) REFERENCES Extra(id_extra)
);

DROP TABLE IF EXISTS CustomerOrder CASCADE;
CREATE TABLE CustomerOrder(
    id_order INT NOT NULL AUTO_INCREMENT,
    id_customer INT,
    id_delegation INT,
    comment VARCHAR(255),
    PRIMARY KEY (id_order),
    FOREIGN KEY(id_customer) REFERENCES User(id_user),
    FOREIGN KEY (id_delegation) REFERENCES Delegation(id_delegation)
);

DROP TABLE IF EXISTS OrderItem CASCADE;
CREATE TABLE OrderItem(
    id_order_item INT NOT NULL AUTO_INCREMENT,
    id_order INT,
    id_pizza INT,
    id_drink INT,
    id_mass INT,
    quantity INT DEFAULT 1,
    PRIMARY KEY (id_order_item),
    FOREIGN KEY (id_order) REFERENCES CustomerOrder(id_order),
    FOREIGN KEY (id_pizza) REFERENCES Pizza(id_pizza),
    FOREIGN KEY (id_drink) REFERENCES Drink(id_drink),
    FOREIGN KEY (id_mass) REFERENCES Mass(id_mass)
);



