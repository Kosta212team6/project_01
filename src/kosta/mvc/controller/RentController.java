package kosta.mvc.controller;

import java.util.List;
import java.util.Map;

import kosta.mvc.exception.NotFoundException;
import kosta.mvc.model.dto.BookDTO;
import kosta.mvc.model.service.RentService;
import kosta.mvc.session.Session;
import kosta.mvc.session.SessionSet;
import kosta.mvc.view.FailView;

public class RentController {
	private static RentService rentService = new RentService();
	/**
	 * 대여하기
	 */
	public static void insertRents (List<BookDTO> list, String mID) {
		try {
			rentService.insertRents(list, mID);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	/**
	 *bISBN으로 책바구니 목록 선택삭제 하기 
	 */
	public static void deleteCart (String mID, int bISBN) {
		try {
		rentService.deleteCart(mID, bISBN);
		} catch (NotFoundException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	/**
	 * 동작 전에 책바구니가 비어있는지 확인해주는 메소드
	 */
	public static boolean isEmptyCart (String mID) {
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(mID);
			if(session.getAttribute("cart")==null || ((Map<Integer, BookDTO>)session.getAttribute("cart")).isEmpty()) {
				return false;
			}
		return true;
	}
	
	
	/**
	 * 비우기
	 */
	public static void clearCart (String mID) {
		try {
			rentService.clearCart(mID);
		} catch (NotFoundException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
}