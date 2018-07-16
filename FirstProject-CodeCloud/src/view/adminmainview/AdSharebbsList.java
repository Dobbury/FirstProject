package view.adminmainview;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dto.BBSDto;
import dto.QAbbsDto;
import singleton.Singleton;

public class AdSharebbsList extends JPanel implements ActionListener {

	private JTable jTable;
	private JScrollPane jScrPane;

	private JComboBox<String> choiceList; // 검색 목록 초이스
	private JTextField selectField; // 검색 필드
	private JButton selectBtn;// 검색 버튼

	DefaultTableModel model; // 테이블의 넓이 설정

	String columnNames[] = { "번호", "제목","언어", "추천", "포크", "닉네임", "작성일" };

	Object rowData[][];
	AdminSharebbs adminSharebbs;

	public AdSharebbsList(AdminSharebbs AdShredbbs) {

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
//		jTable.addMouseListener(this);

		// 컬럼의 넓이 설정
		jTable.getColumnModel().getColumn(0).setMaxWidth(50); // 번호
		jTable.getColumnModel().getColumn(0).setCellRenderer(celAlignCenter); 
		jTable.getColumnModel().getColumn(1).setMaxWidth(500); // 제목
		jTable.getColumnModel().getColumn(1).setCellRenderer(celAlignCenter);
		jTable.getColumnModel().getColumn(2).setMaxWidth(50); // 언어
		jTable.getColumnModel().getColumn(2).setCellRenderer(celAlignCenter);
		jTable.getColumnModel().getColumn(3).setMaxWidth(50); // 추천
		jTable.getColumnModel().getColumn(4).setMaxWidth(50); // 포크
		jTable.getColumnModel().getColumn(5).setMaxWidth(100); // 닉네임
		jTable.getColumnModel().getColumn(6).setMaxWidth(200);// 작성일


		jScrPane = new JScrollPane(jTable);

		jScrPane.setBounds(100, 150, 600, 300);
		add(jScrPane);

		// 검색할 부분 콤보박스로 나열해줌
		// Choice(AWT) -> JComboBox(swing)
		String[] selects = new String[] { "제목", "내용", "닉네임" };
		choiceList = new JComboBox<>(selects);
		choiceList.setBounds(150, 600, 80, 20);
		add(choiceList);

		setLayout(null);
		setBackground(Color.LIGHT_GRAY);
		setBounds(50, 50, 300, 300);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == selectBtn) { // 검색버튼으로 디테일뷰 테스트
			
//			adminSharebbs.changPanel(2);
		}
	}
}
