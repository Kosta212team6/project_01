package kosta.mvc.view;

import java.util.List;
import java.util.Map;

import kosta.mvc.model.dto.BookDTO;
import kosta.mvc.model.dto.MemberDTO;
import kosta.mvc.model.dto.RentDTO;
import kosta.mvc.model.dto.RsvDTO;

public class SuccessView {
	public static void printMessage(String message) {
		System.out.println(message);
	}

	public static void loginNotifyMessage(String message) {
		System.out.println(message);
	}

	/**
	 * 책정보 여러권 출력하기
	 * 
	 */
	public static void printBooks(List<BookDTO> list) {
		System.out.println("ISBN | 도서명 | 저자 | 출판사 | 발행년월일 | 재고량 | 분야");
		for (BookDTO dto : list) {
			System.out.println(dto);
		}
		System.out.println();
	}

	/**
	 * 전체 검색 출력하기
	 */
	public static void selectPrintAll(List<MemberDTO> list) {
		System.out.println("검색결과 " + list.size() + "명의 회원이 있습니다.");
		for (MemberDTO memberDTO : list) {
			System.out.println(memberDTO);
		}
		System.out.println();
	}

	/**
	 * 책바구니 보기 (전체출력)
	 * 
	 * @param mID
	 * @param cart
	 */
	public static void printViewCart(String mID, Map<Integer, BookDTO> cart) {
		System.out.println("────────────── 책바구니 내용 ──────────────");

		for (Integer bISBN : cart.keySet()) {
			BookDTO bookDTO = cart.get(bISBN);
			String bName = bookDTO.getbName(); // 책 이름
			System.out.println(bISBN + " : " + bName); // ISBA : 책이름 출력
		}
		System.out.println("───────────────────────────────────");
		System.out.println();
	}

	/**
	 * 책 정보 한 권만 출력하기
	 */
	public static void printOnlyBook(BookDTO bookDTO) {
		System.out.println("ISBN | 도서명 | 저자 | 출판사 | 발행년월일 | 재고량 | 분야");
		System.out.println(bookDTO);

	}


	public static void printRentBookList(List<RentDTO> list) {
		System.out.println("────────────── 대여 목록 ──────────────");
		for(RentDTO dto : list) {
			System.out.println(dto);
		}
		System.out.println("─────────────────────────────────");
		System.out.println();
		
	}

	public static void printRsvBookList(List<RsvDTO> list) {
		System.out.println("────────────── 예약 목록 ──────────────");
		for(RsvDTO dto : list) {
			System.out.println(dto);
		}
		System.out.println("─────────────────────────────────");
		System.out.println();
		
	}

	public static void selectPrintMyInfo(List<MemberDTO> list) {
		System.out.println("────────────── 내 정보 ──────────────");
		for(MemberDTO dto : list) {
			System.out.println(dto);
		}
		System.out.println("────────────────────────────────");
		System.out.println();
		
	}


	/**
	 * 대여 내역 출력
	 */
	public static void printRentHistory(List<RentDTO> list) {
		System.out.println("검색결과 : "+list.size()+"개");
		for(RentDTO rentDTO:list) {
			System.out.println(rentDTO);
		}
		System.out.println();
	}
	
	/**
	 * 예약 내역 출력
	 */
	public static void printRsvHistory(List<RsvDTO> list) {
		System.out.println("검색결과 : "+list.size()+"개");
		for(RsvDTO rsvDTO:list) {
			System.out.println(rsvDTO);
		}
		System.out.println();
	}

}
