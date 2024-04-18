package pe.gob.sunass.marcacion.httpconnection;

import java.util.List;
import java.util.NoSuchElementException;

import lombok.Data;

@Data
public class ResponseConnection {
    private List<ItemConnection> data;

    public ItemConnection getItemFromName( String secretName ){
        return data
            .stream()
            .filter( ic -> ic.getSecretName().equals(secretName) )
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException("No se encuentra el secretName: " + secretName));
    }
}
