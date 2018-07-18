package bbsCircle;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import bbs.BbsCircle;
import dti.BbsDto;

public class BbsCircleList extends JPanel implements ActionListener {

	
	BbsCircle cd;
	JButton testChange;
	public BbsCircleList(BbsCircle bbsCircle   ) {
		cd = bbsCircle; 
		
		
		testChange = new JButton("체인지 뷰 확인 버튼");
        testChange.addActionListener(this);
        testChange.setBounds(420, 600, 100, 20);
        add(testChange);
        
 
        setLayout(null);
        setBackground(Color.ORANGE);
        setBounds(50, 50, 300, 300);
        setVisible(true);


        
        
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
