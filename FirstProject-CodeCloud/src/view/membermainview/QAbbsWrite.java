package view.membermainview;

import java.awt.Color;
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

import dto.QAbbsDto;
import singleton.Singleton;

public class QAbbsWrite extends JPanel implements WindowListener, ActionListener {

	JLabel titleLabel;// 제목
	JLabel titleLabel2;// 내용

	JTextField titleText;
	JTextArea postArea;

	JScrollPane jScrol;

	JButton btn_Commit; // 게시판 글 입력
	JButton btn_Cancle; // 게시판 글 입력 취소

	final int INSERT = 0;
	final int UPDATE = 1;
	final int COMMENT = 2;

	QAbbsMain QAmain;
	QAbbsDto dto;

	int state;

	public QAbbsWrite(QAbbsMain QA, QAbbsDto dto) {
		QAmain = QA;
		this.dto = dto;

		titleLabel = new JLabel("제목: ");
		titleLabel.setBounds(50, 10, 50, 30);

		titleText = new JTextField();
		titleText.setBounds(110, 10, 310, 30);
		titleText.setText(dto.getTitle());

		titleLabel2 = new JLabel("내용 ");
		titleLabel2.setBounds(50, 150, 50, 30);

		postArea = new JTextArea();
		postArea.setBounds(110, 80, 310, 200);
		postArea.append(dto.getContent());

		jScrol = new JScrollPane(postArea);
		jScrol.setBounds(110, 80, 310, 200);

		// 입력 버튼
		btn_Commit = new JButton("확 인");
		btn_Commit.addActionListener(this);
		btn_Commit.setBounds(45, 305, 110, 50);

		// 취소버튼
		btn_Cancle = new JButton("취 소");
		btn_Cancle.addActionListener(this);
		btn_Cancle.setBounds(335, 305, 110, 50);

		add(titleLabel);
		add(titleLabel2);
		add(titleText);
		add(jScrol);

		add(btn_Commit);
		add(btn_Cancle);

		setLayout(null);
		setBackground(Color.PINK);
		setBounds(50, 50, 300, 300);

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

				QAmain.changePanel(1, new QAbbsDto());

			} else if (state == INSERT) {

				dto.setNick(s.nowMember.getNick());
			
				dto.setTitle(titleText.getText());
				dto.setContent(postArea.getText());

				dto.setDel(0); // 0이 삭제 되지 않은 게시글 , 1이 삭제된 게시글
				s.qaDao.insert(dto);

				QAmain.changePanel(1, new QAbbsDto());

			}
		} else if (e.getSource() == btn_Cancle) {// 취소

			QAmain.changePanel(1, new QAbbsDto());
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
