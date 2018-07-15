package main;

import db.DBConnection;
import dto.MemberDto;
import singleton.Singleton;
import view.AdminMainView;
import view.HostDbSetView;
import view.LoginView;
import view.MemberMainView;
import view.memberpanel.Selfbbs;

public class MainClass {

	public static void main(String[] args) {
		Singleton s = Singleton.getInstance();
		s.nowMember = new MemberDto("aa", "aa", "aa", 1, null);
		//new HostDbSetView();
		new AdminMainView();

	}
}
