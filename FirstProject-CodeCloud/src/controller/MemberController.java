package controller;

import javax.swing.JOptionPane;

import Encrypt.PasswordClass;
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
	
	public boolean getNick(String nick) {
		return mService.getNick(nick);
	}
	
	public boolean addMember(String id,String pwd,String nick) {
		return mService.insert(new MemberDto(id,pwd,nick,1,null));
	}
	
	public boolean loginCheck(String id,String pwd) {
		
		MemberDto dto = mService.login(new MemberDto(id,pwd,null,1,null));
		
		PasswordClass pwdCls = new PasswordClass();
		pwd = pwdCls.Encryption(pwd);//암호화 
		
		if(dto != null) {
				Singleton s = Singleton.getInstance();
				s.nowMember = dto;//로그인 성공한 dto 싱글톤 nowMember에 저장
				return true;
		}else {//아이디 없을 때
			return false;
		}
	}
	
	public void memberInfo() {
		//new memberFrame();
	}
}
