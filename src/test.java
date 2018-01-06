import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class test {

    public static void main(String[] args) {
        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();

        calendar.setTime(date);
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println(calendar.getTime());
        SimpleDateFormat formaToDay = new SimpleDateFormat("EEE, d MMM");
        String dayDate = formaToDay.format(calendar.getTime());
        System.out.println(dayDate);
    }
}
