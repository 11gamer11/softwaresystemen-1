package ss.week5;

/**
 * Represents a mark in the Tic Tac Toe game. There three possible values:
 * Mark.XX, Mark.OO and Mark.EMPTY.
 * Module 2 lab assignment
 * 
 * @author Theo Ruys
 * @version $Revision: 1.4 $
 */
public enum Mark {
    
    EMPTY, XXXXX, OOOOO;

    /*@
       ensures this == Mark.XXXXX ==> \result == Mark.OOOOO;
       ensures this == Mark.OOOOO ==> \result == Mark.XXXXX;
       ensures this == Mark.EMPTY ==> \result == Mark.EMPTY;
     */
    /**
     * Returns the other mark.
     * 
     * @return the other mark is this mark is not EMPTY or EMPTY
     */
    public Mark other() {
        if (this == XXXXX) {
            return OOOOO;
        } else if (this == OOOOO) {
            return XXXXX;
        } else {
            return EMPTY;
        }
    }
}
