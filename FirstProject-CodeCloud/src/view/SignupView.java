package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import singleton.Singleton;


public class SignupView extends JFrame implements ActionListener, FocusListener {
	
	private ImageIcon signupIc1;
	private ImageIcon signupIc2;
	private ImageIcon signupIc3;
	private JButton btn_Signup;

	private ImageIcon closeIc1;
	private ImageIcon closeIc2;
	private ImageIcon closeIc3;
	private JButton btn_Close;

	
	private ImageIcon userIc;
	private JLabel userLabel;
	

	private JTextField id_text;
	private JTextField nick_text;
	private JPasswordField pwd_text;

	BufferedImage img = null;
	BufferedImage userImg = null;
	
	public SignupView() {
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 342, 596);
		layeredPane.setLayout(null);

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
		signupIc1 = new ImageIcon("img/signUp/signup1.png");
		signupIc2 = new ImageIcon("img/signUp/signup2.png");
		signupIc3 = new ImageIcon("img/signUp/signup3.png");
		btn_Signup = new JButton(signupIc1);
		btn_Signup.setRolloverIcon(signupIc2);
		btn_Signup.setPressedIcon(signupIc3);
		btn_Signup.setBorderPainted(false);
		btn_Signup.setContentAreaFilled(false);
		btn_Signup.setFocusPainted(false);
		btn_Signup.setBounds(20, 484, 295, 50);
		btn_Signup.addActionListener(this);
		layeredPane.add(btn_Signup);

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

	
		// ---------------------------------------------------------------------------
		// 사용자 프로필 이미지
		userIc = new ImageIcon("img/signUp/userImages.png");
		userLabel = new JLabel(userIc);
		userLabel.setLayout(null);
		userLabel.setBounds(105, 100, 130, 130);
		
		
		//아이디 입력 textField
		id_text = new JTextField();
		id_text.setBounds(70, 303, 220, 30);
		id_text.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		id_text.setOpaque(false);
		id_text.addFocusListener(this);
		layeredPane.add(id_text);
		
		pwd_text = new JPasswordField();
		pwd_text.setBounds(70, 366, 220, 30);
		pwd_text.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		pwd_text.setOpaque(false);
		layeredPane.add(pwd_text);
		
		nick_text = new JTextField();
		nick_text.setBounds(70, 429, 220, 30);
		nick_text.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		nick_text.setOpaque(false);
		layeredPane.add(nick_text);
		
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
		layeredPane.add(userLabel);
		layeredPane.add(panel);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_Signup ) {
			
			Singleton s = Singleton.getInstance();
			boolean b = s.MemCtrl.addMember(id_text.getText(), pwd_text.getText(),
					nick_text.getText());
			//아이디 체크
			
			//닉네임 체크
			
			if(b) {
				JOptionPane.showMessageDialog(null, "회원가입을 축하합니다.");
				//회원가입창 초기화
				this.dispose();
				//로그인창
				s.MemCtrl.login();
			}else {
				JOptionPane.showMessageDialog(null, "이미 사용중인 e-mail 입니다.");
				return;
			}
		}
		if(e.getSource() == btn_Close) {
			System.exit(0);
		}
	}

	class MyPanel extends JPanel {
		public void paint(Graphics g) {
			// g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
			g.drawImage(img, 0, 0, null);

		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}
}
