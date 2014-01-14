package rolit;

import rolit.Board;
import rolit.Mark;

/**
 * Abstract class for keeping a player in the Rolit game.
 * 
 * 
 * @author Sophie Lathouwers & Kevin Alberts
 * @version $Revision: 1.0 $
 */
public abstract class Player {

    // -- Instance variables -----------------------------------------

    private String name;
    private Mark mark;

    // -- Constructors -----------------------------------------------

    /**
     * Creates a new Player object.
     * 
     */
    public Player(String theName, Mark theMark) {
        this.name = theName;
        this.mark = theMark;
    }

    // -- Queries ----------------------------------------------------

    /**
     * Returns the name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the mark of the player.
     */
    public Mark getMark() {
        return mark;
    }

    /**
     * Determines the field for the next move.
     * 
     * @param bord - the current game board
     * @return the player's choice
     */
    public abstract int determineMove(Board board);

    // -- Commands ---------------------------------------------------
    /**
     * Makes a move on the board. <br>
     * 
     * @param bord- the current board
     */
    public void makeMove(Board board) {
        int keuze = determineMove(board);
        board.setField(keuze, getMark());
    }

}
