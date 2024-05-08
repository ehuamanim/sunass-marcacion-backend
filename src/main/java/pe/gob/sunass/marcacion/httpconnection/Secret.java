package pe.gob.sunass.marcacion.httpconnection;

import lombok.Data;

@Data
public class Secret {
    private String secretName;
    private String secretValue;
    private int version;
    private String workspace;
    private String environment;
    private String type;
    private boolean isFallback;
    private String updatedAt;
    private String createdAt;
    private String lastFetchedAt;
}
