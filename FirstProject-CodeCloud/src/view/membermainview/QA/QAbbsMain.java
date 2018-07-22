package view.membermainview.QA;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
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

	final int DETAIL = -2;
	final int LIST = -1;
	final int INSERT = 0;
	final int UPDATE = 1;

	private static JTable Liked,Fork;
    
    static String Lcolum[] = {
         "제목","추천수"
    };      
   
    static String Fcolum[] = {
          "제목", "포크수"
    };   
    
    Object LrowData[][],  FrowData[][];     
   
    JLabel logo;
	

	public QAbbsMain() {
		setLayout(null);
		setOpaque(false);
		setBounds(0, 0, 1100, 700);
		
		// 코드 배경
		ImageIcon logo_Img = new ImageIcon("img/QAbbs/QA_logo.png");

		logo = new JLabel();
		logo.setIcon(logo_Img);
		logo.setBounds(50, 40, 350, 69);
		add(logo);

		mainPanel = new JPanel(cards);

		mainPanel.add("QAbbsList", new QAbbsList(this));
		mainPanel.add("QAbbsDetail", new QAbbsDetail(this, new QAbbsDto()));

		mainPanel.add("QAbbsWrite", new QAbbsWrite(this, new QAbbsDto(),LIST));
		mainPanel.setOpaque(false);

		cards.show(mainPanel, "QAbbsList");// 처음 띄워지는 판

		mainPanel.setBounds(0, 0, 1000, 800);

		// rank
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

		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		celAlignCenter.setOpaque(false);
		
		Font tableFont = new Font("맑은고딕", Font.PLAIN, 15);
		
		Liked.getColumnModel().getColumn(0).setMaxWidth(90);
		Liked.getColumnModel().getColumn(1).setMaxWidth(40);
		Liked.getColumnModel().getColumn(1).setCellRenderer(celAlignCenter);
		Liked.setFont(tableFont);
		
		Fork.getColumnModel().getColumn(0).setMaxWidth(90);
		Fork.getColumnModel().getColumn(1).setMaxWidth(40);
		Fork.getColumnModel().getColumn(1).setCellRenderer(celAlignCenter);
		Fork.setFont(tableFont);
		
		Liked.setBounds(55, 98, 130, 270);
		Fork.setBounds(55, 428, 130, 270);
		Liked.setRowHeight(40);
		Fork.setRowHeight(40);

		// 코드 배경
		ImageIcon Lrank = new ImageIcon("img/QAbbs/rank.png");

		JLabel rankL = new JLabel();
		rankL.setIcon(Lrank);
		rankL.setBounds(10, 60, 180, 270);

		// 코드 배경
		ImageIcon Frank = new ImageIcon("img/QAbbs/rank.png");

		JLabel rankF = new JLabel();
		rankF.setIcon(Frank);
		rankF.setBounds(10, 390, 180, 270);

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

		// 코드 배경
		ImageIcon Like_top5 = new ImageIcon("img/QAbbs/like_top5.png");

		JLabel Like_top5_L = new JLabel();
		Like_top5_L.setIcon(Like_top5);
		Like_top5_L.setBounds(10, 43, 180, 50);

		// 코드 배경
		ImageIcon Fork_top5 = new ImageIcon("img/QAbbs/down_top5.png");

		JLabel Fork_top5_L = new JLabel();
		Fork_top5_L.setIcon(Fork_top5);
		Fork_top5_L.setBounds(10, 373, 180, 50);

		
		endOfRightPanel.setBounds(900, 0, 300, 700);
		endOfRightPanel.setOpaque(false);
		endOfRightPanel.add(Liked);
		endOfRightPanel.add(Fork);
		endOfRightPanel.add(rankL);
		endOfRightPanel.add(rankF);
		endOfRightPanel.add(Like_top5_L);
		endOfRightPanel.add(Fork_top5_L);
		
		add(mainPanel);
		add(endOfRightPanel);
		
		setVisible(true);
	}

	public void changePanel(int state, QAbbsDto dto) {
		if (state == LIST) {
			mainPanel.add("QAbbsList", new QAbbsList(this));
			logo.setVisible(true);
			cards.show(mainPanel, "QAbbsList");
			
		} else if (state == DETAIL) {
			mainPanel.add("QAbbsDetail", new QAbbsDetail(this, dto));
			logo.setVisible(false);
			cards.show(mainPanel, "QAbbsDetail");

		} else if (state == UPDATE) {
			mainPanel.add("QAbbsWrite", new QAbbsWrite(this, dto, state));
			logo.setVisible(false);
			cards.show(mainPanel, "QAbbsWrite");
		} else if (state == INSERT) {
			mainPanel.add("QAbbsWrite", new QAbbsWrite(this, dto, state));
			logo.setVisible(false);
			cards.show(mainPanel, "QAbbsWrite");
		}
	}

	public static void setRankList(Object LList[][], Object FList[][]) {
		// 추천순 포크순 테이블 만들기
		DefaultTableModel modellike = new DefaultTableModel(LList, Lcolum);
		DefaultTableModel modelfork = new DefaultTableModel(FList, Fcolum);

		Liked.setModel(modellike);
		Fork.setModel(modelfork);

		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		celAlignCenter.setOpaque(false);
		
		Font tableFont = new Font("맑은고딕", Font.PLAIN, 15);
		
		Liked.getColumnModel().getColumn(0).setMaxWidth(90);
		Liked.getColumnModel().getColumn(1).setMaxWidth(40);
		Liked.getColumnModel().getColumn(1).setCellRenderer(celAlignCenter);
		Liked.setFont(tableFont);
		
		Fork.getColumnModel().getColumn(0).setMaxWidth(90);
		Fork.getColumnModel().getColumn(1).setMaxWidth(40);
		Fork.getColumnModel().getColumn(1).setCellRenderer(celAlignCenter);
		Fork.setFont(tableFont);
		
		Liked.setBounds(55, 98, 130, 270);
		Fork.setBounds(55, 428, 130, 270);
		Liked.setRowHeight(40);
		Fork.setRowHeight(40);


	
	}
}
