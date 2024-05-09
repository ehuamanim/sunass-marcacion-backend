package pe.gob.sunass.marcacion.model;

import jakarta.persistence.*;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "\"marcacion_intentos\"", schema = "MARCAREMOTO")
public class MarcacionLog implements Serializable {

    @Id
    @Column(name = "\"item\"")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_marcacion_intento")
    @SequenceGenerator(name = "seq_marcacion_intento", sequenceName = "seq_marcacion_intento", allocationSize = 1)
    private Long item;

    @Column(name = "\"personal_id\"", length = 5)
    private String personalId;

    @Column(name = "\"tipo\"")
    private Long tipo;

    @Temporal( TemporalType.TIMESTAMP )
    @Column(name = "\"fecha_log\"")
    private Date fechaLog;

    @Column(name = "\"descripcion\"", length = 800)
    private String descripcion;

    @Column(name = "\"descripcion2\"", length = 800)
    private String descripcion2;
    
}
