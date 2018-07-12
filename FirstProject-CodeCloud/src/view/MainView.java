package view;

import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import view.Panel.QAbbs;
import view.Panel.Sharebbs;
import view.Panel.Selfbbs;

public class MainView extends JFrame {

	private CardLayout cards = new CardLayout();
	JPanel mainPanel;
	public MainView() {
		setBounds(50, 50, 1200, 450);
		setLayout(null);
		
		mainPanel = new JPanel(cards);
		
		
		mainPanel.add("Singlebbs", new Selfbbs(this));
		mainPanel.add("Sharebbs", new Sharebbs(this));
		mainPanel.add("Q&Abbs", new QAbbs(this));
		cards.show(mainPanel, "Singlebbs");
		
		mainPanel.setBounds(300,0,650,450);
		
		
		add(mainPanel);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public void changePanel(int select) {
		if(select == 1) {
			cards.show(mainPanel, "Singlebbs");
		}else if(select == 2) {
			cards.show(mainPanel, "Sharebbs");
		}else if(select == 3) {
			cards.show(mainPanel, "Q&Abbs");
		}
	}
}
