package view.membermainview;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.ShareDao;
import dto.QAbbsDto;
import dto.ShareDto;
import singleton.Singleton;

public class ShareList extends JPanel implements Action, MouseListener {

	private JTable ShareListTable;
	private JScrollPane jScrPane;
 
	DefaultTableModel model; // 테이블의 넓이 설정 ? 


	String columnNames[] = { "no.", "언어", "제목", "닉네임", "추천" , "다운로드", "indseq"};
	Object rowData[][];
	Sharebbs ShareMain; //colorLayout

	List<ShareDto> list;
	

	
	public ShareList(Sharebbs sharebbs) {
		
		ShareMain=sharebbs;
		Singleton s = Singleton.getInstance();
		list = ShareDao.getbbsList();
		
		if(list.size()>0) {
			rowData = new Object[list.size()][7];
			
			for (int i = 0; i < list.size(); i++) {
				rowData[i][0] =  list.get(i).getSeq();
				rowData[i][1] =  list.get(i).getLang();
				rowData[i][2] =  list.get(i).getTitle();
				rowData[i][3] =  list.get(i).getNick();
				rowData[i][4] =  list.get(i).getLiked();
				rowData[i][5] =  list.get(i).getFork();
				rowData[i][6] =  list.get(i).getIndseq();
				
			}
		}
		
		model = new DefaultTableModel(rowData, columnNames);
		
		
		//게시판 붙이기 !!
		ShareListTable = new JTable(model) {
			@Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		
		ShareListTable.addMouseListener(this);
		
		// 컬럼의 넓이 설정 //가 되고있지않음
		ShareListTable.getColumnModel().getColumn(0).setMaxWidth(50);
		ShareListTable.getColumnModel().getColumn(1).setMaxWidth(300);
		ShareListTable.getColumnModel().getColumn(2).setMaxWidth(500);
		ShareListTable.getColumnModel().getColumn(3).setMaxWidth(50);
		ShareListTable.getColumnModel().getColumn(4).setMaxWidth(50);
		ShareListTable.getColumnModel().getColumn(5).setMaxWidth(50);
		ShareListTable.removeColumn(ShareListTable.getColumnModel().getColumn(6));
		
		jScrPane = new JScrollPane(ShareListTable); 
		jScrPane.setBounds(50, 50, 600, 300);
		add(jScrPane);
		
		
		setLayout(null);
		setBackground(Color.ORANGE);
		setBounds(50, 50, 300, 300);
		setVisible(true);
		
		
	}
	/*
	 * 닉네임
	 * 제목
	 * 콘텐츠
	 * 추천
	 * 포크
	 */

	
	
	@Override
	public void actionPerformed(ActionEvent e) {

	
		
	}

	public void mouseClicked(MouseEvent e) {
		JTable source = (JTable) e.getSource();
		int rows = source.rowAtPoint(e.getPoint());
		
		try {
			int seq = (int) source.getModel().getValueAt(rows, 0);
			
			for (int i = 0; i < list.size(); i++) {
				if (seq == list.get(i).getSeq()) {
					ShareDto dto = list.get(i);

					ShareMain.changePanel(2, dto);
					break;
				}
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null, "이 게시물은 볼수 없습니다.");
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
		
		
		int rowNum = ShareListTable.getSelectedRow();
		//if (list.get(rowNum).getSeq() == 1) {}
		
		Singleton s = Singleton.getInstance();
	 
		//ShareMain.changePanel(1, new ShareDto());
		
		}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

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

}
