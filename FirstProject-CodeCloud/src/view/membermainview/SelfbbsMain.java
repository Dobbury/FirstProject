package view.membermainview;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dto.BBSDto;
import dto.QAbbsDto;
import singleton.Singleton;
import view.MemberMainView;
import view.SignupView;

public class SelfbbsMain extends JPanel implements ActionListener,MouseListener {
	private CardLayout cards = new CardLayout();
	JPanel mainPanel;

	final int INSERT = 0;
	final int UPDATE = 1;
	final int DETAIL = 2;

	// 왼쪽 칸
	private JPanel left = new JPanel();
	private JButton plus = new JButton("+");
	private JButton minus = new JButton("-");

	private JTable jTable;
	private JScrollPane jScrPane;
	private DefaultTableModel model;
	private String[] head = { "title", "seq", "share" };
	private Object[][] rowData;
	
	private String[] searchlist = { "전체보기", "제목", "언어", "내용" };
	JComboBox searchbox = new JComboBox(searchlist);

	JTextField searchtext = new JTextField();
	JButton searchbtn = new JButton("검색");
	List<BBSDto> list;
	
	int currseq;

	
	public SelfbbsMain() {
		setLayout(null);
		setOpaque(false);
		setBounds(0, 0, 1100, 700);
		mainPanel = new JPanel(cards);

		
		mainPanel.add("SelfbbsDetail", new SelfbbsDetail(this,new BBSDto()));
		mainPanel.add("SelfbbsWrite", new SelfbbsWrite(this, new BBSDto(),INSERT));

		cards.show(mainPanel, "SelfbbsDetail");// 처음 띄워지는 판

		mainPanel.setBounds(200, 0, 900, 700);
		mainPanel.setOpaque(false);
		
		Singleton s = Singleton.getInstance();
		
		list = s.selfDao.getSelfBbsList();
		
		if (list.size() > 0) {//첫번째 게시물 시퀀스번호 저장
			currseq = list.get(0).getSeq();
		}
		
		// left
		left.setOpaque(false);
		//left.setBackground(new Color(0,0,0,30));
		left.setBounds(0, 0, 200, 800);
		left.setLayout(null);

		plus.setBounds(0, 0, 100, 50);
		plus.addActionListener(this);
		minus.setBounds(100, 0, 100, 50);
		minus.addActionListener(this);
		left.add(plus);
		left.add(minus);

		if (list.size() > 0) {
			rowData = new Object[list.size()][3];

			for (int i = 0; i < list.size(); i++) {

				rowData[i][0] = list.get(i).getTitle();
				rowData[i][1] = list.get(i).getSeq();
				rowData[i][2] = list.get(i).getShare();
			}
		}

		model = new DefaultTableModel(rowData, head);

		// model.setDataVector(rowData, head);

		jTable = new JTable(model) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		jTable.addMouseListener(this);
		jTable.setRowHeight(100);
		jTable.getColumnModel().getColumn(0).setMaxWidth(202);
		jTable.removeColumn(jTable.getColumnModel().getColumn(1));
		jTable.removeColumn(jTable.getColumnModel().getColumn(1));
		jTable.getSelectionModel().setSelectionInterval(0, 0);
		jScrPane = new JScrollPane(jTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 

		// 테이블 투명
		jTable.setOpaque(false);
		((DefaultTableCellRenderer) jTable.getDefaultRenderer(Object.class)).setOpaque(false);
		// 스크롤영역 투명
		jScrPane.setOpaque(false);
		jScrPane.getViewport().setOpaque(false);
		
		jScrPane.setBounds(-1, 50, 204, 650);
		left.add(jScrPane);

		searchbox.setBounds(0, 700, 200, 25);
		left.add(searchbox);
		searchtext.setBounds(0, 725, 140, 40);
		left.add(searchtext);
		searchbtn.setBounds(140, 725, 60, 40);
		searchbtn.addActionListener(this);
		left.add(searchbtn);

		add(mainPanel);
		add(left);
		setVisible(true);
	}

	public void changePanel(int state, BBSDto dto) {
		if (state == INSERT) {
			mainPanel.add("SelfbbsWrite", new SelfbbsWrite(this,dto,state));
			cards.show(mainPanel, "SelfbbsWrite");
		} else if (state == UPDATE) {
			mainPanel.add("SelfbbsWrite", new SelfbbsWrite(this, dto,state));
			cards.show(mainPanel, "SelfbbsWrite");
		} else if (state == DETAIL) {
			mainPanel.add("SelfbbsDetail", new SelfbbsDetail(this,dto));
			cards.show(mainPanel, "SelfbbsDetail");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		Singleton s = Singleton.getInstance();
		
		try {
			int rowNum = jTable.getSelectedRow();
			
			BBSDto dto = list.get(rowNum);

			changePanel(DETAIL, dto); // 해당 글 보는 곳

		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "이 게시물은 볼수 없습니다.");
		}
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
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Singleton s = Singleton.getInstance();
		
		if (e.getSource() == plus) {
			changePanel(INSERT, new BBSDto());
		}
		if( e.getSource() == minus) {
			int choice = JOptionPane.YES_NO_OPTION;
			choice = JOptionPane.showConfirmDialog(null, "이 코드를 정말 삭제하시겠습니까?", "WARNING", choice);

			if (choice == 0) {
				//삭제부분
				boolean result = s.selfDao.delete(currseq);
				if (result) {
					JOptionPane.showMessageDialog(null, "삭제되었습니다.");
					model.removeRow(jTable.getSelectedRow());
				}

			}

		}
		if(e.getSource() == searchbtn) {
			// {"전체보기", "제목", "언어", "내용"};

			String searchstr = searchtext.getText();
			List<BBSDto> tmplist = new LinkedList<>();
			Object[][] tmparr;

			if (searchbox.getSelectedIndex() == 0) {
				// 전체보기
				tmplist = s.selfDao.getSelfBbsList();
				tmparr = new Object[tmplist.size()][3];

				for (int i = 0; i < tmplist.size(); i++) {
					BBSDto dto = tmplist.get(i);

					tmparr[i][0] = dto.getTitle();
					tmparr[i][1] = dto.getSeq();
					tmparr[i][2] = dto.getShare();
				}

				model.setDataVector(tmparr, head);
				jTable.setModel(model);
				jTable.getColumnModel().getColumn(0).setMaxWidth(200);
				jTable.removeColumn(jTable.getColumnModel().getColumn(1));
				jTable.removeColumn(jTable.getColumnModel().getColumn(1));
				((AbstractTableModel) model).fireTableDataChanged();
				searchtext.setText("");

			} else if (searchbox.getSelectedIndex() == 1) {
				// 이름
				String pattern = ".*" + searchstr + ".*";

				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getTitle().matches(pattern)) {
						tmplist.add(list.get(i));
					}
				}
				tmparr = new Object[tmplist.size()][3]; // 테이블의 2차원배열이 생성

				for (int i = 0; i < tmplist.size(); i++) {
					BBSDto dto = tmplist.get(i);

					tmparr[i][0] = dto.getTitle();
					tmparr[i][1] = dto.getSeq();
					tmparr[i][2] = dto.getShare();
				}
				model.setDataVector(tmparr, head);
				jTable.setModel(model);
				jTable.getColumnModel().getColumn(0).setMaxWidth(200);
				jTable.removeColumn(jTable.getColumnModel().getColumn(1));
				jTable.removeColumn(jTable.getColumnModel().getColumn(1));
				((AbstractTableModel) model).fireTableDataChanged();
				searchtext.setText("");

			} else if (searchbox.getSelectedIndex() == 2) {
				// 언어

				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getLanguage().equals(searchstr)) {
						tmplist.add(list.get(i));
					}
				}
				tmparr = new Object[tmplist.size()][3]; // 테이블의 2차원배열이 생성

				for (int i = 0; i < tmplist.size(); i++) {
					BBSDto dto = tmplist.get(i);

					tmparr[i][0] = dto.getTitle();
					tmparr[i][1] = dto.getSeq();
					tmparr[i][2] = dto.getShare();
				}
				model.setDataVector(tmparr, head);
				jTable.setModel(model);
				jTable.getColumnModel().getColumn(0).setMaxWidth(200);
				jTable.removeColumn(jTable.getColumnModel().getColumn(1));
				jTable.removeColumn(jTable.getColumnModel().getColumn(1));
				((AbstractTableModel) model).fireTableDataChanged();
				searchtext.setText("");

			} else if (searchbox.getSelectedIndex() == 3) {
				// 내용
				String pattern = ".*" + searchstr + ".*";

				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getContent().matches(pattern)) {
						tmplist.add(list.get(i));
					}
				}
				tmparr = new Object[tmplist.size()][3]; // 테이블의 2차원배열이 생성

				for (int i = 0; i < tmplist.size(); i++) {
					BBSDto dto = tmplist.get(i);

					tmparr[i][0] = dto.getTitle();
					tmparr[i][1] = dto.getSeq();
					tmparr[i][2] = dto.getShare();
				}
				model.setDataVector(tmparr, head);
				jTable.setModel(model);
				jTable.getColumnModel().getColumn(0).setMaxWidth(200);
				jTable.removeColumn(jTable.getColumnModel().getColumn(1));
				jTable.removeColumn(jTable.getColumnModel().getColumn(1));
				((AbstractTableModel) model).fireTableDataChanged();
				searchtext.setText("");

			}

		}
	}
	
	public void setList(List<BBSDto> list) {
		rowData = new Object[list.size()][3];

		int n = 1;
		for (int i = 0; i < list.size(); i++) {
			BBSDto dto = list.get(i);
			rowData[i][0] = dto.getTitle();
			rowData[i][1] = dto.getSeq();
			rowData[i][2] = dto.getShare();
		}
		////////////////////////////// table 형태 유지
		model.setDataVector(rowData, head);
		jTable.setModel(model);
		jTable.getColumnModel().getColumn(0).setMaxWidth(200);
		jTable.removeColumn(jTable.getColumnModel().getColumn(1));
		jTable.removeColumn(jTable.getColumnModel().getColumn(1));
		((AbstractTableModel) model).fireTableDataChanged();
		searchtext.setText("");
	}

	
}
