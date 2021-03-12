package kosta.mvc.exception;

/**
 * 탈퇴 회원 로그인시, 로그인을 막는 예외
 */
public class NotAnymoreMemberException extends Exception {

	public NotAnymoreMemberException() {}
	
	public NotAnymoreMemberException(String message) {
		super(message);
	}
}
