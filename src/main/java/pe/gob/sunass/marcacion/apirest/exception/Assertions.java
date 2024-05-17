package pe.gob.sunass.marcacion.apirest.exception;

public class Assertions {

	public static void assertTrue(boolean value, String message) throws Exception {
		
		if( !value ) {
			throw new Exception(message);
		}
		
	}
	
	
}
