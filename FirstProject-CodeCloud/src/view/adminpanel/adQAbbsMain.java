package view.adminpanel;

import java.awt.CardLayout;
import javax.swing.JPanel;
import dto.QAbbsDto;
import view.memberpanel.QAbbsList;

public class adQAbbsMain extends JPanel {
	private CardLayout cards = new CardLayout();
	JPanel admainPanel;
	
	adQAbbsDetail addetail;
	
	public adQAbbsMain() {
		setLayout(null);
		setBounds(0, 0, 1000, 800);
		admainPanel = new JPanel(cards);
		
		admainPanel.add("adQAbbsDetail", new adQAbbsDetail(this, new QAbbsDto()));
		admainPanel.add("adQAbbswrite", new adQAbbswrite(this, new QAbbsDto()));
		admainPanel.add("adQAbbsList", new adQAbbsList(this));
		cards.show(admainPanel,"adQAbbsList");// 처음 띄워지는 판

		admainPanel.setBounds(0, 0, 1000, 800);
		add(admainPanel);
		setVisible(true);
	}
	public void changePanel(int select, QAbbsDto dto) {
		if (select == 1) {
			admainPanel.add("adQAbbsDetail", new adQAbbsDetail(this, dto));
			cards.show(admainPanel, "adQAbbsDetail");
		}else if(select ==2) {		
			admainPanel.add("adQAbbswrite", new adQAbbswrite(this, dto));
			cards.show(admainPanel, "adQAbbswrite");
		}else if(select ==3) {
			admainPanel.add("adQAbbsList", new adQAbbsList(this));
			cards.show(admainPanel, "adQAbbsList");
		}
	}
}