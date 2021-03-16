package kosta.mvc.controller;

import java.sql.SQLException;

import kosta.mvc.model.service.NotifyServiceImpl;

public class NotifyController {
	NotifyServiceImpl notifyService = new NotifyServiceImpl();
	
	public String printMessage(String mID) throws SQLException {
			String message = notifyService.printNmessage(mID);
			return message;
		}
	}
