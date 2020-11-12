-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 12-Nov-2020 às 19:17
-- Versão do servidor: 10.4.13-MariaDB

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE `movie` (
  `id` int(11) NOT NULL,
  `title` varchar(20) NOT NULL,
  `synopsis` varchar(255) NOT NULL,
  `length` int(11) NOT NULL,
  `image3d` tinyint(1) NOT NULL,
  `dubbed` tinyint(1) NOT NULL,
  `category` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `movie`
  ADD PRIMARY KEY (`id`);
