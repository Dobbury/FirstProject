package main;

import db.DBConnection;
import view.MemberMainView;

public class MainClass {

	public static void main(String[] args) {
		DBConnection.initConnect();
		
		new MemberMainView();

	}

}
