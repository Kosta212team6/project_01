package kosta.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.model.dto.RentDTO;
import kosta.mvc.model.dto.RsvDTO;

public interface ReturnService {
//	public List<RentDTO> searchRentHistory(String mID) throws SQLException;
		

	
	public List<RsvDTO> searchRsvHistory(String mID) throws SQLException;
	


	public void returnBooks(String mID, int bISBN) throws SQLException;

}
