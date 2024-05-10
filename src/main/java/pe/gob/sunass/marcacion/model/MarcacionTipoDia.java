package pe.gob.sunass.marcacion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "\"marcacion_tipo_dia\"")
public class MarcacionTipoDia {
    @Id
    @Column(name = "\"Tipo_Dia_Id\"")
    private String tipoDiaId;

    @Column(name = "\"Descripcion\"")
    private String descripcion;
}
