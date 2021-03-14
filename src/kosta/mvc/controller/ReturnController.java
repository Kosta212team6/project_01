package kosta.mvc.controller;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.model.dto.RentDTO;
import kosta.mvc.model.dto.RsvDTO;
import kosta.mvc.model.service.ReturnService;
import kosta.mvc.model.service.ReturnServiceImpl;
import kosta.mvc.view.FailView;
import kosta.mvc.view.SuccessView;

public class ReturnController {

	private static ReturnService returnService = new ReturnServiceImpl();
	
	public static void searchRentHistory(String mID) {
		try {
			List<RentDTO> list = returnService.searchRentHistory(mID);
			SuccessView.printRentHistory(list);
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	public static void searchRsvHistory(String mID) {
		try {
			List<RsvDTO> list = returnService.searchRsvHistory(mID);
			SuccessView.printRsvHistory(list);
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
}
