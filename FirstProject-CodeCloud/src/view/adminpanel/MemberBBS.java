package view.adminpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import Encrypt.PasswordClass;
import dao.MemberDao;
import dto.MemberDto;
import singleton.Singleton;

public class MemberBBS extends JPanel implements ActionListener, MouseListener {
	
	//멤버 테이블
	LinkedList<MemberDto> list;
	private DefaultTableModel model;
	private JTable jTable;
	private JScrollPane jScrPane;
	
	private String[] head = {"아이디", "닉네임", "비밀번호"};
	private Object[][] rowData;
	
	//수정, 삭제
	
	JButton editbtn = new JButton("비밀번호 변경");
	JButton deletebtn = new JButton("회원삭제");
	
	String selectedid = null;
	
	
	public MemberBBS() {
		this.setLayout(null);
		Singleton s = Singleton.getInstance();
		
		list = MemberDao.memlist();
		
		if(list.size()>0) {
			rowData = new Object[list.size()][3];
			
			for (int i = 0; i < list.size(); i++) {
				
				rowData[i][0] = list.get(i).getID();
				rowData[i][1] = list.get(i).getNick();
				rowData[i][2] = list.get(i).getPWD();
			}
		}
		
		model = new DefaultTableModel(rowData, head);
		
		jTable = new JTable(model) {
			@Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		jTable.addMouseListener(this);
		
		jScrPane = new JScrollPane(jTable);
		jScrPane.setBounds(0, 0, 600, 300);
		this.add(jScrPane);
		
		editbtn.setBounds(350, 400, 120, 50);
		deletebtn.setBounds(500, 400, 100, 50);
		editbtn.addActionListener(this);
		deletebtn.addActionListener(this);
		this.add(editbtn);
		this.add(deletebtn);
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		
		if (obj == editbtn) {
			if (jTable.getSelectionModel().isSelectionEmpty()) {
				JOptionPane.showMessageDialog(null, "회원이 선택이 되어 있어야 합니다.");
			}else {
				int choice = JOptionPane.YES_NO_OPTION;
				choice = JOptionPane.showConfirmDialog (null, "정말 수정하시겠습니까?","WARNING", choice);
				
				if (choice == 0) {
					String newpw = JOptionPane.showInputDialog(null, "새로운 비번을 입력해주세요");
					String encrypt = PasswordClass.Encryption(newpw);
					int result = MemberDao.changepw(encrypt, selectedid);
					if (result == 1) {
						JOptionPane.showMessageDialog(null, "비밀번호가 수정되었습니다.");
					}
					list = MemberDao.memlist();
					
					rowData = new Object[list.size()][3];
					
					for (int i = 0; i < list.size(); i++) {
						
						rowData[i][0] = list.get(i).getID();
						rowData[i][1] = list.get(i).getNick();
						rowData[i][2] = list.get(i).getPWD();
					}
					
					model.setDataVector(rowData, head);
					jTable.setModel(model);
					((AbstractTableModel) model).fireTableDataChanged();
				}
			}
		}
		if(obj == deletebtn) {
			if (jTable.getSelectionModel().isSelectionEmpty()) {
				JOptionPane.showMessageDialog(null, "회원이 선택이 되어 있어야 합니다.");
			}else {
				int choice = JOptionPane.YES_NO_OPTION;
				choice = JOptionPane.showConfirmDialog (null, "정말 삭제하시겠습니까?","WARNING", choice);
				
				if (choice == 0) {
					int result = MemberDao.memdelete(selectedid);
					if (result >0) {
						JOptionPane.showMessageDialog(null, "회원이 삭제되었습니다.");
					}
						list = MemberDao.memlist();
						
						rowData = new Object[list.size()][3];
						
						for (int i = 0; i < list.size(); i++) {
							
							rowData[i][0] = list.get(i).getID();
							rowData[i][1] = list.get(i).getNick();
							rowData[i][2] = list.get(i).getPWD();
						}
						
						model.setDataVector(rowData, head);
						jTable.setModel(model);
						((AbstractTableModel) model).fireTableDataChanged();
					}
			}	
			
		
		}
	}




	@Override
	public void mouseClicked(MouseEvent e) {
		JTable source = (JTable) e.getSource();
		int rows = source.rowAtPoint(e.getPoint());
		
		
		selectedid = (String) source.getModel().getValueAt(rows, 1);
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
