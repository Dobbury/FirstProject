package view.adminpanel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AdminSharebbs extends JPanel {

	private CardLayout cards = new CardLayout();
	JPanel mainPanel;

	// 디테일
	AdSharebbsDetail detail;

	public AdminSharebbs() {
		setLayout(null);
		setBounds(0, 0, 1000, 800);
		setBackground(Color.black);
		mainPanel = new JPanel(cards);

//		mainPanel.add("AdSharedbbsList", new AdSharebbsList(this));
//		mainPanel.add("AdSharedbbsDetail", new AdSharebbsDetail());

		mainPanel.setBounds(0, 0, 1000, 800);
		add(mainPanel);
		setVisible(true);
	}

}
