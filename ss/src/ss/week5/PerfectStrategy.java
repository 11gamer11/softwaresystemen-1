package ss.week5;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PerfectStrategy implements Strategy {
	
	/**
	 * Name of Strategy
	 */
	private String name = "Perfect";
	/**
	 * Counter to indicate the amount of moves determined.
	 */
	private int moveCounter = 0;
	/**
	 * Statics to use for moves
	 */
	public static final int LOSING = -1;
	public static final int NEUTRAL = 0;
	public static final int WINNING = 1;
	/**
	 * Statics to use for row/column distinction
	 */
	public static final int ROW = 10;
	public static final int COL = 11;
	/**
	 * Enables debugging output if true
	 */
	boolean debug = false;
	boolean verbose = false;
	/**
	 * Current recursion depth, just for shits and giggles
	 */
	int depth = 0;
	
	/**
	 * Returns the name of the strategy
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Prints the string to console
	 * @param string - the string to print
	 */
	public void debug(String string){
		if(debug){
			System.out.println(string);
		}
	}

	/**
	 * Determines the best next move for the current board and mark.
	 * @param b - Board
	 * @param m - Mark
	 */
	public int determineMove(Board b, Mark m) {	
/*
	Strategy:
	0. If the game has just begun, play the center square, if the center square is taken, choose a corner at random.
	1. Complete a winning Tic-Tac-Toe if possible.
	2. Block an opposing Tic-Tac-Toe if possible.
	3. Rate each square for its desirability, for each other taken square (by the AI) on a line, add one point of desirability for that square. For each square taken by the opponent, remove one point of desirability.
 */
		// Increment MoveCounter
		moveCounter++;
		
		// Create two variables to hold the best rating and best move index
		int bestRating = -999;
		int bestMove = 0;
		int winningMove = -1;
		int losingMove = -1;
		
		// Print board if verbose
		if(verbose){
			debug("-------------  The board at this point:  -------------");
			debug(b.toString());
		}
		
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
			
			int moveType = determineMoveType(b,m,field);
			
			if(determineMoveType(b, m, field) == WINNING){
				winningMove = field;
			}else if(determineMoveType(b, m, field) == LOSING){
				losingMove = field;
			}else{
				int rating = rateField(b,m,field);
				if(rating > bestRating){
					bestRating = rating;
					bestMove = field;
				}else if(rating == bestRating){
					int coinFlip = (int) Math.round(Math.random());
					debug("[DEBUG] Ratings the same! Doing a CoinFlip: "+coinFlip);
					if(coinFlip == 1){
						debug("[DEBUG] Changing best rating for randomness");
						bestRating = rating;
						bestMove = field;
					}
				}
			}
		}
		
		// Create testboard to reset the moveCounter if the board is full after 1 move
		// to avoid a bug that the moveCounter doesn't reset after restarting a game.
		Board moveCounterBugAvoidanceBoard = b.deepCopy();
		
		// Do a (possible) last move on the avoidanceBoard, doesn't matter what move.
		moveCounterBugAvoidanceBoard.setField(bestMove, m);
		// If the game is over after the just-issued move
		if(moveCounterBugAvoidanceBoard.gameOver()){
			// Reset the move counter for the next game
			this.moveCounter = 0;
		}
		
		// If 4 is empty take the center square!
		if(b.isEmptyField(4)){
			debug("[DEBUG] Decision: 4 - Reason: Center is free");
			return 4;
		}else if(winningMove != -1){
			debug("[DEBUG] Decision: "+winningMove+" - Reason: Winning move.");
			return winningMove;
		}else if(losingMove != -1){
			debug("[DEBUG] Decision: "+losingMove+" - Reason: Otherwise opponent would win.");
			return losingMove;
		}else{
			debug("[DEBUG] Decision: "+bestMove+" - Reason: The rating of this move was highest, namely "+bestRating);
			return bestMove;
		}
	}

	/**
	 * Determines if the move given is winning, losing or neutral.
	 * @param b - Board
	 * @param m - Mark
	 * @param field - Index of field
	 * @return WINNING,LOSING or NEUTRAL
	 */
	public int determineMoveType(Board b, Mark m, int field){
		// Get copy of board
		Board tb = b.deepCopy();
		// Set field to own mark
		tb.setField(field, m);
		// Check if we have won, return WINNING if we have
		if(tb.hasWinner() && tb.isWinner(m)){
			return WINNING;
		}
		// Set field to other's mark
		tb.setField(field, m.other());
		// Check if other has won, return LOSING if he has
		if(tb.hasWinner() && tb.isWinner(m.other())){
			return LOSING;
		}
		// If none of the above, return neutral.
		return NEUTRAL;
	}
	
	public int rateField(Board b,Mark m,int field){
		
		int[][] diagonalIndices = {{4,8},{-1,-1},{4,6},{-1,-1},{0,2,6,8},{-1,-1},{4,2},{-1,-1},{4,0}};
		int row = getRowOrColumn(field, ROW);
		int col = getRowOrColumn(field, COL);
		
		debug("[DEBUG] Determining ratings for mark "+m.toString()+" @ index "+field+" ("+row+","+col+")");
		
		int rating = 0;
		 // Hor & ver ratings
		for(int i=0;i<3;i++){
			if(b.getField(row, i) == m){
				debug("[DEBUG] Increasing rating; Horizontal ("+row+","+i+") is a "+m.toString());
				rating++;
			}else if(b.getField(row, i) == m.other()){
				debug("[DEBUG] Decreasing rating; Horizontal ("+row+","+i+") is a "+m.other().toString());
				rating--;
			}
			if(b.getField(i, col) == m){
				debug("[DEBUG] Increasing rating; Vertical ("+i+","+col+") is a "+m.toString());
				rating++;
			}else if(b.getField(i, col) == m.other()){
				debug("[DEBUG] Decreasing rating; Vertical ("+i+","+col+") is a "+m.other().toString());
				rating--;
			}
		}
		
		// Diagonal ratings
		for(int x:diagonalIndices[field]){
			
			if(x != -1){
				int xRow = getRowOrColumn(x,ROW);
				int xCol = getRowOrColumn(x,COL);
			
				if(b.getField(x) == m){
					debug("[DEBUG] Increasing rating; Diagonal ("+xRow+","+xCol+") is a "+m.toString());
					rating++;
				}else if(b.getField(x) == m.other()){
					debug("[DEBUG] Increasing rating; Diagonal ("+xRow+","+xCol+") is a "+m.other().toString());
					rating--;
				}
			}
		}
		
		debug("[DEBUG] Total rating for square "+field+": "+rating);
		return rating;
	}
	
	public int getRowOrColumn(int index, int type){
		int[][] boardIndices = {{0,0},{0,1},{0,2},{1,0},{1,1},{1,2},{2,0},{2,1},{2,2}};
		if(type == ROW){
			return boardIndices[index][0];
		}
		if(type == COL){
			return boardIndices[index][1];
		}
		return -1;
	}
	
}



















