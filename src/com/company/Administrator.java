package com.company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Administrator extends Person implements Crud {
    public Administrator() {
    }

    public Administrator(int idPerson, String nama, String username, String password, int role) {
        super(idPerson, nama, username, password, role);
    }

    @Override
    public void create() {
        HashMap<String, String> map = new HashMap<>();
        map.put("nama",String.format("'%s'",getNama()));
        map.put("username", String.format("'%s'",getUsername()));
        map.put("password",String.format("'%s'",getPassword()));
        map.put("role","1");

        Boolean isSuccess = DBHelper.insert(getTABEL(),map);

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
    public ArrayList read() throws SQLException {
        ResultSet resultSet = DBHelper.selectAll(getTABEL(),"role = 1");
        ArrayList<Administrator> allAdministrator = new ArrayList<>();
        while (true) {
            try {
                if (resultSet.next()) {
                    Administrator pustakawan = new Administrator();
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
}
