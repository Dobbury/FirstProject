package view.adminmainview.member;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import dao.MemberDao;
import dao.QAbbsDao;
import dto.MemberDto;
import dto.QAbbsDto;
import singleton.Singleton;
import view.adminmainview.member.adMemberbbsUpdate.MyPanel;

public class adMemberbbsDetail extends JPanel implements ActionListener, WindowListener {

	final int DETAIL = 0;
	final int LIST = 1;
	final int UPDATE = 2;
	
	private ImageIcon signupIc1;
	private ImageIcon signupIc2;
	private ImageIcon signupIc3;
	private JButton btn_Update;
	
	private ImageIcon userIc;
	private JLabel userLabel;

	private JTextField id_text;

	private JTextField nick_text;
	
	private JPasswordField pwd_text;

	String pwd_Hint = "패스워드 입력";
	
	BufferedImage img = null;
	BufferedImage userImg = null;
	
	adMemberbbsMain adMembermian;
	MemberDto dto;
	
	public adMemberbbsDetail(adMemberbbsMain adMembermian, MemberDto dto) {
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 342, 596);
		layeredPane.setLayout(null);
		
		setOpaque(false);
		
		this.adMembermian = adMembermian;
		this.dto = dto;
		
		// ---------------------------------------------------------------------------
		// 배경화면
		try {
			img = ImageIO.read(new File("img/signUp/signBack.png"));
		} catch (IOException e) {
			System.out.println("이미지 불러오기 실패");
			System.exit(0);
		}

		MyPanel panel = new MyPanel();
		panel.setBounds(0, 0, 341, 597);

		// ---------------------------------------------------------------------------
		// 버튼
		// 회원가입
		signupIc1 = new ImageIcon("img/signUp/btn_sign1.png");
		signupIc2 = new ImageIcon("img/signUp/btn_sign2.png");
		signupIc3 = new ImageIcon("img/signUp/btn_sign3.png");
		btn_Update = new JButton(signupIc1);
		btn_Update.setRolloverIcon(signupIc2);
		btn_Update.setPressedIcon(signupIc3);
		btn_Update.setBorderPainted(false);
		btn_Update.setContentAreaFilled(false);
		btn_Update.setFocusPainted(false);
		btn_Update.setBounds(20, 484, 295, 50);
		btn_Update.addActionListener(this);
		layeredPane.add(btn_Update);
	
		// ---------------------------------------------------------------------------
		// 사용자 프로필 이미지
		userIc = new ImageIcon(dto.getProfile_Img());
		userLabel = new JLabel(userIc);
		userLabel.setLayout(null);
		userLabel.setBounds(105, 100, 130, 130);
		layeredPane.add(userLabel);

		// 아이디 입력 textField
		id_text = new JTextField();
		id_text.setText(dto.getID());
		id_text.setBounds(70, 303, 220, 30);
		id_text.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		id_text.setOpaque(false);
		id_text.setEditable(false);
		layeredPane.add(id_text);

		pwd_text = new JPasswordField();
		pwd_text.setText(pwd_Hint);
		pwd_text.setForeground(Color.WHITE);
		pwd_text.setBounds(70, 366, 220, 30);
		pwd_text.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		pwd_text.setOpaque(false);
		pwd_text.setEditable(false);
		layeredPane.add(pwd_text);

		nick_text = new JTextField();
		nick_text.setForeground(Color.WHITE);
		nick_text.setBounds(70, 429, 220, 30);
		nick_text.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		nick_text.setOpaque(false);
		nick_text.setEditable(false);
		layeredPane.add(nick_text);

		// 기본 설정
		// setBackground(new Color(0,0,0,122)); 배경 투명 설정
		setLayout(null);
		setBounds(0, 0, 341, 597);
		add(layeredPane);
		setVisible(true);
		layeredPane.add(panel);
	}

	class MyPanel extends JPanel {
		public void paint(Graphics g) {
			// g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
			g.drawImage(img, 0, 0, null);

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
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btn_Update) {
			int choice = JOptionPane.YES_NO_OPTION;
			choice = JOptionPane.showConfirmDialog(null, "'"+dto.getID()+"' 회원의 비밀번호를 변경 하시겠습니까?", "WARNING", choice);

			if (choice == 0) {
				adMembermian.changePanel(UPDATE, dto);
				
			}
		} 
	}
}
