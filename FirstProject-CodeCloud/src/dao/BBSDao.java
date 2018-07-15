package dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import Encrypt.PasswordClass;
import db.DBClose;
import db.DBConnection;
import dto.BBSDto;
import dto.MemberDto;
import singleton.Singleton;

public class BBSDao implements BBSDaoImpl {

	@Override
	public LinkedList<BBSDto> list() {
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
			
			while(rs.next()) {
				int i = 1;
				
				BBSDto dto = new BBSDto(rs.getInt(i++), 
										rs.getString(i++), 
										rs.getString(i++), 
										rs.getInt(i++), 
										rs.getInt(i++), 
										rs.getInt(i++), 
										rs.getString(i++));
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
	public int insert(String title, String lang, String content) {
		Singleton s = Singleton.getInstance();
		String sql = "INSERT INTO " + s.nowMember.getID() 
				+ " VALUES("+s.nowMember.getID()+"_SEQ.NEXTVAL, ?, ?, 0, 0, 0, ?)";
		
		String sql2 = "SELECT "+s.nowMember.getID()+"_SEQ.CURRVAL FROM DUAL";
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
			
			//이미지 파일을 Blob으로 저장
			
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
		
		s.selfcodelist.add(new BBSDto(seq, title, content, 0, 0, 0, lang));

		return seq;

	}

	@Override
	public void select() {
		// TODO Auto-generated method stub

	}

	@Override
	public int update(int seq, String title, String lang, String content, boolean share) {
		Singleton s = Singleton.getInstance();
		String sql = "UPDATE " + s.nowMember.getID() 
				+ " SET TITLE=?, LANG=?, CONT=? WHERE SEQ=?";
		
		String sql2 = "UPDATE SHAR" 
		+ " SET TITLE=?, LAN=?, CONT=? WHERE INDSEQ=? AND NICK=?";

		Connection conn = DBConnection.makeConnection();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int count = 0;
		int count2 = 0;
		
		System.out.println(sql);

		try {

			psmt = conn.prepareStatement(sql);

			psmt.setString(1, title);
			psmt.setString(2, lang); // 암호화된 값을 넣어줌
			psmt.setString(3, content);
			psmt.setInt(4, seq);
			
			count = psmt.executeUpdate();
			
			if (share == true) {
				psmt = conn.prepareStatement(sql2);

				psmt.setString(1, title);
				psmt.setString(2, lang); // 암호화된 값을 넣어줌
				psmt.setString(3, content);
				psmt.setInt(4, seq);
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
		
		for (int i = 0; i < s.selfcodelist.size(); i++) {
			if (s.selfcodelist.get(i).getSeq() == seq) {
				s.selfcodelist.get(i).setTitle(title);
				s.selfcodelist.get(i).setContent(content);
				s.selfcodelist.get(i).setLanguage(lang);
			}
		}

		return count;

	}

	@Override
	public int delete(int seq, boolean share) {
		Singleton s = Singleton.getInstance();
		String sql = "DELETE FROM " + s.nowMember.getID() 
				+ " WHERE SEQ=?";
		
		String sql2 = "DELETE FROM SHAR"
		+ " WHERE INDSEQ=? AND NICK=?";

		Connection conn = DBConnection.makeConnection();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int count = 0;
		
		System.out.println(sql);

		try {

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			count = psmt.executeUpdate();
			
			if (share == true) {
				psmt = conn.prepareStatement(sql2);
				psmt.setInt(1, seq);
				psmt.setString(2, s.nowMember.getNick());
				
				int count2 = psmt.executeUpdate();
				if (count2 > 0) {
					JOptionPane.showMessageDialog(null, "공유 게시판에 글도 삭제되었습니다.");
				}
			}

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		
		for (int i = 0; i < s.selfcodelist.size(); i++) {
			if (s.selfcodelist.get(i).getSeq() == seq) {
				s.selfcodelist.remove(i);
			}
		}

		return count;

	}

	@Override
	public int share(BBSDto dto) { 
		//개인 코드 테이블에서 쉐어 컬럼 수정
		Singleton s = Singleton.getInstance();
		String sql = "UPDATE " + s.nowMember.getID() 
		+ " SET SHA=1 WHERE SEQ=?";
		
		String sql2 = "INSERT INTO SHAR"
		+ " VALUES(SHAR_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";

		Connection conn = DBConnection.makeConnection();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int count = 0;
		//쉐어 테이블에 삽입
		
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
		//개인 코드 테이블에서 쉐어 컬럼 수정
		Singleton s = Singleton.getInstance();
		String sql = "UPDATE " + s.nowMember.getID() 
		+ " SET SHA=0 WHERE SEQ=?";
		
		String sql2 = "DELETE FROM SHAR" 
		+ " WHERE INDSEQ=? AND NICK=?";
		//쉐어테이블에서 삭제
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
