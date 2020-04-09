package seedu.zerotoone.storage.schedule.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.zerotoone.testutil.schedule.TypicalSchedules.SCHEDULE_AT_FIRST_JUNE;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.commons.exceptions.IllegalValueException;
import seedu.zerotoone.storage.workout.util.JacksonWorkout;

class JacksonScheduleTest {

    @Test
    public void toModelType_validScheduleDetails_returnsSchedule() throws Exception {
        JacksonSchedule schedule = new JacksonSchedule(SCHEDULE_AT_FIRST_JUNE);
        assertEquals(SCHEDULE_AT_FIRST_JUNE, schedule.toModelType());
    }

    @Test
    public void toModelType_validWorkoutAndDatetime_returnsSchedule() throws IllegalValueException {
        JacksonWorkout jacksonWorkout = new JacksonWorkout(SCHEDULE_AT_FIRST_JUNE.getWorkoutToSchedule());
        JacksonDateTime jacksonDateTime = new JacksonDateTime(SCHEDULE_AT_FIRST_JUNE.getDateTime());

        JacksonSchedule schedule = new JacksonSchedule(jacksonWorkout, jacksonDateTime);
        assertEquals(SCHEDULE_AT_FIRST_JUNE, schedule.toModelType());
    }
}
