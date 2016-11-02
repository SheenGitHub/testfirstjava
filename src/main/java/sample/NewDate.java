package sample;

import java.time.*;
import java.time.temporal.ChronoField;
import java.util.Date;

/**
 * Created by Administrator on 2016/7/9.
 */
public class NewDate {
    public static void main(String[] args) {

        LocalDate date = LocalDate.of(2014, 3, 18);
        int year = date.getYear();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        DayOfWeek dow = date.getDayOfWeek();
        int len = date.lengthOfMonth();
        boolean leap = date.isLeapYear();

        LocalDate today = LocalDate.now();

        LocalTime time = LocalTime.of(13, 45, 20);
        LocalDate date1 = LocalDate.parse("2014-03-18");

        today.atTime(time);


    }
}
