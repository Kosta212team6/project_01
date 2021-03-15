package kosta.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kosta.mvc.model.dto.MemberDTO;
import kosta.mvc.util.DBUtil;

public class NotifyDAOImpl implements NotifyDAO {
	/**
	 * 메세지 가져오는 메소드
	 */
	@Override
	public String printNmessage(String mID) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT NCONTENT FROM MEMBER JOIN NOTIFY USING(NCODE) WHERE MID=?";
		String message = null;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, mID);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				message = rs.getString(1);
			}
		} finally {
			DBUtil.dbClose(con, ps, rs);
		}
		
		return message;
	}
	

}
