package view.memberpanel;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import dto.QAbbsDto;
import dto.ShareDto;
import singleton.Singleton;

public class ShareDetail extends JPanel implements Action {

	JLabel titleLabel;// 닉네임
	JLabel titleLabel2;// 제목
	JLabel titleLabel3;// content
	
	JLabel likelab = new JLabel();
	JLabel forklab = new JLabel();

	JTextField titleText;// 닉넴 텍스트필드
	JTextField titleText2;// 제목 텍스트필드
	JTextField titleText3;// content 텍스트 필드
	
	JTextArea postArea;
	JScrollPane jScrol;
	
	private JButton btn_List;// 목록으로
	private JButton btn_liked;//좋아요 버튼
	private JButton btn_fork;// 다운로드 버튼 
	
	
	Sharebbs ShareMain;
	QAbbsDto dto;
	public ShareDetail(Sharebbs sharebbs, ShareDto dto) {
		
		ShareMain=sharebbs;
		
		titleLabel = new JLabel("닉네임: ");
		titleLabel.setBounds(100, 100, 50, 30);

		titleText = new JTextField();
		titleText.setBounds(200,100, 310, 30);
		titleText.setText(dto.getNick());
		titleText.setEditable(false);

		titleLabel2 = new JLabel("제목: ");
		titleLabel2.setBounds(100, 150, 50, 30);

		titleText2 = new JTextField();
		titleText2.setBounds(200, 150, 310, 30);
		titleText2.setText(dto.getTitle());
		titleText2.setEditable(false);

		titleLabel3 = new JLabel("CONTENT: ");
		titleLabel3.setBounds(100, 180, 100, 60);
		
		postArea = new JTextArea();
		postArea.setBounds(200, 200, 300, 300);
		postArea.append(dto.getContent());
		postArea.setEditable(false);
		
		jScrol = new JScrollPane(postArea);
		jScrol.setBounds(200, 200, 300, 300);

		// 글 목록
		btn_List = new JButton("글 목록");
		btn_List.addActionListener(this);
		btn_List.setBounds(500, 550, 110, 50);
		
		//좋아요버튼
		btn_liked = new JButton("추천");
		btn_liked.addActionListener(this);
		btn_liked.setBounds(50, 450, 60, 50);
		likelab.setBounds(130, 450, 60, 50);
		likelab.setText(dto.getLiked()+"");
		
		//포크 버튼 
		btn_fork = new JButton("퍼가요");
		btn_fork.addActionListener(this);
		btn_fork.setBounds(50, 550, 60, 50);
		forklab.setBounds(130, 550, 60, 50);
		forklab.setText(dto.getFork()+"");
		

		add(titleLabel);
		add(titleLabel2);
		add(titleLabel3);
		
		add(titleText);
		add(titleText2);
		
		add(jScrol);

		
		add(btn_List);
		add(btn_liked);
		add(btn_fork);
		add(likelab);
		add(forklab);
		
		
		setLayout(null);
		setBackground(Color.PINK);
		setBounds(50, 50, 300, 300);
		
		setVisible(true);
		
		
		
	}
	
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

	Singleton s = Singleton.getInstance();
			
		 
		if(e.getSource() == btn_List) {
			//Sharebbs.changePanel(new ShareList(ShareMain));
			ShareMain.changePanel(1, null);
			 
			
		}else if (e.getSource() == btn_liked) {
			 
			
		}else if(e.getSource() == btn_fork) {
			 
		}

	 
		
		
	}
	
	
	
	
	
	
	

	@Override
	public Object getValue(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putValue(String arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

}
