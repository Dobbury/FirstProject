package view.adminmainview.share;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dto.BBSDto;
import dto.QAbbsDto;
import dto.ShareDto;
import singleton.Singleton;

public class AdSharebbsList extends JPanel implements ActionListener,MouseListener {

	private JTable jTable;
	private JScrollPane jScrPane;

	private JComboBox<String> choiceList; // 검색 목록 초이스
	private JTextField selectField; // 검색 필드
	
	ImageIcon searchIc1;
	ImageIcon searchIc2;
	ImageIcon searchIc3;
	private JButton searchBtn;// 검색 버튼

	DefaultTableModel model; // 테이블의 넓이 설정

	String columnNames[] = { "번호", "제목","언어", "추천", "포크", "닉네임"};

	Object rowData[][];
	AdminSharebbs adSharebbs;
	
	List<ShareDto> list;
	
	public AdSharebbsList(AdminSharebbs adSharebbs) {
		setOpaque(false);
		this.adSharebbs = adSharebbs;
		Singleton s= Singleton.getInstance();
		
		list = s.sharDao.getbbsList();

		rowData = new Object[list.size()][6];// 테이블의 2차원배열이 생성

		for (int i = 0; i < list.size(); i++) {
			ShareDto dto = list.get(i);

			rowData[i][0] = dto.getSeq();// 번호
			rowData[i][1] = " "+list.get(i).getTitle();	//제목
			rowData[i][2] = list.get(i).getLang();	//언오
			rowData[i][3] = list.get(i).getLiked();	//추천
			rowData[i][4] = list.get(i).getFork(); //포크
			rowData[i][5] = list.get(i).getNick(); //닉네임

		}
		
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		celAlignCenter.setOpaque(false);
		
		// 검색
		selectField = new JTextField();
		selectField.setBounds(140, 570, 150, 40);
		add(selectField);

		searchIc1 = new ImageIcon("img/adminMain/share/share_search_on.png");
		searchIc2 = new ImageIcon("img/adminMain/share/share_search_off.png");
		searchIc3 = new ImageIcon("img/adminMain/share/share_search_ing.png");
		searchBtn = new JButton(searchIc1);
		searchBtn.setRolloverIcon(searchIc2);
		searchBtn.setPressedIcon(searchIc3);
		searchBtn.setBorderPainted(false);
		searchBtn.setContentAreaFilled(false);
		searchBtn.setFocusPainted(false);
		
		searchBtn.addActionListener(this);
		searchBtn.setBounds(300, 570, 101, 41);
		add(searchBtn);

		model = new DefaultTableModel(columnNames, 0);
		model.setDataVector(rowData, columnNames);

		jTable = new JTable(model);
		jTable.addMouseListener(this);

		// 컬럼의 넓이 설정
		jTable.getColumnModel().getColumn(0).setMaxWidth(50); // 번호
		jTable.getColumnModel().getColumn(0).setCellRenderer(celAlignCenter); 
		jTable.getColumnModel().getColumn(1).setMaxWidth(480); // 제목
		jTable.getColumnModel().getColumn(2).setMaxWidth(50); // 언어
		jTable.getColumnModel().getColumn(2).setCellRenderer(celAlignCenter);
		jTable.getColumnModel().getColumn(3).setMaxWidth(50); // 추천
		jTable.getColumnModel().getColumn(3).setCellRenderer(celAlignCenter);
		jTable.getColumnModel().getColumn(4).setMaxWidth(70); // 포크
		jTable.getColumnModel().getColumn(4).setCellRenderer(celAlignCenter);
		jTable.getColumnModel().getColumn(5).setMaxWidth(100); // 닉네임
		jTable.getColumnModel().getColumn(5).setCellRenderer(celAlignCenter);

		jTable.setOpaque(false);
		jTable.setForeground(Color.WHITE);
		jTable.setTableHeader(null);
		jTable.setShowGrid(false);
		jTable.setRowHeight(25);
		Font tableFont = new Font("맑은고딕", Font.PLAIN, 15);
		jTable.setFont(tableFont);	
		
		//스크롤바 0으로 줄여서 안보이게하는 코드
		jScrPane = new JScrollPane(jTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
		jScrPane.getVerticalScrollBar().setPreferredSize (new Dimension(0,0));

		jScrPane.setOpaque(false);
		jScrPane.getViewport().setOpaque(false);
		((DefaultTableCellRenderer) jTable.getDefaultRenderer(Object.class)).setOpaque(false);

		// 테두리 없애기
		jScrPane.setBorder(BorderFactory.createCompoundBorder(null, BorderFactory.createEmptyBorder(0, 0, 0, 0)));

		jScrPane.setBounds(50, 205, 800, 340);
		add(jScrPane);

		// 코드 배경
		ImageIcon bbs_back_Img = new ImageIcon("img/adminMain/share/adShare_list_background.png");

		JLabel bbs_backgorund = new JLabel();
		bbs_backgorund.setIcon(bbs_back_Img);
		bbs_backgorund.setBounds(50,150, 800, 400);
		add(bbs_backgorund);
		// 검색할 부분 콤보박스로 나열해줌
		// Choice(AWT) -> JComboBox(swing)
		String[] selects = new String[] { "전체보기", "제목", "내용", "닉네임" };
		choiceList = new JComboBox<>(selects);
		choiceList.setBounds(50, 570, 80, 40);
		
		choiceList.setOpaque(false);
		choiceList.setFocusable(false);
		choiceList.setForeground(Color.white);
		choiceList.setRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				JComponent result = (JComponent) super.getListCellRendererComponent(list, value, index, isSelected,
						cellHasFocus);
				result.setOpaque(false);
				return result;
			}
		});
		
		add(choiceList);

		setLayout(null);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchBtn) { // 검색버튼으로 디테일뷰 테스트
			Singleton s = Singleton.getInstance();

			String selectedItem = (String) choiceList.getSelectedItem();
			list = s.sharDao.getTitleFindList(selectField.getText(),selectedItem.toString());
			
			if (list.size() == 0 || selectField.getText().equals("")) {
				if(!selectedItem.equals("전체보기"))
					JOptionPane.showMessageDialog(null, "검색하신 단어로는 데이터를 찾지못했습니다");

				list = s.sharDao.getbbsList(); // 만약 데이터가 없으면 초기화함
			}
			setList(list);
		}
	}
	
	public void setList(List<ShareDto> list) {
		rowData = new Object[list.size()][6];

		int n = 1;
		for (int i = 0; i < list.size(); i++) {
			ShareDto dto = list.get(i);
			
			rowData[i][0] = dto.getSeq();// 번호
			rowData[i][1] = " "+list.get(i).getTitle();	//제목
			rowData[i][2] = list.get(i).getLang();	//언오
			rowData[i][3] = list.get(i).getLiked();	//추천
			rowData[i][4] = list.get(i).getFork(); //포크
			rowData[i][5] = list.get(i).getNick(); //닉네임

		}
		////////////////////////////// table 형태 유지
		model.setDataVector(rowData, columnNames);
		
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		celAlignCenter.setOpaque(false);
		// 컬럼의 넓이 설정
		jTable.getColumnModel().getColumn(0).setMaxWidth(50); // 번호
		jTable.getColumnModel().getColumn(0).setCellRenderer(celAlignCenter); 
		jTable.getColumnModel().getColumn(1).setMaxWidth(500); // 제목
		jTable.getColumnModel().getColumn(2).setMaxWidth(50); // 언어
		jTable.getColumnModel().getColumn(2).setCellRenderer(celAlignCenter);
		jTable.getColumnModel().getColumn(3).setMaxWidth(50); // 추천
		jTable.getColumnModel().getColumn(3).setCellRenderer(celAlignCenter);
		jTable.getColumnModel().getColumn(4).setMaxWidth(50); // 포크
		jTable.getColumnModel().getColumn(4).setCellRenderer(celAlignCenter);
		jTable.getColumnModel().getColumn(5).setMaxWidth(100); // 닉네임
		jTable.getColumnModel().getColumn(5).setCellRenderer(celAlignCenter);
		//////////////////////////////

		jTable.setModel(model);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
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

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int rowNum = jTable.getSelectedRow();
		
		Singleton s = Singleton.getInstance();
		ShareDto dto = s.sharDao.search(list.get(rowNum).getSeq());

		adSharebbs.changPanel(2, dto); // 해당 글 보는 곳
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
