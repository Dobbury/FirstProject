package view.membermainview;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
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
import javax.swing.border.Border;
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
		Singleton s = Singleton.getInstance();
		list = s.selfDao.getSelfBbsList();
		
		setLayout(null);
		setOpaque(false);
		setBounds(0, 0, 1100, 700);
		mainPanel = new JPanel(cards);

		if (list.size() > 0) {	
			mainPanel.add("SelfbbsDetail", new SelfbbsDetail(this, list.get(0)));
		}else {
			mainPanel.add("SelfbbsDetail", new SelfbbsDetail(this, new BBSDto()));
		}
		mainPanel.add("SelfbbsWrite", new SelfbbsWrite(this, new BBSDto(),INSERT));

		cards.show(mainPanel, "SelfbbsDetail");// 처음 띄워지는 판

		mainPanel.setBounds(300, 0, 800, 700);


		mainPanel.setOpaque(false);

		/*if (list.size() > 0) {//첫번째 게시물 시퀀스번호 저장
			currseq = list.get(0).getSeq();
		}*/
		
		// left
		left.setOpaque(false);
		//left.setBackground(new Color(0,0,0,30));
		left.setBounds(0, 0, 300, 700);
		left.setLayout(null);

		plus.setBounds(0, 0, 300, 50);

		plus.setOpaque(false);
		plus.setContentAreaFilled(false);
		plus.setBorderPainted(true);
		plus.setForeground(Color.WHITE);
		plus.addActionListener(this);
		plus.setFont(new Font("맑은고딕", Font.PLAIN, 40));


		left.add(plus);

		if (list.size() > 0) {
			rowData = new Object[list.size()][3];

			for (int i = 0; i < list.size(); i++) {
				rowData[i][0] = list.get(i).getTitle();
				rowData[i][1] = list.get(i).getSeq();
				rowData[i][2] = list.get(i).getShare();
			}
		}

		model = new DefaultTableModel(rowData, head);

		jTable = new JTable(model) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		//한글 지원 안됨
		jTable.setFont(new Font("맑은고딕", Font.PLAIN, 30));
		jTable.addMouseListener(this);
		jTable.setRowHeight(70);
		jTable.getColumnModel().getColumn(0).setMaxWidth(305);


		jTable.removeColumn(jTable.getColumnModel().getColumn(1));
		jTable.removeColumn(jTable.getColumnModel().getColumn(1));
		jTable.getSelectionModel().setSelectionInterval(0, 0);
		jTable.setTableHeader(null);
		jTable.setForeground(Color.WHITE);		
		jTable.setSelectionForeground(Color.BLACK);
		
		
		//스크롤바 0으로 줄여서 안보이게하는 코드
		jScrPane = new JScrollPane(jTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
		jScrPane.getVerticalScrollBar().setPreferredSize (new Dimension(0,0));
		// 테이블 투명
		jTable.setOpaque(false);
		((DefaultTableCellRenderer) jTable.getDefaultRenderer(Object.class)).setOpaque(false);
		// 스크롤영역 투명
		jScrPane.setOpaque(false);
		jScrPane.getViewport().setOpaque(false);
		
		jScrPane.setBounds(0, 50, 305, 550);

		left.add(jScrPane);
		
	
		searchbox.setBounds(0, 600, 300, 25);
		searchbox.setOpaque(false);
		searchbox.setFocusable(false);
		searchbox.setForeground(Color.white);
		searchbox.setRenderer(new DefaultListCellRenderer(){
		    @Override
		    public Component getListCellRendererComponent(JList list, Object value,
		            int index, boolean isSelected, boolean cellHasFocus) {
		        JComponent result = (JComponent)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		        result.setOpaque(false);
		        return result;
		    }});

		left.add(searchbox);
		searchtext.setForeground(Color.white);
		searchtext.setOpaque(false);
		searchtext.setBounds(0, 625, 225, 40);
		left.add(searchtext);
		searchbtn.setBounds(225, 625, 75, 40);
		searchbtn.setOpaque(false);
		searchbtn.setContentAreaFilled(false);
		//searchbtn.setBorderPainted(true);
		searchbtn.setForeground(Color.WHITE);
		searchbtn.addActionListener(this);
		left.add(searchbtn);

		add(mainPanel);
		
		Border border = BorderFactory.createLineBorder(Color.white);
	    left.setBorder(BorderFactory.createCompoundBorder(border,
	            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
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
		JTable source = (JTable) e.getSource();
		int rows = source.rowAtPoint(e.getPoint());
		
		Singleton s = Singleton.getInstance();
		try {
			int seq = (int) source.getModel().getValueAt(rows, 1);
			int shar = (int) source.getModel().getValueAt(rows, 2);
			System.out.println(shar);
			currseq = seq;
	
			for (int i = 0; i < list.size(); i++) {
				if (seq == list.get(i).getSeq()) {
					changePanel(DETAIL, list.get(i));
					break;
				}
			}
			
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
				jTable.getColumnModel().getColumn(0).setMaxWidth(300);
				jTable.removeColumn(jTable.getColumnModel().getColumn(1));
				jTable.removeColumn(jTable.getColumnModel().getColumn(1));
				changePanel(DETAIL, tmplist.get(0));
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
				jTable.getColumnModel().getColumn(0).setMaxWidth(300);
				changePanel(DETAIL, tmplist.get(0));
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
				jTable.getColumnModel().getColumn(0).setMaxWidth(300);
				changePanel(DETAIL, tmplist.get(0));
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
				jTable.getColumnModel().getColumn(0).setMaxWidth(300);
				changePanel(DETAIL, tmplist.get(0));
				jTable.removeColumn(jTable.getColumnModel().getColumn(1));
				jTable.removeColumn(jTable.getColumnModel().getColumn(1));
				((AbstractTableModel) model).fireTableDataChanged();
				searchtext.setText("");
			}
		}
	}
	
	public void setList(List<BBSDto> list) {
		rowData = new Object[list.size()][3];
		this.list = list;

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
		jTable.getColumnModel().getColumn(0).setMaxWidth(300);
		jTable.removeColumn(jTable.getColumnModel().getColumn(1));
		jTable.removeColumn(jTable.getColumnModel().getColumn(1));
		((AbstractTableModel) model).fireTableDataChanged();
		searchtext.setText("");
	}	
}
