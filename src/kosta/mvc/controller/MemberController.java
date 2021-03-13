package kosta.mvc.controller;

import java.sql.SQLException;
import java.util.List;

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
	
	public static void memberSelectAll() {
		try {
			List<MemberDTO> list = memberService.memberSelectAll();
			SuccessView.selectPrintAll(list);
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	public static void overdueMember() {
		try {
			List<MemberDTO> list = memberService.overdueMember();
			SuccessView.selectPrintAll(list);
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}

}
