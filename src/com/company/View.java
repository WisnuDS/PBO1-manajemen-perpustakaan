package com.company;

import com.company.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class View {
    public void viewTambahAnggota() {
        Scanner scn = new Scanner(System.in);
        System.out.println("Masukan Data Anggota Baru");
        System.out.println("Nama        : ");
        String nama = scn.nextLine();
        System.out.println("Username    : ");
        String username = scn.nextLine();
        System.out.println("Password    : ");
        String password = scn.nextLine();

        Anggota anggota = new Anggota(0, nama, username, password, 3);
        anggota.create();
    }

    //View untuk Buku

    public void viewDataBuku() {
        Buku buku = new Buku();
        ArrayList<Buku> allBuku = new ArrayList<>(buku.read());
        System.out.println("NO | Judul Buku | Pengarang | Penerbit | Jumlah");
        int i = 1;
        for (Buku someBuku :
                allBuku) {
            System.out.println(String.format("%s | %s | %s | %s | %s"
                    , i, someBuku.getJudulBuku(), someBuku.getPengarang(), someBuku.getPenerbit(), someBuku.getKetersediaan()));
            i++;
        }
    }

    public void viewTambahBuku() {
        Scanner scn = new Scanner(System.in);
        try {
            System.out.println("Inputkan Data Buku");
            System.out.println("Judul Buku :");
            String judul = scn.nextLine();
            System.out.println("Pengarang : ");
            String pengarang = scn.nextLine();
            System.out.println("Penerbit : ");
            String penerbit = scn.nextLine();
            System.out.println("Jumlah Buku : ");
            int jumlah = scn.nextInt();
            Buku buku = new Buku(0, judul, pengarang, penerbit, jumlah);
            buku.create();
        } catch (Exception e) {
            System.out.println("Inputan Anda Tidak Valid");
        }
    }

    public void viewHapusBuku() {
        Scanner scn = new Scanner(System.in);
        int id;
        System.out.println("Masukan ID buku yang ingin dihapus!");
        try {
            System.out.print("ID Buku : ");
            id = scn.nextInt();
            Buku buku = new Buku();
            buku.setIdBuku(id);
            buku.delete();
        } catch (Exception e) {
            System.out.println("Inputan Anda Tidak Valid");
        }

    }

    public void viewEditBuku() {
        Scanner sc = new Scanner(System.in);
        Scanner scn = new Scanner(System.in);
        System.out.print("Masukan Id Buku yang Ingin di Edit : ");
        int id = sc.nextInt();
        System.out.print("Judul Buku : ");
        String judul = scn.nextLine();
        System.out.print("Pengarang : ");
        String pengarang = scn.nextLine();
        System.out.print("Penerbit : ");
        String penerbit = scn.nextLine();
        System.out.print("Jumlah : ");
        int jumlah = sc.nextInt();
        Buku buku = new Buku(id, judul, pengarang, penerbit, jumlah);
        buku.update();
    }

    public void viewSearchBerdasarkan(int key) {
        Scanner scn = new Scanner(System.in);
        Buku buku = new Buku();
        ArrayList<Buku> hasil = new ArrayList<>();
        if (key == 1) {
            System.out.print("Masukan Judul Buku : ");
            String keyword = scn.nextLine();
            try {
                hasil.addAll(buku.read("judul_buku", keyword));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (key == 2) {
            System.out.print("Masukan Judul Penerbit : ");
            String keyword = scn.nextLine();
            try {
                hasil.addAll(buku.read("penerbit", keyword));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.print("Masukan Judul Pengarang : ");
            String keyword = scn.nextLine();
            try {
                hasil.addAll(buku.read("pengarang", keyword));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (hasil.isEmpty()) {
            System.out.println("Buku Masih Belum Tersedia");
        } else {
            int i = 1;
            for (Buku someBuku :
                    hasil) {
                System.out.println(String.format("%s | %s | %s | %s | %s"
                        , i, someBuku.getJudulBuku(), someBuku.getPengarang(), someBuku.getPenerbit(), someBuku.getKetersediaan()));
                i++;
            }
        }

    }

    //View Pustakawan

    public void viewDataPustakawan() throws SQLException {
        Pustakawan pustakawan = new Pustakawan();
        ArrayList<Pustakawan> allpustakawan = new ArrayList<>(pustakawan.read());
        int i = 1;
        System.out.println("No | Nama | Username | Password");
        for (Pustakawan pustakawan1 :
                allpustakawan) {
            System.out.println(String.format("%s | %s | %s | %s " +
                    "", i, pustakawan1.getNama(), pustakawan1.getUsername(), pustakawan1.getPassword()));
            i++;
        }
    }

    public void viewTambahPustakawan() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Inputkan data Pustakawan baru");
        System.out.print("Nama: ");
        String nama = sc.nextLine();
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        Pustakawan pustakawan = new Pustakawan(0, nama, username, password, 2);
        pustakawan.create();
    }

    //ViewTransaksiBuku

    public void viewDataTransaksiAnggota() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Inputkan Nama Anggota :");
        String nama = sc.nextLine();
        TransaksiBuku transaksiBuku = new TransaksiBuku();
        ArrayList<TransaksiBuku> allTransaksi = new ArrayList<>();
        try {
            allTransaksi = transaksiBuku.read(nama);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("NO | Judul Buku | Pengarang | Penerbit | Jumlah");
        int i = 1;
        for (TransaksiBuku transaksi :
                allTransaksi) {
            System.out.println(String.format("%s | %s | %s | %s | %s | %s"
                    , i, transaksi.getPeminjam(), transaksi.getBuku(), transaksi.getTanggal_pinjam(), transaksi.getTanggal_kembali(), transaksi.getSts_pengembalian()));
            i++;
        }
    }

    public void viewPeminjaman() {
        Scanner sc = new Scanner(System.in);
        Scanner scn = new Scanner(System.in);
        ArrayList<Buku> bukus = new ArrayList<>();
        Anggota anggota = new Anggota();
        System.out.print("Masukan Jumlah Buku yang dipinjam(Maksimal 3) : ");
        int jumlah = sc.nextInt();
        if (jumlah > 3) {
            System.out.println("Maksimal buku yang dipinjam adalah 3");
        } else {
            System.out.printf("Masukan Id Anggota : ");
            int idAnggota = sc.nextInt();
            anggota.setIdPerson(idAnggota);
            try {
                if (anggota.cekAnggota()) {
                    Buku buku;
                    TransaksiBuku transaksiBuku;
                    Person person;
                    for (int i = 0; i < jumlah; i++) {
                        buku = new Buku();
                        transaksiBuku = new TransaksiBuku();
                        System.out.print("Masukan id Buku yang dipinjam : ");
                        int idBuku = sc.nextInt();
                        buku.setIdBuku(idBuku);
                        try {
                            buku.getBukuById(idBuku);
                            bukus.add(buku);
                            transaksiBuku.setBuku(String.valueOf(idBuku));
                            transaksiBuku.setPeminjam(String.valueOf(idAnggota));
                            transaksiBuku.create();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        System.out.print("Nama : " + anggota.read(idAnggota));
                        System.out.printf("Telah Meminjam Buku sebanyak " + jumlah + " Yaitu dengan rincian berikut :");
                        int i = 1;
                        for (Buku buk :
                                bukus) {
                            System.out.println(String.format("%s. %s | %s | %s", i, buk.getJudulBuku(), buk.getPengarang(), buk.getPenerbit()));
                            i++;
                            buk.setKetersediaan(buk.getKetersediaan() - 1);
                            buk.update();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Id tidak ditemukan atau Masih Memiliki Tanggungan Buku yang belum dikembalikan");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void viewPengembalian() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int jumlahPinjaman = 0;
        System.out.print("Masukan Id Anggota : ");
        int idAnggota = sc.nextInt();
        String colum[] = {"COUNT(sts_pengembalian) AS total"};
        String constrain = "peminjam = " + idAnggota + " and sts_pengembalian = 'Belum Kembali' GROUP BY peminjam";
        ResultSet resultSet = DBHelper.selectColumn("transaksi_buku", colum, constrain);
        while (resultSet.next()) {
            jumlahPinjaman = resultSet.getInt("total");
        }
        int i=0;
        while (i<jumlahPinjaman){
            System.out.print("Masukan ID Buku yang dipinjam :");
            int idBuku = sc.nextInt();
            Buku buku = new Buku();
            boolean cek = buku.getBukuById(idBuku);
            if (cek) {
                buku.setKetersediaan(buku.getKetersediaan()+1);
                buku.update();
                TransaksiBuku transaksiBuku = new TransaksiBuku();
                System.out.println(idAnggota);
                transaksiBuku.setPeminjam(String.valueOf(idAnggota));
                transaksiBuku.setBuku(String.valueOf(idBuku));
                transaksiBuku.update();
                i++;
            }
        }
    }

    public void viewDataTransaksi() throws SQLException {
        TransaksiBuku transaksiBuku = new TransaksiBuku();
        ArrayList<TransaksiBuku> allTransaksi = transaksiBuku.read();
        System.out.println("NO | Judul Buku | Pengarang | Penerbit | Jumlah");
        int i = 1;
        for (TransaksiBuku transaksi :
                allTransaksi) {
            System.out.println(String.format("%s | %s | %s | %s | %s | %s"
                    ,i,transaksi.getPeminjam(),transaksi.getBuku(),transaksi.getTanggal_pinjam(),transaksi.getTanggal_kembali(),transaksi.getSts_pengembalian()));
            i++;
        }
    }
}
