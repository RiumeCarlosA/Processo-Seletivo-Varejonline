package br.com.varejonline.riume.exception;

public class MovimentacaoInvalid extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public MovimentacaoInvalid(String message, Throwable cause) {
		super(message, cause);
	}
	
	public MovimentacaoInvalid(String message) {
		super(message);
	}
	
	
}
