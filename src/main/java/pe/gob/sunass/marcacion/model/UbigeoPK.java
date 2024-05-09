package pe.gob.sunass.marcacion.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import jakarta.persistence.Column;
import lombok.Data;

@Data
@Embeddable
public class UbigeoPK implements Serializable {

    @Column(name = "\"Codigo\"", length = 2)
    private String codigo;

    @Column(name = "\"Sub_Filtro\"", length = 2)
    private String subFiltro;

}
