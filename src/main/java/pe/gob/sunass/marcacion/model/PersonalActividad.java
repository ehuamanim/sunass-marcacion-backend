package pe.gob.sunass.marcacion.model;

import jakarta.persistence.*;

import lombok.Data;
import pe.gob.sunass.marcacion.dto.PersonalActividadDto;
import pe.gob.sunass.marcacion.dto.PersonalDto;

import java.util.Date;

@Data
@Entity
@Table(name = "\"personal_actividad\"", schema = "MARCAREMOTO")
@SqlResultSetMapping(
    name = "PersonalActividadDtoMapping",
    classes = @ConstructorResult(
        targetClass = PersonalActividadDto.class,
        columns = {
			@ColumnResult(name = "item"),
			@ColumnResult(name = "personalId"),
			@ColumnResult(name = "actividadId"),
			@ColumnResult(name = "fechaInicio"),
			@ColumnResult(name = "actividad"),
			@ColumnResult(name = "userReg"),
			@ColumnResult(name = "nroDoc")
        }
    )
)
@NamedNativeQuery(
	    name = "findActividadesAsignadas",
	    query = "SELECT "
	    		+ "	pa.\"item\" 		AS item, "
	    		+ "	pa.\"personal_id\"	AS personalId, "
	    		+ "	pa.\"actividad_id\"	AS actividadId, "
	    		+ "	pa.\"fecha_inicio\"	AS fechaInicio, "
	    		+ "	a.\"descripcion\" 	AS actividad, "
	    		+ "	pa.\"userReg\" 		AS userReg, "
	    		+ "	p.\"nro_doc\" 		AS nroDoc "
	    		+ "FROM "
	    		+ "	\"personal_actividad\" pa "
	    		+ "LEFT JOIN \"actividad\" a ON a.\"actividad_id\" = pa.\"actividad_id\"  "
	    		+ "LEFT JOIN \"personal\" p ON p.\"personal_id\" = pa.\"personal_id\"  "
	    		+ "WHERE 1=1  "
	    		+ "	AND pa.\"fecha_inicio\" < SYSDATE "
	    		+ "	AND pa.\"fecha_fin\" > SYSDATE "
	    		+ " AND (:personaId IS NULL OR pa.\"personal_id\" = :personaId) "
	    		+ "ORDER BY actividad",
	    resultSetMapping = "PersonalActividadDtoMapping"
	)
public class PersonalActividad {

    @Id
    @Column(name = "\"item\"", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PERSONAL_ACTIVIDAD")
    @SequenceGenerator(name = "SEQ_PERSONAL_ACTIVIDAD", sequenceName = "SEQ_PERSONAL_ACTIVIDAD", allocationSize = 1)
    private Long item;

    @Column(name = "\"personal_id\"", length = 5)
    private String personalId;

    @Column(name = "\"actividad_id\"", length = 6)
    private String actividadId;

    @Temporal( TemporalType.TIMESTAMP )
    @Column(name = "\"fecha_inicio\"")
    private Date fechaInicio;

    @Temporal( TemporalType.TIMESTAMP )
    @Column(name = "\"fecha_fin\"")
    private Date fechaFin;

    @Column(name = "\"status\"", length = 2)
    private String status;

    @Column(name = "\"flag\"", length = 1)
    private String flag;

    @Column(name = "\"userReg\"", length = 20)
    private String userReg;

    @Temporal( TemporalType.TIMESTAMP )
    @Column(name = "\"fecReg\"")
    private Date fecReg;

    @Column(name = "\"userMod\"", length = 20)
    private String userMod;

    @Temporal( TemporalType.TIMESTAMP )
    @Column(name = "\"fecMod\"")
    private Date fecMod;

}
