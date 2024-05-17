package pe.gob.sunass.marcacion.apirest.body.response;

import java.util.List;

import pe.gob.sunass.marcacion.apirest.body.response.base.Pagination;

public class ListEntries {

	private Pagination pagination;
	private List<NodeResponse> entries;
	
	public Pagination getPagination() {
		return pagination;
	}
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	public List<NodeResponse> getEntries() {
		return entries;
	}
	public void setEntries(List<NodeResponse> entries) {
		this.entries = entries;
	}
	
	public boolean isEmptyEntries() {
		return ( entries == null ) || ( entries.isEmpty() );
	}
	
	
}
