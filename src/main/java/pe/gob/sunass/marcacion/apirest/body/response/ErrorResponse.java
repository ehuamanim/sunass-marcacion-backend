package pe.gob.sunass.marcacion.apirest.body.response;

public class ErrorResponse {

	private String errorKey;
	private Integer statusCode;
	private String briefSumary;
	private String stackTrace;
	private String descriptionURL;
	
	public String getErrorKey() {
		return errorKey;
	}
	public void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public String getBriefSumary() {
		return briefSumary;
	}
	public void setBriefSumary(String briefSumary) {
		this.briefSumary = briefSumary;
	}
	public String getStackTrace() {
		return stackTrace;
	}
	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}
	public String getDescriptionURL() {
		return descriptionURL;
	}
	public void setDescriptionURL(String descriptionURL) {
		this.descriptionURL = descriptionURL;
	}
	
}
