package kosta.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kosta.mvc.model.dto.RentDTO;
//import kosta.mvc.util.DBUtil;
import kosta.mvc.util.DBUtil;

public class RentDAOImpl implements RentDAO {

	@Override
	public int RentInsert(Connection con, RentDTO rentDTO) throws SQLException {
		PreparedStatement ps = null;
		String sql = "INSERT INTO RENT(RNUM, RDATE, REXDATE, RSTATUS, BISBN, MID) VALUES(RNUM_SEQ.NEXTVAL, SYSDATE, SYSDATE + 7, 1, ?, ?)";
		int result = 0;
			
			try {
			ps = con.prepareStatement(sql);
			
			
			} finally {
				DBUtil.dbClose(null,ps,null);
			}
		return result;
	}

	@Override
	public List<RentDTO> selectRentByUserId(String mID) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<RentDTO> list = new ArrayList<RentDTO>();
		String sql = "select * from ";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
		 
			while(rs.next()) {
			 
			}
		 
		 
		} finally {
			DBUtil.dbClose(con, ps, rs);
		}
		
		return list;
	}

}
