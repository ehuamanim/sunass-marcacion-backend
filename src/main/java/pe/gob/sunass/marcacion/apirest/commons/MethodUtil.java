package pe.gob.sunass.marcacion.apirest.commons;

import java.lang.reflect.Field;

import pe.gob.sunass.marcacion.apirest.exception.Assertions;

public class MethodUtil {
	
	public static void setStringField( Object obj, String xmlFieldName, String value ){
		
		try {

			int fieldindex = xmlFieldName.lastIndexOf("}") ;
			Assertions.assertTrue( (-1 != fieldindex), "QNAME no valido: " + xmlFieldName );
			
			String fieldname = xmlFieldName.substring(fieldindex + 1); 
			Field field = obj.getClass().getDeclaredField(fieldname);
			Boolean isAccesible = field.isAccessible();
			
			field.setAccessible(true);
			field.set(obj, value);
			field.setAccessible(isAccesible);
			
		} catch (NoSuchFieldException e) {
			System.out.println("No se encuentra el campo definido");
		} catch (SecurityException e) {
			System.out.println("No se puede acceder al campo");
		} catch (IllegalArgumentException e) {
			System.out.println("Error al acceder al campo");
		} catch (IllegalAccessException e) {
			System.out.println("Error al acceder al campo");
		} catch( Exception e ) {
			System.out.println("Error " + e.getMessage());
		}
		
	}
	
}
