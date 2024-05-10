package pe.gob.sunass.marcacion.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(name = "\"actividad\"", uniqueConstraints = {
		@UniqueConstraint(columnNames = "actividad_id") })
public class Actividad {
	@Id
	@Column(name = "\"actividad_id\"", nullable = false, length = 6)
	private String actividadId;

	@Column(name = "\"descripcion\"", length = 200)
	private String descripcion;

	@Column(name = "\"prioridad_id\"", length = 2)
	private String prioridadId;

	@Column(name = "\"status\"", length = 2)
	private String status;

	@Column(name = "\"flag\"", length = 1)
	private String flag;

	@Column(name = "\"userReg\"", length = 20)
	private String userReg;

	@Temporal(TemporalType.DATE)
	@Column(name = "\"fecReg\"")
	private Date fecReg;

	@Column(name = "\"userMod\"", length = 20)
	private String userMod;

	@Temporal(TemporalType.DATE)
	@Column(name = "\"fecMod\"")
	private Date fecMod;
}
