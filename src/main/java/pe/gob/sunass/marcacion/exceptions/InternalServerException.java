package pe.gob.sunass.marcacion.exceptions;

import jakarta.servlet.ServletException;

public class InternalServerException extends ServletException {
    public InternalServerException(String message) {
      super(message);
    }
}