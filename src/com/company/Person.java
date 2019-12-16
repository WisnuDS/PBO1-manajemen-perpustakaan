package com.company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
    private static final String TABEL = "person";
    private int IdPerson;
    private String nama;
    private String username;
    private String password;
    private int role;

    public Person() {
    }

    public Person(int idPerson, String nama, String username, String password, int role) {
        IdPerson = idPerson;
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public static String getTABEL() {
        return TABEL;
    }

    public int getIdPerson() {
        return IdPerson;
    }

    public void setIdPerson(int idPerson) {
        IdPerson = idPerson;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    private final static Pattern validInput = Pattern.compile(".*[a-zA-Z0-9]", Pattern.CASE_INSENSITIVE);

    public static Person loginUser(String username, String password) throws SQLException {
        Matcher matcherUsername = validInput.matcher(username);
        Matcher matcherPassword = validInput.matcher(password);
        if (!(matcherUsername.matches() && matcherPassword.matches())){
            System.out.println(matcherPassword.matches()+" "+matcherUsername.matches());
            return null;
        }
        ResultSet resultSet = DBHelper.selectAll(TABEL,String.format("username = '%s' and password = '%s'",username,password));
        if (!resultSet.next()){
            return null;
        }else{
            Person person = new Person();
            person.setIdPerson(resultSet.getInt("id"));
            person.setNama(resultSet.getString("nama"));
            person.setUsername(resultSet.getString("username"));
            person.setPassword(resultSet.getString("password"));
            person.setRole(resultSet.getInt("role"));
            return person;
        }
    }

    public void printData(){
        System.out.println("Nama : "+getNama());
        System.out.println("Username : "+getUsername());
        System.out.println("Password : "+getPassword());
    }
}
