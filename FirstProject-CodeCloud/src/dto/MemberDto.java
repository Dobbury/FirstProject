package dto;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;

public class MemberDto {

	String ID;
	String PWD;
	String Nick;
	int auth = -1;
	BufferedImage profile_Img;

	public MemberDto() {

	}

	public MemberDto(String iD, String pWD, String nick, int auth, BufferedImage profile_Img) {
		super();
		ID = iD;
		PWD = pWD;
		Nick = nick;
		this.auth = auth;
		this.profile_Img = profile_Img;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getPWD() {
		return PWD;
	}

	public void setPWD(String pWD) {
		PWD = pWD;
	}

	public String getNick() {
		return Nick;
	}

	public void setNick(String nick) {
		Nick = nick;
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}

	public BufferedImage getProfile_Img() {
		return profile_Img;
	}

	public void setProfile_Img(BufferedImage profile_Img) {
		this.profile_Img = profile_Img;
	}

}
