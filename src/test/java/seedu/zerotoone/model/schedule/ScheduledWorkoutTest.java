package seedu.zerotoone.model.schedule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zerotoone.testutil.schedule.ScheduleCommandTestUtil.VALID_DATETIME_IN_THE_PAST;
import static seedu.zerotoone.testutil.schedule.ScheduleCommandTestUtil.VALID_DATETIME_JULY;
import static seedu.zerotoone.testutil.schedule.TypicalScheduledWorkouts.getTypicalScheduledWorkouts;
import static seedu.zerotoone.testutil.schedule.TypicalSchedules.SCHEDULE_AT_FIRST_JUNE;

import java.util.List;

import org.junit.jupiter.api.Test;

class ScheduledWorkoutTest {

    @Test
    void isOutDated() {
        DateTime dateTimeJuly = new DateTime(VALID_DATETIME_JULY);
        assertTrue(SCHEDULE_AT_FIRST_JUNE.getScheduledWorkout(dateTimeJuly).get().get(0).isOutDated());

        DateTime dateTimeInThePast = new DateTime(VALID_DATETIME_IN_THE_PAST);
        assertFalse(SCHEDULE_AT_FIRST_JUNE.getScheduledWorkout(dateTimeInThePast).get().get(0).isOutDated());
    }

    @Test
    void testEquals() {
        List<ScheduledWorkout> scheduledWorkouts = getTypicalScheduledWorkouts();
        ScheduledWorkout scheduledWorkoutOne = scheduledWorkouts.get(0);
        ScheduledWorkout scheduledWorkoutTwo = scheduledWorkouts.get(1);

        // same values -> returns true
        ScheduledWorkout scheduledWorkoutOneCopy = getTypicalScheduledWorkouts().get(0);
        assertTrue(scheduledWorkoutOne.equals(scheduledWorkoutOneCopy));

        // same object -> returns true
        assertTrue(scheduledWorkoutOne.equals(scheduledWorkoutOne));

        // null -> returns false
        assertFalse(scheduledWorkoutOne.equals(null));

        // different type -> returns false
        assertFalse(scheduledWorkoutOne.equals(5));

        // different ScheduledWorkout -> returns false
        assertFalse(scheduledWorkoutOne.equals(scheduledWorkoutTwo));
    }
}
