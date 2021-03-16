package kosta.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.model.dao.ReturnDAO;
import kosta.mvc.model.dao.ReturnDAOImpl;
import kosta.mvc.model.dto.RentDTO;
import kosta.mvc.model.dto.RsvDTO;

public class ReturnServiceImpl implements ReturnService{

	private ReturnDAO returnDAO = new ReturnDAOImpl();

	@Override
	public void returnBooks(String mID) throws SQLException {
		int result = returnDAO.returnBooks(mID);
	}	
}
