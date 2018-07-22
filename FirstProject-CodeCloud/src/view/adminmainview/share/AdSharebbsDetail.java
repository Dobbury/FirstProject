package view.adminmainview.share;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import dto.BBSDto;
import dto.ShareDto;
import singleton.Singleton;

public class AdSharebbsDetail extends JPanel implements ActionListener {

	JLabel likelab = new JLabel();
	JLabel forklab = new JLabel();

	JLabel nickText;// 닉넴 텍스트필드
	JLabel langText;
	JLabel titleText;// 제목 텍스트필드

	JTextArea postArea;
	JScrollPane jScrol;

	private JLabel btn_liked;// 좋아요 버튼
	private JLabel btn_fork;// 다운로드 버튼

	ImageIcon deleteIc1;
	ImageIcon deleteIc2;
	ImageIcon deleteIc3;
	private JButton btn_Delete; // 게시글 삭제
	
	ImageIcon listIc1;
	ImageIcon listIc2;
	ImageIcon listIc3;
	private JButton btn_List;// 목록으로

	AdminSharebbs adminSharebbs;
	ShareDto dto;

	public AdSharebbsDetail(AdminSharebbs adshbbs, ShareDto dto) {
		setOpaque(false);
		adminSharebbs = adshbbs;
		this.dto = dto;

		// 닉네임
		Font nickFont = new Font("굴림", Font.BOLD, 25);
		nickText = new JLabel();
		nickText.setFont(nickFont);
		nickText.setForeground(Color.WHITE);
		nickText.setBounds(65, 160, 310, 30);
		nickText.setText(dto.getNick());

		Font titleFont = new Font("굴림", Font.BOLD, 40);
		titleText = new JLabel();
		titleText.setFont(titleFont);
		titleText.setForeground(Color.WHITE);
		titleText.setBounds(65, 100, 310, 50);
		titleText.setText(dto.getTitle());

		JLabel langText = new JLabel(dto.getLang(), SwingConstants.CENTER);
		langText.setFont(titleFont);
		langText.setForeground(Color.WHITE);
		langText.setBounds(605, 100, 160, 50);
		add(langText);

		postArea = new JTextArea();
		postArea.setBounds(200, 200, 500, 300);
		postArea.append(dto.getContent());

		// 컨텐츠
		postArea = new JTextArea();
		postArea.append(dto.getContent());
		postArea.setOpaque(false);
		postArea.setEditable(false);
		postArea.setForeground(Color.WHITE);

		//스크롤바 0으로 줄여서 안보이게하는 코드
		jScrol = new JScrollPane(postArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
		jScrol.getVerticalScrollBar().setPreferredSize (new Dimension(0,0));
				
		jScrol.setBounds(50, 200, 750, 350);
		
		jScrol.setOpaque(false);
		jScrol.getViewport().setOpaque(false);
		//스크롤바 0으로 줄여서 안보이게하는 코드
					
		jScrol.setBorder(BorderFactory.createCompoundBorder(null,
	            BorderFactory.createEmptyBorder(20, 20, 20, 20)));

		
		//코드 배경
		ImageIcon content_back_Img = new ImageIcon("img/adminMain/share/share_detail_content_background.png");
				
		JLabel content_backgorund = new JLabel();
		content_backgorund.setIcon(content_back_Img);
		content_backgorund.setBounds(50,200,750,350);
				
		// 삭제
		// 삭제
		deleteIc1 = new ImageIcon("img/adminMain/share/share_delete_on.png");
		deleteIc2 = new ImageIcon("img/adminMain/share/share_delete_off.png");
		deleteIc3 = new ImageIcon("img/adminMain/share/share_delete_ing.png");
		btn_Delete = new JButton(deleteIc1);
		btn_Delete.setRolloverIcon(deleteIc2);
		btn_Delete.setPressedIcon(deleteIc3);
		btn_Delete.setBorderPainted(false);
		btn_Delete.setContentAreaFilled(false);
		btn_Delete.setFocusPainted(false);
		btn_Delete.addActionListener(this);

		btn_Delete.setBounds(700, 570, 101, 41);

		// 글 목록

		listIc1 = new ImageIcon("img/adminMain/share/share_list_on.png");
		listIc2 = new ImageIcon("img/adminMain/share/share_list_off.png");
		listIc3 = new ImageIcon("img/adminMain/share/share_list_ing.png");
		btn_List = new JButton(listIc1);
		btn_List.setRolloverIcon(listIc2);
		btn_List.setPressedIcon(listIc3);
		btn_List.setBorderPainted(false);
		btn_List.setContentAreaFilled(false);
		btn_List.setFocusPainted(false);
		btn_List.addActionListener(this);
		
		btn_List.setBounds(50, 570, 101, 41);

		// 좋아요버튼
		ImageIcon liked_Img_clicked = new ImageIcon("img/sharebbs/btn_Liked_clicked.png");

		btn_liked = new JLabel(liked_Img_clicked);
		btn_liked.setBounds(610, 160, 30, 30);


		Font liked_fork_Font = new Font("굴림", Font.BOLD, 15);
		likelab.setBounds(650, 160, 30, 30);
		likelab.setFont(liked_fork_Font);
		likelab.setForeground(Color.WHITE);

		likelab.setText(dto.getLiked() + "");

		// 포크 버튼
		ImageIcon fork_Img1 = new ImageIcon("img/sharebbs/fork1.png");
		
		btn_fork = new JLabel(fork_Img1);
		btn_fork.setBounds(710, 160, 30, 30);

		forklab.setBounds(752, 160, 30, 30);
		forklab.setFont(liked_fork_Font);
		forklab.setForeground(Color.WHITE);
		forklab.setText(dto.getFork() + "");
		
		add(titleText);
		add(nickText);
		add(langText);
		
		add(btn_liked);
		add(btn_fork);
		add(likelab);
		add(forklab);

		add(jScrol);

		add(btn_Delete);
		add(btn_List);
		
		add(content_backgorund);
		setLayout(null);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn_List) {
			adminSharebbs.changPanel(1, new ShareDto());

		}else if (e.getSource() == btn_Delete) {
			Singleton s = Singleton.getInstance();
			s.sharDao.delete(dto);
			adminSharebbs.changPanel(1, new ShareDto());
		}
	}
}
