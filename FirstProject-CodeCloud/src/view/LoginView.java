package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import singleton.Singleton;


public class LoginView extends JFrame implements ActionListener,FocusListener {
	
	private Singleton s = Singleton.getInstance();
	
	private ImageIcon startIc1;
	private ImageIcon startIc2;
	private ImageIcon startIc3;
	private JButton btn_Login;
	
	private ImageIcon closeIc1;
	private ImageIcon closeIc2;
	private ImageIcon closeIc3;
	private JButton btn_Close;
	
	private ImageIcon accountIc1;
	private ImageIcon accountIc2;
	private ImageIcon accountIc3;
	private JButton btn_Signup;
	
	private JTextField id_text;
	private JPasswordField pwd_text;

	BufferedImage img = null;
	
	String id_Hint="ID 입력";
	String pwd_Hint="패스워드 입력";

	public LoginView() {
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 342, 596);
		layeredPane.setLayout(null);

		// ---------------------------------------------------------------------------
		// 배경화면
		try {
			img = ImageIO.read(new File("img/longin/loginback.png"));
		} catch (IOException e) {
			System.out.println("이미지 불러오기 실패");
			System.exit(0);
		}

		MyPanel panel = new MyPanel();
		panel.setBounds(0, 0, 341, 597);

		// ---------------------------------------------------------------------------
		// 버튼
		
		// 로그인
		startIc1 = new ImageIcon("img/longin/btn_Login1.png");
		startIc2 = new ImageIcon("img/longin/btn_Login2.png");
		startIc3 = new ImageIcon("img/longin/btn_Login3.png");
		btn_Login = new JButton(startIc1);
		btn_Login.setRolloverIcon(startIc2);
		btn_Login.setPressedIcon(startIc3);
		btn_Login.setBorderPainted(false);
		btn_Login.setContentAreaFilled(false);
		btn_Login.setFocusPainted(false);
		btn_Login.setBounds(20, 484, 295, 50);
		btn_Login.addActionListener(this);
		layeredPane.add(btn_Login);
		
		// 닫기
		closeIc1 = new ImageIcon("img/longin/close1.png");
		closeIc2 = new ImageIcon("img/longin/close2.png");
		closeIc3 = new ImageIcon("img/longin/close3.png");
		btn_Close = new JButton(closeIc1);
		btn_Close.setRolloverIcon(closeIc2);
		btn_Close.setPressedIcon(closeIc3);
		btn_Close.setBorderPainted(false);
		btn_Close.setContentAreaFilled(false);
		btn_Close.setFocusPainted(false);
		btn_Close.setBounds(313, 10, 16, 16);
		btn_Close.addActionListener(this);
		layeredPane.add(btn_Close);
		
		
		// 회원가입
		accountIc1 = new ImageIcon("img/longin/account1.png");
		accountIc2 = new ImageIcon("img/longin/account2.png");
		accountIc3 = new ImageIcon("img/longin/account3.png");
		btn_Signup = new JButton(accountIc1);
		btn_Signup.setRolloverIcon(accountIc2);
		btn_Signup.setPressedIcon(accountIc3);
		btn_Signup.setBorderPainted(false);
		btn_Signup.setContentAreaFilled(false);
		btn_Signup.setFocusPainted(false);
		btn_Signup.setBounds(20, 560, 76, 8);
		btn_Signup.addActionListener(this);
		layeredPane.add(btn_Signup);
		
		
		//id 텍스트 필드
		id_text = new JTextField();
		id_text.setText(id_Hint);
		id_text.setForeground(Color.WHITE);
		id_text.addFocusListener(this);
		id_text.setBounds(70, 366, 220, 30);
		id_text.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		id_text.setOpaque(false);
		layeredPane.add(id_text);

		// pwd 텍스트 필드
		pwd_text = new JPasswordField();
		pwd_text.setText(pwd_Hint);
		pwd_text.setForeground(Color.WHITE);
		pwd_text.addFocusListener(this);
		pwd_text.setBounds(70, 429, 220, 30);
		pwd_text.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		pwd_text.setOpaque(false);
		layeredPane.add(pwd_text);
				
		// ---------------------------------------------------------------------------
		// 기본 설정
		setUndecorated(true); // 상단 닫기 최소화 설정
		// setBackground(new Color(0,0,0,122)); 배경 투명 설정
		setTitle("로그인테스트");
		setLayout(null);
		setBounds(0, 0, 341, 597);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btn_Close) {
			System.exit(0);
		}
		if(e.getSource() == btn_Login) {
			boolean result =s.MemCtrl.loginCheck(id_text.getText(), pwd_text.getText());
			if(result == true) {
				new MemberMainView();
				this.dispose();
			}else {
				id_text.setText("");
				pwd_text.setText("");
			}
		}
		if(e.getSource() == btn_Signup) {
			//회원 가입
			dispose();
			new SignupView();
		}
	}
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == id_text) {
			if(id_text.getText().equals(id_Hint))
				id_text.setText("");
			id_text.setForeground(Color.black);
		}
		if(e.getSource() == pwd_text) {
			if(pwd_text.getText().equals(pwd_Hint))
				pwd_text.setText("");
			pwd_text.setForeground(Color.black);
		}
	}
	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == id_text) {
			if(id_text.getText().length()==0) {
				id_text.setText(id_Hint);
				id_text.setForeground(Color.WHITE);
			}
		}
		if(e.getSource() == pwd_text) {
			if(pwd_text.getText().length()==0) {
				pwd_text.setText(pwd_Hint);
				pwd_text.setForeground(Color.WHITE);
			}
		}
	}
}
