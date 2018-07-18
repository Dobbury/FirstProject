package view.membermainview;

import java.awt.Color;
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
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.BBSDao;
import dto.BBSDto;

import singleton.Singleton;
import view.MemberMainView;

public class SelfbbsWrite extends JPanel implements ActionListener {

	final int INSERT = 0;
	final int UPDATE = 1;
	final int DETAIL = 2;
	
	// 오른쪽 칸
	JPanel right = new JPanel();
	JLabel titlelab = new JLabel("제목");
	JTextField titletxt = new JTextField("");

	JToggleButton Tbtn_Java;
	JToggleButton Tbtn_SQL;
	JToggleButton Tbtn_C;
	JToggleButton Tbtn_ETC;
	
	
	JTextArea codetxt = new JTextArea("");

	JButton savebtn = new JButton("저장");

	BBSDto dto;
	SelfbbsMain selfMain;
	int state;
	
	public SelfbbsWrite(SelfbbsMain selfMain,BBSDto dto,int state) {
		setLayout(null);
		setOpaque(false);
		this.selfMain = selfMain;
		this.state = state;
		this.dto = dto;
		
		Singleton s = Singleton.getInstance();
		
		right.setOpaque(false);
		right.setBounds(0, 0, 900, 700);
		right.setLayout(null);

		Font tilteFont = new Font("굴림", Font.BOLD, 40);

		titletxt.setBounds(25, 25, 750, 50);
		titletxt.setFont(tilteFont);
		titletxt.setForeground(Color.WHITE);
		titletxt.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		//titletxt.setBackground(new Color(0,0,0,70));
		titletxt.setOpaque(false);
		
		titletxt.setText(dto.getTitle());
		

		right.add(titletxt);

		
		ButtonGroup langTogglebtnGroup = new ButtonGroup();
		
		Tbtn_Java = new JToggleButton("JAVA");
		Tbtn_Java.setBounds(25, 100, 75, 50);
		Tbtn_Java.setSelected(true);
		langTogglebtnGroup.add(Tbtn_Java);
		
		Tbtn_SQL = new JToggleButton("SQL");
		Tbtn_SQL.setBounds(100,100,75,50);
		langTogglebtnGroup.add(Tbtn_SQL);
		
		Tbtn_C = new JToggleButton("C");
		Tbtn_C.setBounds(175,100,75,50);
		langTogglebtnGroup.add(Tbtn_C);
		
		Tbtn_ETC = new JToggleButton("ETC");
		Tbtn_ETC.setBounds(250,100,75,50);
		langTogglebtnGroup.add(Tbtn_ETC);
		
		right.add(Tbtn_Java);
		right.add(Tbtn_SQL);
		right.add(Tbtn_C);
		right.add(Tbtn_ETC);

		
		Font contentFont = new Font("굴림", Font.BOLD, 15);

		codetxt.setBounds(25, 170, 750, 400);
		//codetxt.setBackground(new Color(0,0,0,70));
		codetxt.setOpaque(false);
		codetxt.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		codetxt.setFont(contentFont);
		codetxt.setForeground(Color.white);
		codetxt.append(dto.getContent());
		right.add(codetxt);

		
		savebtn.setBounds(600, 580, 75, 50);
		savebtn.addActionListener(this);
		right.add(savebtn);

		add(right);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Singleton s = Singleton.getInstance();
		Object obj = e.getSource();

		if (obj == savebtn) {
			if (state == INSERT) {
				String lang;
				if(Tbtn_Java.isSelected())
					lang="JAVA";
				else if(Tbtn_C.isSelected())
					lang="C";
				else if(Tbtn_SQL.isSelected())
					lang="SQL";
				else
					lang="ETC";
					
				boolean result = s.selfDao.insert(titletxt.getText(),lang, codetxt.getText());
				
				if (result) {
					JOptionPane.showMessageDialog(null, "저장되었습니다.");
					selfMain.setList(s.selfDao.getSelfBbsList());
					selfMain.changePanel(DETAIL, dto);
				}
				//테이블 모델 갱신
				
			} else if (state == UPDATE){
				int choice = JOptionPane.YES_NO_OPTION;
				choice = JOptionPane.showConfirmDialog(null, "정말 수정하시겠습니까?", "WARNING", choice);

				if (choice == 0) {
					dto.setTitle(titletxt.getText());
					dto.setContent(codetxt.getText());
					//dto.setLanguage(language);
					
					boolean result = s.selfDao.update(dto);
					if (result) {
						JOptionPane.showMessageDialog(null, "수정되었습니다.");
						selfMain.setList(s.selfDao.getSelfBbsList());
						selfMain.changePanel(DETAIL, dto);
					}
					
				}
			}
		}
	}
}