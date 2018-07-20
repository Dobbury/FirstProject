package view.adminmainview.share;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;

import dto.BBSDto;
import dto.ShareDto;

public class AdminSharebbs extends JPanel {

	private CardLayout cards = new CardLayout();
	JPanel mainPanel;
   
    JLabel logo;
	
	
	public AdminSharebbs() {
		setLayout(null);
		setBounds(0, 0, 1150, 700);
		setOpaque(false);
		
		// 코드 배경
		ImageIcon logo_Img = new ImageIcon("img/adminMain/share/share_logo.png");

		logo = new JLabel();
		logo.setIcon(logo_Img);
		logo.setBounds(50, 40, 294, 70);
		add(logo);
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
			logo.setVisible(true);
			mainPanel.add("AdSharedbbsList", new AdSharebbsList(this));
			cards.show(mainPanel, "AdSharedbbsList");
			
		} else if (select == 2) {
			logo.setVisible(false);
			mainPanel.add("AdSharedbbsDetail", new AdSharebbsDetail(this,dto));
			cards.show(mainPanel, "AdSharedbbsDetail");
		}
	}
}
