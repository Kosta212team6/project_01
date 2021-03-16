package kosta.mvc.model.dao;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.model.dto.BookDTO;

public interface BookDAO {

	/**
	 * 도서 등록
	 */
	int InsertBook(BookDTO bookDTO) throws SQLException;

	/**
	 * 도서 수정 : ISBN
	 */
	int UpdateBookISBN(int bISBN, int newISBN) throws SQLException;

	/**
	 * 도서 수정 : 도서명
	 */
	int UpdateBookName(BookDTO bookDTO) throws SQLException;

	/**
	 * 도서 수정 : 저자
	 */
	int UpdateBookWrite(BookDTO bookDTO) throws SQLException;

	/**
	 * 도서 수정 : 출판사
	 */
	int UpdateBookPub(BookDTO bookDTO) throws SQLException;

	/**
	 * 도서 수정 : 발행년월일
	 */
	int UpdateBookDate(BookDTO bookDTO) throws SQLException;

	/**
	 * 도서 수정 : 장르코드 수정
	 */
	int UpdateBookCode(BookDTO bookDTO) throws SQLException;

	/**
	 * 도서 삭제
	 */
	int DeleteBook(int bISBN) throws SQLException;
	
	/**
	 * ISBN으로 도서 검색
	 */
	public BookDTO bookSelectByBisbn(int bISBN) throws SQLException;

	/**
	 * 작가로 도서 검색
	 */
	public List<BookDTO> bookSelectByWriter(String bWrite) throws SQLException;

	/**
	 * 출판사로 도서 검색
	 */
	public List<BookDTO> bookSelectByPublisher(String bPub) throws SQLException;
	
	/**
	 * 장르코드로 도서 검색
	 */
	public List<BookDTO> bookSelectBySname(String sName) throws SQLException;

	/**
	 * 도서명으로 도서 검색
	 */
	public List<BookDTO> bookSelectByBname(String bName) throws SQLException;

}
