package kosta.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kosta.mvc.model.dto.RentDTO;
import kosta.mvc.model.dto.ReturnDTO;
import kosta.mvc.model.dto.RsvDTO;
import kosta.mvc.util.DBUtil;
import sun.security.action.GetIntegerAction;

public class ReturnDAOImpl implements ReturnDAO {

	@Override
	public List<RentDTO> searchRentHistory(String mID) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT RNUM, RDATE, REXDATE, RSTATUS, BISBN, MID "
				+ "FROM RENT WHERE MID=?";
		List<RentDTO> list = new ArrayList<RentDTO>();
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, mID);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				int rNum = rs.getInt(1);
				String rDate = rs.getString(2);
				String rExDate = rs.getString(3);
				int rStatus = rs.getInt(4);
				int bISBN = rs.getInt(5);
				RentDTO rentDTO = new RentDTO(rNum, rDate, rExDate, rStatus, bISBN, mID);
				list.add(rentDTO);
			}
			
		} finally {
			DBUtil.dbClose(con, ps, rs);
		}
		return list;
	}

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
	
	/**
	 * 대망의 반납...!
	 */
	@Override
	public void returnBooks(String mID, int bISBN) throws SQLException {
		
		
	}

	/**
	 * 대여테이블에서 bISBN으로 rnum 파라미터 받기
	 */
	public RentDTO getRentNumberByISBN(int bISBN) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT RNUM FROM RENT WHERE BISBN=?";
		RentDTO rentDTO = null;
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, bISBN);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				int rnum=rs.getInt(1);
				rentDTO=new RentDTO(rnum, bISBN);
			}
			
		} finally {
			DBUtil.dbClose(con, ps, rs);
		}
		return rentDTO;
	}
	
	/**
	 * 반납테이블에 기록
	 */
	public int insertReturnHistory(RentDTO rentDTO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = "INSERT INTO RETURN(RNUM, RTDATE, BISBN) VALUES(?, SYSDATE, ?)";
		int result = 0;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, rentDTO.getrNum());
			ps.setInt(2, rentDTO.getbISBN());
			
			result = ps.executeUpdate();
			
		} finally {
			DBUtil.dbClose(con, ps);
		}
		
		return result;
	}

	/**
	 * 대여상태 false로 변경
	 */
	public int switchRentStatusFalse(RentDTO rentDTO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = "UPDATE RENT SET RSTATUS=0 WHERE BISBN=?";
		int result = 0;
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, rentDTO.getbISBN());
			
			result = ps.executeUpdate();
			
		} finally {
			DBUtil.dbClose(con, ps);
		}
		return result;
	}


	public RsvDTO checkBookRsv(int bISBN) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	public int switchBStatusTrue(Connection con, int bISBN) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public static void main(String[] args)  {
		ReturnDAOImpl returnDAO = new ReturnDAOImpl();
		try {
			RentDTO rentDTO = returnDAO.getRentNumberByISBN(165210991);
			System.out.println(rentDTO);
			int result = returnDAO.insertReturnHistory(rentDTO);
			System.out.println(result);
			int result2 = returnDAO.switchRentStatusFalse(rentDTO);
			System.out.println(result2);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
