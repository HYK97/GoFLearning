package create.factory_method.apply;

import java.util.Calendar;
import java.util.Locale;

public class CalenderExample {
    public static void main(String[] args) {
        Calendar calendarDefault = Calendar.getInstance();
        System.out.println("calendar.getClass() = " + calendarDefault.getClass());
        Calendar calenderTh = Calendar.getInstance(Locale.forLanguageTag("th-TH-x-lvariant-TH"));
        System.out.println("calenderTh.getClass() = " + calenderTh.getClass());
        Calendar calenderJP = Calendar.getInstance(Locale.forLanguageTag("ja-JP-x-lvariant-JP"));
        System.out.println("calenderJP.getClass() = " + calenderJP.getClass());
    }
}
