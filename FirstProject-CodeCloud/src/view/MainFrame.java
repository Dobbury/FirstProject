package view;

import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import view.Panel.QAbbsPanel;
import view.Panel.SharebbsPanel;
import view.Panel.SinglebbsPanel;

public class MainFrame extends JFrame {

	private CardLayout cards = new CardLayout();
	JPanel mainPanel;
	public MainFrame() {
		setBounds(50, 50, 950, 450);
		setLayout(null);
		
		mainPanel = new JPanel(cards);
		
		mainPanel.add("Singlebbs", new SinglebbsPanel(this));
		mainPanel.add("Sharebbs", new SharebbsPanel(this));
		mainPanel.add("Q&Abbs", new QAbbsPanel(this));
		cards.show(mainPanel, "Singlebbs");
		
		mainPanel.setBounds(50,50,300,300);
		
		
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
