package kosta.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.model.dto.RentDTO;
import kosta.mvc.model.dto.RsvDTO;

public interface ReturnService {

	/**
	 * 대여 내역 검색
	 */
//	List<RentDTO> searchRentHistory(String mID) throws SQLException;
	
	/**
	 * 예약 내역 검색
	 */
	List<RsvDTO> searchRsvHistory(String mID) throws SQLException;
	
	/**
	 * 반납하기
	 */
	void returnBooks(String mID) throws SQLException;
}
