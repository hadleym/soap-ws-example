package com.hadleym.ws.example;

import javax.ejb.*;
import javax.jws.*;

@Stateless
@WebService
public class ScoreService {
    DbConnector conn;
    String tableName;
    public ScoreService() {
        conn = new DbConnector("/home/mark/data/sqlite/", "test.db");
        this.tableName = "games";

    }
	private static Score score = new Score();
	
        @WebMethod
	public Score getScore() { return score; }
        
	@WebMethod
	public int increaseWins() { 
            int currentWins = conn.getValue(tableName, "wins");
            conn.update(tableName, "wins", ++currentWins);
            return currentWins;
        }
	
        @WebMethod
        public int increaseTies() { return ++score.ties; }
        
        @WebMethod
	public int increaseLosses() { return ++score.losses; }
	
        @WebMethod
	public int getWins() { return conn.getValue(tableName, "wins");}
        
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
            conn.update("data", "wins", 0);
		score.losses = score.ties = 0;
	}
        
        public void deleteDatabase() {
            conn.deleteDatabase();
        }
}
