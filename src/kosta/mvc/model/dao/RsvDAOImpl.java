package kosta.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kosta.mvc.model.dto.RsvDTO;
import kosta.mvc.util.DBUtil;

public class RsvDAOImpl implements RsvDAO {

	@Override
	public List<RsvDTO> searchRsvHistory(String mID) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT RSVNUM, RSVDATE, MID, BISBN, BNAME, BSTATUS "
				+ "FROM RSV JOIN BOOK USING(BISBN) WHERE MID=?";
		List<RsvDTO> list = new ArrayList<RsvDTO>();
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, mID);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				int rsvNum = rs.getInt(1);
				String rsvDate = rs.getString(2);
				int bISBN = rs.getInt(4);
				String bName = rs.getString(5);
				int bStatus = rs.getInt(6);
				RsvDTO rsvDTO = new RsvDTO(rsvNum, rsvDate, mID, bISBN, bName, bStatus);
				list.add(rsvDTO);
			}
			
		} finally {
			DBUtil.dbClose(con, ps, rs);
		}
		return list;
	}
}
