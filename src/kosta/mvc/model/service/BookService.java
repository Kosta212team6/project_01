package kosta.mvc.model.service;

import java.sql.SQLException;

import kosta.mvc.model.dto.BookDTO;

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

}
