package pe.gob.sunass.marcacion.common;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

    public static Date addHours(Date date, int hoursToAdd) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        localDateTime = localDateTime.plusHours(hoursToAdd);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static Date addMinutes(Date date, int minutesToAdd) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        localDateTime = localDateTime.plusMinutes(minutesToAdd);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static Date addTime(Date date, int hoursToAdd, int minutesToAdd) {
        Date dateHours = addHours(date, hoursToAdd);
        Date dateFinal = addMinutes(dateHours, minutesToAdd);
        return dateFinal;
    }

}
