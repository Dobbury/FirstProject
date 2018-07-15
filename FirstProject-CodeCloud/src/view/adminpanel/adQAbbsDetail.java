package view.adminpanel;

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

public class adQAbbsDetail extends JPanel implements ActionListener, WindowListener, MouseListener {

	JLabel titleLabel;// 닉네임
	JLabel titleLabel2;// 제목
	JLabel titleLabel3;// content

	JTextField titleText;// 닉넴 텍스트필드
	JTextField titleText2;// 제목 텍스트필드
	JTextField titleText3;// content 텍스트 필드
	JTextArea postArea;
	JPanel comments;
	JScrollPane jScrol;

	private JButton btn_comment; // 게시글 코멘트 버튼
	private JButton btn_List;// 목록으로
	final int INSERT = 0;
	final int UPDATE = 1;
	
	
	public adQAbbsDetail() {
		titleLabel = new JLabel("닉네임: ");
		titleLabel.setBounds(100, 100, 50, 30);

		titleText = new JTextField();
		titleText.setBounds(200,100, 310, 30);
		//titleText.setText(dto.getNick());
		titleText.setEditable(false);

		titleLabel2 = new JLabel("제목: ");
		titleLabel2.setBounds(100, 150, 50, 30);

		titleText2 = new JTextField();
		titleText2.setBounds(200, 150, 310, 30);
		//titleText2.setText(dto.getTitle());
		titleText2.setEditable(false);

		titleLabel3 = new JLabel("CONTENT: ");
		titleLabel3.setBounds(100, 180, 100, 60);
		
		postArea = new JTextArea();
		postArea.setBounds(200, 200, 300, 300);
		//postArea.append(dto.getContent());
		postArea.setEditable(false);
		
		jScrol = new JScrollPane(postArea);
		jScrol.setBounds(200, 200, 300, 300);

		// 수정
		btn_comment = new JButton("답글달기");
		btn_comment.addActionListener(this);
		btn_comment.setBounds(100, 550, 110, 50);

		// 글 목록
		btn_List = new JButton("글 목록");
		btn_List.addActionListener(this);
		btn_List.setBounds(500, 550, 110, 50);

		add(titleLabel);
		add(titleLabel2);
		add(titleLabel3);		
		add(titleText);
		add(titleText2);		
		add(jScrol);
		add(btn_comment);
		add(btn_List);

		setLayout(null);
		setBackground(Color.PINK);
		setBounds(50, 50, 300, 300);
		
		setVisible(true);
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

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
		if(e.getSource() == btn_comment) {
			
		}

	}

}
