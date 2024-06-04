package pe.gob.sunass.marcacion.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PersonalActividadDto {
	private Long item;
	private String personalId;
	private String actividadId;
	private Date fechaInicio;
	private String actividad;
	private String userReg;
	private String nroDoc;
	private String modalidadId;
	private String condicionesId;
}
