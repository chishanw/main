package seedu.zerotoone.model.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zerotoone.testutil.Assert.assertThrows;
import static seedu.zerotoone.testutil.schedule.TypicalSchedules.SCHEDULE_AT_FIRST_JULY;
import static seedu.zerotoone.testutil.schedule.TypicalSchedules.SCHEDULE_AT_FIRST_JUNE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.model.schedule.exceptions.DuplicateScheduleException;
import seedu.zerotoone.model.schedule.exceptions.ScheduleNotFoundException;
import seedu.zerotoone.testutil.schedule.OneTimeScheduleBuilder;

class UniqueScheduleListTest {

    private final UniqueScheduleList uniqueScheduleList = new UniqueScheduleList();

    @Test
    public void contains_nullSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduleList.contains(null));
    }

    @Test
    public void contains_scheduleNotInList_returnsFalse() {
        assertFalse(uniqueScheduleList.contains(SCHEDULE_AT_FIRST_JUNE));
    }

    @Test
    public void contains_scheduleInList_returnsTrue() {
        uniqueScheduleList.add(SCHEDULE_AT_FIRST_JUNE);
        assertTrue(uniqueScheduleList.contains(SCHEDULE_AT_FIRST_JUNE));
    }

    @Test
    public void contains_scheduleWithSameIdentityFieldsInList_returnsTrue() {
        uniqueScheduleList.add(SCHEDULE_AT_FIRST_JUNE);
        Schedule editedBenchPress = new OneTimeScheduleBuilder(SCHEDULE_AT_FIRST_JUNE).build();
        assertTrue(uniqueScheduleList.contains(editedBenchPress));
    }

    @Test
    public void add_nullSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduleList.add(null));
    }

    @Test
    public void add_duplicateSchedule_throwsDuplicateScheduleException() {
        uniqueScheduleList.add(SCHEDULE_AT_FIRST_JUNE);
        assertThrows(DuplicateScheduleException.class, () -> uniqueScheduleList.add(SCHEDULE_AT_FIRST_JUNE));
    }

    @Test
    public void setSchedule_nullTargetSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduleList.setSchedule(
                null,
                SCHEDULE_AT_FIRST_JUNE));
    }

    @Test
    public void setSchedule_nullEditedSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduleList.setSchedule(
                SCHEDULE_AT_FIRST_JUNE,
                null));
    }

    @Test
    public void setSchedule_targetScheduleNotInList_throwsScheduleNotFoundException() {
        assertThrows(ScheduleNotFoundException.class, () -> uniqueScheduleList.setSchedule(
                SCHEDULE_AT_FIRST_JUNE,
                SCHEDULE_AT_FIRST_JUNE));
    }

    @Test
    public void setSchedule_editedScheduleIsSameSchedule_success() {
        uniqueScheduleList.add(SCHEDULE_AT_FIRST_JUNE);
        uniqueScheduleList.setSchedule(SCHEDULE_AT_FIRST_JUNE, SCHEDULE_AT_FIRST_JUNE);
        UniqueScheduleList expectedUniqueScheduleList = new UniqueScheduleList();
        expectedUniqueScheduleList.add(SCHEDULE_AT_FIRST_JUNE);
        assertEquals(expectedUniqueScheduleList, uniqueScheduleList);
    }

    @Test
    public void setSchedule_editedScheduleHasSameIdentity_success() {
        uniqueScheduleList.add(SCHEDULE_AT_FIRST_JUNE);
        Schedule editedBenchPress = new OneTimeScheduleBuilder(SCHEDULE_AT_FIRST_JUNE).build();
        uniqueScheduleList.setSchedule(SCHEDULE_AT_FIRST_JUNE, editedBenchPress);
        UniqueScheduleList expectedUniqueScheduleList = new UniqueScheduleList();
        expectedUniqueScheduleList.add(editedBenchPress);
        assertEquals(expectedUniqueScheduleList, uniqueScheduleList);
    }

    @Test
    public void setSchedule_editedScheduleHasDifferentIdentity_success() {
        uniqueScheduleList.add(SCHEDULE_AT_FIRST_JUNE);
        uniqueScheduleList.setSchedule(SCHEDULE_AT_FIRST_JUNE, SCHEDULE_AT_FIRST_JULY);
        UniqueScheduleList expectedUniqueScheduleList = new UniqueScheduleList();
        expectedUniqueScheduleList.add(SCHEDULE_AT_FIRST_JULY);
        assertEquals(expectedUniqueScheduleList, uniqueScheduleList);
    }

    @Test
    public void setSchedule_editedScheduleHasNonUniqueIdentity_throwsDuplicateScheduleException() {
        uniqueScheduleList.add(SCHEDULE_AT_FIRST_JUNE);
        uniqueScheduleList.add(SCHEDULE_AT_FIRST_JULY);
        assertThrows(DuplicateScheduleException.class, () -> uniqueScheduleList.setSchedule(
                SCHEDULE_AT_FIRST_JUNE,
                SCHEDULE_AT_FIRST_JULY));
    }

    @Test
    public void remove_nullSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduleList.remove(null));
    }

    @Test
    public void remove_scheduleDoesNotExist_throwsScheduleNotFoundException() {
        assertThrows(ScheduleNotFoundException.class, () -> uniqueScheduleList.remove(SCHEDULE_AT_FIRST_JUNE));
    }

    @Test
    public void remove_existingSchedule_removesSchedule() {
        uniqueScheduleList.add(SCHEDULE_AT_FIRST_JUNE);
        uniqueScheduleList.remove(SCHEDULE_AT_FIRST_JUNE);
        UniqueScheduleList expectedUniqueScheduleList = new UniqueScheduleList();
        assertEquals(expectedUniqueScheduleList, uniqueScheduleList);
    }

    @Test
    public void setSchedules_nullUniqueScheduleList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduleList.setSchedules((UniqueScheduleList) null));
    }

    @Test
    public void setSchedules_uniqueScheduleList_replacesOwnListWithProvidedUniqueScheduleList() {
        uniqueScheduleList.add(SCHEDULE_AT_FIRST_JUNE);
        UniqueScheduleList expectedUniqueScheduleList = new UniqueScheduleList();
        expectedUniqueScheduleList.add(SCHEDULE_AT_FIRST_JULY);
        uniqueScheduleList.setSchedules(expectedUniqueScheduleList);
        assertEquals(expectedUniqueScheduleList, uniqueScheduleList);
    }

    @Test
    public void setSchedules_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduleList.setSchedules((List<Schedule>) null));
    }

    @Test
    public void setSchedules_list_replacesOwnListWithProvidedList() {
        uniqueScheduleList.add(SCHEDULE_AT_FIRST_JUNE);
        List<Schedule> scheduleList = Collections.singletonList(SCHEDULE_AT_FIRST_JULY);
        uniqueScheduleList.setSchedules(scheduleList);
        UniqueScheduleList expectedUniqueScheduleList = new UniqueScheduleList();
        expectedUniqueScheduleList.add(SCHEDULE_AT_FIRST_JULY);
        assertEquals(expectedUniqueScheduleList, uniqueScheduleList);
    }

    @Test
    public void setSchedules_listWithDuplicateSchedules_throwsDuplicateScheduleException() {
        List<Schedule> listWithDuplicateSchedules = Arrays.asList(SCHEDULE_AT_FIRST_JUNE, SCHEDULE_AT_FIRST_JUNE);
        assertThrows(
                DuplicateScheduleException.class, () -> uniqueScheduleList.setSchedules(listWithDuplicateSchedules));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> uniqueScheduleList.asUnmodifiableList().remove(0));
    }

    @Test
    void iterator() {
        assertNotNull(uniqueScheduleList.iterator());
    }

    @Test
    void testHashCode() {
        assertNotNull(uniqueScheduleList.hashCode());
    }
}
