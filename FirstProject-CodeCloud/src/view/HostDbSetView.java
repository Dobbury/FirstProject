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
import java.awt.image.ImagingOpException;
import java.awt.peer.ButtonPeer;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import db.DBCheck;
import db.DBConnection;
import singleton.Singleton;
import view.LoginView.MyPanel;

public class HostDbSetView extends JFrame implements FocusListener, ActionListener, MouseListener, MouseMotionListener {

	private String Server_IP = "미 정";
	private ImageIcon closeIc1;
	private ImageIcon closeIc2;
	private ImageIcon closeIc3;
	private JButton btn_Close;

	private BufferedImage img = null;

	private JTextField IP_Text;

	private ImageIcon serverIp1;
	private ImageIcon serverIp2;
	private ImageIcon serverIp3;
	private JToggleButton Tbtn_server_Host; // 서버 아이피

	private ImageIcon soloIp1;
	private ImageIcon soloIp2;
	private ImageIcon soloIp3;
	private JToggleButton Tbtn_indv_Host; // 개인 아이피

	private ImageIcon check1;
	private ImageIcon check2;
	private ImageIcon check3;
	private JButton btn_check;

	private int posX = 0, posY = 0;
	private ImageIcon drag1;
	private ImageIcon drag2;
	private JButton btn_drag;

	private String IP_Hint = "IP 입력";

	public HostDbSetView() {

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 341, 413);
		layeredPane.setLayout(null);

//		// ---------------------------------------------------------------------------
//		// 배경화면
		try {
			img = ImageIO.read(new File("img/hostDB/dbBack.png"));
		} catch (IOException e) {
			System.out.println("이미지 불러오기 실패");
			System.exit(0);
		}

		MyPanel panel = new MyPanel();
		panel.setBounds(0, 0, 341, 413);

//		// ---------------------------------------------------------------------------
//		

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

		IP_Text = new JTextField();
		IP_Text.setText(IP_Hint);
		IP_Text.setForeground(Color.WHITE);
		IP_Text.setFont(new Font("menlo", Font.PLAIN, 14));
		IP_Text.addFocusListener(this);
		IP_Text.setBounds(92, 246, 220, 30);
		IP_Text.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		IP_Text.setOpaque(false);
		IP_Text.setEnabled(true);

		layeredPane.add(IP_Text);

		ButtonGroup TogglebtnGroup = new ButtonGroup();

		serverIp1 = new ImageIcon("img/hostDB/btn_online1.png");
		serverIp2 = new ImageIcon("img/hostDB/btn_online2.png");
		serverIp3 = new ImageIcon("img/hostDB/btn_online3.png");
		Tbtn_server_Host = new JToggleButton(serverIp3);
		Tbtn_server_Host.setRolloverIcon(serverIp2);
		Tbtn_server_Host.setPressedIcon(serverIp2);
		Tbtn_server_Host.setBorderPainted(false);
		Tbtn_server_Host.setContentAreaFilled(false);
		Tbtn_server_Host.setFocusPainted(false);
		Tbtn_server_Host.setSelected(true);
		Tbtn_server_Host.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Tbtn_server_Host.setIcon(serverIp3);
				Tbtn_indv_Host.setIcon(soloIp1);
				IP_Text.setText(IP_Hint);
				IP_Text.setEnabled(true);
			}
		});

		Tbtn_server_Host.setBounds(23, 195, 73, 26);
		layeredPane.add(Tbtn_server_Host);

		soloIp1 = new ImageIcon("img/hostDB/btn_solo1.png");
		soloIp2 = new ImageIcon("img/hostDB/btn_solo2.png");
		soloIp3 = new ImageIcon("img/hostDB/btn_solo3.png");
		Tbtn_indv_Host = new JToggleButton(soloIp1);
		Tbtn_indv_Host.setRolloverIcon(soloIp2);
		Tbtn_indv_Host.setPressedIcon(soloIp2);
		Tbtn_indv_Host.setBorderPainted(false);
		Tbtn_indv_Host.setContentAreaFilled(false);
		Tbtn_indv_Host.setFocusPainted(false);
		Tbtn_indv_Host.setBounds(98, 195, 73, 26);

		Tbtn_indv_Host.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Tbtn_indv_Host.setIcon(soloIp3);
				Tbtn_server_Host.setIcon(serverIp1);

				IP_Text.setText(Server_IP);
				IP_Text.setEnabled(false);

			}
		});
		layeredPane.add(Tbtn_indv_Host);

		// 그룹에 추가
		TogglebtnGroup.add(Tbtn_server_Host);
		TogglebtnGroup.add(Tbtn_indv_Host);

		// 확인
		check1 = new ImageIcon("img/hostDB/btn_db1.png");
		check2 = new ImageIcon("img/hostDB/btn_db2.png");
		check3 = new ImageIcon("img/hostDB/btn_db3.png");

		btn_check = new JButton(check1);
		btn_check.setSelectedIcon(check2);
		btn_check.setRolloverIcon(check2);
		btn_check.setPressedIcon(check3);
		btn_check.setBorderPainted(false);
		btn_check.setContentAreaFilled(false);
		btn_check.setFocusPainted(false);
		btn_check.setBounds(22, 301, 296, 51);
		btn_check.requestFocus(true);
		btn_check.addActionListener(this);
		layeredPane.add(btn_check);

		setUndecorated(true); // 상단 닫기 최소화 설정
		// setBackground(new Color(0,0,0,122)); 배경 투명 설정
		setTitle("DB IP 설정");
		setLayout(null);
		setBounds(0, 0, 340, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(layeredPane);
		add(panel);
		setVisible(true);
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
		if (e.getSource() == IP_Text) {
			if (IP_Text.getText().equals(IP_Hint))
				IP_Text.setText("");
			IP_Text.setForeground(Color.white);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == IP_Text) {
			if (IP_Text.getText().length() == 0) {
				IP_Text.setText(IP_Hint);
				IP_Text.setForeground(Color.WHITE);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == btn_Close) {
			System.exit(0);
		}

		if (e.getSource() == btn_check) {
			DBConnection.initConnect(IP_Text.getText());
			Singleton s = Singleton.getInstance();
			s.hostDB_IP = IP_Text.getText();

			DBCheck.memDBcheck();
			DBCheck.shareDBCheck();
			DBCheck.qaDBCheck();
			DBCheck.createUpdateTrigger();

			s.MemCtrl.login();
			dispose();
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
