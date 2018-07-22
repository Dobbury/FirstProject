package view.chatview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import chatting.ClientBackground;
import singleton.Singleton;

public class chatPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JTextArea jta = new JTextArea(34, 25);
	private JTextField jtf = new JTextField(25);

	private JScrollPane scrl;
	private ClientBackground client = new ClientBackground();
	private static String nickName;

	public chatPanel() {

		Singleton s = Singleton.getInstance();
		
		
		nickName = s.nowMember.getNick();
		
		//채팅판 투명화, 커서 흰색, 텍스트에리어 수정불가
		jta.setOpaque(false);
		jta.setEditable(false);
		jta.setForeground(Color.WHITE);
		jtf.setOpaque(false);
		jtf.setCaretColor(Color.WHITE);
		jtf.setForeground(Color.WHITE);
		
		
		//스크롤바 0으로 줄여서 안보이게하는 코드
		scrl = new JScrollPane(jta, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
		scrl.getVerticalScrollBar().setPreferredSize (new Dimension(0,0));
		scrl.setOpaque(false);
		scrl.getViewport().setOpaque(false);
					
		add(scrl, BorderLayout.CENTER);

		add(jtf, BorderLayout.SOUTH);
		jtf.addActionListener(this);

		setBounds(0, 0, 300, 700);
		
		client.setGui(this);
		client.setNickName(nickName);

	}

	@Override
	// 말치면 보내는 부분
	public void actionPerformed(ActionEvent e) {
		String msg = nickName + " : " + jtf.getText() + "\n";
		System.out.println(msg);
		client.sendMessage(msg);
		jtf.setText("");
	}

	public void appendMsg(String msg) {
		jta.append(msg);
	}

	public void connect() {
		client.connet();
	}

	public void close() {
		client.close();
	}
}
