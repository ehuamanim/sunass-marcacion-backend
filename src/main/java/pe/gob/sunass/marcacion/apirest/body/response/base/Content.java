package pe.gob.sunass.marcacion.apirest.body.response.base;

public class Content {
	private String mimeType;
    private String mimeTypeName;
    private Long sizeInBytes;
    private String encoding;
    
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getMimeTypeName() {
		return mimeTypeName;
	}
	public void setMimeTypeName(String mimeTypeName) {
		this.mimeTypeName = mimeTypeName;
	}
	public Long getSizeInBytes() {
		return sizeInBytes;
	}
	public void setSizeInBytes(Long sizeInBytes) {
		this.sizeInBytes = sizeInBytes;
	}
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
    
    
}
