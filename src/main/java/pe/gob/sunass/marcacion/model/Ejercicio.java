package pe.gob.sunass.marcacion.model;

import jakarta.persistence.*;

import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "\"ejercicio\"", schema = "MARCAREMOTO")
public class Ejercicio implements Serializable {

    @Id
    @Column(name = "\"ejercicio_id\"", length = 4)
    private String ejercicioId;

    @Column(name = "\"descripcion\"", length = 50)
    private String descripcion;

    @Column(name = "\"status\"", length = 2)
    private String status;

    @Column(name = "\"flag\"", length = 1)
    private String flag;

}