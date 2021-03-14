package kosta.mvc.session;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kosta.mvc.controller.CartController;
import kosta.mvc.model.dto.BookDTO;

/**
 * 사용자 객체
 * */
public class Session {
	private String sessionId;
	private Map<String,Object> attributes; // 책바구니
	
	
	public Session() {}
	public Session(String sessionId) {
		this.sessionId = sessionId;
		attributes = new HashMap<>();
	}
	public String getSessionId() {
		return sessionId;
	}
	
	//추가
	public void setAttribute(String name, Object value) {// cart , Map<Book, Integer>
		attributes.put(name,value);
	}
	
	//조회(Map에 key에 해당하는 value 찾기)
	public Object getAttribute(String name) {//cart
		return attributes.get(name);
	}
	
	//제거(책바구니를 비울대 사용한다)
	public void removeAttribute(String name) {//cart
		attributes.remove(name);
	}
	
	/**
	 * 책바구니 안 품목 List로 리턴해주는 메소드
	 */
	public List<BookDTO> booksInCart(Map<String, Object> map, String mID) {
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(mID);
		
		List<BookDTO> list = (List<BookDTO>) session.getAttribute(mID);
		
		for(BookDTO bookDTO : list) {
			list.add(bookDTO);
		}
		
		return list;
	}
	
	/**
	 * 책바구니 안 품목 개별삭제 메소드
	 */
	public void removeItem(List<Object> list , String mID, int bISBN) {
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(mID);
		
		
//		List<BookDTO> bookList = session.booksInCart(attributes, mID);
		List<BookDTO> bookList = CartController.getBookDTOInCart(mID);
		
		
		for(BookDTO bookDTO : bookList) {
			if(bookDTO.getbIsbn()==bISBN) {
				bookList.remove(bookDTO);
			}
		}
		
	}
	
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	
	@Override
	public String toString() {
		return sessionId + "님 로그인" + attributes;
	}
	
	
	@Override
	public int hashCode() {
		return sessionId.hashCode();
	}
	
	/**
	 * 같은 객체라는 뜻은 hashCode가 같아야하고,
	 * equlas의 결과가 true여야한다.
	 * 
	 *  hash코드가 다르면 무조건 다른 객체
	 *  hash코드가 같으면 같은 객체일수도, 다른 객체일수도 있다.
	 * */
	@Override
	public boolean equals(Object obj) {
		Session other = (Session) obj;
		if(sessionId.equals(other.sessionId)) {
			return true;
		}else {
			return false;
		}
		
	}
	
}