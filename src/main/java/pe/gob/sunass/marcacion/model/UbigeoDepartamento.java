package pe.gob.sunass.marcacion.model;

import jakarta.persistence.*;

import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "\"ubigeo_departamento\"", schema = "MARCAREMOTO")
public class UbigeoDepartamento implements Serializable {

    @Id
    @Column(name = "\"Codigo\"", length = 2)
    private String codigo;

    @Column(name = "\"Descripcion\"", length = 100)
    private String descripcion;
}
