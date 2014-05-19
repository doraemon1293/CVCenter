SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `cvcenter` ;
CREATE SCHEMA IF NOT EXISTS `cvcenter` DEFAULT CHARACTER SET utf8 ;
USE `cvcenter` ;

-- -----------------------------------------------------
-- Table `cvcenter`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cvcenter`.`users` ;

CREATE  TABLE IF NOT EXISTS `cvcenter`.`users` (
  `USER_ID` INT(10) UNSIGNED NOT NULL ,
  `USERNAME` VARCHAR(45) NOT NULL ,
  `PASSWORD` VARCHAR(45) NOT NULL ,
  `ENABLED` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`USER_ID`),
UNIQUE INDEX `user_name_UNIQUE` (`USERNAME` ASC)  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cvcenter`.`student`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cvcenter`.`student` ;

CREATE  TABLE IF NOT EXISTS `cvcenter`.`student` (
  `STUDENT_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `FIRST_NAME` VARCHAR(45) NOT NULL ,
  `LAST_NAME` VARCHAR(45) NOT NULL ,
  `EMAIL` VARCHAR(45) NOT NULL ,
  `TEL` VARCHAR(15) NULL DEFAULT NULL ,
  `INITIAL` VARCHAR(10) NULL DEFAULT NULL ,
  `USER_ID` INT(10) UNSIGNED NOT NULL ,
  PRIMARY KEY (`STUDENT_ID`) ,
  UNIQUE INDEX `student_id_UNIQUE` (`STUDENT_ID` ASC) ,
  INDEX `fk_student_users1_idx` (`USER_ID` ASC) ,
  CONSTRAINT `fk_student_users1`
    FOREIGN KEY (`USER_ID` )
    REFERENCES `cvcenter`.`users` (`USER_ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cvcenter`.`user_roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cvcenter`.`user_roles` ;

CREATE  TABLE IF NOT EXISTS `cvcenter`.`user_roles` (
  `USER_ROLE_ID` INT(10) UNSIGNED NOT NULL ,
  `USER_ID` INT(10) UNSIGNED NOT NULL ,
  `AUTHORITY` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`USER_ROLE_ID`) ,
  INDEX `FK_user_roles` (`USER_ID` ASC) ,
  CONSTRAINT `FK_user_roles`
    FOREIGN KEY (`USER_ID` )
    REFERENCES `cvcenter`.`users` (`USER_ID` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cvcenter`.`company`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cvcenter`.`company` ;

CREATE  TABLE IF NOT EXISTS `cvcenter`.`company` (
  `COMPANY_ID` INT(10) UNSIGNED NOT NULL ,
  `COMPANY_NAME` VARCHAR(45) NOT NULL ,
  `ADDRESS` VARCHAR(100) NOT NULL ,
  `EMAIL` VARCHAR(45) NOT NULL ,
  `TEL` VARCHAR(45) NULL ,
  `CONTACT_NAME` VARCHAR(45) NOT NULL ,
  `USER_ID` INT(10) UNSIGNED NOT NULL ,
  PRIMARY KEY (`COMPANY_ID`) ,
  INDEX `fk_company_users1_idx` (`USER_ID` ASC) ,
  CONSTRAINT `fk_company_users1`
    FOREIGN KEY (`USER_ID` )
    REFERENCES `cvcenter`.`users` (`USER_ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cvcenter`.`cv`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cvcenter`.`cv` ;

CREATE  TABLE IF NOT EXISTS `cvcenter`.`cv` (
  `CV_ID` INT(10) UNSIGNED NOT NULL ,
  `FORMAT` VARCHAR(45) NULL DEFAULT 'pdf' ,
  `FILEURL` VARCHAR(100) NOT NULL ,
  `FILENAME` VARCHAR(45) NULL ,
  `JOB_CAT` VARCHAR(200) NULL ,
  `STUDENT_ID` INT(10) UNSIGNED NOT NULL ,
  PRIMARY KEY (`CV_ID`) ,
  INDEX `fk_cv_student1_idx` (`STUDENT_ID` ASC) ,
  CONSTRAINT `fk_cv_student1`
    FOREIGN KEY (`STUDENT_ID` )
    REFERENCES `cvcenter`.`student` (`STUDENT_ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cvcenter`.`job`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cvcenter`.`job` ;

CREATE  TABLE IF NOT EXISTS `cvcenter`.`job` (
  `JOB_ID` INT(10) UNSIGNED NOT NULL ,
  `JOB_CAT` VARCHAR(200) NOT NULL ,
  `TITLE` VARCHAR(100) NOT NULL ,
  `DEADLINE` DATE NULL ,
  `TEXTURL` VARCHAR(100) NULL ,
  `COMPANY_ID` INT(10) UNSIGNED NOT NULL ,
  PRIMARY KEY (`JOB_ID`) ,
  INDEX `fk_job_company1_idx` (`COMPANY_ID` ASC) ,
  CONSTRAINT `fk_job_company1`
    FOREIGN KEY (`COMPANY_ID` )
    REFERENCES `cvcenter`.`company` (`COMPANY_ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cvcenter`.`job_has_cv`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cvcenter`.`job_has_cv` ;

CREATE  TABLE IF NOT EXISTS `cvcenter`.`job_has_cv` (
  `JOB_ID` INT(10) UNSIGNED NULL ,
  `CV_ID` INT(10) UNSIGNED NULL ,
  PRIMARY KEY (`JOB_ID`, `CV_ID`) ,
  INDEX `fk_job_has_cv_cv1_idx` (`CV_ID` ASC) ,
  INDEX `fk_job_has_cv_job1_idx` (`JOB_ID` ASC) ,
  CONSTRAINT `fk_job_has_cv_job1`
    FOREIGN KEY (`JOB_ID` )
    REFERENCES `cvcenter`.`job` (`JOB_ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_has_cv_cv1`
    FOREIGN KEY (`CV_ID` )
    REFERENCES `cvcenter`.`cv` (`CV_ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
