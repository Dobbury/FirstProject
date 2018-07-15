package main;

import db.DBConnection;
import singleton.Singleton;
import view.HostDbSetView;
import view.LoginView;
import view.MemberMainView;
import view.memberpanel.Selfbbs;

public class MainClass {

	public static void main(String[] args) {
		

		//Singleton s = Singleton.getInstance()
		new HostDbSetView();

	}
}
