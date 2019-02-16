package com.hadleym.ws.example;
import static org.junit.Assert.*;

import org.junit.Test;

public class ScoreTest {

	@Test
	public void test() {
		Score score = new Score();
		assertEquals(0,  score.losses);
		score.wins = -23;
		assertEquals(-23,  score.wins);
		assertEquals(0,  score.ties);
	}
}	

