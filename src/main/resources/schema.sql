DROP SCHEMA IF EXISTS `lab404` ;
CREATE SCHEMA IF NOT EXISTS `lab404`;
USE `lab404` ;

DROP TABLE IF EXISTS `lab404`.`doctor` ;
CREATE TABLE IF NOT EXISTS `lab404`.`doctor` (
    `employee_id` BIGINT NOT NULL,
    `department` VARCHAR(255) NULL DEFAULT NULL,
    `name` VARCHAR(255) NULL DEFAULT NULL,
    `status` ENUM('ON_CALL', 'ON', 'OFF') NULL DEFAULT NULL,
    PRIMARY KEY (`employee_id`));

DROP TABLE IF EXISTS `lab404`.`patient` ;
CREATE TABLE IF NOT EXISTS `lab404`.`patient` (
    `patient_id` BIGINT NOT NULL AUTO_INCREMENT,
    `date_of_birth` DATE NULL DEFAULT NULL,
    `name` VARCHAR(255) NULL DEFAULT NULL,
    `admitted_by` BIGINT NULL DEFAULT NULL,
    PRIMARY KEY (`patient_id`),
    INDEX `FKoedppywf1reywfnwyaupjufae` (`admitted_by` ASC) VISIBLE,
    CONSTRAINT `FKoedppywf1reywfnwyaupjufae`
    FOREIGN KEY (`admitted_by`)
    REFERENCES `lab404`.`doctor` (`employee_id`));
