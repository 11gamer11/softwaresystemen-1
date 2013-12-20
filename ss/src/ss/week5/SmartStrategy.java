package ss.week5;

import java.util.*;

public class SmartStrategy implements Strategy {
	
	String strategyName = "Smart";
	// Enables debugging output if true;
	boolean debug = true;
	// Enables verbose debugging if true;
	boolean verbose = false;
	
	public String getName() {
		return this.strategyName;
	}

	public int determineMove(Board b, Mark m) {
		
		// Create set to hold empty fields;
		Set<Integer> emptyFields = new HashSet<Integer>();
		
		// Fill the set with empty fields;
		for(int i=0; i<9; i++){
			if(b.isEmptyField(i)){ emptyFields.add(i); }
		}
		
		// Check if middle field is empty;
		// If it is, return that immediately;
		if(emptyFields.contains(4)){ 
			if(this.debug){ System.out.println("COMPUTER: Chosen 4. Reason: Middle empty."); }
			return 4;
		}
		
		// Check if we can guarantee a win;
		// If we can, return that immediately;
		int selfWinMove = determineWinningMoveForMark(b,m);
		if(selfWinMove != -1){ 
			if(this.debug){ System.out.println("COMPUTER: Chosen " + selfWinMove + ". Reason: Winning move for me.");}
			return selfWinMove;
		}
		
		// Check if there is a guaranteed win for the opposing side;
		// If we can, return that immediately, so we don't lose;
		int opponentWinMove = determineWinningMoveForMark(b,m.other());
		if(opponentWinMove != -1){ 
			if(this.debug){ System.out.println("COMPUTER: Chosen " + opponentWinMove + ". Reason: Winning move for opponent.");}
			return opponentWinMove;
		}
		
		// If there are no other options, select a random field;
		Iterator<Integer> iter = emptyFields.iterator();
		int choice = iter.next();
		if(this.debug){ System.out.println("COMPUTER: Chosen " + choice + ". Reason: Random field because no priorities.");}
		return choice;
	}
	
	public int determineWinningMoveForMark(Board b, Mark m){
		if(this.verbose){ 
			for(int i=0;i<9;i++){
				System.out.println("COMPUTER: Field "+i+" is: "+b.getField(i).toString());
			}
		}
		
		// Check all fields for winning moves, and return it if it is;
		// Horizontal:
		if(b.getField(0) == m && b.getField(1) == m && b.isEmptyField(2)){ return 2; }
		if(b.getField(1) == m && b.getField(2) == m && b.isEmptyField(0)){ return 0; }
		if(b.getField(0) == m && b.getField(2) == m && b.isEmptyField(1)){ return 1; }

		if(b.getField(3) == m && b.getField(4) == m && b.isEmptyField(5)){ return 5; }
		if(b.getField(4) == m && b.getField(5) == m && b.isEmptyField(3)){ return 3; }
		if(b.getField(3) == m && b.getField(5) == m && b.isEmptyField(5)){ return 4; }

		if(b.getField(6) == m && b.getField(7) == m && b.isEmptyField(8)){ return 8; }
		if(b.getField(7) == m && b.getField(8) == m && b.isEmptyField(6)){ return 6; }
		if(b.getField(6) == m && b.getField(8) == m && b.isEmptyField(7)){ return 7; }
		
		// Vertical:
		if(b.getField(0) == m && b.getField(3) == m && b.isEmptyField(6)){ return 6; }
		if(b.getField(3) == m && b.getField(6) == m && b.isEmptyField(0)){ return 0; }
		if(b.getField(0) == m && b.getField(6) == m && b.isEmptyField(3)){ return 3; }

		if(b.getField(1) == m && b.getField(4) == m && b.isEmptyField(7)){ return 7; }
		if(b.getField(4) == m && b.getField(7) == m && b.isEmptyField(1)){ return 1; }
		if(b.getField(1) == m && b.getField(7) == m && b.isEmptyField(4)){ return 4; }

		if(b.getField(2) == m && b.getField(5) == m && b.isEmptyField(8)){ return 8; }
		if(b.getField(5) == m && b.getField(8) == m && b.isEmptyField(2)){ return 2; }
		if(b.getField(2) == m && b.getField(8) == m && b.isEmptyField(5)){ return 5; }

		// Diagonal:
		if(b.getField(0) == m && b.getField(4) == m && b.isEmptyField(8)){ return 8; }
		if(b.getField(4) == m && b.getField(8) == m && b.isEmptyField(0)){ return 0; }
		if(b.getField(0) == m && b.getField(8) == m && b.isEmptyField(4)){ return 4; }

		if(b.getField(2) == m && b.getField(4) == m && b.isEmptyField(6)){ return 6; }
		if(b.getField(4) == m && b.getField(6) == m && b.isEmptyField(2)){ return 2; }
		if(b.getField(2) == m && b.getField(6) == m && b.isEmptyField(4)){ return 4; }
		
		
		// No winning move detected, return -1;
		return -1;
	}

}
