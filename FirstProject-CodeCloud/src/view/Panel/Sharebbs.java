package view.Panel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import view.MainView;

public class Sharebbs extends JPanel {
	
	MainView F;
	
	public Sharebbs(MainView f) {
		F=f;
		
		setLayout(null);
		setBackground(Color.black);
		setBounds(50,50,300,300);
		
		JButton btn = new JButton();
		btn.setBounds(10, 10, 40, 40);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				F.changePanel(3);
			}
		});
		
		add(btn);
		setVisible(true);
		
	}
}
