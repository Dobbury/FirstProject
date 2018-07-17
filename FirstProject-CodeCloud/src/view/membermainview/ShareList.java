package view.membermainview;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.List;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
	

	
	private JButton writeBtn;

	private JComboBox<String> choiceList; // 검색 목록 초이스
	private JTextField selectField; // 검색 필드
	private JButton selectBtn;// 검색 버튼
	
	
	
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
		
		
		
		
		//맨 아랫 콤보박스 텍스트필드 검색 버튼 글쓰기 버튼
		
		

		// 검색할 부분 콤보박스로 나열해줌
		// Choice(AWT) -> JComboBox(swing)
		String[] selects = new String[] { "제목", "내용", "닉네임","언어"};
		choiceList = new JComboBox<>(selects);
		choiceList.setBounds(150, 600, 80, 20);
		add(choiceList);

		
		// 글쓰기 버튼
				writeBtn = new JButton("글쓰기");
				writeBtn.addActionListener(this);
				writeBtn.setBounds(600, 600, 100, 40);
				add(writeBtn);

				
				// 검색
				selectField = new JTextField();
				selectField.setBounds(250, 600, 150, 20);
				add(selectField);

				selectBtn = new JButton("검색");
				selectBtn.addActionListener(this);
				selectBtn.setBounds(420, 600, 100, 20);
				add(selectBtn);

		
		
		
				
				
				
				
		
		setLayout(null);
		setBackground(Color.ORANGE);
		setBounds(50, 50, 300, 300);
		setVisible(true);
		
		
	}
	 
	// seq lang title nick liked fork
	public void setList(List<ShareDto> list) {
		rowData = new Object[list.size()][7];

		int n = 1;
 
		
			for (int i = 0; i < list.size(); i++) {
				rowData[i][0] =  list.get(i).getSeq();
				rowData[i][1] =  list.get(i).getLang();
				rowData[i][2] =  list.get(i).getTitle();
				rowData[i][3] =  list.get(i).getNick();
				rowData[i][4] =  list.get(i).getLiked();
				rowData[i][5] =  list.get(i).getFork();
				rowData[i][6] =  list.get(i).getIndseq();
			
/*			
//			ShareDto dto = list.get(i);
//			
//			
//			rowData[i][0] = dto.getSeq();
//			rowData[i][1] = dto.getLang();
//			rowData[i][2] = dto.getTitle();
//			
//			rowData[i][3] = dto.getNick();
//			rowData[i][4] = dto.getLiked();
//			rowData[i][5] = dto.getFork();
//			rowData[i][6] =  dto.getContent();
//}
//			*/
//		
//		Dto(rs.getInt(i++), //seq
//				0, //indseq
//				rs.getString(i++), // nick 
//				rs.getString(i++), // String title
//				rs.getString(i++),// String content
//				0, //int liked, 
//				0, //int fork, 
//				rs.getString(i++) //String lang 
//				);
//					
//		 
		
		
		
//		for (int i = 0; i < list.size(); i++) {
//			rowData[i][0] =  list.get(i).getSeq();
//			rowData[i][1] =  list.get(i).getLang();
//			rowData[i][2] =  list.get(i).getTitle();
//			rowData[i][3] =  list.get(i).getNick();
//			rowData[i][4] =  list.get(i).getLiked();
//			rowData[i][5] =  list.get(i).getFork();
//			rowData[i][6] =  list.get(i).getIndseq();
//			
		
		
		
		
		
		
		
		model.setDataVector(rowData, columnNames);
		ShareListTable.getColumnModel().getColumn(0).setMaxWidth(50);
		ShareListTable.getColumnModel().getColumn(1).setMaxWidth(300);
		ShareListTable.getColumnModel().getColumn(2).setMaxWidth(500);
		ShareListTable.getColumnModel().getColumn(3).setMaxWidth(50);
		ShareListTable.getColumnModel().getColumn(4).setMaxWidth(50);
		ShareListTable.getColumnModel().getColumn(5).setMaxWidth(50);
		ShareListTable.removeColumn(ShareListTable.getColumnModel().getColumn(6));
		
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		ShareListTable.getColumn("번호").setCellRenderer(celAlignCenter);
		ShareListTable.getColumn("작성일").setCellRenderer(celAlignCenter);
		
		
			}	
		
		//////////////////////////////

		ShareListTable.setModel(model);
		
		
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		// 글쓰기
		if (obj == writeBtn) {
			ShareMain.changePanel(2, new ShareDto());
			
		}
		// 검색 버튼
		else if (obj == selectBtn) {
			Singleton s = Singleton.getInstance();

				
			 String selectedItem = (String) choiceList.getSelectedItem();

			list = s.sharDao.getTitleFindList(selectField.getText(), selectedItem);
			JOptionPane.showMessageDialog(null, selectedItem);

 
			if (list.size() == 0 || selectField.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "검색하신 단어로는 데이터를 찾지못했습니다");

				list = ShareDao.getbbsList(); // 만약 데이터가 없으면 초기화함
			}
			// 테이블 초기화
			setList(list);
		}
	
		
		
		
		
		
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
