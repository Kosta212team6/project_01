package kosta.mvc.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kosta.mvc.exception.NotFoundException;
import kosta.mvc.model.dao.BookDAO;
import kosta.mvc.model.dao.BookDAOImpl;
import kosta.mvc.model.dto.BookDTO;
import kosta.mvc.util.DBUtil;

public class BookServiceImpl implements BookService {
	
	private BookDAO bookDAO = new BookDAOImpl();

	@Override
	public void InsertBook(BookDTO bookDTO) throws SQLException {
		int result = bookDAO.InsertBook(bookDTO);
		if(result==0) {
			throw new SQLException("오류 : 등록되지 않았습니다");
		}
	}

	@Override
	public void UpdateBookISBN(int bISBN, int newISBN) throws SQLException {
		int result = bookDAO.UpdateBookISBN(bISBN, newISBN);
		if(result==0) {
			throw new SQLException("오류 : ISBN이 수정되지 않았습니다");
		}
	}

	@Override
	public void UpdateBookName(BookDTO bookDTO) throws SQLException {
		int result = bookDAO.UpdateBookName(bookDTO);
		if(result==0) {
			throw new SQLException("오류 : ISBN이 수정되지 않았습니다");
		}		
	}

	@Override
	public void UpdateBookWrite(BookDTO bookDTO) throws SQLException {
		int result = bookDAO.UpdateBookWrite(bookDTO);
		if(result==0) {
			throw new SQLException("오류 : 저자가 수정되지 않았습니다");
		}		
	}

	@Override
	public void UpdateBookPub(BookDTO bookDTO) throws SQLException {
		int result = bookDAO.UpdateBookPub(bookDTO);
		if(result==0) {
			throw new SQLException("오류 : 출판사가 수정되지 않았습니다");
		}				
	}

	@Override
	public void UpdateBookDate(BookDTO bookDTO) throws SQLException {
		int result = bookDAO.UpdateBookDate(bookDTO);
		if(result==0) {
			throw new SQLException("오류 : 발행년월일이 수정되지 않았습니다");
		}				
	}

	@Override
	public void UpdateBookCode(BookDTO bookDTO) throws SQLException {
		int result = bookDAO.UpdateBookCode(bookDTO);
		if(result==0) {
			throw new SQLException("오류 : 분류코드가 수정되지 않았습니다");
		}				
	}

	@Override
	public void DeleteBook(int bISBN) throws SQLException {
		int result = bookDAO.DeleteBook(bISBN);
		if(result==0) {
			throw new SQLException("오류 : 도서정보가 삭제되지 않았습니다");
		}
		
	}

	@Override
	public BookDTO bookSelectByBisbn(int bISBN) throws SQLException, NotFoundException {
		BookDTO bookDTO = bookDAO.bookSelectByBisbn(bISBN);
		if(bookDTO==null) {
			throw new NotFoundException("해당 ISBN에 해당하는 책이 없습니다");
		}
		return bookDTO;
	}
	
	@Override
	public BookDTO bookSelectByWriter(String bWrite) throws SQLException, NotFoundException {
		BookDTO bookDTO = bookDAO.bookSelectByWriter(bWrite);
		if(bookDTO==null) {
			throw new NotFoundException("해당 저자에 해당하는 책이 없습니다");
		}
		return bookDTO;
	}

}
