package pe.gob.sunass.marcacion.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "\"marcacion_horario\"")
public class MarcacionHorario {

    @Id
    @Column(name = "\"item_horario\"")
    private String itemHorario;

    @Column(name = "\"hor_ini\"")
    private Date horIni;

    @Column(name = "\"hor_fin\"")
    private Date horFin;

    @Column(name = "\"hor_ini_ref\"")
    private Date horIniRef;

    @Column(name = "\"hor_fin_ref\"")
    private Date horFinRef;

    @Column(name = "\"descripcion\"")
    private String descripcion;

    @Column(name = "\"tolerancia\"")
    private Integer tolerancia;

    @Column(name = "\"status\"")
    private String status;

    @Column(name = "\"flag\"")
    private String flag;

    @Column(name = "\"userReg\"")
    private String userReg;

    @Column(name = "\"fecReg\"")
    private Date fecReg;

    @Column(name = "\"userMod\"")
    private String userMod;

    @Column(name = "\"fecMod\"")
    private Date fecMod;
}
