package db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Encrypt.PasswordClass;

public class DBCheck {

	
	public static void memDBcheck() throws FileNotFoundException {

		String sql = "SELECT TABLE_NAME FROM ALL_TABLES WHERE OWNER='HR' AND TABLE_NAME='MEMBER'";
		String path = "img/signUp/userImages.png";
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.makeConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			if (!rs.next()) { // 테이블이 없다면 생성
				sql = "CREATE TABLE MEMBER(" 
						+ "ID VARCHAR2(15 CHAR) PRIMARY KEY," 
						+ "PWD VARCHAR2(10 CHAR) NOT NULL,"
						+ "NICK VARCHAR2(15 CHAR) UNIQUE," 
						+ "AUTH NUMBER NOT NULL," + "IMG BLOB )";

				psmt = conn.prepareStatement(sql);
				psmt.executeQuery();
				
				psmt = conn.prepareStatement(sql2);

				String pwtmp = PasswordClass.Encryption("1");
				psmt.setString(1, pwtmp); // 암호화된 값을 넣어줌

				
				//이미지 파일을 Blob으로 저장
				//psmt.setBinaryStream(4, null);
	
				psmt.setBinaryStream(2,fis, (int)imgfile.length());//이미지 저장 알아볼것
				psmt.executeQuery();
				System.out.println("관리자 아이디 생성");
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

			if (!rs.next()) { // 테이블이 없다면 생성
				sql = "CREATE TABLE SHAR(" 
						+ "SEQ NUMBER PRIMARY KEY," 
						+ "INDSEQ NUMBER NOT NULL,"
						+ "NICK VARCHAR2(15 CHAR) NOT NULL," 
						+ "TITLE VARCHAR2(50 CHAR) NOT NULL,"
						+ "CONT VARCHAR2(4000 CHAR) NOT NULL," 
						+ "LIKED NUMBER NOT NULL," 
						+ "FORK NUMBER NOT NULL,"
						+ "LAN VARCHAR2(10 CHAR) NOT NULL," 
						+ "CONSTRAINT FK_SHAR_NICK FOREIGN KEY(NICK) "
						+ "REFERENCES MEMBER(NICK) ON DELETE CASCADE )";

				psmt = conn.prepareStatement(sql);
				psmt.executeQuery();
			}

			psmt = conn.prepareStatement(sqlseq);
			rs = psmt.executeQuery();

			if (!rs.next()) { // 테이블이 없다면 생성
				sqlseq = "CREATE SEQUENCE SHAR_SEQ " + "START WITH 1 " + "INCREMENT BY 1";

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
			if (!rs.next()) { // 테이블이 없다면 생성
				sql = "CREATE TABLE QA(" 
						+ "SEQ     NUMBER		PRIMARY KEY," 
						+ "TITLE	VARCHAR2(50 CHAR)	NOT NULL,"
						+ "DAT		DATE			NOT NULL," 
						+ "NICK	VARCHAR2(15 CHAR),"
						+ "CONTENT	VARCHAR2(4000 CHAR)	NOT NULL," 
						+ "DEL		NUMBER	    NOT NULL," + "REF 	NUMBER,	"
						+ "STEP 	NUMBER," 
						+ "DEPT 	NUMBER," 
						+ "VISIBLE	NUMBER	NOT NULL,"
						+ "ANSWER	NUMBER  NOT NULL," 
						+ "CONSTRAINT FK_QA_NICK FOREIGN KEY(NICK) "
						+ "REFERENCES MEMBER(NICK) ON DELETE CASCADE )";
				psmt = conn.prepareStatement(sql);
				psmt.executeQuery();

				sql = "INSERT INTO QA VALUES (0,'ROOT',SYSDATE, NULL,'ROOT',0,null,0,0,0,0)";

				psmt = conn.prepareStatement(sql);
				psmt.executeUpdate();

			}

			psmt = conn.prepareStatement(sqlseq);
			rs = psmt.executeQuery();
			if (!rs.next()) { // 테이블이 없다면 생성
				sqlseq = "CREATE SEQUENCE QA_SEQ " + "START WITH 1 " + "INCREMENT BY 1";
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
	public static void createUpdateTrigger() {
		String Tri_sql = "CREATE OR REPLACE TRIGGER MEMBER_UPDATE_TRG " + 
				"AFTER UPDATE OF NICK ON MEMBER FOR EACH ROW " + 
				"BEGIN " + 
				"UPDATE SHAR " + 
				"SET NICK =:NEW.NICK " + 
				"WHERE NICK =:OLD.NICK; " + 
				"UPDATE QA " + 
				"SET NICK =:NEW.NICK " + 
				"WHERE NICK =:OLD.NICK; " + 
				"END;";

		
		Connection conn = null;
		Statement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.makeConnection();
			
			psmt = conn.createStatement();
			psmt.executeQuery(Tri_sql);
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		System.out.println("Trigger Update 완료");

	}
}
