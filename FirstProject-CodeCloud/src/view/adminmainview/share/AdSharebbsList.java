package view.adminmainview.share;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.List;

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

import dto.BBSDto;
import dto.QAbbsDto;
import dto.ShareDto;
import singleton.Singleton;

public class AdSharebbsList extends JPanel implements ActionListener,MouseListener {

	private JTable jTable;
	private JScrollPane jScrPane;

	private JComboBox<String> choiceList; // 검색 목록 초이스
	private JTextField selectField; // 검색 필드
	private JButton selectBtn;// 검색 버튼

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
			rowData[i][1] = list.get(i).getTitle();	//제목
			rowData[i][2] = list.get(i).getLang();	//언오
			rowData[i][3] = list.get(i).getLiked();	//추천
			rowData[i][4] = list.get(i).getFork(); //포크
			rowData[i][5] = list.get(i).getNick(); //닉네임

		}
		
		
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		
		// 검색
		selectField = new JTextField();
		selectField.setBounds(250, 600, 150, 20);
		add(selectField);

		selectBtn = new JButton("검색");
		selectBtn.addActionListener(this);
		selectBtn.setBounds(420, 600, 100, 20);
		add(selectBtn);

		model = new DefaultTableModel(columnNames, 0);
		model.setDataVector(rowData, columnNames);

		jTable = new JTable(model);
		jTable.addMouseListener(this);

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


		jScrPane = new JScrollPane(jTable);

		jScrPane.setBounds(80, 150, 800, 400);
		add(jScrPane);

		// 검색할 부분 콤보박스로 나열해줌
		// Choice(AWT) -> JComboBox(swing)
		String[] selects = new String[] { "제목", "내용", "닉네임" };
		choiceList = new JComboBox<>(selects);
		choiceList.setBounds(150, 600, 80, 20);
		add(choiceList);

		setLayout(null);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == selectBtn) { // 검색버튼으로 디테일뷰 테스트
			Singleton s = Singleton.getInstance();
			
			list = s.sharDao.getTitleFindList(selectField.getText(), choiceList.getSelectedItem().toString());
			
			if (list.size() == 0 || selectField.getText().equals("")) {
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
			rowData[i][1] = list.get(i).getTitle();	//제목
			rowData[i][2] = list.get(i).getLang();	//언오
			rowData[i][3] = list.get(i).getLiked();	//추천
			rowData[i][4] = list.get(i).getFork(); //포크
			rowData[i][5] = list.get(i).getNick(); //닉네임

		}
		////////////////////////////// table 형태 유지
		model.setDataVector(rowData, columnNames);
		
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
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
