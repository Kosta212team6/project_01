package kosta.mvc.model.dao;

import java.sql.SQLException;
import java.util.List;

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
	
	public BookDTO bookSelectByBisbn(int bISBN) throws SQLException;

	public List<BookDTO> bookSelectByWriter(String bWrite) throws SQLException;

	public List<BookDTO> bookSelectByPublisher(String bPub) throws SQLException;
	
	public List<BookDTO> bookSelectBySname(String sName) throws SQLException;

	public List<BookDTO> bookSelectByBname(String bName) throws SQLException;

	
}
