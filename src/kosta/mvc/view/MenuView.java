package kosta.mvc.view;

import java.util.Scanner;

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

	public static void login() {
		
	}

	public static void register() {
		
	}

	public static void printMenu() {
		System.out.println("┌──── 도서관프로그램 ─────┐");
		System.out.println("│ 1. 가입하기   2. 로그인  9. 종료 │");
		System.out.println("└─────────────────┘");
	}
	
	public static void printUserMenu() {
		
	}
}
