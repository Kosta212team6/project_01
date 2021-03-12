package kosta.mvc.view;

import java.util.Scanner;

import kosta.mvc.controller.BookController;
import kosta.mvc.controller.MemberController;
import kosta.mvc.model.dto.BookDTO;
import kosta.mvc.session.Session;
import kosta.mvc.session.SessionSet;

public class MenuView {

	private static Scanner sc = new Scanner(System.in);
	
	public static void menu() {
		while(true) {
			MenuView.printMenu();
			System.out.print(" > ");
			
			try {
				int menu = Integer.parseInt(sc.nextLine());
				switch (menu) {
				case 1:
					MenuView.register();
					break;
				case 2:
					MenuView.login();
					break;
				case 9:
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);
					
				default:
					System.out.println("메뉴번호에 해당하는 번호를 입력해주십시오.");
					break;
				}
				
			} catch (NumberFormatException e) {
				System.out.println("숫자만 입력해주세요");
			}
			
		}
	}

	/**
	 * 로그인 메뉴
	 */
	public static void login() {
		System.out.print("ID : ");
		String mID = sc.nextLine();
		
		System.out.print("PW : ");
		String mPwd = sc.nextLine();
		
		MemberController.login(mID, mPwd);
	}
	
	/**
	 * 로그아웃 메뉴
	 */
	public static void logout(String mID) {
		Session session = new Session(mID);
		
		SessionSet ss = SessionSet.getInstance();
		ss.remove(session);
	}
	
	/**
	 * 가입하기 메뉴
	 */
	public static void register() {
		
	}
	
	/**
	 * 메인메뉴
	 */
	public static void printMenu() {
		System.out.println("┌──── 도서관프로그램 ─────┐");
		System.out.println("│ 1. 가입하기   2. 로그인  9. 종료 │");
		System.out.println("└─────────────────┘");
	}
	
	/**
	 * 회원용 메뉴
	 */
	public static void printUserMenu(String mID) {
		while(true) {
			SessionSet ss = SessionSet.getInstance();
			System.out.println(ss.getSet());
			System.out.println(mID+"님 로그인 하셨습니다.");
			System.out.println("1. 로그아웃   2. 검색하기   3. 책바구니 담기   4. 책바구니 보기   5. 마이서재");
			
			try {
				int menu = Integer.parseInt(sc.nextLine());
				switch (menu) {
				case 1:
					System.out.println("로그아웃 합니다");
					logout(mID);
					return;
				case 2:
					System.out.println("책을 검색합니다");
					break;
				case 3:
					System.out.println("책바구니에 책을 담습니다");
				case 4:
					System.out.println("책바구니에 담은 책을 봅니다");
					break;
				case 5:
					System.out.println("마이서재를 엽니다");
					break;
				default:
					System.out.println("메뉴번호에 해당하는 번호를 입력해주십시오.");
					break;
				}
				
			} catch (NumberFormatException e) {
				System.out.println("숫자만 입력해주세요");
			}
		}
	}
	
	/**
	 * 관리자용 메인메뉴
	 */
	public static void printAdminMenu(String mID) {
		while(true) {
			SessionSet ss = SessionSet.getInstance();
			System.out.println(ss.getSet());
			System.out.println(mID+"님 관리자로 로그인 하셨습니다.");
			System.out.println("1. 로그아웃   2. 도서검색   3. 도서관리   4. 회원관리");
			
			try {
				int menu = Integer.parseInt(sc.nextLine());
				switch (menu) {
				case 1:
					System.out.println("로그아웃 합니다");
					logout(mID);
					return;
				case 2:
					System.out.println("도서검색을 선택하셨습니다");
					break;
				case 3:
					System.out.println("도서관리를 선택하셨습니다");
					TestView.printAdminBookDMLMenu(mID);
					break;
				case 4:
					System.out.println("회원관리를 선택하셨습니다");
					break;
				default:
					System.out.println("메뉴번호에 해당하는 번호를 입력해주십시오.");
					break;
				}
				
			} catch (NumberFormatException e) {
				System.out.println("숫자만 입력해주세요");
			}
		}
	}
	
	/**
	 * 관리자용 도서 관리 메뉴
	 */
	public static void printAdminBookDMLMenu(String mID) {
		SessionSet ss = SessionSet.getInstance();
		System.out.println(ss.getSet());
		System.out.println(mID+"님 관리자로 로그인 하셨습니다.");
		
		System.out.println("1. 도서추가   2. 도서정보 수정   3. 도서정보 삭제   9. 뒤로가기");

		try {
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				System.out.println("도서를 추가합니다");
				printAdminInsertBook();
				break;
			case 2:
				System.out.println("도서정보를 수정합니다");
				break;
			case 3:
				System.out.println("도서정보를 삭제합니다");
				break;
			case 9:
				System.out.println("이전 메뉴를 불러옵니다");
				return;
			default:
				System.out.println("메뉴번호에 해당하는 번호를 입력해주십시오.");
				break;
			}
		} catch (NumberFormatException e) {
			System.out.println("숫자만 입력해주세요");
		}	
	}
	
	/**
	 * 도서추가
	 */
	public static void printAdminInsertBook() {
		System.out.println("도서를 추가합니다");
		System.out.print("ISBN > ");
		int bISBN = Integer.parseInt(sc.nextLine());
		System.out.print("도서 제목 > ");
		String bName = sc.nextLine();
		System.out.print("저자 > ");
		String bWrite = sc.nextLine();
		System.out.print("출판사 > ");
		String bPub = sc.nextLine();
		System.out.print("발행년월일(yyyy-mm-dd) > ");
		String bDate = sc.nextLine();
		System.out.print("장르코드 > ");
		// 여기에 db로부터 장르테이블 얻어오면 좋을 듯
		int sCode = Integer.parseInt(sc.nextLine());
		
		BookDTO bookDTO = new BookDTO(bISBN, bName, bWrite, bPub, bDate, 1, sCode);
		BookController.InsertBook(bookDTO);
		
	}

	/**
	 * 도서정보 수정
	 */
	public static void printAdminUpdateBook() {
		System.out.print("수정 할 도서의 ISBN은? > ");
		int bISBN = Integer.parseInt(sc.nextLine());
		
		System.out.println("수정 할 정보를 선택하세요");
		System.out.println("1. ISBN   2. 도서명   3. 저자   4. 출판사   5. 발행년월일   6. 분류코드");
		
		try {
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				System.out.println("ISBN을 수정합니다");
				printAdminInsertBook();
				break;
			case 2:
				System.out.println("도서정보를 수정합니다");
				break;
			case 3:
				System.out.println("도서정보를 삭제합니다");
				break;
			case 9:
				System.out.println("이전 메뉴를 불러옵니다");
				return;
			default:
				System.out.println("메뉴번호에 해당하는 번호를 입력해주십시오.");
				break;
			}
		} catch (NumberFormatException e) {
			System.out.println("숫자만 입력해주세요");
		}
	}
	
	/**
	 * 도서정보 삭제
	 */
	public static void printAdminDeleteBook() {
		
	}

}
