package ss.week5;

import java.util.Calendar;

public class Score<E extends Comparable<E>, F> implements Comparable<Score<E,F>>{

	Calendar date;
	E score;
	F achiever;
	
	public Score(E score, F achiever, Calendar date){
		this.score = score;
		this.achiever = achiever;
		this.date = date;
	}
	
	public Calendar getDate(){
		return this.date;
	}
	
	public Object getScore(){
		return this.score;
	}
	
	public Object getAchiever(){
		return this.achiever;
	}

	public int compareTo(Score<E, F> o) {
		return this.score.compareTo(o.score);
	}
	
}
