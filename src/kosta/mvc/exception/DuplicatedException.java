package kosta.mvc.exception;

public class DuplicatedException extends Exception {
	public DuplicatedException() {
	
	}
	
	public DuplicatedException(String message) {
		super(message);
	}
}
