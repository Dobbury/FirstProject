package dao;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import Encrypt.PasswordClass;
import db.DBClose;
import db.DBConnection;
import dto.BBSDto;
import dto.MemberDto;
import singleton.Singleton;

public class BBSDao implements BBSDaoImpl {

	@Override
	public LinkedList<BBSDto> getSelfBbsList() {
		Singleton s = Singleton.getInstance();
		String sql = "SELECT * FROM " + s.nowMember.getID();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		LinkedList<BBSDto> tmp = new LinkedList<>();
		try {
			conn = DBConnection.makeConnection();

			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();

			while (rs.next()) {
				int i = 1;

				BBSDto dto = new BBSDto(rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getInt(i++),
						rs.getInt(i++), rs.getInt(i++), rs.getString(i++));
				tmp.add(dto);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return tmp;
	}

	
	@Override
	public boolean insert(String title, String lang, String content) {
		Singleton s = Singleton.getInstance();
		String sql = "INSERT INTO " + s.nowMember.getID() + " VALUES(" + s.nowMember.getID()
				+ "_SEQ.NEXTVAL, ?, ?, 0, 0, 0, ?)";

		String sql2 = "SELECT " + s.nowMember.getID() + "_SEQ.CURRVAL FROM DUAL";
		int seq = -1;

		Connection conn = DBConnection.makeConnection();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int count = 0;

		System.out.println(sql);

		try {

			psmt = conn.prepareStatement(sql);

			psmt.setString(1, title);
			psmt.setString(2, content); // 암호화된 값을 넣어줌
			psmt.setString(3, lang);

			// 이미지 파일을 Blob으로 저장

			count = psmt.executeUpdate();

			psmt = conn.prepareStatement(sql2);
			rs = psmt.executeQuery();
			rs.next();
			seq = rs.getInt(1);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}

		return count>0?true:false;

	}

	@Override
	public BBSDto select(int seq) {
		// TODO Auto-generated method stub
		Singleton s = Singleton.getInstance();
		
		String sql = " SELECT TITLE,CONT,SHA,LIKED,FORK,LANG FROM " + s.nowMember.getID()+" WHERE SEQ =" + seq;

		Connection conn = null; // DB info
		PreparedStatement psmt = null; // sql query
		ResultSet rs = null; // result value

		BBSDto self = null;
		
		try {
			conn = DBConnection.makeConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			if (rs.next()) {
				
				String title = rs.getString(1);
				String cont = rs.getString(2);
				int shar = rs.getInt(3);
				int liked = rs.getInt(4);
				int fork = rs.getInt(5);
				String lang = rs.getString(6);
				self = new BBSDto(seq,title,cont,shar,liked,fork,lang);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}

		return self;
		
	}

	@Override
	public boolean update(BBSDto dto) {
		Singleton s = Singleton.getInstance();
		String sql = "UPDATE " + s.nowMember.getID() + " SET TITLE=?, LANG=?, CONT=? WHERE SEQ=?";

		String sql2 = "UPDATE SHAR" + " SET TITLE=?, LAN=?, CONT=? WHERE INDSEQ=? AND NICK=?";

		Connection conn = DBConnection.makeConnection();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int count = 0;
		int count2 = 0;

		System.out.println(sql);

		try {

			psmt = conn.prepareStatement(sql);

			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getLanguage()); // 암호화된 값을 넣어줌
			psmt.setString(3, dto.getContent());
			psmt.setInt(4, dto.getSeq());

			count = psmt.executeUpdate();

			if (dto.getShare() == 1) {
				psmt = conn.prepareStatement(sql2);

				psmt.setString(1, dto.getTitle());
				psmt.setString(2, dto.getLanguage()); // 암호화된 값을 넣어줌
				psmt.setString(3, dto.getContent());
				psmt.setInt(4, dto.getSeq());
				psmt.setString(5, s.nowMember.getNick());

				count2 = psmt.executeUpdate();
				if (count2 > 0) {
					JOptionPane.showMessageDialog(null, "공유 게시판에 글도 수정되었습니다.");
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}

		return count>0?true:false;

	}

	@Override
	public boolean delete(int seq) {
		Singleton s = Singleton.getInstance();
		String sql = "DELETE FROM " + s.nowMember.getID() + " WHERE SEQ=?";

		String sql2 = "DELETE FROM SHAR" + " WHERE INDSEQ=? AND NICK=?";

		Connection conn = DBConnection.makeConnection();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int count = 0;

		System.out.println(sql);

		try {
			//공유된글ㅇ ㅣ있는지 확인후 지우고 그다음 개인게시판 지우기
			psmt = conn.prepareStatement(sql2);
			psmt.setInt(1, seq);
			psmt.setString(2, s.nowMember.getNick());

			int count2 = psmt.executeUpdate();
			if (count2 > 0) {
				JOptionPane.showMessageDialog(null, "공유 게시판에 글도 삭제되었습니다.");
			}
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);

			count = psmt.executeUpdate();


	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}

		return count>0?true:false;

	}

	@Override
	public int share(BBSDto dto) {
		// 개인 코드 테이블에서 쉐어 컬럼 수정
		Singleton s = Singleton.getInstance();
		String sql = "UPDATE " + s.nowMember.getID() + " SET SHA=1 WHERE SEQ=?";

		String sql2 = "INSERT INTO SHAR" + " VALUES(SHAR_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";

		Connection conn = DBConnection.makeConnection();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int count = 0;
		// 쉐어 테이블에 삽입

		try {

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, dto.getSeq());

			psmt.executeUpdate();

			psmt = conn.prepareStatement(sql2);
			psmt.setInt(1, dto.getSeq());
			psmt.setString(2, s.nowMember.getNick());
			psmt.setString(3, dto.getTitle());
			psmt.setString(4, dto.getContent());
			psmt.setInt(5, dto.getLiked());
			psmt.setInt(6, dto.getFork());
			psmt.setString(7, dto.getLanguage());

			count = psmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}

		return count;
	}

	@Override
	public int unshare(int seq) {
		// 개인 코드 테이블에서 쉐어 컬럼 수정
		Singleton s = Singleton.getInstance();
		String sql = "UPDATE " + s.nowMember.getID() + " SET SHA=0 WHERE SEQ=?";

		String sql2 = "DELETE FROM SHAR" + " WHERE INDSEQ=? AND NICK=?";
		// 쉐어테이블에서 삭제
		Connection conn = DBConnection.makeConnection();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int count = 0;

		try {

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);

			psmt.executeUpdate();

			psmt = conn.prepareStatement(sql2);
			psmt.setInt(1, seq);
			psmt.setString(2, s.nowMember.getNick());

			count = psmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}

		return count;
	}

}
