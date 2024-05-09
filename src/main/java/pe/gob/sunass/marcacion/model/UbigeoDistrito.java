package pe.gob.sunass.marcacion.model;

import jakarta.persistence.*;

import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "\"ubigeo_distrito\"", schema = "MARCAREMOTO")
public class UbigeoDistrito implements Serializable {

    @EmbeddedId
    private UbigeoPK id;

    @Column(name = "\"Descripcion\"", length = 100)
    private String descripcion;

}