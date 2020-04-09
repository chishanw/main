package seedu.zerotoone.model.schedule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zerotoone.testutil.Assert.assertThrows;
import static seedu.zerotoone.testutil.schedule.TypicalSchedules.SCHEDULE_AT_FIRST_JULY;
import static seedu.zerotoone.testutil.schedule.TypicalSchedules.SCHEDULE_AT_FIRST_JUNE;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.model.schedule.exceptions.DuplicateScheduledWorkoutException;

class UniqueScheduledWorkoutListTest {

    private final UniqueScheduledWorkoutList uniqueScheduledWorkoutList = new UniqueScheduledWorkoutList();
    private final DateTime now = DateTime.now();
    private final ScheduledWorkout scheduledWorkoutOne =
            SCHEDULE_AT_FIRST_JUNE.getScheduledWorkout(now).get().get(0);
    private final ScheduledWorkout scheduledWorkoutOneCopy =
            SCHEDULE_AT_FIRST_JUNE.getScheduledWorkout(now).get().get(0);
    private final ScheduledWorkout scheduledWorkoutTwo =
            SCHEDULE_AT_FIRST_JULY.getScheduledWorkout(now).get().get(0);

    @Test
    public void contains_nullScheduledWorkout_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduledWorkoutList.contains(null));
    }

    @Test
    public void contains_scheduledWorkoutNotInList_returnsFalse() {
        assertFalse(uniqueScheduledWorkoutList.contains(scheduledWorkoutOne));
    }

    @Test
    public void contains_scheduledWorkoutInList_returnsTrue() {
        uniqueScheduledWorkoutList.add(scheduledWorkoutOne);
        assertTrue(uniqueScheduledWorkoutList.contains(scheduledWorkoutOne));
    }

    @Test
    public void contains_scheduledWorkoutWithSameIdentityFieldsInList_returnsTrue() {
        uniqueScheduledWorkoutList.add(scheduledWorkoutOne);
        assertTrue(uniqueScheduledWorkoutList.contains(scheduledWorkoutOneCopy));
    }

    @Test
    public void add_nullScheduledWorkout_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduledWorkoutList.add(null));
    }

    @Test
    public void add_duplicateScheduledWorkout_throwsDuplicateScheduledWorkoutException() {
        uniqueScheduledWorkoutList.add(scheduledWorkoutTwo);
        assertThrows(
                DuplicateScheduledWorkoutException.class, () -> uniqueScheduledWorkoutList.add(scheduledWorkoutTwo));
    }

    @Test
    void iterator() {
        assertNotNull(uniqueScheduledWorkoutList.iterator());
    }
}
