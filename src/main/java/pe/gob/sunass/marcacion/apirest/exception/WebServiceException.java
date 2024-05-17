package pe.gob.sunass.marcacion.apirest.exception;

public class WebServiceException extends RuntimeException {
	  private static final long serialVersionUID = 8884198261405846600L;
	  
	  public WebServiceException(String message) {
	    super(message);
	  }
	  
	  public WebServiceException(String message, Throwable exception) {
	    super(message, exception);
	  }
	}
