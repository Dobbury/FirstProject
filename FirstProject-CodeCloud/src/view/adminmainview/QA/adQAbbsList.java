package view.adminmainview.QA;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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

public class adQAbbsList extends JPanel implements MouseListener, WindowListener {

	final int DETAIL = 0;
	final int LIST = 1;
	final int COMMENT = 2;
	final int COMMENT_UPDATE = 3;

	private JTable jTable;
	private JScrollPane jScrPane;
	private JButton writeBtn;

	DefaultTableModel model; // 테이블의 넓이 설정

	Object rowData[][];

	String columnNames[] = { "번호", "제목", "작성자", "작성일" };
	adQAbbsMain adQAmian;

	List<QAbbsDto> list;

	public adQAbbsList(adQAbbsMain QA) {

		setOpaque(false);

		adQAmian = QA;

		Singleton s = Singleton.getInstance();

		list = s.qaDao.getbbsList();

		rowData = new Object[list.size()][4];// 테이블의 2차원배열이 생성

		for (int i = 0; i < list.size(); i++) {
			QAbbsDto dto = list.get(i);
			if(dto.getRef()!=0){
				rowData[i][0]="";
			}

			rowData[i][0] = dto.getSeq();// 번호

			if (dto.getDel() == 1) {
				rowData[i][1] = "*************이 글은 삭제되었습니다*************";

			}else {
				// 댓글 작업 부분
				rowData[i][1] = "";
				for (int j = 0; j < list.get(i).getDept(); j++) {
					rowData[i][1] += "    ";
				}

				if (rowData[i][1].equals(""))
					rowData[i][1] = " "+list.get(i).getTitle();
				else
					rowData[i][1] += "┗ [답변] " + list.get(i).getTitle();
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

		model = new DefaultTableModel(columnNames, 0);
		model.setDataVector(rowData, columnNames);

		jTable = new JTable(model);
		jTable.addMouseListener(this);

		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		celAlignCenter.setOpaque(false);
		// 컬럼의 넓이 설정
		jTable.getColumnModel().getColumn(0).setMaxWidth(50);
		jTable.getColumnModel().getColumn(0).setCellRenderer(celAlignCenter);
		jTable.getColumnModel().getColumn(1).setMaxWidth(540);
		jTable.getColumnModel().getColumn(2).setMaxWidth(95);
		jTable.getColumnModel().getColumn(2).setCellRenderer(celAlignCenter);
		jTable.getColumnModel().getColumn(3).setMaxWidth(115);
		jTable.getColumnModel().getColumn(3).setCellRenderer(celAlignCenter);

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
		ImageIcon bbs_back_Img = new ImageIcon("img/adminMain/QA/adQA_list_background.png");

		JLabel bbs_backgorund = new JLabel();
		bbs_backgorund.setIcon(bbs_back_Img);
		bbs_backgorund.setBounds(50,150, 800, 400);
		add(bbs_backgorund);
		
		setLayout(null);
		setVisible(true);
	}

	public void setList(List<QAbbsDto> list) {
		rowData = new Object[list.size()][4];

		int n = 1;
		for (int i = 0; i < list.size(); i++) {
			QAbbsDto dto = list.get(i);

			rowData[i][0] = dto.getSeq();
			if(dto.getRef()!=0){
				rowData[i][0]="";
			}

			if (dto.getDel() == 1)
				rowData[i][1] = "*************이 글은 삭제되었습니다*************";
			else {
				// 댓글 작업 부분
				rowData[i][1] = "";
				for (int j = 0; j < list.get(i).getDept(); j++) {
					rowData[i][1] += "    ";
				}

				if (rowData[i][1].equals(""))
					rowData[i][1] =" "+ list.get(i).getTitle();
				else
					rowData[i][1] += "┗ [답변] " + list.get(i).getTitle();
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
		////////////////////////////// table 형태 유지
		model.setDataVector(rowData, columnNames);

		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		celAlignCenter.setOpaque(false);
		// 컬럼의 넓이 설정
		jTable.getColumnModel().getColumn(0).setMaxWidth(50);
		jTable.getColumnModel().getColumn(0).setCellRenderer(celAlignCenter);
		jTable.getColumnModel().getColumn(1).setMaxWidth(540);
		jTable.getColumnModel().getColumn(2).setMaxWidth(95);
		jTable.getColumnModel().getColumn(2).setCellRenderer(celAlignCenter);
		jTable.getColumnModel().getColumn(3).setMaxWidth(115);
		jTable.getColumnModel().getColumn(3).setCellRenderer(celAlignCenter);

		jTable.setOpaque(false);
		jTable.setForeground(Color.WHITE);
		jTable.setTableHeader(null);
		jTable.setShowGrid(false);
		jTable.setRowHeight(25);
		Font tableFont = new Font("맑은고딕", Font.PLAIN, 15);
		jTable.setFont(tableFont);
		
		jTable.setModel(model);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
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
		// TODO Auto-generated method stub
		int rowNum = jTable.getSelectedRow();

		Singleton s = Singleton.getInstance();

		QAbbsDto dto = s.qaDao.search(list.get(rowNum).getSeq(), list.get(rowNum).getRef(), list.get(rowNum).getStep(),
				list.get(rowNum).getDept());
	
		adQAmian.changePanel(DETAIL, dto); // 해당 글 보는 곳
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
