package pe.gob.sunass.marcacion.apirest.alfresco.cmis;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.ContentStreamImpl;
import org.apache.commons.io.FileUtils;

import pe.gob.sunass.marcacion.apirest.exception.AlfrescoConnectorException;
import pe.gob.sunass.marcacion.apirest.exception.ConnectorErrorCodes;
import pe.gob.sunass.marcacion.apirest.commons.HttpUtil;

public class CMISImpl implements CMISSession{

	private Session session;
	private static String usuario;
	private static String clave;
	private static String url;
	
	public static CMISSession createSession(String usuario, String clave, String url) {
		SessionFactory factory = SessionFactoryImpl.newInstance();
		Map<String, String> parameter = new HashMap<String, String>();

		// user credentials
		parameter.put(SessionParameter.USER, HttpUtil.getUserDebug(usuario));
		parameter.put(SessionParameter.PASSWORD, HttpUtil.getPassDebug(clave));

		// connection settings
		parameter.put(SessionParameter.ATOMPUB_URL, url);
		parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());

		// create session
		List<Repository> repositories = factory.getRepositories(parameter);
		parameter.put( SessionParameter.REPOSITORY_ID , repositories.get(0).getId() );
		
		Session session = factory.createSession(parameter);
		CMISSession cmisSession = new CMISImpl();
		cmisSession.setSession(session);
		
		return cmisSession;
	}
	
	public CmisObject getObjectByPath(String path) {
		String pathResolver = path.replaceAll("//", "/");
		CmisObject obj = session.getObjectByPath( HttpUtil.addIfNotStartSlash( pathResolver ) );
		return obj;
	}
	
	public Document getLatestDocumentVersionByPath( String pathObject ) {
		
		CmisObject obj = getObjectByPath(pathObject);
		Document doc = session.getLatestDocumentVersion(obj.getId());
		
		return doc;
		
	}
	
	public Document modifyBinaryContent( Document doc, File file ) throws AlfrescoConnectorException{
		
		byte[] data;
		try {
			data = FileUtils.readFileToByteArray(file);
			InputStream stream = new ByteArrayInputStream(data);
			ContentStream cs = new ContentStreamImpl(file.getName(), BigInteger.valueOf(data.length), "application/octet-stream", stream);
			
			ContentStream streamDoc = doc.getContentStream();
			boolean ovewrite = false;
			
			if( streamDoc != null ) {
				ovewrite = true;
			}
			
			doc.setContentStream(cs, ovewrite);
		} catch (IOException e1) {
			System.out.println( this.getClass().getName() + ": " + e1.getMessage() );
			throw new AlfrescoConnectorException("No se puede descargar el archivo", ConnectorErrorCodes.FILE_NOT_DOWNLOADED, e1.getCause()  );
		} 
		
		return doc;
		
	}
	
	
	public static CMISSession createSession() {
		return createSession(usuario, clave, url);
	} 
	
	public void close() {
		session.clear();
	}

	public Session getSession() {
		return this.session;
	}
	
	public void setSession(Session session) {
		this.session = session;
	}
	
	
	
	
}
