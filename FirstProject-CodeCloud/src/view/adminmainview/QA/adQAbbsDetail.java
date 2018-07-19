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

	private JButton btn_comment; // 게시글 답변달기
	private JButton btn_List;// 목록으로
	private JButton btn_delete;// 삭제
	private JButton btn_update;// 수정


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
		postArea.setEditable(false);

		//스크롤바 0으로 줄여서 안보이게하는 코드
		jScrol = new JScrollPane(postArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
		jScrol.getVerticalScrollBar().setPreferredSize (new Dimension(0,0));
		jScrol.setBounds(50, 200, 750, 350);

		// 게시글 답글달기
		btn_comment = new JButton("답글 달기");
		btn_comment.addActionListener(this);
		btn_comment.setBounds(50, 570, 110, 40);

		// 글 목록
		btn_List = new JButton("글 목록");
		btn_List.addActionListener(this);
		btn_List.setBounds(400, 570, 110, 40);

		// 삭제
		btn_delete = new JButton("삭제");
		btn_delete.addActionListener(this);
		btn_delete.setBounds(550, 570, 110, 40);

		// 수정
		//btn_update = null;
		btn_update = new JButton("수정");
		btn_update.addActionListener(this);
		btn_update.setBounds(700, 570, 110, 40);

		Singleton s = Singleton.getInstance();

		// 수정 버튼의 비활성화 (다른 id일 경우만)

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
				adQAmian.changePanel(LIST, dto);
			}
		});


		add(nickText);
		add(titleText);
		add(jScrol);
		add(btn_comment);
		add(btn_List);
		add(btn_delete);
		add(btn_update);

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

		} else if (e.getSource() == btn_update) {

			adQAmian.changePanel(COMMENT_UPDATE, dto);

		}
	}

}
