-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema db-campos
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema db-campos
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db-campos` DEFAULT CHARACTER SET latin1 ;
USE `db-campos` ;

-- -----------------------------------------------------
-- Table `db-campos`.`campo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db-campos`.`campo` (
  `NUMERO_CAMPO` INT(11) NOT NULL,
  `NOMBRE` VARCHAR(255) NULL DEFAULT NULL,
  `ESTADO` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`NUMERO_CAMPO`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `db-campos`.`tipo_suelo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db-campos`.`tipo_suelo` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPCION` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `db-campos`.`lote`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db-campos`.`lote` (
  `NUMERO_LOTE` INT(11) NOT NULL AUTO_INCREMENT,
  `SUPERFICIE` DOUBLE NULL DEFAULT NULL,
  `TIPO_SUELO_ID` INT(11) NOT NULL,
  PRIMARY KEY (`NUMERO_LOTE`),
  INDEX `FKp1bfw6n6co3w2eenw8goiwnim` (`TIPO_SUELO_ID` ASC) VISIBLE,
  CONSTRAINT `FKp1bfw6n6co3w2eenw8goiwnim`
    FOREIGN KEY (`TIPO_SUELO_ID`)
    REFERENCES `db-campos`.`tipo_suelo` (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `db-campos`.`campo_lote`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db-campos`.`campo_lote` (
  `NUMERO_CAMPO` INT(11) NOT NULL,
  `NUMERO_LOTE` INT(11) NOT NULL,
  PRIMARY KEY (`NUMERO_CAMPO`, `NUMERO_LOTE`),
  INDEX `FK6v2pls7fg7eljqqt44r25k0hy` (`NUMERO_LOTE` ASC) VISIBLE,
  CONSTRAINT `FK6v2pls7fg7eljqqt44r25k0hy`
    FOREIGN KEY (`NUMERO_LOTE`)
    REFERENCES `db-campos`.`lote` (`NUMERO_LOTE`),
  CONSTRAINT `FKakm10e9yyopi1vdoxfh2xmofr`
    FOREIGN KEY (`NUMERO_CAMPO`)
    REFERENCES `db-campos`.`campo` (`NUMERO_CAMPO`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
