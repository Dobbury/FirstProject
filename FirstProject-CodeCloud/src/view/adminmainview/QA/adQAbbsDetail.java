package view.adminmainview.QA;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import dao.MemberDao;
import dao.QAbbsDao;
import dto.QAbbsDto;
import singleton.Singleton;

public class adQAbbsDetail extends JPanel implements ActionListener, WindowListener {

	
	final int DETAIL = 0;
	final int LIST = 1;
	final int COMMENT = 2;
	final int COMMENT_UPDATE = 3;
	

	JLabel nickText;// 닉넴
	JLabel titleText;// 제목

	JTextArea postArea;
	JPanel comments;

	JScrollPane jScrol;

	
	ImageIcon commentIc1;
	ImageIcon commentIc2;
	ImageIcon commentIc3;
	private JButton btn_comment; // 게시글 답변달기
	
	ImageIcon updateIc1;
	ImageIcon updateIc2;
	ImageIcon updateIc3;
	private JButton btn_Update; // 게시판 글 정보수정
	
	ImageIcon listIc1;
	ImageIcon listIc2;
	ImageIcon listIc3;
	private JButton btn_List;// 목록으로
	
	ImageIcon deleteIc1;
	ImageIcon deleteIc2;
	ImageIcon deleteIc3;
	private JButton btn_delete;// 삭제버튼

	adQAbbsMain adQAmian;
	QAbbsDto dto;

	public adQAbbsDetail(adQAbbsMain QA, QAbbsDto dto) {

		setOpaque(false);
		adQAmian = QA;
		this.dto = dto;

		// 타이틀
		Font titleFont = new Font("굴림", Font.BOLD, 40);
		titleText = new JLabel();
		titleText.setFont(titleFont);
		titleText.setForeground(Color.WHITE);
		titleText.setBounds(65, 100, 310, 50);
		titleText.setText(dto.getTitle());

		// 닉네임
		Font nickFont = new Font("굴림", Font.BOLD, 25);
		nickText = new JLabel();
		nickText.setFont(nickFont);
		nickText.setForeground(Color.WHITE);
		nickText.setBounds(65, 160, 310, 30);
		nickText.setText(dto.getNick());

		
		postArea = new JTextArea();
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
		ImageIcon content_back_Img = new ImageIcon("img/adminMain/QA/adQA_detail_content_background.png");
				
		JLabel content_backgorund = new JLabel();
		content_backgorund.setIcon(content_back_Img);
		content_backgorund.setBounds(50,200,750,350);
				
		// 게시글 답글달기
		commentIc1 = new ImageIcon("img/adminMain/QA/comment_on.png");
		commentIc2 = new ImageIcon("img/adminMain/QA/comment_off.png");
		commentIc3 = new ImageIcon("img/adminMain/QA/comment_ing.png");
		btn_comment = new JButton(commentIc1);
		btn_comment.setRolloverIcon(commentIc2);
		btn_comment.setPressedIcon(commentIc3);
		btn_comment.setBorderPainted(false);
		btn_comment.setContentAreaFilled(false);
		btn_comment.setFocusPainted(false);
		btn_comment.addActionListener(this);
		btn_comment.setBounds(400, 570, 101, 41);

		// 글 목록
		listIc1 = new ImageIcon("img/adminMain/QA/QA_list_on.png");
		listIc2 = new ImageIcon("img/adminMain/QA/QA_list_off.png");
		listIc3 = new ImageIcon("img/adminMain/QA/QA_list_ing.png");
		btn_List = new JButton(listIc1);
		btn_List.setRolloverIcon(listIc2);
		btn_List.setPressedIcon(listIc3);
		btn_List.setBorderPainted(false);
		btn_List.setContentAreaFilled(false);
		btn_List.setFocusPainted(false);
		btn_List.addActionListener(this);
		btn_List.setBounds(50, 570, 101, 41);
		
		//삭제
		deleteIc1 = new ImageIcon("img/adminMain/QA/QA_delete_on.png");
		deleteIc2 = new ImageIcon("img/adminMain/QA/QA_delete_off.png");
		deleteIc3 = new ImageIcon("img/adminMain/QA/QA_delete_ing.png");
		btn_delete = new JButton(deleteIc1);
		btn_delete.setRolloverIcon(deleteIc2);
		btn_delete.setPressedIcon(deleteIc3);
		btn_delete.setBorderPainted(false);
		btn_delete.setContentAreaFilled(false);
		btn_delete.setFocusPainted(false);
		btn_delete.addActionListener(this);
		btn_delete.setBounds(550, 570, 101, 41);

		// 수정
		updateIc1 = new ImageIcon("img/adminMain/QA/QA_update_on.png");
		updateIc2 = new ImageIcon("img/adminMain/QA/QA_update_off.png");
		updateIc3 = new ImageIcon("img/adminMain/QA/QA_update_ing.png");
		btn_Update = new JButton(updateIc1);
		btn_Update.setRolloverIcon(updateIc2);
		btn_Update.setPressedIcon(updateIc3);
		btn_Update.setBorderPainted(false);
		btn_Update.setContentAreaFilled(false);
		btn_Update.setFocusPainted(false);
		btn_Update.addActionListener(this);

		btn_Update.setBounds(700, 570, 101, 41);

		Singleton s = Singleton.getInstance();

		// 수정 버튼의 비활성화 (다른 id일 경우만)

		if (!dto.getNick().equals(s.nowMember.getNick())) {
			btn_Update.setEnabled(false);
			btn_delete.setEnabled(false);
		}
		// 삭제 액션 구현
		btn_delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Singleton s = Singleton.getInstance();

				if (s.qaDao.deletebbs(dto.getSeq())) {
					JOptionPane.showMessageDialog(null, "글을 삭제하였습니다");
				} else {
					JOptionPane.showMessageDialog(null, "글이 삭제되지 않았습니다");
				}
				adQAmian.changePanel(LIST, dto);
			}
		});


		add(nickText);
		add(titleText);
		add(jScrol);
		add(btn_comment);
		add(btn_List);
		add(btn_delete);
		add(btn_Update);
		
		add(content_backgorund);
		setLayout(null);

		setVisible(true);

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btn_comment) {
			QAbbsDto dto = new QAbbsDto();
			dto.setSeq(this.dto.getSeq());
			adQAmian.changePanel(COMMENT, dto);

		} else if (e.getSource() == btn_List) {
			adQAmian.changePanel(LIST, dto);

		} else if (e.getSource() == btn_Update) {

			adQAmian.changePanel(COMMENT_UPDATE, dto);

		}
	}

}
