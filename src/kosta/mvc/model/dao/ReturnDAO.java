package kosta.mvc.model.dao;

import java.sql.SQLException;

public interface ReturnDAO {

/*
	-- 반납 데이터베이스에 업로드
	INSERT INTO RETURN(RNUM, RTDATE, BISBN)
	VALUES(RNUM_SEQ.CURRVAL, SYSDATE+1, 9788936434267);
	-- 대여 상태변수 변경
	UPDATE RENT SET RSTATUS = 0 WHERE MID='choi';
	-- 예약이 존재하는지 확인 RETURN, RSV 자바에서 .next()써서 null이면 예약 없음
	SELECT MID, BISBN, RSVNUM, RSVDATE
	FROM RETURN JOIN RSV
	USING(BISBN);
	-- 책 상태변수 증가
	UPDATE BOOK SET BSTATUS = 1 WHERE BISBN=9788936434267;
*/

	/**
	 * 반납하기
	 */
	int returnBooks(String mID) throws SQLException;

}
