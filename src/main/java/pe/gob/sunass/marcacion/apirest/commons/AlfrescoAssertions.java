package pe.gob.sunass.marcacion.apirest.commons;

import pe.gob.sunass.marcacion.apirest.body.response.NodeResponse;
import pe.gob.sunass.marcacion.apirest.exception.AlfrescoConnectorException;
import pe.gob.sunass.marcacion.apirest.exception.ConnectorErrorCodes;

public class AlfrescoAssertions {

	public static void assertNotEmpty( NodeResponse nr, String message ) throws AlfrescoConnectorException{
		if( (nr==null ) || (nr.getEntry()==null) ) {
			throw new AlfrescoConnectorException( StringUtil.switchValue(message, "NodeResponse vac�o"), 
					ConnectorErrorCodes.NODE_EMPTY);
		}
	}
	
	public static void assertNotEmpty( NodeResponse nr) throws AlfrescoConnectorException{
		assertNotEmpty(nr, null);
	}
}
