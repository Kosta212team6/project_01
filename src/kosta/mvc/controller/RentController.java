package kosta.mvc.controller;

import java.sql.Connection;
import java.sql.SQLException;

import kosta.mvc.model.dto.RentDTO;
import kosta.mvc.model.service.RentService;
import kosta.mvc.view.FailView;

public class RentController {
	private static RentService rentService = new RentService();
	/**
	 * 대여하기
	 */
	public static void insertRents (Connection con, RentDTO rentDTO) {
		try {
			rentService.insertRents(con, rentDTO);
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	
}
