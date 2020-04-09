package seedu.zerotoone.model.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zerotoone.testutil.Assert.assertThrows;
import static seedu.zerotoone.testutil.schedule.TypicalSchedules.SCHEDULE_AT_FIRST_JUNE;
import static seedu.zerotoone.testutil.schedule.TypicalSchedules.getTypicalScheduleList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.model.schedule.exceptions.DuplicateScheduleException;
import seedu.zerotoone.testutil.schedule.OneTimeScheduleBuilder;

class ScheduleListTest {

    private final ScheduleList scheduleList = new ScheduleList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), scheduleList.getScheduleList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> scheduleList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyScheduleList_replacesData() {
        ScheduleList newData = getTypicalScheduleList();
        scheduleList.resetData(newData);
        assertEquals(newData, scheduleList);
    }

    @Test
    public void resetData_withDuplicateSchedules_throwsDuplicateScheduleException() {
        // Two schedules with the same identity fields
        Schedule editedBenchPress = new OneTimeScheduleBuilder(SCHEDULE_AT_FIRST_JUNE).build();
        List<Schedule> newSchedules = Arrays.asList(SCHEDULE_AT_FIRST_JUNE, editedBenchPress);
        ScheduleListStub newData = new ScheduleListStub(newSchedules);

        assertThrows(DuplicateScheduleException.class, () -> scheduleList.resetData(newData));
    }

    @Test
    public void hasSchedule_nullSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> scheduleList.hasSchedule(null));
    }

    @Test
    public void hasSchedule_scheduleNotInScheduleList_returnsFalse() {
        assertFalse(scheduleList.hasSchedule(SCHEDULE_AT_FIRST_JUNE));
    }

    @Test
    public void hasSchedule_scheduleInScheduleList_returnsTrue() {
        scheduleList.addSchedule(SCHEDULE_AT_FIRST_JUNE);
        assertTrue(scheduleList.hasSchedule(SCHEDULE_AT_FIRST_JUNE));
    }

    @Test
    public void hasSchedule_scheduleWithSameIdentityFieldsInScheduleList_returnsTrue() {
        scheduleList.addSchedule(SCHEDULE_AT_FIRST_JUNE);
        Schedule editedBenchPress = new OneTimeScheduleBuilder(SCHEDULE_AT_FIRST_JUNE).build();
        assertTrue(scheduleList.hasSchedule(editedBenchPress));
    }

    @Test
    public void getScheduleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> scheduleList.getScheduleList().remove(0));
    }

    @Test
    void testToString() {
        assertNotNull(scheduleList.toString());
    }

    @Test
    void testHashCode() {
        assertNotNull(scheduleList.hashCode());
    }

    /**
     * A stub ReadOnlyScheduleList whose schedules list can violate interface constraints.
     */
    private static class ScheduleListStub extends ScheduleList {
        private final List<Schedule> schedules = new ArrayList<>();

        ScheduleListStub(Collection<Schedule> schedules) {
            this.schedules.clear();
            this.schedules.addAll(schedules);
        }

        public List<Schedule> getScheduleList() {
            return schedules;
        }
    }
}
