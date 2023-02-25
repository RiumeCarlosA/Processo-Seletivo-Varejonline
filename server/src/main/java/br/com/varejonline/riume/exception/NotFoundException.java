package br.com.varejonline.riume.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotFoundException(String message) {
		super(message);
	}
	
	public NotFoundException(String message, Throwable t) {
	    super(message, t);
	}
}
