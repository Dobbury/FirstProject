package view.memberpanel;

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
import javax.swing.table.DefaultTableModel;

import dao.BBSDao;
import dto.BBSDto;

import singleton.Singleton;
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
	private String[] head = {"title", "seq", "share"};
	private Object[][] rowData;
	
	private String[] searchlist = {"전체보기", "제목", "언어", "내용"};
	JComboBox searchbox = new JComboBox(searchlist);
	
	JTextField searchtext = new JTextField();
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
	
	JToggleButton editbtn = new JToggleButton("수정");
	JToggleButton sharebtn = new JToggleButton("공유");
	JButton savebtn = new JButton("저장");
	
	int currseq;
	
	BBSDao dao = new BBSDao();
	
	MouseEvent selectfirst;
	
	Robot robot;

	public Selfbbs() {
		setLayout(null);
		
		Singleton s = Singleton.getInstance();
		
		if(s.selfcodelist.size() >0) {
			currseq = s.selfcodelist.get(0).getSeq();
		}
		
		
		
		//left
		left.setBackground(Color.GRAY);
		left.setBounds(0, 0, 200, 800);
		left.setLayout(null);
		
		plus.setBounds(0, 0, 100, 50);
		plus.addActionListener(this);
		minus.setBounds(100, 0, 100, 50);
		minus.addActionListener(this);
		left.add(plus);
		left.add(minus);
		
		if(s.selfcodelist.size()>0) {
			rowData = new Object[s.selfcodelist.size()][3];
			
			for (int i = 0; i < s.selfcodelist.size(); i++) {
				
				rowData[i][0] = s.selfcodelist.get(i).getTitle();
				rowData[i][1] = s.selfcodelist.get(i).getSeq();
				rowData[i][2] = s.selfcodelist.get(i).getShare();
			}
		}
		

		
		model = new DefaultTableModel(rowData, head);

		
		//model.setDataVector(rowData, head);
		
		jTable = new JTable(model) {
			@Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		jTable.addMouseListener(this);
		jTable.setRowHeight(100);
		jTable.getColumnModel().getColumn(0).setMaxWidth(200);
		jTable.removeColumn(jTable.getColumnModel().getColumn(1));
		jTable.removeColumn(jTable.getColumnModel().getColumn(1));
		jTable.getSelectionModel().setSelectionInterval(0, 0);
		jScrPane = new JScrollPane(jTable);
		jScrPane.setBounds(0, 50, 200, 650);
		left.add(jScrPane);
		
		searchbox.setBounds(0, 700, 200, 25);
		left.add(searchbox);
		searchtext.setBounds(0, 725, 140, 40);
		left.add(searchtext);
		searchbtn.setBounds(140, 725, 60, 40);
		searchbtn.addActionListener(this);
		left.add(searchbtn);
		
		//right
		right.setBackground(Color.DARK_GRAY);
		right.setBounds(200, 0, 800, 800);
		right.setLayout(null);
		
		Font font = new Font("Times New Roman", Font.BOLD, 22);
		
		titlelab.setBounds(25, 25, 75, 50);
		titlelab.setOpaque(true);
		titlelab.setBackground(Color.lightGray);
		right.add(titlelab);
		titletxt.setBounds(120, 25, 200, 50);
		titletxt.setEditable(false);
		titletxt.setFont(font);
        titletxt.setForeground(Color.BLACK);
        titletxt.setBackground(Color.lightGray);

		if(s.selfcodelist.size()>0) {
			titletxt.setText(s.selfcodelist.get(0).getTitle());
		}
		
		
		right.add(titletxt);
		
		langlab.setBounds(25, 100, 75, 50);
		langlab.setOpaque(true);
		langlab.setBackground(Color.lightGray);
		right.add(langlab);
		langbox.setBounds(120, 100, 100, 50);

		langbox.setEnabled(false);
		if(s.selfcodelist.size()>0) {
			String tmp = s.selfcodelist.get(0).getLanguage();
			if(tmp.equals("JAVA")) {
				langbox.setSelectedIndex(0);
			}else if(tmp.equals("SQL")) {
				langbox.setSelectedIndex(1);
			}else if(tmp.equals("C")) {
				langbox.setSelectedIndex(2);
			}else {
				langbox.setSelectedIndex(3);
			}
		}
		right.add(langbox);
		
		codelab.setBounds(25, 170, 50, 30);
		codelab.setOpaque(true);
		codelab.setBackground(Color.lightGray);
		codetxt.setBounds(25, 210, 650, 450);
		codetxt.setBackground(Color.lightGray);
        codetxt.setFont(font);
        codetxt.setForeground(Color.BLACK);

		if(s.selfcodelist.size()>0) {
			codetxt.setText(s.selfcodelist.get(0).getContent());
		}
		codetxt.setEditable(false);
		
		right.add(codelab);
		right.add(codetxt);
		
		//700
		editbtn.setBounds(400, 680, 75, 50);
		editbtn.addActionListener(this);
		right.add(editbtn);
		sharebtn.setBounds(500, 680, 75, 50);
		if(s.selfcodelist.size()>0) {
			if (s.selfcodelist.get(0).getShare() == 1) {
				sharebtn.setSelected(true);
			}
		}
		sharebtn.addActionListener(this);
		right.add(sharebtn);
		savebtn.setBounds(600, 680, 75, 50);
		savebtn.addActionListener(this);
		right.add(savebtn);
		
		
		this.add(left);
		this.add(right);

	}



	@Override
	public void actionPerformed(ActionEvent e) {
		Singleton s = Singleton.getInstance();
		Object obj = e.getSource();
		if(obj == plus) { 
			titletxt.setText("");
			titletxt.setEditable(true);
			
			langbox.setEnabled(true);
			langbox.setSelectedIndex(0);
			
			codetxt.setText("");
			codetxt.setEditable(true);
			
			editbtn.setVisible(false);
			sharebtn.setVisible(false);
			savebtn.setVisible(true);
		}
		if (obj == savebtn) {
			if (editbtn.isVisible() == false) {
				//수정 버튼 없을시 새로운 코드 저장
				int seq = dao.insert(titletxt.getText(), (String)langbox.getSelectedItem(), codetxt.getText());
				model.addRow(new Object[] {titletxt.getText(), seq, 0});
			}else {
				if (editbtn.isSelected() == true) {
					int choice = JOptionPane.YES_NO_OPTION;
					choice = JOptionPane.showConfirmDialog (null, "정말 수정하시겠습니까?","WARNING", choice);
					
					if (choice == 0) {
						boolean sbool;
						if (sharebtn.isVisible() && sharebtn.isSelected()) {
							sbool = true;
						}else {
							sbool = false;
						}
						int result =dao.update(currseq, titletxt.getText(), (String)langbox.getSelectedItem(), codetxt.getText(), sbool);
						if (result > 0) {
							JOptionPane.showMessageDialog(null, "수정되었습니다.");
							editbtn.setSelected(false);
						}
						
					}else {
						//수정 안하면 다시 원상복귀
						editbtn.setSelected(false);
						for (int i = 0; i < s.selfcodelist.size(); i++) {
							if (currseq == s.selfcodelist.get(i).getSeq()) {
								titletxt.setText(s.selfcodelist.get(i).getTitle());
								titletxt.setEditable(false);
								codetxt.setText(s.selfcodelist.get(i).getContent());
								codetxt.setEditable(false);
								String tmp = s.selfcodelist.get(i).getLanguage();
								if(tmp.equals("JAVA")) {
									langbox.setSelectedIndex(0);
								}else if(tmp.equals("SQL")) {
									langbox.setSelectedIndex(1);
								}else if(tmp.equals("C")) {
									langbox.setSelectedIndex(2);
								}else {
									langbox.setSelectedIndex(3);
								}
								langbox.setEnabled(false);
								break;
							}
						}
					}
					
				}
			}
		}
		if (obj == editbtn) {
			if (editbtn.isSelected()) {
				titletxt.setEditable(true);
				codetxt.setEditable(true);
				langbox.setEnabled(true);
			}else {
				//수정 저장 안하고 다시 수정 버튼 누르면 원상복귀
				for (int i = 0; i < s.selfcodelist.size(); i++) {
					if (currseq == s.selfcodelist.get(i).getSeq()) {
						titletxt.setText(s.selfcodelist.get(i).getTitle());
						titletxt.setEditable(false);
						codetxt.setText(s.selfcodelist.get(i).getContent());
						codetxt.setEditable(false);
						String tmp = s.selfcodelist.get(i).getLanguage();
						if(tmp.equals("JAVA")) {
							langbox.setSelectedIndex(0);
						}else if(tmp.equals("SQL")) {
							langbox.setSelectedIndex(1);
						}else if(tmp.equals("C")) {
							langbox.setSelectedIndex(2);
						}else {
							langbox.setSelectedIndex(3);
						}
						langbox.setEnabled(false);
						break;
					}
				}
			}
			
		}
		if (obj == sharebtn) {
			
			if (editbtn.isSelected()) {
				JOptionPane.showMessageDialog(null, "수정중에는 공유할 수 없습니다.");
				sharebtn.setSelected(false);
			}else {
				
				if (sharebtn.isSelected()) {
					//공유 하기
					int choice = JOptionPane.YES_NO_OPTION;
					choice = JOptionPane.showConfirmDialog (null, "이 코드를 공유하시겠습니까?","WARNING", choice);
					if (choice == 0) {
						BBSDto tempdto = null;;
						for (int i = 0; i < s.selfcodelist.size(); i++) {
							if (currseq == s.selfcodelist.get(i).getSeq()) {
								s.selfcodelist.get(i).setShare(1);
								tempdto = s.selfcodelist.get(i);
								break;
							}
						}
						
						int result =dao.share(tempdto);
						if (result > 0) {
							JOptionPane.showMessageDialog(null, "공유되었습니다.");
						}
					}else {
						sharebtn.setSelected(false);
					}
				}else {
					//공유 풀기
					int choice2 = JOptionPane.YES_NO_OPTION;
					choice2 = JOptionPane.showConfirmDialog (null, "이 코드를 공유해제 하시겠습니까?","WARNING", choice2);
					if (choice2 == 0) {
						for (int i = 0; i < s.selfcodelist.size(); i++) {
							if (currseq == s.selfcodelist.get(i).getSeq()) {
								s.selfcodelist.get(i).setShare(0);
								break;
							}
						}
						
						int result2 =dao.unshare(currseq);
						if (result2 > 0) {
							JOptionPane.showMessageDialog(null, "공유가 해제 되었습니다.");
						}
					}else {
						sharebtn.setSelected(true);
					}
				}
				
			}
		}
		if (obj == minus) {
			int choice = JOptionPane.YES_NO_OPTION;
			choice = JOptionPane.showConfirmDialog (null, "이 코드를 정말 삭제하시겠습니까?","WARNING", choice);
			
			if (choice == 0) {
				boolean sbool;
				if (sharebtn.isVisible() && sharebtn.isSelected()) {
					sbool = true;
				}else {
					sbool = false;
				}
				int result =dao.delete(currseq, sbool);
				if (result > 0) {
					JOptionPane.showMessageDialog(null, "삭제되었습니다.");
					editbtn.setSelected(false);
					model.removeRow(jTable.getSelectedRow());
				}
				
			}
			titletxt.setText("");
			titletxt.setEnabled(false);
			langbox.setSelectedIndex(0);
			langbox.setEnabled(false);
			codetxt.setText("");
			codetxt.setEnabled(false);
			sharebtn.setVisible(false);
			editbtn.setVisible(false);
			savebtn.setVisible(false);
			
		}
		if (obj == searchbtn) {
			//{"전체보기", "제목", "언어", "내용"};

			String searchstr = searchtext.getText();
			List<BBSDto> tmplist = new LinkedList<>();
			Object[][] tmparr;
			
			if(searchbox.getSelectedIndex() == 0) {
				//전체보기
				tmplist = s.selfcodelist;
				tmparr = new Object[tmplist.size()][3];
				
				for (int i = 0; i < tmplist.size(); i++) {
					BBSDto dto = tmplist.get(i);
		
					tmparr[i][0] = dto.getTitle();
					tmparr[i][1] = dto.getSeq();
					tmparr[i][2] = dto.getShare();
				}
				
				model.setDataVector(tmparr, head);
				jTable.setModel(model);
				jTable.getColumnModel().getColumn(0).setMaxWidth(200);
				jTable.removeColumn(jTable.getColumnModel().getColumn(1));
				jTable.removeColumn(jTable.getColumnModel().getColumn(1));
				((AbstractTableModel) model).fireTableDataChanged();
				searchtext.setText("");
				
			}else if(searchbox.getSelectedIndex() == 1){
				//이름
				String pattern = ".*" + searchstr + ".*";
				
				for (int i = 0; i < s.selfcodelist.size(); i++) {
					if (s.selfcodelist.get(i).getTitle().matches(pattern)){
						tmplist.add(s.selfcodelist.get(i));
					}
				}
				tmparr = new Object[tmplist.size()][3];	// 테이블의 2차원배열이 생성
				
				for (int i = 0; i < tmplist.size(); i++) {
					BBSDto dto = tmplist.get(i);
		
					tmparr[i][0] = dto.getTitle();
					tmparr[i][1] = dto.getSeq();
					tmparr[i][2] = dto.getShare();
				}
				model.setDataVector(tmparr, head);
				jTable.setModel(model);
				jTable.getColumnModel().getColumn(0).setMaxWidth(200);
				jTable.removeColumn(jTable.getColumnModel().getColumn(1));
				jTable.removeColumn(jTable.getColumnModel().getColumn(1));
				((AbstractTableModel) model).fireTableDataChanged();
				searchtext.setText("");
				
				
			}else if(searchbox.getSelectedIndex() == 2){
				//언어
				
				for (int i = 0; i < s.selfcodelist.size(); i++) {
					if (s.selfcodelist.get(i).getLanguage().equals(searchstr)){
						tmplist.add(s.selfcodelist.get(i));
					}
				}
				tmparr = new Object[tmplist.size()][3];	// 테이블의 2차원배열이 생성
				
				for (int i = 0; i < tmplist.size(); i++) {
					BBSDto dto = tmplist.get(i);
		
					tmparr[i][0] = dto.getTitle();
					tmparr[i][1] = dto.getSeq();
					tmparr[i][2] = dto.getShare();
				}
				model.setDataVector(tmparr, head);
				jTable.setModel(model);
				jTable.getColumnModel().getColumn(0).setMaxWidth(200);
				jTable.removeColumn(jTable.getColumnModel().getColumn(1));
				jTable.removeColumn(jTable.getColumnModel().getColumn(1));
				((AbstractTableModel) model).fireTableDataChanged();
				searchtext.setText("");
				
			}else if(searchbox.getSelectedIndex() == 3){
				//내용
				String pattern = ".*" + searchstr + ".*";
				
				for (int i = 0; i < s.selfcodelist.size(); i++) {
					if (s.selfcodelist.get(i).getContent().matches(pattern)){
						tmplist.add(s.selfcodelist.get(i));
					}
				}
				tmparr = new Object[tmplist.size()][3];	// 테이블의 2차원배열이 생성
				
				for (int i = 0; i < tmplist.size(); i++) {
					BBSDto dto = tmplist.get(i);
		
					tmparr[i][0] = dto.getTitle();
					tmparr[i][1] = dto.getSeq();
					tmparr[i][2] = dto.getShare();
				}
				model.setDataVector(tmparr, head);
				jTable.setModel(model);
				jTable.getColumnModel().getColumn(0).setMaxWidth(200);
				jTable.removeColumn(jTable.getColumnModel().getColumn(1));
				jTable.removeColumn(jTable.getColumnModel().getColumn(1));
				((AbstractTableModel) model).fireTableDataChanged();
				searchtext.setText("");
				
			}
			
		}
		
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		JTable source = (JTable) e.getSource();
		int rows = source.rowAtPoint(e.getPoint());
		
		Singleton s = Singleton.getInstance();
		try {
			int seq = (int) source.getModel().getValueAt(rows, 1);
			int shar = (int) source.getModel().getValueAt(rows, 2);
			currseq = seq;
			for (int i = 0; i < s.selfcodelist.size(); i++) {
				if (seq == s.selfcodelist.get(i).getSeq()) {
					titletxt.setText(s.selfcodelist.get(i).getTitle());
					titletxt.setEditable(false);
					codetxt.setText(s.selfcodelist.get(i).getContent());
					codetxt.setEditable(false);
					String tmp = s.selfcodelist.get(i).getLanguage();
					if(tmp.equals("JAVA")) {
						langbox.setSelectedIndex(0);
					}else if(tmp.equals("SQL")) {
						langbox.setSelectedIndex(1);
					}else if(tmp.equals("C")) {
						langbox.setSelectedIndex(2);
					}else {
						langbox.setSelectedIndex(3);
					}
					langbox.setEnabled(false);
					
					if (shar == 1) {
						sharebtn.setSelected(true);
					}else {
						sharebtn.setSelected(false);
					}
					
					break;
				}
			}
			savebtn.setVisible(true);
			editbtn.setVisible(true);
			sharebtn.setVisible(true);
			
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "이 게시물은 볼수 없습니다.");
		}
		
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
