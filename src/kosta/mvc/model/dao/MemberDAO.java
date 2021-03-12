package kosta.mvc.model.dao;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.model.dto.MemberDTO;

public interface MemberDAO {
	/**
	 * 로그인
	 */
	MemberDTO login(String mID, String mPwd) throws SQLException;
	
	/**
	 * 관리자 : 전체 회원 조회
	 */
	List<MemberDTO> memberSelectAll() throws SQLException;
}
