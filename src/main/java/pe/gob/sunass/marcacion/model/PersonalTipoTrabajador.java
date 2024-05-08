package pe.gob.sunass.marcacion.model;

import jakarta.persistence.*;

import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "\"personal_tipo_trabajador\"", schema = "MARCAREMOTO")
public class PersonalTipoTrabajador implements Serializable {

    @Id
    @Column(name = "\"tipo_persona_id\"", length = 2)
    private String tipoPersonaId;

    @Column(name = "\"descripcion\"", length = 100)
    private String descTipoTrabajador;

    @Column(name = "\"status\"", length = 2)
    private String status;

    @Column(name = "\"flag\"", length = 2)
    private String flag;

}
