package com.lkamdem.db;


import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url = "jdbc:postgresql://localhost:5432/bankingsystem";
    private static final String username = "postgres";
    private static final String password = "Postgre2002&";
    private static  DatabaseConnection dbcon;

    public Connection getConnection(){
        try{
            return DriverManager.getConnection(url, username, password);
        }catch(SQLException e){
            System.out.println(e.getMessage()); // TODO: Replace with proper logging when GUI is added
            return null;
        }
    }
    private DatabaseConnection(){

    }
    public static DatabaseConnection getDatabaseConnection(){
        if(dbcon != null){
        return dbcon;
        }
       return dbcon = new DatabaseConnection();
    }
}
