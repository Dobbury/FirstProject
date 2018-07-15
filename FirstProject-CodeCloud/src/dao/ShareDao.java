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
	
	
	// 한개의 row만 가져옴
		public   ShareDto search(){
			
			
/*			
			String sql = "SELECT * FROM QA말고 개인게시판!! " + "WHERE seq=" + seq;

			Connection conn = DBConnection.makeConnection();

			PreparedStatement stmt = null;
			ResultSet rs = null; // DB에서 데이터를 받아주는 객체

			ShareDto dto = null;

			System.out.println(sql);

			try {
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();

				if (rs.next()) {
					dto = new ShareDto();

					dto.setSeq(rs.getInt("seq"));
					dto.setNick(rs.getString("nick"));
					dto.setLang(rs.getString("lang"));
					dto.setTitle(rs.getString("title"));
					dto.setContent(rs.getString("cotent"));
					dto.setLiked(rs.getInt("liked"));
					dto.setFork(rs.getInt("fork"));
					
					
					
					
					
			 
			 
					
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBClose.close(stmt, conn, rs);
			}
*/	
			 
			return null;
		}
	 
}
