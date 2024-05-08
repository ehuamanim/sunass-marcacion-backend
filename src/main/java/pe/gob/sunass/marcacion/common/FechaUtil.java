package pe.gob.sunass.marcacion.common;

import java.util.Calendar;
import java.util.Date;

public class FechaUtil {

    public static Date getDateWithoutHours( Date date ){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); 
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date newDate = calendar.getTime();
        return newDate;
    }

}
