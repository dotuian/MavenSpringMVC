-- --------------------------------------------------------
-- ホスト:                          127.0.0.1
-- サーバのバージョン:                    5.6.25 - MySQL Community Server (GPL)
-- サーバー OS:                      Win32
-- HeidiSQL バージョン:               9.2.0.4981
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for springmvc
DROP DATABASE IF EXISTS `springmvc`;
CREATE DATABASE IF NOT EXISTS `springmvc` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `springmvc`;


-- Dumping structure for テーブル springmvc.t_users
DROP TABLE IF EXISTS `t_users`;
CREATE TABLE IF NOT EXISTS `t_users` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` char(50) COLLATE utf8_bin NOT NULL DEFAULT '0',
  `username` char(50) COLLATE utf8_bin NOT NULL DEFAULT '0',
  `password` char(50) COLLATE utf8_bin NOT NULL DEFAULT '0',
  `birthday` date DEFAULT NULL,
  `sex` char(1) COLLATE utf8_bin DEFAULT '0',
  `status` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0',
  `hobbys` text COLLATE utf8_bin,
  `last_login_time` timestamp NULL DEFAULT NULL,
  `last_password_time` timestamp NULL DEFAULT NULL,
  `version` bigint(20) NOT NULL DEFAULT '0',
  `delete_flag` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0',
  `create_user` bigint(20) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_user` bigint(20) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- エクスポートするデータが選択されていません
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
