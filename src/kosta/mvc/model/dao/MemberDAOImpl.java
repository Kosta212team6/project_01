package kosta.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kosta.mvc.model.dto.MemberDTO;
import kosta.mvc.util.DBUtil;

public class MemberDAOImpl implements MemberDAO {

	// select mid, mpwd, mstatus from Member where mID='choi' and mPwd=2222;
	@Override
	public MemberDTO login(String mID, String mPwd) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "select mid, mpwd, mstatus from Member where mID=? and mPwd=?";
		MemberDTO memberDTO = null;
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, mID);
			ps.setString(2, mPwd);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				memberDTO = new MemberDTO(rs.getString(1), rs.getString(2), rs.getInt(3));
			}
			
		} finally {
			DBUtil.dbClose(con, ps, rs);
		}
		
		return memberDTO;
	}

}
