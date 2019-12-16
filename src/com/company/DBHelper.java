package com.company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class DBHelper {
    public static ResultSet selectAll(String table){
        String sql = String.format("SELECT * FROM %s", table);
        return getResultSet(sql);
    }

    public static ResultSet selectAll(String table, String requirment){
        String sql = String.format("SELECT * FROM %s WHERE %s", table, requirment);
        return getResultSet(sql);
    }

    public static ResultSet selectAll(String table, String joinTable, String foreignKey){
        String sql = String.format("SELECT * FROM %s JOIN %s ON %s.%s = %s.id", table, joinTable, table, foreignKey, joinTable);
        return getResultSet(sql);
    }

    public static ResultSet selectAll(String table, String requirment, String joinTable, String foreignKey){
        String sql = String.format("SELECT * FROM %s JOIN %s ON %s.%s = %s.id WHERE %s", table, joinTable, table, foreignKey, joinTable, requirment);
        return getResultSet(sql);
    }

    public static ResultSet selectColumn(String table, String[] columns){
        String col = "";
        for(String s : columns){
            col += s + ", ";
        }
        col = col.substring(0, col.length() - 2);
        String sql = String.format("SELECT %s FROM %s", col, table);
        return getResultSet(sql);
    }

    public static ResultSet selectColumn(String table, String[] columns, String requirment){
        String col = "";
        for(String s : columns){
            col += s + ", ";
        }
        col = col.substring(0, col.length() - 2);
        String sql = String.format("SELECT %s FROM %s WHERE %s", col, table, requirment);
        return getResultSet(sql);
    }

    public static boolean insert(String table, Map<String, String> params){
        String columns = table + "(";
        String values = "VALUES(";
        for(String key : params.keySet()){
            columns += String.format("%s,", key);
        }
        columns = columns.substring(0, columns.length() - 1);
        columns += ") ";

        for(String val : params.values()){
            values += String.format("%s,", val);
        }
        values = values.substring(0, values.length() - 1);
        values += ") ";

        String sql = "INSERT INTO " + columns + values;
        System.out.println(sql);
        try {
            DBConection conn = new DBConection();
            Statement statement = conn.connection().createStatement();
            return statement.executeUpdate(sql) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean update(String table, Map<String, String> params, String clause){
        DBConection dbConection = new DBConection();
        String sql = "UPDATE " + table + " SET ";
        for(int i=0; i<params.size(); i++){
            String col = (String) params.keySet().toArray()[i];
            String val = (String) params.values().toArray()[i];
            sql += col + " = " + val;
            if(i != params.size() - 1){
                sql += ", ";
            }
        }
        sql += " WHERE " + clause;
        System.out.println(sql);
        try {
            Statement statement = dbConection.connection().createStatement();
            return statement.executeUpdate(sql) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delete(String table, String clause){
        String sql = String.format("DELETE FROM %s WHERE %s ;",table,clause);
        System.out.println(sql);
        DBConection dbConection = new DBConection();
        try {
            Statement statement = dbConection.connection().createStatement();
            return statement.executeUpdate(sql) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    private static ResultSet getResultSet(String sql) {
       // System.out.println(sql);
        ResultSet resultSet = null;
        try {
            DBConection db = new DBConection();
            Statement statement = db.connection().createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
