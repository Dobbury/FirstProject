package controller;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Encrypt.PasswordClass;
import convertImg.ImageToBufferedImageClass;
import dto.MemberDto;
import service.MemberService;
import service.MemberServiceImpl;
import singleton.Singleton;
import view.AdminMainView;
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
	public boolean memberUpdate(String id, String pwd, String nick,int auth,ImageIcon imgIcon) {
		
		//Image -> BufferedImage
		Image oriImg = imgIcon.getImage();
		BufferedImage img = ImageToBufferedImageClass.toBufferedImage(oriImg);
		
		MemberDto dto = new MemberDto(id,pwd,nick,auth,img);
		if(mService.update(dto)) {
			Singleton s = Singleton.getInstance();
			s.nowMember = dto;
			return  true;
		}
		else
			return false;
	}
	public boolean addMember(String id, String pwd, String nick) {
		return mService.insert(new MemberDto(id, pwd, nick, 1, null));
	}

	
	public boolean loginCheck(String id,String pwd) {
		
<<<<<<< HEAD
		MemberDto dto = mService.login(new MemberDto(id,pwd,null,1,null));

		PasswordClass pwdCls = new PasswordClass();
		pwd = pwdCls.Encryption(pwd);//암호화 
=======

		MemberDto dto = mService.login(new MemberDto(id,pwd,null,-1,null));
>>>>>>> c6d0a980b1b378fbefda7d652763da0e78b25d75

		if(dto != null) {
				Singleton s = Singleton.getInstance();
				s.nowMember = dto;//로그인 성공한 dto 싱글톤 nowMember에 저장
				
				if(dto.getAuth() == 0 ) {
					new AdminMainView();
				}else if (dto.getAuth() == 1) {
					new MemberMainView();
				}
				return true;
		}else {//아이디 없을 때

			return false;
		}
	}

	public void memberInfo() {
		// new memberFrame();
	}
	

}
