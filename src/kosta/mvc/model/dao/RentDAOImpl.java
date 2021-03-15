package kosta.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kosta.mvc.controller.CartController;
import kosta.mvc.model.dto.BookDTO;
import kosta.mvc.model.dto.RentDTO;
import kosta.mvc.session.SessionSet;
//import kosta.mvc.util.DBUtil;
import kosta.mvc.util.DBUtil;
import kosta.mvc.view.FailView;

public class RentDAOImpl implements RentDAO {
	//private static CartController cartController = new CartController();
	//private static BookDTO bookDTO = new BookDTO();
	private static SessionSet ss = SessionSet.getInstance();
	/**
	 * 책바구니에 있는 도서목록 일괄대여
	 */
	@Override
	public int insertRents(List<BookDTO> list, String mID) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO RENT(RNUM, RDATE, REXDATE, RSTATUS, BISBN, MID) VALUES(RNUM_SEQ.NEXTVAL, SYSDATE, SYSDATE + 7, 1, ?, ?)";
		int result = 0;
			
			try {
				con = DBUtil.getConnection();
				con.setAutoCommit(false);
				
				for(BookDTO bookDTO :list) {
					ps = con.prepareStatement(sql);
					ps.setInt(1, bookDTO.getbISBN());
					ps.setString(2, mID);
					
					result = ps.executeUpdate();
					if(result==0) {
						con.rollback();
						throw new SQLException("대여에 실패하였습니다.");
					}
					
					switchBstatus(con, bookDTO.getbISBN());
					con.commit();
				}
			
			} finally {
				DBUtil.dbClose(con,ps);
			}
		return result;
	}
	
	/**
	 * 대여시 도서 대출가능여부 0으로 바꾸기
	 */
	@Override
	public int switchBstatus(Connection con, int bISBN) throws SQLException {
		PreparedStatement ps = null;
		String sql = "UPDATE BOOK SET BSTATUS = 0 WHERE BISBN=?";
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
	 * 대여중인 도서목록 출력
	 * */
	@Override
	public List<RentDTO> printRentBookList(String mID) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<RentDTO> list = new ArrayList<RentDTO>();
		String sql = "select rNum, rDate, rExDate, rstatus, bISBN, mID, bName from book natural join rent where mid = ? and rstatus = 1 order by rNum asc";
		
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
				String ID= rs.getString(6);
				String bName= rs.getString(7);
				
				RentDTO dto = new RentDTO(rNum, rDate, rExDate, rStatus, bISBN, ID,bName);
				list.add(dto);
			}
		}finally {
			DBUtil.dbClose(con, ps, rs);
		}
		return list;
	}

}
