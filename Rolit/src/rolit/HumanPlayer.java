package rolit;

import java.util.Scanner;

/**
 * Class for maintaining a human player in Rolit.
 * 
 * @author Sophie Lathouwers & Kevin Alberts
 * @version $Revision: 1.0 $
 */
public class HumanPlayer extends Player {

    // -- Constructors -----------------------------------------------
    /**
     * Creates a new human player object.
     * 
     */
    public HumanPlayer(String name, Mark mark) {
        super(name, mark);
    }

    // -- Commands ---------------------------------------------------
    /**
     * Asks the user to input the field where to place the next mark. This is
     * done using the standard input/output. \
     * 
     * @param board - the game board
     * @return the player's chosen field
     */
    public int determineMove(Board board) {
        String prompt = "> " + getName() + " (" + getMark().toString() + ")"
                + ", what is your choice? ";
        int choice = readInt(prompt);
        boolean valid = board.isField(choice) && isMoveValid(choice, board);
        while (!valid) {
            System.out.println("ERROR: field " + choice
                    + " is no valid choice.");
            choice = readInt(prompt);
            valid = board.isField(choice) && board.isEmptyField(choice);
        }
        return choice;
    }
    
    public boolean isMoveValid(int choice, Board b){
    	int[] fields = b.getValidFields(b, this.getMark());
    	for(int i : fields){
    		if(i == choice){
    			return true;
    		}
    	}
    	return false;
    }

    /**
     * Writes a prompt to standard out and tries to read an int value from
     * standard in. This is repeated until an int value is entered.
     * 
     * @param prompt - the question to prompt the user
     * @return the first int value which is entered by the user
     */
    private int readInt(String prompt) {
        int value = 0;
        boolean intRead = false;
        do {
            System.out.print(prompt);
            String line = (new Scanner(System.in)).nextLine();
            Scanner scannerLine = new Scanner(line);
            if (scannerLine.hasNextInt()) {
                intRead = true;
                value = scannerLine.nextInt();
            }
        } while (!intRead);
        	
        return value;
    }

}