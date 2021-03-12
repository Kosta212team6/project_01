package kosta.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import kosta.mvc.model.dto.BookDTO;
import kosta.mvc.util.DBUtil;

public class BookDAOImpl implements BookDAO {
	
	// INSERT INTO BOOK(BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES(?, ?, ?, ?, ?, 1, ?);
	@Override
	public int InsertBook(BookDTO bookDTO) throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = "INSERT INTO BOOK(BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) "
				+ "VALUES(?, ?, ?, ?, ?, 1, ?);";
		int result = 0;
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, bookDTO.getbIsbn());
			ps.setString(2, bookDTO.getbName());
			ps.setString(3, bookDTO.getbWrite());
			ps.setString(4, bookDTO.getbPub());
			ps.setString(5, bookDTO.getbDate());
			ps.setInt(6, bookDTO.getsCode());
			
			result = ps.executeUpdate();
			
		} finally { 
			DBUtil.dbClose(con, ps);
		}
		
		return result;
	}
	
	public BookDTO bookSelectByBisbn(int bISBN) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		BookDTO bookDTO = null;
		String sql = "select * from book where bisbn = ?";
				
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, bISBN);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				bookDTO = new BookDTO(rs.getInt(1));
			}
		} finally {
			DBUtil.dbClose(con, ps, rs);
		}
		
		return bookDTO;
	}

}
