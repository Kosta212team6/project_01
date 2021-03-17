package kosta.mvc.controller;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.model.dto.RsvDTO;
import kosta.mvc.model.service.ReturnService;
import kosta.mvc.model.service.ReturnServiceImpl;
import kosta.mvc.view.FailView;
import kosta.mvc.view.SuccessView;

public class ReturnController {

	private static ReturnService returnService = new ReturnServiceImpl();
	
	public static void returnBooks(String mID) {
		try { 
			returnService.returnBooks(mID);
		} catch (SQLException e) {
			//e.printStackTrace(); 
			FailView.errorMessage(e.getMessage());
		}
	}
}
