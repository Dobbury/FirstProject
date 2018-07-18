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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import dao.QAbbsDao;
import dto.QAbbsDto;
import singleton.Singleton;

public class QAbbsDetail extends JPanel implements ActionListener, WindowListener {

	JTextField titleText;// 닉넴 텍스트필드
	JTextField titleText2;// 제목 텍스트필드
	JTextField titleText3;// content 텍스트 필드

	JTextArea postArea;
	JPanel comments;

	JScrollPane jScrol;

	private JButton btn_Update; // 게시판 글 정보수정
	private JButton btn_List;// 목록으로
	private JButton btn_delete;// 삭제버튼

	final int DETAIL = -2;
	final int LIST = -1;
	final int INSERT = 0;
	final int UPDATE = 1;

	QAbbsMain QAmain;
	QAbbsDto dto;

	public QAbbsDetail(QAbbsMain QA, QAbbsDto dto) {

		setOpaque(false);
		QAmain = QA;
		this.dto = dto;

		System.out.println(dto.toString() + "회원 디테일 확인용");

		// 제목
		titleText2 = new JTextField();
		titleText2.setBounds(50, 100, 310, 50);
		titleText2.setText(dto.getTitle());
		titleText2.setEditable(false);

		// 닉네임
		titleText = new JTextField();
		titleText.setBounds(50, 160, 310, 30);
		titleText.setText(dto.getNick());
		titleText.setEditable(false);

		// 컨텐츠
		postArea = new JTextArea();
		postArea.append(dto.getContent());
		postArea.setEditable(false);

		jScrol = new JScrollPane(postArea);
		jScrol.setBounds(50, 200, 750, 350);

		// 글 목록
		btn_List = new JButton("글 목록");
		btn_List.addActionListener(this);
		btn_List.setBounds(50, 570, 100, 40);

		// 삭제
		btn_delete = new JButton("삭제");
		btn_delete.addActionListener(this);
		btn_delete.setBounds(550, 570, 100, 50);

		// 수정
		btn_Update = new JButton("수정");
		btn_Update.addActionListener(this);
		btn_Update.setBounds(700, 570, 100, 50);

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
				
				QAmain.changePanel(1, new QAbbsDto(), LIST);
			}
		});

		add(titleText);
		add(titleText2);
		
		add(jScrol);
		
		add(btn_Update);
		add(btn_List);
		add(btn_delete);

		Singleton s = Singleton.getInstance();

		if (!s.nowMember.getNick().equals(dto.getNick())) {// 접속자와 게시글의 닉네임이 다르면 버튼 비활성화
			btn_Update.setVisible(false);
			btn_delete.setVisible(false);
		}
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
		Singleton s = Singleton.getInstance();

		// TODO Auto-generated method stub
		if (e.getSource() == btn_List) {
			QAmain.changePanel(1, new QAbbsDto(), LIST);

		} else if (e.getSource() == btn_Update) {
			QAmain.changePanel(3, dto, UPDATE);

		}
	}

}
