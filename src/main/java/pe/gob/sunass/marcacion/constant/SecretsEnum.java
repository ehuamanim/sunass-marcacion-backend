package pe.gob.sunass.marcacion.constant;

public enum SecretsEnum {
    DB_CNX_STRING( "ORCL_CONNSTRING"),
    DB_USERNAME( "ORCL_USERNAME"),
    DB_PASSWORD( "ORCL_PASSWORD"),
    ALF_URL( "ALF_URL"),
    ALF_USER( "ALF_USER"),
    ALF_PASSWORD( "ALF_PASSWORD");

    private final String options;

    private SecretsEnum( String options ){
        this.options = options;
    }

    public String value(){
        return options;
    }
}
