package seedu.zerotoone.model.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zerotoone.testutil.schedule.ScheduleCommandTestUtil.INVALID_DATETIME;
import static seedu.zerotoone.testutil.schedule.ScheduleCommandTestUtil.VALID_DATETIME_IN_THE_PAST;
import static seedu.zerotoone.testutil.schedule.ScheduleCommandTestUtil.VALID_DATETIME_JULY;
import static seedu.zerotoone.testutil.schedule.ScheduleCommandTestUtil.VALID_DATETIME_JUNE;

import org.junit.jupiter.api.Test;

class DateTimeTest {

    @Test
    void isValidDateTime() {
        assertTrue(DateTime.isValidDateTime(VALID_DATETIME_JUNE));
        assertTrue(DateTime.isValidDateTime(VALID_DATETIME_JULY));
        assertTrue(DateTime.isValidDateTime(VALID_DATETIME_IN_THE_PAST));
        assertTrue(DateTime.isValidDateTime("2015-08-12 12:24"));

        assertFalse(DateTime.isValidDateTime(INVALID_DATETIME));
        assertFalse(DateTime.isValidDateTime("2020-02-02 2200"));
        assertFalse(DateTime.isValidDateTime("2020-02-31 12:00"));
    }

    @Test
    void isDateEqualOrLaterThan() {
        DateTime dateTimeOne;
        DateTime dateTimeTwo;

        // same date, same time -> true
        dateTimeOne = new DateTime("2020-04-04 00:00");
        dateTimeTwo = new DateTime("2020-04-04 00:00");
        assertTrue(dateTimeOne.isDateEqualOrLaterThan(dateTimeTwo));

        // same date, different time -> both true
        dateTimeOne = new DateTime("2020-04-04 00:00");
        dateTimeTwo = new DateTime("2020-04-04 03:00");
        assertTrue(dateTimeOne.isDateEqualOrLaterThan(dateTimeTwo));
        assertTrue(dateTimeTwo.isDateEqualOrLaterThan(dateTimeOne));

        // different date, same time -> depends
        dateTimeOne = new DateTime("2020-04-03 00:00");
        dateTimeTwo = new DateTime("2020-04-04 00:00");
        assertFalse(dateTimeOne.isDateEqualOrLaterThan(dateTimeTwo));
        assertTrue(dateTimeTwo.isDateEqualOrLaterThan(dateTimeOne));
    }

    @Test
    void getLocalDateTime() {
        DateTime dateTimeOne = new DateTime(VALID_DATETIME_JUNE);
        DateTime dateTimeTwo = new DateTime(VALID_DATETIME_JUNE);
        assertEquals(dateTimeOne.getLocalDateTime(), dateTimeTwo.getLocalDateTime());
    }

    @Test
    void testEquals() {
        DateTime dateTime = new DateTime(VALID_DATETIME_JUNE);

        // same values -> returns true
        DateTime dateTimeCopy = new DateTime(VALID_DATETIME_JUNE);
        assertTrue(dateTime.equals(dateTimeCopy));

        // same object -> returns true
        assertTrue(dateTime.equals(dateTime));

        // null -> returns false
        assertFalse(dateTime.equals(null));

        // different type -> returns false
        assertFalse(dateTime.equals(5));

        // different datetime -> returns false
        DateTime differentDateTime = new DateTime(VALID_DATETIME_JULY);
        assertFalse(dateTime.equals(differentDateTime));
    }
}
