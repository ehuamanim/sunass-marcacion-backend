package pe.gob.sunass.marcacion.apirest.alfresco;

import static pe.gob.sunass.marcacion.apirest.alfresco.constant.URIAlfresco.COPY_NODE;
import static pe.gob.sunass.marcacion.apirest.alfresco.constant.URIAlfresco.CREATE_NODE;
import static pe.gob.sunass.marcacion.apirest.alfresco.constant.URIAlfresco.DELETE_NODE;
import static pe.gob.sunass.marcacion.apirest.alfresco.constant.URIAlfresco.DELETE_NODE_VERSION;
import static pe.gob.sunass.marcacion.apirest.alfresco.constant.URIAlfresco.DEL_TICKET;
import static pe.gob.sunass.marcacion.apirest.alfresco.constant.URIAlfresco.GET_FILE_NODE;
import static pe.gob.sunass.marcacion.apirest.alfresco.constant.URIAlfresco.GET_FILE_NODE_VERSION;
import static pe.gob.sunass.marcacion.apirest.alfresco.constant.URIAlfresco.GET_NODE;
import static pe.gob.sunass.marcacion.apirest.alfresco.constant.URIAlfresco.GET_NODE_VERSION;
import static pe.gob.sunass.marcacion.apirest.alfresco.constant.URIAlfresco.GET_TICKET;
import static pe.gob.sunass.marcacion.apirest.alfresco.constant.URIAlfresco.LIST_CHILDREN;
import static pe.gob.sunass.marcacion.apirest.alfresco.constant.URIAlfresco.LIST_NODE_VERSIONS;
import static pe.gob.sunass.marcacion.apirest.alfresco.constant.URIAlfresco.MOVE_NODE;
import static pe.gob.sunass.marcacion.apirest.alfresco.constant.URIAlfresco.PARAM_NODE_ID;
import static pe.gob.sunass.marcacion.apirest.alfresco.constant.URIAlfresco.PARAM_NODE_VERSION_ID;
import static pe.gob.sunass.marcacion.apirest.alfresco.constant.URIAlfresco.PARAM_VALUE_ROOT;
import static pe.gob.sunass.marcacion.apirest.alfresco.constant.URIAlfresco.UPDATE_NODE;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.chemistry.opencmis.client.api.Document;

import pe.gob.sunass.marcacion.apirest.alfresco.cmis.CMISImpl;
import pe.gob.sunass.marcacion.apirest.alfresco.cmis.CMISSession;
import pe.gob.sunass.marcacion.apirest.alfresco.constant.Constants;
import pe.gob.sunass.marcacion.apirest.alfresco.constant.HttpParameter;
import pe.gob.sunass.marcacion.apirest.alfresco.constant.NodeType;
import pe.gob.sunass.marcacion.apirest.exception.AlfrescoConnectorException;
import pe.gob.sunass.marcacion.apirest.exception.ConnectorErrorCodes;
import pe.gob.sunass.marcacion.apirest.body.request.NodeRequest;
import pe.gob.sunass.marcacion.apirest.body.request.UserRequest;
import pe.gob.sunass.marcacion.apirest.body.response.ListNodeResponse;
import pe.gob.sunass.marcacion.apirest.body.response.NodeResponse;
import pe.gob.sunass.marcacion.apirest.body.response.TicketResponse;
import pe.gob.sunass.marcacion.apirest.body.response.base.Properties;
import pe.gob.sunass.marcacion.apirest.commons.APIConstant;
import pe.gob.sunass.marcacion.apirest.commons.AlfrescoAssertions;
import pe.gob.sunass.marcacion.apirest.commons.HttpUtil;
import pe.gob.sunass.marcacion.apirest.commons.MethodUtil;
import pe.gob.sunass.marcacion.apirest.commons.StringUtil;

public class AlfrescoConnector {
	
	private static AlfrescoConnector instance;

	private String externalServerURL;
	private String serverURL;
	
	private String atomUrl;

	public AlfrescoConnector() {}
	
	public AlfrescoConnector(String server) {
		this.serverURL = server.concat( Constants.REST_RESOURCE );
		this.atomUrl = server.concat( Constants.ATOMB_RESOURCE );
	}
	
	
	public static AlfrescoConnector getInstance() {
		if (instance == null) {
			instance = new AlfrescoConnector();
		}

		return instance;
	}

	public String getExternalServerURL() {
		return this.externalServerURL;
	}

	public void setExternalServerURL(String externalServerURL) {
		this.externalServerURL = externalServerURL;
	}

	public String getServerURL() {
		return this.serverURL;
	}

	public void setServerURL(String serverURL) {
		this.serverURL = serverURL;
	}

	public enum RETURN_CODE {
		SUCCESS(0), ERROR(1), PERMISSION_DENIED(2), DUPLICATED(3), NOT_EXISTS(4), OT_ACTIVE_WORKFLOW(5), LOCKED(7),
		MISSING_PARAMETER(8);

		private int id;

		RETURN_CODE(int id) {
			this.id = id;
		}

		public int getId() {
			return this.id;
		}

		public static RETURN_CODE valueById(int id) {
			for (RETURN_CODE return_code : values()) {
				if (return_code.getId() == id)
					return return_code;
			}
			return null;
		}
	}

	/**
	 * Obtiene un ticket desde las credenciales ingresadas
	 * 
	 * @param usuario
	 * @param password
	 * @return
	 * @throws IOException
	 */
	public String getTicket(String usuario, String password) throws AlfrescoConnectorException {
		
		HttpUtil http = new HttpUtil();
		String response;
		TicketResponse tr;
		String ticket = null;
		
		try {
			response = http.reqPost(getServerURL().concat(GET_TICKET),
					new UserRequest(HttpUtil.getUserDebug(usuario), HttpUtil.getPassDebug(password)).toJsonString(), null);
			tr = TicketResponse.toTicketResponse(response);
			ticket = tr.getEntry().getId();
		} catch (IOException e) {
			throw new AlfrescoConnectorException("No se puede generar el ticket", ConnectorErrorCodes.TICKET_NOT_GENERATED, e.getCause() );
		}
		
		return ticket;
	}

	public String getRepoInfo(String usuario, String password) throws AlfrescoConnectorException {
		
		HttpUtil http = new HttpUtil();
		String response;
		TicketResponse tr;
		
		try {
			response = http.reqPost(getServerURL().concat(GET_TICKET),
					new UserRequest(usuario, password).toJsonString(), null);
			tr = TicketResponse.toTicketResponse(response);
		} catch (IOException e) {
			throw new AlfrescoConnectorException("No se puede obtener la informacion del repositorio", ConnectorErrorCodes.TICKET_NOT_GENERATED, e.getCause() );
		}
		
		return tr.getEntry().getId();
	}

	public NodeResponse createDirectory(String usuario, String password, String parent, String name)
			throws AlfrescoConnectorException {
		
		HttpUtil http = new HttpUtil();
		String URI = StringUtil.replace(CREATE_NODE, PARAM_NODE_ID, PARAM_VALUE_ROOT);
		
		String ticket = getTicket(usuario, password);
		String response;
		
		try {
			response = http.reqPost(StringUtil.join(getServerURL(), URI),
					new NodeRequest(name, NodeType.FOLDER, parent).toJsonString(), ticket);
		} catch (IOException e) {
			throw new AlfrescoConnectorException("No se puede crear el directorio", ConnectorErrorCodes.NODE_FOLDER_NOT_CREATED, e.getCause() );
		}
		
		return NodeResponse.toNodeResponse(response);
	}

	public NodeResponse copyNode(String usuario, String password, String srcId, String dstId, String newName)
			throws AlfrescoConnectorException {
		
		HttpUtil http = new HttpUtil();
		String URI = StringUtil.replace(COPY_NODE, PARAM_NODE_ID, srcId);
		String ticket = getTicket(usuario, password);
		String response;
		
		try {
			response = http.reqPost(StringUtil.join(getServerURL(), URI),
					new NodeRequest(newName, null, null, dstId).toJsonString(), ticket);
		} catch (IOException e) {
			throw new AlfrescoConnectorException("No se puede copiar el nodo", ConnectorErrorCodes.NODE_NOT_COPIED  );
		}
		
		return NodeResponse.toNodeResponse(response);
	}
	
	public NodeResponse moveNode(String usuario, String password, String srcId, String dstId)
			throws AlfrescoConnectorException {
		
		NodeResponse nrSrc = getNode(usuario, password, srcId);
		NodeResponse nrDst = getNode(usuario, password, dstId);
		
		HttpUtil http = new HttpUtil();
		String URI = StringUtil.replace(MOVE_NODE, PARAM_NODE_ID, nrSrc.getEntry().getId() );
		String ticket = getTicket(usuario, password);
		String response;
		
		try {
			response = http.reqPost(StringUtil.join(getServerURL(), URI),
					new NodeRequest(null, null, null, nrDst.getEntry().getId() ).toJsonString(), ticket);
		} catch (IOException e) {
			throw new AlfrescoConnectorException("No se puede copiar el nodo", ConnectorErrorCodes.NODE_NOT_COPIED  );
		}
		
		return NodeResponse.toNodeResponse(response);
	}
	
	public NodeResponse updateNode(String usuario, String password, NodeResponse nr)
			throws AlfrescoConnectorException {
		
		HttpUtil http = new HttpUtil();
		String URI = StringUtil.replace(UPDATE_NODE, PARAM_NODE_ID, nr.getEntry().getId() );
		String ticket = getTicket(usuario, password);
		String response;
		
		NodeRequest nreq = new NodeRequest();
		nreq.setAspectNames(nr.getEntry().getAspectNames());
		
		try {
			response = http.reqPut(StringUtil.join(getServerURL(), URI),
					nreq.toJsonString(), ticket);
		} catch (IOException e) {
			throw new AlfrescoConnectorException("No se puede copiar el nodo", ConnectorErrorCodes.NODE_NOT_COPIED  );
		}
		
		return NodeResponse.toNodeResponse(response);
	}
	
	public NodeResponse deleteNode(String usuario, String password, String path )
			throws AlfrescoConnectorException {
		
		HttpUtil http = new HttpUtil();
		NodeResponse nr = getNode(usuario, password, path);
		String URI = StringUtil.replace( DELETE_NODE, PARAM_NODE_ID, nr.getEntry().getId() );
		String ticket = getTicket(usuario, password);
		String response;
		
		try {
			response = http.reqDel( StringUtil.join(getServerURL(), URI), ticket );
		} catch (IOException e) {
			throw new AlfrescoConnectorException("No se puede copiar el nodo", ConnectorErrorCodes.NODE_NOT_COPIED  );
		}
		
		return NodeResponse.toNodeResponse(response);
	}
	
	public NodeResponse deleteNode(String usuario, String password, String nodeId, String versionId )
			throws AlfrescoConnectorException {
		
		HttpUtil http = new HttpUtil();
		String ticket = getTicket(usuario, password);
		NodeResponse nr = getNode(nodeId, ticket);
		String URI = StringUtil.replace( DELETE_NODE_VERSION, PARAM_NODE_ID, nr.getEntry().getId() );
		URI = StringUtil.replace(URI, PARAM_NODE_VERSION_ID, versionId);
		
		String response;
		try {
			response = http.reqDel( StringUtil.join(getServerURL(), URI), ticket );
		} catch (IOException e) {
			throw new AlfrescoConnectorException("No se puede eliminar la version", ConnectorErrorCodes.NODE_NOT_COPIED  );
		}
		
		return NodeResponse.toNodeResponse(response);
	}

	public NodeResponse createFile(String usuario, String password, String parent, String name) throws AlfrescoConnectorException {
		
		HttpUtil http = new HttpUtil();
		String URI = StringUtil.replace(CREATE_NODE, PARAM_NODE_ID, PARAM_VALUE_ROOT);
		String ticket = getTicket(usuario, password);
		String response;
		
		try {
			response = http.reqPost(StringUtil.join(getServerURL(), URI),
					new NodeRequest(name, NodeType.FILE, parent).toJsonString(), ticket);
		} catch (IOException e) {
			throw new AlfrescoConnectorException("No se puede crear el archivo", ConnectorErrorCodes.FILE_NOT_COPIED  );
		}
		
		return NodeResponse.toNodeResponse(response);
	}
	
	public NodeResponse createCustomNode(String usuario, String password, String parent, String name, String nodeType, Map<String, String> propiedades) throws AlfrescoConnectorException {
		
		HttpUtil http = new HttpUtil();
		String URI = StringUtil.replace(CREATE_NODE, PARAM_NODE_ID, PARAM_VALUE_ROOT);
		String ticket = getTicket(usuario, password);
		String response;
		
		NodeRequest nr = new NodeRequest(name, nodeType, parent);
		nr.setProperties( parseToProperties(propiedades) );
		nr.setNodeType( APIConstant.NODE_TYPE.get(nodeType) );
		
		try {
			response = http.reqPost(StringUtil.join(getServerURL(), URI), nr.toJsonString(), ticket);
		} catch (IOException e) {

			try {
				nr.setNodeType( NodeType.FILE );
				if( nodeType.indexOf("expediente") > -1 ) {
					nr.setNodeType( NodeType.FOLDER );
				}
				
				response = http.reqPost(StringUtil.join(getServerURL(), URI),
						nr.toJsonString(), ticket);
			}catch(IOException ex) {
				System.out.println( this.getClass().getName() + ": " + ex.getMessage() );
				throw new AlfrescoConnectorException("No se puede crear el archivo", ConnectorErrorCodes.FILE_NOT_COPIED  );
			}
			
			
		}
		
		NodeResponse nrsp = NodeResponse.toNodeResponse(response);
		
		if( nrsp.hasError() ) {
			System.out.println( this.getClass().getName() + " error: " + nrsp.toJsonString() );
		}
		
		return NodeResponse.toNodeResponse(response);
	}
	
	private Properties parseToProperties( Map<String, String> prop ) {
		
		Properties p = new Properties();
		if( prop == null || prop.isEmpty()) {
			p.setIdDocumento( StringUtil.ID_EMPTY );
		}
		
		for( Entry<String, String> field: prop.entrySet() ) {
			try {
				MethodUtil.setStringField(p, field.getKey(), new String( field.getValue().getBytes(), "UTF-8" ));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		return p;
		
	}
	
	
	public ListNodeResponse listNodosHijos( String usuario, String clave, String path ) {
		
		String pathFinal = HttpUtil.removeIfStartSlash( path );
		HttpUtil http = new HttpUtil();
		String URI = StringUtil.replace( LIST_CHILDREN, PARAM_NODE_ID, PARAM_VALUE_ROOT );
		String httpParams = HttpUtil.generateHttpParameters(
				HttpParameter.MAX_ITEMS.parameWithValue("1000"),
				HttpParameter.RELATIVE_PATH.parameWithValue(pathFinal),
				HttpParameter.INCLUDE.parameWithValue("permissions"));
		String ticket = getTicket(usuario, clave);
		String response;
		
		try {
			response = http.reqGet(StringUtil.join(getServerURL(), URI, httpParams), ticket);
		} catch (IOException e) {
			throw new AlfrescoConnectorException("No se puede obtener el nodo", ConnectorErrorCodes.NODE_NOT_OBTAINED  );
		}
		
		return ListNodeResponse.toListNodeResponse(response);
		
	}

	public Boolean isNodeAdmin(String usuario, String password, String path) throws AlfrescoConnectorException {
		NodeResponse nr = getNode(usuario, password, path);
		return nr.getEntry().getCreatedByUser().getId().equals(usuario);
	}

	public Boolean isNodeExists(String usuario, String password, String path) throws AlfrescoConnectorException {
		NodeResponse nr = getNode(usuario, password, path);
		return nr != null && nr.getEntry() != null;
	}

	/**
	 * Obtiene el nodo mediante id, version y ticket
	 * @param usuario
	 * @param password
	 * @param path
	 * @return
	 * @throws AlfrescoConnectorException
	 */
	public NodeResponse getNodeVersion( String nodeId, String versionID, String ticket ) throws AlfrescoConnectorException {

		
		HttpUtil http = new HttpUtil();
		String URI = StringUtil.replace(GET_NODE_VERSION, PARAM_NODE_ID, nodeId);
		URI = StringUtil.replace(URI, PARAM_NODE_VERSION_ID, versionID);
		
		String response;
		
		try {
			response = http.reqGet(StringUtil.join(getServerURL(), URI), ticket);
		} catch (IOException e) {
			throw new AlfrescoConnectorException("No se puede obtener el nodo", ConnectorErrorCodes.NODE_NOT_OBTAINED  );
		}
		
		return NodeResponse.toNodeResponse(response);

	}
	
	/**
	 * Obtiene informaci�n del nodo por su Id
	 * @param nodeId
	 * @param ticket
	 * @return
	 * @throws AlfrescoConnectorException
	 */
	public NodeResponse getNode(String nodeId, String ticket) throws AlfrescoConnectorException {

		HttpUtil http = new HttpUtil();
		String URI = StringUtil.replace(GET_NODE, PARAM_NODE_ID, nodeId);
		String response;
		
		try {
			response = http.reqGet(StringUtil.join(getServerURL(), URI), ticket);
		} catch (IOException e) {
			throw new AlfrescoConnectorException("No se puede obtener el nodo", ConnectorErrorCodes.NODE_NOT_OBTAINED  );
		}
		
		return NodeResponse.toNodeResponse(response);

	}
	
	/**
	 * Obtiene informaci�n del nodo por su Id
	 * @param nodeId
	 * @param ticket
	 * @return
	 * @throws AlfrescoConnectorException
	 */
	public NodeResponse getNode(String usuario, String password, String path) throws AlfrescoConnectorException {

		String pathFinal = HttpUtil.removeIfStartSlash( path );
		HttpUtil http = new HttpUtil();
		String URI = StringUtil.replace(GET_NODE, PARAM_NODE_ID, PARAM_VALUE_ROOT);
		String httpParams = HttpUtil.generateHttpParameters(HttpParameter.RELATIVE_PATH.parameWithValue(pathFinal),
				HttpParameter.INCLUDE.parameWithValue("permissions"));
		String ticket = getTicket(usuario, password);
		String response;
		
		try {
			response = http.reqGet(StringUtil.join(getServerURL(), URI, httpParams), ticket);
		} catch (IOException e) {
			throw new AlfrescoConnectorException("No se puede obtener el nodo", ConnectorErrorCodes.NODE_NOT_OBTAINED  );
		}
		
		NodeResponse nr = NodeResponse.toNodeResponse(response);
		
		if( nr.hasError() && !path.startsWith( Constants.DEBUG_REPOSITORY )) {
			nr = getNode( usuario, password, Constants.DEBUG_REPOSITORY + path);
		}
		
		return nr;

	}
	
	public NodeResponse getNodeByPath(String path, String ticket) throws AlfrescoConnectorException {
		
		String pathFinal = HttpUtil.removeIfStartSlash( path );
		HttpUtil http = new HttpUtil();
		String URI = StringUtil.replace(GET_NODE, PARAM_NODE_ID, PARAM_VALUE_ROOT);
		String httpParams = HttpUtil.generateHttpParameters(HttpParameter.RELATIVE_PATH.parameWithValue(pathFinal),
				HttpParameter.INCLUDE.parameWithValue("permissions"));
		String response;
		
		try {
			response = http.reqGet(StringUtil.join(getServerURL(), URI, httpParams), ticket);
		} catch (IOException e) {
			throw new AlfrescoConnectorException("No se puede obtener el nodo", ConnectorErrorCodes.NODE_NOT_OBTAINED  );
		}
		
		return NodeResponse.toNodeResponse(response);

	}
	
	/**
	 * Obtiene los bytes de un nodo
	 * @param nodeId
	 * @param ticket
	 * @return
	 * @throws AlfrescoConnectorException
	 */
	public byte[] getBytesNode(String nodeId, String ticket) throws AlfrescoConnectorException {
		
		byte[] dataResponse = null;
		String URI = "";
		HttpUtil http = new HttpUtil();
		
		try {
			URI = StringUtil.replace(GET_FILE_NODE, PARAM_NODE_ID, nodeId );
			dataResponse = http.reqGetBytes( StringUtil.join(getServerURL(), URI ), ticket );
			
		} catch (IOException e) {
			throw new AlfrescoConnectorException("No se puede descargar el archivo", ConnectorErrorCodes.FILE_NOT_DOWNLOADED  );
		}
		
		return dataResponse;

	}
	
	/**
	 * Obtiene los bytes de un nodo
	 * @param nodeId
	 * @param ticket
	 * @return
	 * @throws AlfrescoConnectorException
	 */
	public byte[] getBytesNode(String nodeId, String nodeVersionId, String ticket) throws AlfrescoConnectorException {
		
		byte[] dataResponse = null;
		String URI = "";
		HttpUtil http = new HttpUtil();
		
		try {
			URI = StringUtil.replace(GET_FILE_NODE_VERSION, PARAM_NODE_ID, nodeId );
			URI = StringUtil.replace(URI, PARAM_NODE_VERSION_ID, nodeVersionId );
			dataResponse = http.reqGetBytes( StringUtil.join(getServerURL(), URI ), ticket );
			
		} catch (IOException e) {
			throw new AlfrescoConnectorException("No se puede descargar el archivo", ConnectorErrorCodes.FILE_NOT_DOWNLOADED  );
		}
		
		return dataResponse;

	}
	
	/**
	 * Agrega un aspecto al nodo
	 * @param usuario
	 * @param password
	 * @param path
	 * @param aspect
	 * @return
	 * @throws AlfrescoConnectorException
	 */
	public NodeResponse addAspect(String usuario, String password, String path, String aspect) throws AlfrescoConnectorException {
		NodeResponse nr = getNode(usuario, password, path);
		
		if(  !nr.getEntry().getAspectNames().contains(aspect) ) {			
			nr.getEntry().getAspectNames().add(aspect);
		}
		
		updateNode(usuario, password, nr);
		return nr;
	}
	
	
	
	public void deleteTicket( String ticket ) throws IOException {
		HttpUtil http = new HttpUtil();
		String URI = StringUtil.replace(DEL_TICKET, PARAM_NODE_ID, PARAM_VALUE_ROOT);
		http.reqDel(StringUtil.join(getServerURL(), URI ), ticket);
	}
	
	public byte[] downloadFile(String user, String password, String path) throws AlfrescoConnectorException {
		
		byte[] dataResponse = null;
		NodeResponse nr = getNode(user, password, path);
		String ticket = getTicket(user, password);
		String URI = "";
		HttpUtil http = new HttpUtil();
		
		try {
		
			AlfrescoAssertions.assertNotEmpty(nr, "Archivo no encontrado");
			URI = StringUtil.replace(GET_FILE_NODE, PARAM_NODE_ID, nr.getEntry().getId() );
			dataResponse = http.reqGetBytes( StringUtil.join(getServerURL(), URI ), ticket );
			
		} catch (IOException e) {
			throw new AlfrescoConnectorException("No se puede descargar el archivo", ConnectorErrorCodes.FILE_NOT_DOWNLOADED  );
		}
		
		return dataResponse;
		
	}
	
	public NodeResponse modifyBinaryContent( String user, String password, String path, String filename, File file ) {
		
		System.out.println("Suebiendo CMIS en: " + path);
		System.out.println("Filename: " + filename);
		System.out.println("File: " + file.getAbsolutePath());
		
		CMISSession session = CMISImpl.createSession(user, password, this.atomUrl );
		Document doc = session.getLatestDocumentVersionByPath( path );
		session.modifyBinaryContent(doc, file);
		NodeResponse nr = getNode(user, password, path);
		
		session.close();
		
		return nr;
		
	}
	
	public ListNodeResponse getVersions(String usuario, String clave, String path) throws AlfrescoConnectorException {

		String pathFinal = HttpUtil.removeIfStartSlash( path );
		NodeResponse nr = getNode(usuario, clave, pathFinal);
		HttpUtil http = new HttpUtil();
		String URI = StringUtil.replace(LIST_NODE_VERSIONS, PARAM_NODE_ID, nr.getEntry().getId() );
		String ticket = getTicket(usuario, clave);
		String response;
		
		try {
			response = http.reqGet(StringUtil.join(getServerURL(), URI), ticket);
		} catch (IOException e) {
			throw new AlfrescoConnectorException("No se puede obtener el nodo", ConnectorErrorCodes.NODE_NOT_OBTAINED  );
		}
		
		return ListNodeResponse.toListNodeResponse(response);

	}
	
	

}





























