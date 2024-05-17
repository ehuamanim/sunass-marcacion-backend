package pe.gob.sunass.marcacion.apirest.commons;

import java.util.HashMap;
import java.util.Map;

public class APIConstant {

	public static final Map<String, String> NODE_TYPE;
	
	static {
		
		Map<String, String>  constants = new HashMap<String, String>();
		constants.put("file", "cm:content");
		
		NODE_TYPE = constants;
		
	}
	
}
