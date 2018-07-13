package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
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

import db.DBConnection;
import singleton.Singleton;
import view.LoginView.MyPanel;

public class HostDbSetView extends JFrame implements FocusListener,ActionListener {

	private ImageIcon closeIc1;
	private ImageIcon closeIc2;
	private ImageIcon closeIc3;
	private JButton btn_Close;
	
	BufferedImage img = null;
	
	JTextField IP_Text;
	
	JToggleButton Tbtn_server_Host;	//서버 아이피
	JToggleButton Tbtn_indv_Host;	//개인 아이피
	JButton btn_check;
	
	String IP_Hint = "IP 입력";
	
	public HostDbSetView() {

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 341, 400);
		layeredPane.setLayout(null);

//		// ---------------------------------------------------------------------------
//		// 배경화면
//		try {
//			img = ImageIO.read(new File("img/longin/loginback.png"));
//		} catch (IOException e) {
//			System.out.println("이미지 불러오기 실패");
//			System.exit(0);
//		}
//
//		MyPanel panel = new MyPanel();
//		panel.setBounds(0, 0, 341, 400);
//
//		// ---------------------------------------------------------------------------
//		

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
		
		
		//id 텍스트 필드
		IP_Text = new JTextField();
		IP_Text.setText(IP_Hint);
		IP_Text.setForeground(Color.WHITE);
		IP_Text.addFocusListener(this);
		IP_Text.setBounds(70, 216, 220, 30);
		IP_Text.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		IP_Text.setOpaque(false);
		layeredPane.add(IP_Text);
		
		ButtonGroup TogglebtnGroup = new ButtonGroup();
		Tbtn_server_Host = new JToggleButton("서버 IP");
		Tbtn_server_Host.setSelected(true);
		Tbtn_server_Host.setBounds(70,260,100,40);
		layeredPane.add(Tbtn_server_Host);
		
		Tbtn_indv_Host = new JToggleButton("IP 설정");
		Tbtn_indv_Host.setBounds(170,260,100,40);
		layeredPane.add(Tbtn_indv_Host);
		TogglebtnGroup.add(Tbtn_server_Host);
		TogglebtnGroup.add(Tbtn_indv_Host);
		
		btn_check = new JButton("확인");
		btn_check.setBounds(70,330,200,40);
		btn_check.requestFocus(true);
		btn_check.addActionListener(this);
		layeredPane.add(btn_check);
		
		setUndecorated(true); // 상단 닫기 최소화 설정
		// setBackground(new Color(0,0,0,122)); 배경 투명 설정
		setTitle("DB IP 설정");
		setLayout(null);
		setBounds(0, 0, 341, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(layeredPane);
		//add(panel);
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
		if(e.getSource() == IP_Text) {
			if(IP_Text.getText().equals(IP_Hint))
				IP_Text.setText("");
			IP_Text.setForeground(Color.black);
		}
	}
	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == IP_Text) {
			if(IP_Text.getText().length()==0) {
				IP_Text.setText(IP_Hint);
				IP_Text.setForeground(Color.WHITE);
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btn_Close) {
			System.exit(0);
		}
		if(e.getSource() == btn_check) {
			DBConnection.initConnect(IP_Text.getText());
			//아이피 설정
			Singleton s = Singleton.getInstance();
			s.MemCtrl.login();
			dispose();
		}
	}
}
