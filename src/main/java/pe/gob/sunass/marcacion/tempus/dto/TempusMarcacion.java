package pe.gob.sunass.marcacion.tempus.dto;

import lombok.Data;

@Data
public class TempusMarcacion {
    private String codAct;
    private String actividad;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombres;
    private String nroDocumento;
    private String fechaDeIngreso;
    private String fecha;
    private String horaDeInicio;
    private String horaDeFin;
    private Integer timeInMinutes;
}
