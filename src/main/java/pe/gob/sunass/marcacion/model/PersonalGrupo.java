package pe.gob.sunass.marcacion.model;

import jakarta.persistence.*;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "\"personal_grupo\"", schema = "MARCAREMOTO")
public class PersonalGrupo implements Serializable {

    @Id
    @Column(name = "\"item\"", nullable = false)
    private Long item;

    @Column(name = "\"personal_id\"", length = 5)
    private String personalId;

    @Column(name = "\"personal_id_ref\"", length = 5)
    private String personalIdRef;

    @Column(name = "\"status\"", length = 2)
    private String status;

    @Column(name = "\"flag\"", length = 1)
    private String flag;

    @Column(name = "\"user_reg\"", length = 20)
    private String userReg;

    @Column(name = "\"fec_reg\"")
    private Date fecReg;

    @Column(name = "\"user_mod\"", length = 20)
    private String userMod;

    @Column(name = "\"fec_mod\"")
    private Date fecMod;
}