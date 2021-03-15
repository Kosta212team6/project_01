package kosta.mvc.controller;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.exception.NotFoundException;
import kosta.mvc.model.dto.BookDTO;
import kosta.mvc.model.service.BookService;
import kosta.mvc.model.service.BookServiceImpl;
import kosta.mvc.view.FailView;
import kosta.mvc.view.SuccessView;

public class BookController {

	private static BookService bookService = new BookServiceImpl();

	/**
	 * 도서추가
	 */
	public static void InsertBook(BookDTO bookDTO) {

		try {
			bookService.InsertBook(bookDTO);
			SuccessView.printMessage("도서가 등록되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}

	public static void UpdateBookISBN(int bISBN, int newISBN) {

		try {
			bookService.UpdateBookISBN(bISBN, newISBN);
			SuccessView.printMessage("책의 ISBN이 수정되었습니다");
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	public static void UpdateBookName(BookDTO bookDTO) {
		try {
			bookService.UpdateBookName(bookDTO);
			SuccessView.printMessage("도서명이 수정되었습니다");
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	public static void UpdateBookWrite(BookDTO bookDTO) {
		try {
			bookService.UpdateBookWrite(bookDTO);
			SuccessView.printMessage("저자가 수정되었습니다");
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}

	}

	public static void UpdateBookPub(BookDTO bookDTO) {
		try {
			bookService.UpdateBookPub(bookDTO);
			SuccessView.printMessage("출판사가 수정되었습니다");
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	public static void UpdateBookDate(BookDTO bookDTO) {
		try {
			bookService.UpdateBookDate(bookDTO);
			SuccessView.printMessage("발행년월일이 수정되었습니다");
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	public static void UpdateBookCode(BookDTO bookDTO) {
		try {
			bookService.UpdateBookCode(bookDTO);
			SuccessView.printMessage("분류코드가 수정되었습니다");
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	public static void deleteBook(int bISBN) {
		try {
			bookService.DeleteBook(bISBN);
			SuccessView.printMessage("도서정보가 삭제되었습니다 !");
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	public static BookDTO bookSelectByBisbn(int bISBN) {
		try {
			BookDTO bookDTO = bookService.bookSelectByBisbn(bISBN);
			SuccessView.printOnlyBook(bookDTO);
			return bookDTO;
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		return null;
	}

	public static void bookSelectByWriter(String bWrite) {
		try {
			List<BookDTO> list = bookService.bookSelectByWriter(bWrite);
			SuccessView.printBooks(list);

		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}

	}

	public static void bookSelectByPublisher(String bPub) {
		try {
			List<BookDTO> list = bookService.bookSelectByPublisher(bPub);
			SuccessView.printBooks(list);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}

	}

	public static void bookSelectBySname(String sName) {
		try {
			List<BookDTO> list = bookService.bookSelectBySname(sName);
			SuccessView.printBooks(list);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}

	}

	public static void bookSelectByBname(String bName) {
		try {
			List<BookDTO> list = bookService.bookSelectByBname(bName);
			SuccessView.printBooks(list);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

}
