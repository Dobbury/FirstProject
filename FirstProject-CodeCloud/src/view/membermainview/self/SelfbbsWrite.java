package view.membermainview.self;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.BBSDao;
import dto.BBSDto;

import singleton.Singleton;
import textlimit.*;
import view.MemberMainView;

public class SelfbbsWrite extends JPanel implements ActionListener,FocusListener {

	final int INSERT = 0;
	final int UPDATE = 1;
	final int DETAIL = 2;

	// 오른쪽 칸
	JPanel right = new JPanel();
	JLabel titlelab = new JLabel("제목");
	JTextField titletxt = new JTextField("");

	ImageIcon javaIc1;
	ImageIcon javaIc2;
	ImageIcon javaIc3;
	JToggleButton Tbtn_Java;

	ImageIcon sqlIc1;
	ImageIcon sqlIc2;
	ImageIcon sqlIc3;
	JToggleButton Tbtn_SQL;

	ImageIcon cIc1;
	ImageIcon cIc2;
	ImageIcon cIc3;
	JToggleButton Tbtn_C;

	ImageIcon etcIc1;
	ImageIcon etcIc2;
	ImageIcon etcIc3;
	JToggleButton Tbtn_ETC;

	private JScrollPane jScrPane;


	ImageIcon saveIc1;
	ImageIcon saveIc2;
	ImageIcon saveIc3;
	JButton savebtn;
	ButtonGroup langTogglebtnGroup;

	JTextArea codetxt = new JTextArea("");



	BBSDto dto;
	SelfbbsMain selfMain;
	int state;
	String lang = "JAVA";
	String title_Hint = "제목을 입력해 주세요";
	
	public SelfbbsWrite(SelfbbsMain selfMain, BBSDto dto, int state) {
		setLayout(null);
		setOpaque(false);
		this.selfMain = selfMain;
		this.state = state;
		this.dto = dto;
		if (dto.getLanguage() != null) {
			this.lang = dto.getLanguage();
		}
		
		

		Singleton s = Singleton.getInstance();

		right.setOpaque(false);
		right.setBounds(0, 0, 900, 700);
		right.setLayout(null);

		Font tilteFont = new Font("굴림", Font.BOLD, 30);

		titletxt.setBounds(25, 35, 400, 50);
		titletxt.setDocument(new JTextFieldLimit(50)); // 글자수 50개로 제한
		titletxt.setFont(tilteFont);
		titletxt.setForeground(Color.WHITE);
		titletxt.setBorder(BorderFactory.createCompoundBorder(null, BorderFactory.createEmptyBorder(0, 0, 0, 0)));
		titletxt.setOpaque(false);
		if(dto.getTitle().equals(""))
			titletxt.setText(title_Hint);
		else
			titletxt.setText(dto.getTitle());
		titletxt.addFocusListener(this);
		right.add(titletxt);

		langTogglebtnGroup = new ButtonGroup();


		javaIc1 = new ImageIcon("img/selfbbs/btn_java1.png");
		javaIc2 = new ImageIcon("img/selfbbs/btn_java2.png");
		javaIc3 = new ImageIcon("img/selfbbs/btn_java3.png");
		Tbtn_Java = new JToggleButton(javaIc3);
		Tbtn_Java.setRolloverIcon(javaIc2);
		Tbtn_Java.setPressedIcon(javaIc2);
		Tbtn_Java.setBorderPainted(false);
		Tbtn_Java.setContentAreaFilled(false);
		Tbtn_Java.setFocusPainted(false);
		Tbtn_Java.setSelected(true);
		Tbtn_Java.setBounds(25, 100, 83, 43);
		Tbtn_Java.addActionListener(this);
		langTogglebtnGroup.add(Tbtn_Java);

		cIc1 = new ImageIcon("img/selfbbs/btn_c1.png");
		cIc2 = new ImageIcon("img/selfbbs/btn_c2.png");
		cIc3 = new ImageIcon("img/selfbbs/btn_c3.png");
		Tbtn_C = new JToggleButton(cIc1);
		Tbtn_C.setRolloverIcon(cIc2);
		Tbtn_C.setPressedIcon(cIc2);
		Tbtn_C.setBorderPainted(false);
		Tbtn_C.setContentAreaFilled(false);
		Tbtn_C.setFocusPainted(false);
		Tbtn_C.setSelected(true);
		Tbtn_C.setBounds(188, 100, 83, 43);
		Tbtn_C.addActionListener(this);
		langTogglebtnGroup.add(Tbtn_C);

		sqlIc1 = new ImageIcon("img/selfbbs/btn_sql1.png");
		sqlIc2 = new ImageIcon("img/selfbbs/btn_sql2.png");
		sqlIc3 = new ImageIcon("img/selfbbs/btn_sql3.png");
		Tbtn_SQL = new JToggleButton(sqlIc1);
		Tbtn_SQL.setRolloverIcon(sqlIc2);
		Tbtn_SQL.setPressedIcon(sqlIc2);
		Tbtn_SQL.setBorderPainted(false);
		Tbtn_SQL.setContentAreaFilled(false);
		Tbtn_SQL.setFocusPainted(false);
		Tbtn_SQL.setSelected(true);
		Tbtn_SQL.setBounds(106, 100, 83, 43);
		Tbtn_SQL.addActionListener(this);
		langTogglebtnGroup.add(Tbtn_SQL);

		etcIc1 = new ImageIcon("img/selfbbs/btn_etc1.png");
		etcIc2 = new ImageIcon("img/selfbbs/btn_etc2.png");
		etcIc3 = new ImageIcon("img/selfbbs/btn_etc3.png");
		Tbtn_ETC = new JToggleButton(etcIc1);
		Tbtn_ETC.setRolloverIcon(etcIc2);
		Tbtn_ETC.setPressedIcon(etcIc2);
		Tbtn_ETC.setBorderPainted(false);
		Tbtn_ETC.setContentAreaFilled(false);
		Tbtn_ETC.setFocusPainted(false);
		Tbtn_ETC.setSelected(true);
		Tbtn_ETC.setBounds(270, 100, 83, 43);
		Tbtn_ETC.addActionListener(this);
		langTogglebtnGroup.add(Tbtn_ETC);
		
		if(lang.equals("SQL")) {
			Tbtn_SQL.setSelected(true);
		}else if(lang.equals("C")) {
			Tbtn_C.setSelected(true);
		}else if(lang.equals("ETC")) {
			Tbtn_ETC.setSelected(true);
		}

		right.add(Tbtn_Java);
		right.add(Tbtn_SQL);
		right.add(Tbtn_C);
		right.add(Tbtn_ETC);

		Font contentFont = new Font("굴림", Font.BOLD, 20);


		//코드 배경
		ImageIcon code_back_Img = new ImageIcon("img/selfbbs/self_code_background.png");
		
		JLabel code_backgorund = new JLabel();
		code_backgorund.setIcon(code_back_Img);
		code_backgorund.setBounds(25,170,750,400);
		
		
		codetxt.setDocument(new JTextFieldLimit(4000));	//4000자 제한
		//codetxt.setBackground(new Color(0,0,0,70));


		codetxt.setOpaque(false);
		
		codetxt.setFont(contentFont);
		codetxt.setForeground(Color.white);
		
		codetxt.append(dto.getContent());

		//스크롤바 0으로 줄여서 안보이게하는 코드
		jScrPane = new JScrollPane(codetxt, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
		jScrPane.getVerticalScrollBar().setPreferredSize (new Dimension(0,0));
		jScrPane.setBorder(BorderFactory.createCompoundBorder(null, BorderFactory.createEmptyBorder(20, 20, 20, 20)));
		jScrPane.setOpaque(false);
		jScrPane.getViewport().setOpaque(false);
		jScrPane.setBounds(25,170,750,400);

		right.add(jScrPane);

		saveIc1 = new ImageIcon("img/selfbbs/self_save_on.png");
		saveIc2 = new ImageIcon("img/selfbbs/self_save_off.png");
		saveIc3 = new ImageIcon("img/selfbbs/self_save_ing.png");
		savebtn = new JButton(saveIc1);
		savebtn.setRolloverIcon(saveIc2);
		savebtn.setPressedIcon(saveIc3);
		savebtn.setBorderPainted(false);
		savebtn.setContentAreaFilled(false);
		savebtn.setFocusPainted(false);
		
		savebtn.setBounds(700, 590, 76, 51);
		savebtn.addActionListener(this);
		right.add(savebtn);
		add(right);
		add(code_backgorund);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Singleton s = Singleton.getInstance();
		Object obj = e.getSource();
		if (e.getSource() == Tbtn_Java) {
			Tbtn_Java.setIcon(javaIc3);
			Tbtn_C.setIcon(cIc1);
			Tbtn_SQL.setIcon(sqlIc1);
			Tbtn_ETC.setIcon(etcIc1);
		} else if (e.getSource() == Tbtn_C) {
			Tbtn_C.setIcon(cIc3);
			Tbtn_Java.setIcon(javaIc1);
			Tbtn_SQL.setIcon(sqlIc1);
			Tbtn_ETC.setIcon(etcIc1);
		} else if (e.getSource() == Tbtn_SQL) {
			Tbtn_SQL.setIcon(sqlIc3);
			Tbtn_Java.setIcon(javaIc1);
			Tbtn_C.setIcon(cIc1);
			Tbtn_ETC.setIcon(etcIc1);
		} else if (e.getSource() == Tbtn_ETC) {
			Tbtn_ETC.setIcon(etcIc3);
			Tbtn_Java.setIcon(javaIc1);
			Tbtn_C.setIcon(cIc1);
			Tbtn_SQL.setIcon(sqlIc1);
		}
		if (obj == savebtn) {
			if (titletxt.getText().equals(title_Hint) ||titletxt.getText().equals("") || codetxt.getText().equals("") || langTogglebtnGroup.getSelection() == null) {
				JOptionPane.showMessageDialog(null, "제목 내용을 입력하시고, 언어를 선택하세요");
				return;
			}
			if (state == INSERT) {
				if (Tbtn_Java.isSelected()) {
					lang = "JAVA";

				} else if (Tbtn_C.isSelected()) {
					lang = "C";
				}
				else if (Tbtn_SQL.isSelected()) {
					lang = "SQL";

				} else {
					lang = "ETC";

				}

				int seq = s.selfDao.insert(titletxt.getText(), lang, codetxt.getText());

				JOptionPane.showMessageDialog(null, "저장되었습니다.");
				selfMain.changePanel(DETAIL, new BBSDto(seq, titletxt.getText(), codetxt.getText(), 0, 0, 0, lang));
				selfMain.setList(s.selfDao.getSelfBbsList());
			}else if (state == UPDATE) {
				int choice = JOptionPane.YES_NO_OPTION;
				choice = JOptionPane.showConfirmDialog(null, "정말 수정하시겠습니까?", "WARNING", choice);

				if (choice == 0) {
					if (Tbtn_Java.isSelected())
						lang = "JAVA";
					else if (Tbtn_C.isSelected())
						lang = "C";
					else if (Tbtn_SQL.isSelected())
						lang = "SQL";
					else
						lang = "ETC";

					dto.setTitle(titletxt.getText());
					dto.setContent(codetxt.getText());
					dto.setLanguage(lang);

					boolean result = s.selfDao.update(dto);
					if (result) {
						JOptionPane.showMessageDialog(null, "수정되었습니다.");
						selfMain.setList(s.selfDao.getSelfBbsList());
						selfMain.changePanel(DETAIL, dto);
					}
				}
			}
			// 테이블 모델 갱신

		} 
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == titletxt) {
			if (titletxt.getText().equals(title_Hint))
				titletxt.setText("");
			titletxt.setForeground(Color.white);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == titletxt) {
			if (titletxt.getText().length() == 0) {
				titletxt.setText(title_Hint);
				titletxt.setForeground(Color.WHITE);
			}
		}
	}
}
