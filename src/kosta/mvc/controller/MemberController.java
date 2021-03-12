package kosta.mvc.controller;

import kosta.mvc.model.dto.MemberDTO;
import kosta.mvc.model.service.MemberService;
import kosta.mvc.view.FailView;
import kosta.mvc.view.MenuView;

public class MemberController {
	static MemberService memberService = new MemberService();

	/**
	 * 로그인
	 */
	public static void login(String mID, String mPwd) {
		try {
			MemberDTO memberDTO = memberService.login(mID, mPwd);
			MenuView.printUserMenu(mID);
		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}

}
