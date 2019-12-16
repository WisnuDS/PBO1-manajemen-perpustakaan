package com.company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Buku implements Crud {
    private static final String TABEL = "buku";
    private int idBuku;
    private String judulBuku;
    private String pengarang;
    private String penerbit;
    private int ketersediaan;

    public int getIdBuku() {
        return idBuku;
    }

    public void setIdBuku(int idBuku) {
        this.idBuku = idBuku;
    }

    public String getJudulBuku() {
        return judulBuku;
    }

    public void setJudulBuku(String judulBuku) {
        this.judulBuku = judulBuku;
    }

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public int getKetersediaan() {
        return ketersediaan;
    }

    public void setKetersediaan(int ketersediaan) {
        this.ketersediaan = ketersediaan;
    }

    public Buku() {}

    public Buku(int idBuku, String judulBuku, String pengarang, String penerbit, int ketersediaan) {
        this.idBuku = idBuku;
        this.judulBuku = judulBuku;
        this.pengarang = pengarang;
        this.penerbit = penerbit;
        this.ketersediaan = ketersediaan;
    }

    @Override
    public void create() {
        HashMap<String,String> map = new HashMap<>();
        map.put("judul_buku", String.format("'%s'",judulBuku));
        map.put("pengarang", String.format("'%s'",pengarang));
        map.put("penerbit", String.format("'%s'",penerbit));
        map.put("ketersediaan",Integer.toString(ketersediaan));

        boolean isSuccess = DBHelper.insert("buku",map);

        if(isSuccess){
            System.out.println("Buku Berhasil Ditambahakan");
        }else {
            System.out.println("Buku Gagal DItambahkan");
        }
    }

    @Override
    public void update() {
        HashMap<String,String> map = new HashMap<>();
        map.put("judul_buku", "'"+judulBuku+"'");
        map.put("pengarang", "'"+pengarang+"'");
        map.put("penerbit","'"+penerbit+"'");
        map.put("ketersediaan",Integer.toString(ketersediaan));

        String clause = String.format("id = %s", idBuku);

        boolean isSuccess = DBHelper.update(TABEL,map,clause);

        if(isSuccess){
            System.out.println("Buku Berhasil Diedit");
        }else {
            System.out.println("Buku Gagal Diedit");
        }
    }

    @Override
    public void delete() {
        String clause = "id="+idBuku;
        boolean isSuccess = DBHelper.delete(TABEL,clause);
        if (isSuccess){
            System.out.println("Buku Berhasil Dihapus");
        }else{
            System.out.println("Buku Gagal Dihapus, mungkin id yang anda masukan salah");
        }
    }

    @Override
    public ArrayList<Buku> read() {
        ResultSet resultSet = DBHelper.selectAll(TABEL);
        ArrayList<Buku> allbuku = new ArrayList<>();
        while (true){
            try {
                if (resultSet.next()){
                    Buku buku = new Buku();
                    buku.setIdBuku(resultSet.getInt("id"));
                    buku.setJudulBuku(resultSet.getString("judul_buku"));
                    buku.setPengarang(resultSet.getString("pengarang"));
                    buku.setPenerbit(resultSet.getString("penerbit"));
                    buku.setKetersediaan(resultSet.getInt("ketersediaan"));
                    allbuku.add(buku);
                }else{
                    break;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return allbuku;
    }

    public ArrayList<Buku> read(String type, String search) throws SQLException {
        ArrayList<Buku> allBuku = new ArrayList<>();
        Buku buku;
        String query = type+" like '%"+search+"%'";
        ResultSet resultSet = DBHelper.selectAll(TABEL,query);
        while (resultSet.next()){
            buku = new Buku();
            buku.setJudulBuku(resultSet.getString("judul_buku"));
            buku.setPengarang(resultSet.getString("pengarang"));
            buku.setPenerbit(resultSet.getString("penerbit"));
            buku.setKetersediaan(resultSet.getInt("ketersediaan"));
            buku.setIdBuku(resultSet.getInt("id"));
            allBuku.add(buku);
        }
        return allBuku;
    }

    public boolean getBukuById(int idBuku) throws SQLException {
        String query = "id = "+idBuku;
        ResultSet resultSet = DBHelper.selectAll(TABEL,query);
        if (resultSet.next()){
            setIdBuku(resultSet.getInt("id"));
            setJudulBuku(resultSet.getString("judul_buku"));
            setPengarang(resultSet.getString("pengarang"));
            setPenerbit(resultSet.getString("penerbit"));
            setKetersediaan(resultSet.getInt("ketersediaan"));
            return true;
        }else{
            System.out.println("Buku Tidak ditemukan");
            return false;
        }
    }
}
