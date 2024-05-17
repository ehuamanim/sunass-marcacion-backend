package pe.gob.sunass.marcacion.apirest.body.response;

import com.google.gson.Gson;

public class TicketResponse extends BaseResponse{

	private TicketEntry entry;

	public TicketEntry getEntry() {
		return entry;
	}

	public void setEntry(TicketEntry entry) {
		this.entry = entry;
	}
	
	public static TicketResponse toTicketResponse(String data) {
		return new Gson().fromJson(data, TicketResponse.class);
	}
	
}
