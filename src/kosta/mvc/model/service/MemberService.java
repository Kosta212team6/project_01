package kosta.mvc.model.service;

import java.sql.SQLException;

import kosta.mvc.exception.NotAnymoreMemberException;
import kosta.mvc.exception.NotFoundException;
import kosta.mvc.model.dao.MemberDAO;
import kosta.mvc.model.dao.MemberDAOImpl;
import kosta.mvc.model.dto.MemberDTO;
import kosta.mvc.session.Session;
import kosta.mvc.session.SessionSet;
import kosta.mvc.view.MenuView;

public class MemberService {
	MemberDAO memberDAO = new MemberDAOImpl();
	
	/**
	 * 로그인
	 */
	public MemberDTO login(String mID, String mPwd) throws SQLException, NotFoundException, NotAnymoreMemberException {
		
		MemberDTO memberDTO = memberDAO.login(mID, mPwd);
		if(memberDTO==null) {
			// 일치하는 정보 없음
			throw new NotFoundException("정보를 다시 확인해주세요.");
		}
		else if(memberDTO.getmStatus()==0) {
			// 탈퇴한 회원 조회
			throw new NotAnymoreMemberException("탈퇴한 회원입니다");
		}
		
		// 로그인 된 정보 저장하기
		Session session = new Session(mID);
		SessionSet sessionSet = SessionSet.getInstance();
		sessionSet.add(session);
		
		return memberDTO;

	}
	
}
