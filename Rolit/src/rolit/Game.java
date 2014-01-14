package rolit;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class for maintaining the Rolit game.
 * 
 * @author Sophie Lathouwers & Kevin Alberts
 * @version $Revision: 1.0 $
 */
public class Game {

    // -- Instance variables -----------------------------------------

    public static final int MAX_PLAYERS = 4;
    private Board board;
    /**
     * The 2/3/4 players of the game.
     */
    private ArrayList<Player> playersList;
    private Player[] playersArray;
    private int numPlayers; 
    /**
     * Index of the current player.
     */
    private int current;

    // -- Constructors -----------------------------------------------
    /**
     * Creates a new Game object.
     * 
     * @param s0 - the first player
     * @param s1 - the second player
     */
    public Game(ArrayList<Player> players) {
    	this.numPlayers = players.size();
    	this.playersList = players;

    	this.playersArray = new Player[players.size()];
    	for(int i=0; i<players.size(); i++){
    		playersArray[i] = players.get(i);
    	}

        board = new Board(this.numPlayers);
        current = 0;
    }

    // -- Commands ---------------------------------------------------

    /**
     * Starts the Tic Tac Toe game. <br>
     * Asks after each ended game if the user want to continue. Continues until
     * the user does not want to play anymore.
     */
    public void start() {
        boolean oneMoreTime = true;
        while (oneMoreTime) {
            reset();
            play();
            oneMoreTime = readBoolean("\n> Play another time? (y/n)?", "y", "n");
        }
    }

    /**
     * Prints a question which can be answered by yes (true) or no (false).
     * After prompting the question on standard out, this method reads a String
     * from standard in and compares it to the parameters for yes and no. If the
     * user inputs a different value, the prompt is repeated and te method reads
     * input again.
     * 
     * @parom prompt the question to print
     * @param yes
     *            the String corresponding to a yes answer
     * @param no
     *            the String corresponding to a no answer
     * @return true is the yes answer is typed, false if the no answer is typed
     */
    private boolean readBoolean(String prompt, String yes, String no) {
        String answer;
        do {
            System.out.print(prompt);
            Scanner in = new Scanner(System.in);
            answer = in.hasNextLine() ? in.nextLine() : null;
        } while (answer == null || (!answer.equals(yes) && !answer.equals(no)));
        return answer.equals(yes);
    }

    /**
     * Resets the game. <br>
     * The board is emptied and player[0] becomes the current player.
     */
    private void reset() {
        current = 0;
        board.reset();
    }

    /**
     * Plays the Tic Tac Toe game. <br>
     * First the (still empty) board is shown. Then the game is played until it
     * is over. Players can make a move one after the other. After each move,
     * the changed game situation is printed.
     */
    private void play() {
        update();
        while (!board.gameOver()) {
        	playersArray[current].makeMove(board);
            current = (current + 1) % numPlayers;
            update();
        }
        printResult();
    }

    /**
     * Prints the game situation.
     */
    private void update() {
        System.out.println("\ncurrent game situation: \n\n" + board.toString()
                + "\n");
        int[] possibleMoves = board.getValidFields(board, this.playersArray[this.current].getMark());
        System.out.println("Possible moves: "+Board.intArrayToString(possibleMoves));
    }

    /**
     * Prints the result of the last game. <br>
     */
    private void printResult() {
        if (board.hasWinner()) {
            Player winner = board.isWinner(playersArray[0].getMark()) ? playersArray[0]
                    : playersArray[1];
            System.out.println("Player " + winner.getName() + " ("
                    + winner.getMark().toString() + ") has won!");
        } else {
            System.out.println("Draw. There is no winner!");
        }
    }
}
