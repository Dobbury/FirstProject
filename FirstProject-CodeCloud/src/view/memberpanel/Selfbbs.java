package view.memberpanel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Action;
import javax.swing.GrayFilter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import view.MemberMainView;

public class Selfbbs extends JPanel implements Action, MouseListener{

	MemberMainView F;
	//왼쪽 칸
	private JPanel left = new JPanel();
	private JButton plus = new JButton("+");
	private JButton minus = new JButton("-");
	
	private JTable jTable;
	private JScrollPane jScrPane;
	private DefaultTableModel model;
	private String[] head = {"title"};
	private Object[][] rowData;
	
	private String[] searchlist = {"전체보기", "제목", "언어", "내용"};
	JComboBox searchbox = new JComboBox(searchlist);
	
	JTextField searchtxt = new JTextField();
	JButton searchbtn = new JButton("검색");
	
	//오른쪽 칸
	JPanel right = new JPanel();
	JLabel titlelab = new JLabel("제목");
	JTextField titletxt = new JTextField("");
	
	JLabel langlab = new JLabel("언어");
	String[] langlist = {"JAVA", "SQL", "C", "ETC"};
	JComboBox langbox = new JComboBox(langlist);
	
	JLabel codelab = new JLabel("C O D E");
	JTextArea codetxt = new JTextArea("");
	
	JButton editbtn = new JButton("수정");
	JButton sharebtn = new JButton("공유");
	JButton savebtn = new JButton("저장");
	

	public Selfbbs() {
	
		setLayout(null);
		
		//left
		left.setBackground(Color.GRAY);
		left.setBounds(0, 0, 200, 800);
		left.setLayout(null);
		
		plus.setBounds(0, 0, 100, 50);
		plus.addActionListener(this);
		minus.setBounds(100, 0, 100, 50);
		left.add(plus);
		left.add(minus);
		
		rowData = new Object[10][1];
		for (int i = 0; i < 10; i++) {
			rowData[i][0] = "TEMP";
		}
		
		model = new DefaultTableModel(rowData, head);
		//model.setDataVector(rowData, head);
		
		jTable = new JTable(model);
		jTable.addMouseListener(this);
		jTable.setRowHeight(100);
		jScrPane = new JScrollPane(jTable);
		jScrPane.setBounds(0, 50, 200, 650);
		left.add(jScrPane);
		
		searchbox.setBounds(0, 700, 200, 25);
		left.add(searchbox);
		searchtxt.setBounds(0, 725, 140, 40);
		left.add(searchtxt);
		searchbtn.setBounds(140, 725, 60, 40);
		left.add(searchbtn);
		
		//right
		right.setBackground(Color.DARK_GRAY);
		right.setBounds(200, 0, 800, 800);
		right.setLayout(null);
		
		titlelab.setBounds(25, 25, 75, 50);
		titlelab.setOpaque(true);
		titlelab.setBackground(Color.lightGray);
		right.add(titlelab);
		titletxt.setBounds(120, 25, 200, 50);
		titletxt.setText("TEMP");
		titletxt.setEnabled(false);
		right.add(titletxt);
		
		langlab.setBounds(25, 100, 75, 50);
		langlab.setOpaque(true);
		langlab.setBackground(Color.lightGray);
		right.add(langlab);
		langbox.setBounds(120, 100, 100, 50);
		langbox.setEnabled(false);
		right.add(langbox);
		
		codelab.setBounds(25, 170, 50, 30);
		codelab.setOpaque(true);
		codelab.setBackground(Color.lightGray);
		codetxt.setBounds(25, 210, 650, 450);
		codetxt.setText("TEMP");
		codetxt.setEnabled(false);
		
		right.add(codelab);
		right.add(codetxt);
		
		//700
		editbtn.setBounds(400, 680, 75, 50);
		right.add(editbtn);
		sharebtn.setBounds(500, 680, 75, 50);
		right.add(sharebtn);
		savebtn.setBounds(600, 680, 75, 50);
		right.add(savebtn);
		
		
		this.add(left);
		this.add(right);

	}



	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == plus) {
			titletxt.setText("");
			titletxt.setEnabled(true);
			
			langbox.setEnabled(true);
			
			codetxt.setText("");
			codetxt.setEnabled(true);
			
			editbtn.setVisible(false);
			sharebtn.setVisible(false);
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		titletxt.setText("TEMP");
		titletxt.setEnabled(false);
		
		langbox.setEnabled(true);
		
		codetxt.setText("TEMP");
		codetxt.setEnabled(false);
		
		editbtn.setVisible(true);
		sharebtn.setVisible(true);
		
	}



	@Override
	public Object getValue(String key) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void putValue(String key, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
}
