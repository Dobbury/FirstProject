package view.membermainview;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
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

	static String Lcolum[] = {"제목", "추천수" };

	static String Fcolum[] = {"제목", "포크수" };

	Object LrowData[][], FrowData[][];
	JLabel logo;
	
	public Sharebbs() {
		setLayout(null);
		setOpaque(false);
		setBounds(0, 0, 1100, 700);

		// 코드 배경
		ImageIcon logo_Img = new ImageIcon("img/sharebbs/share_logo.png");

		logo = new JLabel();
		logo.setIcon(logo_Img);
		logo.setBounds(50, 40, 294, 70);
		add(logo);

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
		ImageIcon Lrank = new ImageIcon("img/sharebbs/rank.png");

		JLabel rankL = new JLabel();
		rankL.setIcon(Lrank);
		rankL.setBounds(10, 60, 180, 270);

		// 코드 배경
		ImageIcon Frank = new ImageIcon("img/sharebbs/rank.png");

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
		ImageIcon Like_top5 = new ImageIcon("img/sharebbs/like_top5.png");

		JLabel Like_top5_L = new JLabel();
		Like_top5_L.setIcon(Like_top5);
		Like_top5_L.setBounds(10, 43, 180, 50);

		// 코드 배경
		ImageIcon Fork_top5 = new ImageIcon("img/sharebbs/down_top5.png");

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
      

      //가운데 패널 체인지해주는 패널 부착
      add(mainPanel);
      
    
      add(endOfRightPanel);
     
      
      setVisible(true);

      
   }
   public static void setRankList(Object LList[][], Object FList[][]) {
	   //추천순 포크순 테이블 만들기
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
	      
	      Liked.setBounds(55,98,130,270);
	      Fork.setBounds(55,428,130,270);
	      Liked.setRowHeight(40);
	      Fork.setRowHeight(40);
	      

   }
   public void changePanel(int i, ShareDto shareDto) {

      if (i == 1) {
         mainPanel.add("ShareList", new ShareList(this));
         logo.setVisible(true);
         cards.show(mainPanel, "ShareList");
      } else if (i == 2) {
         mainPanel.add("ShareDetail", new ShareDetail(this, shareDto));
         logo.setVisible(false);
         cards.show(mainPanel, "ShareDetail");
 
      }
            
      
      
   }
   
}