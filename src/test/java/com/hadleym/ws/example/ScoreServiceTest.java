package com.hadleym.ws.example;

import static org.junit.Assert.*;
import org.junit.Test;

public class ScoreServiceTest {

	@Test
	public void test() {
                DbConnector conn = new DbConnector("/home/mark/data/sqlite/", "test.db");
                conn.deleteDatabase();
                conn.createDatabase();
                conn.createTable("games");
                conn.insert("games", "wins", 0);
                conn.insert("games", "ties", 0);
                conn.insert("games", "losses", 0);
                
		ScoreService scoreService = new ScoreService();
		assertEquals(scoreService.getWins(), 0);
		assertEquals(scoreService.getTies(), 0);
		assertEquals(scoreService.getLosses(), 0);
                conn.deleteTable("games");
	}
	
	@Test
	public void testUpdate() {
            DbConnector conn = new DbConnector("/home/mark/data/sqlite/", "test.db");
            conn.deleteTable("games");
            conn.createTable("games");
            conn.insert("games", "wins", 0);
            conn.insert("games", "ties", 0);
            conn.insert("games", "losses", 0);
            ScoreService scoreService = new ScoreService();
            assertEquals(1, scoreService.increaseLosses());
            assertEquals(1, scoreService.increaseTies());
            assertEquals(1, scoreService.increaseWins());

            assertEquals(2, scoreService.increaseLosses());
            assertEquals(2, scoreService.increaseTies());
            assertEquals(2, scoreService.increaseWins());

            scoreService.reset();
            assertEquals(1, scoreService.increaseLosses());
            assertEquals(1, scoreService.increaseTies());
            assertEquals(1, scoreService.increaseWins());
            conn.deleteTable("games");
	}

}
