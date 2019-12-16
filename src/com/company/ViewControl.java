package com.company;
import java.sql.SQLException;
import java.util.Scanner;

public class ViewControl {
    private Person personLogin;
    private View view = new View();
    private Scanner sc = new Scanner(System.in);

    public void loginUser() throws SQLException {
        System.out.println("SELAMAT DATANG DI PERPUSTAKAAN ILKOM" +
                "SILAHKAN LOGIN TERLEBIH DAHULU");
        while (true){
            System.out.print("Username : ");
            String username = sc.nextLine();
            System.out.print("Password : ");
            String password = sc.nextLine();
            try {
                personLogin = Person.loginUser(username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(personLogin == null){
                System.out.println("Akun Tidak Ditemukan Login Ulang");
            }else{
                switch (personLogin.getRole()){
                    case 1:
                        viewAdministrator();
                        break;
                    case 2:
                        viewPustakawan();
                        break;
                    case 3:
                        viewAnggota();
                        break;
                }
            }
        }
    }

    private void viewAdministrator() throws SQLException {
        loop : while (true){
            System.out.println("Selamat datang Di Wilayah Administrator "+personLogin.getNama());
            System.out.println("Lakukan Apa yang ingin anda lakukan\n" +
                    "1. Menambah Pustakawan Baru\n" +
                    "2. Tampilkan Data Pustakawan\n" +
                    "3. Tampilkan Data Buku\n" +
                    "4. Tampilkan Data Transaksi Buku\n" +
                    "5. Logout\n");
            System.out.print("Pilihan anda dalam angka :");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    view.viewTambahPustakawan();
                    break;
                case 2:
                    view.viewDataPustakawan();
                    break;
                case 3:
                    view.viewDataBuku();
                    break;
                case 4:
                    view.viewDataTransaksiAnggota();
                    break;
                case 5:
                    System.out.println("Anda Akan Kembali kehalaman Login. Seeyou");
                    break loop;
                default:
                    System.out.println("Nomer yang anda masukan Salah, Pilih Nomer 1-5");
            }
        }
    }

    private void viewPustakawan() throws SQLException {
        System.out.println("Selamat Datang " + personLogin.getNama());
        loop:
        while (true) {
            System.out.println("Silahkan Pilih Menu");
            System.out.println("\n" +
                    "1. Lihat semua Data Buku\n" +
                    "2. Lihat Semua Record Peminjaman\n" +
                    "3. Lihat Record Peminjaman Anggota\n" +
                    "4. Tambah Buku\n" +
                    "5. Edit Buku\n" +
                    "6. Hapus Buku\n" +
                    "7. Peminjaman\n" +
                    "8. Pegembalian\n" +
                    "9. Tambah Anggota\n"+
                    "10. Exit");
            System.out.print("Pilihan :");
            int pilihan = sc.nextInt();
            switch (pilihan){
                case 1:
                    view.viewDataBuku();
                    break ;
                case 2:
                    view.viewDataTransaksi();
                    break;
                case 3:
                    view.viewDataTransaksiAnggota();
                    break ;
                case 4:
                    view.viewTambahBuku();
                    break ;
                case 5:
                    view.viewEditBuku();
                    break ;
                case 6:
                    view.viewHapusBuku();
                    break ;
                case 7:
                    view.viewPeminjaman();
                    break ;
                case 8:
                    view.viewPengembalian();
                    break ;
                case 9:
                    view.viewTambahAnggota();
                    break;
                case 10:
                    break loop;
            }
        }
    }

    private void viewAnggota(){
        System.out.println("Selamat Datang "+personLogin.getNama());
        loop : while (true){
            System.out.println("Silahkan Pilih Menu");
            System.out.println("1. Lihat semua Data Buku\n" +
                    "2. Cari Buku Berdasarkan Judul\n" +
                    "3. Cari Berdasarkan Penerbit\n" +
                    "4. Cari Berdasarkan Penulis\n" +
                    "5. Data Diri\n"+
                    "5. Logout");
            System.out.print("Pilihan :");
            int pilihan = sc.nextInt();
            switch (pilihan){
                case 1 :
                    view.viewDataBuku();
                    break ;
                case 2:
                    view.viewSearchBerdasarkan(1);
                    break ;
                case 3:
                    view.viewSearchBerdasarkan(2);
                    break ;
                case 4:
                    view.viewSearchBerdasarkan(3);
                    break ;
                case 5:
                    personLogin.printData();
                    break ;
                case 6:
                    break loop;
                default:
                    System.out.println("Angka yang anda pilih tidak sesuai, silahkan pilih antara 1-5 sesuai Menu");
            }
        }

    }
}
