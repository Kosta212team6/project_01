package kosta.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.model.dao.ReturnDAO;
import kosta.mvc.model.dao.ReturnDAOImpl;
import kosta.mvc.model.dto.RentDTO;
import kosta.mvc.model.dto.RsvDTO;

public class ReturnServiceImpl implements ReturnService{

	private ReturnDAO returnDAO = new ReturnDAOImpl();

//	@Override
//	public List<RentDTO> searchRentHistory(String mID) throws SQLException {

////		List<RentDTO> list = returnDAO.searchRentHistory(mID);
////		if(list==null) {
////			throw new SQLException("대여한 내역이 없습니다");
////		}
////		return list;

//		List<RentDTO> list = returnDAO.searchRentHistory(mID);
//		if(list==null) {
//			throw new SQLException("대여한 내역이 없습니다");
//		}
//		return list;

//	}

	@Override
	public List<RsvDTO> searchRsvHistory(String mID) throws SQLException {
		List<RsvDTO> list = returnDAO.searchRsvHistory(mID);
		if(list==null) {
			throw new SQLException("예약한 내역이 없습니다");
		}
		return list;
	}

	@Override
	public void returnBooks(String mID, int bISBN) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	
}
