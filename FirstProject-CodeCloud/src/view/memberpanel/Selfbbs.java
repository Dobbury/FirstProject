package view.memberpanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import view.MemberMainView;

public class Selfbbs extends JPanel{
	
	MemberMainView F;
	
	public Selfbbs(MemberMainView f) {
		F=f;
		
		setLayout(null);
		setBackground(Color.BLUE);
		setBounds(50,50,300,300);

		
		setVisible(true);
		
	}
}
