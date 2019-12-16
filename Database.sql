CREATE TABLE `buku` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `judul_buku` varchar(255),
  `pengarang` varchar(255),
  `penerbit` varchar(255),
  `ketersediaan` int
);

CREATE TABLE `person` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `username` varchar(255) UNIQUE,
  `nama` varchar(255),
  `role` int,
  `password` varchar(255)
);

CREATE TABLE `transaksi_buku` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `peminjam` int,
  `buku` int,
  `tangga_pinjam` datetime,
  `tangga_kembali` datetime
);

CREATE TABLE `role` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `nama_role` varchar(255)
);

ALTER TABLE `transaksi_buku` ADD CONSTRAINT `meminjam` FOREIGN KEY (`peminjam`) REFERENCES `person` (`id`);

ALTER TABLE `transaksi_buku` ADD CONSTRAINT `dipinjam` FOREIGN KEY (`buku`) REFERENCES `buku` (`id`);

ALTER TABLE `role` ADD CONSTRAINT `peran` FOREIGN KEY (`id`) REFERENCES `person` (`role`);
