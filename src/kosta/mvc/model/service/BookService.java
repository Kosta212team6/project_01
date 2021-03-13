package kosta.mvc.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kosta.mvc.exception.NotFoundException;
import kosta.mvc.model.dto.BookDTO;
import kosta.mvc.util.DBUtil;

public interface BookService {
	
	/**
	 * 도서추가
	 */
	void InsertBook(BookDTO bookDTO) throws SQLException;

	/**
	 * 도서정보수정 : ISBN
	 */
	void UpdateBookISBN(int bISBN, int newISBN) throws SQLException;

	/**
	 * 도서정보수정 : 도서명
	 */
	void UpdateBookName(BookDTO bookDTO) throws SQLException;

	/**
	 * 도서정보수정 : 저자
	 */
	void UpdateBookWrite(BookDTO bookDTO) throws SQLException;

	/**
	 * 도서정보수정 : 출판사
	 */
	void UpdateBookPub(BookDTO bookDTO) throws SQLException;

	/**
	 * 도서정보수정 : 발행년월일
	 */
	void UpdateBookDate(BookDTO bookDTO) throws SQLException;

	/**
	 * 도서정보수정 : 분류코드
	 */
	void UpdateBookCode(BookDTO bookDTO) throws SQLException;

	/**
	 * 도서정보삭제
	 */
	void DeleteBook(int bISBN) throws SQLException;
	
	/**
	 * bISBN으로 도서 검색
	 */
	public BookDTO bookSelectByBisbn(int bISBN) throws SQLException, NotFoundException;
	
	
	/**
	 *bWrite으로 도서검색
	 */
	public BookDTO bookSelectByWriter(String bWrite) throws SQLException, NotFoundException;
	
	/**
	 *bPub으로 도서검색
	 */
	public BookDTO bookSelectByPublisher(String bPub) throws SQLException, NotFoundException;
	/**
	 *sName으로 도서검색
	 */
	public BookDTO bookSelectBySname(String sName) throws SQLException, NotFoundException;
	/**
	 *bName으로 도서검색
	 */

	public BookDTO bookSelectByBname(String bName) throws SQLException, NotFoundException;
	

	
}
