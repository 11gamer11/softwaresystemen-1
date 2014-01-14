package rolit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Game board for Rolit
 * 
 * @author Sophie Lathouwers & Kevin Alberts
 * @version $Revision: 1.0 $
 */
public class Board {
	
	/*		CONSTANTS		*/
	public static final int DIM = 8;
	private static final String LINE = "-----";
	private static final String DELIM = "     ";
	
	/*		INSTANCE VARIABLES		*/
	/**
	 * The DIM by DIM fields of the Tic Tac Toe board. See NUMBERING for the
	 * coding of the fields.
	 */
	private Mark[] fields;
	private int amountPlayers;
	 
	/**
	 * Creates an empty board.
	 * 
	 * @param amountPlayers - The amount of players on the board.
	 */
	
	/*		CONSTRUCTOR		*/
	public Board(int amountPlayers){
		fields = new Mark[DIM * DIM];
		this.amountPlayers = amountPlayers;
		reset();
	}
	
	/**
	 * Creates a deep copy of this field.
	 */
	public Board deepCopy() {
		Board copy = new Board(this.amountPlayers);
		for (int i = 0; i < fields.length; i++) {
			copy.fields[i] = this.fields[i];
		}
		return copy;
	}
	
	/**
	 * Calculates the index in the linear array of fields from a (row, col)
	 * pair.
	 * @return the index belonging to the (row,col)-field
	 */
	public int index(int row, int col) {
		return DIM * row + col;
	}
	
	/**
	 * Calculates the row and col of the given index
	 * 
	 * @return {row,col} of given index
	 */
	public int[] rowcol(int index){
		int[] rowcol = new int[2];
		int row, col;
		
		row = (int) Math.floor(index/DIM);
		col = (int) index%DIM;
		
		rowcol[0]=row;
		rowcol[1]=col;
		return rowcol;
	}
	
	/**
	 * Returns true if <code>ix</code> is a valid index of a field on tbe board.
	 * @return <code>true</code> if <code>0 <= ix < DIM*DIM</code>
	 */
	public boolean isField(int ix) {
		return (0 <= ix) && (ix < DIM * DIM);
	}

	/**
	 * Returns true of the (row,col) pair refers to a valid field on the board.
	 * 
	 * @return true if <code>0 <= row < DIM && 0 <= col < DIM</code>
	 */
	public boolean isField(int row, int col) {
		return (0 <= row) && (row < DIM) && (0 <= col) && (col < DIM);
	}

	/**
	 * Returns the content of the field <code>i</code>.
	 * 
	 * @param i
	 *            the number of the field (see NUMBERING)
	 * @return the mark on the field
	 */
	public Mark getField(int i) {
		return fields[i];
	}

	/**
	 * Returns the content of the field referred to by the (row,col) pair.
	 * 
	 * @param row
	 *            the row of the field
	 * @param col
	 *            the column of the field
	 * @return the mark on the field
	 */
	public Mark getField(int row, int col) {
		return fields[index(row, col)];
	}
	
	/**
	 * Returns the number of fields on the board.
	 * 
	 * @return number of fields
	 */
	public int getFieldCount(){
		return DIM*DIM;
	}

	/**
	 * Returns true if the field <code>i</code> is empty.
	 * 
	 * @param i
	 *            the index of the field (see NUMBERING)
	 * @return true if the field is empty
	 */
	public boolean isEmptyField(int i) {
		return getField(i) == Mark.EMP;
	}

	/**
	 * Returns true if the field referred to by the (row,col) pair it empty.
	 * 
	 * @param row
	 *            the row of the field
	 * @param col
	 *            the column of the field
	 * @return true if the field is empty
	 */
	public boolean isEmptyField(int row, int col) {
		return isEmptyField(index(row, col));
	}

	/**
	 * Tests if the whole board is full.
	 * 
	 * @return true if all fields are occupied
	 */
	public boolean isFull() {
		boolean result = true;
		for (int i = 0; i < fields.length; i++) {
			if (isEmptyField(i)) {
				result = false;
			}
		}
		return result;
	}

	/**
	 * Returns true if the game is over. The game is over when there is a winner
	 * or the whole board is full.
	 * 
	 * @return true if the game is over
	 */
	public boolean gameOver() {
		return isFull() || hasWinner();
	}

	/**
	 * Checks whether there is a row which is full and only contains the mark
	 * <code>m</code>.
	 * 
	 * @param m
	 *            the mark of interest
	 * @return true if there is a row controlled by <code>m</code>
	 */
	public boolean hasRow(Mark m) {
		int i = 0;
		boolean hrow = false;
		while (i < DIM && !hrow) {
			int j = 0;
			hrow = true;
			while (j < DIM && hrow) {
				hrow = getField(i, j) == m;
				j++;
			}
			i++;
		}
		return hrow;
	}

	/**
	 * Checks whether there is a column which is full and only contains the mark
	 * <code>m</code>.
	 * 
	 * @param m
	 *            the mark of interest
	 * @return true if there is a column controlled by <code>m</code>
	 */
	public boolean hasColumn(Mark m) {
		int j = 0;
		boolean hcol = false;
		while (j < DIM && !hcol) {
			int i = 0;
			hcol = true;
			while (i < DIM && hcol) {
				hcol = getField(i, j) == m;
				i++;
			}
			j++;
		}
		return hcol;
	}

	/**
	 * Checks whether there is a diagonal which is full and only contains the
	 * mark <code>m</code>.
	 * 
	 * @param m
	 *            the mark of interest
	 * @return true if there is a diagonal controlled by <code>m</code>
	 */
	public boolean hasDiagonal(Mark m) {
		int i = 0;
		boolean hDia1 = true;
		boolean hDia2 = true;
		while (i < DIM && (hDia1 || hDia2)) {
			hDia1 = hDia1 && (getField(i, i) == m);
			hDia2 = hDia2 && (getField(i, DIM - i - 1) == m);
			i++;
		}
		return hDia1 || hDia2;
	}

	/**
	 * Checks if the mark <code>m</code> has won. A mark wins if it controls at
	 * least one row, column or diagonal.
	 * 
	 * @param m
	 *            the mark of interest
	 * @return true if the mark has won
	 */
	public boolean isWinner(Mark m) {
		HashMap<Mark,Integer> counts = countSquares(this);
		ArrayList<Mark> mark = getMarkWithMostFields(counts);
		return isFull() && mark.contains(m) && mark.size() == 1;
	}

	private ArrayList<Mark> getMarkWithMostFields(HashMap<Mark, Integer> counts) {
		ArrayList<Mark> winners = new ArrayList<Mark>();
		int highScore = 0;
		Mark[] marks = {Mark.RED, Mark.YEL, Mark.GRN, Mark.BLU};
		for(Mark m : marks){
			int score;			
			if(counts.containsKey(m)){
				score = counts.get(m);
			}else{
				score = 0;
			}
			if(score > highScore){
				highScore = score;
			}
			if(score == highScore){
				winners.add(m);
			}
		}
		return winners;
	}

	/**
	 * Returns true if the game has a winner. This is the case when one of the
	 * marks controls at least one row, column or diagonal.
	 * 
	 * @return true if the board has a winner.
	 */
	public boolean hasWinner() {
		return isWinner(Mark.RED) || isWinner(Mark.YEL) || isWinner(Mark.GRN) || isWinner(Mark.BLU);
	}

	/**
	 * Returns a String representation of this board. In addition to the current
	 * situation, the String also shows the numbering of the fields.
	 * 
	 * @return the game situation as String
	 */
	public String toString() {
		
		// Variables
		int numVakjes = DIM*DIM-1;
		int numStreepjes = String.valueOf(numVakjes).length() + 2;
		String streepjes = "";
		String[] numberMap = new String[DIM*2-1];
		
		// Generate line delimiter
		for(int streep=0; streep<numStreepjes; streep++){
			streepjes = streepjes+"-";
		}
		
		// Generate rows
		for(int row=0; row<DIM; row++){
			String mapLine = "";
			String mapDelim = "";
			
			// Generate one row
			for(int vak=0; vak<DIM; vak++){
				String lineDelim = "";
				String plusDelim = "";
				int vakje = vak+(DIM*row);
				
				// Add leading zeroes
				String format = String.format("%%0%dd", numStreepjes-2);
				String vakLeading = String.format(format, vakje);
				
				if(vak != 0){
					lineDelim = "|"; 
					plusDelim = "+";
				}
				mapLine = mapLine+lineDelim+" "+vakLeading+" ";
				mapDelim = mapDelim+plusDelim+streepjes;
			}
			
			if(row != 0){
				numberMap[row*2-1] = mapDelim;
			}
			numberMap[row*2] = mapLine;
		}
		
		String s = "";
		for (int i = 0; i < DIM; i++) {
			String row = "";
			for (int j = 0; j < DIM; j++) {
				String field = getField(i,j).toString();
				if(field == "EMP"){
					field = "   ";
				}
				row = row + " " + field + " ";
				if (j < DIM - 1) {
					row = row + "|";
				}
			}
			s = s + row + DELIM + numberMap[i * 2];
			if (i < DIM - 1) {
				String line="";
				for(int k=1; k<=DIM;k++){
					if(line == ""){
						line = LINE;
					}else{
						line = line+"+"+LINE;
					}
				}
				s = s + "\n" + line + DELIM + numberMap[i * 2 + 1] + "\n";
			}
		}
		
		return s;
	}

	/*		COMMANDS		*/
	/**
	 * Empties all fields of this board (i.e., let them refer to the value
	 * Mark.EMP).
	 */
	public void reset() {
		for (int i = 0; i < fields.length; i++) {
			setField(i, Mark.EMP);
		}
		int topLeft = ((DIM/2)-1)+(DIM/2-1)*DIM;
		int topRight = ((DIM/2))+(DIM/2-1)*DIM;
		int btmLeft = ((DIM/2)-1)+(DIM/2)*DIM;
		int btmRight = ((DIM/2))+(DIM/2)*DIM;
		// Set initial state
		int[] indices = {topLeft,topRight,btmLeft,btmRight};
		Mark[] marks = {Mark.RED, Mark.YEL, Mark.BLU, Mark.GRN};
		for(int i=0; i<4; i++){
			setFieldNoChecks(indices[i], marks[i]);
		}
	}

	/**
	 * Sets the content of field <code>i</code> to the mark <code>m</code>.
	 * 
	 * @param i
	 *            the field number (see NUMBERING)
	 * @param m
	 *            the mark to be placed
	 */
	public void setField(int i, Mark m) {
//		ArrayList<Integer> hitMoves = getHittableMoves(getSurroundingFields(this),m);
//		if(!(hitMoves.size() == 0)){
//			// We have a hit. Check which fields have been affected.
//		}
		
		if(m != Mark.EMP){
			ArrayList<Integer> affectedFields = new ArrayList<Integer>();
			affectedFields.addAll(getHitRow(i,m));
			affectedFields.addAll(getHitCol(i,m));
			affectedFields.addAll(getHitDiagTLDR(i,m));
			affectedFields.addAll(getHitDiagTRDL(i,m));
			
			if(affectedFields.size() != 0){
				testBoard.debug("Affected Fields: "+affectedFields.toString());
				for(int field : affectedFields){
					setFieldNoChecks(field,m);
				}
			}
		}
		
		fields[i] = m;
	}
	
	public void setFieldNoChecks(int i, Mark m){
		fields[i] = m;
	}

	/**
	 * Sets the content of the field represented by the (row,col) pair to the
	 * mark <code>m</code>.
	 * 
	 * @param row
	 *            the field's row
	 * @param col
	 *            the field's column
	 * @param m
	 *            the mark to be placed
	 */
	public void setField(int row, int col, Mark m) {
		setField(index(row, col), m);
	}
	
	/**
	 * Counts the amount of fields each color owns.
	 * 
	 * @param b - The board to count.
	 * 
	 * @return Array of number of squares each color owns: [red, yellow, green, blue]
	 */
	public HashMap<Mark,Integer> countSquares(Board b){
		
		// Initialize the countlist.
		HashMap<Mark,Integer> counts = new HashMap<Mark, Integer>();
		
		for(int i=0; i<64; i++){
			Mark mark = getField(i);
			int count;
			if(counts.containsKey(mark)){
				count = counts.get(mark);
			}else{
				count = 0;
			}
			counts.put(mark, count+1);
		}
		
		return counts;
	}
	
	/**
	 * Checks if the given index is next to an already filled index for the board given.
	 * This indicates a valid move if no hits can be made.
	 * @param index - The move to make
	 * @param b	- The board to check the move on
	 * @return - true if valid move, false if not valid.
	 */
	public boolean isNextToFilledField(int index, Board b){
		
		if(!b.isEmptyField(index)){
			return false;
		}
		
		int[] rowcol = rowcol(index);
		int[][] surroundingFields = new int[8][2];
		surroundingFields[0][0] = rowcol[0]-1;
		surroundingFields[0][1] = rowcol[1]-1;
		surroundingFields[1][0] = rowcol[0]-1;
		surroundingFields[1][1] = rowcol[1];
		surroundingFields[2][0] = rowcol[0]-1;
		surroundingFields[2][1] = rowcol[1]+1;
		surroundingFields[3][0] = rowcol[0];
		surroundingFields[3][1] = rowcol[1]-1;
		surroundingFields[4][0] = rowcol[0];
		surroundingFields[4][1] = rowcol[1]+1;
		surroundingFields[5][0] = rowcol[0]+1;
		surroundingFields[5][1] = rowcol[1]-1;
		surroundingFields[6][0] = rowcol[0]+1;
		surroundingFields[6][1] = rowcol[1];
		surroundingFields[7][0] = rowcol[0]+1;
		surroundingFields[7][1] = rowcol[1]+1;
		
		boolean[] fieldStatuses = new boolean[8];
		
		int counter = 0;
		boolean filled = false;
		for(int[] field : surroundingFields){
			if(field[0] < 8 && field[0] >= 0 && field[1] < 8 && field[1] >= 0){
				if(!b.isEmptyField(field[0],field[1])){
					filled = true;
				}
			}
			fieldStatuses[counter] = filled;
			counter++;
		}
		
		return filled;
	}
	
	public int[] getSurroundingFields(Board b){
		ArrayList<Integer> validMoves = new ArrayList<Integer>();
		
		for(int i=0; i<DIM*DIM; i++){
			if(isNextToFilledField(i,b)){
				validMoves.add(i);
			}
		}
		
		int[] result = new int[validMoves.size()];
		int counter = 0;
		for(int value : validMoves){
			result[counter] = value;
			counter++;
		}
		
		return result;
	}
	
	public int[] getValidFields(Board b, Mark m){
		ArrayList<Integer> hittableMoves = b.getHittableMoves(b.getSurroundingFields(b), m);
		if(hittableMoves.size() != 0){
			int[] moves = new int[hittableMoves.size()];
			for(int i=0; i<hittableMoves.size(); i++){
				moves[i] = hittableMoves.get(i);
			}
			return moves;
		}else{
			return b.getSurroundingFields(b);
		}
	}
	
	public static String intArrayToString(int[] array){
		String result = "";
		String delim = "";
		
		for(int value : array){
			if(result != ""){
				delim = ", ";
			}
			result = result+delim+String.valueOf(value);
		}
		
		return "["+result+"]";
	}
	
	public ArrayList<Integer> getHitRow(int index, Mark m){
		// Get the row and column of the given field
		int[] indexrc = rowcol(index);
		// Create a new list for the indices
		ArrayList<Integer> rowIndices = new ArrayList<Integer>();
		// And create a list of possible hits to return at the end of the method.
		ArrayList<Integer> affectedFields = new ArrayList<Integer>();
		
		// Get all indices in the row of the field.
		for(int i=0; i<DIM; i++){
			int square = index(indexrc[0],i);
			if(square != index){
				rowIndices.add(square);
			}
		}
		
		// For each field in the row,
		for(int square : rowIndices){
			int[] squarerc = rowcol(square);

			// If we encounter a field that we already own,
			// And it is not the same field, or right next to it.
			if(getField(square) == m && Math.abs(squarerc[1]-indexrc[1]) > 1){
				// Get squares between square and index.
				// Get the start and end indices of the squares in between the square and index so we can loop over them.
				int[] startEndIndices = new int[2];
				if(squarerc[1] < indexrc[1]){
					startEndIndices[0]=squarerc[1];
					startEndIndices[1]=indexrc[1];
				}else{
					startEndIndices[0]=indexrc[1];
					startEndIndices[1]=squarerc[1];
				}
				// Create boolean we can flip if we encounter an empty field
				boolean validAffectedFields = true;
				// Create an arraylist we can add the fields to
				ArrayList<Integer> possiblyAffectedFields = new ArrayList<Integer>();
				// Then loop over the fields in between the start and end index
				for(int i=startEndIndices[0]+1; i<startEndIndices[1]; i++){
					// Get the index of the field
					int possiblyAffectedFieldIndex = index(indexrc[0],i);
					// Add it to the possiblyAffectedFields array
					possiblyAffectedFields.add(possiblyAffectedFieldIndex);
					// Check if the field is empty or equal to our own mark
					if(getField(possiblyAffectedFieldIndex)==Mark.EMP || getField(possiblyAffectedFieldIndex)==m){
						// If it is, flip the boolean, this arraylist is invalid.
						validAffectedFields = false;
					}
				}
				
				// If we concluded the array is valid
				if(validAffectedFields){
					// Add the array to the final array of affected fields.
					affectedFields.addAll(possiblyAffectedFields);
				}
			}
		}
		//testBoard.debug("getRow results:");
		//testBoard.debug(affectedFields.toString());
		return affectedFields;
	}
	
	public ArrayList<Integer> getHitCol(int index, Mark m){
		// Get the row and column of the given field
		int[] indexrc = rowcol(index);
		// Create a new list for the indices
		ArrayList<Integer> colIndices = new ArrayList<Integer>();
		// And create a list of possible hits to return at the end of the method.
		ArrayList<Integer> affectedFields = new ArrayList<Integer>();
		
		// Get all indices in the row of the field.
		for(int i=0; i<DIM; i++){
			int square = index(i,indexrc[1]);
			if(square != index){
				colIndices.add(square);
			}
		}
		
		// For each field in the row,
		for(int square : colIndices){
			int[] squarerc = rowcol(square);

			// If we encounter a field that we already own,
			// And it is not the same field, or right next to it.
			if(getField(square) == m && Math.abs(squarerc[0]-indexrc[0]) > 1){
				// Get squares between square and index.
				// Get the start and end indices of the squares in between the square and index so we can loop over them.
				int[] startEndIndices = new int[2];
				if(squarerc[0] < indexrc[0]){
					startEndIndices[0]=squarerc[0];
					startEndIndices[1]=indexrc[0];
				}else{
					startEndIndices[0]=indexrc[0];
					startEndIndices[1]=squarerc[0];
				}
				// Create boolean we can flip if we encounter an empty field
				boolean validAffectedFields = true;
				// Create an arraylist we can add the fields to
				ArrayList<Integer> possiblyAffectedFields = new ArrayList<Integer>();
				// Then loop over the fields in between the start and end index
				for(int i=startEndIndices[0]+1; i<startEndIndices[1]; i++){
					// Get the index of the field
					int possiblyAffectedFieldIndex = index(i, indexrc[1]);
					// Add it to the possiblyAffectedFields array
					possiblyAffectedFields.add(possiblyAffectedFieldIndex);
					// Check if the field is empty or equal to our own mark
					if(getField(possiblyAffectedFieldIndex)==Mark.EMP || getField(possiblyAffectedFieldIndex)==m){
						// If it is, flip the boolean, this arraylist is invalid.
						validAffectedFields = false;
					}
				}
				
				// If we concluded the array is valid
				if(validAffectedFields){
					// Add the array to the final array of affected fields.
					affectedFields.addAll(possiblyAffectedFields);
				}
			}
		}
		//testBoard.debug("getCol Results:");
		//testBoard.debug(affectedFields.toString());
		return affectedFields;
	}
	
	public ArrayList<Integer> getHitDiagTRDL(int index, Mark m){
		// Get rowcol of index
		int[] indexrc = rowcol(index);
		// Create a new list for the indices
		ArrayList<Integer> diagIndices = new ArrayList<Integer>();
		// And create a list of possible hits to return at the end of the method.
		ArrayList<Integer> possibleHits = new ArrayList<Integer>();
		ArrayList<Integer> affectedFields = new ArrayList<Integer>();
		
		int r=indexrc[0];
		int c=indexrc[1];
		// Get all indices in the diagonal of the field.
		while(r >= 0 && c < DIM){
			if(index(r,c) != index){
				diagIndices.add(index(r,c));
			}
			r--;
			c++;
		}
		r=indexrc[0];
		c=indexrc[1];
		while(r < DIM && c >= 0){
			if(index(r,c) != index){
				diagIndices.add(index(r,c));
			}
			r++;
			c--;
		}
		Collections.sort(diagIndices);
		
		// For each field in the row,
		for(int square : diagIndices){
			int[] squarerc = rowcol(square);
			int arrayIndex = diagIndices.indexOf(square);
			
			// If we encounter a field that we already own,
			// And it is not the same field, or right next to it.
			if(getField(square) == m && Math.abs(squarerc[1]-indexrc[1]) > 1){
				// Get squares between square and index.
				// Get the start and end indices of the squares in between the square and index so we can loop over them.
				int[] startEndIndices = new int[2];
				if(squarerc[1] < indexrc[1]){
					startEndIndices[0]=squarerc[1];
					startEndIndices[1]=indexrc[1];
				}else{
					startEndIndices[0]=indexrc[1];
					startEndIndices[1]=squarerc[1];
				}
				// Create boolean we can flip if we encounter an empty field
				boolean validAffectedFields = true;
				// Create an arraylist we can add the fields to
				ArrayList<Integer> possiblyAffectedFields = new ArrayList<Integer>();
				// Then loop over the fields in between the start and end index
				// If the square is located left of the index, we need to loop through it backwards
				if(squarerc[1]<indexrc[1]){
					// Loop backwards
					for(int i=arrayIndex-1; i>arrayIndex-(startEndIndices[1]-startEndIndices[0]); i--){
						// Get the index of the field
						int possiblyAffectedFieldIndex = diagIndices.get(i);
						// Add it to the possiblyAffectedFields array
						possiblyAffectedFields.add(possiblyAffectedFieldIndex);
						// Check if the field is empty or equal to our own mark
						if(getField(possiblyAffectedFieldIndex)==Mark.EMP || getField(possiblyAffectedFieldIndex)==m){
							// If it is, flip the boolean, this arraylist is invalid.
							validAffectedFields = false;
						}
					}	
				// Otherwise, if the square is located right of the index, we need to loop normally.
				}else{
					// Loop forwards
					for(int i=arrayIndex+1; i<arrayIndex+(startEndIndices[1]-startEndIndices[0]); i++){
						// Get the index of the field
						int possiblyAffectedFieldIndex = diagIndices.get(i);
						// Add it to the possiblyAffectedFields array
						possiblyAffectedFields.add(possiblyAffectedFieldIndex);
						// Check if the field is empty or equal to our own mark
						if(getField(possiblyAffectedFieldIndex)==Mark.EMP || getField(possiblyAffectedFieldIndex)==m){
							// If it is, flip the boolean, this arraylist is invalid.
							validAffectedFields = false;
						}
					}
				}
				
				// If we concluded the array is valid
				if(validAffectedFields){
					// Add the array to the final array of affected fields.
					affectedFields.addAll(possiblyAffectedFields);
				}
			}
		}
		//testBoard.debug("getDiagTRDL results:");
		//testBoard.debug(affectedFields.toString());
		return affectedFields;
	}
	
	public ArrayList<Integer> getHitDiagTLDR(int index, Mark m){
		// Get rowcol of index
		int[] indexrc = rowcol(index);
		// Create a new list for the indices
		ArrayList<Integer> diagIndices = new ArrayList<Integer>();
		// And create a list of possible hits to return at the end of the method.
		ArrayList<Integer> possibleHits = new ArrayList<Integer>();
		ArrayList<Integer> affectedFields = new ArrayList<Integer>();
		
		int r=indexrc[0];
		int c=indexrc[1];
		// Get all indices in the diagonal of the field.
		while(r >= 0 && c >= 0){
			if(index(r,c) != index){
				diagIndices.add(index(r,c));
			}
			r--;
			c--;
		}
		r=indexrc[0];
		c=indexrc[1];
		while(r < DIM && c < DIM){
			if(index(r,c) != index){
				diagIndices.add(index(r,c));
			}
			r++;
			c++;
		}
		Collections.sort(diagIndices);
		
		// For each field in the row,
		for(int square : diagIndices){
			int[] squarerc = rowcol(square);
			int arrayIndex = diagIndices.indexOf(square);
			
			// If we encounter a field that we already own,
			// And it is not the same field, or right next to it.
			if(getField(square) == m && Math.abs(squarerc[1]-indexrc[1]) > 1){
				// Get squares between square and index.
				// Get the start and end indices of the squares in between the square and index so we can loop over them.
				int[] startEndIndices = new int[2];
				if(squarerc[1] < indexrc[1]){
					startEndIndices[0]=squarerc[1];
					startEndIndices[1]=indexrc[1];
				}else{
					startEndIndices[0]=indexrc[1];
					startEndIndices[1]=squarerc[1];
				}
				// Create boolean we can flip if we encounter an empty field
				boolean validAffectedFields = true;
				// Create an arraylist we can add the fields to
				ArrayList<Integer> possiblyAffectedFields = new ArrayList<Integer>();
				// Then loop over the fields in between the start and end index
				// If the square is located left of the index, we need to loop through it backwards
				if(squarerc[1]>indexrc[1]){
					// Loop backwards
					for(int i=arrayIndex-1; i>arrayIndex-(startEndIndices[1]-startEndIndices[0]); i--){
						// Get the index of the field
						int possiblyAffectedFieldIndex = diagIndices.get(i);
						// Add it to the possiblyAffectedFields array
						possiblyAffectedFields.add(possiblyAffectedFieldIndex);
						// Check if the field is empty or equal to our own mark
						if(getField(possiblyAffectedFieldIndex)==Mark.EMP || getField(possiblyAffectedFieldIndex)==m){
							// If it is, flip the boolean, this arraylist is invalid.
							validAffectedFields = false;
						}
					}	
				// Otherwise, if the square is located right of the index, we need to loop normally.
				}else{
					// Loop forwards
					for(int i=arrayIndex+1; i<arrayIndex+(startEndIndices[1]-startEndIndices[0]); i++){
						// Get the index of the field
						int possiblyAffectedFieldIndex = diagIndices.get(i);
						// Add it to the possiblyAffectedFields array
						possiblyAffectedFields.add(possiblyAffectedFieldIndex);
						// Check if the field is empty or equal to our own mark
						if(getField(possiblyAffectedFieldIndex)==Mark.EMP || getField(possiblyAffectedFieldIndex)==m){
							// If it is, flip the boolean, this arraylist is invalid.
							validAffectedFields = false;
						}
					}
				}
				
				// If we concluded the array is valid
				if(validAffectedFields){
					// Add the array to the final array of affected fields.
					affectedFields.addAll(possiblyAffectedFields);
				}
			}
		}
		//testBoard.debug("getDiagTRDL results:");
		//testBoard.debug(affectedFields.toString());
		return affectedFields;
	}
	
	public boolean canHitRow(int index, Mark m){
		ArrayList<Integer> hitMoves = getHitRow(index,m);
		return !(hitMoves.size() ==0);
	}
	
	public boolean canHitCol(int index, Mark m){
		ArrayList<Integer> hitMoves = getHitCol(index,m);
		return !(hitMoves.size() ==0);
	}
	
	public boolean canHitDiagonalLeftRight(int index, Mark m){
		ArrayList<Integer> hitMoves = getHitDiagTLDR(index,m);
		return !(hitMoves.size() ==0);
	}
	
	public boolean canHitDiagonalRightLeft(int index, Mark m){
		ArrayList<Integer> hitMoves = getHitDiagTRDL(index,m);
		return !(hitMoves.size() ==0);
	}
	
	public ArrayList<Integer> getHittableMoves(int[] surroundingFields, Mark m){
		
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		for(int move : surroundingFields){
			boolean testRow = canHitRow(move, m);
			boolean testCol = canHitCol(move, m);
			boolean testDiag1 = canHitDiagonalLeftRight(move, m);
			boolean testDiag2 = canHitDiagonalRightLeft(move, m);
			if((testRow || testCol || testDiag1 || testDiag2) && isEmptyField(move)){
				result.add(move);
			}
		}
		return result;
	}
	
	public int getPlayerCount(){
		return this.amountPlayers;
	}
}