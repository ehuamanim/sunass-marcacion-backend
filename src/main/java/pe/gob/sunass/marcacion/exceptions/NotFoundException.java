package pe.gob.sunass.marcacion.exceptions;

import jakarta.servlet.ServletException;

public class NotFoundException extends ServletException {
    public NotFoundException(String message) {
      super(message);
    }
}