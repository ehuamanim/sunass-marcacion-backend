package pe.gob.sunass.marcacion.apirest.exception;

public class AlfrescoConnectorException extends RuntimeException {


	private static final long serialVersionUID = 1L;
	
	private Integer errorCode;
	
	public AlfrescoConnectorException( String message, Integer errorCode ) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public AlfrescoConnectorException( String message, Integer errorCode, Throwable cause ) {
		super(message, cause);
		this.errorCode = errorCode;
	}
	
	public Integer getErrorCode() {
		return this.errorCode;
	}
	
}
