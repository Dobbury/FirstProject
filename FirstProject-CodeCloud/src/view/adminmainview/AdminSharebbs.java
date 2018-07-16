package view.adminmainview;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;

import dto.BBSDto;

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


		mainPanel.add("AdSharedbbsList", new AdSharebbsList(this));
//		mainPanel.add("AdSharedbbsDetail", new AdSharebbsDetail(this,new BBSDto()));
		mainPanel.add("AdSharedbbsDetail", new AdSharebbsDetail(this));
		cards.show(mainPanel, "AdSharedbbsDetail"); // 처음 띄어지는 패널
		
		mainPanel.setBounds(0, 0, 1000, 800);
		add(mainPanel);
		setVisible(true);
	}

//	public void changPanel(int select, BBSDto dto) {
	public void changPanel(int select) {

		if (select == 1) {
			mainPanel.add("AdSharedbbsList", new AdSharebbsList(this));
			cards.show(mainPanel, "AdSharedbbsList");
		} else if (select == 2) {
			mainPanel.add("AdSharedbbsDetail", new AdSharebbsDetail(this));
			cards.show(mainPanel, "AdSharedbbsDetail");
		}
	}

}
