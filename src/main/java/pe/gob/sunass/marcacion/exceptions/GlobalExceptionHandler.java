package pe.gob.sunass.marcacion.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> badRequestExceptionHandler(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El cuerpo del parámetro no puede estar vacío");
    }
	
	@ExceptionHandler(LocalNotFoundException.class)
    public ResponseEntity<?> localNotFoundException(LocalNotFoundException exception){
		
		Map<String, Object> customError =new HashMap<>();
		customError.put("message", exception.getMessage());
		
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(customError);
    }
}
