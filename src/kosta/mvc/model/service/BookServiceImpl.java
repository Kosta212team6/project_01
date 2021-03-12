package kosta.mvc.model.service;

import java.sql.SQLException;

import kosta.mvc.model.dao.BookDAO;
import kosta.mvc.model.dao.BookDAOImpl;
import kosta.mvc.model.dto.BookDTO;

public class BookServiceImpl implements BookService {
	
	private BookDAO bookDAO = new BookDAOImpl();

	@Override
	public void InsertBook(BookDTO bookDTO) throws SQLException {
		int result = bookDAO.InsertBook(bookDTO);
		if(result==0) {
			throw new SQLException("오류 : 등록되지 않았습니다");
		}
	}

}
