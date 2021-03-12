package kosta.mvc.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import kosta.mvc.model.dto.RentDTO;

public interface RentDAO {
 /**
  * 대여하기 ( 대여번호 PK   | 	ISBN FK(반납TABLE)  |  아이디 FK(멤버TABLE)  |  대여일자  |  반납예정일  |  대여상태
  *  
  *  1) 대여하려는 책 BISBN으로 검색
  *   >		SELECT * FROM BOOK WHERE BISBN='9788936434267';
  *  
  *  2) 대여하기(대여 테이블에 insert)
  *  
  *  	INSERT INTO RENT(RNUM, RDATE, REXDATE, RSTATUS, BISBN, MID)
		VALUES(RNUM_SEQ.NEXTVAL, SYSDATE, SYSDATE + 7, 1, 9788936434267, 'choi');
		
  *  3) 대여상태(rStatus) 1에서 0으로 변경시키기
  *  
  *  	UPDATE BOOK SET BSTATUS = 0 WHERE BISBN=9788936434267;
  *  
  *  4) 대여한 도서내역 검색하기
  * 	SELECT RENT.RNUM, RENT.REXDATE, BOOK.BNAME
		FROM RENT JOIN BOOK
		USING(BISBN) WHERE MID='choi';
		
		SELECT * FROM BOOK
		WHERE BSTATUS = 1;
  */
	
	
	
	
	/**
	 * 대여테이블에 넣어주기
	 */
	int RentInsert(Connection con, RentDTO rentDTO)throws SQLException; 
	
	/**
	 * mID로 대여한 도서 검색하기
	 */
	List<RentDTO> selectRentByUserId(String mID)throws SQLException;
	
	
}
