package ss.week5;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PerfectStrategy implements Strategy {

	/**
	 * Name of the strategy
	 */
	private String name = "Recursive";
	
	public static final int WINNING = 1;
	public static final int NEUTRAL = 0;
	public static final int LOSING = -1;
	
	public String getName() {
		return this.name;
	}

	public int determineMove(Board b, Mark m) {
		
		return bestMove(b,m)[0];
		
	}
	
	public int[] bestMove(Board b, Mark m){
		
		int bestQual = -1;
		int bestMove = -1;
		
		// Create set of empty fields
		Set<Integer> emptyFields = new HashSet<Integer>();
		// loop over fields
		for(int i=0; i<9; i++){
			// If field is empty
			if(b.isEmptyField(i)){
				// Add to set of empty fields
				emptyFields.add(i);
			}
		}
		
		// Get an iterator over the empty fields
		Iterator<Integer> iter = emptyFields.iterator();
		
		// For all fields
		while(iter.hasNext()){
			// Save the field value to field
			int field = iter.next();
			
			int qual = evaluateMove(b,m,field);
			
			if(qual > bestQual){
				bestQual = qual;
				bestMove = field;
			}else if(qual == bestQual && Math.floor(Math.random()*2) == 1){
				bestQual = qual;
				bestMove = field;
			}
		}
		
		int[] result = new int[2];
		result[0] = bestMove;
		result[1] = bestQual;
		
		return result;
	
	}
	
	private int evaluateMove(Board b, Mark m, int field) {
		
		int res;
		
		b.setField(field, m);
		res = evaluate(b, m);
		b.setField(field, Mark.EMPTY);
		
		return res;
	}

	private int evaluate(Board b, Mark m) {
		
		int res = 0;
		
		if(!b.isWinner(m)){
			if(b.isWinner(m.other())){
				res = LOSING;
			}else if(b.isFull()){
				res = NEUTRAL;
			}else{
				int[] oppBestMove = bestMove(b,m.other());
				int oppQual = oppBestMove[1];
				
				if(oppQual == WINNING){
					return LOSING;
				}else if(oppQual == LOSING){
					return WINNING;
				}else{
					return NEUTRAL;
				}
				
			}
		}else{
			res = WINNING;
		}
		
		return res;
		
	}

}
