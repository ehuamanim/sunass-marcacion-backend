package pe.gob.sunass.marcacion.apirest.commons;

import org.apache.commons.codec.binary.Base64;

public class Encode {

	public static String encodeBase64( String value ) {
		String encoded = new String( Base64.encodeBase64(value.getBytes() ) ); 
		return encoded;
	}
	
}
