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
	/**
	 *DB상에서는 bStatus가 1인데 책바구니 담을때 튕겼다!!! >> 이건 무슨 이슈일까 ?!?!?!?!!?!!?!?!!??
	 * 
	 */
	private static SessionSet ss = SessionSet.getInstance();
	
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
					ps.setInt(1, bookDTO.getbIsbn());
					ps.setString(2, mID);
					
					result = ps.executeUpdate();
					if(result==0) {
						con.rollback();
						throw new SQLException("대여에 실패하였습니다.");
					}
					
					switchBstatus(con, bookDTO.getbIsbn());
				}
			
			} finally {
				con.commit();
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

}
