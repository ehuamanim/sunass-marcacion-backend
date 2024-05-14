package pe.gob.sunass.marcacion.exceptions;

import jakarta.servlet.ServletException;

public class UnAuthorizedException extends ServletException{
	public UnAuthorizedException(String message) {
		super(message);
	}
}
