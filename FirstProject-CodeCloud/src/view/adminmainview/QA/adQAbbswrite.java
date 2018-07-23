package view.adminmainview.QA;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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

import dto.QAbbsDto;
import singleton.Singleton;
import textlimit.JTextFieldLimit;
import view.membermainview.QA.QAbbsMain;

public class adQAbbswrite extends JPanel implements ActionListener, WindowListener,FocusListener {


	JTextField titleText;
	JTextArea postArea;

	JScrollPane jScrol;


	ImageIcon commitIc1;
	ImageIcon commitIc2;
	ImageIcon commitIc3;
	JButton btn_Commit; // 답글 입력
	
	ImageIcon listIc1;
	ImageIcon listIc2;
	ImageIcon listIc3;
	JButton btn_list; // 글목록

	final int DETAIL = 0;
	final int LIST = 1;
	final int COMMENT = 2;
	final int COMMENT_UPDATE = 3;

	int state;

	adQAbbsMain adQAmian;
	QAbbsDto dto;


	String title_Hint = "제목을 입력해 주세요";
	
	public adQAbbswrite(adQAbbsMain QA, QAbbsDto dto, int state) {
		setOpaque(false);
		adQAmian = QA;
		this.dto = dto;
		this.state = state;


		Font titleFont = new Font("굴림",Font.BOLD, 40);
		titleText = new JTextField();
		titleText.setFont(titleFont);
		titleText.setCaretColor(Color.WHITE);
		titleText.setForeground(Color.WHITE);
		titleText.setDocument(new JTextFieldLimit(50));	//50자 제한
		titleText.setBounds(50, 100, 750, 50);
		titleText.setOpaque(false);
		titleText.setBorder(BorderFactory.createCompoundBorder(null, BorderFactory.createEmptyBorder(0, 0, 0, 0)));
		
		if(dto.getTitle().equals(""))
			titleText.setText(title_Hint);
		else
			titleText.setText(dto.getTitle());
		titleText.addFocusListener(this);
		
		Font contentFont = new Font("굴림", Font.BOLD, 20);

		postArea = new JTextArea();
		postArea.setFont(contentFont);
		postArea.setCaretColor(Color.WHITE);
		postArea.setForeground(Color.WHITE);
		postArea.setDocument(new JTextFieldLimit(4000));	//4000자 제한
		postArea.setBounds(110, 80, 310, 200);
		postArea.setOpaque(false);
		postArea.append(dto.getContent());

		// 스크롤바 0으로 줄여서 안보이게하는 코드
		jScrol = new JScrollPane(postArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jScrol.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
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
		
		// 입력 버튼
		// 입력 버튼
		commitIc1 = new ImageIcon("img/adminMain/QA/QA_save_on.png");
		commitIc2 = new ImageIcon("img/adminMain/QA/QA_save_off.png");
		commitIc3 = new ImageIcon("img/adminMain/QA/QA_save_ing.png");
		btn_Commit = new JButton(commitIc1);
		btn_Commit.setRolloverIcon(commitIc2);
		btn_Commit.setPressedIcon(commitIc3);
		btn_Commit.setBorderPainted(false);
		btn_Commit.setContentAreaFilled(false);
		btn_Commit.setFocusPainted(false);
		btn_Commit.addActionListener(this);

		btn_Commit.setBounds(700, 570, 101, 41);

		// 글 목록
		listIc1 = new ImageIcon("img/adminMain/QA/QA_list_on.png");
		listIc2 = new ImageIcon("img/adminMain/QA/QA_list_off.png");
		listIc3 = new ImageIcon("img/adminMain/QA/QA_list_ing.png");
		btn_list = new JButton(listIc1);
		btn_list.setRolloverIcon(listIc2);
		btn_list.setPressedIcon(listIc3);
		btn_list.setBorderPainted(false);
		btn_list.setContentAreaFilled(false);
		btn_list.setFocusPainted(false);
		btn_list.addActionListener(this);
		btn_list.setBounds(50, 570, 101, 41);

		add(titleText);
		add(jScrol);

		add(btn_Commit);
		add(btn_list);

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
		if (e.getSource() == btn_Commit) {
			Singleton s = Singleton.getInstance();

			if (state == COMMENT_UPDATE) {
				if (titleText.getText().equals(title_Hint) ||titleText.getText().equals("") || postArea.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "제목 내용을 입력하세요");
					return;
				}
				dto.setTitle(titleText.getText());
				dto.setContent(postArea.getText());
				s.qaDao.update(dto);

				// list로
				adQAmian.changePanel(LIST, new QAbbsDto());

			} else if (state == COMMENT) {
				if (titleText.getText().equals(title_Hint) ||titleText.getText().equals("") || postArea.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "제목 내용을 입력하세요");
					return;
				}
				// 상위 글의 SEQ가 그룹번호
				dto.setRef(dto.getSeq());

				int ref = dto.getRef();
				int dept = dto.getDept();
				++dept;
				// step최댓값 구해서 그다음에 추가하기
				int nextStep = s.qaDao.searchMaxStep(ref, dept);

				System.out.println(dto.getSeq());
				System.out.println(nextStep);

				dto.setStep(++nextStep);
				dto.setDept(dept);

				dto.setTitle(titleText.getText());
				dto.setContent(postArea.getText());
				dto.setNick(s.nowMember.getNick());
				dto.setDel(0);

				s.qaDao.insert(dto);
				adQAmian.changePanel(LIST, new QAbbsDto());
			}
		} else if (e.getSource() == btn_list) {
			adQAmian.changePanel(LIST, new QAbbsDto());
			
		}
	}
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == titleText) {
			if (titleText.getText().equals(title_Hint))
				titleText.setText("");
			titleText.setForeground(Color.white);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == titleText) {
			if (titleText.getText().length() == 0) {
				titleText.setText(title_Hint);
				titleText.setForeground(Color.WHITE);
			}
		}
	}
}
