package com.hadleym.ws.example;

import javax.ejb.*;
import javax.jws.*;

@Stateless
@WebService
public class ScoreService {
	private static Score score = new Score();
	
        @WebMethod
	public Score getScore() { return score; }
	@WebMethod
	public int increaseWins() { return ++score.wins; }
	
        @WebMethod
        public int increaseTies() { return ++score.ties; }
        
        @WebMethod
	public int increaseLosses() { return ++score.losses; }
	
        @WebMethod
	public int getWins() { return score.wins; }
        
        @WebMethod
	public int getTies() { return score.ties; }
        
        @WebMethod
	public int getLosses() { return score.losses; }
	
        @WebMethod
	public Score updateScore(int wins, int losses, int ties) {
		score.wins = wins;
		score.losses = losses;
		score.ties = ties;
		return score;
	}

	@WebMethod(operationName="resetScore")
	public void reset() {
		score.wins = score.losses = score.ties = 0;
	}
}
