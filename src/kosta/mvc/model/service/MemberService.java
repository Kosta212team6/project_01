package kosta.mvc.model.service;

import java.sql.SQLException;

import kosta.mvc.exception.NotAnymoreMemberException;
import kosta.mvc.exception.NotFoundException;
import kosta.mvc.model.dao.MemberDAO;
import kosta.mvc.model.dao.MemberDAOImpl;
import kosta.mvc.model.dto.MemberDTO;

public class MemberService {
	MemberDAO memberDAO = new MemberDAOImpl();
	
	/**
	 * 로그인
	 */
	public MemberDTO login(String mID, String mPwd) throws SQLException, NotFoundException, NotAnymoreMemberException {
		
		MemberDTO memberDTO = memberDAO.login(mID, mPwd);
		if(memberDTO==null) {
			throw new NotFoundException("정보를 다시 확인해주세요.");
		}
		else if(memberDTO.getmStatus()==0) {
			throw new NotAnymoreMemberException("탈퇴한 회원입니다");
		}
		return memberDTO;
	}
}
