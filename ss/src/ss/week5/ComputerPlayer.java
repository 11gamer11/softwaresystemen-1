package ss.week5;

public class ComputerPlayer extends Player {

	Mark mark;
	Strategy strategy;
	String playerName;
	
	public ComputerPlayer(Mark mark, Strategy strategy) {
		super(strategy.getName()+"-"+mark.name(), mark);
		this.mark = mark;
		this.strategy = strategy;
		this.playerName = strategy.getName()+"-"+mark.name();
	}
	
	public ComputerPlayer(Mark mark) {
		super("Naive-"+mark.name(), mark);
		this.mark = mark;
		this.strategy = new NaiveStrategy();
	}

	public int determineMove(Board board) {
		return this.strategy.determineMove(board, this.mark);
	}

}
