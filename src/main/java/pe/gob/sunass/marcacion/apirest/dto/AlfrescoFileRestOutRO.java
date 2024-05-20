package pe.gob.sunass.marcacion.apirest.dto;

import lombok.Data;

@Data
public class AlfrescoFileRestOutRO {

	private String status;
	private String message;
    private String filename;
    private String nodeId;

}
