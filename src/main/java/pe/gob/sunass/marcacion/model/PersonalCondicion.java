package pe.gob.sunass.marcacion.model;

import jakarta.persistence.*;

import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "\"personal_condicion\"", schema = "MARCAREMOTO")
public class PersonalCondicion implements Serializable {

    @Id
    @Column(name = "\"condicion_id\"", length = 2, nullable = false)
    private String condicionId;

    @Column(name = "\"descripcion\"", length = 100)
    private String descripcion;

    @Column(name = "\"status\"", length = 2)
    private String status;

    @Column(name = "\"flag\"", length = 1)
    private String flag;
}