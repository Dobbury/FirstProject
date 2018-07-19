package view.membermainview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.Action;
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

import dao.ShareDao;
import dto.QAbbsDto;
import dto.ShareDto;
import singleton.Singleton;

public class ShareDetail extends JPanel implements Action {


	JLabel likelab = new JLabel();
	JLabel forklab = new JLabel();

	JLabel nickText;// 닉넴 텍스트필드
	JLabel langText;
	JLabel titleText;// 제목 텍스트필드
	
	
	JTextArea postArea;
	JScrollPane jScrol;
	
	ImageIcon listIc1;
	ImageIcon listIc2;
	ImageIcon listIc3;
	private JButton btn_List;// 목록으로
	private JToggleButton btn_liked;//좋아요 버튼
	private JButton btn_fork;// 다운로드 버튼 
	
	
	Sharebbs ShareMain;
	ShareDto dto;
	public ShareDetail(Sharebbs sharebbs, ShareDto dto) {
		
		
		List<Integer> list = ShareDao.getlikeseqlist();
		
		this.dto = dto;
		
		ShareMain=sharebbs;
		
		
		//닉네임
		Font nickFont = new Font("굴림",Font.BOLD, 25);
		nickText = new JLabel();
		nickText.setFont(nickFont);
		nickText.setForeground(Color.WHITE);
		nickText.setBounds(65, 160, 310, 30);
		nickText.setText(dto.getNick());
		

		Font titleFont = new Font("굴림",Font.BOLD, 40);
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
		postArea.setOpaque(false);
		postArea.setForeground(Color.WHITE);
		postArea.append(dto.getContent());
		postArea.setEditable(false);
		
		
		//코드 배경
		ImageIcon code_back_Img = new ImageIcon("img/sharebbs/share_code_background.png");
				
		JLabel code_backgorund = new JLabel();
		code_backgorund.setIcon(code_back_Img);
		code_backgorund.setBounds(50,200,750,350);
				
			
		//스크롤바 0으로 줄여서 안보이게하는 코드
		jScrol = new JScrollPane(postArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jScrol.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
				
		jScrol.setOpaque(false);
		jScrol.getViewport().setOpaque(false);
		jScrol.setBounds(50, 200, 750, 350);
		jScrol.setBorder(BorderFactory.createCompoundBorder(null,
	            BorderFactory.createEmptyBorder(20, 20, 20, 20)));

		// 글 목록
		listIc1 = new ImageIcon("img/sharebbs/share_list_on.png");
		listIc2 = new ImageIcon("img/sharebbs/share_list_off.png");
		listIc3 = new ImageIcon("img/sharebbs/share_list_ing.png");
		btn_List = new JButton(listIc1);
		btn_List.setRolloverIcon(listIc2);
		btn_List.setPressedIcon(listIc3);
		btn_List.setBorderPainted(false);
		btn_List.setContentAreaFilled(false);
		btn_List.setFocusPainted(false);
	
		btn_List.addActionListener(this);
		btn_List.setBounds(50, 570, 101, 41);
		
		//좋아요버튼
		ImageIcon liked_Img = new ImageIcon("img/sharebbs/btn_Liked.png");
		ImageIcon liked_Img_clicked = new ImageIcon("img/sharebbs/btn_Liked_clicked.png");
		
		btn_liked = new JToggleButton(liked_Img);
		btn_liked.setSelectedIcon(liked_Img_clicked);
		btn_liked.setFocusPainted(false);
		btn_liked.setContentAreaFilled(false);
		btn_liked.setBorderPainted(false);
		btn_liked.addActionListener(this);

		btn_liked.setBounds(610, 160, 30, 30);

		
		for (int i = 0; i < list.size(); i++) {
			if (dto.getSeq() == list.get(i)) {
				btn_liked.setSelected(true);
				break;
			}
		}

		Font liked_fork_Font = new Font("굴림",Font.BOLD,15);
		likelab.setBounds(650, 160, 30, 30);
		likelab.setFont(liked_fork_Font);
		likelab.setForeground(Color.WHITE);

		likelab.setText(dto.getLiked()+"");
		
		//포크 버튼 
		ImageIcon fork_Img1 = new ImageIcon("img/sharebbs/fork1.png");
		ImageIcon fork_Img2 = new ImageIcon("img/sharebbs/fork2.png");
		ImageIcon fork_Img3 = new ImageIcon("img/sharebbs/fork3.png");
		
		btn_fork = new JButton(fork_Img1);
		btn_fork.setRolloverIcon(fork_Img2);
		btn_fork.setPressedIcon(fork_Img3);
		btn_fork.setFocusPainted(false);
		btn_fork.setContentAreaFilled(false);
		btn_fork.setBorderPainted(false);
		btn_fork.addActionListener(this);

		btn_fork.setBounds(710, 160, 30, 30);

		
		forklab.setBounds(752, 160, 30, 30);
		forklab.setFont(liked_fork_Font);
		forklab.setForeground(Color.WHITE);
		forklab.setText(dto.getFork()+"");
		
		add(nickText);
		add(titleText);
		
		add(jScrol);
		add(code_backgorund);
		add(btn_List);
		add(btn_liked);
		add(btn_fork);
		add(likelab);
		add(forklab);
		
		setLayout(null);
		setOpaque(false);
		
		setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

	Singleton s = Singleton.getInstance();
			
		 
		if(e.getSource() == btn_List) {
			//Sharebbs.changePanel(new ShareList(ShareMain));
			ShareMain.changePanel(1, null);
			
		}else if (e.getSource() == btn_liked) {
			
			if (btn_liked.isSelected() == true) {
				ShareDao.clicklike(s.nowMember.getID(), dto.getNick(), dto.getIndseq(), dto.getSeq());
				dto.setLiked(dto.getLiked()+1);
			}else {
				ShareDao.clickunlike(s.nowMember.getID(), dto.getNick(), dto.getIndseq(), dto.getSeq());
				dto.setLiked(dto.getLiked()-1);
			}
			
			likelab.setText(dto.getLiked()+"");
			
		}else if(e.getSource() == btn_fork) {
			 ShareDao.codefork(dto);
			 dto.setFork(dto.getFork()+1);
			 forklab.setText(dto.getFork()+"");
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
