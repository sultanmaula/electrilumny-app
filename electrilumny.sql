-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 02 Feb 2022 pada 16.01
-- Versi server: 10.4.21-MariaDB
-- Versi PHP: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `electrilumny`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `event`
--

CREATE TABLE `event` (
  `id` int(11) NOT NULL,
  `nama_event` varchar(100) NOT NULL,
  `tgl_pelaksanaan` varchar(100) NOT NULL,
  `deskripsi_acara` text NOT NULL,
  `lokasi_acara` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `event`
--

INSERT INTO `event` (`id`, `nama_event`, `tgl_pelaksanaan`, `deskripsi_acara`, `lokasi_acara`) VALUES
(1, 'as', '20-January-2022', 'ddvfe', 'defegr'),
(2, 'Reun Akbar Angkatan 2018', '28-February-2022', 'Ayo ya datang semua', 'UMM DOME');

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `nama_lengkap` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL,
  `password` varchar(30) NOT NULL,
  `role` varchar(10) NOT NULL,
  `th_angkatan` varchar(4) NOT NULL,
  `jenis_pendidikan` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`id_user`, `nama_lengkap`, `email`, `password`, `role`, `th_angkatan`, `jenis_pendidikan`) VALUES
(16, 'widya', 'widya', 'widya123', 'admin', '2018', 'S1'),
(17, 'Sultan Maula', 'sultan@gmail.com', 'sultan123', 'admin', '2018', 'S1'),
(19, 'nur', 'nur28@gmail.com', 'nur123', 'admin', '2018', 's1'),
(20, 'nur', 'nur28@gmail.com', 'nur123', 'admin', '2018', 's1'),
(21, 'admin', 'admin@gmail.com', 'admin123', 'admin', '2018', 'd3'),
(22, 'admin', 'admin@gmail.com', 'admin123', 'admin', '2018', 'd3'),
(23, 'user', 'user@gmail.com', 'user123', 'user', '2018', 'D3'),
(24, 'user', 'user@gmail.com', 'user123', 'user', '2018', 'D3');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `event`
--
ALTER TABLE `event`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
