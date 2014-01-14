package rolit;

/**
 * Represents a mark in the Tic Tac Toe game. There are five possible values:
 * Mark.RED, Mark.GREEN, Mark.YELLOW, Mark.BLUE and Mark.EMPTY.
 * 
 * @author Sophie Lathouwers & Kevin Alberts
 * @version $Revision: 1.0 $
 */
public enum Mark {
	
	EMP, RED, YEL, GRN, BLU;

	/**
	 * Returns the next player based on the mark given.
	 * 
	 * @return the other mark is this mark is not EMPTY or EMPTY
	 */
	public Mark next(Mark mark) {
		Mark next;
		switch(mark){
			case RED:
				next = YEL;
				break;
			case YEL:
				next = GRN;
				break;
			case GRN:
				next = BLU;
				break;
			case BLU:
				next = RED;
				break;
			case EMP:
			default:
				next = EMP;
				break;
		}
		return next;
	}
}