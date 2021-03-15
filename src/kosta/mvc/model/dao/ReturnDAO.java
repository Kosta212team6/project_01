package kosta.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kosta.mvc.model.dto.RentDTO;
import kosta.mvc.model.dto.RsvDTO;
import kosta.mvc.util.DBUtil;

public interface ReturnDAO {
	public List<RentDTO> searchRentHistory(String mID) throws SQLException;

	public List<RsvDTO> searchRsvHistory(String mID) throws SQLException;
	
	/**
	 * 대망의 반납...!
	 */
	public void returnBooks(String mID, int bISBN) throws SQLException;

	/**
	 * 대여테이블에서 bISBN으로 rnum 파라미터 받기
	 */
	public RentDTO getRentNumberByISBN(int bISBN) throws SQLException;
	
	
	/**
	 * 반납테이블에 기록
	 */
	public int insertReturnHistory(RentDTO rentDTO) throws SQLException; 

	/**
	 * 대여상태 false로 변경
	 */


	public RsvDTO checkBookRsv(int bISBN) throws SQLException;


	public int switchBStatusTrue(Connection con, int bISBN) throws SQLException;
}
