-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  lun. 04 mars 2019 à 09:34
-- Version du serveur :  5.7.24
-- Version de PHP :  7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `projetsport`
--
CREATE DATABASE IF NOT EXISTS `projetsport` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `projetsport`;

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

DROP TABLE IF EXISTS `evenement`;
CREATE TABLE IF NOT EXISTS `evenement` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_event` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `horaire` varchar(255) DEFAULT NULL,
  `nbr_participants` int(11) NOT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `createur_id` bigint(20) DEFAULT NULL,
  `sport_id` bigint(20) DEFAULT NULL,
  `nbrmax` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKahtvcfxc7y6o305dtj3mtq05m` (`createur_id`),
  KEY `FKgidp1g7gor3ee1tsr63rmvppt` (`sport_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `evenement`
--


INSERT INTO `evenement` (`id`, `date_event`, `description`, `horaire`, `nbr_participants`, `titre`, `createur_id`, `sport_id`) VALUES
(1, '19/03/15', 'Petit tennis tranquillou après le taf', '17:30', 2, 'Tennis Simple niveau débutant', 2, 1),
(2, '25/03/15', 'Seulement pour les pros', '18:30', 1, 'Tennis Double expert', 3, 1),
(3, '22/03/15', 'Vous savez où trouver un terrain ?', '20:00', 1, 'Volley entre amis', 1, 3),
(4, '23/03/15', 'Quelqu\'un a des chaussures ?', '17:30', 3, 'Football', 3, 2),
(5, '24/03/15', 'Barack tu viens obligé', '18:30', 3, 'NBA street', 3, 4);



DROP TRIGGER IF EXISTS participmax;
DELIMITER $$
CREATE TRIGGER participmax BEFORE INSERT ON evenement FOR EACH ROW BEGIN
 IF NEW.nbrmax = 0 THEN
    SET NEW.nbrmax = 
    	(SELECT nbr_max FROM sport WHERE sport.id = NEW.sport_id) ;
 END IF ;
END $$
DELIMITER ;
-- --------------------------------------------------------

--
-- Structure de la table `participation`
--

DROP TABLE IF EXISTS `participation`;
CREATE TABLE IF NOT EXISTS `participation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `event_id` bigint(20) DEFAULT NULL,
  `participant_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhx2eb7nm32t8n2loaqujjipgs` (`event_id`),
  KEY `FKn4fpf71kopdfgyq1fcp804r8s` (`participant_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `participation`
--

INSERT INTO `participation` (`id`, `event_id`, `participant_id`) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 2, 2),
(4, 3, 3),
(5, 4, 8),
(6, 5, 6),
(7, 5, 9),
(8, 5, 2),
(9, 4, 2);
--
-- Déclencheurs `participation`
--
DROP TRIGGER IF EXISTS `incNbPart`;
DELIMITER $$
CREATE TRIGGER `incNbPart` AFTER INSERT ON `participation` FOR EACH ROW BEGIN
    UPDATE evenement 
    SET nbr_participants = nbr_participants + 1 
    WHERE id = NEW.event_id ;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `sport`
--

DROP TABLE IF EXISTS `sport`;
CREATE TABLE IF NOT EXISTS `sport` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lien` varchar(255) DEFAULT NULL,
  `nbr_max` int(11) NOT NULL,
  `nbr_min` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `sport`
--

INSERT INTO `sport` (`id`, `lien`, `nbr_max`, `nbr_min`, `nom`) VALUES
(1, '../assets/tennis-event.jpg', 4, 2, 'Tennis'),
(2, '../assets/foot-event.jpg', 22, 5, 'Football'),
(3, '../assets/volley-event.jpg', 8, 6, 'Volley-Ball'),
(4, '../assets/basket-event.jpg', 12, 6, 'Basketball'),
(5, '../assets/badminton-event.jpg', 4, 2, 'Badminton'),
(6, '../assets/course-event.jpg', 50, 2, 'Course à pied');


-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mail` varchar(255) DEFAULT NULL,
  `mdp` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `pseudo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `mail`, `mdp`, `nom`, `prenom`, `pseudo`) VALUES
(1, 'martin.lenglet@hotmail.fr', 'martin', 'Lenglet', 'Martin', 'TinMar du 62'),
(2, 'pierre.falck@hotmail.fr', 'pierre', 'Falck', 'Pierre', 'PedroElFalko'),
(3, 'hugo.carlevaris@hotmail.fr', 'hugo', 'Carlevaris', 'Hugo', 'Hugoleboss'),
(4, 'sara.croche@hotmail.fr', 'sara', 'Croche', 'Sara', 'Saracroche'),
(5, 'joel.banka@hotmail.fr', 'joel', 'Banka', 'Joel', 'Jojo92'),
(6, 'blanche.neige@hotmail.fr', 'blanche', 'Neige', 'Blanche', 'Blancheneige'),
(7, 'jeremy.star@hotmail.fr', 'jeremy', 'Star', 'Jeremy', 'Jeremstar'),
(8, 'david.douillet@hotmail.fr', 'david', 'Douillet', 'David', 'DavidD'),
(9, 'wilfrid@hotmail.fr', 'wilfrid', 'Dupond', 'Wilfrid', 'Wil'),
(10, 'barack.obama@hotmail.fr', 'barack', 'Obama', 'Barack', 'Baracko');






COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
