package kosta.mvc.controller;

import kosta.mvc.model.dto.MemberDTO;
import kosta.mvc.model.service.MemberService;
import kosta.mvc.view.FailView;
import kosta.mvc.view.MenuView;
import kosta.mvc.view.TestView;

public class MemberController {
	static MemberService memberService = new MemberService();

	/**
	 * 로그인
	 */
	public static void login(String mID, String mPwd) {
		try {
			MemberDTO memberDTO = memberService.login(mID, mPwd);
			if(memberDTO.getmStatus()==2) {
				// 관리자 메뉴 진입
				TestView.printAdminMenu(mID);
			}
			TestView.printUserMenu(mID);
		} catch (Exception e) {
//			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}

}
