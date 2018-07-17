package main;

import db.DBConnection;
import dto.MemberDto;
import singleton.Singleton;
import view.AdminMainView;
import view.HostDbSetView;
import view.LoginView;
import view.MemberMainView;

import view.membermainview.Selfbbs;

import view.SignupView;

public class MainClass {

	public static void main(String[] args) {

		new HostDbSetView();
	}
}
