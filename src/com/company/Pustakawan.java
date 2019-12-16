package com.company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Pustakawan extends Person implements Crud {
    public Pustakawan() {
    }

    public Pustakawan(int idPerson, String nama, String username, String password, int role) {
        super(idPerson, nama, username, password, role);
    }

    @Override
    public void create() {
        HashMap<String, String> map = new HashMap<>();
        map.put("nama",String.format("'%s'",getNama()));
        map.put("username", String.format("'%s'",getUsername()));
        map.put("password",String.format("'%s'",getPassword()));
        map.put("role","2");

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
    public ArrayList read(){
        ResultSet resultSet = DBHelper.selectAll(getTABEL(),"role = 2");
        ArrayList<Pustakawan> allpustakawan = new ArrayList<>();
        while (true) {
            try {
                if (resultSet.next()) {
                    Pustakawan pustakawan = new Pustakawan();
                    pustakawan.setNama(resultSet.getString("nama"));
                    pustakawan.setIdPerson(resultSet.getInt("id"));
                    pustakawan.setUsername(resultSet.getString("username"));
                    pustakawan.setPassword(resultSet.getString("password"));
                    pustakawan.setRole(resultSet.getInt("role"));
                    allpustakawan.add(pustakawan);
                } else {
                    break;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return allpustakawan;
    }
}
