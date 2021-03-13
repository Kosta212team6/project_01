package kosta.mvc.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kosta.mvc.model.dto.BookDTO;
import kosta.mvc.util.DBUtil;

public interface BookService {
	
	/**
	 * 도서추가
	 */
	void InsertBook(BookDTO bookDTO) throws SQLException;
	
	/**
	 * bISBN으로 도서 검색
	 */
	public BookDTO bookSelectByBisbn(int bISBN) throws SQLException;
	
}
