package pe.gob.sunass.marcacion.model;

import jakarta.persistence.*;

import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "\"personal_tipodoc\"", schema = "MARCAREMOTO")
public class PersonalTipoDoc implements Serializable {

    @Id
    @Column(name = "\"tipo_doc_id\"", length = 2)
    private String tipoDocId;

    @Column(name = "\"descripcion\"", length = 50)
    private String descTipoDocumento;

    @Column(name = "\"status\"", length = 2)
    private String status;

    @Column(name = "\"flag\"", length = 1)
    private String flag;

}
