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

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.ShareDao;
import dto.QAbbsDto;
import view.MemberMainView;
import view.SignupView;

public class QAbbsMain extends JPanel {
	private CardLayout cards = new CardLayout();
	JPanel mainPanel, endOfRightPanel;;

	// 추가부분
	QAbbsDetail detail;
	final int INSERT = 0;
	final int UPDATE = 1;

	final int DETAIL = -2;
	final int LIST = -1;

	private static JTable Liked,Fork;
    
    static String Lcolum[] = {
         "순위","제목","추천수"
    };      
   
    static String Fcolum[] = {
          "순위","제목", "포크수"
    };   
    
    Object LrowData[][],  FrowData[][];     
   
    

	public QAbbsMain() {
		setLayout(null);
		setOpaque(false);
		setBounds(0, 0, 1100, 700);
		mainPanel = new JPanel(cards);

		mainPanel.add("QAbbsList", new QAbbsList(this));
		mainPanel.add("QAbbsDetail", new QAbbsDetail(this, new QAbbsDto()));
<<<<<<< HEAD
		mainPanel.add("QAbbsWrite", new QAbbsWrite(this, new QAbbsDto(),LIST));
		mainPanel.setOpaque(false);
=======
		mainPanel.add("QAbbsWrite", new QAbbsWrite(this, new QAbbsDto(), INSERT));
		mainPanel.add("QAbbsWrite", new QAbbsWrite(this, new QAbbsDto(), LIST));
>>>>>>> 6e1f19c0cbb7c937b222296d2c62743b5d1e0f95
		cards.show(mainPanel, "QAbbsList");// 처음 띄워지는 판

		
		mainPanel.setBounds(0, 0, 1000, 800);

		//rank
		endOfRightPanel = new JPanel();
		endOfRightPanel.setLayout(null);

		// 추천순 포크순 테이블 만들기
		DefaultTableModel modellike = new DefaultTableModel(ShareDao.getLikeList(), Lcolum);
		DefaultTableModel modelfork = new DefaultTableModel(ShareDao.getForkList(), Fcolum);

		Liked = new JTable(modellike) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		Fork = new JTable(modelfork) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		Liked.getColumnModel().getColumn(0).setMaxWidth(40);
		Liked.getColumnModel().getColumn(1).setMaxWidth(100);
		Liked.getColumnModel().getColumn(2).setMaxWidth(40);

		Fork.getColumnModel().getColumn(0).setMaxWidth(40);
		Fork.getColumnModel().getColumn(1).setMaxWidth(100);
		Fork.getColumnModel().getColumn(2).setMaxWidth(40);

		Liked.setBounds(10, 50, 180, 270);
		Fork.setBounds(10, 370, 180, 270);
		Liked.setRowHeight(45);
		Fork.setRowHeight(45);

		Liked.setOpaque(false);
		Liked.setForeground(Color.WHITE);
		Liked.setShowGrid(false);
		Liked.setOpaque(false);
		((DefaultTableCellRenderer) Liked.getDefaultRenderer(Object.class)).setOpaque(false);

		Fork.setOpaque(false);
		Fork.setForeground(Color.WHITE);
		Fork.setShowGrid(false);
		Fork.setOpaque(false);
		((DefaultTableCellRenderer) Fork.getDefaultRenderer(Object.class)).setOpaque(false);

		endOfRightPanel.setBounds(900, 0, 300, 700);
		endOfRightPanel.setOpaque(false);
		endOfRightPanel.add(Liked);
		endOfRightPanel.add(Fork);

		add(mainPanel);
		add(endOfRightPanel);
		setVisible(true);
	}

	public void changePanel(int select, QAbbsDto dto, int state) {
		if (select == 1) {
			mainPanel.add("QAbbsList", new QAbbsList(this));
			cards.show(mainPanel, "QAbbsList");
		} else if (select == 2) {
			mainPanel.add("QAbbsDetail", new QAbbsDetail(this, dto));
			cards.show(mainPanel, "QAbbsDetail");
		} else if (select == 3) {
			mainPanel.add("QAbbsWrite", new QAbbsWrite(this, dto, state));
			cards.show(mainPanel, "QAbbsWrite");
		}
	}

	public static void setRankList(Object LList[][], Object FList[][]) {
		// 추천순 포크순 테이블 만들기
		DefaultTableModel modellike = new DefaultTableModel(LList, Lcolum);
		DefaultTableModel modelfork = new DefaultTableModel(FList, Fcolum);

		Liked.setModel(modellike);
		Fork.setModel(modelfork);

		Liked.getColumnModel().getColumn(0).setMaxWidth(40);
		Liked.getColumnModel().getColumn(1).setMaxWidth(100);
		Liked.getColumnModel().getColumn(2).setMaxWidth(40);

		Fork.getColumnModel().getColumn(0).setMaxWidth(40);
		Fork.getColumnModel().getColumn(1).setMaxWidth(100);
		Fork.getColumnModel().getColumn(2).setMaxWidth(40);

		Liked.setRowHeight(45);
		Fork.setRowHeight(45);

	
	}
}
