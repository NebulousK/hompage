-- phpMyAdmin SQL Dump
-- version 4.0.10
-- http://www.phpmyadmin.net
--
-- 호스트: localhost
-- 처리한 시간: 14-06-29 21:07
-- 서버 버전: 5.1.41-community
-- PHP 버전: 5.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 데이터베이스: `sns`
--

-- --------------------------------------------------------

--
-- 테이블 구조 `calendar`
--

CREATE TABLE IF NOT EXISTS `calendar` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `couple` int(11) NOT NULL,
  `id` varchar(50) COLLATE utf8_bin NOT NULL,
  `title` varchar(50) COLLATE utf8_bin NOT NULL,
  `start` varchar(50) COLLATE utf8_bin NOT NULL,
  `end` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`no`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=4 ;

--
-- 테이블의 덤프 데이터 `calendar`
--

INSERT INTO `calendar` (`no`, `couple`, `id`, `title`, `start`, `end`) VALUES
(3, 1, 'ㅂㅂㅂㅂㅂ', 'ㅂㅂㅂㅂㅂ', '2014-06-05', '2014-06-06');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
