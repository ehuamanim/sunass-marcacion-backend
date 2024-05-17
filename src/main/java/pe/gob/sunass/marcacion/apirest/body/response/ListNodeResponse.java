package pe.gob.sunass.marcacion.apirest.body.response;

import java.util.List;

import com.google.gson.Gson;

public class ListNodeResponse extends BaseResponse{

	private ListEntries list;

	public ListEntries getList() {
		return list;
	}

	public void setList(ListEntries list) {
		this.list = list;
	}
	
	public boolean isEntriesEmpty() {
		return ( list == null ) || ( list.isEmptyEntries() );
	}
	
	public List<NodeResponse> getNodesResponse(){
		return list.getEntries();
	}
	
	public static ListNodeResponse toListNodeResponse(String data) {
		return new Gson().fromJson(data, ListNodeResponse.class);
	}
	
	
}
