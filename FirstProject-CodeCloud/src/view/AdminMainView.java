package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import singleton.Singleton;
import view.adminpanel.adQAbbsMain;



public class AdminMainView extends JFrame implements ActionListener {

   private CardLayout cards = new CardLayout();
   JPanel mainPanel;

   JPanel memProfile_Img;
   JLabel memName;

   JButton btn_Member;
   JButton btn_Sharebbs;
   JButton btn_QAbbs;
   JButton btn_Chat;
   JButton btn_Logout;
   boolean chat;

   public AdminMainView() {

      setBounds(50, 50, 1200, 800);

      setLayout(null);

      mainPanel = new JPanel(cards);
      
   //   mainPanel.add("Memberbbs", new MemberBBS());
  //    mainPanel.add("AdminSharebbs", new AdminSharebbs());
      
      mainPanel.add("Q&Abbs", new adQAbbsMain());
      cards.show(mainPanel, "Q&Abbs");

      mainPanel.setBounds(200, 0, 1000, 800);
      memProfile_Img = new JPanel();
      memProfile_Img.setBackground(Color.DARK_GRAY);
      memProfile_Img.setBounds(37, 50, 120, 120);

      memName = new JLabel();
      memName.setText("관리자");
      memName.setBounds(50, 200, 40, 30);

      btn_Member = new JButton("회원 관리");
      btn_Member.addActionListener(this);
      btn_Member.setBounds(30, 250, 100, 80);

      btn_Sharebbs = new JButton("공유 관리");
      btn_Sharebbs.addActionListener(this);
      btn_Sharebbs.setBounds(30, 350, 100, 80);

      btn_QAbbs = new JButton("Q&A 관리");
      btn_QAbbs.addActionListener(this);
      btn_QAbbs.setBounds(30, 450, 100, 80);

      btn_Chat = new JButton("채팅");
      btn_Chat.addActionListener(this);
      btn_Chat.setBounds(30, 550, 100, 80);

      btn_Logout = new JButton("로그아웃");
      btn_Logout.addActionListener(this);
      btn_Logout.setBounds(30, 650, 100, 80);

      add(btn_Member);
      add(btn_Sharebbs);
      add(btn_QAbbs);
      add(btn_Chat);
      add(btn_Logout);

      add(memName);
      add(memProfile_Img);
      add(mainPanel);
      setVisible(true);
      setResizable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   }

   public void changePanel(int select) {
      if (select == 1) {
         cards.show(mainPanel, "Memberbbs");
      } else if (select == 2) {
         cards.show(mainPanel, "AdminSharebbs");
      } else if (select == 3) {
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
            setBounds(50, 50, 1200, 800);
            chat = false;
         } else {
            setBounds(50, 50, 1500, 800);
            chat = true;
         }
      } else if (e.getSource() == btn_Logout) {
         Singleton s = Singleton.getInstance();
         s.nowMember = null;
         this.dispose();
         s.MemCtrl.login();

      }
   }
}