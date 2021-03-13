package kosta.mvc.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import kosta.mvc.controller.CartController;
import kosta.mvc.model.dao.RentDAO;
import kosta.mvc.model.dao.RentDAOImpl;
import kosta.mvc.model.dto.RentDTO;
import kosta.mvc.util.DBUtil;

public class RentService {
	RentDAO rentDAO = new RentDAOImpl();
	/**
	 * 대여하기
	 */
	public void insertRents(Connection con, RentDTO rentDTO) throws SQLException {
		try {
			int result = rentDAO.RentInsert(con, rentDTO);
			if(result==0) {
				con.rollback();
				throw new SQLException("대여 실패");
			} else {
				rentDAO.switchBstatus(con, CartController.getBookDTOInCart(rentDTO.getmID()));
			}
			
		} finally {
			DBUtil.dbClose(con, null);
		}
		
	}
	
	
}
