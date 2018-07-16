package view.membermainview;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dto.QAbbsDto;
import view.MemberMainView;
import view.SignupView;

public class QAbbsMain extends JPanel {
	private CardLayout cards = new CardLayout();
	JPanel mainPanel;

	// 추가부분
	QAbbsDetail detail;

	public QAbbsMain() {
		setLayout(null);
		setBounds(0, 0, 1000, 800);
		mainPanel = new JPanel(cards);

		
		mainPanel.add("QAbbsList", new QAbbsList(this));
		mainPanel.add("QAbbsDetail", new QAbbsDetail(this, new QAbbsDto()));
		mainPanel.add("QAbbsWrite", new QAbbsWrite(this, new QAbbsDto()));
		cards.show(mainPanel, "QAbbsList");// 처음 띄워지는 판

		mainPanel.setBounds(0, 0, 1000, 800);
		add(mainPanel);
		setVisible(true);
	}

	public void changePanel(int select, QAbbsDto dto) {

		if (select == 1) {
			mainPanel.add("QAbbsList", new QAbbsList(this));
			cards.show(mainPanel, "QAbbsList");
		} else if (select == 2) {
			mainPanel.add("QAbbsDetail", new QAbbsDetail(this, dto));
			cards.show(mainPanel, "QAbbsDetail");

		} else if (select == 3) {
			mainPanel.add("QAbbsWrite", new QAbbsWrite(this, dto));
			cards.show(mainPanel, "QAbbsWrite");
		}
	}
}
