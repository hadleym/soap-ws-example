package com.hadleym.ws.example;

import static org.junit.Assert.*;
import org.junit.Test;

public class ScoreServiceTest {

	@Test
	public void test() {
		ScoreService scoreService = new ScoreService();
		assertEquals(scoreService.getWins(), 0);
		assertEquals(scoreService.getTies(), 0);
		assertEquals(scoreService.getLosses(), 0);
	}
	
	@Test
	public void testUpdate() {
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
	}

}
