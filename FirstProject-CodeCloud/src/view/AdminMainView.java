package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import singleton.Singleton;
import view.MemberMainView.MyPanel;
import view.adminmainview.QA.adQAbbsMain;
import view.adminmainview.member.adMemberbbsMain;
import view.adminmainview.share.AdminSharebbs;
import view.chatview.chatPanel;

public class AdminMainView extends JFrame implements ActionListener, MouseListener, MouseMotionListener {

	private CardLayout cards = new CardLayout();
	JPanel mainPanel;

	JLabel memProfile_Img;
	JLabel memName;

	// 코드플래닛 로고
	JLabel codePlanet;
	// 바탕 라벨
	JLabel backLabel;
	// 로그아웃 라벨
	JLabel logLabel;

	// 버튼
	ImageIcon memberIc1;
	ImageIcon memberIc2;
	ImageIcon memberIc3;
	JToggleButton btn_Memberbbs;

	ImageIcon sharedIc1;
	ImageIcon sharedIc2;
	ImageIcon sharedIc3;
	JToggleButton btn_Sharebbs;

	ImageIcon qaIc1;
	ImageIcon qaIc2;
	ImageIcon qaIc3;
	JToggleButton btn_QAbbs;

	ImageIcon chatIc1;
	ImageIcon chatIc2;
	JToggleButton btn_Chat;

	ImageIcon logoutIc1;
	ImageIcon logoutIc2;
	JButton btn_Logout;
	boolean chat;

	BufferedImage img = null;

	private int posX = 0, posY = 0;
	private ImageIcon drag1;
	private ImageIcon drag2;
	private JButton btn_drag;

	private ImageIcon closeIc1;
	private ImageIcon closeIc2;
	private ImageIcon closeIc3;
	private JButton btn_Close;

	adQAbbsMain adQAMain = new adQAbbsMain();

	// 채팅 부분
	private chatPanel chatPanel;

	public AdminMainView() {

		setBounds(50, 50, 1100, 700);
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
		panel.setBounds(0, 0, 1400, 700);

		// ---------------------------------------------------------------------------

		mainPanel = new JPanel(cards);

		mainPanel.add("Memberbbs", new adMemberbbsMain());
		mainPanel.add("AdminSharebbs", new AdminSharebbs());
		//mainPanel.add("Q&Abbs", adQAMain);
		mainPanel.add("Q&Abbs",new adQAbbsMain());
		cards.show(mainPanel, "Memberbbs");

		mainPanel.setOpaque(false);
		mainPanel.setBounds(150, 0, 950, 700);

		Singleton s = Singleton.getInstance();

		ImageIcon img = new ImageIcon(s.nowMember.getProfile_Img());
		Image ori = img.getImage();
		Image changedImg = ori.getScaledInstance(130, 130, Image.SCALE_SMOOTH);

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

		chatPanel = new chatPanel();
		chatPanel.setOpaque(false);
		chatPanel.connect();
		chatPanel.setBounds(1100, 30, 300, 700);

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

		// 멤버 버튼
		memberIc1 = new ImageIcon("img/adminMain/btn_member1.png");
		memberIc2 = new ImageIcon("img/adminMain/btn_member2.png");
		memberIc3 = new ImageIcon("img/adminMain/btn_member3.png");
		btn_Memberbbs = new JToggleButton(memberIc1);
		btn_Memberbbs.setRolloverIcon(memberIc2);
		btn_Memberbbs.setPressedIcon(memberIc2);
		btn_Memberbbs.setSelectedIcon(memberIc3);
		btn_Memberbbs.setBorderPainted(false);
		btn_Memberbbs.setContentAreaFilled(false);
		btn_Memberbbs.setFocusPainted(false);

		btn_Memberbbs.addActionListener(this);
		btn_Memberbbs.setBounds(0, 262, 150, 88);

		// 공유 버튼
		sharedIc1 = new ImageIcon("img/adminMain/btn_shared1.png");
		sharedIc2 = new ImageIcon("img/adminMain/btn_shared2.png");
		sharedIc3 = new ImageIcon("img/adminMain/btn_shared3.png");
		btn_Sharebbs = new JToggleButton(sharedIc1);
		btn_Sharebbs.setRolloverIcon(sharedIc2);
		btn_Sharebbs.setPressedIcon(sharedIc2);
		btn_Sharebbs.setSelectedIcon(sharedIc3);
		btn_Sharebbs.setBorderPainted(false);
		btn_Sharebbs.setContentAreaFilled(false);
		btn_Sharebbs.setFocusPainted(false);
		btn_Sharebbs.addActionListener(this);
		btn_Sharebbs.setBounds(0, 350, 150, 88);

		// Q&A 버튼
		qaIc1 = new ImageIcon("img/adminMain/btn_qa1.png");
		qaIc2 = new ImageIcon("img/adminMain/btn_qa2.png");
		qaIc3 = new ImageIcon("img/adminMain/btn_qa3.png");
		btn_QAbbs = new JToggleButton(qaIc1);
		btn_QAbbs.setRolloverIcon(qaIc2);
		btn_QAbbs.setPressedIcon(qaIc2);
		btn_QAbbs.setSelectedIcon(qaIc3);
		btn_QAbbs.setBorderPainted(false);
		btn_QAbbs.setContentAreaFilled(false);
		btn_QAbbs.setFocusPainted(false);
		btn_QAbbs.addActionListener(this);
		btn_QAbbs.setBounds(0, 437, 150, 88);

		// 채팅 버튼
		chatIc1 = new ImageIcon("img/adminMain/btn_chatting1.png");
		chatIc2 = new ImageIcon("img/adminMain/btn_chatting2.png");
		btn_Chat = new JToggleButton(chatIc1);
		btn_Chat.setRolloverIcon(chatIc2);
		btn_Chat.setPressedIcon(chatIc2);
		btn_Chat.setSelectedIcon(chatIc2);
		btn_Chat.setBorderPainted(false);
		btn_Chat.setContentAreaFilled(false);
		btn_Chat.setFocusPainted(false);
		btn_Chat.addActionListener(this);
		btn_Chat.setBounds(0, 525, 150, 88);
		chat = false;

		// 로그아웃
		logLabel = new JLabel();
		logLabel.setBounds(0, 612, 150, 88);
		logLabel.setIcon(new ImageIcon("img/adminMain/backlabel.png"));
		logoutIc1 = new ImageIcon("img/adminMain/btn_logout1.png");
		logoutIc2 = new ImageIcon("img/adminMain/btn_logout2.png");
		btn_Logout = new JButton(logoutIc1);
		btn_Logout.setRolloverIcon(logoutIc2);
		btn_Logout.setPressedIcon(logoutIc2);
		btn_Logout.setBorderPainted(false);
		btn_Logout.setContentAreaFilled(false);
		btn_Logout.setFocusPainted(false);
		btn_Logout.addActionListener(this);
		btn_Logout.setBounds(12, 670, 19, 19);

		closeIc1 = new ImageIcon("img/close/close1.png");
		closeIc2 = new ImageIcon("img/close/close2.png");
		closeIc3 = new ImageIcon("img/close/close3.png");
		btn_Close = new JButton(closeIc1);
		btn_Close.setRolloverIcon(closeIc2);
		btn_Close.setPressedIcon(closeIc3);
		btn_Close.setBorderPainted(false);
		btn_Close.setContentAreaFilled(false);
		btn_Close.setFocusPainted(false);
		btn_Close.setBounds(1079, 5, 16, 16);
		btn_Close.addActionListener(this);
		
		
		ButtonGroup Tbtn_group = new ButtonGroup();
		
		Tbtn_group.add(btn_Memberbbs);
		Tbtn_group.add(btn_QAbbs);
		Tbtn_group.add(btn_Sharebbs);
		
		add(btn_Close);

		add(btn_drag);
		add(chatPanel);

		add(btn_Memberbbs);
		add(btn_Sharebbs);
		add(btn_QAbbs);
		add(btn_Chat);
		add(btn_Logout);

		add(memName);
		add(memProfile_Img);
		add(mainPanel);

		add(backLabel);
		add(logLabel);
		setUndecorated(true);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(panel);

	}

	public void changePanel(int select) {
		if (select == 1) {
			mainPanel.add("Memberbbs", new adMemberbbsMain());
			cards.show(mainPanel, "Memberbbs");
		} else if (select == 2) {
			mainPanel.add("AdminSharebbs", new AdminSharebbs());
			cards.show(mainPanel, "AdminSharebbs");
		} else if (select == 3) {
			mainPanel.add("Q&Abbs",new adQAbbsMain());
			cards.show(mainPanel, "Q&Abbs");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn_Memberbbs) {
			changePanel(1);
			
			
		} else if (e.getSource() == btn_Sharebbs) {
			changePanel(2);

		} else if (e.getSource() == btn_QAbbs) {
			changePanel(3);

		} else if (e.getSource() == btn_Chat) {
			if (chat) {
				btn_Close.setBounds(1079, 5, 16, 16);
				setBounds(50, 50, 1100, 700);
				chat = false;

			} else {
				btn_Close.setBounds(1379, 5, 16, 16);
				setBounds(50, 50, 1400, 700);
				chat = true;

			}
		} else if (e.getSource() == btn_Logout) {
			Singleton s = Singleton.getInstance();
			s.nowMember = null;
			this.dispose();
			s.MemCtrl.login();

		} else if (e.getSource() == btn_Close) {
			dispose();
		}
	}

	class MyPanel extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);

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
