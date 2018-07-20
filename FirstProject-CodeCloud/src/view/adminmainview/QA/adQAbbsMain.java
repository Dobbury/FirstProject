package view.adminmainview.QA;

import java.awt.CardLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import dto.QAbbsDto;
import view.membermainview.QAbbsList;

public class adQAbbsMain extends JPanel {
	
	final int DETAIL = 0;
	final int LIST = 1;
	final int COMMENT = 2;
	final int COMMENT_UPDATE = 3;
	
	public CardLayout cards = new CardLayout();
	public JPanel admainPanel;

	adQAbbsDetail addetail;
	   
    JLabel logo;
	
	public adQAbbsMain() {
		setLayout(null);
		setOpaque(false);
		setBounds(0, 0, 1150, 700);
		
		ImageIcon logo_Img = new ImageIcon("img/adminMain/QA/QA_logo.png");

		logo = new JLabel();
		logo.setIcon(logo_Img);
		logo.setBounds(50, 40, 350, 69);
		add(logo);
		
		admainPanel = new JPanel(cards);

		admainPanel.add("adQAbbsDetail", new adQAbbsDetail(this, new QAbbsDto()));
		admainPanel.add("adQAbbswrite", new adQAbbswrite(this, new QAbbsDto(), 0));
		admainPanel.add("adQAbbsList", new adQAbbsList(this));
		admainPanel.setOpaque(false);
		cards.show(admainPanel, "adQAbbsList");// 처음 띄워지는 판

		admainPanel.setBounds(0, 0, 1150, 700);
		add(admainPanel);
		setVisible(true);
	}

	public void changePanel(int state, QAbbsDto dto) {
		if (state == DETAIL) {
			logo.setVisible(false);
			admainPanel.add("adQAbbsDetail", new adQAbbsDetail(this, dto));
			cards.show(admainPanel, "adQAbbsDetail");
		} else if (state == COMMENT) {
			logo.setVisible(false);
			admainPanel.add("adQAbbswrite", new adQAbbswrite(this, dto, state));
			cards.show(admainPanel, "adQAbbswrite");
		} else if (state == LIST) {
			logo.setVisible(true);
			admainPanel.add("adQAbbsList", new adQAbbsList(this));
			cards.show(admainPanel, "adQAbbsList");
		}else if (state == COMMENT_UPDATE) {
			logo.setVisible(false);
			admainPanel.add("adQAbbswrite", new adQAbbswrite(this, dto, state));
			cards.show(admainPanel, "adQAbbswrite");
		}
	}
}