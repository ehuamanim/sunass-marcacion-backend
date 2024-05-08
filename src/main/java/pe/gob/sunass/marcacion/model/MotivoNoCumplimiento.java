package pe.gob.sunass.marcacion.model;

import jakarta.persistence.*;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "\"motivo_no_cumplimiento\"", schema = "MARCAREMOTO")
public class MotivoNoCumplimiento implements Serializable {

    @Id
    @Column(name = "\"motivo_id\"", length = 3)
    private String motivoId;

    @Column(name = "\"descripcion\"", length = 100)
    private String descripcion;

    @Column(name = "\"status\"", length = 2)
    private String status;

    @Column(name = "\"flag\"", length = 1)
    private String flag;

    @Column(name = "\"userReg\"", length = 50)
    private String userReg;

    @Column(name = "\"fecReg\"")
    private Date fecReg;

    @Column(name = "\"userMod\"", length = 50)
    private String userMod;

    @Column(name = "\"fecMod\"")
    private Date fecMod;
}