package pe.gob.sunass.marcacion.exceptions;

import jakarta.servlet.ServletException;

public class BadRequestException extends ServletException {
    public BadRequestException(String message) {
        super(message);
    }
}
