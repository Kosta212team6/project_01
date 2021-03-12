package kosta.mvc.view;

import java.util.Scanner;

import kosta.mvc.controller.MemberController;

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
	public static void printUserMenu() {
		while(true) {
			System.out.println("mID님 로그인 하셨습니다.");
			System.out.println("1. 로그아웃   2. 검색하기   3. 책바구니 담기   4. 책바구니 보기   5. 마이서재");
			
			try {
				int menu = Integer.parseInt(sc.nextLine());
				switch (menu) {
				case 1:
					System.out.println("로그아웃 합니다");
					//logout(mID);
					break;
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
}
