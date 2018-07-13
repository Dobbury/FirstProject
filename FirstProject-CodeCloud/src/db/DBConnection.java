package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import singleton.Singleton;

public class DBConnection {
	
	public static void initConnect() {
		try {
			//클래스가 있는지없는지 확인
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			System.out.println("Driver Loading Success");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection makeConnection() {
		Connection conn = null;
		Singleton s = Singleton.getInstance();
		
		try {
			//DB 설정 및 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@"+s.hostDB_IP+":1521:xe", "hr", "hr");
			
			System.out.println("DB Connection Success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
}
