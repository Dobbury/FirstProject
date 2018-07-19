package view.membermainview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.List;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
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

		// 테이블안에 컬럼을 위치설정
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		celAlignCenter.setOpaque(false);

		// 컬럼의 넓이 설정 //가 되고있지않음
		ShareListTable.getColumnModel().getColumn(0).setMaxWidth(50);
		ShareListTable.getColumnModel().getColumn(0).setCellRenderer(celAlignCenter);
		ShareListTable.getColumnModel().getColumn(1).setMaxWidth(60);
		ShareListTable.getColumnModel().getColumn(1).setCellRenderer(celAlignCenter);
		ShareListTable.getColumnModel().getColumn(2).setMaxWidth(405);
		ShareListTable.getColumnModel().getColumn(3).setMaxWidth(95);
		ShareListTable.getColumnModel().getColumn(3).setCellRenderer(celAlignCenter);
		ShareListTable.getColumnModel().getColumn(4).setMaxWidth(70);
		ShareListTable.getColumnModel().getColumn(4).setCellRenderer(celAlignCenter);
		ShareListTable.getColumnModel().getColumn(5).setMaxWidth(70);
		ShareListTable.getColumnModel().getColumn(5).setCellRenderer(celAlignCenter);
		ShareListTable.removeColumn(ShareListTable.getColumnModel().getColumn(6));

		ShareListTable.setOpaque(false);
		ShareListTable.setForeground(Color.WHITE);
		ShareListTable.setTableHeader(null);
		ShareListTable.setShowGrid(false);
		ShareListTable.setRowHeight(25);
		Font tableFont = new Font("맑은고딕", Font.PLAIN, 15);
		ShareListTable.setFont(tableFont);
		
		//스크롤바 0으로 줄여서 안보이게하는 코드
		jScrPane = new JScrollPane(ShareListTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
		jScrPane.getVerticalScrollBar().setPreferredSize (new Dimension(0,0));
		jScrPane.setOpaque(false);
		((DefaultTableCellRenderer) ShareListTable.getDefaultRenderer(Object.class)).setOpaque(false);
		jScrPane.getViewport().setOpaque(false);

		// 테두리 없애기
		jScrPane.setBorder(BorderFactory.createCompoundBorder(null,
							BorderFactory.createEmptyBorder(0, 0, 0, 0)));

		// 코드 배경
		ImageIcon bbs_back_Img = new ImageIcon("img/sharebbs/Sharebbs_list_background.png");

		JLabel list_backgorund = new JLabel();
		list_backgorund.setIcon(bbs_back_Img);
		list_backgorund.setBounds(50, 150, 750, 400);
		///////////////////////////////////////////////////
		
		jScrPane.setBounds(55, 205, 740, 340);
		add(jScrPane);
		add(list_backgorund);
		
		// 검색할 부분 콤보박스로 나열해줌
		// Choice(AWT) -> JComboBox(swing)
		String[] selects = new String[] { "전체보기", "제목", "내용", "닉네임", "언어" };
		choiceList = new JComboBox<>(selects);
		choiceList.setBounds(50, 570, 80, 40);
		add(choiceList);

		// 검색
		selectField = new JTextField();
		selectField.setBounds(140, 570, 150, 40);
		add(selectField);

		selectBtn = new JButton("검색");
		selectBtn.addActionListener(this);
		selectBtn.setBounds(300, 570, 100, 40);
		add(selectBtn);

		setLayout(null);
		setOpaque(false);
		setBounds(50, 50, 300, 300);
		setVisible(true);				
	}
	
	public void setList(List<ShareDto> list) {
		rowData = new Object[list.size()][7];

		int n = 1;

		for (int i = 0; i < list.size(); i++) {
			rowData[i][0] = list.get(i).getSeq();
			rowData[i][1] = list.get(i).getLang();
			rowData[i][2] = list.get(i).getTitle();
			rowData[i][3] = list.get(i).getNick();
			rowData[i][4] = list.get(i).getLiked();
			rowData[i][5] = list.get(i).getFork();
			rowData[i][6] = list.get(i).getIndseq();
		}
		
		model.setDataVector(rowData, columnNames);

		ShareListTable.getColumnModel().getColumn(0).setMaxWidth(50);
		ShareListTable.getColumnModel().getColumn(1).setMaxWidth(60);
		ShareListTable.getColumnModel().getColumn(2).setMaxWidth(600);
		ShareListTable.getColumnModel().getColumn(3).setMaxWidth(60);
		ShareListTable.getColumnModel().getColumn(4).setMaxWidth(50);
		ShareListTable.getColumnModel().getColumn(5).setMaxWidth(50);
		ShareListTable.removeColumn(ShareListTable.getColumnModel().getColumn(6));

		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		ShareListTable.getColumn("번호").setCellRenderer(celAlignCenter);
		ShareListTable.getColumn("작성일").setCellRenderer(celAlignCenter);

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

			if(selectedItem.equals("전체보기")) {
				list = s.sharDao.getbbsList();
			}
			else {
				list = s.sharDao.getTitleFindList(selectField.getText(), selectedItem);					
			}

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
		Singleton s = Singleton.getInstance();
	
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
