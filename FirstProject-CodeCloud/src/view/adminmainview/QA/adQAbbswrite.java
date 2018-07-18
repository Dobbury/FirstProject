package view.adminmainview.QA;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dto.QAbbsDto;
import singleton.Singleton;
import view.membermainview.QAbbsMain;

public class adQAbbswrite extends JPanel implements ActionListener, WindowListener {

	JLabel titleLabel;// 제목
	JLabel titleLabel2;// 내용

	JTextField titleText;
	JTextArea postArea;

	JScrollPane jScrol;

	JButton btn_Commit; // 답글 입력
	JButton btn_Cancle; // 답글 입력 취소

	final int DETAIL = 0;
	final int LIST = 1;
	final int COMMENT = 2;
	final int COMMENT_UPDATE = 3;

	int state;

	adQAbbsMain adQAmian;
	QAbbsDto dto;

	public adQAbbswrite(adQAbbsMain QA, QAbbsDto dto, int state) {

		setOpaque(false);
		adQAmian = QA;
		this.dto = dto;
		this.state = state;

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
				dto.setTitle(titleText.getText());
				dto.setContent(postArea.getText());
				s.qaDao.update(dto);

				// list로
				adQAmian.changePanel(LIST, new QAbbsDto());

			} else if (state == COMMENT) {
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
		} else if (e.getSource() == btn_Cancle) {
			adQAmian.changePanel(LIST, new QAbbsDto());
		}
	}
}
