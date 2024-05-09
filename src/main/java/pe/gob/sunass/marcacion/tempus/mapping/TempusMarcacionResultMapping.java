package pe.gob.sunass.marcacion.tempus.mapping;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;

import pe.gob.sunass.marcacion.tempus.dto.TempusMarcacion;

@SqlResultSetMapping(name = "MarcacionResultMapping", classes = {
    @ConstructorResult(targetClass = TempusMarcacion.class, columns = {
            @ColumnResult(name = "CODACT", type = String.class),
            @ColumnResult(name = "ACTIVIDAD", type = String.class),
            @ColumnResult(name = "APELLIDO_PATERNO", type = String.class),
            @ColumnResult(name = "APELLIDO_MATERNO", type = String.class),
            @ColumnResult(name = "NOMBRES", type = String.class),
            @ColumnResult(name = "NRO_DOCUMENTO", type = String.class),
            @ColumnResult(name = "FECHA_DE_INGRESO", type = String.class),
            @ColumnResult(name = "FECHA", type = String.class),
            @ColumnResult(name = "HORA_DE_INICIO", type = String.class),
            @ColumnResult(name = "HORA_DE_FIN", type = String.class),
            @ColumnResult(name = "TIME_IN_MINUTES", type = Integer.class)
    })
})
public class TempusMarcacionResultMapping {

}
