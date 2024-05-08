package pe.gob.sunass.marcacion.model;

import java.util.Date;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "\"personal_remoto\"", schema = "MARCAREMOTO")
public class PersonalRemoto {

    @Id
    @Column(name = "\"item\"")
    private Long item;

    @Column(name = "\"personal_id\"", length = 5)
    private String personalId;

    @Column(name = "\"fecha_ini_remoto\"")
    private Date fechaIniRemoto;

    @Column(name = "\"fecha_fin_remoto\"")
    private Date fechaFinRemoto;

    @Lob
    @Column(name = "\"observacion\"")
    private String observacion;

    @Column(name = "\"docreferencia\"", length = 100)
    private String docreferencia;

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