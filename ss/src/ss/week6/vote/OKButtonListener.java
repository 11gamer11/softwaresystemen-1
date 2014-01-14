package ss.week6.vote;

import java.awt.Choice;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import javax.swing.JButton;

public class OKButtonListener implements ActionListener{
	private Choice choicemenu;
	private Label label;
	private JButton button;
	
	public OKButtonListener(Choice choicemenu, Label label, JButton button) {
		this.choicemenu = choicemenu;
		this.label = label;
		this.button = button;
	}
	
	public void actionPerformed(ActionEvent action) {
		int choice = choicemenu.getSelectedIndex();
		System.out.println(choice);
		if (choice!=0){	
			choicemenu.removeAll();
			label.setText("Choose a party");
			button.setEnabled(false);
			choicemenu.add("Choose a party");
			choicemenu.add("CDA");
			choicemenu.add("D66");
			choicemenu.add("GroenLinks");
			choicemenu.add("PVDA");
			choicemenu.add("PVV");
			choicemenu.select(0);
		}
	}

}
