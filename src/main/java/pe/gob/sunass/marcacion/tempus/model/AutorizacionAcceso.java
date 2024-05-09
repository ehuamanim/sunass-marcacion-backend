
package pe.gob.sunass.marcacion.tempus.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;

@Entity
@Table(name = "AUTORIZACION_ACCESO", schema = "SUNASS")
@NamedStoredProcedureQuery( 
    name="usp_info_marcacion", 
    procedureName = "usp_info_marcacion",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name="nrodoc", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name="fecha_ini", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name="fecha_fin", type = String.class)
    } )
public class AutorizacionAcceso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_AutAcceso")
    private Long id;

    @Column(name = "Codigo", nullable = false)
    private String codigo;

    @Column(name = "Empresa", nullable = false)
    private String empresa;

    @Column(name = "Id_TipoLectora")
    private Integer idTipoLectora;

    @Column(name = "Valor")
    private String valor;

    @Column(name = "Fecha_Fin")
    private LocalDateTime fechaFin;

    @Column(name = "Fecha_Ini")
    private LocalDateTime fechaIni;
}
