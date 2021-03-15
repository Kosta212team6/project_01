package kosta.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.exception.NotAnymoreMemberException;
import kosta.mvc.exception.NotFoundException;
import kosta.mvc.model.dao.MemberDAO;
import kosta.mvc.model.dao.MemberDAOImpl;
import kosta.mvc.model.dto.MemberDTO;
import kosta.mvc.session.Session;
import kosta.mvc.session.SessionSet;
import kosta.mvc.view.MenuView;

public class MemberServiceImpl implements MemberService {
	MemberDAO memberDAO = new MemberDAOImpl();

	/**
	 * 로그인
	 */
	public MemberDTO login(String mID, String mPwd) throws SQLException, NotFoundException, NotAnymoreMemberException {

		MemberDTO memberDTO = memberDAO.login(mID, mPwd);
		if (memberDTO == null) {
			// 일치하는 정보 없음
			throw new NotFoundException("정보를 다시 확인해주세요.");
		} else if (memberDTO.getmStatus() == 0) {
			// 탈퇴한 회원 조회
			throw new NotAnymoreMemberException("탈퇴한 회원입니다");
		}

		// 로그인 된 정보 저장하기
		Session session = new Session(mID);
		SessionSet sessionSet = SessionSet.getInstance();
		sessionSet.add(session);

		return memberDTO;

	}

	@Override
	public List<MemberDTO> memberSelectAll() throws SQLException {
		List<MemberDTO> list = memberDAO.memberSelectAll();
		if (list == null) {
			throw new SQLException("오류 : 등록된 회원이 없습니다");
		}
		return list;
	}

	public List<MemberDTO> overdueMember() throws SQLException {
		List<MemberDTO> list = memberDAO.overdueMember();
		if (list == null) {
			throw new SQLException("오류 : 연체 회원이 존재하지 않습니다");
		}
		return list;
	}

	public boolean checkIDMember(String NowID) throws SQLException {
		boolean result = memberDAO.checkIDMember(NowID);
		return result;
	}

	public void createMember(MemberDTO memberDTO) throws SQLException {
		int result = memberDAO.createMember(memberDTO);
		if (result == 0) {
			throw new SQLException("오류 : 회원가입이 실패하였습니다");
		}

	}

	public List<MemberDTO> myInFo(String mID) throws SQLException, NotFoundException {
		List<MemberDTO> list = memberDAO.myInFo(mID);
		if (list == null) {
			throw new NotFoundException("오류 : 회원정보 출력 불가");
		}
		return list;

	}

	public void UpdatePassWord(MemberDTO memberDTO) throws SQLException {
		int result = memberDAO.UpdatePassword(memberDTO);
		if (result == 0) {
			throw new SQLException("오류 : 비밀번호 변경 실패");
		}

	}

	public void UpdatePhoneNumber(MemberDTO memberDTO) throws SQLException {
		int result = memberDAO.UpdatePhoneNumber(memberDTO);
		if (result == 0) {
			throw new SQLException("오류 : 전화번호 변경 실패");
		}

	}

	public void cancelAccount(String mID, String mPwd) throws SQLException {
		int result = memberDAO.cancelAccount(mID, mPwd);
		if (result == 0) {
			throw new SQLException("오류 : 회원탈퇴 실패");

		}
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(mID);
		ss.remove(session); //세선 끊기

	}
}
