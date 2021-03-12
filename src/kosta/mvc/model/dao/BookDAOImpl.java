package kosta.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

}
