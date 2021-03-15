package kosta.mvc.model.dao;

import java.sql.SQLException;

public interface NotifyDAO {
	
	/**
	 * 예약메시지 가져오는 메소드
	 */
	public String printNmessage(String mID)throws SQLException;
}
