package pe.gob.sunass.marcacion.model;

import jakarta.persistence.*;

import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "\"personal_cargo\"", schema = "MARCAREMOTO")
public class PersonalCargo implements Serializable {

    @Id
    @Column(name = "\"cargo_id\"", length = 3, nullable = false)
    private String cargoId;

    @Column(name = "\"descripcion\"", length = 200)
    private String descCargo;

    @Column(name = "\"status\"", length = 2)
    private String status;

    @Column(name = "\"flag\"", length = 1)
    private String flag;
}
