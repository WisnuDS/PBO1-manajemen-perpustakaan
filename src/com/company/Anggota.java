package com.company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Anggota extends Person implements Crud {
    public Anggota() {
    }

    public Anggota(int idPerson, String nama, String username, String password, int role) {
        super(idPerson, nama, username, password, role);
    }

    @Override
    public void create() {
        HashMap<String, String> map = new HashMap<>();
        map.put("nama",String.format("'%s'",getNama()));
        map.put("username", String.format("'%s'",getUsername()));
        map.put("password",String.format("'%s'",getPassword()));
        map.put("role","3");

        boolean isSuccess = DBHelper.insert(getTABEL(),map);

        if (isSuccess){
            System.out.println("Data Pustakawan Baru Telah Ditambahkan");
        }else {
            System.out.println("Data Pustakawan Baru gagal ditambah");
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public ArrayList read() {
        ResultSet resultSet = DBHelper.selectAll(getTABEL(),"role = 3");
        ArrayList<Anggota> allAdministrator = new ArrayList<>();
        while (true) {
            try {
                if (resultSet.next()) {
                    Anggota pustakawan = new Anggota();
                    pustakawan.setNama(resultSet.getString("nama"));
                    pustakawan.setIdPerson(resultSet.getInt("id"));
                    pustakawan.setUsername(resultSet.getString("username"));
                    pustakawan.setPassword(resultSet.getString("password"));
                    pustakawan.setRole(resultSet.getInt("role"));
                    allAdministrator.add(pustakawan);
                } else {
                    break;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return allAdministrator;
    }

    public String read(int idPerson) throws SQLException {
        String nama = "";
        String query = "id = "+idPerson;
        ResultSet resultSet = DBHelper.selectAll(getTABEL(),query);
        while (resultSet.next()) nama = resultSet.getString("nama");
        return nama;
    }

    public boolean cekAnggota() throws SQLException {
        String[] column = new String[1];
        String status;
        column[0] = "sts_pengembalian";
        String clause = String.format("id = %s ORDER BY tangga_pinjam DESC LIMIT 1 ",getIdPerson());
        ResultSet resultSet = DBHelper.selectColumn("transaksi_buku",column,clause);
        if (resultSet.next()){
            status = resultSet.getString("sts_pengembalian");
            return !status.equals("Belum Kembali");
        }else {
            return true;
        }
    }
}
