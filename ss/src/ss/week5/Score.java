package ss.week5;

import java.util.Calendar;

public class Score<E extends Comparable<E>, F> implements Comparable<Score<E,F>>{

	private Calendar date;
	private E score;
	private F achiever;
	
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
	
	public int toInt(){
		return this.score.toInt();
	}
	
	public String toString(){
		String dateString = String.valueOf(this.date.get(Calendar.DAY_OF_MONTH)) + "-" + String.valueOf(this.date.get(Calendar.MONTH)) + "-" + String.valueOf(this.date.get(Calendar.YEAR)) + " " + String.valueOf(this.date.get(Calendar.HOUR)) + ":" + String.valueOf(this.date.get(Calendar.MINUTE));
		return "Score: "+this.score.toString()+" - Achieved by: "+this.achiever.toString()+" on "+dateString+"\n";
	}
	
}
