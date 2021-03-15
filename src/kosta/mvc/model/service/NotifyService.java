package kosta.mvc.model.service;

import java.sql.SQLException;

import kosta.mvc.model.dao.NotifyDAO;
import kosta.mvc.model.dao.NotifyDAOImpl;
import kosta.mvc.session.Session;
import kosta.mvc.session.SessionSet;

public class NotifyService {
	NotifyDAO notifyDAO = new NotifyDAOImpl();
	
	
	public String printNmessage(String mID) throws SQLException {
		
		String message = notifyDAO.printNmessage(mID);
		if(message == null) {
			throw new SQLException("안녕하세요");
		}
		return message;
	}
	
}
