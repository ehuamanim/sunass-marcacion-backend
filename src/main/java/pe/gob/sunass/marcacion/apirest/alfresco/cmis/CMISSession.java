package pe.gob.sunass.marcacion.apirest.alfresco.cmis;


import java.io.File;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Session;

import pe.gob.sunass.marcacion.apirest.exception.AlfrescoConnectorException;

public interface CMISSession {

	public static final String SECONDARY_OBJECT_TYPE_IDS_PROP_NAME = "cmis:secondaryObjectTypeIds";
	
	public Session getSession();
	
	public CmisObject getObjectByPath(String path);
	
	public Document getLatestDocumentVersionByPath( String pathObject );
	public Document modifyBinaryContent( Document doc, File file ) throws AlfrescoConnectorException;
	
	public void setSession( Session session );
	public void close();
	
}
