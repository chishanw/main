package seedu.zerotoone.storage.schedule.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.zerotoone.storage.schedule.util.JacksonDateTime.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.zerotoone.testutil.Assert.assertThrows;
import static seedu.zerotoone.testutil.schedule.ScheduleCommandTestUtil.INVALID_DATETIME;
import static seedu.zerotoone.testutil.schedule.ScheduleCommandTestUtil.VALID_DATETIME_JUNE;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.commons.exceptions.IllegalValueException;
import seedu.zerotoone.model.schedule.DateTime;

class JacksonDateTimeTest {

    @Test
    public void toModelType_validDateTime_returnsDateTime() throws Exception {
        JacksonDateTime dateTime = new JacksonDateTime(new DateTime(VALID_DATETIME_JUNE));
        assertEquals(new DateTime(VALID_DATETIME_JUNE), dateTime.toModelType());
    }

    @Test
    public void toModelType_invalidDateTime_throwsIllegalValueException() {
        JacksonDateTime dateTime = new JacksonDateTime(INVALID_DATETIME);
        String expectedMessage = DateTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, dateTime::toModelType);
    }

    @Test
    public void toModelType_nullDateTime_throwsIllegalValueException() {
        JacksonDateTime dateTime = new JacksonDateTime((String) null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DateTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, dateTime::toModelType);
    }
}
