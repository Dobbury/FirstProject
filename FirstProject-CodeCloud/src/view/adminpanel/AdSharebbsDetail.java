package view.adminpanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dto.BBSDto;

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

	private JButton btn_Update; // 게시글 삭제
	private JButton btn_List;// 목록으로

	AdminSharebbs adminSharebbs;
//	BBSDto dto;

//	public AdSharebbsDetail(AdminSharebbs adshbbs, BBSDto dto) {
	public AdSharebbsDetail(AdminSharebbs adshbbs) {

		adminSharebbs = adshbbs;
//		this.dto = dto;

//		System.out.println(dto.toString());
		titleLabel = new JLabel("닉네임: ");
		titleLabel.setBounds(100, 100, 50, 30);

		titleText = new JTextField();
		titleText.setBounds(200, 100, 310, 30);
		// titleText.setText(dto.getNick());
		 titleText.setText("닉네임");
		titleText.setEditable(false);

		titleLabel2 = new JLabel("제목");
		titleLabel2.setBounds(100, 150, 50, 30);

		titleText2 = new JTextField();
		titleText2.setBounds(200, 150, 310, 30);
//		titleText2.setText(dto.getTitle());
		titleText2.setText("제목");
		titleText2.setEditable(false);
		
		titleLabel4 = new JLabel("언어");
		titleLabel4.setBounds(520, 150, 25, 30);
		titleText3 = new JTextField();
		titleText3.setBounds(550, 150, 50, 30);
		titleText3.setText("JAVA");
		titleText3.setEditable(false);

		titleLabel5 = new JLabel("추천");
		titleLabel5.setBounds(520, 100, 25, 30);
		titleText4 = new JTextField();
		titleText4.setBounds(550, 100, 50, 30);
		titleText4.setText("999");
		titleText4.setEditable(false);
		
		titleLabel6 = new JLabel("포크");
		titleLabel6.setBounds(610, 100, 25, 30);
		titleText5 = new JTextField();
		titleText5.setBounds(640, 100, 50, 30);
		titleText5.setText("999");
		titleText5.setEditable(false);
		
		titleLabel3 = new JLabel("Code");
		titleLabel3.setBounds(100, 180, 100, 60);

		postArea = new JTextArea();
		postArea.setBounds(200, 200, 500, 300);
//		postArea.append(dto.getContent());
		postArea.append("내용");
		postArea.setEditable(false);

		jScrol = new JScrollPane(postArea);
		jScrol.setBounds(200, 200, 500, 300);

		// 삭제
		btn_Update = new JButton("삭제");
		btn_Update.addActionListener(this);
		btn_Update.setBounds(200, 550, 110, 50);

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

		add(btn_Update);
		add(btn_List);

		setLayout(null);
		setBackground(Color.LIGHT_GRAY);
		setBounds(50, 50, 300, 300);

		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn_List) {
			adminSharebbs.changPanel(1);
		}

	}

}
