package seedu.zerotoone.model.schedule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zerotoone.testutil.schedule.TypicalSchedules.getTypicalScheduleList;

import org.junit.jupiter.api.Test;

class SchedulerTest {

    @Test
    void testEquals() {
        ScheduleList scheduleList = new ScheduleList();
        Scheduler scheduler = new Scheduler(scheduleList);
        Scheduler schedulerCopy = new Scheduler(scheduleList);

        // same values -> returns true
        assertTrue(scheduler.equals(schedulerCopy));

        // same object -> returns true
        assertTrue(scheduler.equals(scheduler));

        // null -> returns false
        assertFalse(scheduler.equals(null));

        // different types -> returns false
        assertFalse(scheduler.equals(5));

        // different ScheduleList -> returns false
        assertFalse(scheduler.equals(new Scheduler(getTypicalScheduleList())));
    }
}
