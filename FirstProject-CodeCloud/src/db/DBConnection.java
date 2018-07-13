package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import singleton.Singleton;

public class DBConnection {
<<<<<<< HEAD
	
	static String Server_IP;
	
=======
	static String Server_IP;
>>>>>>> f6d05562119d7eb12ae0d5c5fe3336b0a3f57164
	public static void initConnect(String ip) {
		try {
			//클래스가 있는지없는지 확인
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			System.out.println("Driver Loading Success");
			
<<<<<<< HEAD
=======
		
>>>>>>> f6d05562119d7eb12ae0d5c5fe3336b0a3f57164
			Server_IP = ip;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection makeConnection() {
		Connection conn = null;
		
		try {
			//DB 설정 및 연결
<<<<<<< HEAD
			conn = DriverManager.getConnection("jdbc:oracle:thin:@"+Server_IP+":1521:xe", "hr", "hr");
			
=======
			System.out.println("서버 IP: "+Server_IP);
			conn = DriverManager.getConnection("jdbc:oracle:thin:@"+Server_IP+":1521:xe", "hr", "hr");
		
>>>>>>> f6d05562119d7eb12ae0d5c5fe3336b0a3f57164
			System.out.println("DB Connection Success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
}
