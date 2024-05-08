package pe.gob.sunass.marcacion.model;

import lombok.Data;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = "\"ubigeo_provincia\"", schema = "MARCAREMOTO")
public class UbigeoProvincia implements Serializable {

    @EmbeddedId
    private UbigeoPK id;

    @Column(name = "\"Descripcion\"", length = 100)
    private String descripcion;

}