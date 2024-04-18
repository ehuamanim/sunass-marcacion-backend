package pe.gob.sunass.marcacion.httpconnection;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestConnection {
    private String token;
    private String env;
}
