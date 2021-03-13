package kosta.mvc.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kosta.mvc.exception.DuplicatedException;
import kosta.mvc.model.dto.BookDTO;
import kosta.mvc.model.service.BookService;
import kosta.mvc.model.service.BookServiceImpl;
import kosta.mvc.session.Session;
import kosta.mvc.session.SessionSet;
import kosta.mvc.view.FailView;
import kosta.mvc.view.SuccessView;



public class CartController {
	private static BookService bookService = new BookServiceImpl();
	/**
	 * 책바구니에 책 집어넣기
	 * @param mID
	 * @param bISBN
	 * @param bStatus
	 */
	public static void putCart(String mID, int bISBN) {
		
		try {
			//bISBN에 해당하는 책 찾기
			BookDTO bookDTO = bookService.bookSelectByBisbn(bISBN);
			
			if(bookDTO.getbStatus()==0) {
				throw new SQLException("해당 도서는 이미 대출중이므로"
						+ "책바구니에 담을 수 없습니다.");
			}
			//mID에 해당하는 세션찾기
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(mID);
			
			//세션에서 책바구니 찾기
			Map<BookDTO, Integer> cart = (Map<BookDTO, Integer>)session.getAttribute("cart");
			
			
			//책바구니가 없으면 책바구니 생성해주기
			// 중복된 도서 추가하지 않기

			// 1. 내가 선택한 bookDTO의 ISBN 먼저 가져온다
			// -> bookDTO.getbISBN();
			// 2. 기존의 카트에 있는 책들의 ISBN을 가져온다
			// -> 
			// 3. 둘을 비교하여 같은지 다른지 비교한다
			// 4. 카트에 같은게 존재하면 추가하지 않는다
			
			// 중복된 도서 추가하지 않기
//				Integer qty = cart.get(bookDTO);
//				if(bookDTO != null) {
//					throw new DuplicatedException("동일한 도서가 이미 책바구니에 있습니다");
//				}
			if(cart == null) { 
				cart = new HashMap<>(); 
				session.setAttribute("cart", cart); // String, Object 두개를 파라미터로 전달
			}
			
			if(!cart.containsValue(bISBN) ) {
				cart.put(bookDTO, bISBN);
				SuccessView.printMessage("책바구니에 책을 담았습니다.");
			} else {
				FailView.errorMessage("이미 책바구니에 책이 있습니다.");
			}
			
		} 
//		catch (DuplicatedException e) {
//			FailView.errorMessage(e.getMessage());
//		} 
		catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * 책바구니 보기
	 * @param mID
	 */
	public static void viewCart(String mID) {
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(mID);
		
		Map<BookDTO, Integer> cart = (Map<BookDTO, Integer>) session.getAttribute("cart");
		if(cart==null) {
			FailView.errorMessage("책바구니가 비었습니다.");
		} else {
			SuccessView.printViewCart(mID, cart);
		}
		
		//if(OldISBN =) {
//			System.out.println("이미 책바구니에 있는 책입니다.");
//		}
//		if(bookDTO != null) {
//			throw new DuplicatedException("동일한 도서가 이미 책바구니에 있습니다");
//		}
		// 세션에서 꺼내와서
		// isbn이 겹치면 튕겨내기
		
		
		
		
		
	}
	/**
	 * 대여하기 전에 책바구니 안에 있는 책 리턴해주는 메소드
	 * @param mID
	 * @return
	 */
	public static List<BookDTO> getBookDTOInCart(String mID) {
		List<BookDTO> list = null;
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(mID);
		
		Map<BookDTO, Integer> cart = (Map<BookDTO, Integer>)session.getAttribute("cart");
		for(BookDTO bookDTO : cart.keySet()) {
//			int bISBN = bookDTO.getbIsbn(); //ISBN
//			String bName = bookDTO.getbName(); //책 이름
			
			list.add(new BookDTO(bookDTO.getbIsbn(), bookDTO.getbName()));
		}
				
		return list;
	}
	
	
}