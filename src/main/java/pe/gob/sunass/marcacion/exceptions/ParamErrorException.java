package pe.gob.sunass.marcacion.exceptions;

public class ParamErrorException extends RuntimeException {
	 
    public ParamErrorException() {
    }
 
    public ParamErrorException(String message) {
        super(message);
    }
 
}