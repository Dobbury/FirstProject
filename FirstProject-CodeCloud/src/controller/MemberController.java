package controller;

import javax.swing.JOptionPane;

import dto.MemberDto;
import service.MemberService;
import service.MemberServiceImpl;
import singleton.Singleton;
import view.LoginView;
import view.MemberMainView;

public class MemberController {
	MemberServiceImpl mService = new MemberService();
	
	public void login() {
		new LoginView();
	}
	public boolean getId(String id) {
		
		return mService.getId(id);
		
	}
	
	public boolean addMember(String id,String pwd,String nick) {
		return mService.insert(new MemberDto(id,pwd,nick,1,null));
	}
	
	public boolean loginCheck(String id,String pwd) {
		
		MemberDto dto = mService.login(new MemberDto(id,pwd,null,1,null));
		
		if(dto != null) {
			if(pwd.equals(dto.getPWD())) {
				Singleton s = Singleton.getInstance();
				s.nowMember = dto;//로그인 성공한 dto 싱글톤 nowMember에 저장
				return true;
			}else {	//비밀번호 틀릴 때 
				JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.");
				return false;
			}
		}else {//아이디 없을 때
			JOptionPane.showMessageDialog(null, "해당 아이디가 존재하지 않습니다.");
			return false;
		}
	}
	
	public void memberInfo() {
		//new memberFrame();
	}
}
