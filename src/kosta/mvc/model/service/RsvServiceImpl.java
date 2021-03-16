package kosta.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.model.dao.RsvDAO;
import kosta.mvc.model.dao.RsvDAOImpl;
import kosta.mvc.model.dto.RsvDTO;

public class RsvServiceImpl implements RsvService {

	private RsvDAO rsvDAO = new RsvDAOImpl();
	
	@Override
	public List<RsvDTO> searchRsvHistory(String mID) throws SQLException {
		List<RsvDTO> list = rsvDAO.searchRsvHistory(mID);
		if(list==null) {
			throw new SQLException("예약한 내역이 없습니다");
		}
		return list;
	}

	@Override
	public void insertRsv(String mID, int bISBN) throws SQLException {
		int result = rsvDAO.insertRsv(mID, bISBN);
		if(result==0) {
			throw new SQLException("예약이 실패하였습니다");
		}
		
	}
}
