package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import singleton.Singleton;
import thread.RankThread;
import view.SignupView.MyPanel;
import view.chatview.chatPanel;
import view.membermainview.QA.QAbbsMain;
import view.membermainview.QA.QAbbsWrite;
import view.membermainview.self.SelfbbsMain;
import view.membermainview.self.SelfbbsWrite;
import view.membermainview.share.Sharebbs;
import Encrypt.PasswordClass;
import chatting.ClientBackground;
import dao.BBSDao;
import db.DBClose;
import db.DBConnection;
import dto.BBSDto;
import dto.MemberDto;
import singleton.Singleton;

public class MemberMainView extends JFrame implements ActionListener, MouseListener, MouseMotionListener {

	public CardLayout cards = new CardLayout();
	public JPanel mainPanel;
	public JPanel left;

	public JLabel memProfile_Img;
	public JLabel memName;

	// 코드플래닛 로고
	JLabel codePlanet;
	// 바탕 라벨
	JLabel backLabel;
	// 로그아웃 라벨
	JLabel logLabel;

	// 버튼
	ImageIcon privateIc1;
	ImageIcon privateIc2;
	ImageIcon privateIc3;
	JButton btn_Selfbbs;

	ImageIcon sharedIc1;
	ImageIcon sharedIc2;
	ImageIcon sharedIc3;
	JButton btn_Sharebbs;

	ImageIcon qaIc1;
	ImageIcon qaIc2;
	ImageIcon qaIc3;
	JButton btn_QAbbs;

	ImageIcon chatIc1;
	ImageIcon chatIc2;
	JButton btn_Chat;

	ImageIcon logoutIc1;
	ImageIcon logoutIc2;
	JButton btn_Logout;
	boolean chat;

	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	String sql;

	private int posX = 0, posY = 0;
	private ImageIcon drag1;
	private ImageIcon drag2;
	private JButton btn_drag;

	private ImageIcon closeIc1;
	private ImageIcon closeIc2;
	private ImageIcon closeIc3;
	private JButton btn_Close;
	
	BufferedImage img = null;
	// 채팅 부분
	private chatPanel chatPanel;

	public MemberMainView() {
		setBounds(50, 50, 1300, 700);
		setLayout(null);

		// ---------------------------------------------------------------------------
		// 배경화면
		try {

			img = ImageIO.read(new File("img/background.png"));

		} catch (IOException e) {
			System.out.println("이미지 불러오기 실패");
			System.exit(0);
		}

		MyPanel panel = new MyPanel();
		panel.setBounds(0, 0, 1600, 700);

		// ---------------------------------------------------------------------------

		left = new JPanel();
		left.setBackground(new Color(0, 0, 0, 0));
		left.setBounds(0, 0, 150, 700);

		Singleton s = Singleton.getInstance();

		mainPanel = new JPanel(cards);
		mainPanel.add("SelfbbsMain", new SelfbbsMain());
		mainPanel.add("Sharebbs", new Sharebbs());
		mainPanel.add("Q&Abbs", new QAbbsMain());
		cards.show(mainPanel, "Singlebbs");
		
		RankThread rank = new RankThread();
		rank.start();
		
		mainPanel.setOpaque(false);

		mainPanel.setBounds(150, 0, 1150, 700);

		ImageIcon img = new ImageIcon(s.nowMember.getProfile_Img());
		Image ori = img.getImage();
		Image changedImg = ori.getScaledInstance(115, 115, Image.SCALE_SMOOTH);

		img = new ImageIcon(changedImg);

		memProfile_Img = new JLabel();
		memProfile_Img.setIcon(img);
		memProfile_Img.addMouseListener(this);
		memProfile_Img.setBounds(17, 108, 115, 115);

		Font nickFont = new Font("맑은고딕",Font.BOLD,13);
		memName = new JLabel(s.nowMember.getNick(), SwingConstants.CENTER);
		memName.setForeground(Color.WHITE);
		memName.setFont(nickFont);
		memName.setBounds(10, 230, 130, 30);	
		

		// 창 드래그
		drag1 = new ImageIcon("img/drag/drag1.png");
		drag1 = new ImageIcon("img/drag/drag2.png");
		btn_drag = new JButton(drag1);
		btn_drag.setRolloverIcon(drag2);
		btn_drag.setPressedIcon(drag2);
		btn_drag.setBorderPainted(false);
		btn_drag.setContentAreaFilled(false);
		btn_drag.setFocusPainted(false);
		btn_drag.setBounds(0, 0, 17, 17);
		btn_drag.addMouseMotionListener(this);
		btn_drag.addMouseListener(this);
		add(btn_drag);

		// 코드플레닛 이미지 라벨
		ImageIcon code = new ImageIcon("img/memberMain/codeplanet.png");
		codePlanet = new JLabel();
		codePlanet.setIcon(code);
		codePlanet.setBounds(0, 0, 150, 88);
		add(codePlanet);

		// 프로필 이미지 뒷 반투명 배경
		backLabel = new JLabel();
		backLabel.setIcon(new ImageIcon("img/memberMain/backlabel.png"));
		backLabel.setBounds(0, 88, 150, 175);

		// 버튼

		// 프라이빗 버튼
		privateIc1 = new ImageIcon("img/memberMain/btn_private1.png");
		privateIc2 = new ImageIcon("img/memberMain/btn_private2.png");
		privateIc3 = new ImageIcon("img/memberMain/btn_private3.png");
		btn_Selfbbs = new JButton(privateIc3);
		btn_Selfbbs.setRolloverIcon(privateIc3);
		btn_Selfbbs.setPressedIcon(privateIc2);
		btn_Selfbbs.setBorderPainted(false);
		btn_Selfbbs.setContentAreaFilled(false);
		btn_Selfbbs.setFocusPainted(false);

		btn_Selfbbs.addActionListener(this);
		btn_Selfbbs.setBounds(0, 262, 150, 88);

		// 공유 버튼
		sharedIc1 = new ImageIcon("img/memberMain/btn_shared1.png");
		sharedIc2 = new ImageIcon("img/memberMain/btn_shared2.png");
		sharedIc3 = new ImageIcon("img/memberMain/btn_shared3.png");
		btn_Sharebbs = new JButton(sharedIc1);
		btn_Sharebbs.setRolloverIcon(sharedIc2);
		btn_Sharebbs.setPressedIcon(sharedIc2);
		btn_Sharebbs.setBorderPainted(false);
		btn_Sharebbs.setContentAreaFilled(false);
		btn_Sharebbs.setFocusPainted(false);
		btn_Sharebbs.addActionListener(this);
		btn_Sharebbs.setBounds(0, 350, 150, 88);

		// Q&A 버튼
		qaIc1 = new ImageIcon("img/memberMain/btn_qa1.png");
		qaIc2 = new ImageIcon("img/memberMain/btn_qa2.png");
		qaIc3 = new ImageIcon("img/memberMain/btn_qa3.png");
		btn_QAbbs = new JButton(qaIc1);
		btn_QAbbs.setRolloverIcon(qaIc2);
		btn_QAbbs.setPressedIcon(qaIc2);
		btn_QAbbs.setBorderPainted(false);
		btn_QAbbs.setContentAreaFilled(false);
		btn_QAbbs.setFocusPainted(false);
		btn_QAbbs.addActionListener(this);
		btn_QAbbs.setBounds(0, 437, 150, 88);

		// 채팅 버튼
		chatIc1 = new ImageIcon("img/memberMain/btn_chatting1.png");
		chatIc2 = new ImageIcon("img/memberMain/btn_chatting2.png");
		btn_Chat = new JButton(chatIc1);
		btn_Chat.setRolloverIcon(chatIc2);
		btn_Chat.setPressedIcon(chatIc2);
		btn_Chat.setBorderPainted(false);
		btn_Chat.setContentAreaFilled(false);
		btn_Chat.setFocusPainted(false);
		btn_Chat.addActionListener(this);
		btn_Chat.setBounds(0, 525, 150, 88);
		chat = false;

		// 로그아웃
		logLabel = new JLabel();
		logLabel.setBounds(0, 612, 150, 88);
		logLabel.setIcon(new ImageIcon("img/memberMain/backlabel.png"));
		logoutIc1 = new ImageIcon("img/memberMain/btn_logout1.png");
		logoutIc2 = new ImageIcon("img/memberMain/btn_logout2.png");
		btn_Logout = new JButton(logoutIc1);
		btn_Logout.setRolloverIcon(logoutIc2);
		btn_Logout.setPressedIcon(logoutIc2);
		btn_Logout.setBorderPainted(false);
		btn_Logout.setContentAreaFilled(false);
		btn_Logout.setFocusPainted(false);
		btn_Logout.addActionListener(this);
		btn_Logout.setBounds(12, 670, 19, 19);

		chatPanel = new chatPanel();
		chatPanel.connect();
		chatPanel.setOpaque(false);
		chatPanel.setBounds(1300, 30, 300, 700);

	
		
		//닫기 
		closeIc1 = new ImageIcon("img/close/close1.png");
		closeIc2 = new ImageIcon("img/close/close2.png");
		closeIc3 = new ImageIcon("img/close/close3.png");
		btn_Close = new JButton(closeIc1);
		btn_Close.setRolloverIcon(closeIc2);
		btn_Close.setPressedIcon(closeIc3);
		btn_Close.setBorderPainted(false);
		btn_Close.setContentAreaFilled(false);
		btn_Close.setFocusPainted(false);
		btn_Close.setBounds(1279, 5, 16, 16);
		btn_Close.addActionListener(this);
		add(btn_Close);

		add(chatPanel);

		add(btn_Selfbbs);
		add(btn_Sharebbs);
		add(btn_QAbbs);
		add(btn_Chat);
		add(btn_Logout);

		add(memName);
		add(memProfile_Img);
		add(mainPanel);
		add(left);
		add(backLabel);
		add(logLabel);

		setUndecorated(true);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		add(panel);

	}

	public void changePanel(int select) {
		if (select == 1) {
			mainPanel.add("SelfbbsMain", new SelfbbsMain());
			cards.show(mainPanel, "SelfbbsMain");
		} else if (select == 2) {
			mainPanel.add("Sharebbs", new Sharebbs());
			cards.show(mainPanel, "Sharebbs");
		} else if (select == 3) {
			mainPanel.add("Q&Abbs", new QAbbsMain());
			cards.show(mainPanel, "Q&Abbs");

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btn_Selfbbs) {
			btn_Selfbbs.setIcon(privateIc3);
			btn_Selfbbs.setRolloverIcon(privateIc3);
			btn_Sharebbs.setRolloverIcon(sharedIc2);
			btn_Sharebbs.setPressedIcon(sharedIc2);
			btn_QAbbs.setRolloverIcon(qaIc2);
			btn_QAbbs.setPressedIcon(qaIc2);
			btn_Sharebbs.setIcon(sharedIc1);
			btn_QAbbs.setIcon(qaIc1);
						
			mainPanel.add("SelfbbsMain", new SelfbbsMain());
			changePanel(1);
			
		} else if (e.getSource() == btn_Sharebbs) {	
			btn_Sharebbs.setIcon(sharedIc3);
			btn_Sharebbs.setRolloverIcon(sharedIc3);
			btn_Selfbbs.setRolloverIcon(privateIc2);
			btn_Selfbbs.setPressedIcon(privateIc2);
			btn_QAbbs.setRolloverIcon(qaIc2);
			btn_QAbbs.setPressedIcon(qaIc2);
			btn_Selfbbs.setIcon(privateIc1);
			btn_QAbbs.setIcon(qaIc1);
			
			
			mainPanel.add("Sharebbs", new Sharebbs());
			changePanel(2);
			
		} else if (e.getSource() == btn_QAbbs) {
			btn_QAbbs.setIcon(qaIc3);
			btn_QAbbs.setRolloverIcon(qaIc3);
			btn_Sharebbs.setRolloverIcon(sharedIc2);
			btn_Sharebbs.setPressedIcon(sharedIc2);
			btn_Selfbbs.setRolloverIcon(privateIc2);
			btn_Selfbbs.setPressedIcon(privateIc2);
			btn_Selfbbs.setIcon(privateIc1);
			btn_Sharebbs.setIcon(sharedIc1);
			
			
			mainPanel.add("Q&Abbs", new QAbbsMain());
			changePanel(3);
			
		} else if (e.getSource() == btn_Chat) {
			
			
			if (chat) {
				btn_Chat.setIcon(chatIc1);
				setBounds(50,50, 1300, 700);
				setLocationRelativeTo(null);
				btn_Close.setBounds(1279, 5, 16, 16);
				
				chat = false;
			} else {
				btn_Chat.setIcon(chatIc2);
				setBounds(50, 50, 1600, 700);
				setLocationRelativeTo(null);
				btn_Close.setBounds(1579, 5, 16, 16);
				
				chat = true;
			}
		} else if (e.getSource() == btn_Logout) {
			Singleton s = Singleton.getInstance();
			s.nowMember = null;
			this.dispose();
			s.MemCtrl.login();
		} else if (e.getSource() == btn_Close) {
			System.exit(0);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
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
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == memProfile_Img) {
			Singleton s = Singleton.getInstance();

			new MemberUpdateView(this, s.nowMember);
		} else if (e.getSource() == btn_drag) {
			posX = e.getX();
			posY = e.getY();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
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

	class MyPanel extends JPanel {
		public void paint(Graphics g) {
			// g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
			g.drawImage(img, 0, 0, null);

		}
	}
}
