package pe.gob.sunass.marcacion.apirest.body.response;

public class BaseResponse {
	
	protected ErrorResponse error;

	public ErrorResponse getError() {
		return error;
	}

	public void setError(ErrorResponse error) {
		this.error = error;
	}
	
	public boolean hasError() {
		return error!=null;
	}
	
	
}
