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
//import kosta.mvc.util.DBUtil;
import kosta.mvc.util.DBUtil;

public class RentDAOImpl implements RentDAO {
	//private static CartController cartController = new CartController();
	//private static BookDTO bookDTO = new BookDTO();
	/**
	 * 대여테이블에 insert
	 * connection 유지한채로
	 * mID, bISBN를 FK로 가진 rentDTO타입 가지고 들어온다.
	 */
	
	@Override
	public int RentInsert(Connection con, RentDTO rentDTO) throws SQLException {
		PreparedStatement ps = null;
		String sql = "INSERT INTO RENT(RNUM, RDATE, REXDATE, RSTATUS, BISBN, MID) VALUES(RNUM_SEQ.NEXTVAL, SYSDATE, SYSDATE + 7, 1, ?, ?)";
		int result = 0;
			
			try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, rentDTO.getbISBN());
			ps.setString(2, rentDTO.getmID());
			
			List<BookDTO> booksInCart = CartController.getBookDTOInCart(rentDTO.getmID());
				if(booksInCart == null) {
					throw new SQLException("책바구니에 책을 담고 다시 시도해주세요.");
				} else {
					result = ps.executeUpdate();
				}
			
			} finally {
				DBUtil.dbClose(null,ps);
			}
		return result;
	}
	
	/**
	 * 대여시 도서 대출가능여부 0으로 바꾸기
	 */
	public int[] switchBstatus(Connection con, List<BookDTO> list) throws SQLException {
		PreparedStatement ps = null;
		String sql = "UPDATE BOOK SET BSTATUS = 0 WHERE BISBN=?";
		int result [] = null;
		try {
			ps = con.prepareStatement(sql);
			for(BookDTO bookDTO : list) {
				ps.setInt(1, bookDTO.getbIsbn());
				ps.addBatch();
				ps.clearParameters();
			}
			
			result = ps.executeBatch();
		} finally {
			DBUtil.dbClose(null, ps);
		}
		
		
		return result;
	}
}
