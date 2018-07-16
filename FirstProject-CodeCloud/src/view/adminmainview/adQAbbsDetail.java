package view.adminmainview;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import dao.MemberDao;
import dao.QAbbsDao;
import dto.QAbbsDto;
import singleton.Singleton;



public class adQAbbsDetail extends JPanel implements ActionListener, WindowListener {


	JLabel titleLabel;// 닉네임
	JLabel titleLabel2;// 제목
	JLabel titleLabel3;// content

	JTextField titleText;// 닉넴 텍스트필드
	JTextField titleText2;// 제목 텍스트필드
	JTextField titleText3;// content 텍스트 필드

	JTextArea postArea;
	JPanel comments;

	JScrollPane jScrol;

	private JButton btn_comment; // 게시글 답변달기
	private JButton btn_List;// 목록으로
	private JButton btn_delete;// 삭제
	private JButton btn_update;// 수정

	final int INSERT = 0;
	final int UPDATE = 1;

	adQAbbsMain adQAmian;

	QAbbsDto dto;

	public adQAbbsDetail(adQAbbsMain QA, QAbbsDto dto) {
		adQAmian = QA;
		this.dto = dto;


		titleLabel = new JLabel("닉네임: ");
		titleLabel.setBounds(100, 100, 50, 30);

		titleText = new JTextField();

		titleText.setBounds(200, 100, 310, 30);
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

		// 게시글 답글달기
		btn_comment = new JButton("답글 달기");
		btn_comment.addActionListener(this);
		btn_comment.setBounds(200, 550, 110, 50);

		// 글 목록
		btn_List = new JButton("글 목록");
		btn_List.addActionListener(this);

		btn_List.setBounds(400, 550, 110, 50);

		// 삭제
		btn_delete = new JButton("삭제");
		btn_delete.addActionListener(this);
		btn_delete.setBounds(550, 550, 110, 50);

		// 수정
		btn_update = null;
		btn_update = new JButton("수정");
		btn_update.addActionListener(this);
		btn_update.setBounds(700, 550, 110, 50);

		Singleton s = Singleton.getInstance();
		// 수정 버튼의 비활성화 (같은 id일 경우만)
		if (!dto.getNick().equals(s.nowMember.getNick())) {
			btn_update.setEnabled(false);
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
				adQAmian.changePanel(3, dto,0);
			}
		});

		add(titleLabel);
		add(titleLabel2);
		add(titleLabel3);
		add(titleText);
		add(titleText2);
		add(jScrol);
		add(btn_comment);
		add(btn_List);
		add(btn_delete);
		add(btn_update);


		setLayout(null);
		setBackground(Color.PINK);
		setBounds(50, 50, 300, 300);

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
			adQAmian.changePanel(2, dto,2);

		} else if (e.getSource() == btn_List) {
			adQAmian.changePanel(3, dto,0);

		} else if (e.getSource() == btn_update) {
			adQAmian.changePanel(2, dto,1);

		}
	}

}
