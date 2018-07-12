package view.memberpanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import view.MemberMainView;

public class QAbbs extends JPanel {
	
	MemberMainView F;
	
	public QAbbs(MemberMainView f) {
		F=f;
		
		setLayout(null);
		setBackground(Color.PINK);
		setBounds(50,50,300,300);
		
	
		setVisible(true);
		
	}
}
