package dao;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.print.attribute.ResolutionSyntax;
import javax.swing.ImageIcon;

import Encrypt.PasswordClass;
import db.DBClose;
import db.DBConnection;
import dto.MemberDto;
/*
MEMBER	TABLE			RESTRICTION
ID		VARCHAR2(15)	PRIMARY 
PW 		VARCHAR2(10) 	NOT NULL
NICK 		VARCHAR2(15) 	UNIQUE
AUTH		NUMBER		NOT NULL	-- 0 이면 관리자 1 회원
IMG		BLOB
 */
public class MemberDao implements MemberDaoImpl {
	// 비밀번호 암호화
	PasswordClass pwdCls = new PasswordClass();

	public MemberDao() {
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
						+ "AUTH NUMBER NOT NULL"
						+ "IMG BLOB )";
				psmt = conn.prepareStatement(sql);
				psmt.executeQuery();
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);			
		}
				
	}
	public boolean getId(String id) {
		
		String sql = " SELECT ID FROM MEMBER "
				+ " WHERE ID ='" + id + "'";
		
		Connection conn = null;			// DB info
		PreparedStatement psmt = null;	// sql query
		ResultSet rs = null;			// result value
		
		Boolean findId = false;
				
		try {
			conn = DBConnection.makeConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				findId = true;
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);			
		}
		
		return findId;	
	}

	public boolean insert(MemberDto dto) {
		String path = "img/signUp/userImages.png";	//기본이미지 경로
		String pwd = pwdCls.Encryption(dto.getID());// 암호화

		String sql2 = "INSERT INTO MEMBER(id, pwd, nick, auth, img) " + "VALUES(?,?,?,?,?)";

		Connection conn = DBConnection.makeConnection();
		PreparedStatement psmt = null;
		int count = 0;

		System.out.println(sql2);

		try {

			psmt = conn.prepareStatement(sql2);

			psmt.setString(1, dto.getID());
			psmt.setString(2, pwd); // 암호화된 값을 넣어줌
			psmt.setString(3, dto.getNick());
			
			//이미지 파일을 Blob으로 저장
			File imgfile = new File(path);
			FileInputStream fis = new FileInputStream(imgfile);
			//psmt.setBinaryStream(4, null);
			psmt.setInt(4, dto.getAuth());
			psmt.setBinaryStream(5,fis, (int)imgfile.length());//이미지 저장 알아볼것
			

			count = psmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}

		return count!=0?true:false;

	}
	public MemberDto login(MemberDto dto) {
		String sql = " SELECT ID, PWD, NICK, AUTH, IMG "
				+ " FROM MEMBER "
				+ " WHERE ID=?";
		
		Connection conn = null;			
		PreparedStatement psmt = null;	
		ResultSet rs = null;
		
		MemberDto mem = null;
		
		try {
		
			conn = DBConnection.makeConnection();		
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, dto.getID());
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				String id = rs.getString(1);
				
				String pwd = pwdCls.Decode(rs.getString(2));	//복호화
				System.out.println(pwd);
				String nick = rs.getString(3);
				int auth = rs.getInt(4);
				//이미지 처리
				InputStream is = rs.getBinaryStream(5); 
				BufferedImage profile_Img = ImageIO.read(is);
				/////////////////////////////
				mem = new MemberDto(id, pwd, nick, auth,profile_Img);				
			}			
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);			
		}
		
		return mem;
	}
}
