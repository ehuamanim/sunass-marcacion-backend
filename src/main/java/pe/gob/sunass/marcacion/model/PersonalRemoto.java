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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PERSONAL_REMOTO")
    @SequenceGenerator(name = "SEQ_PERSONAL_REMOTO", sequenceName = "SEQ_PERSONAL_REMOTO", allocationSize = 1)
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

    @Column(name = "\"userReg\"", length = 20)
    private String userReg;

    @Column(name = "\"fecReg\"")
    private Date fecReg;

    @Column(name = "\"userMod\"", length = 20)
    private String userMod;

    @Column(name = "\"fecMod\"")
    private Date fecMod;
}