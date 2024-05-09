package pe.gob.sunass.marcacion.httpconnection;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SecretList {
    private List<Secret> data;
}
