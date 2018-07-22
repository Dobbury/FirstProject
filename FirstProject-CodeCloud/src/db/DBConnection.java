package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import singleton.Singleton;

public class DBConnection {

	static String Server_IP;

	public static void initConnect(String ip) {
		try {
			// 클래스가 있는지없는지 확인
			Class.forName("oracle.jdbc.driver.OracleDriver");

			System.out.println("Driver Loading Success");

			Server_IP = ip;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection makeConnection() {
		Connection conn = null;

		try {
			// DB 설정 및 연결
			System.out.println(Server_IP);
			conn = DriverManager.getConnection("jdbc:oracle:thin:@" + Server_IP + ":1521:xe", "hr", "hr");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}
}
