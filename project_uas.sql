-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 27, 2019 at 08:10 AM
-- Server version: 5.7.24
-- PHP Version: 7.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `project_uas`
--

-- --------------------------------------------------------

--
-- Table structure for table `data_custumer`
--

CREATE TABLE `data_custumer` (
  `id_custumer` int(10) UNSIGNED NOT NULL,
  `nama_custumer` varchar(255) DEFAULT NULL,
  `alamat` text,
  `no_telepon` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `data_custumer`
--

INSERT INTO `data_custumer` (`id_custumer`, `nama_custumer`, `alamat`, `no_telepon`) VALUES
(10, 'Test 1', 'Test 1', '1234'),
(11, 'Test 2', 'Test 2', '123456'),
(12, 'Test 3', 'Test 3', '12345'),
(13, 'Test 4', 'Test 4', '1234567'),
(14, 'Test 5', 'Test 5', '123456789'),
(15, 'ASD', 'Test', '123312');

-- --------------------------------------------------------

--
-- Table structure for table `data_perusahaan`
--

CREATE TABLE `data_perusahaan` (
  `id_perusahaan` int(10) UNSIGNED NOT NULL,
  `nama_perusahaan` varchar(255) DEFAULT NULL,
  `alamat` text,
  `no_telepon` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `data_perusahaan`
--

INSERT INTO `data_perusahaan` (`id_perusahaan`, `nama_perusahaan`, `alamat`, `no_telepon`) VALUES
(1, 'Test 1', 'Test', '1234567890'),
(2, 'Perusahaan B', 'Jakarta', '087786077947');

-- --------------------------------------------------------

--
-- Table structure for table `data_sampah`
--

CREATE TABLE `data_sampah` (
  `id_sampah` int(10) UNSIGNED NOT NULL,
  `nama_sampah` varchar(255) DEFAULT NULL,
  `harga_satuan` float UNSIGNED DEFAULT NULL,
  `harga_jual` float UNSIGNED NOT NULL DEFAULT '0',
  `sampah_terkumpul` int(10) UNSIGNED NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `data_sampah`
--

INSERT INTO `data_sampah` (`id_sampah`, `nama_sampah`, `harga_satuan`, `harga_jual`, `sampah_terkumpul`) VALUES
(1, 'Buku Sekolah', 5000, 5500, 2),
(2, 'Botol Plastik', 2500, 2600, 0),
(3, 'E-waste', 50000, 52500, 0),
(4, 'Kaleng', 2500, 3000, 0),
(7, 'Sampah 1', 5000, 5500, 15);

-- --------------------------------------------------------

--
-- Table structure for table `data_transaksi_keluar`
--

CREATE TABLE `data_transaksi_keluar` (
  `id_transaksi` int(10) UNSIGNED NOT NULL,
  `tanggal` timestamp NULL DEFAULT NULL,
  `no_perusahaan` int(10) UNSIGNED DEFAULT NULL,
  `berat_sampah` tinyint(3) UNSIGNED NOT NULL DEFAULT '0',
  `harga_sampah` float UNSIGNED NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `data_transaksi_keluar`
--

INSERT INTO `data_transaksi_keluar` (`id_transaksi`, `tanggal`, `no_perusahaan`, `berat_sampah`, `harga_sampah`) VALUES
(1, '2019-12-21 15:05:24', 1, 6, 23000),
(2, '2019-12-23 04:29:06', 1, 58, 1402700),
(3, '2019-12-23 07:16:37', 2, 8, 44000);

-- --------------------------------------------------------

--
-- Table structure for table `data_transaksi_keluar_relasi`
--

CREATE TABLE `data_transaksi_keluar_relasi` (
  `id_transaksi_relasi` int(10) UNSIGNED NOT NULL,
  `id_transaksi` int(10) UNSIGNED DEFAULT NULL,
  `id_sampah` int(10) UNSIGNED DEFAULT NULL,
  `berat_sampah` tinyint(3) UNSIGNED DEFAULT NULL,
  `jumlah_pemasukan` float UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `data_transaksi_keluar_relasi`
--

INSERT INTO `data_transaksi_keluar_relasi` (`id_transaksi_relasi`, `id_transaksi`, `id_sampah`, `berat_sampah`, `jumlah_pemasukan`) VALUES
(1, 1, 7, 2, 11000),
(2, 1, 4, 4, 12000),
(3, 2, 2, 22, 57200),
(4, 2, 4, 11, 33000),
(5, 2, 3, 25, 1312500),
(6, 3, 1, 4, 22000),
(7, 3, 7, 4, 22000);

-- --------------------------------------------------------

--
-- Table structure for table `data_transaksi_masuk`
--

CREATE TABLE `data_transaksi_masuk` (
  `id_transaksi` int(10) UNSIGNED NOT NULL,
  `tanggal` timestamp NULL DEFAULT NULL,
  `no_custumer` int(10) UNSIGNED DEFAULT NULL,
  `berat_sampah` tinyint(3) UNSIGNED NOT NULL DEFAULT '0',
  `harga_sampah` float UNSIGNED NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `data_transaksi_masuk`
--

INSERT INTO `data_transaksi_masuk` (`id_transaksi`, `tanggal`, `no_custumer`, `berat_sampah`, `harga_sampah`) VALUES
(1, '2019-12-20 09:58:57', 15, 2, 5000),
(2, '2019-12-20 10:48:33', 13, 6, 25000),
(3, '2019-12-20 10:53:06', 10, 46, 115000),
(4, '2019-12-20 10:54:19', 12, 7, 27500),
(5, '2019-12-20 11:40:26', 12, 11, 132500),
(6, '2019-12-20 11:42:48', 13, 10, 25000),
(7, '2019-12-20 11:45:25', 11, 22, 110000),
(8, '2019-12-20 11:48:32', 14, 10, 50000),
(9, '2019-12-23 01:06:35', 13, 5, 250000),
(10, '2019-12-23 04:28:43', 11, 19, 492500);

-- --------------------------------------------------------

--
-- Table structure for table `data_transaksi_masuk_relasi`
--

CREATE TABLE `data_transaksi_masuk_relasi` (
  `id_transaksi_relasi` int(10) UNSIGNED NOT NULL,
  `id_transaksi` int(10) UNSIGNED DEFAULT NULL,
  `id_sampah` int(10) UNSIGNED DEFAULT NULL,
  `berat_sampah` tinyint(3) UNSIGNED DEFAULT NULL,
  `jumlah_pengeluaran` float UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `data_transaksi_masuk_relasi`
--

INSERT INTO `data_transaksi_masuk_relasi` (`id_transaksi_relasi`, `id_transaksi`, `id_sampah`, `berat_sampah`, `jumlah_pengeluaran`) VALUES
(1, 2, 4, 2, 5000),
(2, 2, 1, 4, 20000),
(3, 3, 4, 2, 5000),
(4, 3, 2, 44, 110000),
(5, 4, 4, 3, 7500),
(6, 4, 1, 4, 20000),
(7, 5, 3, 2, 100000),
(8, 5, 1, 4, 20000),
(9, 5, 2, 5, 12500),
(10, 6, 4, 10, 25000),
(11, 7, 1, 22, 110000),
(12, 8, 1, 10, 50000),
(13, 9, 3, 5, 250000),
(14, 10, 3, 5, 250000),
(15, 10, 3, 4, 200000),
(16, 10, 4, 3, 7500),
(17, 10, 7, 7, 35000);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` int(10) UNSIGNED NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `nama` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `username`, `password`, `nama`) VALUES
(1, 'cham', '$2a$12$pePSUCeHkwhGDvd3wuW/w.7KsDaHs6Hr2tfDuWVxO9aO0Hd.S1.Ra', 'Chamelia Try Winda');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `data_custumer`
--
ALTER TABLE `data_custumer`
  ADD PRIMARY KEY (`id_custumer`);

--
-- Indexes for table `data_perusahaan`
--
ALTER TABLE `data_perusahaan`
  ADD PRIMARY KEY (`id_perusahaan`);

--
-- Indexes for table `data_sampah`
--
ALTER TABLE `data_sampah`
  ADD PRIMARY KEY (`id_sampah`);

--
-- Indexes for table `data_transaksi_keluar`
--
ALTER TABLE `data_transaksi_keluar`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `fk_no_perusahaan_keluar` (`no_perusahaan`);

--
-- Indexes for table `data_transaksi_keluar_relasi`
--
ALTER TABLE `data_transaksi_keluar_relasi`
  ADD PRIMARY KEY (`id_transaksi_relasi`),
  ADD KEY `fk_id_transaksi` (`id_transaksi`),
  ADD KEY `fk_id_sampah` (`id_sampah`);

--
-- Indexes for table `data_transaksi_masuk`
--
ALTER TABLE `data_transaksi_masuk`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `fk_no_custumer_masuk` (`no_custumer`);

--
-- Indexes for table `data_transaksi_masuk_relasi`
--
ALTER TABLE `data_transaksi_masuk_relasi`
  ADD PRIMARY KEY (`id_transaksi_relasi`),
  ADD KEY `fk_id_transaksi_masuk` (`id_transaksi`),
  ADD KEY `fk_id_sampah_masuk` (`id_sampah`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `data_custumer`
--
ALTER TABLE `data_custumer`
  MODIFY `id_custumer` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `data_perusahaan`
--
ALTER TABLE `data_perusahaan`
  MODIFY `id_perusahaan` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `data_sampah`
--
ALTER TABLE `data_sampah`
  MODIFY `id_sampah` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `data_transaksi_keluar`
--
ALTER TABLE `data_transaksi_keluar`
  MODIFY `id_transaksi` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `data_transaksi_keluar_relasi`
--
ALTER TABLE `data_transaksi_keluar_relasi`
  MODIFY `id_transaksi_relasi` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `data_transaksi_masuk`
--
ALTER TABLE `data_transaksi_masuk`
  MODIFY `id_transaksi` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `data_transaksi_masuk_relasi`
--
ALTER TABLE `data_transaksi_masuk_relasi`
  MODIFY `id_transaksi_relasi` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `data_transaksi_keluar`
--
ALTER TABLE `data_transaksi_keluar`
  ADD CONSTRAINT `fk_no_perusahaan_keluar` FOREIGN KEY (`no_perusahaan`) REFERENCES `data_perusahaan` (`id_perusahaan`) ON DELETE SET NULL ON UPDATE SET NULL;

--
-- Constraints for table `data_transaksi_keluar_relasi`
--
ALTER TABLE `data_transaksi_keluar_relasi`
  ADD CONSTRAINT `fk_id_sampah` FOREIGN KEY (`id_sampah`) REFERENCES `data_sampah` (`id_sampah`) ON DELETE SET NULL ON UPDATE SET NULL,
  ADD CONSTRAINT `fk_id_transaksi` FOREIGN KEY (`id_transaksi`) REFERENCES `data_transaksi_keluar` (`id_transaksi`) ON DELETE SET NULL ON UPDATE SET NULL;

--
-- Constraints for table `data_transaksi_masuk`
--
ALTER TABLE `data_transaksi_masuk`
  ADD CONSTRAINT `fk_no_custumer_masuk` FOREIGN KEY (`no_custumer`) REFERENCES `data_custumer` (`id_custumer`) ON DELETE SET NULL ON UPDATE SET NULL;

--
-- Constraints for table `data_transaksi_masuk_relasi`
--
ALTER TABLE `data_transaksi_masuk_relasi`
  ADD CONSTRAINT `fk_id_sampah_masuk` FOREIGN KEY (`id_sampah`) REFERENCES `data_sampah` (`id_sampah`) ON DELETE SET NULL ON UPDATE SET NULL,
  ADD CONSTRAINT `fk_id_transaksi_masuk` FOREIGN KEY (`id_transaksi`) REFERENCES `data_transaksi_masuk` (`id_transaksi`) ON DELETE SET NULL ON UPDATE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
