package kosta.mvc.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kosta.mvc.controller.CartController;
import kosta.mvc.exception.NotFoundException;
import kosta.mvc.model.dao.RentDAO;
import kosta.mvc.model.dao.RentDAOImpl;
import kosta.mvc.model.dto.BookDTO;
import kosta.mvc.session.Session;
import kosta.mvc.session.SessionSet;

public class RentService {
	RentDAO rentDAO = new RentDAOImpl();
	
	/**
	 * 대여하기
	 */
	public void insertRents(List<BookDTO> list, String mID) throws SQLException {
		rentDAO.insertRents(list, mID);
	}
	
	/**
	 * bISBN으로 책바구니 목록 선택삭제 하기
	 */
	public void deleteCart(String mID, int bISBN) throws NotFoundException {
//		SessionSet ss = SessionSet.getInstance();
//		Session session = ss.get(mID);
//		if(session.getAttribute("cart")==null) {
//			throw new NotFoundException("책바구니에 책을 담고 다시 시도해 주세요.");
//		}
//		
//		if(((Map<Integer, BookDTO>)session.getAttribute("cart")).isEmpty()) {
//			throw new NotFoundException("책바구니에 책을 담고 다시 시도해 주세요.");
//		}
		CartController.deleteCart(mID, bISBN);
	}
	
	/**
	 * 비우기
	 */
	public void clearCart(String mID) throws NotFoundException {
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(mID);
		if(session.getAttribute("cart")==null) {
			throw new NotFoundException("현재 [ " + mID + " ] 님의 책바구니가 비었습니다.");
		}
		CartController.clearCart(mID);
	}
}
