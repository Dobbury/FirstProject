package view;

import java.awt.CardLayout;
import java.awt.Color;
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

import singleton.Singleton;
import thread.RankThread;
import view.SignupView.MyPanel;
import view.chatview.chatPanel;
import view.membermainview.QAbbsMain;
import view.membermainview.QAbbsWrite;
import view.membermainview.SelfbbsMain;
import view.membermainview.SelfbbsWrite;
import view.membermainview.Sharebbs;
import Encrypt.PasswordClass;
import chatting.ClientBackground;
import dao.BBSDao;
import db.DBClose;
import db.DBConnection;
import dto.BBSDto;
import dto.MemberDto;
import singleton.Singleton;

import view.membermainview.SelfbbsWrite;
import view.membermainview.Sharebbs;

public class MemberMainView extends JFrame implements ActionListener, MouseListener, MouseMotionListener {

	public CardLayout cards = new CardLayout();
	public JPanel mainPanel;
	public JPanel left;

	public JLabel memProfile_Img;
	public JLabel memName;

	JButton btn_Selfbbs;
	JButton btn_Sharebbs;
	JButton btn_QAbbs;
	JButton btn_Chat;
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
		// 제일 왼쪽 판넬
		left = new JPanel();

		left.setBounds(0,0,150,700);
		left.setBackground(new Color(0, 0, 0,60));

		Singleton s = Singleton.getInstance();

		mainPanel = new JPanel(cards);
		mainPanel.add("SelfbbsMain", new SelfbbsMain());
		mainPanel.add("Sharebbs", new Sharebbs());
		mainPanel.add("Q&Abbs", new QAbbsMain());
		cards.show(mainPanel, "Singlebbs");
		
		mainPanel.setOpaque(false);
		mainPanel.setBounds(150, 0, 1150, 700);
		
		RankThread rank = new RankThread();
		rank.start();
		
		

		
		
		ImageIcon img = new ImageIcon(s.nowMember.getProfile_Img());
		Image ori = img.getImage();
		Image changedImg = ori.getScaledInstance(130, 130, Image.SCALE_SMOOTH);


		img = new ImageIcon(changedImg);

		memProfile_Img = new JLabel();
		memProfile_Img.setIcon(img);
		memProfile_Img.addMouseListener(this);
		memProfile_Img.setBounds(10, 50, 130, 130);

		memName = new JLabel();
		memName.setText(s.nowMember.getNick());


		memName.setBounds(10, 200, 40, 30);


		btn_Selfbbs = new JButton("개인 코드");
		btn_Selfbbs.addActionListener(this);
		btn_Selfbbs.setBounds(10, 260, 130, 50);

		btn_Sharebbs = new JButton("코드 공유");
		btn_Sharebbs.addActionListener(this);
		btn_Sharebbs.setBounds(10, 360, 130, 50);

		btn_QAbbs = new JButton("Q&A");
		btn_QAbbs.addActionListener(this);
		btn_QAbbs.setBounds(10, 460, 130, 50);

		btn_Chat = new JButton("채팅 ");
		btn_Chat.addActionListener(this);
		btn_Chat.setBounds(10, 560, 130, 50);

		btn_Logout = new JButton("로그아웃");
		btn_Logout.addActionListener(this);
		btn_Logout.setBounds(10, 591, 130, 50);


		chatPanel = new chatPanel();
		chatPanel.connect();
		chatPanel.setOpaque(false);
		chatPanel.setBounds(1300, 7, 300, 700);

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

		add(btn_Selfbbs);
		add(btn_Sharebbs);
		add(btn_QAbbs);
		add(btn_Chat);
		add(btn_Logout);

		add(memName);
		add(memProfile_Img);
		add(mainPanel);
		add(left);
		
		setUndecorated(true);
		setVisible(true);
		setResizable(false);
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
			mainPanel.add("SelfbbsMain", new SelfbbsMain());
			changePanel(1);
		} else if (e.getSource() == btn_Sharebbs) {
			mainPanel.add("Sharebbs", new Sharebbs());
			changePanel(2);
		} else if (e.getSource() == btn_QAbbs) {

			mainPanel.add("Q&Abbs", new QAbbsMain());
			changePanel(3);
		} else if (e.getSource() == btn_Chat) {
			if (chat) {
				setBounds(50, 50, 1300, 700);
				chat = false;
			} else {
				setBounds(50, 50, 1600, 700);
				chat = true;
			}
		} else if (e.getSource() == btn_Logout) {
			Singleton s = Singleton.getInstance();
			s.nowMember = null;
			this.dispose();
			s.MemCtrl.login();
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
