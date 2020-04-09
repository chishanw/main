package seedu.zerotoone.model.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zerotoone.testutil.Assert.assertThrows;
import static seedu.zerotoone.testutil.schedule.TypicalScheduledWorkouts.getTypicalScheduledWorkoutList;
import static seedu.zerotoone.testutil.schedule.TypicalSchedules.SCHEDULE_AT_FIRST_JULY;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.zerotoone.model.schedule.exceptions.DuplicateScheduledWorkoutException;

class ScheduledWorkoutListTest {
    private final ScheduledWorkoutList scheduledWorkoutList = new ScheduledWorkoutList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), scheduledWorkoutList.getScheduledWorkoutList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> scheduledWorkoutList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyScheduleList_replacesData() {
        ScheduledWorkoutList newData = getTypicalScheduledWorkoutList();
        scheduledWorkoutList.resetData(newData);
        assertEquals(newData, scheduledWorkoutList);
    }

    @Test
    public void resetData_withDuplicateExercises_throwsDuplicateExerciseException() {
        // Two exercises with the same identity fields
        DateTime now = DateTime.now();
        ScheduledWorkout scheduledWorkoutOne = SCHEDULE_AT_FIRST_JULY.getScheduledWorkout(now).get().get(0);
        ScheduledWorkout scheduledWorkoutTwo = SCHEDULE_AT_FIRST_JULY.getScheduledWorkout(now).get().get(0);

        List<ScheduledWorkout> newExercises = Arrays.asList(scheduledWorkoutOne, scheduledWorkoutTwo);
        ScheduledWorkoutListStub newData = new ScheduledWorkoutListStub(newExercises);

        assertThrows(DuplicateScheduledWorkoutException.class, () -> scheduledWorkoutList.resetData(newData));
    }

    @Test
    public void getScheduledWorkoutList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> scheduledWorkoutList
                .getScheduledWorkoutList()
                .remove(0));
    }

    @Test
    void testToString() {
        assertNotNull(scheduledWorkoutList.toString());
    }

    @Test
    void testEquals() {
        // same objects, return true
        assertTrue(scheduledWorkoutList.equals(scheduledWorkoutList));

        // null, return true
        assertFalse(scheduledWorkoutList.equals(null));

        // different objects but same content
        ScheduledWorkoutList scheduledWorkoutListOne = new ScheduledWorkoutList();
        ScheduledWorkoutList scheduledWorkoutListTwo = new ScheduledWorkoutList();
        assertTrue(scheduledWorkoutListOne.equals(scheduledWorkoutListTwo));
    }

    @Test
    void testHashCode() {
        assertNotNull(scheduledWorkoutList.hashCode());
    }

    /**
     * A stub ReadOnlyScheduledWorkoutList whose exercises list can violate interface constraints.
     */
    private static class ScheduledWorkoutListStub implements ReadOnlyScheduledWorkoutList {
        private final ObservableList<ScheduledWorkout> scheduledWorkouts = FXCollections.observableArrayList();

        ScheduledWorkoutListStub(Collection<ScheduledWorkout> scheduledWorkouts) {
            this.scheduledWorkouts.setAll(scheduledWorkouts);
        }

        @Override
        public ObservableList<ScheduledWorkout> getScheduledWorkoutList() {
            return scheduledWorkouts;
        }
    }

}
