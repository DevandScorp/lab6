import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class DateLocaleFormat {

    public static String getTimeStyle(Date now,Locale currentLocale) {
        DateFormat formatter = DateFormat.getTimeInstance(DateFormat.FULL, currentLocale);
        return formatter.format(now);
    }

}

