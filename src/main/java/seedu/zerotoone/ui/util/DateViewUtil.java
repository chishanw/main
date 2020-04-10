package seedu.zerotoone.ui.util;

import static seedu.zerotoone.commons.util.DateUtil.getPrettyDateTimeString;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


/**
 * The type Date view util.
 */
public class DateViewUtil {

    public static String getPrettyDateTime(LocalDateTime time) {
        int year = time.getYear();
        int day = time.getDayOfMonth();

        String month = capitalize(time.getMonth().toString());
        String dayOfWeek = capitalize(time.getDayOfWeek().toString().substring(0, 3));

        int startHour = time.getHour();
        int startMinute = time.getMinute();
        return String.format("%s %s %s %s,  %02d:%02d", dayOfWeek, day, month, year, startHour, startMinute);
    }

    /**
     * Gets pretty date range date time.
     *
     * @param start the start
     * @param end   the end
     * @return the pretty date range date time
     */
    public static String getPrettyDateRangeDateTime(LocalDateTime start, LocalDateTime end) {
        if (isDateEqual(start, end)) {
            return getPrettyDateRangeSameDay(start, end);
        }
        return getPrettyDateRangeDifferentDay(start, end);
    }

    /**
     * Gets pretty date range same day.
     *
     * @param start the start
     * @param end   the end
     * @return the pretty date range same day
     */
    public static String getPrettyDateRangeSameDay(LocalDateTime start, LocalDateTime end) {
        int year = start.getYear();
        int day = start.getDayOfMonth();

        String month = capitalize(start.getMonth().toString());
        String dayOfWeek = capitalize(start.getDayOfWeek().toString().substring(0, 3));

        int startHour = start.getHour();
        int startMinute = start.getMinute();
        int endHour = end.getHour();
        int endMinute = end.getMinute();
        return String.format("%s %s %s %s,  %02d:%02d - %02d:%02d", dayOfWeek, day, month, year, startHour,
            startMinute, endHour, endMinute);
    }

    /**
     * Gets pretty date range different day.
     *
     * @param start the start
     * @param end   the end
     * @return the pretty date range different day
     */
    public static String getPrettyDateRangeDifferentDay(LocalDateTime start, LocalDateTime end) {
        return getPrettyDateTimeString(start) + " - " + getPrettyDateTimeString(end);
    }

    /**
     * Gets pretty time difference.
     *
     * @param start the start
     * @param end   the end
     * @return the pretty time difference
     */
    public static String getPrettyTimeDifference(LocalDateTime start, LocalDateTime end) {
        LocalDateTime tempDateTime = LocalDateTime.from(start);

        long hours = tempDateTime.until(end, ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusHours(hours);

        long minutes = tempDateTime.until(end, ChronoUnit.MINUTES);
        tempDateTime = tempDateTime.plusMinutes(minutes);

        long seconds = tempDateTime.until(end, ChronoUnit.SECONDS);

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    private static boolean isDateEqual(LocalDateTime start, LocalDateTime end) {
        return start.getYear() == end.getYear() && start.getMonthValue() == end.getMonthValue()
            && start.getDayOfMonth() == end.getDayOfMonth();
    }

    private static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    /**
     * Gets pretty duration.
     *
     * @param duration the duration eg 6h 50m 15s
     * @return the pretty duration
     */
    public static String getPrettyDuration(Duration duration) {
        return duration.toString()
            .substring(2)
            .replaceAll("(\\d[HMS])(?!$)", "$1 ")
            .toLowerCase();
    }


    public static Long getDurationInMinutes(LocalDateTime start, LocalDateTime end) {
        return (Duration.between(start, end)).toMinutes();
    }
}
