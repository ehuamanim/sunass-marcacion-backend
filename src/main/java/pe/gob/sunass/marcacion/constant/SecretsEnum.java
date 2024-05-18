package pe.gob.sunass.marcacion.constant;

public enum SecretsEnum {
	
	// CNX DB Marcacion
    DB_CNX_STRING( "ORCL_CONNSTRING"),
    DB_USERNAME( "ORCL_USERNAME"),
    DB_PASSWORD( "ORCL_PASSWORD"),

    // Alfresco
    ALF_URL( "ALF_URL"),
    ALF_USER( "ALF_USER"),
    ALF_PASSWORD( "ALF_PASS"),
    ALF_FOLDER_ID( "ALF_FOLDER_ID"),
    ALF_FOLDER_ROOT_NAME( "ALF_FOLDER_ROOT");

    private final String options;

    private SecretsEnum( String options ){
        this.options = options;
    }

    public String value(){
        return options;
    }
}
