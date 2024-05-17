package pe.gob.sunass.marcacion.apirest.body.response;

import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

import pe.gob.sunass.marcacion.apirest.body.response.base.Content;
import pe.gob.sunass.marcacion.apirest.body.response.base.Path;
import pe.gob.sunass.marcacion.apirest.body.response.base.Permissions;
import pe.gob.sunass.marcacion.apirest.body.response.base.Properties;
import pe.gob.sunass.marcacion.apirest.body.response.base.User;

public class NodeEntry {

	private String id;
	private String name;
	private String nodeType;
	private Boolean isFolder;
	private Boolean isFile;
	private Boolean isLocked;
	private Date modifiedAt;
	private User modifiedByUser;
	private Date createdAt;
	private User createdByUser;
	private String parentId;
	private Boolean isLink;
	private Boolean isFavorite;
	private Content content;
	private List<String> aspectNames;
	private Properties properties;
	private List<String> allowableOperations;
	private Path path;
	private Permissions permissions;
	private String versionComment;
	
	

	public String getVersionComment() {
		return versionComment;
	}

	public void setVersionComment(String versionComment) {
		this.versionComment = versionComment;
	}

	public List<String> getAspectNames() {
		return aspectNames;
	}

	public void setAspectNames(List<String> aspectNames) {
		this.aspectNames = aspectNames;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Boolean isFolder() {
		return isFolder;
	}

	public void setFolder(Boolean isFolder) {
		this.isFolder = isFolder;
	}

	public Boolean isFile() {
		return isFile;
	}

	public void setFile(Boolean isFile) {
		this.isFile = isFile;
	}

	public User getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(User createdByUser) {
		this.createdByUser = createdByUser;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public User getModifiedByUser() {
		return modifiedByUser;
	}

	public void setModifiedByUser(User modifiedByUser) {
		this.modifiedByUser = modifiedByUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Boolean getIsFolder() {
		return isFolder;
	}

	public void setIsFolder(Boolean isFolder) {
		this.isFolder = isFolder;
	}

	public Boolean getIsFile() {
		return isFile;
	}

	public void setIsFile(Boolean isFile) {
		this.isFile = isFile;
	}

	public Boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}

	public Boolean getIsLink() {
		return isLink;
	}

	public void setIsLink(Boolean isLink) {
		this.isLink = isLink;
	}

	public Boolean getIsFavorite() {
		return isFavorite;
	}

	public void setIsFavorite(Boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public List<String> getAllowableOperations() {
		return allowableOperations;
	}

	public void setAllowableOperations(List<String> allowableOperations) {
		this.allowableOperations = allowableOperations;
	}

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	public Permissions getPermissions() {
		return permissions;
	}

	public void setPermissions(Permissions permissions) {
		this.permissions = permissions;
	}
	
	public String toJsonString() {
		return new Gson().toJson(this);
	}
}
