package pe.gob.sunass.marcacion.httpconnection;

import lombok.Data;

@Data
public class ItemConnection {

    private String secretName;
    private String secretValue;
    private String version;
    private String workspace;
    private String environment;
    private String type;
    private String isFallback;

}
