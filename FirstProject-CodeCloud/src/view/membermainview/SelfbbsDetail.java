package view.membermainview;

import java.awt.Color;
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
import javax.swing.ButtonGroup;
import javax.swing.GrayFilter;
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

	JLabel lang;// 자바
	
	JPanel right = new JPanel();
	JLabel titlelab = new JLabel("제목");
	JTextField titletxt = new JTextField("");

	JTextArea codetxt = new JTextArea("");

	JButton editbtn = new JButton("수정");
	JToggleButton sharebtn = new JToggleButton("공유");


	BBSDao dao = new BBSDao();

	MouseEvent selectfirst;

	SelfbbsMain selfMain;
	
	List<BBSDto> list;
	BBSDto dto;
	
	public SelfbbsDetail(SelfbbsMain selfMain,BBSDto dto) {
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

		
		Font langFont = new Font("굴림",Font.BOLD, 25);
		lang = new JLabel();
		lang.setText("JAVA");
		lang.setFont(langFont);
		lang.setForeground(new Color(4,102,95));
		lang.setBounds(30,30,100,50);
		right.add(lang);
		
		Font tilteFont = new Font("굴림", Font.BOLD, 40);

		titletxt.setBounds(25,  75, 400, 50);
		titletxt.setEditable(false);
		titletxt.setFont(tilteFont);
		titletxt.setForeground(Color.BLACK);
		titletxt.setOpaque(false);

		titletxt.setText(dto.getTitle());

		right.add(titletxt);
	
		Font contentFont = new Font("굴림", Font.BOLD, 15);

		codetxt.setBounds(30, 170, 750, 400);
		codetxt.setOpaque(false);
		codetxt.setFont(contentFont);
		codetxt.setForeground(Color.white);
		
		codetxt.setText(dto.getContent());
		codetxt.setEditable(false);

		right.add(codetxt);

		// 700
		editbtn.setBounds(400, 580, 75, 50);
		editbtn.addActionListener(this);
		right.add(editbtn);
		sharebtn.setBounds(500, 580, 75, 50);
		if (list.size() > 0) {
			if (list.get(0).getShare() == 1) {
				sharebtn.setSelected(true);
			}
		}
		sharebtn.addActionListener(this);
		right.add(sharebtn);
		
		add(right);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Singleton s = Singleton.getInstance();
		Object obj = e.getSource();
	
		if (obj == editbtn) {
			selfMain.changePanel(UPDATE,dto);
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
	}

}
