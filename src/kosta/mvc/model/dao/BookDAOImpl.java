package kosta.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import kosta.mvc.model.dto.BookDTO;
import kosta.mvc.util.DBUtil;

public class BookDAOImpl implements BookDAO {
	private Properties proFile = DBUtil.getProFile();
	
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

	/**
	 * bISBN으로 도서 검색
	 */
	@Override
	public BookDTO bookSelectByBisbn(int bISBN) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		BookDTO bookDTO = null;
		String sql = "SELECT BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SNAME "
				+ "FROM BOOK JOIN SORT USING(SCODE) WHERE BISBN=?";
				
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, bISBN);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				bookDTO = new BookDTO(
						rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getString(7)
						);
			}
		} finally {
			DBUtil.dbClose(con, ps, rs);
		}
		return bookDTO;
	}
	
	/**
	 * bWrite으로 도서 검색
	 * */
	@Override
	public List<BookDTO> bookSelectByWriter(String bWrite) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		BookDTO bookDTO = null;
		List<BookDTO> list = new ArrayList<BookDTO>();
		String sql = "SELECT BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SNAME "
				+ "FROM BOOK JOIN SORT USING(SCODE) WHERE bWrite=?";
				
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, bWrite);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int bISBN = rs.getInt(1);
				String bNAME = rs.getString(2);
				String bWRITE =rs.getString(3);
				String bPUB = rs.getString(4);
				String bDATE = rs.getString(5);
				int bSTATUS = rs.getInt(6);
				String sNAME =rs.getString(7);
				
				BookDTO dto = new BookDTO(bISBN, bNAME, bWRITE, bPUB, bDATE, bSTATUS, sNAME);
				list.add(dto);
			}
		} finally {
			DBUtil.dbClose(con, ps, rs);
		}
		return list;
	}
	
	/**
	 * bPub으로 도서 검색
	 * */
	public List<BookDTO> bookSelectByPublisher(String bPub) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		BookDTO bookDTO = null;
		List<BookDTO> list = new ArrayList<BookDTO>();
		String sql = "SELECT BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SNAME "
				+ "FROM BOOK JOIN SORT USING(SCODE) WHERE bPub like %?%";
				
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, bPub);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int bISBN = rs.getInt(1);
				String bNAME = rs.getString(2);
				String bWRITE =rs.getString(3);
				String bPUB = rs.getString(4);
				String bDATE = rs.getString(5);
				int bSTATUS = rs.getInt(6);
				String sNAME =rs.getString(7);
				
				BookDTO dto = new BookDTO(bISBN, bNAME, bWRITE, bPUB, bDATE, bSTATUS, sNAME);
				list.add(dto);
			}
		} finally {
			DBUtil.dbClose(con, ps, rs);
		}
		return list;
	}
	
	
	/**
	 * sName으로 도서 검색
	 * */

	@Override
	public List<BookDTO> bookSelectBySname(String sName) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		BookDTO bookDTO = null;
		List<BookDTO> list = new ArrayList<BookDTO>();
		String sql = proFile.getProperty("book.SelectBySname");
		
		//String sql = "SELECT BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SNAME "
			//	+ "FROM BOOK JOIN SORT USING(SCODE) WHERE sName=?";
				
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, sName);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int bISBN = rs.getInt(1);
				String bNAME = rs.getString(2);
				String bWRITE =rs.getString(3);
				String bPUB = rs.getString(4);
				String bDATE = rs.getString(5);
				int bSTATUS = rs.getInt(6);
				String sNAME =rs.getString(7);
				
				BookDTO dto = new BookDTO(bISBN, bNAME, bWRITE, bPUB, bDATE, bSTATUS, sNAME);
				list.add(dto);
			}
			/*if(rs.next()) {
				bookDTO = new BookDTO(
						rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getString(7)
						);
			} */
		} finally {
			DBUtil.dbClose(con, ps, rs);
		}
		return list;
	}



	/**
	 * bName으로 도서 검색
	 * */
	
	@Override
	public BookDTO bookSelectByBname(String bName) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		BookDTO bookDTO = null;
		String sql = proFile.getProperty("book.selectBybName");
		//String sql = "SELECT BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SNAME "
		//		+ "FROM BOOK JOIN SORT USING(SCODE) WHERE bName=?";
				
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, bName);
			rs = ps.executeQuery();
			while(rs.next()) {
				 rs.getInt(1);
				 rs.getString(2);
				rs.getString(3);
				rs.getString(4);
				rs.getString(5);
				rs.getInt(6);
				rs.getString(7);
							
			}
		/*	if(rs.next()) {
				bookDTO = new BookDTO(
						rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getString(7)
						);
			}
			*/
		} finally {
			DBUtil.dbClose(con, ps, rs);
		}
		return bookDTO;
	}




	
	
}
