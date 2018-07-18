package view.adminmainview.share;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;

import dto.BBSDto;
import dto.ShareDto;

public class AdminSharebbs extends JPanel {

	private CardLayout cards = new CardLayout();
	JPanel mainPanel;

	AdSharebbsDetail detail;

	public AdminSharebbs() {
		setLayout(null);
		setBounds(0, 0, 1150, 700);
		setOpaque(false);
		mainPanel = new JPanel(cards);

		mainPanel.add("AdSharedbbsList", new AdSharebbsList(this));
		mainPanel.add("AdSharedbbsDetail", new AdSharebbsDetail(this, new ShareDto()));
		cards.show(mainPanel, "AdSharedbbsList"); // 처음 띄어지는 패널
		
		mainPanel.setBounds(0, 0, 1150, 700);
		mainPanel.setOpaque(false);
		add(mainPanel);
		setVisible(true);
	}

	public void changPanel(int select, ShareDto dto) {

		if (select == 1) {
			mainPanel.add("AdSharedbbsList", new AdSharebbsList(this));
			cards.show(mainPanel, "AdSharedbbsList");
			
		} else if (select == 2) {
			mainPanel.add("AdSharedbbsDetail", new AdSharebbsDetail(this,dto));
			cards.show(mainPanel, "AdSharedbbsDetail");
		}
	}
}
