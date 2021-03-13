package kosta.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.exception.NotAnymoreMemberException;
import kosta.mvc.exception.NotFoundException;
import kosta.mvc.model.dto.MemberDTO;

public interface MemberService {

	/**
	 * 로그인
	 */
	MemberDTO login(String mID, String mPwd) throws SQLException, NotFoundException, NotAnymoreMemberException;

	/**
	 * 관리자 : 전체 회원 조회
	 */
	List<MemberDTO> memberSelectAll() throws SQLException;
	
	/**
	 * 아이디 중복 체크
	 */
	boolean checkIDMember(String NowID) throws SQLException;
	
	/**
	 * 아이디 생성
	 */
	void createMember(MemberDTO memberDTO) throws SQLException;
}