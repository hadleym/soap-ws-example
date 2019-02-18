/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadleym.ws.example;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.Assert.*;
import org.junit.Test;

public class DatabaseTest {
    @Test
    public void test() {
        String url = "/home/mark/data/sqlite/";
        DbConnector conn = new DbConnector(url, "test.db");

//        conn.deleteDatabase();
        conn.createTable("test1");
        conn.deleteTable("test1");

//        conn.createDatabase();
//        assertTrue(Files.exists(Paths.get(url+"test.db")));
        conn.createTable("games");
//        assertEquals(conn.getTables().get(0), 
//                new String("games"));
//        conn.createTable("games2");
//        assertEquals(conn.getTables().get(1), 
//                new String("games2"));
        conn.insert("games", "ties", 0);
        conn.insert("games", "wins", 0);
        conn.insert("games", "losses", 0);
        
        assertEquals(conn.getValue("games", "ties"), 0);
                
        conn.update("games", "wins", 1);
        assertEquals(conn.getValue("games", "wins"), 1);
        
        conn.update("games", "wins", 2);
        assertEquals(conn.getValue("games", "wins"), 2);
        
        conn.deleteTable("games");
        
        
    }
}
