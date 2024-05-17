package pe.gob.sunass.marcacion.common;

public class StringUtil {

    public static final String concat(String ...values){
        if( isEmpty(values) ){
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (var each : values) {
            sb.append( each );
        }

        return sb.toString();
    }

    public static final boolean isEmpty( Object obj ){
        return obj==null;
    }

}
