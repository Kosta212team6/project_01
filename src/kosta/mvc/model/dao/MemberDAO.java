package kosta.mvc.model.dao;

import java.sql.SQLException;

import kosta.mvc.model.dto.MemberDTO;

public interface MemberDAO {
	/**
	 * 로그인
	 */
	MemberDTO login(String mID, String mPwd) throws SQLException;
}
