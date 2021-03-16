package kosta.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.model.dto.RentDTO;
import kosta.mvc.model.dto.RsvDTO;

public interface ReturnService {
	
	/**
	 * 반납하기
	 */
	void returnBooks(String mID) throws SQLException;
}
