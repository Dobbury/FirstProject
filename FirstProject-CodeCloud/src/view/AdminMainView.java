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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

	JButton btn_Member;
	JButton btn_Sharebbs;
	JButton btn_QAbbs;
	JButton btn_Chat;
	JButton btn_Logout;
	boolean chat;

	BufferedImage img = null;

	private int posX = 0, posY = 0;
	private ImageIcon drag1;
	private ImageIcon drag2;
	private JButton btn_drag;

	//adQAbbsMain adQAMain = new adQAbbsMain();

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
		Image changedImg= ori.getScaledInstance(130, 130, Image.SCALE_SMOOTH );
		
		img = new ImageIcon(changedImg);
		
		memProfile_Img = new JLabel();
		memProfile_Img.setIcon(img);
		memProfile_Img.setBounds(10, 50, 130, 130);

		Font nickFont = new Font("맑은고딕",Font.BOLD,13);
		memName = new JLabel(s.nowMember.getNick(), SwingConstants.CENTER);
		memName.setForeground(Color.WHITE);
		memName.setFont(nickFont);
		memName.setBounds(10, 200, 130, 30);		

		btn_Member = new JButton("회원 관리");
		btn_Member.addActionListener(this);
		btn_Member.setBounds(10, 260, 130, 50);

		btn_Sharebbs = new JButton("공유 관리");
		btn_Sharebbs.addActionListener(this);
		btn_Sharebbs.setBounds(10, 360, 130, 50);

		btn_QAbbs = new JButton("Q&A 관리");
		btn_QAbbs.addActionListener(this);
		btn_QAbbs.setBounds(10, 460, 130, 50);

		btn_Chat = new JButton("채팅");
		btn_Chat.addActionListener(this);
		btn_Chat.setBounds(10, 560, 130, 50);

		btn_Logout = new JButton("로그아웃");
		btn_Logout.addActionListener(this);
		btn_Logout.setBounds(30, 650, 100, 80);

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
		btn_drag.setBounds(0, 0, 13, 13);
		btn_drag.addMouseMotionListener(this);
		btn_drag.addMouseListener(this);
		
		
		add(btn_drag);
		add(chatPanel);

		add(btn_Member);
		add(btn_Sharebbs);
		add(btn_QAbbs);
		add(btn_Chat);
		add(btn_Logout);

		add(memName);
		add(memProfile_Img);
		add(mainPanel);
		
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
		if (e.getSource() == btn_Member) {
			changePanel(1);
			
		} else if (e.getSource() == btn_Sharebbs) {
			changePanel(2);
			
		} else if (e.getSource() == btn_QAbbs) {
			changePanel(3);
			
		} else if (e.getSource() == btn_Chat) {
			if (chat) {
				setBounds(50, 50, 1100, 700);
				chat = false;
				
			} else {
				setBounds(50, 50, 1400, 700);
				chat = true;
				
			}
		} else if (e.getSource() == btn_Logout) {
			Singleton s = Singleton.getInstance();
			s.nowMember = null;
			this.dispose();
			s.MemCtrl.login();

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
