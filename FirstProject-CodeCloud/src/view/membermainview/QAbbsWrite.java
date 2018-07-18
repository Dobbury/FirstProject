package view.membermainview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import dto.QAbbsDto;
import singleton.Singleton;
import textLimit.JTextFieldLimit;

public class QAbbsWrite extends JPanel implements WindowListener, ActionListener {

	JLabel titleLabel;// 제목
	JLabel titleLabel2;// 내용

	JTextField titleText;
	JTextArea postArea;

	JScrollPane jScrol;

	JButton btn_Commit; // 게시판 글 입력
	JButton btn_Cancle; // 게시판 글 입력 취소

	final int DETAIL = -2;
	final int LIST = -1;
	final int INSERT = 0;
	final int UPDATE = 1;


	QAbbsMain QAmain;
	QAbbsDto dto;

	int state;

	public QAbbsWrite(QAbbsMain QA, QAbbsDto dto,int state) {
		setOpaque(false);
		QAmain = QA;
		this.dto = dto;

		this.state=state;
		
		//타이틀
		titleText = new JTextField();
		titleText.setDocument(new JTextFieldLimit(50));//50자 제한
		titleText.setBounds(100, 50, 310, 30);
		titleText.setText(dto.getTitle());


		postArea = new JTextArea();
		postArea.setDocument(new JTextFieldLimit(4000));	//4000자 제한
		postArea.append(dto.getContent());

		//스크롤바 0으로 줄여서 안보이게하는 코드
		jScrol = new JScrollPane(postArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
		jScrol.getVerticalScrollBar().setPreferredSize (new Dimension(0,0));
				
		jScrol.setBounds(50, 150, 650, 400);

		// 입력 버튼
		btn_Commit = new JButton("저 장");
		btn_Commit.addActionListener(this);
		btn_Commit.setBounds(600, 570, 100, 40);

		// 취소버튼
		btn_Cancle = new JButton("글 목록");
		btn_Cancle.addActionListener(this);
		btn_Cancle.setBounds(50, 570, 100, 40);

		add(titleText);
		add(jScrol);

		add(btn_Commit);
		add(btn_Cancle);

		setLayout(null);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btn_Commit) {// 확인
			Singleton s = Singleton.getInstance();
			
			if (state == UPDATE) {
				dto.setTitle(titleText.getText());
				dto.setContent(postArea.getText());
				s.qaDao.update(dto);

				QAmain.changePanel(1, new QAbbsDto(),UPDATE);

			} else if (state == INSERT) {

				dto.setNick(s.nowMember.getNick());
			
				dto.setTitle(titleText.getText());
				dto.setContent(postArea.getText());

				dto.setDel(0); // 0이 삭제 되지 않은 게시글 , 1이 삭제된 게시글
				s.qaDao.insert(dto);

				QAmain.changePanel(1, new QAbbsDto(),INSERT);

			}
		} else if (e.getSource() == btn_Cancle) {// 취소

			QAmain.changePanel(1, new QAbbsDto(),INSERT);
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

}
