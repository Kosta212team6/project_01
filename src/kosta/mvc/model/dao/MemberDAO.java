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
	
	List<MemberDTO> myInFo(String mID) throws SQLException;
	
	int UpdatePassword(MemberDTO memberDTO) throws SQLException;

	int UpdatePhoneNumber(MemberDTO memberDTO) throws SQLException;

	int cancelAccount(String mID, String mPwd)throws SQLException;
<<<<<<< HEAD
=======



==========
>>>>>>> branch 'main' of https://github.com/Kosta212team6/project_01.git
	
	public List<MemberDTO> myInFo(String mID) throws SQLException;
	
	public int UpdatePassword(MemberDTO memberDTO) throws SQLException ;
	
	public int UpdatePhoneNumber(MemberDTO memberDTO) throws SQLException;
}
