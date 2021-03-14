package kosta.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.controller.CartController;
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
	public int deleteCart(String mID) {
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(mID);
		int bISBN = 0;
		
		return bISBN;
		
	}
	
	/**
	 * 비우기
	 */
	public void clearCart(String mID) throws SQLException {
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(mID);
		if(session.getAttribute("cart")==null) {
			throw new SQLException("현재 [ " + mID + " ] 님의 장바구니가 비었습니다.");
		}
		CartController.clearCart(mID);
	}
}
