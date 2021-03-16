package kosta.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.model.dto.RsvDTO;

public interface RsvService {

	/**
	 * 예약 내역 검색
	 */
	List<RsvDTO> searchRsvHistory(String mID) throws SQLException;
}
