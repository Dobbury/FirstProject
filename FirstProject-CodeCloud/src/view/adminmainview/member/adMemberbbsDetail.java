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
	
	private ImageIcon updateIc1;
	private ImageIcon updateIc2;
	private ImageIcon updateIc3;
	private JButton btn_Update;

	private ImageIcon closeIc1;
	private ImageIcon closeIc2;
	private ImageIcon closeIc3;
	private JButton btn_Close;
	

	// 이미지 파일 불러오는 버튼 이미지 나중에 바꿔야함
	private ImageIcon edit1;
	private ImageIcon edit2;
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
			img = ImageIO.read(new File("img/memberUpdate/updatebck.png"));
		} catch (IOException e) {
			System.out.println("이미지 불러오기 실패");
			System.exit(0);
		}

		MyPanel panel = new MyPanel();
		panel.setBounds(0, 0, 341, 596);

		// ---------------------------------------------------------------------------
		// 버튼

		// 회원가입
		updateIc1 = new ImageIcon("img/memberUpdate/btn_update1.png");
		updateIc2 = new ImageIcon("img/memberUpdate/btn_update2.png");
		updateIc3 = new ImageIcon("img/memberUpdate/btn_update3.png");
		btn_Update = new JButton(updateIc1);
		btn_Update.setRolloverIcon(updateIc2);
		btn_Update.setPressedIcon(updateIc3);
		btn_Update.setBorderPainted(false);
		btn_Update.setContentAreaFilled(false);
		btn_Update.setFocusPainted(false);
		btn_Update.setBounds(20, 484, 296, 51);
		btn_Update.addActionListener(this);
		layeredPane.add(btn_Update);

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
		btn_Close.setBounds(318, 5, 16, 16);
		btn_Close.addActionListener(this);
		layeredPane.add(btn_Close);

		edit1 = new ImageIcon("img/memberUpdate/btn_edit1.png");
		edit2 = new ImageIcon("img/memberUpdate/btn_edit2.png");
		btn_ImgSelect = new JButton(edit1);
		btn_ImgSelect.setRolloverIcon(edit2);
		btn_ImgSelect.setPressedIcon(edit2);
		btn_ImgSelect.setBorderPainted(false);
		btn_ImgSelect.setContentAreaFilled(false);
		btn_ImgSelect.setFocusPainted(false);
		btn_ImgSelect.setBounds(143, 243, 59, 12);
		btn_ImgSelect.addActionListener(this);
		layeredPane.add(btn_ImgSelect);

		// ---------------------------------------------------------------------------
		// 사용자 프로필 이미지
		userIc = new ImageIcon(dto.getProfile_Img());
		userLabel = new JLabel(userIc);
		userLabel.setLayout(null);
		userLabel.setBounds(115, 108, 115, 115);
		layeredPane.add(userLabel);

		// 아이디 입력 textField
		id_text = new JTextField();
		id_text.setText(dto.getID());
		id_text.setForeground(Color.gray);
		id_text.setBounds(92, 282, 220, 30);
		id_text.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		id_text.setOpaque(false);
		id_text.setEditable(false);
		layeredPane.add(id_text);

		pwd_text = new JPasswordField();
		pwd_text.setText(dto.getPWD());
		pwd_text.setForeground(Color.WHITE);
		pwd_text.setBounds(92, 351, 220, 30);
		pwd_text.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		pwd_text.setOpaque(false);
		pwd_text.setEditable(false);
		layeredPane.add(pwd_text);

		pwd_check_label = new JLabel("test");
		pwd_check_label.setBounds(92, 387, 300, 30);
		layeredPane.add(pwd_check_label);

		nick_text = new JTextField();
		nick_text.setText(dto.getNick());
		nick_text.setForeground(Color.WHITE);
		nick_text.setBounds(92, 423, 220, 30);
		nick_text.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		nick_text.setOpaque(false);
		nick_text.setEditable(false);
		layeredPane.add(nick_text);

		nick_check_label = new JLabel("test");
		nick_check_label.setBounds(92, 456, 300, 30);
		layeredPane.add(nick_check_label);

		// 기본 설정
		// setBackground(new Color(0,0,0,122)); 배경 투명 설정

		setLayout(null);
		setBounds(0, 0, 341, 596);
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
