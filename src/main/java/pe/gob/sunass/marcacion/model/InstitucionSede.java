package pe.gob.sunass.marcacion.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "\"institucion_sede\"", schema = "MARCAREMOTO")
public class InstitucionSede {

    @Id
    @Column(name = "\"sede_id\"", nullable = false, length = 2)
    private String sedeId;

    @Column(name = "\"compania_id\"", length = 2)
    private String companiaId;

    @Column(name = "\"nomsede\"", length = 100)
    private String nomsede;

    @Column(name = "\"direccion\"", length = 100)
    private String direccion;

    @Column(name = "\"departamento_id\"", length = 2)
    private String departamentoId;

    @Column(name = "\"provincia_id\"", length = 4)
    private String provinciaId;

    @Column(name = "\"distrito_id\"", length = 6)
    private String distritoId;

    @Column(name = "\"status\"", length = 2)
    private String status;

    @Column(name = "\"flag\"", length = 1)
    private String flag;

    @Column(name = "\"userReg\"", length = 20)
    private String userreg;

    @Column(name = "\"fecReg\"")
    private Date fecreg;

    @Column(name = "\"userMod\"", length = 20)
    private String usermod;

    @Column(name = "\"fecMod\"")
    private Date fecmod;

    @Column(name = "\"item_horario\"", length = 2)
    private String itemHorario;
}
