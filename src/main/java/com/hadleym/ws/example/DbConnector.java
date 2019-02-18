/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadleym.ws.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mark
 */
public class DbConnector {
    public String url;
    public String header = "jdbc:sqlite:";
    private String dbName;
    private Connection conn;
    public DbConnector(String url, String dbName) {
        this.url = url;
        this.dbName = dbName;
    }

    private Connection connect(String name) {
        // SQLite connection string
        String dbUrl = this.header + this.url + name;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbUrl);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public List<String> getTables() {
        String sql = "SELECT name FROM sqlite_master WHERE type='table'";
        List<String> list = new ArrayList<String>();
        
         // SQLite connection string
        String fullUrl = this.header + this.url + this.dbName;
        // SQL statement for creating a new table
        
        try (Connection conn = DriverManager.getConnection(fullUrl);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)){
            // create a new tab
            while (rs.next()) {
                list.add(rs.getString("name"));
            }
            
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION !!!!" + e.getMessage());
        }
        
        return list;
        
    }
    
    public boolean createDatabase() {
         // SQLite connection string
        String fullUrl = header + this.url + this.dbName;
        
        try {
            DriverManager.getConnection(fullUrl);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    public void deleteTable(String tableName) {
        String fullUrl = header + this.url + this.dbName;
        String sql = "DROP TABLE ?";
        try (Connection conn = DriverManager.getConnection(fullUrl);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, tableName);
                pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION !!!!" + e.getMessage());
        }
    }
    
    public boolean deleteDatabase() {
        Path path = Paths.get(this.url+this.dbName);
        try {
            Files.delete(path);
        } catch (IOException x) {
            System.out.println("Cannot Delete path: " + x);
            return false;
        }
        return true;
    }
    
    public void insert(String tableName, String name, int value) {
        String sql = String.format("INSERT INTO %s(name, value) VALUES(?,?)" , 
                tableName);
//        String sql = new String("INSERT INTO games(name, value) VALUES(?,?)");
        
        
         // SQLite connection string
        String fullUrl = this.header + this.url + this.dbName;
        // SQL statement for creating a new table
        
        try (Connection conn = DriverManager.getConnection(fullUrl);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setDouble(2, value);
                pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION !!!!" + e.getMessage());
        }
    }
    public void update(String tableName, String name, int value) {
        String fullUrl = this.header + this.url + this.dbName;
        String sql = "UPDATE games SET value = ? WHERE name = ?";
        
        try (Connection conn = DriverManager.getConnection(fullUrl);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, value);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public int getValue(String tableName, String name) {
        String sql = "SELECT id, name, value FROM " + tableName + " WHERE name is \"" + name +"\"";
        String fullUrl = this.header + this.url + dbName;
        int result = 0;
        try (Connection conn = DriverManager.getConnection(fullUrl);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                /*
                System.out.println(rs.getInt("id") +  "\t" + 
                                   rs.getString("name") + "\t" +
                                   rs.getDouble("value"));
                */
                result = rs.getInt("value");
            }
            System.out.println("Finished");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }
    
    public boolean createTable(String tableName) {
         // SQLite connection string
        String url = this.header + this.url + dbName;
        
        // SQL statement for creating a new table
        String sql = String.format("CREATE TABLE IF NOT EXISTS %s (\n", tableName)
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	value INTEGER \n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
