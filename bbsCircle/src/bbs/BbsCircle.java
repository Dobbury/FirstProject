package bbs;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;

import dti.BbsDto;

public class BbsCircle extends JPanel{
	
	 private CardLayout card1 = new CardLayout();
	  JPanel CircleMainPanel;
	  
	public BbsCircle() {
		CircleMainPanel= new JPanel();
		
	
		setLayout(null);
        setBackground(Color.black); //게시판 메인 패널의 색상 블랙 
        setBounds(10,90	,440,520);
        
        card1.show(CircleMainPanel, "BbsCircleList"); //CircleMainPanel에  BbsCircleList 화면이 먼저 온다 
        
        CircleMainPanel.setBounds(10,90,440,520);
        add(CircleMainPanel ); //여기까지 하면 카드레이아웃메인패널위에 게시판리스트 부착
        
        setVisible(true);
 
		  
		 
	}
	
	 public void changePanel(int i, BbsDto bbsdto) {//ShareDto shareDto) {
		 
	        if (i == 1) {  
	        	CircleMainPanel.add("BbsCircleList", new BbsCircleList bbsCircle);
	        	card1.show(CircleMainPanel, "BbsCircleList");
	        } else if (i == 2) {
	        	CircleMainPanel.add("BbsCircleRead", new BbsCircleRead(this));
	        	card1.show(CircleMainPanel, "BbsCircleRead");
	 
	        }else if (i == 2) {
	        	CircleMainPanel.add("BbsCircleWrite", new BbsCircleWrite(this));
	        	card1.show(CircleMainPanel, "BbsCircleWrite");
	 


	
	

}
