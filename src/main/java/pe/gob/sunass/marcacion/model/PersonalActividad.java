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
            @ColumnResult(name = "nroDoc"),
            @ColumnResult(name = "modalidadId"),
            @ColumnResult(name = "condicionesId"),
            @ColumnResult(name = "fecReg")
        }
    )
)
@NamedNativeQuery(
	    name = "findActividadesAsignadas",
	    query = "SELECT "
	            + " pa.\"item\"        AS item, "
	            + " pa.\"personal_id\" AS personalId, "
	            + " pa.\"actividad_id\"    AS actividadId, "
	            + " pa.\"fecha_inicio\" AS fechaInicio, "
	            + " a.\"descripcion\"   AS actividad, "
	            + " pa.\"userReg\"      AS userReg, "
	            + " p.\"nro_doc\"       AS nroDoc, "
	            + " pa.\"modalidad_id\" AS modalidadId, "
	            + " pa.\"condiciones_id\" AS condicionesId, "
	            + " pa.\"fecReg\"       AS fecReg "
	            + "FROM "
	            + " \"personal_actividad\" pa "
	            + "LEFT JOIN \"actividad\" a ON a.\"actividad_id\" = pa.\"actividad_id\"  "
	            + "LEFT JOIN \"personal\" p ON p.\"personal_id\" = pa.\"personal_id\"  "
	            + "WHERE "
	            + " pa.\"personal_id\" = :personalId "
	            + "ORDER BY actividad",
	    resultSetMapping = "PersonalActividadDtoMapping"
	)

@NamedNativeQuery(
	    name = "updateActividadAsignada",
	    query = "UPDATE \"personal_actividad\" SET " +
	            "\"actividad_id\" = COALESCE(:actividadId, \"actividad_id\"), " +
	            "\"personal_id\" = COALESCE(:personalId, \"personal_id\"), " +
	            "\"fecha_inicio\" = COALESCE(:fechaInicio, \"fecha_inicio\"), " +
	            "\"fecha_fin\" = COALESCE(:fechaFin, \"fecha_fin\"), " +
	            "\"status\" = COALESCE(:status, \"status\"), " +
	            "\"flag\" = COALESCE(:flag, \"flag\"), " +
	            "\"userReg\" = COALESCE(:userReg, \"userReg\"), " +
	            "\"fecReg\" = COALESCE(:fecReg, \"fecReg\"), " +
	            "\"modalidad_id\" = COALESCE(:modalidadId, \"modalidad_id\"), " +
	            "\"condiciones_id\" = COALESCE(:condicionesId, \"condiciones_id\"), " +
	            "\"userMod\" = COALESCE(:userMod, \"userMod\"), " +
	            "\"fecMod\" = COALESCE(:fecMod, \"fecMod\") " +
	            "WHERE \"item\" = :item",
	    resultClass = PersonalActividad.class
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "\"fecha_inicio\"")
    private Date fechaInicio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "\"fecha_fin\"")
    private Date fechaFin;

    @Column(name = "\"status\"", length = 2)
    private String status;

    @Column(name = "\"flag\"", length = 1)
    private String flag;

    @Column(name = "\"userReg\"", length = 20)
    private String userReg;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "\"fecReg\"")
    private Date fecReg;

    @Column(name = "\"userMod\"", length = 20)
    private String userMod;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "\"fecMod\"")
    private Date fecMod;
    
    @Column(name = "\"modalidad_id\"", length = 2)
    private String modalidadId;
    
    @Column(name = "\"condiciones_id\"", length = 2)
    private String condicionesId;

}
