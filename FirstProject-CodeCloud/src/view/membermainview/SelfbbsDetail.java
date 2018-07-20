package view.membermainview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.BBSDao;
import dto.BBSDto;
import dto.QAbbsDto;
import singleton.Singleton;
import view.MemberMainView;

public class SelfbbsDetail extends JPanel implements ActionListener {

	final int INSERT = 0;
	final int UPDATE = 1;
	final int DETAIL = 2;

	JLabel lang;

	JPanel right = new JPanel();
	JLabel titletxt = new JLabel();

	JTextArea codetxt = new JTextArea("");

	// 버튼
	ImageIcon editIc1;
	ImageIcon editIc2;
	ImageIcon editIc3;
	JButton editbtn;

	// 버튼
	ImageIcon shareIc1;
	ImageIcon shareIc2;
	ImageIcon shareIc3;
	JToggleButton sharebtn;

	// 버튼
	ImageIcon deleteIc1;
	ImageIcon deleteIc2;
	ImageIcon deleteIc3;
	JButton deletebtn;

	private JScrollPane jScrPane;

	BBSDao dao = new BBSDao();

	MouseEvent selectfirst;

	SelfbbsMain selfMain;

	List<BBSDto> list;
	BBSDto dto;

	public SelfbbsDetail(SelfbbsMain selfMain, BBSDto dto) {
		setLayout(null);
		setOpaque(false);
		this.selfMain = selfMain;
		this.dto = dto;

		Singleton s = Singleton.getInstance();
		list = s.selfDao.getSelfBbsList();

		// right
		right.setOpaque(false);
		right.setBounds(0, 0, 900, 700);
		right.setLayout(null);

		Font langFont = new Font("굴림", Font.BOLD, 25);
		lang = new JLabel();
		lang.setText(dto.getLanguage());
		lang.setFont(langFont);
		lang.setForeground(Color.white);
		lang.setBounds(35, 110, 75, 50);
		right.add(lang);

		Font tilteFont = new Font("굴림", Font.BOLD, 40);

		titletxt.setBounds(30, 55, 400, 50);
		titletxt.setFont(tilteFont);
		titletxt.setForeground(Color.white);
		titletxt.setOpaque(false);

		titletxt.setText(dto.getTitle());
		right.add(titletxt);

		Font contentFont = new Font("굴림", Font.BOLD, 15);
		
		//코드 배경
		ImageIcon code_back_Img = new ImageIcon("img/selfbbs/self_code_background.png");
		
		JLabel code_backgorund = new JLabel();
		code_backgorund.setIcon(code_back_Img);
		code_backgorund.setBounds(25,170,750,400);
		
		
		codetxt.setOpaque(false);
		codetxt.setFont(contentFont);
		codetxt.setForeground(Color.white);

		codetxt.setText(dto.getContent());
		codetxt.setEditable(false);

		//스크롤바 0으로 줄여서 안보이게하는 코드
		jScrPane = new JScrollPane(codetxt, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
		jScrPane.getVerticalScrollBar().setPreferredSize (new Dimension(0,0));
		jScrPane.setBounds(25,170,750,400);
		jScrPane.setOpaque(false);
		jScrPane.getViewport().setOpaque(false);
		jScrPane.setBorder(BorderFactory.createCompoundBorder(null,
	            BorderFactory.createEmptyBorder(20, 20, 20, 20)));
		right.add(jScrPane);
		right.add(code_backgorund);
		
		
		editIc1 = new ImageIcon("img/selfbbs/self_edit_on.png");
		editIc2 = new ImageIcon("img/selfbbs/self_edit_off.png");
		editIc3 = new ImageIcon("img/selfbbs/self_edit_ing.png");
		editbtn = new JButton(editIc1);
		editbtn.setRolloverIcon(editIc2);
		editbtn.setPressedIcon(editIc3);
		editbtn.setBorderPainted(false);
		editbtn.setContentAreaFilled(false);
		editbtn.setFocusPainted(false);
		// 공유 수정 삭제
		editbtn.setBounds(500, 590, 76, 51);
		editbtn.addActionListener(this);
		right.add(editbtn);
		
		shareIc1 = new ImageIcon("img/selfbbs/self_share_on.png");
		shareIc2 = new ImageIcon("img/selfbbs/self_share_off.png");
		shareIc3 = new ImageIcon("img/selfbbs/self_share_ing.png");
		sharebtn = new JToggleButton(shareIc1);
		sharebtn.setRolloverIcon(shareIc2);
		sharebtn.setPressedIcon(shareIc3);
		sharebtn.setBorderPainted(false);
		sharebtn.setContentAreaFilled(false);
		sharebtn.setFocusPainted(false);
		
		sharebtn.setBounds(600, 590, 76, 51);

		if (dto.getShare() == 1) {
			sharebtn.setSelected(true);
		}

		deleteIc1 = new ImageIcon("img/selfbbs/self_delete_on.png");
		deleteIc2 = new ImageIcon("img/selfbbs/self_delete_off.png");
		deleteIc3 = new ImageIcon("img/selfbbs/self_delete_ing.png");
		deletebtn = new JButton(deleteIc1);
		deletebtn.setRolloverIcon(deleteIc2);
		deletebtn.setPressedIcon(deleteIc3);
		deletebtn.setBorderPainted(false);
		deletebtn.setContentAreaFilled(false);
		deletebtn.setFocusPainted(false);
		
		deletebtn.setBounds(700, 590, 76, 51);
		deletebtn.addActionListener(this);
		right.add(deletebtn);

		sharebtn.addActionListener(this);
		right.add(sharebtn);
		add(right);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Singleton s = Singleton.getInstance();
		Object obj = e.getSource();

		if (obj == editbtn) {
			selfMain.changePanel(UPDATE, dto);
		}
		if (obj == sharebtn) {

			if (sharebtn.isSelected()) {
				// 공유 하기
				int choice = JOptionPane.YES_NO_OPTION;
				choice = JOptionPane.showConfirmDialog(null, "이 코드를 공유하시겠습니까?", "WARNING", choice);
				if (choice == 0) {

					int result = dao.share(dto);
					if (result > 0) {
						JOptionPane.showMessageDialog(null, "공유되었습니다.");
					}
				} else {
					sharebtn.setSelected(false);
				}
			} else {
				// 공유 풀기
				int choice2 = JOptionPane.YES_NO_OPTION;
				choice2 = JOptionPane.showConfirmDialog(null, "이 코드를 공유해제 하시겠습니까?", "WARNING", choice2);
				if (choice2 == 0) {

					int result2 = dao.unshare(dto.getSeq());
					if (result2 > 0) {
						JOptionPane.showMessageDialog(null, "공유가 해제 되었습니다.");
					}
				} else {
					sharebtn.setSelected(true);
				}
			}
		}
		if (e.getSource() == deletebtn) {
			int choice = JOptionPane.YES_NO_OPTION;
			choice = JOptionPane.showConfirmDialog(null, "이 코드를 정말 삭제하시겠습니까?", "WARNING", choice);

			if (choice == 0) {
				// 삭제부분
				boolean result = s.selfDao.delete(dto.getSeq());
				if (result) {
					JOptionPane.showMessageDialog(null, "삭제되었습니다.");
					list = s.selfDao.getSelfBbsList();
					if(list.size() > 0) {
						selfMain.changePanel(DETAIL, list.get(0));
					}else {
						selfMain.changePanel(DETAIL, new BBSDto());
					}
					selfMain.setList(list);
				}
			}
		}
	}
}
