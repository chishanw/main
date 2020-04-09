package seedu.zerotoone.model.schedule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zerotoone.testutil.schedule.ScheduleCommandTestUtil.VALID_DATETIME_JULY;
import static seedu.zerotoone.testutil.schedule.TypicalSchedules.SCHEDULE_AT_FIRST_JULY;
import static seedu.zerotoone.testutil.schedule.TypicalSchedules.SCHEDULE_AT_FIRST_JUNE;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.testutil.schedule.OneTimeScheduleBuilder;

class OneTimeScheduleTest {

    @Test
    void testIsSameSchedule() {
        // same values -> returns true
        Schedule copy = new OneTimeScheduleBuilder(SCHEDULE_AT_FIRST_JUNE).build();
        assertTrue(SCHEDULE_AT_FIRST_JUNE.isSameSchedule(copy));

        // same object -> returns true
        assertTrue(SCHEDULE_AT_FIRST_JUNE.isSameSchedule(SCHEDULE_AT_FIRST_JUNE));

        // null -> returns false
        assertFalse(SCHEDULE_AT_FIRST_JUNE.isSameSchedule(null));

        // different schedule -> returns false
        assertFalse(SCHEDULE_AT_FIRST_JUNE.isSameSchedule(SCHEDULE_AT_FIRST_JULY));

        // different datetime -> returns false
        Schedule edited = new OneTimeScheduleBuilder(SCHEDULE_AT_FIRST_JUNE)
                .withDateTime(new DateTime(VALID_DATETIME_JULY))
                .build();
        assertFalse(SCHEDULE_AT_FIRST_JUNE.isSameSchedule(edited));

        // different workout -> returns false
        edited = new OneTimeScheduleBuilder(SCHEDULE_AT_FIRST_JUNE)
                .withWorkout(SCHEDULE_AT_FIRST_JULY.getWorkoutToSchedule())
                .build();
        assertFalse(SCHEDULE_AT_FIRST_JUNE.isSameSchedule(edited));
    }

    @Test
    void testEquals() {
        // different type -> returns false
        assertFalse(SCHEDULE_AT_FIRST_JUNE.equals(5));
    }
}
