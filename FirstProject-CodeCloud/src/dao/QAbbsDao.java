package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.QAbbsDto;

public class QAbbsDao {
	// 수정부분.. 할때 넣은 코드
	private static QAbbsDao dao = new QAbbsDao();
	private QAbbsDao() {}
	public static QAbbsDao getInstance() {
		return dao;
	}
	// 삭제?
	public boolean deletebbs(int seq) {
		String sql = " UPDATE QA "+ "SET del = 1"
					+"WHERE SEQ =?";
		
		Connection conn = DBConnection.makeConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null; // DB에서 데이터를 받아주는 객체
		
		int count = 0;
		
		try {
			conn = DBConnection.makeConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, seq);
			
			count = stmt.executeUpdate();
		}catch(SQLException e) {			
			e.printStackTrace();
		} finally{
			DBClose.close(stmt, conn, null);				
		}		
		
		return count>0?true:false;
	}
	
	public List<QAbbsDto> getbbsList() {
		//쿼리 수정해야함
//	      String sql = "SELECT SEQ,nick,TITLE,dat,DEL,REF,STEP,DEPT, visible"
//	            + "FROM QA "
//	            + "WHERE SEQ > 0"
//	            + "START WITH SEQ = 0 "
//	            + "CONNECT BY PRIOR SEQ = REF "
//	            + "ORDER SIBLINGS BY STEP ASC,SEQ DESC";
//	      
	      
		String sql = "SELECT seq, nick, title, content, dat, del " + "FROM QA " + "ORDER BY seq DESC";

		Connection conn = DBConnection.makeConnection();

		PreparedStatement stmt = null;
		ResultSet rs = null; // DB에서 데이터를 받아주는 객체

		List<QAbbsDto> list = new ArrayList<QAbbsDto>();

		System.out.println(sql);

		try {
			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();

			while (rs.next()) {

				QAbbsDto dto = new QAbbsDto();

				dto.setSeq(rs.getInt("seq"));
				dto.setNick(rs.getString("nick"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setWdate(rs.getString("dat"));
				dto.setDel(rs.getInt("del"));
				//dto.setRef(rs.getInt("ref"));
				//dto.setStep(rs.getInt("step"));
				//dto.setDept(rs.getInt("dept"));
				//dto.setVisible(rs.getInt("visible"));
				list.add(dto);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(stmt, conn, rs);
		}
		return list;
	}

	// 한개의 row만 가져옴
	public QAbbsDto search(int seq) {
		String sql = "SELECT * FROM QA " + "WHERE seq=" + seq;

		Connection conn = DBConnection.makeConnection();

		PreparedStatement stmt = null;
		ResultSet rs = null; // DB에서 데이터를 받아주는 객체

		QAbbsDto dto = null;

		System.out.println(sql);

		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				dto = new QAbbsDto();

				dto.setSeq(rs.getInt("seq"));
				dto.setNick(rs.getString("nick"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setWdate(rs.getString("dat"));
				dto.setDel(rs.getInt("del"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(stmt, conn, rs);
		}
		return dto;
	}

	// 쓰기
	public boolean insert(QAbbsDto dto) {
		String sql = "INSERT INTO QA(seq, nick, title, content, dat, del,ref,step,dept,visible,answer)"
				+ "	VALUES(board_seq.nextval, ?, ?, ?, SYSDATE, ?,null,null,null,0,0)";
		Connection conn = DBConnection.makeConnection();

		PreparedStatement stmt = null;
		int count = 0;

		System.out.println(sql);
		try {
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, dto.getNick());
			stmt.setString(2, dto.getTitle());
			stmt.setString(3, dto.getContent());
			stmt.setInt(4, dto.getDel());

			count = stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBClose.close(stmt, conn, null);
		}
		return count > 0 ? true : false;
	}

	// 수정
	public boolean update(QAbbsDto dto) {
		String sql = "UPDATE QA SET title=?, content=?" + "WHERE seq = ?";
		Connection conn = DBConnection.makeConnection();
		PreparedStatement stmt = null;
		int count = 0;

		System.out.println(sql);
		try {
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, dto.getTitle());
			stmt.setString(2, dto.getContent());
			stmt.setInt(3, dto.getSeq());

			count = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(stmt, conn, null);
		}
		return count > 0 ? true : false;
	}

	// 삭제
	public int delete(int seq) {
		String sql = " DELETE QA " + "WHERE seq=" + seq;

		Connection conn = DBConnection.makeConnection();
		Statement stmt = null;

		int count = 0;

		System.out.println(sql);
		try {
			stmt = conn.createStatement();
			count = stmt.executeUpdate(sql);

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBClose.close(stmt, conn, null);
		}
		return count;
	}

	// 검색
	public List<QAbbsDto> getTitleFindList(String fStr, String fword) {
		List<QAbbsDto> list = new ArrayList<QAbbsDto>();

		String sql = " SELECT SEQ, nick, TITLE, dat " + " FROM QA ";

		if (fword.equals("제목")) {
			sql = sql + " WHERE TITLE LIKE ?";
		} else if (fword.equals("내용")) {
			sql = sql + " WHERE CONTENT LIKE ?";
		} else if (fword.equals("작성자")) {
			sql = sql + " WHERE nick = ?";
		}

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.makeConnection();

			psmt = conn.prepareStatement(sql);

			if (fword.equals("작성자")) {
				psmt.setString(1, fStr);
			} else {
				psmt.setString(1, "%" + fStr + "%");
			}

			rs = psmt.executeQuery();

			while (rs.next()) {
				int i = 1;
				
				QAbbsDto dto = new QAbbsDto(rs.getInt(i++), // SEQ
						rs.getString(i++), // nick
						rs.getString(i++), // TITLE
						null, // CONTENT
						rs.getString(i++), // WDATE
						0,0,0,0,0); // DEL

				list.add(dto);
			}

		} catch (SQLException e) {
			System.out.println("getTitleFindList fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}

		return list;
	}
}
