package ss.week6.vote;

import java.awt.Choice;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;

public class PartyChoiceListener implements ItemListener{
	private Choice choicemenu;
	private Label label;
	private JButton button;
	
	public PartyChoiceListener(Choice choicemenu, Label label, JButton button) {
		this.choicemenu = choicemenu;
		this.label = label;
		this.button = button;
	}

	public void itemStateChanged(ItemEvent e) {
		int choice = choicemenu.getSelectedIndex();
		if (e.getStateChange() == e.SELECTED && choice==0){
			label.setText("Choose a party");
			button.setEnabled(false);
		}
		else
		{
			label.setText("Change selection or press OK");
			button.setEnabled(true);			
		}
	}
	

}
