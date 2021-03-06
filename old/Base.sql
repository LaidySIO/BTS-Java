-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema inscription
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema inscription
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `inscription` DEFAULT CHARACTER SET utf8 ;
USE `inscription` ;

-- -----------------------------------------------------
-- Table `inscription`.`candidat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inscription`.`candidat` (
  `idCandidat` INT(11) NOT NULL AUTO_INCREMENT,
  `nomCandidat` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCandidat`))
ENGINE = InnoDB
AUTO_INCREMENT = 533
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `inscription`.`competition`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inscription`.`competition` (
  `idCompetition` INT(11) NOT NULL AUTO_INCREMENT,
  `nomCompetition` VARCHAR(35) NOT NULL,
  `Description` VARCHAR(200) NULL DEFAULT NULL,
  `dateCloture` DATE NOT NULL,
  `enEquipe` BLOB NULL DEFAULT NULL,
  PRIMARY KEY (`idCompetition`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `inscription`.`equipe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inscription`.`equipe` (
  `idEquipe` INT(11) NOT NULL AUTO_INCREMENT,
  `nomEquipe` VARCHAR(45) NULL DEFAULT NULL,
  `idCandidat` INT(11) NOT NULL,
  PRIMARY KEY (`idEquipe`),
  INDEX `fk_Equipe_Candidat_idx` (`idEquipe` ASC),
  INDEX `fk_equipe_candidat1_idx` (`idCandidat` ASC),
  CONSTRAINT `fk_equipe_candidat1`
    FOREIGN KEY (`idCandidat`)
    REFERENCES `inscription`.`candidat` (`idCandidat`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `inscription`.`inscription`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inscription`.`inscription` (
  `idCompetition` INT(11) NOT NULL,
  `idCandidat` INT(11) NOT NULL,
  PRIMARY KEY (`idCompetition`, `idCandidat`),
  INDEX `fk_Competition_has_Candidat_Candidat1_idx` (`idCandidat` ASC),
  INDEX `fk_Competition_has_Candidat_Competition1_idx` (`idCompetition` ASC),
  CONSTRAINT `fk_Competition_has_Candidat_Candidat1_idx`
    FOREIGN KEY (`idCandidat`)
    REFERENCES `inscription`.`candidat` (`idCandidat`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Competition_has_Candidat_Competition1_idx`
    FOREIGN KEY (`idCompetition`)
    REFERENCES `inscription`.`competition` (`idCompetition`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `inscription`.`personne`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inscription`.`personne` (
  `idPersonne` INT(11) NOT NULL AUTO_INCREMENT,
  `nomPersonne` VARCHAR(45) NOT NULL,
  `prenomPersonne` VARCHAR(45) NULL DEFAULT NULL,
  `idCandidat` INT(11) NULL DEFAULT NULL,
  `mail` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idPersonne`),
  INDEX `fk_personne_candidat1_idx` (`idCandidat` ASC),
  CONSTRAINT `fk_personne_candidat1`
    FOREIGN KEY (`idCandidat`)
    REFERENCES `inscription`.`candidat` (`idCandidat`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8;

USE `inscription` ;

-- -----------------------------------------------------
-- Placeholder table for view `inscription`.`competitionenequipe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inscription`.`competitionenequipe` (`nomCompetition` INT);

-- -----------------------------------------------------
-- Placeholder table for view `inscription`.`competitionnew`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inscription`.`competitionnew` (`nomCompetition` INT);

-- -----------------------------------------------------
-- Placeholder table for view `inscription`.`competitionold`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inscription`.`competitionold` (`nomCompetition` INT);

-- -----------------------------------------------------
-- Placeholder table for view `inscription`.`pasdequipe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inscription`.`pasdequipe` (`nomPersonne` INT);

-- -----------------------------------------------------
-- procedure addtocompete
-- -----------------------------------------------------

DELIMITER $$
USE `inscription`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addtocompete`(idcand int(32), idcomp int(32))
begin
		insert into inscription (idcandidat,idcompetition) values (idcand,idcomp);
end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure afficheEquipe
-- -----------------------------------------------------

DELIMITER $$
USE `inscription`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `afficheEquipe`()
begin
        select * from equipe;
     
end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getidcand
-- -----------------------------------------------------

DELIMITER $$
USE `inscription`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getidcand`(cand varchar(45))
begin
		select idCandidat
        from Candidat
        where cand = nomCandidat;
	
end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getidcomp
-- -----------------------------------------------------

DELIMITER $$
USE `inscription`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getidcomp`(compet varchar(45))
begin
		select idCompetition
        from competition
        where compet = nomCompetition;
	
end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure insertEquipe
-- -----------------------------------------------------

DELIMITER $$
USE `inscription`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertEquipe`(nom varchar(45))
begin
        insert into equipe (nomEquipe) values (nom);
        insert into candidat (idCandidat,nomCandidat) values (last_insert_id(), nom);
end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure insertInscription
-- -----------------------------------------------------

DELIMITER $$
USE `inscription`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertInscription`(idCand int, idComp int )
begin
        insert into inscription (idCandidat, idCompetition) values (idCand, idComp);
end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure insertPersonne
-- -----------------------------------------------------

DELIMITER $$
USE `inscription`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertPersonne`(nom varchar(45), prenom varchar(45), mail varchar(45))
begin
        insert into personne (nomPersonne, prenomPersonne, mail) values (nom, prenom, mail);
        insert into candidat (idCandidat,nomCandidat) values (last_insert_id(), nom);
end$$

DELIMITER ;

-- -----------------------------------------------------
-- View `inscription`.`competitionenequipe`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inscription`.`competitionenequipe`;
USE `inscription`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `inscription`.`competitionenequipe` AS select `inscription`.`competition`.`nomCompetition` AS `nomCompetition` from `inscription`.`competition` where (`inscription`.`competition`.`enEquipe` = 'true');

-- -----------------------------------------------------
-- View `inscription`.`competitionnew`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inscription`.`competitionnew`;
USE `inscription`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `inscription`.`competitionnew` AS select `inscription`.`competition`.`nomCompetition` AS `nomCompetition` from `inscription`.`competition` where (`inscription`.`competition`.`dateCloture` > cast(now() as date));

-- -----------------------------------------------------
-- View `inscription`.`competitionold`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inscription`.`competitionold`;
USE `inscription`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `inscription`.`competitionold` AS select `inscription`.`competition`.`nomCompetition` AS `nomCompetition` from `inscription`.`competition` where (`inscription`.`competition`.`dateCloture` < cast(now() as date));

-- -----------------------------------------------------
-- View `inscription`.`pasdequipe`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inscription`.`pasdequipe`;
USE `inscription`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `inscription`.`pasdequipe` AS select `p`.`nomPersonne` AS `nomPersonne` from `inscription`.`personne` `p` where (not(`p`.`idPersonne` in (select `m`.`idPersonne` from `inscription`.`membres_equipe` `m`)));

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
