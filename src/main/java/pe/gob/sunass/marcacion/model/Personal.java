package pe.gob.sunass.marcacion.model;

import lombok.Data;
import pe.gob.sunass.marcacion.dto.PersonalDto;

import java.util.Date;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "\"personal\"", schema = "MARCAREMOTO")
@SqlResultSetMapping(
    name = "PersonalDtoMapping",
    classes = @ConstructorResult(
        targetClass = PersonalDto.class,
        columns = {
            @ColumnResult(name = "personalId"),
            @ColumnResult(name = "planillaId"),
            @ColumnResult(name = "nroDoc"),
            @ColumnResult(name = "trabajador"),
            @ColumnResult(name = "flag", type = String.class),
            @ColumnResult(name = "situacionEspId"),
            @ColumnResult(name = "cargoId"),
            @ColumnResult(name = "tipoPersonaId"),
            @ColumnResult(name = "itemUo"),
            @ColumnResult(name = "tipoDocId"),
            @ColumnResult(name = "condicionId"),
            @ColumnResult(name = "fechaIngreso"),
            @ColumnResult(name = "fechaCese"),
            @ColumnResult(name = "tipoDoc"),
            @ColumnResult(name = "cargo"),
            @ColumnResult(name = "tipoPersona"),
            @ColumnResult(name = "unidadOrganizativa"),
            @ColumnResult(name = "institucionSede"),
            @ColumnResult(name = "direccionSede"),
            @ColumnResult(name = "username"),
            @ColumnResult(name = "rol"),
            @ColumnResult(name = "macAddress", type = String.class)
        }
    )
)
@NamedNativeQueries({
    @NamedNativeQuery(
        name = "findPersonalAllByNroDoc",
        query = "SELECT "
                + "     prs.\"personal_id\"             as personalId, "
                + "     prs.\"planilla_id\"             as planillaId, "
                + "     prs.\"nro_doc\"                 as nroDoc, "
                + "     prs.\"trabajador\"              as trabajador, "
                + "     prs.\"flag\"                    as flag, "
                + "     prs.\"situacion_esp_id\"        as situacionEspId, "
                + "     prs.\"cargo_id\"                as cargoId, "
                + "     prs.\"tipo_persona_id\"         as tipoPersonaId, "
                + "     prs.\"item_uo\"                 as itemUo, "
                + "     prs.\"tipo_doc_id\"             as tipoDocId, "
                + "     prs.\"condicion_id\"            as condicionId, "
                + "     prs.\"fecha_ingreso\"           as fechaIngreso, "
                + "     prs.\"fecha_cese\"              as fechaCese, "
                + "     ptd.\"descripcion\"             as tipoDoc, "
                + "     prc.\"descripcion\"             as cargo, "
                + "     ptt.\"descripcion\"             as tipoPersona, "
                + "     uor.\"descripcion\"             as unidadOrganizativa, "
                + "     isd.\"nomsede\"                 as institucionSede, "
                + "     isd.\"direccion\"               as direccionSede, "
                + "     prs.USERNAME                    as username, "
                + "     prs.ROL                         as rol, "
                + "     prs.MAC_ADDR                    as macAddress, "
                + "     prs.MAC_ADDR_2                  as macAddress2, "
                + "     prs.FLAG_MAC_ADDR               as flagMacAddress "
                + " FROM \"personal\" prs "
                + " LEFT JOIN \"personal_cargo\" prc ON prc.\"cargo_id\" = prs.\"cargo_id\" "
                + " LEFT JOIN \"personal_tipo_trabajador\" ptt ON ptt.\"tipo_persona_id\" = prs.\"tipo_persona_id\" "
                + " LEFT JOIN \"personal_uo\" uor ON uor.\"item_uo\" = prs.\"item_uo\" "
                + " LEFT JOIN \"personal_tipodoc\" ptd ON ptd.\"tipo_doc_id\" = prs.\"tipo_doc_id\" "
                + " LEFT JOIN \"institucion_sede\" isd ON uor.\"sede_id\" = isd.\"sede_id\" "
                + " WHERE "
                + " (:nroDoc IS NULL OR prs.\"nro_doc\" = :nroDoc) "
                + " ORDER BY trabajador asc ",
        resultSetMapping = "PersonalDtoMapping"
    ),
    @NamedNativeQuery(
        name = "findPersonalAll",
        query = "SELECT "
                + "     prs.\"personal_id\"             as personalId, "
                + "     prs.\"planilla_id\"             as planillaId, "
                + "     prs.\"nro_doc\"                 as nroDoc, "
                + "     prs.\"trabajador\"              as trabajador, "
                + "     prs.\"flag\"                    as flag, "
                + "     prs.\"situacion_esp_id\"        as situacionEspId, "
                + "     prs.\"cargo_id\"                as cargoId, "
                + "     prs.\"tipo_persona_id\"         as tipoPersonaId, "
                + "     prs.\"item_uo\"                 as itemUo, "
                + "     prs.\"tipo_doc_id\"             as tipoDocId, "
                + "     prs.\"condicion_id\"            as condicionId, "
                + "     prs.\"fecha_ingreso\"           as fechaIngreso, "
                + "     prs.\"fecha_cese\"              as fechaCese, "
                + "     ptd.\"descripcion\"             as tipoDoc, "
                + "     prc.\"descripcion\"             as cargo, "
                + "     ptt.\"descripcion\"             as tipoPersona, "
                + "     uor.\"descripcion\"             as unidadOrganizativa, "
                + "     isd.\"nomsede\"                 as institucionSede, "
                + "     isd.\"direccion\"               as direccionSede, "
                + "     prs.USERNAME                    as username, "
                + "     prs.ROL                         as rol, "
                + "     prs.MAC_ADDR                    as macAddress, "
                + "     prs.MAC_ADDR_2                  as macAddress2, "
                + "     prs.FLAG_MAC_ADDR               as flagMacAddress "
                + " FROM \"personal\" prs "
                + " LEFT JOIN \"personal_cargo\" prc ON prc.\"cargo_id\" = prs.\"cargo_id\" "
                + " LEFT JOIN \"personal_tipo_trabajador\" ptt ON ptt.\"tipo_persona_id\" = prs.\"tipo_persona_id\" "
                + " LEFT JOIN \"personal_uo\" uor ON uor.\"item_uo\" = prs.\"item_uo\" "
                + " LEFT JOIN \"personal_tipodoc\" ptd ON ptd.\"tipo_doc_id\" = prs.\"tipo_doc_id\" "
                + " LEFT JOIN \"institucion_sede\" isd ON uor.\"sede_id\" = isd.\"sede_id\" "
                + " WHERE "
                + " (:unidadOrganizativa IS NULL OR prs.\"item_uo\" = :unidadOrganizativa) AND "
                + " ((:filter IS NULL OR prs.\"nro_doc\" like :filter) OR "
                + " (:filter IS NULL OR LOWER(prs.\"trabajador\") like :filter))"
                + " ORDER BY trabajador asc ",
        resultSetMapping = "PersonalDtoMapping"
    ),
    @NamedNativeQuery(
	    name = "updatePersonal",
	    query = "UPDATE \"personal\" SET "
	            + "\"planilla_id\" = :planillaId, "
	            + "\"nro_doc\" = :nroDoc, "
	            + "\"trabajador\" = :trabajador, "
	            + "\"flag\" = :flag, "
	            + "\"situacion_esp_id\" = :situacionEspId, "
	            + "\"cargo_id\" = :cargoId, "
	            + "\"tipo_persona_id\" = :tipoPersonaId, "
	            + "\"item_uo\" = :itemUo, "
	            + "\"tipo_doc_id\" = :tipoDocId, "
	            + "\"condicion_id\" = :condicionId, "
	            + "\"fecha_ingreso\" = :fechaIngreso, "
	            + "\"fecha_cese\" = :fechaCese, "
	            + "\"USERNAME\" = :username, "
	            + "\"ROL\" = :rol, "
	            + "\"MAC_ADDR\" = :macAddress, "
	            + "\"MAC_ADDR_2\" = :macAddress2, "
	            + "\"FLAG_MAC_ADDR\" = :flagMacAddress "
	            + "WHERE \"personal_id\" = :personalId"
	)
})

public class Personal {

    @Id
    @Column(name = "\"personal_id\"", nullable = false, length = 5)
    private String personalId;

    @Column(name = "\"planilla_id\"", length = 2)
    private String planillaId;

    @Column(name = "\"nro_doc\"", length = 11)
    private String nroDoc;

    @Column(name = "\"trabajador\"", length = 200)
    private String trabajador;

    @Column(name = "\"status\"", length = 2)
    private String status;

    @Column(name = "\"flag\"", length = 1)
    private String flag;

    @Column(name = "\"userReg\"", length = 20)
    private String userReg;

    @Column(name = "\"fecReg\"")
    private Date fecReg;

    @Column(name = "\"userMod\"", length = 20)
    private String userMod;

    @Column(name = "\"fecMod\"")
    private Date fecMod;

    @Column(name = "\"situacion_esp_id\"", length = 2)
    private String situacionEspId;

    @Column(name = "\"cargo_id\"", length = 3)
    private String cargoId;

    @Column(name = "\"tipo_persona_id\"", length = 2)
    private String tipoPersonaId;

    @Column(name = "\"item_uo\"", length = 2)
    private String itemUo;

    @Column(name = "\"tipo_doc_id\"", length = 2)
    private String tipoDocId;

    @Column(name = "\"condicion_id\"", length = 2)
    private String condicionId;

    @Column(name = "\"fecha_ingreso\"")
    private Date fechaIngreso;

    @Column(name = "\"fecha_cese\"")
    private Date fechaCese;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "ROL")
    private String rol;

    @Column(name = "MAC_ADDR")
    private String macAddress;
    
    @Column(name = "MAC_ADDR_2")
    private String macAddress2;
    
    @Column(name = "FLAG_MAC_ADDR")
    private String flagMacAddress;

}
