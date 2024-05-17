package pe.gob.sunass.marcacion.apirest.commons;

import java.util.List;
import java.util.Map;

public class StringUtil {

	public static final String ID_EMPTY = "-1";
	
	public static String replace( String source, String findText, String replaceText ) {
		String newSource = source.replaceAll("\\{".concat(findText).concat("\\}"), replaceText);
		return newSource;
	}
	
	public static String join( String value, String... values ) {
		for( String v: values ) {
			value = value.concat(v);
		}
		
		return value;
	}
	
	public static String randomText(int longitud) {
		
		StringBuffer randomText = new StringBuffer();
		String universe = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_";
		int top = 0;
		
		while ( (top++) < longitud ){
			int randomIndex = new Long( Math.round( Math.random() * ( universe.length() - 1 ) ) ).intValue();
			randomText.append( universe.charAt(randomIndex) );
		}
		
		return randomText.toString();
		
	}
	
	public static boolean isEmpty( Object value) {
		
		if( value == null ) {
			return true;
		}
		
		if( value instanceof String ) {
			return ((String) value).isEmpty();
		}
		
		return false;
		
	}
	
	public static String switchValue( String valuePrimary, String vaueDefault ) {
		
		if( isEmpty(valuePrimary) ) {
			return vaueDefault;
		}
		
		return valuePrimary;
		
	}
	
	public static String toHtmlParams(Map<String, String> map) {
		
		StringBuffer sb = new StringBuffer();
		
		for( Map.Entry<String, String> dt: map.entrySet() ) {
			sb.append(dt.getKey())
			.append("=")
			.append(dt.getValue())
			.append("&");
		}
		
		sb.append("random=")
		.append(randomText(5));
		
		return sb.toString();
		
	}
	
	public static void removeIfExist( List<Object> list, String value ) {
		
		int indice = 0;
		
		for( Object obj: list ) {
			
			if(  obj != null && obj.toString().equals(value) ) {
				list.remove(indice);
				break;
			}
			indice++;
			
		}
		
	} 
	
}
