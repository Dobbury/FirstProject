package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.BBSDto;
import dto.QAbbsDto;
import dto.ShareDto;
import singleton.Singleton;

public class ShareDao {

	public static List<ShareDto> getbbsList() {
		
		List<ShareDto> list = new ArrayList<>();
		
		//Singleton s = Singleton.getInstance();
		String sql = "SELECT * FROM SHAR";
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.makeConnection();
		
			psmt = conn.prepareStatement(sql);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				int i = 1;
				
				ShareDto dto = new ShareDto(rs.getInt(i++), 
										rs.getInt(i++), 
										rs.getString(i++), 
										rs.getString(i++), 
										rs.getString(i++), 
										rs.getInt(i++),
										rs.getInt(i++),
										rs.getString(i++));
				
				list.add(dto);				
			}				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);			
		}
		return list;
	}
	
	public static List<Integer> getlikeseqlist(){
		//이 유저가 추천한 게시글 목록 불러오기
		Singleton s = Singleton.getInstance();
		String idtmp = s.nowMember.getID();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<Integer> list = new ArrayList<>();
		
		//쉐어테이블 추천수 올림
		String sql = "SELECT * FROM " + idtmp+"_LIKED ";
		
		try {
			conn = DBConnection.makeConnection();
			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getInt(1));				
			}
		}
		catch(Exception e){
			System.out.println("에러났어여");
		}
		return list;
		
	}
	
	public static void clicklike(String id, String nick, int indseq, int seq) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		//쉐어테이블 추천수 올림
		String sql = "UPDATE SHAR"
		+ " SET LIKED=LIKED+1 WHERE SEQ=?";
		
		//멤버 라이크 테이블에 시퀀스 넘버 추가// 좋아하는 게시글에 각각의 시퀀스 넘버 부여 > 중복을 확인할수 있음 
		String sql2 = "INSERT INTO " + id +"_LIKED"
		+ " VALUES(?)";
		
		//원래 글 추천수 올림 // 누군가 공유게에서 추천을 눌렀는데   개인게의 추천버튼도 카운트 되는것 
		
		String sql3 = "SELECT ID FROM MEMBER"
		+ " WHERE NICK=?";

		try {
			conn = DBConnection.makeConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			psmt.executeQuery();
			
			psmt = conn.prepareStatement(sql2);
			psmt.setInt(1, seq);
			psmt.executeQuery();
			
			psmt = conn.prepareStatement(sql3);
			psmt.setString(1, nick);
			rs = psmt.executeQuery();
			
			rs.next();
			String idtmp = rs.getString(1);
			
			String sql4 = "UPDATE " + idtmp
					+ " SET LIKED=LIKED+1 WHERE SEQ=?";
			
			System.out.println(sql4);
			
			psmt = conn.prepareStatement(sql4);
			psmt.setInt(1, indseq);
			rs = psmt.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);			
		}
		
	}
	
	public static void clickunlike(String id, String nick, int indseq, int seq) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		//쉐어테이블 추천수 올림
		String sql = "UPDATE SHAR"
		+ " SET LIKED=LIKED-1 WHERE SEQ=?";
		
		//멤버 라이크 테이블에 시퀀스 넘버 추가// 좋아하는 게시글에 각각의 시퀀스 넘버 부여 > 중복을 확인할수 있음 
		String sql2 = "DELETE FROM" + id +"_LIKED"
		+ " WHERE LIKEDSHARESEQ="+seq;
		
		//원래 글 추천수 올림 // 누군가 공유게에서 추천을 눌렀는데   개인게의 추천버튼도 카운트 되는것 
		
		String sql3 = "SELECT ID FROM MEMBER"
		+ " WHERE NICK=?";

		try {
			conn = DBConnection.makeConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			psmt.executeQuery();
			
			psmt = conn.prepareStatement(sql2);
			
			
			psmt = conn.prepareStatement(sql3);
			psmt.setString(1, nick);
			rs = psmt.executeQuery();
			
			rs.next();
			String idtmp = rs.getString(1);
			
			String sql4 = "UPDATE " + idtmp
					+ " SET LIKED=LIKED-1 WHERE SEQ=?";
			
			psmt = conn.prepareStatement(sql4);
			psmt.setInt(1, indseq);
			rs = psmt.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);			
		}
	}
	
	public static void codefork(ShareDto dto) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		Singleton s = Singleton.getInstance();
		
		
		
		
		//개인 게시판에 추가
		String sql = "INSERT INTO "+ s.nowMember.getID() 
				+ " VALUES(" + s.nowMember.getID()+ "_SEQ.NEXTVAL, ?, ?, 0, 0, 0, ?)";
		
		String SQL = "SELECT " + s.nowMember.getID()+ "_SEQ.CURRVAL FROM DUAL";
		
		//공유게시판에서 포크수 업데이트 하고 화면 바꿔주기.
		String sql2 = "UPDATE SHAR"
				+ " SET FORK=FORK+1 WHERE SEQ=?";
		
		//공유한 사람 게시판 포크수 없데이트전 아이디 검색
		//글을 공유한 사람의 테이블을 들어가려면 아이디가 필요한데 쉐어 게시판에서는 닉네임밖에 없어서
		String sql3 = "SELECT ID FROM MEMBER"
				+ " WHERE NICK=?";
		int indseq2 = 0;
		try {
			conn = DBConnection.makeConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getLang());
			psmt.executeQuery();
			
			psmt = conn.prepareStatement(SQL);
			rs = psmt.executeQuery();
			rs.next();
			
			indseq2 = rs.getInt(1);
			
			psmt = conn.prepareStatement(sql2);
			psmt.setInt(1, dto.getSeq());
			psmt.executeQuery();
			
			psmt = conn.prepareStatement(sql3);
			psmt.setString(1, dto.getNick());
			rs = psmt.executeQuery();
			
			rs.next();
			String idtmp = rs.getString(1);
			
			//공유한 사람 게시판 포크수 업데이트
			
			String sql4 = "UPDATE " + idtmp
					+ " SET FORK=FORK+1 WHERE SEQ=?";
			
			psmt = conn.prepareStatement(sql4);
			psmt.setInt(1, dto.getIndseq());
			psmt.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);			
		}
		
		s.selfcodelist.add(new BBSDto(indseq2, dto.getTitle(), dto.getContent(), 0, 0, 0, dto.getLang()));
	}
	
	public static Object[][] getLikeList(){
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		Object[][] rowData = new Object[5][3];
		
		String sql = "SELECT TITLE, LIKED FROM SHAR ORDER BY LIKED DESC";
		
		try {
			conn = DBConnection.makeConnection();
			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();
			
			for (int i = 0; i < rowData.length; i++) {
				if(rs.next()) {;
				rowData[i][0] = i+1;
				rowData[i][1] = rs.getString(1);
				rowData[i][2] = rs.getInt(2);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);			
		}
		return rowData;
	}
		
	public static Object[][] getForkList(){
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		Object[][] rowData = new Object[5][3];
		
		String sql = "SELECT TITLE, FORK FROM SHAR ORDER BY FORK DESC";
		
		try {
			conn = DBConnection.makeConnection();
			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();
			
			for (int i = 0; i < rowData.length; i++) {
				if(rs.next()) {;
				rowData[i][0] = i+1;
				rowData[i][1] = rs.getString(1);
				rowData[i][2] = rs.getInt(2);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);			
		}
		return rowData;
	}
	
	
	// 검색
		public static List<ShareDto> getTitleFindList(String fStr, String fword) {
			List<ShareDto> list = new ArrayList<ShareDto>();

			
			String sql = " SELECT SEQ,  TITLE , CONT, NICK , LAN " + " FROM SHAR ";

			if (fword.equals("제목")) {
				sql = sql + " WHERE TITLE LIKE ?";
			} else if (fword.equals("내용")) {
				sql = sql + " WHERE CONT LIKE ?";
			} else if (fword.equals("닉네임")) {
				sql = sql + " WHERE NICK = ?";
			} else if (fword.equals("언어")) {
				sql = sql + " WHERE LAN = ?";
			}
				

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			try {
				conn = DBConnection.makeConnection();

				psmt = conn.prepareStatement(sql);

				if (fword.equals("닉네임")) {
					psmt.setString(1, fStr);
				} else {
					psmt.setString(1, "%" + fStr + "%");
				}

				rs = psmt.executeQuery();

				while (rs.next()) {
					
					
		//SELECT SEQ,  TITLE , CONT, NICK , LAN " + " FROM SHAR "; 
		 ShareDto dto = new ShareDto(rs.getInt(1), //seq
										0, //indseq
										rs.getString(3), // nick 
										rs.getString(4), // String title
										rs.getString(5),// String content
										0, //int liked, 
										0, //int fork, 
										rs.getString(8) //String lang 
										);
											
								 
										
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
