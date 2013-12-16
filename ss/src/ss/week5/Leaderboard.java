package ss.week5;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * Leaderboard class for a game
 * 
 * Can be used to store and inspect high-scores.
 * 
 * @author Kevin Alberts & Sophie Lathouwers
 *
 */
public class Leaderboard<E extends Comparable<E>, F> {
	
	public List<Score<E,F>> scoreDB = new ArrayList<Score<E,F>>();
	
	/**
	 * Method to add new scores to the leaderboard database
	 * @param score - The score to add.
	 */
	
	public void addScore(E score, F player){
		Calendar calendar = Calendar.getInstance();
		this.addScore(score, player, calendar);
	}
	
	public void addScore(E score, F player, Calendar date){
		scoreDB.add(new Score<E,F>(score, player, date));
	}
	
	/*
	 * Score retrieval functions
	 */
	
	public List<E> getTop(int amount){
		return null;
	}
	
	public List<E> getScoresHigherThan(){
		return null;
	}
	
	public List<E> getScoresLowerThan(){
		return null;
	}
	
	public E getAverageScore(){
		return null;
	}
	
	public E getAverageScoreOfDay(Calendar date){
		return null;
	}
	
	public List<Score<E,F>> getScoreOfDay(Calendar date){
		
		List<Score<E,F>> dayScores = new ArrayList<Score<E,F>>();
		
		Iterator<Score<E, F>> iter = scoreDB.iterator();
		while(iter.hasNext()){
			Score<E, F> next = iter.next();
			Calendar nextDate = next.getDate();

			boolean daySame = nextDate.get(Calendar.DAY_OF_MONTH) == date.get(Calendar.DAY_OF_MONTH);
			boolean monthSame = nextDate.get(Calendar.MONTH) == date.get(Calendar.MONTH);
			boolean yearSame = nextDate.get(Calendar.YEAR) == date.get(Calendar.YEAR); 
			
			if(daySame && monthSame && yearSame){
				dayScores.add(next);
			}
		}
		return dayScores;
	}
	
	/*
	 * Team retrieval functions
	 */
	
	public List<E> getBestResultOfAchiever(){
		return null;
	}
	
	public Calendar getBestDayForAchiever(){
		return null;
	}

	
	public static void main(String[] args){
		Leaderboard<Integer, String> b = new Leaderboard<Integer, String>();
		b.addScore(50, "Kevin Alberts");
		b.addScore(0, "John Smith");
		b.addScore(20, "Rainbow Dash");
		b.addScore(42, "Arthur Dent");
		b.addScore(9001, "Goku");
		
		b.getScoreOfDay(Calendar.getInstance());
	}
	
}
