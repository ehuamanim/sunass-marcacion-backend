package pe.gob.sunass.marcacion.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class AsignacionPersonalRestIn {
    List<Date> dias;
    List<MaestroRestOutDto> actividades;
    List<PersonalDto> personal;
    String modalidad;
    String condiciones;
}
