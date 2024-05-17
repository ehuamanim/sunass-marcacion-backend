package pe.gob.sunass.marcacion.apirest.alfresco.constant;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public enum HttpParameter {
	
	MAX_ITEMS ("maxItems"),
	RELATIVE_PATH ("relativePath"),
	INCLUDE("include");
	private String param;
	
	
	private HttpParameter( String param ) {
		this.param = param;
	}
	
	public String param() {
		return this.param;
	}
	
	public String parameWithValue(String value) {
		
		String param = this.param.concat("=").concat(value);
		try {
			param = this.param.concat("=").concat(URLEncoder.encode(value, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			System.out.println( "Error al pasar los parametros: " + e.getMessage() );
		}
		return param;
		
	}
	
}
