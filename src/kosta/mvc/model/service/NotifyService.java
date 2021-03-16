package kosta.mvc.model.service;

import java.sql.SQLException;

public interface NotifyService {

	/**
	 * 회원의 알림 메세지 받아오는 메소드
	 */
	String printNmessage(String mID) throws SQLException;
}
