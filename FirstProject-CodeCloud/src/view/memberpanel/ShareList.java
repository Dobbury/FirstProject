package view.memberpanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dto.QAbbsDto;
import dto.ShareDto;
import singleton.Singleton;

public class ShareList extends JPanel implements Action, MouseListener {

	private JButton testChange;
	private JTable ShareListTable;
	private JScrollPane jScrPane;
 
	DefaultTableModel model; // 테이블의 넓이 설정 ? 


	String columnNames[] = { "no.", "언어", "제목", "추천" , "다운로드"  };
	Object rowData[][];
	Sharebbs ShareMain; //colorLayout

	List<ShareDto> list;
	

	
	public ShareList(Sharebbs sharebbs) {
		
		ShareMain=sharebbs;
		Singleton s = Singleton.getInstance();
		// list = s.qaDao.getbbsList(); 값져올때 다시 만들기
		
		
		//게시판 만들기
		
 
//	 
//		
//		rowData = new Object[list.size()][5];
//		
//		for (int i = 0; i < list.size(); i++) {  
//		
//		rowData[i][0] =  list.get(i).getSeq();
//		rowData[i][1] =  list.get(i).getLang();
//		rowData[i][2] =  list.get(i).getTitle();
//		rowData[i][3] =  list.get(i).getLiked();
//		rowData[i][4] =  list.get(i).getFork();
//		}
//			
		
		
		rowData = new Object[1][5];
		rowData[0][0] =  1;
		rowData[0][1] =  2;
		rowData[0][2] =  3;
		rowData[0][3] =  4;
		rowData[0][4] = 5;
		
		testChange = new JButton("체인지 뷰 확인 버튼");
		testChange.addActionListener(this);
		testChange.setBounds(420, 600, 100, 20);
		add(testChange);


		
		
		
		 
		//게시판 붙이기 !!
		model = new DefaultTableModel(columnNames, 0);
		model.setDataVector(rowData, columnNames);
		ShareListTable = new JTable(model); 
		ShareListTable.addMouseListener(this);
		
		// 컬럼의 넓이 설정 //가 되고있지않음
		ShareListTable.getColumnModel().getColumn(0).setMaxWidth(50);
		ShareListTable.getColumnModel().getColumn(1).setMaxWidth(300);
		ShareListTable.getColumnModel().getColumn(2).setMaxWidth(500);
		ShareListTable.getColumnModel().getColumn(3).setMaxWidth(50);
		ShareListTable.getColumnModel().getColumn(3).setMaxWidth(50);
		
		jScrPane = new JScrollPane(ShareListTable); 
		jScrPane.setBounds(50, 50, 600, 300);
		add(jScrPane);
		
		
		
		
		
		
		
		setLayout(null);
		setBackground(Color.ORANGE);
		setBounds(50, 50, 300, 300);
		setVisible(true);
		
		
	}
	

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		
		if (obj == testChange) {
			ShareMain.changePanel(2, new ShareDto());
			
			
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

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
	 
		ShareMain.changePanel(1, new ShareDto());
		
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
