package pe.gob.sunass.marcacion.httpconnection.restin;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AuthRestIn {

    private String usuario;
    private String clave;

}
