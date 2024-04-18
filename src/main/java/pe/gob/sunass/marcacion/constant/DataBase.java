package pe.gob.sunass.marcacion.constant;

public enum DataBase {
    CNX_STRING("ORCL_CONNSTRING"),
    USERNAME("ORCL_USERNAME"),
    PASSWORD("ORCL_PASSWORD");

    private final String options;

    private DataBase( String options ){
        this.options = options;
    }

    public String value(){
        return options;
    }
}
