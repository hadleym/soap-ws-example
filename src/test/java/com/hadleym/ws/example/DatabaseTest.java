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

        conn.deleteDatabase();
        assertTrue(conn.createDatabase("test.db"));
        assertTrue(conn.deleteDatabase());

        conn.createDatabase("test.db");
        assertTrue(Files.exists(Paths.get(url+"test.db")));
        conn.createTable("test.db", "games");
        assertEquals(conn.getTables("test.db").get(0), 
                new String("games"));
        conn.createTable("test.db", "games2");
        assertEquals(conn.getTables("test.db").get(1), 
                new String("games2"));
        conn.insert("test.db", "games", "ties", 0);
        conn.insert("test.db", "games", "wins", 0);
        conn.insert("test.db", "games", "losses", 0);
        
        assertEquals(conn.getValue("test.db", "games", "ties"), 0);
                
        conn.update("test.db"," games", "wins", 1);
        assertEquals(conn.getValue("test.db", "games", "wins"), 1);
        
    }
}
