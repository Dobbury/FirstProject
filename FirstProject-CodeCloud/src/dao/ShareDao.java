package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.QAbbsDto;
import dto.ShareDto;

public class ShareDao {

	public List<ShareDao> getbbsList() {
	
		List<ShareDao> list = new ArrayList<ShareDao>();	
	
	
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
