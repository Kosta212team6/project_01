package kosta.mvc.controller;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.exception.NotFoundException;
import kosta.mvc.model.dto.MemberDTO;
import kosta.mvc.model.service.MemberServiceImpl;
import kosta.mvc.view.FailView;
import kosta.mvc.view.MenuView;
import kosta.mvc.view.SuccessView;


public class MemberController {
	static MemberServiceImpl memberService = new MemberServiceImpl();

	/**
	 * 로그인
	 */
	public static void login(String mID, String mPwd) {
		try {
			MemberDTO memberDTO = memberService.login(mID, mPwd);
			if(memberDTO.getmStatus()==2)
				// 관리자 메뉴 진입
				MenuView.printAdminMenu(mID);
			else
				// 일반회원 메뉴 진입
				MenuView.printUserMenu(mID);

		} catch (Exception e) {
//			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 회원 전체 출력
	 */
	public static void memberSelectAll() {
		try {
			List<MemberDTO> list = memberService.memberSelectAll();
			SuccessView.selectPrintAll(list);
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * 연체 회원 출력
	 */
	public static void overdueMember() {
		try {
			List<MemberDTO> list = memberService.overdueMember();
			SuccessView.selectPrintAll(list);
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}
	
	/**
	 * 아이디 중복체크
	 */
	public static boolean checkIDMember(String NowID) {
		try {
			boolean result = memberService.checkIDMember(NowID);
			return result;
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
		return false;
	}

	public static void createMember(MemberDTO memberDTO) {
		try {
			memberService.createMember(memberDTO);
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}

	public static void myInfo(String mID) {
		try {
			List<MemberDTO> list = memberService.myInFo(mID);
			SuccessView.selectPrintMyInfo(list);
		}catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}
	
	public static void loginForChangeInfo(String mID, String mPwd) {
		try {
			MemberDTO memberDTO = memberService.login(mID, mPwd);
			if(memberDTO.getmPwd().contentEquals(mPwd))
				// 개인정보 변경 진입
				MenuView.printModifyMyInFo(mID);

		} catch (Exception e) {
//			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
			MenuView.printMyLibary(mID); // 서재로 돌아가기
		}
	}

	public static void UpdatePassWord(MemberDTO memberDTO) {
		try {
			memberService.UpdatePassWord(memberDTO);
			SuccessView.printMessage("비밀번호 변경 성공!");
		}catch(SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}

	public static void UpdatePhoneNumber(MemberDTO memberDTO) {
		try {
		memberService.UpdatePhoneNumber(memberDTO);
		SuccessView.printMessage("전화번호 변경 성공!");
		}catch(SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}



}
