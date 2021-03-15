package kosta.mvc.controller;

import java.sql.SQLException;

import kosta.mvc.model.service.NotifyService;
import kosta.mvc.view.FailView;

public class NotifyController {
	NotifyService notifyService = new NotifyService();
	
	public String printMessage(String mID) throws SQLException {
			String message = notifyService.printNmessage(mID);
			return message;
		}
	}
