package kosta.mvc.controller;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.model.dto.RsvDTO;
import kosta.mvc.model.service.RsvService;
import kosta.mvc.model.service.RsvServiceImpl;
import kosta.mvc.view.FailView;
import kosta.mvc.view.SuccessView;

public class RsvController {

	private static RsvService rsvService = new RsvServiceImpl();
	
	public static void searchRsvHistory(String mID) {
		try {
			List<RsvDTO> list = rsvService.searchRsvHistory(mID);
			SuccessView.printRsvHistory(list);
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	public static void insertRsv(String mID, int bISBN) {
		try {
			rsvService.insertRsv(mID, bISBN);
			System.out.println("ISBN : "+bISBN+" 도서의 예약이 성공하였습니다");
		} catch (SQLException e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
}
