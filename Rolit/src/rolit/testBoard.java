package rolit;

import java.util.ArrayList;

public class testBoard {
	
	public static void debug(String string){
		System.out.println(string);
	}
	
	public static void fillBoard(Board board){
		while(!board.isFull()){
			int field;
			do{
				field = (int) Math.floor(Math.random()*board.getFieldCount());
			}while(!board.isNextToFilledField(field, board));
			Mark mark;
			int numPlayers = board.getPlayerCount();
			switch((int) Math.floor(Math.random()*numPlayers+1)){
				case 1:
					mark = Mark.RED;
					break;
				case 2:
					mark = Mark.YEL;
					break;
				case 3:
					mark = Mark.BLU;
					break;
				case 4:
					mark = Mark.GRN;
					break;
				default:
					mark = Mark.EMP;
					break;
			}
			board.setField(field, mark);
			debug(board.toString());
			debug("\n\n");
		}
	}
	
	public static void testRowColumn(int numPlayers){
		debug("Start row & column tests:");
		Board b = new Board(numPlayers);
		int[][] tests = {{0,0,0},{11,1,3},{63,7,7},{42,5,2}};
		
		for(int[] test : tests){
			int[] result = b.rowcol(test[0]);
			debug("Tested "+test[0]+": Result: "+result[0]+","+result[1]+" - Expected: "+test[1]+","+test[2]);
		}
	}
	
	public static void testValidMoves(Board b){
		int[] validMoves = b.getSurroundingFields(b);
		for(int move : validMoves){
			b.setField(move, Mark.YEL);
		}
		debug(b.toString());
		debug("\n");
		debug("Possible Moves: "+b.intArrayToString(validMoves));
		debug("\n\n");
	}
	
	public static void testRow(Board b, int i, Mark m){
		b.getHitRow(i, m);
	}
	public static void testCol(Board b, int i, Mark m){
		b.getHitCol(i, m);
	}
	public static void testDiagTRDL(Board b, int i, Mark m){
		b.getHitDiagTRDL(i, m);
	}
	public static void testDiagTLDR(Board b, int i, Mark m){
		b.getHitDiagTLDR(i, m);
	}
	
	public static void main(String[] args){
		int numPlayers = 4;
		Board board = new Board(numPlayers);
		
		debug(board.toString());
		//fillBoard(board);
		
		testRow(board, 29, Mark.RED);
		testCol(board, 43, Mark.RED);
		testDiagTRDL(board, 21, Mark.BLU);
		testDiagTRDL(board, 42, Mark.YEL);
		testDiagTLDR(board, 18, Mark.GRN);
		testDiagTLDR(board, 45, Mark.RED);
		
		//testRowColumn(numPlayers);
		//testValidMoves(board);
		//board.setField(43, Mark.BLU);
//		if(board.canHitDiagonalLeftRight(45, Mark.RED)){
//			debug("CAN HIT!");
//		}else{
//			debug("CANNOT HIT!");
//		}
		
		//Mark mark = Mark.RED;
		
		//ArrayList<Integer> hittableMoves = board.getHittableMoves(board.getSurroundingFields(board), mark);
		//debug("\nPossible moves for color "+mark.toString()+":");
		//debug(board.intArrayToString(board.getValidFields(board, mark)));
		
		//if(board.canHitDiagonalRightLeft(20, Mark.GRN)){
		//	debug("CAN HIT!");
		//}else{
		//	debug("CANNOT HIT!");
		//}
		
	}
	
}
