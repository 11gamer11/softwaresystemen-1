package ss.week6.vote;

import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;

public class VoteFrame extends Frame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static Choice choicemenu = new Choice();
	public static Label label;
	public static JButton button;
	public static ActionListener action;
	public static ItemListener item;
	public static VoteFrame frame;
/*
	public void itemStateChanged(ItemEvent e) {
		/*int choice = choicemenu.getSelectedIndex();
		if (e.getStateChange() == e.SELECTED && choice==0){
			label.setText("Choose a party");
			button.setEnabled(false);
		}
		else
		{
			label.setText("Change selection or press OK");
			button.setEnabled(true);			
		}
	}*/
/*
	public void actionPerformed(ActionEvent action) {
		/*int choice = choicemenu.getSelectedIndex();
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
	} */
	private static class OKButtonListener implements ActionListener{
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
	
	private static class PartyChoiceListener implements ItemListener{
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
	
	public static void main(String[] args){
		label = new Label();
		choicemenu = new Choice();
		button = new JButton();
		frame = new VoteFrame();
		label.setText("Choose a party");
		choicemenu.add("Choose a party");
		choicemenu.add("CDA");
		choicemenu.add("D66");
		choicemenu.add("GroenLinks");
		choicemenu.add("PVDA");
		choicemenu.add("PVV");
		choicemenu.select(0);
		button.setText("OK");
		button.setEnabled(false);
		button.addActionListener(new ActionListener(){
			@Override
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

		});
		frame.add(label);
		frame.add(button);
		frame.add(choicemenu);
		frame.setLayout(new FlowLayout());
		frame.setVisible(true);
		choicemenu.addItemListener(new ItemListener (){
			@Override
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
		});
	}
}
