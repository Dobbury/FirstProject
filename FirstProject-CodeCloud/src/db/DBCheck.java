package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCheck {
	
	public static void memDBcheck() {
		String sql = "SELECT TABLE_NAME FROM ALL_TABLES WHERE OWNER='HR' AND TABLE_NAME='MEMBER'";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.makeConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if(!rs.next()) {	//테이블이 없다면 생성
				sql = "CREATE TABLE MEMBER("
						+ "ID VARCHAR2(15) PRIMARY KEY,"
						+ "PWD VARCHAR2(10) NOT NULL,"
						+ "NICK VARCHAR2(15) UNIQUE,"
						+ "AUTH NUMBER NOT NULL,"
						+ "IMG BLOB )";
				psmt = conn.prepareStatement(sql);
				psmt.executeQuery();
			}

		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);			
		}
		
		System.out.println("멤버테이블 체크 성공");
	}
	
	public static void shareDBCheck() {
		String sql = "SELECT TABLE_NAME FROM ALL_TABLES WHERE OWNER='HR' AND TABLE_NAME='SHAR'";
		String sqlseq = "SELECT * FROM USER_SEQUENCES WHERE SEQUENCE_NAME='SHAR_SEQ'";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.makeConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if(!rs.next()) {	//테이블이 없다면 생성
				sql = "CREATE TABLE SHAR("
						+ "SEQ NUMBER PRIMARY KEY,"
						+ "INDSEQ NUMBER NOT NULL,"
						+ "NICK VARCHAR2(15) NOT NULL,"
						+ "TITLE VARCHAR2(50) NOT NULL,"
						+ "CONT VARCHAR2(4000) NOT NULL,"
						+ "LIKED NUMBER NOT NULL,"
						+ "FORK NUMBER NOT NULL,"
						+ "LAN VARCHAR2(10) NOT NULL,"
						//+ "LIKELIST VARCHAR2(4000) "
						+ "CONSTRAINT FK_SHAR_NICK FOREIGN KEY(NICK) "
						+ "REFERENCES MEMBER(NICK))";
				psmt = conn.prepareStatement(sql);
				psmt.executeQuery();
			}
			
			psmt = conn.prepareStatement(sqlseq);
			rs = psmt.executeQuery();
			
			if(!rs.next()) {	//테이블이 없다면 생성
				sqlseq = "CREATE SEQUENCE SHAR_SEQ " 
						+ "START WITH 1 "
						+ "INCREMENT BY 1";
						
				psmt = conn.prepareStatement(sqlseq);
				psmt.executeQuery();
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);			
		}
		System.out.println("공유테이블 체크 성공");
	}
	
	public static void qaDBCheck() {
		String sql = "SELECT TABLE_NAME FROM ALL_TABLES WHERE OWNER='HR' AND TABLE_NAME='QA'";
		String sqlseq = "SELECT * FROM USER_SEQUENCES WHERE SEQUENCE_NAME='QA_SEQ'";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.makeConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if(!rs.next()) {	//테이블이 없다면 생성
				sql = "CREATE TABLE QA("
						+ "SEQ     NUMBER		PRIMARY KEY,"
						+ "TITLE	VARCHAR2(50)	NOT NULL,"
						+ "DAT		DATE			NOT NULL,"
						+ "NICK	VARCHAR2(15) NOT NULL,"
						+ "CONTENT	VARCHAR2(4000)	NOT NULL,"
						+ "DEL		NUMBER	    NOT NULL,"
						+ "REF 	NUMBER,	"
						+ "STEP 	NUMBER,"
						+ "DEPT 	NUMBER,"
						+ "VISIBLE	NUMBER	NOT NULL,"
						+ "ANSWER	NUMBER  NOT NULL,"
						+ "CONSTRAINT FK_QA_NICK FOREIGN KEY(NICK) "
						+ "REFERENCES MEMBER(NICK))";
				psmt = conn.prepareStatement(sql);
				psmt.executeQuery();
			}
			
			psmt = conn.prepareStatement(sqlseq);
			rs = psmt.executeQuery();
			if(!rs.next()) {	//테이블이 없다면 생성
				sqlseq = "CREATE SEQUENCE QA_SEQ " 
						+ "START WITH 1 "
						+ "INCREMENT BY 1";
				psmt = conn.prepareStatement(sqlseq);
				psmt.executeQuery();
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);			
		}
		System.out.println("QA테이블 체크 성공");
	}
	
	
	

}
