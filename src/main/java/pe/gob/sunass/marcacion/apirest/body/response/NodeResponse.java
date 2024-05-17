package pe.gob.sunass.marcacion.apirest.body.response;

import com.google.gson.Gson;

public class NodeResponse extends BaseResponse{

	private NodeEntry entry;

	public NodeEntry getEntry() {
		return entry;
	}

	public void setEntry(NodeEntry entry) {
		this.entry = entry;
	}
	
	public static NodeResponse toNodeResponse(String data) {
		return new Gson().fromJson(data, NodeResponse.class);
	}
	
	public String toJsonStringEntry() {
		return entry.toJsonString();
	}
	
	public String toJsonString() {
		return new Gson().toJson(this);
	}
	
}
