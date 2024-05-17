package pe.gob.sunass.marcacion.apirest.alfresco.constant;

public interface URIAlfresco {

	public static final String PARAM_NODE_ID = "nodeId";
	public static final String PARAM_NODE_VERSION_ID = "nodeVersionId";
	
	public static final String PARAM_VALUE_MY= "-my-";
	public static final String PARAM_VALUE_ROOT= "-root-";
	
	public static final String GET_TICKET = "/authentication/versions/1/tickets";
	public static final String DEL_TICKET = "/authentication/versions/1/tickets/-me-";
	public static final String LIST_CHILDREN = "/alfresco/versions/1/nodes/{" + PARAM_NODE_ID + "}/children";
	public static final String LIST_NODE_VERSIONS = "/alfresco/versions/1/nodes/{" + PARAM_NODE_ID + "}/versions";
	public static final String CREATE_NODE = "/alfresco/versions/1/nodes/{" + PARAM_NODE_ID + "}/children";
	public static final String DELETE_NODE = "/alfresco/versions/1/nodes/{" + PARAM_NODE_ID + "}";
	public static final String DELETE_NODE_VERSION = "/alfresco/versions/1/nodes/{" + PARAM_NODE_ID + "}/versions/{" + PARAM_NODE_VERSION_ID + "}";
	public static final String COPY_NODE = "/alfresco/versions/1/nodes/{" + PARAM_NODE_ID + "}/copy";
	public static final String MOVE_NODE = "/alfresco/versions/1/nodes/{" + PARAM_NODE_ID + "}/move";
	public static final String UPDATE_NODE = "/alfresco/versions/1/nodes/{" + PARAM_NODE_ID + "}";
	public static final String GET_FILE_NODE = "/alfresco/versions/1/nodes/{" + PARAM_NODE_ID + "}/content";
	public static final String GET_FILE_NODE_VERSION = "/alfresco/versions/1/nodes/{" + PARAM_NODE_ID + "}/versions/{" + PARAM_NODE_VERSION_ID + "}/content?attachment=true";
	public static final String GET_NODE_VERSION = "/alfresco/versions/1/nodes/{" + PARAM_NODE_ID + "}/versions/{" + PARAM_NODE_VERSION_ID + "}";
	public static final String GET_NODE = "/alfresco/versions/1/nodes/{" + PARAM_NODE_ID + "}";
	public static final String UPDATE_NODE_CONTENT = "/alfresco/versions/1/nodes/{" + PARAM_NODE_ID + "}/content";

}
