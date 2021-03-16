package kosta.mvc.model.dao;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.model.dto.RsvDTO;

public interface RsvDAO {

	/**
	 * 예약 내역 검색
	 */
	List<RsvDTO> searchRsvHistory(String mID) throws SQLException;
	
	/**
	 * 예약 등록하기
	 */
	int insertRsv(String mID, int bISBN) throws SQLException;
}
