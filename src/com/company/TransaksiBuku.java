package com.company;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class TransaksiBuku implements Crud {
    private static final String TABEL = "transaksi_buku";
    private int id;
    private String peminjam;
    private String buku;
    private java.sql.Date tanggal_pinjam;
    private java.sql.Date tanggal_kembali;
    private String sts_pengembalian;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPeminjam() {
        return peminjam;
    }

    public void setPeminjam(String peminjam) {
        this.peminjam = peminjam;
    }

    public String getBuku() {
        return buku;
    }

    public void setBuku(String buku) {
        this.buku = buku;
    }

    public Date getTanggal_pinjam() {
        return tanggal_pinjam;
    }

    public void setTanggal_pinjam(Date tanggal_pinjam) {
        this.tanggal_pinjam = tanggal_pinjam;
    }

    public Date getTanggal_kembali() {
        return tanggal_kembali;
    }

    public void setTanggal_kembali(Date tanggal_kembali) {
        this.tanggal_kembali = tanggal_kembali;
    }

    public String getSts_pengembalian() {
        return sts_pengembalian;
    }

    public void setSts_pengembalian(String sts_pengembalian) {
        this.sts_pengembalian = sts_pengembalian;
    }

    public TransaksiBuku() {
    }

    public TransaksiBuku(int id, String peminjam, String buku, Date tanggal_pinjam, Date tanggal_kembali, String sts_pengembalian) {
        this.id = id;
        this.peminjam = peminjam;
        this.buku = buku;
        this.tanggal_pinjam = tanggal_pinjam;
        this.tanggal_kembali = tanggal_kembali;
        this.sts_pengembalian = sts_pengembalian;
    }

    @Override
    public void create() {
        LocalDate tanggalPinjam;
        LocalDate tanggalKembali;
        String status = "'Belum Kembali'";
        tanggalPinjam = java.time.LocalDate.now();
        tanggalKembali = tanggalPinjam.plusDays(7);
        HashMap<String,String > hashMap = new HashMap<>();
        hashMap.put("buku", String.valueOf(buku));
        hashMap.put("peminjam",String.valueOf(peminjam));
        hashMap.put("tangga_pinjam",String.format("'%s'",tanggalPinjam));
        hashMap.put("tangga_kembali",String.format("'%s'",tanggalKembali));
        hashMap.put("sts_pengembalian",status);

        DBHelper.insert(TABEL,hashMap);
    }

    @Override
    public void update() {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("sts_pengembalian","'Sudah Kembali'");
        String params = String.format("peminjam = %s and buku = %s",peminjam,buku);
        DBHelper.update(TABEL,hashMap,params);
    }

    @Override
    public void delete() {

    }

    @Override
    public ArrayList<TransaksiBuku> read() throws SQLException {
        String[] col = {"t.id","p.nama","b.judul_buku","t.tangga_pinjam","t.tangga_kembali","t.sts_pengembalian"};
        String join = TABEL+" t join buku b on t.buku=b.id join person p on t.peminjam = p.id";
        ResultSet resultSet = DBHelper.selectColumn(join,col);
        return getTransaksiBukus(resultSet);
    }

    public ArrayList<TransaksiBuku> read(String nama) throws SQLException {
        String[] col = {"t.id","p.nama","b.judul_buku","t.tangga_pinjam","t.tangga_kembali","t.sts_pengembalian"};
        String join = TABEL+" t join buku b on t.buku=b.id join person p on t.peminjam = p.id";
        String requirment = "nama='"+nama+"'";
        ResultSet resultSet = DBHelper.selectColumn(join,col,requirment);
        return getTransaksiBukus(resultSet);
    }

    private ArrayList<TransaksiBuku> getTransaksiBukus(ResultSet resultSet) throws SQLException {
        TransaksiBuku transaksiBuku;
        ArrayList<TransaksiBuku> allTransaksi = new ArrayList<>();
        while (resultSet.next()){
            transaksiBuku = new TransaksiBuku();
            transaksiBuku.setId(resultSet.getInt("id"));
            transaksiBuku.setPeminjam(resultSet.getString("nama"));
            transaksiBuku.setBuku(resultSet.getString("judul_buku"));
            transaksiBuku.setTanggal_pinjam(resultSet.getDate("tangga_pinjam"));
            transaksiBuku.setTanggal_kembali(resultSet.getDate("tangga_kembali"));
            transaksiBuku.setSts_pengembalian(resultSet.getString("sts_pengembalian"));
            allTransaksi.add(transaksiBuku);
        }
        return allTransaksi;
    }
}
