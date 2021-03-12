package kosta.mvc.model.service;

import java.sql.SQLException;

import kosta.mvc.model.dao.MemberDAO;
import kosta.mvc.model.dao.MemberDAOImpl;
import kosta.mvc.model.dto.MemberDTO;

public class MemberService {
	MemberDAO memberDAO = new MemberDAOImpl();
	
	/**
	 * 로그인
	 */
	public MemberDTO login(String mID, String mPwd) throws SQLException {
		
		return null;
	}
}
