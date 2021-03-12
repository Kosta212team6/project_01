package kosta.mvc.controller;

import java.sql.SQLException;

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
			FailView.errorMessage(e.getMessage());
		}
	}

}
