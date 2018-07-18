package view.membermainview;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.ShareDao;
import dto.ShareDto;

public class Sharebbs extends JPanel {
	private CardLayout cards = new CardLayout();
	JPanel mainPanel, endOfRightPanel;

	private static JTable Liked, Fork;

	static String Lcolum[] = { "순위", "제목", "추천수" };

	static String Fcolum[] = { "순위", "제목", "포크수" };

	Object LrowData[][], FrowData[][];

	public Sharebbs() {
		setLayout(null);
		setOpaque(false);
		setBounds(0, 0, 1100, 700);

		mainPanel = new JPanel(cards);
		mainPanel.setOpaque(false);
		endOfRightPanel = new JPanel();
		endOfRightPanel.setLayout(null);

		mainPanel.add("ShareList", new ShareList(this));

		cards.show(mainPanel, "ShareList");// 처음 띄워지는 판

		mainPanel.setBounds(0, 0, 800, 700);

		// 추천순 포크순 테이블 생성
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

		// 가운데 패널 체인지해주는 패널 부착
		add(mainPanel);
		add(endOfRightPanel);
		setVisible(true);
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

		Liked.setBounds(10, 50, 180, 270);
		Fork.setBounds(10, 370, 180, 270);
		Liked.setRowHeight(45);
		Fork.setRowHeight(45);
	}

	public void changePanel(int i, ShareDto shareDto) {

		if (i == 1) {
			
			mainPanel.add("ShareList", new ShareList(this));
			cards.show(mainPanel, "ShareList");
			
		} else if (i == 2) {
			
			mainPanel.add("ShareDetail", new ShareDetail(this, shareDto));
			cards.show(mainPanel, "ShareDetail");
		}
	}
}