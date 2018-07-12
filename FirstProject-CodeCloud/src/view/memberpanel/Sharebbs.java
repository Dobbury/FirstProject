package view.memberpanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import view.MemberMainView;

public class Sharebbs extends JPanel {
	
	MemberMainView F;
	
	public Sharebbs(MemberMainView f) {
		F=f;
		
		setLayout(null);
		setBackground(Color.black);
		setBounds(50,50,300,300);
		
	
		
		setVisible(true);
		
	}
}
