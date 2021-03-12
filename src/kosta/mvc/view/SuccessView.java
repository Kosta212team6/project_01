package kosta.mvc.view;

import java.util.List;

import kosta.mvc.model.dto.MemberDTO;



public class SuccessView {
	public static void printMessage(String message) {
		System.out.println(message);
	}
	
	public static void loginNotifyMessage(String message) {
		System.out.println(message);
	}
	
	/**
	 * 전체 검색 출력하기
	 */
	public static void selectPrintAll(List<MemberDTO> list) {
		System.out.println("검색결과 "+list.size()+"명의 회원이 있습니다.");
		for(MemberDTO memberDTO : list) {
			System.out.println(memberDTO);
		}
		System.out.println();
	}
	
}
