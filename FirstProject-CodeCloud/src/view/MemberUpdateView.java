package view;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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

import convertImg.ImageToBufferedImageClass;
import dto.MemberDto;
import singleton.Singleton;

public class MemberUpdateView extends JFrame
		implements ActionListener, FocusListener, WindowListener, MouseMotionListener, MouseListener {

	private ImageIcon signupIc1;
	private ImageIcon signupIc2;
	private ImageIcon signupIc3;
	private JButton btn_Signup;

	private ImageIcon closeIc1;
	private ImageIcon closeIc2;
	private ImageIcon closeIc3;
	private JButton btn_Close;

	// 이미지 파일 불러오는 버튼 이미지 나중에 바꿔야함
	private ImageIcon accountIc1;
	private ImageIcon accountIc2;
	private ImageIcon accountIc3;
	private JButton btn_ImgSelect;

	private ImageIcon userIc;
	private JLabel userLabel;

	private JTextField id_text;

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
	BufferedImage userImg = null;

	String id_Hint = "ID 입력";
	String pwd_Hint = "패스워드 입력";
	String nick_Hint = "닉네임 입력";

	String Diraddress;

	MemberDto dto;
	MemberMainView mainView;

	public MemberUpdateView(MemberMainView mainView, MemberDto dto) {
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 342, 596);
		layeredPane.setLayout(null);

		this.dto = dto;
		this.mainView = mainView;
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

		// 회원가입
		accountIc1 = new ImageIcon("img/longin/account1.png");
		accountIc2 = new ImageIcon("img/longin/account2.png");
		accountIc3 = new ImageIcon("img/longin/account3.png");

		btn_ImgSelect = new JButton(accountIc1);
		btn_ImgSelect.setRolloverIcon(accountIc2);
		btn_ImgSelect.setPressedIcon(accountIc3);
		btn_ImgSelect.setBorderPainted(false);
		btn_ImgSelect.setContentAreaFilled(false);
		btn_ImgSelect.setFocusPainted(false);
		btn_ImgSelect.setBounds(105, 250, 76, 8);
		btn_ImgSelect.addActionListener(this);
		layeredPane.add(btn_ImgSelect);

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
		pwd_text.addFocusListener(this);
		layeredPane.add(pwd_text);

		pwd_check_label = new JLabel("test");
		pwd_check_label.setBounds(33, 390, 300, 30);
		layeredPane.add(pwd_check_label);

		nick_text = new JTextField();
		nick_text.setText(nick_Hint);
		nick_text.setForeground(Color.WHITE);
		nick_text.setBounds(70, 429, 220, 30);
		nick_text.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		nick_text.setOpaque(false);
		nick_text.addFocusListener(this);
		layeredPane.add(nick_text);

		nick_check_label = new JLabel("test");
		nick_check_label.setBounds(33, 455, 300, 30);
		layeredPane.add(nick_check_label);

		// 기본 설정
		// setBackground(new Color(0,0,0,122)); 배경 투명 설정
		setTitle("회원정보 수정테스트");
		setLayout(null);
		setBounds(0, 0, 341, 597);
		setLocationRelativeTo(null);
		add(layeredPane);
		setVisible(true);
		layeredPane.add(panel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn_Signup) {

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

			boolean b = s.MemCtrl.memberUpdate(id_text.getText(), pwd_text.getText(), nick_text.getText(),
					s.nowMember.getAuth(), userIc);

			if (b) {
				JOptionPane.showMessageDialog(null, "회원의 정보가 수정 되었습니다.");
				mainView.memProfile_Img.setIcon(userIc);
				mainView.memName.setText(s.nowMember.getNick());

				this.dispose();

			} else {
				JOptionPane.showMessageDialog(null, "수정 실패");
				return;
			}
		}
		if (e.getSource() == btn_Close) {
			dispose();
		}
		if (e.getSource() == btn_ImgSelect) {
			JFrame img_Frame = new JFrame();
			FileDialog dial = new FileDialog(img_Frame, "Open", FileDialog.LOAD);
			dial.setFile("*.*");
			dial.setVisible(true);
			String DirName = dial.getDirectory();
			String FileName = dial.getFile();
			Diraddress = DirName + "./" + FileName;
			System.out.println(Diraddress);
			userIc = new ImageIcon(Diraddress);
			// 크기조정
			Image ori = userIc.getImage();
			Image changedImg = ori.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
			// 이미지 다시 세팅
			changedImg = imageToOval(changedImg);
			userIc.setImage(changedImg);
			userLabel.setIcon(userIc);
			userLabel.setLayout(null);
			userLabel.setBounds(105, 100, 130, 130);
		}
	}

	class MyPanel extends JPanel {
		public void paint(Graphics g) {
			// g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
			g.drawImage(img, 0, 0, null);

		}
	}

	public BufferedImage imageToOval(Image img) { // 이미지 원으로 자르는 메소드
		// Image -> BufferedImage
		BufferedImage oriImg = ImageToBufferedImageClass.toBufferedImage(img);

		// 기본 프로필 원형 이미지
		ImageIcon basic_profile_img = new ImageIcon("img/signUp/userImages.png");

		Image ori_bpi = basic_profile_img.getImage();
		BufferedImage buf_bpi = ImageToBufferedImageClass.toBufferedImage(ori_bpi);

		for (int y = 0; y < 130; y++) {
			for (int x = 0; x < 130; x++) {
				int pixel = buf_bpi.getRGB(x, y);

				if (pixel == 0) {// 픽셀이 투명이면
					oriImg.setRGB(x, y, pixel);
				}
			}
		}
		return oriImg;
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == id_text) {
			if (id_text.getText().equals(id_Hint))
				id_text.setText("");

			id_text.setForeground(Color.black);
		}
		if (e.getSource() == pwd_text) {
			if (pwd_text.getText().equals(pwd_Hint))
				pwd_text.setText("");
			pwd_text.setForeground(Color.black);
		}
		if (e.getSource() == nick_text) {
			if (nick_text.getText().equals(nick_Hint))
				nick_text.setText("");
			nick_text.setForeground(Color.black);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub

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
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		dispose();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
