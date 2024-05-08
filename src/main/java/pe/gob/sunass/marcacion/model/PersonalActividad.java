package pe.gob.sunass.marcacion.model;

import jakarta.persistence.*;

import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "\"personal_actividad\"", schema = "MARCAREMOTO")
public class PersonalActividad {

    @Id
    @Column(name = "\"item\"", nullable = false)
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

    @Column(name = "\"user_reg\"", length = 20)
    private String userReg;

    @Temporal( TemporalType.TIMESTAMP )
    @Column(name = "\"fec_reg\"")
    private Date fecReg;

    @Column(name = "\"user_mod\"", length = 20)
    private String userMod;

    @Temporal( TemporalType.TIMESTAMP )
    @Column(name = "\"fec_mod\"")
    private Date fecMod;

}
