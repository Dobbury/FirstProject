package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import singleton.Singleton;
import view.memberpanel.QAbbsMain;
import view.memberpanel.QAbbsWrite;
import view.memberpanel.Selfbbs;
import view.memberpanel.Sharebbs;

public class MemberMainView extends JFrame implements ActionListener{

	private CardLayout cards = new CardLayout();
	JPanel mainPanel;
	
	JPanel memProfile_Img;
	JLabel memName;
	
	JButton btn_Selfbbs;
	JButton btn_Sharebbs;
	JButton btn_QAbbs;
	JButton btn_Chat;
	boolean chat;
	
	public MemberMainView() {
		setBounds(50, 50, 1200, 800);
		setLayout(null);
		
		mainPanel = new JPanel(cards);
		
		
		mainPanel.add("Singlebbs", new Selfbbs());
		mainPanel.add("Sharebbs", new Sharebbs());
		mainPanel.add("Q&Abbs", new QAbbsMain());
		cards.show(mainPanel, "Singlebbs");
		
		mainPanel.setBounds(200,0,1000,800);

		memProfile_Img = new JPanel();
		memProfile_Img.setBackground(Color.DARK_GRAY);
		memProfile_Img.setBounds(37,50,120,120);
		
		Singleton s = Singleton.getInstance();
		
		memName = new JLabel();
		memName.setText(s.nowMember.getNick());
		memName.setBounds(50,200,40,30);
		
		btn_Selfbbs = new JButton("개인 코드");
		btn_Selfbbs.addActionListener(this);
		btn_Selfbbs.setBounds(30, 250, 100, 80);
		
		btn_Sharebbs = new JButton("코드 공유");
		btn_Sharebbs.addActionListener(this);
		btn_Sharebbs.setBounds(30, 350, 100, 80);
		
		btn_QAbbs = new JButton("Q&A");
		btn_QAbbs.addActionListener(this);
		btn_QAbbs.setBounds(30, 450, 100, 80);
		
		btn_Chat = new JButton("채팅 ");
		btn_Chat.addActionListener(this);
		btn_Chat.setBounds(30, 550, 100, 80);
		
		add(btn_Selfbbs);
		add(btn_Sharebbs);
		add(btn_QAbbs);
		add(btn_Chat);
		
		add(memName);
		add(memProfile_Img);
		add(mainPanel);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public void changePanel(int select) {
		if(select == 1) {
			cards.show(mainPanel, "Singlebbs");
		}else if(select == 2) {
			cards.show(mainPanel, "Sharebbs");
		}else if(select == 3) {
			cards.show(mainPanel, "Q&Abbs");
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_Selfbbs) {
			changePanel(1);
		}else if(e.getSource() == btn_Sharebbs) {
			changePanel(2);
		}else if(e.getSource() == btn_QAbbs) {
			changePanel(3);
		}else if(e.getSource() == btn_Chat) {
			if(chat) {
				setBounds(50, 50, 1200, 800);
				chat=false;
			}else {
				setBounds(50, 50, 1500, 800);
				chat = true;
			}
		}
		
	}
	
	
}
