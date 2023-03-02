package com.CS01.SerWise.Controllers;

import com.CS01.SerWise.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class appoinmentTable {

    public static void insert(String attributes, String values) throws SQLException, ClassNotFoundException {
        Connection con = DatabaseConnection.initializeDatabase();
        String query = "insert into serwise.appoinment (%s) values (%s);";
        query = String.format(query, attributes, values);
        Statement statement = con.createStatement();
        statement.executeUpdate(query);
        con.close();
    }

    public static void delete(String primaryKey) throws SQLException, ClassNotFoundException {
        Connection con = DatabaseConnection.initializeDatabase();
        String query = "delete from serwise.appoinment where Appoinment_Id=%s;";
        query = String.format(query, primaryKey);
        Statement statement = con.createStatement();
        statement.executeUpdate(query);
        con.close();
    }

    public static ArrayList<String[]> select(String attributes, String where) throws SQLException, ClassNotFoundException {
        Connection con = DatabaseConnection.initializeDatabase();
        Statement statement = con.createStatement();
        ArrayList outArr = new ArrayList<String[]>();
        String query;
        if (attributes.equals("*")){
            query = "select * from serwise.appoinment where %s;";
            query = String.format(query, where);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                String temp[] = new String[7];
                temp[0] = resultSet.getString("Appoinment_Id");
                temp[1] = resultSet.getString("Date");
                temp[2] = resultSet.getString("Time");
                temp[3] = resultSet.getString("Desription");
                temp[4] = resultSet.getString("Registered_Client_Id");
                temp[5] = resultSet.getString("Branch_Id");
                temp[6] = resultSet.getString("Vehicle_Id");
                outArr.add(temp);
            }
        }
        else {
            query = "select %s from serwise.appoinment where %s;";
            query = String.format(query, attributes, where);
            String[] colnames = attributes.split(",");
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                String temp[] = new String[colnames.length];
                for(int j=0 ; j<colnames.length ; j++){
                    temp[j] = resultSet.getString(colnames[j]);
                }
                outArr.add(temp);
            }
        }
        return outArr;
    }

    public static ArrayList<String[]> select(String attributes) throws SQLException, ClassNotFoundException {
        Connection con = DatabaseConnection.initializeDatabase();
        String query = "select %s from serwise.appoinment;";
        query = String.format(query, attributes);
        String[] colnames = attributes.split(",");
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        ArrayList outArr = new ArrayList<String[]>();
        while (resultSet.next()){
            String temp[] = new String[colnames.length];
            for(int j=0 ; j<colnames.length ; j++){
                temp[j] = resultSet.getString(colnames[j]);
            }
            outArr.add(temp);
        }
        return outArr;
    }

    public static ArrayList<String[]> select() throws SQLException, ClassNotFoundException {
        Connection con = DatabaseConnection.initializeDatabase();
        String query = "select * from serwise.appoinment;";
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        ArrayList outArr = new ArrayList<String[]>();
        while (resultSet.next()){
            String temp[] = new String[7];
            temp[0] = resultSet.getString("Appoinment_Id");
            temp[1] = resultSet.getString("Date");
            temp[2] = resultSet.getString("Time");
            temp[3] = resultSet.getString("Desription");
            temp[4] = resultSet.getString("Registered_Client_Id");
            temp[5] = resultSet.getString("Branch_Id");
            temp[6] = resultSet.getString("Vehicle_Id");
            outArr.add(temp);
        }
        con.close();
        return outArr;
    }

    public static void update(String afterSet, String afterWhere) throws SQLException, ClassNotFoundException {
        Connection con = DatabaseConnection.initializeDatabase();
        String query = "update serwise.appoinment set %s where %s;";
        query = String.format(query, afterSet, afterWhere);
        Statement statement = con.createStatement();
        statement.executeUpdate(query);
        con.close();
    }

}
