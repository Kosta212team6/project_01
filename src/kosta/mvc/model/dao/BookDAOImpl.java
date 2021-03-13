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
				+ "VALUES(?, ?, ?, ?, ?, 1, ?)";
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

	@Override
	public int UpdateBookISBN(int bISBN, int newISBN) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = "UPDATE BOOK SET BISBN=? WHERE BISBN=?";
		int result = 0;
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, newISBN);
			ps.setInt(2, bISBN);
			
			result = ps.executeUpdate();
			
		} finally {
			DBUtil.dbClose(con, ps);
		}
		
		return result;
	}

	@Override
	public int UpdateBookName(BookDTO bookDTO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = "UPDATE BOOK SET BNAME=? WHERE BISBN=?";
		int result = 0;
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, bookDTO.getbName());
			ps.setInt(2, bookDTO.getbIsbn());
			
			result = ps.executeUpdate();
			
		} finally {
			DBUtil.dbClose(con, ps);
		}
		
		return result;
	}

	@Override
	public int UpdateBookWrite(BookDTO bookDTO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = "UPDATE BOOK SET BWRITE=? WHERE BISBN=?";
		int result = 0;
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, bookDTO.getbWrite());
			ps.setInt(2, bookDTO.getbIsbn());
			
			result = ps.executeUpdate();
			
		} finally {
			DBUtil.dbClose(con, ps);
		}
		return result;
	}

	@Override
	public int UpdateBookPub(BookDTO bookDTO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = "UPDATE BOOK SET BPUB=? WHERE BISBN=?";
		int result = 0;
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, bookDTO.getbPub());
			ps.setInt(2, bookDTO.getbIsbn());
			
			result = ps.executeUpdate();
			
		} finally {
			DBUtil.dbClose(con, ps);
		}
		return result;
	}

	@Override
	public int UpdateBookDate(BookDTO bookDTO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = "UPDATE BOOK SET BDATE=? WHERE BISBN=?";
		int result = 0;
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, bookDTO.getbDate());
			ps.setInt(2, bookDTO.getbIsbn());
			
			result = ps.executeUpdate();
			
		} finally {
			DBUtil.dbClose(con, ps);
		}
		return result;
	}

	@Override
	public int UpdateBookCode(BookDTO bookDTO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = "UPDATE BOOK SET SCODE=? WHERE BISBN=?";
		int result = 0;
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, bookDTO.getsCode());
			ps.setInt(2, bookDTO.getbIsbn());
			
			result = ps.executeUpdate();
			
		} finally {
			DBUtil.dbClose(con, ps);
		}
		return result;
	}

	@Override
	public int DeleteBook(int bISBN) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = "DELETE FROM BOOK WHERE BISBN=?";
		int result = 0;
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, bISBN);
			
			result = ps.executeUpdate();
			
		} finally {
			DBUtil.dbClose(con, ps);
		}
		return result;
	}

}
