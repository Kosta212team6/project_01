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
	
	/**
	 * 대망의 반납...! : 반납 테이블에 기록
	 */
	@Override
	public int returnBooks(String mID) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = "INSERT INTO RETURN(RNUM, RTDATE, BISBN) VALUES(?, SYSDATE, ?)";
		List<RentDTO> list = null;
		int result = 0;
		
		try {
			con = DBUtil.getConnection();
			con.setAutoCommit(false);
			
			list = searchRentHistory(con, mID);
			
			for(RentDTO rentDTO:list) {
				ps = con.prepareStatement(sql);
				
				ps.setInt(1, rentDTO.getrNum());
				ps.setInt(2, rentDTO.getbISBN());
				
				result = ps.executeUpdate();
				if(result==0) {
					con.rollback();
					throw new SQLException("반납에 실패하였습니다");
				}
				switchRentStatusFalse(con, rentDTO);
				
				String rsvID=checkBookRsv(con, rentDTO.getbISBN());
				if(rsvID==null) {
					result=switchBStatusTrue(con, rentDTO.getbISBN());
					if(result==0) {
						con.rollback();
						throw new SQLException("책 상태 변경 실패");
					}

				}
				else {   
					result=switchNotifyCode(con, rsvID);
					if(result==0) {
						con.rollback(); 
						throw new SQLException("알림 메세지 변경 실패");
					}
					result=rentRsvBook(con, rentDTO.getbISBN(), rsvID);
					if(result==0) {
						con.rollback(); 
						throw new SQLException("예약도서의 대여 실패");
					}
					result=deleteDoneRsv(con, rentDTO.getbISBN());
					if(result==0) { 
						con.rollback(); 
						throw new SQLException("예약내역 삭제 실패");
					}
				}
			}
			con.commit();
		} finally {
			DBUtil.dbClose(con, ps);
		}
		
		return result;
	}
	
	/**
	 * 커넥션 유지하면서 렌트기록 찾아오는 메소드
	 */
	public List<RentDTO> searchRentHistory(Connection con,String mID) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT RNUM, RDATE, REXDATE, RSTATUS, BISBN, MID "
				+ "FROM RENT WHERE MID=?";
		List<RentDTO> list = new ArrayList<RentDTO>();
		
		try {
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
			DBUtil.dbClose(null, ps, rs);
		}
		return list;
	}

	/**
	 * 대여상태 false로 변경
	 */
	public int switchRentStatusFalse(Connection con, RentDTO rentDTO) throws SQLException {
		PreparedStatement ps = null;
		
		String sql = "UPDATE RENT SET RSTATUS=0 WHERE BISBN=?";
		int result = 0;
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, rentDTO.getbISBN());
			
			result = ps.executeUpdate();
			
		} finally {
			DBUtil.dbClose(null, ps);
		}
		return result;
	}

	/**
	 * 예약된 도서가 있는지 확인 : 날짜가 빠른 순서대로 정렬하여 1순위 컬럼만 가져오기
	 */
	public String checkBookRsv(Connection con, int bISBN) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT MID FROM RSV WHERE BISBN=? ORDER BY RSVDATE";
		String mID = null;
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, bISBN);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				mID = rs.getString(1);
			}
		} finally {
			DBUtil.dbClose(null, ps, rs);
		}
		return mID;
	}

	/**
	 * 도서 상태 업데이트
	 */
	public int switchBStatusTrue(Connection con, int bISBN) throws SQLException {
		PreparedStatement ps = null;
		
		String sql = "UPDATE BOOK SET BSTATUS=1 WHERE BISBN=?";
		int result = 0;
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, bISBN); 
			
			result = ps.executeUpdate(); 
		} finally {
			DBUtil.dbClose(null, ps);
		}
		return result;
	}
	
	/**
	 * 예약된 도서가 있는 경우 회원 알림코드를 20으로 변경하기
	 */
	public int switchNotifyCode(Connection con, String mID) throws SQLException {
		PreparedStatement ps = null;
		
		String sql = "UPDATE MEMBER SET NCODE=20 WHERE MID=?";
		int result = 0;
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, mID);
			
			result = ps.executeUpdate();
		} finally {
			DBUtil.dbClose(null, ps);
		}
		return result;
	}
	
	/**
	 * 예약 도서를 바로 대출로 변경하기 
	 */
	public int rentRsvBook(Connection con, int bISBN, String mID) throws SQLException {
		PreparedStatement ps = null;
		
		String sql = "INSERT INTO RENT(RNUM, RDATE, REXDATE, RSTATUS, BISBN, MID) "
				+ "VALUES(RNUM_SEQ.NEXTVAL, SYSDATE, SYSDATE + 7, 1, ?, ?)";
		int result = 0;
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, bISBN);
			ps.setString(2, mID);
			
			result = ps.executeUpdate();
		} finally {
			DBUtil.dbClose(null, ps);
		}
		
		return result;
	}

	/**
	 * 예약 레코드 삭제
	 */
	public int deleteDoneRsv(Connection con, int bISBN) throws SQLException {
		PreparedStatement ps = null;
		
		String sql = "DELETE FROM RSV WHERE BISBN=?";
		int result = 0;
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, bISBN);
			
			result = ps.executeUpdate();
		} finally {
			DBUtil.dbClose(null, ps);
		}
		return result;
	}
	
	public static void main(String[] args)  {
		ReturnDAOImpl returnDAO = new ReturnDAOImpl();
		try {
//			RentDTO rentDTO = returnDAO.getRentNumberByISBN(165210991);
//			System.out.println(rentDTO);
//			int result = returnDAO.insertReturnHistory(rentDTO);
//			System.out.println(result);
//			int result2 = returnDAO.switchRentStatusFalse(rentDTO);
//			System.out.println(result2);
//			String mID = returnDAO.checkBookRsv(963193489);
//			System.out.println(mID);
//			int result=returnDAO.switchBStatusTrue(963193489);
//			System.out.println(result);
			returnDAO.returnBooks("choi");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
