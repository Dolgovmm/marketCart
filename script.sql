CREATE SCHEMA `market` ;

CREATE TABLE `market`.`products` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `price` INT NOT NULL,
  `article` INT NOT NULL,
  `availible` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `article_UNIQUE` (`article` ASC));


CREATE TABLE `market`.`Clients` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `phonenumber` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));

CREATE TABLE `market`.`cart` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `total_items` INT NOT NULL,
  `products_cost` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));

CREATE TABLE `market`.`cart_items` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cart_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));



INSERT INTO `market`.`Clients` (`name`, `email`, `phonenumber`) VALUES ('Alex', 'alex@mail.ru', '123456789');
INSERT INTO `market`.`Clients` (`name`, `email`, `phonenumber`) VALUES ('John', 'john@mail.ru', '234567891');
INSERT INTO `market`.`Clients` (`name`, `email`, `phonenumber`) VALUES ('Parker', 'parker@mail.ru', '345678912');

INSERT INTO `market`.`products` (`name`, `description`, `price`, `article`, `availible`) VALUES ('telephon', 'telephone description', '1000', '1000001', '1');
INSERT INTO `market`.`products` (`name`, `description`, `price`, `article`, `availible`) VALUES ('sd card', 'sd card description', '500', '1000002', '1');
INSERT INTO `market`.`products` (`name`, `description`, `price`, `article`, `availible`) VALUES ('flash', 'flash description', '400', '1000003', '1');









