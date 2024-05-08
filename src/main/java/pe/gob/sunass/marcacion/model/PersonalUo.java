package pe.gob.sunass.marcacion.model;

import jakarta.persistence.*;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "\"personal_uo\"", schema = "MARCAREMOTO")
public class PersonalUo implements Serializable {

    @Id
    @Column(name = "\"item_uo\"", length = 2)
    private String itemUo;

    @Column(name = "\"descripcion\"", length = 100)
    private String descUnidadOrganizativa;

    @Column(name = "\"sede_id\"", length = 2)
    private String sedeId;

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
}
