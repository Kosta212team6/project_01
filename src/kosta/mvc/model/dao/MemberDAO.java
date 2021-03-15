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

	/**
	 * 관리자 : 연체회원 조회 
	 */
	List<MemberDTO> overdueMember() throws SQLException;

	/**
	 * 아이디 중복체크
	 */
	boolean checkIDMember(String NowID) throws SQLException;
	
	/**
	 * 회원가입
	 */
	int createMember(MemberDTO memberDTO) throws SQLException;
	/**
	 * 내정보 조회
	 * */

	List<MemberDTO> myInFo(String mID) throws SQLException;

	/**
	 * 비밀번호 변경
	 * */
	int UpdatePassword(MemberDTO memberDTO) throws SQLException;

	/**
	 * 전화번호 변경
	 * */
	int UpdatePhoneNumber(MemberDTO memberDTO) throws SQLException;

	int cancelAccount(String mID, String mPwd)throws SQLException;

}
