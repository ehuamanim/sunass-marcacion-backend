package pe.gob.sunass.marcacion.apirest.alfresco.constant;

public class Constants {

	public static final String NAMESPACE_MODEL = "http://www.alfresco.org/model/content/1.0";
	
	public static final String DEBUG_REPOSITORY = "/Company Home/";
	
	public static final String ATOMB_RESOURCE = "/alfresco/api/-default-/public/cmis/versions/1.1/atom";
	public static final String REST_RESOURCE = "/alfresco/api/-default-/public";
	
	public static String createQNameString(String namespace, String name) {
		return "{" + namespace + "}" + name;
	}
}
