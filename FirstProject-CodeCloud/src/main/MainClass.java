package main;

import db.DBConnection;
import singleton.Singleton;
import view.HostDbSetView;
import view.LoginView;
import view.MemberMainView;
import view.memberpanel.Selfbbs;

public class MainClass {

	public static void main(String[] args) {
		//DBConnection.initConnect();
		
		//Singleton s = Singleton.getInstance();
		
<<<<<<< HEAD
		new MemberMainView();
=======
		//s.MemCtrl.login();
		
		new HostDbSetView();
>>>>>>> 6f88b459f721664a514de1743ed804bfd1ab3d66
	}
}