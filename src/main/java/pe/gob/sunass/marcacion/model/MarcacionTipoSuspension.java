package pe.gob.sunass.marcacion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "\"marcacion_tipo_suspension\"")
public class MarcacionTipoSuspension {
    @Id
    @Column(name = "\"Tipo_Suspension_RL_Id\"")
    private String tipoSuspensionRlId;

    @Column(name = "\"Descripcion\"")
    private String descripcion;
}
