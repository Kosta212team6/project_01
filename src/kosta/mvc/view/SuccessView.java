package kosta.mvc.view;

import java.util.List;
import java.util.Map;

import kosta.mvc.model.dto.BookDTO;
import kosta.mvc.model.dto.MemberDTO;



public class SuccessView {
	public static void printMessage(String message) {
		System.out.println(message);
	}
	
	public static void loginNotifyMessage(String message) {
		System.out.println(message);
	}
	
	/**
	 * 책 정보 한 권만 출력하기
	 */
	public static void printOnlyBook(BookDTO bookDTO) {
		System.out.println(bookDTO);
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
	/**
	 * 책바구니 보기 (전체출력)
	 * @param mID
	 * @param cart
	 */
	public static void printViewCart(String mID, Map<BookDTO, Integer> cart) {
		System.out.println("------책바구니 내용------");
		
		for(BookDTO bookDTO : cart.keySet()) {
			int bISBN = bookDTO.getbIsbn(); //ISBN
			String bName = bookDTO.getbName(); //책 이름
			
			System.out.println(bISBN + " : " + bName); //ISBA : 책이름 출력
		}
	}
	
}
