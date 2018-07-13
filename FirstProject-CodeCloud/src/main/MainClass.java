package main;

import db.DBConnection;
import singleton.Singleton;
import view.LoginView;
import view.MemberMainView;
import view.memberpanel.Selfbbs;

public class MainClass {

	public static void main(String[] args) {
		//DBConnection.initConnect();
		
		Singleton s = Singleton.getInstance();
		
		new MemberMainView();
	}
}