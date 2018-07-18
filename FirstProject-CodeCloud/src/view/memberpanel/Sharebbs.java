package view.memberpanel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.ShareDao;
import dto.QAbbsDto;
import dto.ShareDto;
import view.MemberMainView;

public class Sharebbs extends JPanel {
	private CardLayout cards = new CardLayout();
	JPanel mainPanel , endOfRightPanel;

 	JTable Liked,Fork;
 	
 	String Lcolum[] = {
			"순위","제목", "추천수"
 	};		
	
 	String Fcolum[] = {
 			"순위","제목", "포크수"
 	};	
 	
 	Object LrowData[][],  FrowData[][];     
 	
 	
	public Sharebbs() {
		setLayout(null);
		setBackground(Color.black);
		setBounds(50,50,300,300);
		mainPanel = new JPanel(cards);
		endOfRightPanel = new JPanel();

		mainPanel.add("ShareList", new ShareList(this));
		//mainPanel.add("ShareDetail", new ShareDetail(this));
		 
		cards.show(mainPanel, "ShareList");// 처음 띄워지는 판

		mainPanel.setBounds(0, 0, 800, 800);
		
		
		//추천순 포크순 테이블 생성

		
		 //추천순 포크순 테이블 만들기
		DefaultTableModel modellike = new DefaultTableModel(ShareDao.getLikeList(), Lcolum);
		DefaultTableModel modelfork = new DefaultTableModel(ShareDao.getForkList(), Fcolum);

		Liked = new JTable(modellike){
			@Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		
		
		Fork = new JTable(modelfork){
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
		
		Liked.setBounds(10,50,180,270);
		Fork.setBounds(10,370,180,270);
		Liked.setRowHeight(45);
		Fork.setRowHeight(45);

		
		//테이블 부착
		
		 endOfRightPanel.setBounds(800,0,200,800);
		 endOfRightPanel.setBackground(Color.GREEN);
		 add(Liked);     
		 endOfRightPanel.add(Liked);
		 add(Fork);     
		 endOfRightPanel.add(Fork);
		
		
		
		
		
		//가운데 패널 체인지해주는 패널 부착
		add(mainPanel);
		
	 
		add(endOfRightPanel);
		
		
		
		
		
		
		setVisible(true);

		
		
		
		
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