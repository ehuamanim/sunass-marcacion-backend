package pe.gob.sunass.marcacion.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "\"marcacion_general\"", schema = "MARCAREMOTO")
public class MarcacionGeneral {

    @Id
    @Column(name = "\"item\"", nullable = false, precision = 11)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MARCACION_GENERAL")
    @SequenceGenerator(name = "SEQ_MARCACION_GENERAL", sequenceName = "SEQ_MARCACION_GENERAL", allocationSize = 1)
    private Long item;

    @Column(name = "\"pers_codigo\"", length = 4)
    private String persCodigo;

    @Column(name = "\"Descripcion\"", length = 800)
    private String descripcion;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "\"fecha_log\"")
    private Date fechaLog;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "\"fecha_reg\"")
    private Date fechaReg;

    @Column(name = "\"flag_atendido\"", precision = 4)
    private Integer flagAtendido;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "\"fecha_ini\"")
    private Date fechaIni;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "\"fecha_fin\"")
    private Date fechaFin;

    @Column(name = "\"est_aten_id\"", length = 3)
    private String estAtenId;

    @Column(name = "\"usua_reg\"", length = 50)
    private String usuaReg;

    @Column(name = "\"pers_trabajador\"", length = 800)
    private String persTrabajador;

    @Column(name = "\"pers_dependencia\"", length = 800)
    private String persDependencia;

    @Column(name = "\"pers_usuario\"", length = 800)
    private String persUsuario;

    @Column(name = "\"revisado\"", precision = 4)
    private Integer revisado;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "\"fecha_revision\"")
    private Date fechaRevision;

    @Column(name = "\"usuario_rev\"", length = 50)
    private String usuarioRev;

    @Column(name = "\"tipoActividad\"", precision = 11)
    private Long tipoActividad;

    @Column(name = "\"latitud\"", length = 50)
    private String latitud;

    @Column(name = "\"longitud\"", length = 50)
    private String longitud;

    @Column(name = "\"motivo_id\"", length = 3)
    private String motivoId;

    @Column(name = "\"Tipo_Dia_Id\"", length = 2)
    private String tipoDiaId;

    @Column(name = "\"Tipo_Suspension_RL_Id\"", length = 2)
    private String tipoSuspensionRLId;

    @Column(name = "\"nro_doc\"", length = 11)
    private String nroDoc;

    @Column(name = "OBSERVACION", length = 11)
    private String observacion;

}
