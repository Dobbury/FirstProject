package view.membermainview;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import dao.ShareDao;
import dto.QAbbsDto;
import dto.ShareDto;
import singleton.Singleton;

public class ShareDetail extends JPanel implements Action {


	JLabel likelab = new JLabel();
	JLabel forklab = new JLabel();

	JTextField titleText;// 닉넴 텍스트필드
	JTextField titleText2;// 제목 텍스트필드
	JTextField titleText3;// content 텍스트 필드
	
	JTextArea postArea;
	JScrollPane jScrol;
	
	private JButton btn_List;// 목록으로
	private JToggleButton btn_liked;//좋아요 버튼
	private JButton btn_fork;// 다운로드 버튼 
	
	
	Sharebbs ShareMain;
	ShareDto dto;
	public ShareDetail(Sharebbs sharebbs, ShareDto dto) {
		
		
		List<Integer> list = ShareDao.getlikeseqlist();
		
		this.dto = dto;
		
		ShareMain=sharebbs;
		
		//닉네임
		titleText = new JTextField();
		titleText.setBounds(50, 160, 310, 30);
		titleText.setText(dto.getNick());
		titleText.setEditable(false);

		//타이틀
		titleText2 = new JTextField();
		titleText2.setBounds(50, 100, 310, 50);
		titleText2.setText(dto.getTitle());
		titleText2.setEditable(false);
		
		postArea = new JTextArea();
		postArea.append(dto.getContent());
		postArea.setEditable(false);
		
		jScrol = new JScrollPane(postArea);
		jScrol.setBounds(50, 200, 750, 350);

		// 글 목록
		btn_List = new JButton("글 목록");
		btn_List.addActionListener(this);
		btn_List.setBounds(50, 570, 100, 40);
		
		//좋아요버튼
		btn_liked = new JToggleButton("추천");
		btn_liked.addActionListener(this);
		btn_liked.setBounds(610, 160, 30, 30);
		
		for (int i = 0; i < list.size(); i++) {
			if (dto.getSeq() == list.get(i)) {
				btn_liked.setSelected(true);
				break;
			}
		}
		
		
		
		
		likelab.setBounds(670, 160, 30, 30);
		likelab.setForeground(Color.WHITE);
		likelab.setText(dto.getLiked()+"");
		
		//포크 버튼 
		btn_fork = new JButton("퍼가요");
		btn_fork.addActionListener(this);
		btn_fork.setBounds(710, 160, 30, 30);
		
		forklab.setBounds(770, 160, 30, 30);
		forklab.setForeground(Color.WHITE);
		forklab.setText(dto.getFork()+"");
		
		add(titleText);
		add(titleText2);
		
		add(jScrol);

		add(btn_List);
		add(btn_liked);
		add(btn_fork);
		add(likelab);
		add(forklab);
		
		setLayout(null);
		setOpaque(false);
		
		setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

	Singleton s = Singleton.getInstance();
			
		 
		if(e.getSource() == btn_List) {
			//Sharebbs.changePanel(new ShareList(ShareMain));
			ShareMain.changePanel(1, null);
			
		}else if (e.getSource() == btn_liked) {
			
			if (btn_liked.isSelected() == true) {
				ShareDao.clicklike(s.nowMember.getID(), dto.getNick(), dto.getIndseq(), dto.getSeq());
				dto.setLiked(dto.getLiked()+1);
			}else {
				ShareDao.clickunlike(s.nowMember.getID(), dto.getNick(), dto.getIndseq(), dto.getSeq());
				dto.setLiked(dto.getLiked()-1);
			}
			
			likelab.setText(dto.getLiked()+"");
			
		}else if(e.getSource() == btn_fork) {
			 ShareDao.codefork(dto);
			 dto.setFork(dto.getFork()+1);
			 forklab.setText(dto.getFork()+"");
		}

	 
		
		
	}
	
	
	
	
	
	
	

	@Override
	public Object getValue(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putValue(String arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

}
