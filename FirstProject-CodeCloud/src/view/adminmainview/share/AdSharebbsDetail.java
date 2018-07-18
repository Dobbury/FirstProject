package view.adminmainview.share;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import dto.BBSDto;
import dto.ShareDto;
import singleton.Singleton;

public class AdSharebbsDetail extends JPanel implements ActionListener {

	JLabel titleLabel;// 닉네임
	JLabel titleLabel2;// 제목
	JLabel titleLabel3;// 코드
	JLabel titleLabel4;// 언어 
	JLabel titleLabel5;// 추천 
	JLabel titleLabel6;// 포크 

	
	JTextField titleText;// 닉넴 텍스트필드
	JTextField titleText2;// 제목 텍스트필드
	JTextField titleText3;// 언어 
	JTextField titleText4;// 추천
	JTextField titleText5;// 포크

	JTextArea postArea; // 코드 창

	JScrollPane jScrol;

	private JButton btn_Delete; // 게시글 삭제
	private JButton btn_List;// 목록으로

	AdminSharebbs adminSharebbs;
	ShareDto dto;

//	public AdSharebbsDetail(AdminSharebbs adshbbs, BBSDto dto) {
	public AdSharebbsDetail(AdminSharebbs adshbbs,ShareDto dto) {
		setOpaque(false);
		adminSharebbs = adshbbs;
		this.dto = dto;

//		System.out.println(dto.toString());
		titleLabel = new JLabel("닉네임: ");
		titleLabel.setBounds(100, 100, 50, 30);

		titleText = new JTextField();
		titleText.setBounds(200, 100, 310, 30);
		titleText.setText(dto.getNick());
		titleText.setEditable(false);

		titleLabel2 = new JLabel("제목");
		titleLabel2.setBounds(100, 150, 50, 30);

		titleText2 = new JTextField();
		titleText2.setBounds(200, 150, 310, 30);
		titleText2.setText(dto.getTitle());
		titleText2.setEditable(false);
		
		titleLabel4 = new JLabel("언어");
		titleLabel4.setBounds(520, 150, 25, 30);
		titleText3 = new JTextField();
		titleText3.setBounds(550, 150, 50, 30);
		titleText3.setText(dto.getLang());
		titleText3.setEditable(false);

		titleLabel5 = new JLabel("추천");
		titleLabel5.setBounds(520, 100, 25, 30);
		titleText4 = new JTextField();
		titleText4.setBounds(550, 100, 50, 30);
		titleText4.setText(dto.getLiked()+"");
		titleText4.setEditable(false);
		
		titleLabel6 = new JLabel("포크");
		titleLabel6.setBounds(610, 100, 25, 30);
		titleText5 = new JTextField();
		titleText5.setBounds(640, 100, 50, 30);
		titleText5.setText(dto.getFork()+"");
		titleText5.setEditable(false);
		
		titleLabel3 = new JLabel("Code");
		titleLabel3.setBounds(100, 180, 100, 60);

		postArea = new JTextArea();
		postArea.setBounds(200, 200, 500, 300);
//		postArea.append(dto.getContent());
		postArea.append(dto.getContent());
		postArea.setEditable(false);

		//스크롤바 0으로 줄여서 안보이게하는 코드
		jScrol = new JScrollPane(postArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
		jScrol.getVerticalScrollBar().setPreferredSize (new Dimension(0,0));
				
		jScrol.setBounds(200, 200, 500, 300);

		// 삭제
		btn_Delete = new JButton("삭제");
		btn_Delete.addActionListener(this);
		btn_Delete.setBounds(200, 550, 110, 50);

		// 글 목록
		btn_List = new JButton("목록");
		btn_List.addActionListener(this);
		btn_List.setBounds(590, 550, 110, 50);

		add(titleLabel);
		add(titleLabel2);
		add(titleLabel3);
		add(titleLabel4);
		add(titleLabel5);
		add(titleLabel6);
		
		add(titleText);
		add(titleText2);
		add(titleText3);
		add(titleText4);
		add(titleText5);

		add(jScrol);

		add(btn_Delete);
		add(btn_List);

		setLayout(null);

		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn_List) {
			adminSharebbs.changPanel(1,new ShareDto());
		}
		if(e.getSource() == btn_Delete) {
			Singleton s = Singleton.getInstance();
			s.sharDao.delete(dto.getSeq());
			adminSharebbs.changPanel(1, new ShareDto());
		}

	}

}
