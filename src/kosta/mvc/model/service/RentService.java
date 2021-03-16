package kosta.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.exception.NotFoundException;
import kosta.mvc.model.dto.BookDTO;
import kosta.mvc.model.dto.RentDTO;

public interface RentService {

	/**
	 * 대여하기
	 */
	void insertRents(List<BookDTO> list, String mID) throws SQLException;
	
	/**
	 * bISBN으로 책바구니 목록 선택삭제하기
	 */
	void deleteCart(String mID, int bISBN) throws NotFoundException;
	
	/**
	 * 책바구니 비우기
	 */
	void clearCart(String mID) throws NotFoundException;
	
	/**
	 * 대여도서 목록 출력
	 */
	List<RentDTO> printRentBookList(String mID) throws SQLException, NotFoundException;
}
