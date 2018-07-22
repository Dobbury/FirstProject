package view.membermainview.QA;

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
import javax.swing.JFrame;
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

public class QAbbsWrite extends JPanel implements WindowListener, ActionListener,FocusListener {

	JTextField titleText;
	JTextArea postArea;

	JScrollPane jScrol;

	ImageIcon commitIc1;
	ImageIcon commitIc2;
	ImageIcon commitIc3;
	JButton btn_Commit; // 게시판 글 입력
	
	ImageIcon cancleIc1;
	ImageIcon cancleIc2;
	ImageIcon cancleIc3;
	JButton btn_Cancle; // 게시판 글 입력 취소

	final int DETAIL = -2;
	final int LIST = -1;
	final int INSERT = 0;
	final int UPDATE = 1;

	QAbbsMain QAmain;
	QAbbsDto dto;

	int state;

	String title_Hint = "제목을 입력해 주세요";
	
	public QAbbsWrite(QAbbsMain QA, QAbbsDto dto,int state) {
		setOpaque(false);
		QAmain = QA;
		this.dto = dto;
		this.state=state;
		

		//타이틀
		Font titleFont = new Font("굴림",Font.BOLD, 40);
		titleText = new JTextField();
		titleText.setFont(titleFont);
		titleText.setForeground(Color.WHITE);
		titleText.setOpaque(false);
		titleText.setBorder(BorderFactory.createCompoundBorder(null, BorderFactory.createEmptyBorder(0, 0, 0, 0)));
		titleText.setBounds(65, 100, 750, 50);
			
		if(dto.getTitle().equals(""))
			titleText.setText(title_Hint);
		else
			titleText.setText(dto.getTitle());
		titleText.addFocusListener(this);
		
		Font contentFont = new Font("굴림", Font.BOLD, 20);

		//컨텐츠
		postArea = new JTextArea();
		postArea.append(dto.getContent());
		postArea.setFont(contentFont);
		postArea.setOpaque(false);
		postArea.setForeground(Color.WHITE);

		// 코드 배경
		ImageIcon content_back_Img = new ImageIcon("img/QAbbs/QA_content_background.png");

		JLabel content_backgorund = new JLabel();
		content_backgorund.setIcon(content_back_Img);
		content_backgorund.setBounds(50, 200, 750, 350);
	

		jScrol = new JScrollPane(postArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
		jScrol.getVerticalScrollBar().setPreferredSize (new Dimension(0,0));
		
		jScrol.setOpaque(false);
		jScrol.getViewport().setOpaque(false);
		jScrol.setBounds(50, 200, 750, 350);

		jScrol.setBorder(BorderFactory.createCompoundBorder(null,
	            BorderFactory.createEmptyBorder(20, 20, 20, 20)));
		
		// 입력 버튼
		commitIc1 = new ImageIcon("img/QAbbs/QA_save_on.png");
		commitIc2 = new ImageIcon("img/QAbbs/QA_save_off.png");
		commitIc3 = new ImageIcon("img/QAbbs/QA_save_ing.png");
		btn_Commit = new JButton(commitIc1);
		btn_Commit.setRolloverIcon(commitIc2);
		btn_Commit.setPressedIcon(commitIc3);
		btn_Commit.setBorderPainted(false);
		btn_Commit.setContentAreaFilled(false);
		btn_Commit.setFocusPainted(false);
		btn_Commit.addActionListener(this);
		
		btn_Commit.setBounds(700, 570, 101, 41);

		// 글 목록
		cancleIc1 = new ImageIcon("img/QAbbs/QA_list_on.png");
		cancleIc2 = new ImageIcon("img/QAbbs/QA_list_off.png");
		cancleIc3 = new ImageIcon("img/QAbbs/QA_list_ing.png");
		btn_Cancle = new JButton(cancleIc1);
		btn_Cancle.setRolloverIcon(cancleIc2);
		btn_Cancle.setPressedIcon(cancleIc3);
		btn_Cancle.setBorderPainted(false);
		btn_Cancle.setContentAreaFilled(false);
		btn_Cancle.setFocusPainted(false);
		btn_Cancle.addActionListener(this);
		
		btn_Cancle.setBounds(50, 570, 101, 41);

		add(titleText);
		add(jScrol);

		add(btn_Commit);
		add(btn_Cancle);

		setLayout(null);
		setVisible(true);
		add(content_backgorund);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btn_Commit) {// 확인
			Singleton s = Singleton.getInstance();
			
			if (state == UPDATE) {
				if (titleText.getText().equals(title_Hint) ||titleText.getText().equals("") || postArea.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "제목 내용을 입력하세요");
					return;
				}
				dto.setTitle(titleText.getText());
				dto.setContent(postArea.getText());
				s.qaDao.update(dto);

				QAmain.changePanel(LIST, new QAbbsDto());

			} else if (state == INSERT) {
				if (titleText.getText().equals(title_Hint) ||titleText.getText().equals("") || postArea.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "제목 내용을 입력하세요");
					return;
				}
				dto.setNick(s.nowMember.getNick());			
				dto.setTitle(titleText.getText());
				dto.setContent(postArea.getText());
				dto.setDel(0); // 0이 삭제 되지 않은 게시글 , 1이 삭제된 게시글
				s.qaDao.insert(dto);

				QAmain.changePanel(LIST, new QAbbsDto());

			}
		} else if (e.getSource() == btn_Cancle) {// 취소
			QAmain.changePanel(LIST, new QAbbsDto());
		}
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
