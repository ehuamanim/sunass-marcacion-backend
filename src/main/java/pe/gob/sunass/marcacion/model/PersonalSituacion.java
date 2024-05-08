package pe.gob.sunass.marcacion.model;

import jakarta.persistence.*;

import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "\"personal_situacion\"", schema = "MARCAREMOTO")
public class PersonalSituacion implements Serializable {

    @Id
    @Column(name = "\"situacion_esp_id\"", length = 2)
    private String situacionEspId;

    @Column(name = "\"descripcion\"", length = 100)
    private String descripcion;

    @Column(name = "\"status\"", length = 2)
    private String status;

    @Column(name = "\"flag\"", length = 1)
    private String flag;

}