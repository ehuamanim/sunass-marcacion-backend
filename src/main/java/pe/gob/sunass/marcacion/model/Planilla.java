package pe.gob.sunass.marcacion.model;

import jakarta.persistence.*;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "\"planilla\"", schema = "MARCAREMOTO")
public class Planilla implements Serializable {

    @Id
    @Column(name = "\"planilla_id\"", length = 2)
    private String planillaId;

    @Column(name = "\"ejercicio_id\"", length = 4)
    private String ejercicioId;

    @Column(name = "\"descripcion\"", length = 50)
    private String descripcion;

    @Column(name = "\"status\"", length = 2)
    private String status;

    @Column(name = "\"flag\"", length = 1)
    private String flag;

}