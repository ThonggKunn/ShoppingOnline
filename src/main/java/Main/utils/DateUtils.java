package Main.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hibernate.type.descriptor.java.JdbcDateJavaType.DATE_FORMAT;

public class DateUtils {

    public static String formatDate(Date date) {
        if (date == null) return null;

        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        return formatter.format(date);
    }
}