package kosta.mvc.view;

import java.sql.SQLException;import java.util.List;
import java.util.Scanner;

import kosta.mvc.controller.BookController;
import kosta.mvc.controller.CartController;
import kosta.mvc.controller.MemberController;
import kosta.mvc.controller.RentController;
import kosta.mvc.exception.StringFormatException;
import kosta.mvc.model.dto.BookDTO;
import kosta.mvc.model.dto.MemberDTO;
import kosta.mvc.session.Session;
import kosta.mvc.session.SessionSet;

public class MenuView {

	private static Scanner sc = new Scanner(System.in);

	public static void menu() {
		while (true) {
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
		menu();
	}

	/**
	 * 가입하기 메뉴
	 */
	public static void register() {
		System.out.println("회원가입을 시작합니다");
		System.out.print("ID > ");
		String mId = sc.nextLine();
		printCheckIDMember(mId);
		System.out.print("Password > ");
		String mPwd = sc.nextLine();
		System.out.print("Password를 다시 입력해주세요 > ");
		String mPwd2 = sc.nextLine();
		printCheckPwd(mPwd, mPwd2);
		System.out.print("name > ");
		String mName = sc.nextLine();
		System.out.print("phone > ");
		String mPhone = sc.nextLine();

		MemberDTO memberDTO = new MemberDTO(mId, mName, mPhone, mPwd, "sysdate", 1, 10);
		MemberController.createMember(memberDTO);
		System.out.println("\"" + memberDTO.getmID() + "\" 아이디로 가입되었습니다");
	}

	/**
	 * 아이디 유효성체크
	 */
	public static void printCheckIDMember(String NowID) {
		boolean result = MemberController.checkIDMember(NowID);
		if (result == true) {
			// 아이디 중복체크
			System.out.println("다른 아이디를 입력해주세요");
			register();
		} else if (NowID.length() < 3) {
			// 아이디 자릿수체크
			System.out.println("아이디는 3자 이상 입력해주세요");
			register();
		} else {
			System.out.println("사용 가능한 아이디입니다");
		}
	}

	/**
	 * 비밀번호 재확인
	 */
	public static void printCheckPwd(String pwd, String pwd2) {
		if (pwd.equals(pwd2)) {
			System.out.println("비밀번호가 일치합니다");
		} else {
			System.out.println("비밀번호가 일치하지 않습니다. 처음부터 다시 작성해주세요");
			register();
		}
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
		while (true) {
			SessionSet ss = SessionSet.getInstance();
			System.out.println(ss.getSet());
			System.out.println(mID + "님 로그인 하셨습니다.");
			System.out.println("1. 로그아웃   2. 검색하기   3. 책바구니 담기   4. 책바구니 보기   5. 마이서재");

			try {
				int menu = Integer.parseInt(sc.nextLine());
				switch (menu) {
				case 1:
					System.out.println("로그아웃 합니다");
					logout(mID);
				case 2:
					System.out.println("책을 검색합니다");
					printBookSearchMenu(mID);
					break;
				case 3:
					printPutCart(mID);
					break;
				case 4:
					printBookCartMenu(mID);
					break;
				case 5:
					System.out.println("마이서재를 엽니다");
					printMyLibary(mID);
					break;
				default:
					System.out.println("메뉴번호에 해당하는 번호를 입력해주십시오.");
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("숫자만 입력해주세요!");
			}
		}
	}

	/**
	 * 책바구니 보기 메뉴
	 */
	public static void printBookCartMenu(String mId) {
		if (RentController.isEmptyCart(mId)) {
			while (true) {
				SessionSet ss = SessionSet.getInstance();
				System.out.println(ss.getSet());

				System.out.println("책바구니에 담은 책을 봅니다");
				System.out.println("1. 전체 대여   2. 책바구니 도서 삭제   " + "3. 책바구니 비우기   4. 뒤로 가기");
				try {
					int menu = Integer.parseInt(sc.nextLine());
					switch (menu) {
					case 1:
						rentForSure(mId);
						break;
					case 2:
						deleteForSure(mId);
						break;
					case 3:
						clearForSure(mId);
						break;
					case 4:
						printUserMenu(mId);
						break;
					default:
						System.out.println("메뉴번호에 해당하는 번호를 입력해주십시오.");
						break;
					}
				} catch (NumberFormatException e) {
					System.out.println("숫자만 입력해주세요");
				}
			}
		} else {
			FailView.errorMessage("먼저 책을 책바구니에 담고 다시 시도해주세요.");
			printUserMenu(mId);
		}
	}

	/**
	 * 도서 검색 메뉴
	 */
	public static void printBookSearchMenu(String mID) {
		while (true) {
			SessionSet ss = SessionSet.getInstance();
			// System.out.println(ss.getSet());
			System.out.println("1. 분야별 검색  2. 도서명 검색  3. 저자검색  4. 출판사 검색   5. 뒤로가기");
			try {
				int menu = Integer.parseInt(sc.nextLine());
				switch (menu) {
				case 1:
					System.out.println("분야별 검색합니다.");
					printSelectBySname();
					break;
				case 2:
					System.out.println("도서명 검색합니다.");
					printSelectByBname();

					break;
				case 3:
					System.out.println("저자 검색합니다.");
					printSelectByWriter();
					break;
				case 4:
					System.out.println("출판사 검색합니다.");
					printSelectByPublisher();
					break;

				case 5:
					System.out.println("뒤로가기...");
					return;

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
	 * 도서명 검색
	 */
	public static void printSelectByBname() {
		System.out.print("도서명을 입력하세요 >");

		String bName = sc.nextLine();
		BookController.bookSelectByBname(bName);

	}

	/**
	 * 분야별 검색
	 */
	public static void printSelectBySname() {
		while (true) {
			System.out.println("분야를 선택하세요 >");
			System.out.println(
					"1.총류  2. 철학  3. 종교   4. 사회과학    5. 자연과학  6. 기술과학   7. 예술   8. 언어   9.문학   10. 역사   0. 뒤로가기");
			String sName = null;
			try {
				int menu = Integer.parseInt(sc.nextLine());
				switch (menu) {
				case 1:
					System.out.println("총류로 검색합니다.");
					sName = "총류";
					BookController.bookSelectBySname(sName);
					break;
				case 2:
					System.out.println("철학으로 검색합니다..");
					sName = "철학";
					BookController.bookSelectBySname(sName);
					break;
				case 3:
					System.out.println("종교로 검색합니다..");
					sName = "종교";
					BookController.bookSelectBySname(sName);
					break;
				case 4:
					System.out.println("사회과학으로 검색합니다..");
					sName = "사회과학";
					BookController.bookSelectBySname(sName);
					break;

				case 5:
					System.out.println("자연과학으로 검색합니다..");
					sName = "자연과학";
					BookController.bookSelectBySname(sName);
					break;

				case 6:
					System.out.println("기술과학으로 검색합니다..");
					sName = "기술과학";
					BookController.bookSelectBySname(sName);
					break;

				case 7:
					System.out.println("예술로 검색합니다..");
					sName = "예술";
					BookController.bookSelectBySname(sName);
					break;

				case 8:
					System.out.println("언어로 검색합니다..");
					sName = "언어";
					BookController.bookSelectBySname(sName);
					break;

				case 9:
					System.out.println("문학으로 검색합니다..");
					sName = "문학";
					BookController.bookSelectBySname(sName);
					break;

				case 10:
					System.out.println("역사로 검색합니다..");
					sName = "역사";
					BookController.bookSelectBySname(sName);
					break;

				case 0:
					System.out.println("뒤로가기...");
					return;

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
	 * 마이서재 메뉴
	 */
	public static void printMyLibary(String mID) {
		while (true) {
			SessionSet ss = SessionSet.getInstance();
			System.out.println(ss.getSet());
			System.out.println(mID + "님 마이서재.");
			System.out.println("1. 대여한 도서보기   2. 예약한 도서보기   3. 반납하기   4. 내 회원정보 열람  5. 내정보 수정   0. 뒤로가기..");
			try {
				int menu = Integer.parseInt(sc.nextLine());
				switch (menu) {
				case 1:
					System.out.println("대여한 도서보기");
					// RentController.rentBookList(mID);
					break;
				case 2:
					System.out.println("예약한 도서보기");
					// RsvController.reservBookList(mID);

					break;
				case 3:
					System.out.println("책반납하기");
					// printPutCart(mID);
					break;
				case 4:
					System.out.println("내 회원정보 열람");
					printMyInFo(mID);
					break;
				case 5:
					System.out.println("내 정보 수정");
					loginForChangeMyInFo(mID);
					break;
				case 0:
					System.out.println("뒤로가기.. ");
					return;
				default:
					System.out.println("메뉴번호에 해당하는 번호를 입력해주십시오.");
					break;
				}

			} catch (NumberFormatException e) {
				System.out.println("숫자만 입력해주세요!");
			}
		}

	}

	public static void loginForChangeMyInFo(String mID) {
		System.out.print("정보수정을 위해 비밀번호를 입력하세요: ");
		String mPwd = sc.nextLine();

		MemberController.loginForChangeInfo(mID, mPwd);
	}

	/**
	 * 내 정보 수정
	 */

	public static void printModifyMyInFo(String mID) {
		while (true) {
			SessionSet ss = SessionSet.getInstance();
			System.out.println(ss.getSet());
			System.out.println(mID + "님 마이서재.");
			System.out.println("1. 비밀번호 변경   2. 전화번호 변경   3. 탈퇴  4.뒤로가기 ");
			try {
				int menu = Integer.parseInt(sc.nextLine());
				switch (menu) {
				case 1:
					System.out.println("비밀번호 변경");
					changePassWord(mID);
					break;
				case 2:
					System.out.println("전화번호 변경");
					changePhoneNumber(mID);
					break;

				case 3:
					System.out.println("탈퇴하기.");
					cancelMyAccount(mID);
					return;

				case 4:
					return;

				default:
					System.out.println("메뉴번호에 해당하는 번호를 입력해주십시오.");
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("숫자만 입력해주세요!");
			}
		}
	}

	/**
	 * 탈퇴
	 */
	public static void cancelMyAccount(String mID) {
		System.out.println(mID + "계정 탈퇴 하시겠습니까? (Y / N)");
		String ans = sc.nextLine();
		if (ans.equalsIgnoreCase("y")) {
			System.out.println("비밀번호를 입력하세요 > ");
			String mPwd = sc.nextLine();
			MemberController.cancelAccount(mID, mPwd);
		} else if (ans.equalsIgnoreCase("n")) {
			printModifyMyInFo(mID);
		} else {
			System.out.println("올바르지 않은 답변입니다. 처음부터 다시 진행하세요");
			printMyLibary(mID);
		}

	}

	/**
	 * 내 정보 열람
	 */
	public static void printMyInFo(String mID) {
		MemberController.myInfo(mID);
	}

	/**
	 * 비밀번호 변경
	 */
	public static void changePassWord(String mID) {
		System.out.print("새로운 비밀번호를 입력하세요 > ");
		String mPwd = sc.nextLine();
		MemberController.UpdatePassWord(mID, mPwd);

	}

	/**
	 * 전화번호 변경
	 */
	public static void changePhoneNumber(String mID) {
		System.out.print("새로운 전화번호를 입력하세요 > ");
		String mPhone = sc.nextLine();
		if(mPhone.length() <11 || mPhone.length()>11) {
			System.out.println("전화번호 길이에 문제가 있습니다.");
			return;
		}
		MemberController.UpdatePhoneNumber(mID, mPhone);

	}

	/**
	 * 출판사 검색
	 */

	public static void printSelectByPublisher() {
		System.out.print("출판사를 입력하세요 >");
		String bPub = sc.nextLine();
		BookController.bookSelectByPublisher(bPub);
	}

	/**
	 * 저자로 검색
	 */

	public static void printSelectByWriter() {
		System.out.print("저자를 입력하세요 >");
		String bWrite = sc.nextLine();
		BookController.bookSelectByWriter(bWrite);
	}

	/**
	 * ISBN으로 검색
	 */

	public static void printSelectByISBN() {
		System.out.print(" > ");
		int bISBN = Integer.parseInt(sc.nextLine());
		BookController.bookSelectByBisbn(bISBN);
	}

	/**
	 * 책바구니 담기
	 */
//	
//}
	public static void printPutCart(String mID) {
		System.out.println("책바구니에 책을 담습니다");
		System.out.print("ISBN > ");
		int bISBN = Integer.parseInt(sc.nextLine());
		int bStatus = (BookController.bookSelectByBisbn(bISBN)).getbStatus();
		CartController.putCart(mID, bISBN);

	}

	/**
	 * 책바구니 안에 있는 도서 목록 대여여부 묻는 메뉴
	 */
	public static void rentForSure(String mID) {

		List<BookDTO> list = CartController.getBookDTOInCart(mID);

		System.out.print("현재 담겨져 있는 도서 목록을 대여하시겠습니까? y | n  ▷  ");

		String rent = sc.nextLine();
		if (rent.equals("y")) {
			RentController.insertRents(list, mID);
			SuccessView.printMessage("대여 성공!!!!!");
		} else if (rent.equals("n")) {
			printUserMenu(mID);
		} else {
			System.out.println("[ yes or no ] 로 입력해주세요");
			rentForSure(mID);
		}

	}

	/**
	 * 책바구니 안에 있는 도서 bISBN으로 선택해서 삭제 여부 묻는 메뉴
	 */
	public static void deleteForSure(String mID) {
		if (RentController.isEmptyCart(mID)) {
			System.out.println("목록에서 삭제할 책의 ISBN을 입력해 주세요  ▷  ");
			int bISBN = Integer.parseInt(sc.nextLine());
			RentController.deleteCart(mID, bISBN);
		} else {
			failToDeleteMenu(mID);
		}
	}

	/**
	 * 책바구니 안에 있는 도서 삭제 실패시 보여주는 메뉴
	 */
	public static void failToDeleteMenu(String mID) {
		System.out.println("┌※ 해당 책이 책바구니 내에 존재하지 않습니다. ※┐");
		System.out.println("│                                                │");
		System.out.println("└────메인메뉴로 돌아가시겠습니까 ? ─────┘");
		System.out.println("		y | n  ▷ ");
		String answer = sc.nextLine();
		if (answer.equals("y")) {
			printUserMenu(mID);
		} else if (answer.equals("n")) {
			deleteForSure(mID);
		} else {
			System.out.println("[ yes or no ] 로 입력해주세요");
			failToDeleteMenu(mID);
		}

	}

	/**
	 * 책바구니 안에 있는 도서 목록 비우기 여부 묻는 메뉴
	 */

	public static void clearForSure(String mID) {
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(mID);
		if (RentController.isEmptyCart(mID)) {
			System.out.println("책바구니를 모두 비우시겠습니까? y | n   ▷  ");
			String clear = sc.nextLine();
			if (clear.equals("y")) {
				RentController.clearCart(mID);
				System.out.println("책바구니 목록이 비워졌습니다.");
				printUserMenu(mID);
			} else if (clear.equals("n")) {
				System.out.println("회원메뉴로 돌아갑니다");
				printUserMenu(mID);
			} else {
				System.out.println("[ yes or no ] 로 입력해주세요 ");
				clearForSure(mID);
			}

		}
	}

	/**
	 * 관리자용 메인메뉴
	 */
	public static void printAdminMenu(String mID) {
		while (true) {
			SessionSet ss = SessionSet.getInstance();
			// System.out.println(ss.getSet());
			System.out.println(mID + "님 관리자로 로그인 하셨습니다.");
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
					printBookSearchMenu(mID); // 검색기능호출
					break;
				case 3:
					System.out.println("도서관리를 선택하셨습니다");
					MenuView.printAdminBookDMLMenu(mID);
					break;
				case 4:
					System.out.println("회원관리를 선택하셨습니다");
					printAdminMemberMenu(mID);
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
	 * 관리자용 회원 관리 메뉴
	 */
	public static void printAdminMemberMenu(String mID) {
		while (true) {
			SessionSet ss = SessionSet.getInstance();
			System.out.println(ss.getSet());
			System.out.println(mID + "님 관리자로 로그인 하셨습니다.");

			System.out.println("1. 전체회원보기   2. 연체회원보기   9. 뒤로가기");
			try {
				int menu = Integer.parseInt(sc.nextLine());
				switch (menu) {
				case 1:
					System.out.println("전체회원을 출력합니다");
					printMemberSelectAll();
					break;
				case 2:
					System.out.println("현재 연체중인 회원을 출력합니다");
					printOverdueMember();
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
	}

	/**
	 * 전체회원 출력
	 */
	public static void printMemberSelectAll() {
		MemberController.memberSelectAll();
	}

	/**
	 * 현재 연체중인 회원 출력
	 */
	public static void printOverdueMember() {
		MemberController.overdueMember();
	}

	/**
	 * 관리자용 도서 관리 메뉴
	 */
	public static void printAdminBookDMLMenu(String mID) {
		while (true) {
			SessionSet ss = SessionSet.getInstance();
			System.out.println(ss.getSet());
			System.out.println(mID + "님 관리자로 로그인 하셨습니다.");

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
					printAdminUpdateBook();
					break;
				case 3:
					System.out.println("도서정보를 삭제합니다");
					printAdminDeleteBook();
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
	}

	/**
	 * 도서추가
	 */
	public static void printAdminInsertBook() {
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
				printAdminUpdateBookISBN(bISBN);
				break;
			case 2:
				System.out.println("도서명을 수정합니다");
				printAdminUpdateBookName(bISBN);
				break;
			case 3:
				System.out.println("저자를 수정합니다");
				printAdminUpdateBookWrite(bISBN);
				break;
			case 4:
				System.out.println("출판사를 수정합니다");
				printAdminUpdateBookPub(bISBN);
				break;
			case 5:
				System.out.println("발행년월일을 수정합니다");
				printAdminUpdateBookDate(bISBN);
				break;
			case 6:
				System.out.println("분류코드를 수정합니다");
				printAdminUpdateBookCode(bISBN);
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
	 * ISBN 수정
	 */
	public static void printAdminUpdateBookISBN(int bISBN) {
		try {
			System.out.print("ISBN > ");
			int newISBN = Integer.parseInt(sc.nextLine());
			BookController.UpdateBookISBN(bISBN, newISBN);
		} catch (NumberFormatException e) {
			System.out.println("ISBN은 숫자만 입력 가능합니다");
		}
	}

	/**
	 * 도서명 수정
	 */
	public static void printAdminUpdateBookName(int bISBN) {
		System.out.print("도서명 > ");
		String bName = sc.nextLine();
		BookDTO bookDTO = new BookDTO(bISBN, bName, null, null, null, 0, 0);
		BookController.UpdateBookName(bookDTO);
	}

	/**
	 * 저자 수정
	 */
	public static void printAdminUpdateBookWrite(int bISBN) {
		System.out.print("저자 > ");
		String bWrite = sc.nextLine();
		BookDTO bookDTO = new BookDTO(bISBN, null, bWrite, null, null, 0, 0);
		BookController.UpdateBookWrite(bookDTO);
	}

	/**
	 * 출판사 수정
	 */
	public static void printAdminUpdateBookPub(int bISBN) {
		System.out.print("출판사 > ");
		String bPub = sc.nextLine();
		BookDTO bookDTO = new BookDTO(bISBN, null, null, bPub, null, 0, 0);
		BookController.UpdateBookPub(bookDTO);
	}

	/**
	 * 발행년월일 수정
	 */
	public static void printAdminUpdateBookDate(int bISBN) {
		System.out.print("발행년월일 > ");
		String bDate = sc.nextLine();
		BookDTO bookDTO = new BookDTO(bISBN, null, null, null, bDate, 0, 0);
		BookController.UpdateBookDate(bookDTO);
	}

	/**
	 * 분류코드 수정
	 */
	public static void printAdminUpdateBookCode(int bISBN) {
		try {
			System.out.print("분류코드 > ");
			int sCode = Integer.parseInt(sc.nextLine());
			BookDTO bookDTO = new BookDTO(bISBN, null, null, null, null, 0, sCode);
			BookController.UpdateBookCode(bookDTO);
		} catch (NumberFormatException e) {
			System.out.println("분류코드는 숫자만 입력 가능합니다");
		}

	}

	/**
	 * 도서정보 삭제
	 */
	public static void printAdminDeleteBook() {
		try {
			System.out.println("삭제 할 도서의 ISBN을 입력하세요");
			System.out.print(" > ");
			int bISBN = Integer.parseInt(sc.nextLine());
			BookController.deleteBook(bISBN);

		} catch (NumberFormatException e) {
			System.out.println("ISBN은 숫자만 입력 가능합니다");
		}

	}

}
