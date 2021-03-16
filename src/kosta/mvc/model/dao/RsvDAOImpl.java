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

	// INSERT INTO RSV(RSVNUM, RSVDATE, MID, BISBN) VALUES (RSVNUM_SEQ.NEXTVAL, SYSDATE, ?, ?)
	@Override
	public int insertRsv(String mID, int bISBN) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = "INSERT INTO RSV(RSVNUM, RSVDATE, MID, BISBN) VALUES (RSVNUM_SEQ.NEXTVAL, SYSDATE, ?, ?)";
		boolean status = false;
		int result = 0;
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			status = isBookRented(con, bISBN);
			if(status==false) {
				throw new SQLException("이 도서는 현재 대출 가능합니다");
			}
			ps.setString(1, mID);
			ps.setInt(2, bISBN);
			
			result = ps.executeUpdate();
		} finally {
			DBUtil.dbClose(con, ps);
		}
		return result;
	}

	/**
	 * 도서의 대여상태가 대출중임을 확인하는 메소드
	 */
	// SELECT BSTATUS FROM BOOK WHERE BISBN=? AND BSTATUS=0;
	public boolean isBookRented(Connection con, int bISBN) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT BSTATUS FROM BOOK WHERE BISBN=? AND BSTATUS=0";
		int bStatus = 0;
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, bISBN);
			if(rs.next()) {
				rs.getInt(bStatus);
				return true;
			}
			else {
				return false;
			}
		} finally {
			DBUtil.dbClose(null, ps, rs);
		}
	}
}
