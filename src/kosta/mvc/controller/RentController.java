package kosta.mvc.controller;

import java.util.List;
import java.util.Map;


import kosta.mvc.exception.NotFoundException;
import com.sun.org.apache.bcel.internal.classfile.ExceptionTable;
import kosta.mvc.exception.StringFormatException;
import kosta.mvc.model.dto.BookDTO;
import kosta.mvc.model.dto.RentDTO;
import kosta.mvc.model.service.RentServiceImpl;
import kosta.mvc.session.Session;
import kosta.mvc.session.SessionSet;
import kosta.mvc.view.FailView;
import kosta.mvc.view.SuccessView;

public class RentController {
	private static RentServiceImpl rentService = new RentServiceImpl();
	
	/**
	 * 대여하기
	 */
	public static void insertRents (List<BookDTO> list, String mID) {
		try {
			rentService.insertRents(list, mID);
			SuccessView.printMessage("대여가 완료되었습니다");
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
			if(session.getAttribute("cart")==null)  {
				return false;
			}
			if(((Map<Integer, BookDTO>)session.getAttribute("cart")).isEmpty()) {
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
	
	/**
	 * 대여한 도서 출력
	 * */
	public static void rentBookList(String mID) {
		try {
			List<RentDTO> list = rentService.printRentBookList(mID);
			SuccessView.printRentBookList(list);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
}