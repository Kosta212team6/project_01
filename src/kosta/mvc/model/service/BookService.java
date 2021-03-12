package kosta.mvc.model.service;

import java.sql.SQLException;

import kosta.mvc.model.dto.BookDTO;

public interface BookService {
	
	/**
	 * 도서추가
	 */
	void InsertBook(BookDTO bookDTO) throws SQLException;

}
