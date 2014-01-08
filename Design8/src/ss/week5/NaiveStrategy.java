package ss.week5;

public class NaiveStrategy implements Strategy {

	public String strategyName = "Naive";
	
	public String getName() {
		return this.strategyName;
	}

	//@ensures b.isEmptyField(\result);
	public int determineMove(Board b, Mark m) {
		
		// Get a random field;
		int randomField = (int) Math.round((Math.random()*8));
		
		// Check if the field is empty;
		// If it is, return it, else, re-call this method to get a valid field;
		if(b.isEmptyField(randomField)){
			return randomField;
		}else{
			return determineMove(b,m);
		}
	}

}
