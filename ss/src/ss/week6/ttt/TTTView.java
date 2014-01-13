package ss.week6.ttt;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TTTView extends Frame implements Observer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton Button;
	public JButton Button2;
	public JButton Button3;
	public JButton Button4;
	public JButton Button5;
	public JButton Button6;
	public JButton Button7;
	public JButton Button8;
	public JButton Button9;
	public JButton Button10;
	public  JButton[] button;
	public  JButton[] buttons;
	public static Label label;
	public static JFrame frame;
	private TTTController controller;
	
	public void update(Observable observer, Object object) {
		Game game = (Game) observer;
		Board board = game.getBoard();
		if(board.gameOver()){
			for(int i=0; i<9; i++){
				buttons[i].setEnabled(false);
			}
			buttons[9].setEnabled(true);
			if (board.hasWinner()){
				if(board.isWinner(Mark.XX)){
					label.setText("X has won");
				}
				else
				{
					label.setText("O has won");
				}
			}
			else
			{
				label.setText("Draw");
			}
		}
		else
		{
			for(int i=0; i<9; i++){
				if(!board.isEmptyField(i)){
					buttons[i].setEnabled(false);
				}
				else
				{
					buttons[i].setEnabled(true);
				}
			}
			if(game.getCurrent()==Mark.XX){
				label.setText("X's turn");
			}
			else
			{
				label.setText("O's turn");
			}
		}
	}
	
	public TTTView(Game game){
		JFrame frame = new JFrame();
		JButton Button = new JButton();
		JButton Button2 = new JButton();
		JButton Button3 = new JButton();
		JButton Button4 = new JButton();
		JButton Button5 = new JButton();
		JButton Button6 = new JButton();
		JButton Button7 = new JButton();
		JButton Button8 = new JButton();
		JButton Button9 = new JButton();
		JButton Button10 = new JButton();
		buttons = new JButton[] {Button, Button2, Button3, Button4, Button5, Button6, Button7, Button8, Button9, Button10};
		FlowLayout layout = new FlowLayout();
		frame.setLayout(layout);
		frame.setSize(150, 175);
		for(int i=0; i<9; i++){
			buttons[i].setText(" ");
			buttons[i].setSize(10, 10);
			frame.add(buttons[i]);
			buttons[i].setVisible(true);
		}
		buttons[9].setEnabled(false);
		label = new Label();
		frame.add(label);
		frame.add(Button10);
		frame.setVisible(true);
		game.addObserver(this);
		controller = new TTTController(buttons ,game);
	}
	
	private class TTTController implements ActionListener{
		private Game model;
		
		public TTTController (JButton[] Buttons, Game model){
			this.model = model;
			for(int i=0; i<9; i++){
				Buttons[i].addActionListener(this);
			}
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			for(int i=0; i<9; i++){
				if(model.getBoard().isEmptyField(i)){
					model.takeTurn(i);
				}
			}
		}
	}
	
	public void vulArray(JButton[] button){
		this.button=button;
	}
	
	public static void main(String[] args){
		Game game = new Game();
		TTTView view = new TTTView(game);
	}
}
