package view.membermainview;

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

public class SelfbbsWrite extends JPanel implements ActionListener {

	final int INSERT = 0;
	final int UPDATE = 1;
	final int DETAIL = 2;


	// 오른쪽 칸
	JPanel right = new JPanel();
	JLabel titlelab = new JLabel("제목");
	JTextField titletxt = new JTextField();

	JToggleButton Tbtn_Java;
	JToggleButton Tbtn_SQL;
	JToggleButton Tbtn_C;
	JToggleButton Tbtn_ETC;
	
	private JScrollPane jScrPane;
	
	JTextArea codetxt = new JTextArea("");


	JButton savebtn = new JButton("저장");
	ButtonGroup langTogglebtnGroup;

	BBSDto dto;
	SelfbbsMain selfMain;
	int state;
	String lang = "JAVA";

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
		titletxt.setDocument(new JTextFieldLimit(50));	//글자수 50개로 제한
		titletxt.setFont(tilteFont);
		titletxt.setForeground(Color.WHITE);
		Border border = BorderFactory.createLineBorder(Color.white);
		titletxt.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		titletxt.setOpaque(false);
		titletxt.setText(dto.getTitle());
		right.add(titletxt);

		langTogglebtnGroup = new ButtonGroup();

		Tbtn_Java = new JToggleButton("JAVA");
		Tbtn_Java.setBounds(25, 100, 75, 50);
		Tbtn_Java.setSelected(true);

		langTogglebtnGroup.add(Tbtn_Java);

		Tbtn_SQL = new JToggleButton("SQL");
		Tbtn_SQL.setBounds(100, 100, 75, 50);

		langTogglebtnGroup.add(Tbtn_SQL);

		Tbtn_C = new JToggleButton("C");
		Tbtn_C.setBounds(175, 100, 75, 50);

		langTogglebtnGroup.add(Tbtn_C);

		Tbtn_ETC = new JToggleButton("ETC");
		Tbtn_ETC.setBounds(250, 100, 75, 50);

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


		codetxt.setDocument(new JTextFieldLimit(4000));	//4000자 제한
		//codetxt.setBackground(new Color(0,0,0,70));

		codetxt.setOpaque(false);
		Border border2 = BorderFactory.createLineBorder(Color.white);
		codetxt.setBorder(BorderFactory.createCompoundBorder(border2, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		codetxt.setFont(contentFont);
		codetxt.setForeground(Color.white);
		codetxt.append(dto.getContent());
		
		//스크롤바 0으로 줄여서 안보이게하는 코드
		jScrPane = new JScrollPane(codetxt, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
		jScrPane.getVerticalScrollBar().setPreferredSize (new Dimension(0,0));
		jScrPane.setOpaque(false);
		jScrPane.getViewport().setOpaque(false);
		jScrPane.setBorder(BorderFactory.createCompoundBorder(null,
	            BorderFactory.createEmptyBorder(20, 20, 20, 20)));
		jScrPane.setBounds(25,170,750,400);
		right.add(jScrPane);

		savebtn.setBounds(700, 570, 75, 50);
		savebtn.addActionListener(this);
		right.add(savebtn);
		add(right);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Singleton s = Singleton.getInstance();
		Object obj = e.getSource();

		if (obj == savebtn) {
			if (titletxt.getText().equals("") || codetxt.getText().equals("") || langTogglebtnGroup.getSelection() == null) {
				JOptionPane.showMessageDialog(null, "제목 내용을 입력하시고, 언어를 선택하세요");
				return;
			}
			if (state == INSERT) {
				if (Tbtn_Java.isSelected())
					lang = "JAVA";
				else if (Tbtn_C.isSelected())
					lang = "C";
				else if (Tbtn_SQL.isSelected())
					lang = "SQL";
				else
					lang = "ETC";

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
}
