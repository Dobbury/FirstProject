package view.adminmainview.member;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dto.MemberDto;
import dto.QAbbsDto;
import singleton.Singleton;
import view.membermainview.QAbbsList;

public class adMemberbbsMain extends JPanel implements ActionListener,MouseListener {

	final int DETAIL = 0;
	final int LIST = 1;
	final int UPDATE = 2;
	
	public CardLayout cards = new CardLayout();
	JPanel ListPanel;
	public JPanel mainPanel;

	private JTable jTable;
	private JScrollPane jScrPane;
	private JButton writeBtn;
	
	DefaultTableModel model; // 테이블의 넓이 설정

	Object rowData[][];

	String columnNames[] = { "ID", "닉네임", "회원 등급" };

	List<MemberDto> list;	
	adMemberbbsDetail addetail;

	public adMemberbbsMain() {
		setLayout(null);
		setBounds(0, 0, 1000, 700);
		setOpaque(false);
		
		ListPanel = new JPanel();
		ListPanel.setLayout(null);
		ListPanel.setOpaque(false);
		ListPanel.setBounds(50,30,500,700);

		Singleton s= Singleton.getInstance();
		
		list = s.MemCtrl.getbbsList();

		rowData = new Object[list.size()][3];// 테이블의 2차원배열이 생성

		for (int i = 0; i < list.size(); i++) {
			MemberDto dto = list.get(i);

			rowData[i][0] = dto.getID();// 번호
			rowData[i][1] = dto.getNick();
			
			if(dto.getAuth()==0)
				rowData[i][2] = "관리자"; 
			else
				rowData[i][2] = "회원"; 		
		}

		model = new DefaultTableModel(columnNames, 0);
		model.setDataVector(rowData, columnNames);

		jTable = new JTable(model);
		jTable.addMouseListener(this);

		// 컬럼의 넓이 설정
		jTable.getColumnModel().getColumn(0).setMaxWidth(130);
		jTable.getColumnModel().getColumn(1).setMaxWidth(130);
		jTable.getColumnModel().getColumn(2).setMaxWidth(100);

		// 테이블안에 컬럼을 위치설정
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		celAlignCenter.setOpaque(false);
		jTable.getColumn("ID").setCellRenderer(celAlignCenter);
		jTable.getColumn("닉네임").setCellRenderer(celAlignCenter);
		jTable.getColumn("회원 등급").setCellRenderer(celAlignCenter);
		
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
		jScrPane.setBounds(50, 55, 360, 537);
		jScrPane.setOpaque(false);
		jScrPane.getViewport().setOpaque(false);
		((DefaultTableCellRenderer) jTable.getDefaultRenderer(Object.class)).setOpaque(false);

		// 테두리 없애기
		jScrPane.setBorder(BorderFactory.createCompoundBorder(null, BorderFactory.createEmptyBorder(0, 0, 0, 0)));

		ListPanel.add(jScrPane);
		
		// 코드 배경
		ImageIcon bbs_back_Img = new ImageIcon("img/adminMain/member/member_list_backgorund.png");

		JLabel bbs_backgorund = new JLabel();
		bbs_backgorund.setIcon(bbs_back_Img);
		bbs_backgorund.setBounds(50, 0, 360, 597);
		ListPanel.add(bbs_backgorund);
		
				
		mainPanel = new JPanel(cards);
		mainPanel.add("adMemberbbsDetail", new adMemberbbsDetail(this, list.get(0)));
		mainPanel.add("adMemberbbsUpdate", new adMemberbbsUpdate(this, list.get(0)));		
		cards.show(mainPanel, "adMemberbbsList");// 처음 띄워지는 판

		mainPanel.setOpaque(false);
		mainPanel.setBounds(550, 30, 500, 700);
		
		add(ListPanel);
		add(mainPanel);
		setVisible(true);
	}

	public void changePanel(int state, MemberDto dto) {
		if (state == DETAIL) {
			mainPanel.add("adMemberbbsDetail", new adMemberbbsDetail(this, dto));
			cards.show(mainPanel, "adMemberbbsDetail");
		} else if (state == UPDATE) {
			mainPanel.add("adMemberbbsUpdate", new adMemberbbsUpdate(this, dto));
			cards.show(mainPanel, "adMemberbbsUpdate");
		} 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int rowNum = jTable.getSelectedRow();
		
		// 비밀번호 변경
		if (e.getSource() == writeBtn) {
			changePanel(UPDATE, list.get(rowNum));
		}
	}

	public void setList(List<MemberDto> list) {
		rowData = new Object[list.size()][4];

			for (int i = 0; i < list.size(); i++) {
				MemberDto dto = list.get(i);

				rowData[i][0] = dto.getID();// 번호
				rowData[i][1] = dto.getNick();
				
				if(dto.getAuth()==0)
					rowData[i][2] = "관리자"; 
				else
					rowData[i][2] = "회원"; 			
			}

			model = new DefaultTableModel(columnNames, 0);
			model.setDataVector(rowData, columnNames);

			jTable.setModel(model);
			
			// 컬럼의 넓이 설정
			jTable.getColumnModel().getColumn(0).setMaxWidth(50);
			jTable.getColumnModel().getColumn(1).setMaxWidth(500);
			jTable.getColumnModel().getColumn(2).setMaxWidth(200);
			jTable.getColumnModel().getColumn(3).setMaxWidth(300);

			// 테이블안에 컬럼을 위치설정
			DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
			celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
			jTable.getColumn("ID").setCellRenderer(celAlignCenter);
			jTable.getColumn("닉네임").setCellRenderer(celAlignCenter);
			jTable.getColumn("회원등급").setCellRenderer(celAlignCenter);			
			
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
		int rowNum = jTable.getSelectedRow();
		
		Singleton s = Singleton.getInstance();
		MemberDto dto = s.MemCtrl.memSearch(list.get(rowNum).getID());

		changePanel(DETAIL, dto); // 해당 글 보는 곳
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}