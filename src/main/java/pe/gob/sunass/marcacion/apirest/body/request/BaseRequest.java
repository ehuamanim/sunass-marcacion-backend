package pe.gob.sunass.marcacion.apirest.body.request;

import com.google.gson.Gson;

public class BaseRequest {

	public String toJsonString() {
		
		String jsonObject = new Gson().toJson(this);
		return jsonObject;
		
	}
	
}
