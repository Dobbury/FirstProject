package view;

import java.awt.Color;
import java.awt.Font;
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

public class SignupView extends JFrame implements ActionListener, FocusListener, MouseMotionListener, MouseListener {

	private ImageIcon signupIc1;
	private ImageIcon signupIc2;
	private ImageIcon signupIc3;
	private JButton btn_Signup;

	private ImageIcon closeIc1;
	private ImageIcon closeIc2;
	private ImageIcon closeIc3;
	private JButton btn_Close;

//	private ImageIcon userIc;
//	private JLabel userLabel;

	private JTextField id_text;
	private JLabel id_check_label;
	private boolean id_check;

	private JTextField nick_text;
	private JLabel nick_check_label;
	private boolean nick_check;

	private JPasswordField pwd_text;
	private JLabel pwd_check_label;
	private boolean pwd_check;

	private int posX = 0, posY = 0;
	private ImageIcon drag1;
	private ImageIcon drag2;
	private JButton btn_drag;

	BufferedImage img = null;
//	BufferedImage userImg = null;

	String id_Hint = "ID 입력";
	String pwd_Hint = "패스워드 입력";
	String nick_Hint = "닉네임 입력";

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
		panel.setBounds(0, 0, 341, 596);

		// ---------------------------------------------------------------------------
		// 버튼

		// 창 드래그
		drag1 = new ImageIcon("img/drag/drag1.png");
		drag1 = new ImageIcon("img/drag/drag2.png");
		btn_drag = new JButton(drag1);
		btn_drag.setRolloverIcon(drag2);
		btn_drag.setPressedIcon(drag2);
		btn_drag.setBorderPainted(false);
		btn_drag.setContentAreaFilled(false);
		btn_drag.setFocusPainted(false);
		btn_drag.setBounds(0, 0, 13, 13);
		btn_drag.addMouseMotionListener(this);
		btn_drag.addMouseListener(this);
		layeredPane.add(btn_drag);

		// 회원가입
		signupIc1 = new ImageIcon("img/signUp/btn_sign1.png");
		signupIc2 = new ImageIcon("img/signUp/btn_sign2.png");
		signupIc3 = new ImageIcon("img/signUp/btn_sign3.png");
		btn_Signup = new JButton(signupIc1);
		btn_Signup.setRolloverIcon(signupIc2);
		btn_Signup.setPressedIcon(signupIc3);
		btn_Signup.setBorderPainted(false);
		btn_Signup.setContentAreaFilled(false);
		btn_Signup.setFocusPainted(false);
		btn_Signup.setBounds(20, 484, 296, 51);
		btn_Signup.addActionListener(this);
		layeredPane.add(btn_Signup);

		// 닫기
		closeIc1 = new ImageIcon("img/close/close1.png");
		closeIc2 = new ImageIcon("img/close/close2.png");
		closeIc3 = new ImageIcon("img/close/close3.png");
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
//		 사용자 프로필 이미지
//		userIc = new ImageIcon("img/signUp/userImages.png");
//		userLabel = new JLabel(userIc);
//		userLabel.setLayout(null);
//		userLabel.setBounds(105, 100, 130, 130);

		// 아이디 입력 textField
		id_text = new JTextField();
		id_text.setText(id_Hint);
		id_text.setForeground(Color.WHITE);
		id_text.setFont(new Font("menlo", Font.PLAIN, 14));
		id_text.setBounds(92, 282, 220, 30);
		id_text.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		id_text.setOpaque(false);
		id_text.addFocusListener(this);
		layeredPane.add(id_text);

		// 아이디 체크 라벨
		id_check_label = new JLabel("test");

		id_check_label.setBounds(92, 316, 300, 30);
		layeredPane.add(id_check_label);

		// pwd 입력
		pwd_text = new JPasswordField();
		pwd_text.setText(pwd_Hint);
		pwd_text.setForeground(Color.WHITE);
		pwd_text.setBounds(92, 351, 220, 30);
		pwd_text.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		pwd_text.setOpaque(false);
		pwd_text.addFocusListener(this);
		layeredPane.add(pwd_text);

		// pwd 확인
		pwd_check_label = new JLabel("test");
		pwd_check_label.setBounds(92, 387, 300, 30);
		layeredPane.add(pwd_check_label);

		// 닉네임 입력
		nick_text = new JTextField();
		nick_text.setText(nick_Hint);
		nick_text.setFont(new Font("menlo", Font.PLAIN, 14));
		nick_text.setForeground(Color.WHITE);
		nick_text.setBounds(92, 423, 220, 30);
		nick_text.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		nick_text.setOpaque(false);
		nick_text.addFocusListener(this);
		layeredPane.add(nick_text);

		// 닉네임 확인
		nick_check_label = new JLabel("test");
		nick_check_label.setBounds(92, 458, 300, 30);
		layeredPane.add(nick_check_label);

		// 기본 설정
		setUndecorated(true); // 상단 닫기 최소화 설정
		// setBackground(new Color(0,0,0,122)); 배경 투명 설정
		setTitle("로그인테스트");
		setLayout(null);
		setBounds(0, 0, 341, 596);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(layeredPane);
		setVisible(true);
//		layeredPane.add(userLabel);
		layeredPane.add(panel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn_Signup) {
			// 아이디 체크
			if (!id_check) {
				JOptionPane.showMessageDialog(null, "아이디를 확인하세요.");
				return;
			}
			// 비밀번호 체크
			if (!pwd_check) {
				JOptionPane.showMessageDialog(null, "비밀번호를 확인하세요.");
				return;
			}
			// 닉네임 체크
			if (!nick_check) {
				JOptionPane.showMessageDialog(null, "닉네임을 확인하세요.");
				return;
			}

			Singleton s = Singleton.getInstance();
			boolean b = s.MemCtrl.addMember(id_text.getText(), pwd_text.getText(), nick_text.getText());

			// 아이디 체크

			// 닉네임 체크

			if (b) {
				JOptionPane.showMessageDialog(null, "회원가입을 축하합니다.");
				this.dispose();
				// 로그인창
				s.MemCtrl.login();
			} else {
				JOptionPane.showMessageDialog(null, "회원가입 안 되었습니다.");
				return;
			}
		}
		if (e.getSource() == btn_Close) {
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
		if (e.getSource() == id_text) {
			if (id_text.getText().equals(id_Hint))
				id_text.setText("");

			id_text.setForeground(Color.white);
		}
		if (e.getSource() == pwd_text) {
			if (pwd_text.getText().equals(pwd_Hint))
				pwd_text.setText("");
			pwd_text.setForeground(Color.white);
		}
		if (e.getSource() == nick_text) {
			if (nick_text.getText().equals(nick_Hint))
				nick_text.setText("");
			nick_text.setForeground(Color.white);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == id_text) {
			if (id_text.getText().length() == 0) {
				id_text.setText(id_Hint);
				id_text.setForeground(Color.WHITE);
				id_check_label.setText("필수 정보입니다.");
				id_check_label.setForeground(Color.RED);

				id_check = false;
			} else {
				String id = id_text.getText();

				for (int i = 0; i < id.length(); i++) {

					if (!(id.charAt(i) >= 97 && id.charAt(i) <= 122) && // 소문자가 아닐때 그리고
							!(id.charAt(i) >= 48 && id.charAt(i) <= 57) && // 숫자가 아닐때 그리고
							id.charAt(i) != 45 && id.charAt(i) != 95) { // -나 _가 아닐때
						// 숫자와 - 이나 _가 아닐때
						id_check_label.setText("소문자,숫자와 특수기호(_),(-)만 사용 가능합니다.");
						id_check_label.setForeground(Color.RED);
						id_check = false;
						return;
					}
				}
				Singleton s = Singleton.getInstance();
				// 아이디체크
				boolean b = s.MemCtrl.getId(id);
				if (b) {
					// true이면 아이디가 중복
					id_check_label.setText("이미 사용중인 아이디입니다.");
					id_check_label.setForeground(Color.RED);
					id_check = false;
				} else {
					// false이면 생성 가능
					id_check_label.setText("사용 가능한 아이디입니다.");
					id_check_label.setForeground(Color.GREEN);
					id_check = true;
				}

			}
		}
		if (e.getSource() == pwd_text) {
			if (pwd_text.getText().length() == 0) {
				pwd_text.setText(pwd_Hint);
				pwd_text.setForeground(Color.WHITE);
				pwd_check_label.setText("필수 정보입니다.");
				pwd_check_label.setForeground(Color.RED);

				pwd_check = false;
			} else {
				// 비밀번호 조건 넣을 부분
				pwd_check_label.setText("사용 가능한 비밀번호입니다.");
				pwd_check_label.setForeground(Color.GREEN);

				pwd_check = true;
			}
		}
		if (e.getSource() == nick_text) {
			if (nick_text.getText().length() == 0) {
				nick_text.setText(nick_Hint);
				nick_text.setForeground(Color.WHITE);

				nick_check_label.setText("필수 정보입니다.");
				nick_check_label.setForeground(Color.RED);

				nick_check = false;
			} else {
				String nick = nick_text.getText();

				Singleton s = Singleton.getInstance();
				// 아이디체크
				boolean b = s.MemCtrl.getNick(nick);

				if (b) {
					// true이면 아이디가 중복
					nick_check_label.setText("이미 사용중인 닉네임입니다.");
					nick_check_label.setForeground(Color.RED);

					nick_check = false;
				} else {
					// false이면 생성 가능
					nick_check_label.setText("사용 가능한 닉네임입니다.");
					nick_check_label.setForeground(Color.GREEN);

					nick_check = true;
				}

			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == btn_drag) {
			posX = e.getX();
			posY = e.getY();
		}

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
	public void mouseDragged(MouseEvent e) {
		if (e.getSource() == btn_drag) {
			setLocation(e.getXOnScreen() - posX, e.getYOnScreen() - posY);
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
