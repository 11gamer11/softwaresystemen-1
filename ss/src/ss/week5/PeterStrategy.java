package ss.week5;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PeterStrategy implements Strategy {

	/**
	 * Name of Strategy
	 */
	public String name = "Peter";
	
	/**
	 * Statics to use for moves
	 */
	private static final int WINNING = 1;
	private static final int LOSING = -1;
	private static final int NEUTRAL = 0;
	
	/**
	 * Statics to use for row/column distinction
	 */
	public static final int ROW = 10;
	public static final int COL = 11;

	/**
	 * Enables debugging output if true
	 */
	private boolean verbose = false;
	private boolean debug = false;
	
	
	/**
	 * Returns the name of the strategy
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Determines the best next move for the current board and mark.
	 * @param b - Board
	 * @param m - Mark
	 */
	public int determineMove(Board b, Mark m) {
		
		/**
- Winnende zet
- Blokkerende zet
- Ander 2 hoeken, jij midden -> GEEN hoek
- Hoek
- Random
		 */
		
		// Create two variables to hold the best rating and best move index
		int bestRating = -999;
		int winningMove = -1;
		int losingMove = -1;
		int move = -1;
		boolean cornerCase = false;
		
		// Print board if verbose
		if(this.verbose){
			debug("-------------  The board at this point:  -------------");
			debug(b.toString());
		}
		
		// Create set of empty fields
		Set<Integer> emptyFields = new HashSet<Integer>();
		ArrayList<Integer> possibleMoves = new ArrayList<Integer>();
		// loop over fields
		for(int i=0; i<9; i++){
			// If field is empty
			if(b.isEmptyField(i)){
				// Add to set of empty fields
				emptyFields.add(i);
				possibleMoves.add(i);
			}
		}
		
		// Get an iterator over the empty fields
		Iterator<Integer> iter = emptyFields.iterator();
		
		// For all fields
		while(iter.hasNext()){
			// Save the field value to field
			int field = iter.next();
			
			int moveType = determineMoveType(b,m,field);
			
			if(moveType == WINNING){
				winningMove = field;
			}else if(moveType == LOSING){
				losingMove = field;
			}else{
				ArrayList<Integer> newPossibleMoves = cornerCase(b,m, possibleMoves);
				if(newPossibleMoves.size() < possibleMoves.size()){
					cornerCase = true;
					possibleMoves = newPossibleMoves;
				}else{
					if(possibleMoves.contains(0) || possibleMoves.contains(2) || possibleMoves.contains(6) || possibleMoves.contains(8)){
						for(int i = 0; i<9; i++){
							if(possibleMoves.contains(i) && i!=0 && i!=2 && i!=6 && i!=8){
								possibleMoves.remove(possibleMoves.indexOf(i));
							}
						}
					}
					
				}
				
				move = possibleMoves.get((int) Math.floor(Math.random()*possibleMoves.size()));
				
			}
		}
		
		// If 4 is empty conquer the center square!
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
			debug("[DEBUG] Decision: "+move+" - Reason: Following strategy: "+bestRating);
			return move;
		}
	}
	
	/**
	 * 
	 * @param b
	 * @param m
	 * @return
	 */
	private ArrayList<Integer> cornerCase(Board b, Mark m, ArrayList<Integer> possibleMoves) {
		// If I own the center field
		if(b.getField(4) == m){
			// And the opponent has two opposing corners
			if((b.getField(0) == m.other() && b.getField(8) == m.other()) || (b.getField(2) == m.other() && b.getField(6) == m.other())){
				// We must not chose a corner, or we will lose.
				// so, remove the corners from possibleMoves.
				// First, copy the list to avoid concurrentModificationError
				ArrayList<Integer> copy = new ArrayList<Integer>();
				for(int move:possibleMoves){
					copy.add(move);
				}
				
				for(int x:possibleMoves){
					if(x == 0 || x == 2 || x == 6 || x == 8){
						copy.remove(copy.indexOf(x));
					}
				}
				possibleMoves = copy;
			}
		}
		return possibleMoves;
	}

	/**
	 * Determines if the move given is winning, losing or neutral.
	 * @param b - Board
	 * @param m - Mark
	 * @param field - Index of field
	 * @return WINNING,LOSING or NEUTRAL
	 */
	private int determineMoveType(Board b, Mark m, int field) {
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
	
	/**
	 * Returns the row or column of the given index
	 * @param index - the square index
	 * @param type - return type (row or column)
	 * @return the row or column of the index, or -1 if invalid type.
	 */
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

	/**
	 * Prints the string to console
	 * @param string - the string to print
	 */
	private void debug(String string) {
		if(this.debug){
			System.out.println(string);
		}
	}

}
