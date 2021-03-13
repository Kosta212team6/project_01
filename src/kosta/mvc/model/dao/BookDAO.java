package kosta.mvc.model.dao;

import java.sql.SQLException;

import kosta.mvc.model.dto.BookDTO;

public interface BookDAO {

	int InsertBook(BookDTO bookDTO) throws SQLException;

	int UpdateBookISBN(int bISBN, int newISBN) throws SQLException;

	int UpdateBookName(BookDTO bookDTO) throws SQLException;

	int UpdateBookWrite(BookDTO bookDTO) throws SQLException;

	int UpdateBookPub(BookDTO bookDTO) throws SQLException;

	int UpdateBookDate(BookDTO bookDTO) throws SQLException;

	int UpdateBookCode(BookDTO bookDTO) throws SQLException;

	int DeleteBook(int bISBN) throws SQLException;
}
