package pe.gob.sunass.marcacion.apirest.commons;

import org.apache.http.NameValuePair;

public class Header implements NameValuePair{

	private String name;
	private String value;

	public Header(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}
	
}
