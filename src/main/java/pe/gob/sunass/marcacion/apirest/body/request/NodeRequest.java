package pe.gob.sunass.marcacion.apirest.body.request;

import java.util.List;

import pe.gob.sunass.marcacion.apirest.body.response.base.Properties;

public class NodeRequest extends BaseRequest {

	private String name;
	private String nodeType;
	private String relativePath;
	private String targetParentId;
	private Properties properties;
	private List<String> aspectNames;

	public NodeRequest() {
		
	}
	
	public NodeRequest(String name, String nodeType, String relativePath) {
		super();
		this.name = name;
		this.nodeType = nodeType;
		this.relativePath = relativePath;
	}

	public NodeRequest(String name, String nodeType, String relativePath, String targetParentId) {
		super();
		this.name = name;
		this.nodeType = nodeType;
		this.relativePath = relativePath;
		this.targetParentId = targetParentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	public String getTargetParentId() {
		return targetParentId;
	}

	public void setTargetParentId(String targetParentId) {
		this.targetParentId = targetParentId;
	}

	public List<String> getAspectNames() {
		return aspectNames;
	}

	public void setAspectNames(List<String> aspectNames) {
		this.aspectNames = aspectNames;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

}
