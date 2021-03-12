package kosta.mvc.model.dao;

import java.sql.SQLException;

import kosta.mvc.model.dto.BookDTO;

public interface BookDAO {

	int InsertBook(BookDTO bookDTO) throws SQLException;
}
