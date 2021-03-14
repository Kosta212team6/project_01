package kosta.mvc.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.sun.org.apache.bcel.internal.classfile.ExceptionTable;

import kosta.mvc.exception.StringFormatException;
import kosta.mvc.model.dto.BookDTO;
import kosta.mvc.model.dto.RentDTO;
import kosta.mvc.model.service.RentService;
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
	public static int deleteCart (String mID) {
		int bISBN = 0;
		
		return bISBN;
	}
	
	/**
	 * 비우기
	 */
	public static void clearCart (String mID) {
		try {
			rentService.clearCart(mID);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
}