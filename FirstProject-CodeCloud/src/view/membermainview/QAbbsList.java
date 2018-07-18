package view.membermainview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Calendar;
import java.util.List;

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
import dto.QAbbsDto;
import singleton.Singleton;

public class QAbbsList extends JPanel implements ActionListener, WindowListener, MouseListener {
	private JTable jTable;
	private JScrollPane jScrPane;
	private JButton writeBtn;

	private JComboBox<String> choiceList; // 검색 목록 초이스
	private JTextField selectField; // 검색 필드
	private JButton selectBtn;// 검색 버튼

	DefaultTableModel model; // 테이블의 넓이 설정

	String columnNames[] = { "번호", "제목", "닉네임", "작성일" };

	Object rowData[][];
	QAbbsMain QAmain;

	List<QAbbsDto> list;
	final int INSERT = 0;
	final int UPDATE = 1;

	final int DETAIL = -2;
	final int LIST = -1;

	public QAbbsList(QAbbsMain QA) {
		setOpaque(false);
		
		QAmain = QA;

		Singleton s = Singleton.getInstance();
		list = s.qaDao.getbbsList();

		rowData = new Object[list.size()][4];// 테이블의 2차원배열이 생성

		for (int i = 0; i < list.size(); i++) {
			QAbbsDto dto = list.get(i);

			rowData[i][0] = dto.getSeq();// 번호


			if (dto.getDel() == 1)
				rowData[i][1] = "  *************이 글은 삭제되었습니다*************";
			else {

				// 댓글 작업 부분
				rowData[i][1] = "";
				for (int j = 0; j < list.get(i).getDept(); j++) {
					rowData[i][1] += "    ";
				}
	
				if (rowData[i][1].equals(""))
					rowData[i][1] ="  " + list.get(i).getTitle();
				else
					rowData[i][1] += "[답변] " + list.get(i).getTitle();
			}

			rowData[i][2] = dto.getNick();

			Calendar cal = Calendar.getInstance();

			// 테이블 날짜 다듬어서 뿌려주기
			// 현재날짜의 글들은 시간과 분으로 출력 이전날짜들은 날짜들만 출력
			// 현재 년도, 월, 일
			int year = cal.get(cal.YEAR);
			int month = cal.get(cal.MONTH) + 1;
			int date = cal.get(cal.DATE);
			// 현재날짜
			String nowDate = year + "-0" + month + "-" + date;
			if (list.get(i).getWdate().contains(nowDate)) {
				// 시간하고 분만 얻어옴
				String nowTime = list.get(i).getWdate().substring(11, 16);
				rowData[i][3] = nowTime;
			} else {
				String beforeDate = list.get(i).getWdate().substring(0, 10);
				rowData[i][3] = beforeDate;
			}
		}

		// 글쓰기 버튼
		writeBtn = new JButton("글쓰기");
		writeBtn.addActionListener(this);

		writeBtn.setBounds(700, 570, 100, 40);

		add(writeBtn);

		// 검색
		selectField = new JTextField();
		selectField.setBounds(140, 570, 150, 40);
		add(selectField);

		selectBtn = new JButton("검색");

		selectBtn.addActionListener(this);

		selectBtn.setBounds(300, 570, 100, 40);
		add(selectBtn);

		model = new DefaultTableModel(columnNames, 0);	
		model.setDataVector(rowData, columnNames);

		jTable = new JTable(model);
		jTable.addMouseListener(this);


		// 테이블안에 컬럼을 위치설정
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		celAlignCenter.setOpaque(false);
		// 컬럼의 넓이 설정
		jTable.getColumnModel().getColumn(0).setMaxWidth(50);
		jTable.getColumnModel().getColumn(0).setCellRenderer(celAlignCenter);
		jTable.getColumnModel().getColumn(1).setMaxWidth(500);
		jTable.getColumnModel().getColumn(2).setMaxWidth(80);
		jTable.getColumnModel().getColumn(2).setCellRenderer(celAlignCenter);
		jTable.getColumnModel().getColumn(3).setMaxWidth(120);
		jTable.getColumnModel().getColumn(3).setCellRenderer(celAlignCenter);
		
		jTable.setOpaque(false);
		jTable.setForeground(Color.WHITE);
		jTable.setTableHeader(null);
		jTable.setShowGrid(false);
		
		//스크롤바 0으로 줄여서 안보이게하는 코드
		jScrPane = new JScrollPane(jTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
		jScrPane.getVerticalScrollBar().setPreferredSize (new Dimension(0,0));
		jScrPane.setOpaque(false);
		((DefaultTableCellRenderer) jTable.getDefaultRenderer(Object.class)).setOpaque(false);
		jScrPane.getViewport().setOpaque(false);
		
		//테두리 없애기
		jScrPane.setBorder(BorderFactory.createCompoundBorder(null,
	            BorderFactory.createEmptyBorder(0, 0, 0, 0)));

		
		// 코드 배경
		ImageIcon bbs_back_Img = new ImageIcon("img/QAbbs/QAbbs_background.png");

		JLabel bbs_backgorund = new JLabel();
		bbs_backgorund.setIcon(bbs_back_Img);
		bbs_backgorund.setBounds(50, 150, 750, 400);
		///////////////////////////////////////////////////
		jScrPane.setBounds(50, 196, 750, 354);
		

		add(jScrPane);
		add(bbs_backgorund);
		
		// 검색할 부분 콤보박스로 나열해줌
		// Choice(AWT) -> JComboBox(swing)
		String[] selects = new String[] { "제목", "내용", "닉네임" };
		choiceList = new JComboBox<>(selects);
		choiceList.setBounds(50, 570, 80, 40);
		add(choiceList);

		setLayout(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		// 글쓰기
		if (obj == writeBtn) {
			QAmain.changePanel(3, new QAbbsDto(), INSERT);
		}
		// 검색 버튼
		else if (obj == selectBtn) {
			Singleton s = Singleton.getInstance();

			String selectedItem = (String) choiceList.getSelectedItem();

			list = s.qaDao.getTitleFindList(selectField.getText(), selectedItem);

			JOptionPane.showMessageDialog(null, selectedItem);

			if (list.size() == 0 || selectField.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "검색하신 단어로는 데이터를 찾지못했습니다");

				list = s.qaDao.getbbsList(); // 만약 데이터가 없으면 초기화함
			}
			// 테이블 초기화
			setList(list);
		}
	}

	// 검색 후에 목록을 셋팅용
	public void setList(List<QAbbsDto> list) {
		rowData = new Object[list.size()][4];

		int n = 1;
		for (int i = 0; i < list.size(); i++) {
			QAbbsDto dto = list.get(i);
			rowData[i][0] = dto.getSeq();
			if (dto.getDel() == 1) {
				rowData[i][1] = "*************이 글은 삭제되었습니다*************";
			}
			else {
				// 댓글 작업 부분
				rowData[i][1] = "";
				for (int j = 0; j < list.get(i).getDept(); j++) {
					rowData[i][1] += "    ";
				}
	
				if (rowData[i][1].equals(""))
					rowData[i][1] = list.get(i).getTitle();
				else
					rowData[i][1] += "┗ [답변] " + list.get(i).getTitle();
			}			rowData[i][2] = dto.getNick();


			Calendar cal = Calendar.getInstance();
			// 테이블 날짜 다듬어서 뿌려주기
			// 현재날짜의 글들은 시간과 분으로 출력 이전날짜들은 날짜들만 출력
			// 현재 년도, 월, 일
			int year = cal.get(cal.YEAR);
			int month = cal.get(cal.MONTH) + 1;
			int date = cal.get(cal.DATE);
			// 현재날짜
			String nowDate = year + "-0" + month + "-" + date;
			if (list.get(i).getWdate().contains(nowDate)) {
				// 시간하고 분만 얻어옴
				String nowTime = list.get(i).getWdate().substring(11, 16);
				rowData[i][3] = nowTime;
			} else {
				String beforeDate = list.get(i).getWdate().substring(0, 10);
				rowData[i][3] = beforeDate;
			}
		}
		////////////////////////////// table 형태 유지
		model.setDataVector(rowData, columnNames);

		// 컬럼의 넓이 설정
		jTable.getColumnModel().getColumn(0).setMaxWidth(50);
		jTable.getColumnModel().getColumn(1).setMaxWidth(600);
		jTable.getColumnModel().getColumn(2).setMaxWidth(60);
		jTable.getColumnModel().getColumn(3).setMaxWidth(150);


		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);

		jTable.getColumn("번호").setCellRenderer(celAlignCenter);
		jTable.getColumn("작성일").setCellRenderer(celAlignCenter);

		jTable.setModel(model);
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {

		int rowNum = jTable.getSelectedRow();
		if (list.get(rowNum).getDel() == 1) {
			JOptionPane.showMessageDialog(null, "이 글은 볼 수 없습니다");
			return;
		}
		Singleton s = Singleton.getInstance();
		QAbbsDto dto = s.qaDao.search(list.get(rowNum).getSeq(), list.get(rowNum).getRef(), list.get(rowNum).getStep(),
				list.get(rowNum).getDept());

		QAmain.changePanel(2, dto, INSERT); // 해당 글 보는 곳
		QAmain.changePanel(2, dto, DETAIL); // 해당 글 보는 곳
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
