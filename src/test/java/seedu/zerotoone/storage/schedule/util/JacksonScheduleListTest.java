package seedu.zerotoone.storage.schedule.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.zerotoone.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.commons.exceptions.IllegalValueException;
import seedu.zerotoone.commons.util.JsonUtil;
import seedu.zerotoone.model.schedule.ScheduleList;
import seedu.zerotoone.testutil.schedule.TypicalSchedules;

class JacksonScheduleListTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "schedule",
            "JacksonScheduleListTest");
    private static final Path TYPICAL_SCHEDULES_FILE = TEST_DATA_FOLDER.resolve("typicalScheduleList.json");
    private static final Path INVALID_SCHEDULE_FILE = TEST_DATA_FOLDER.resolve("invalidScheduleList.json");
    private static final Path DUPLICATE_SCHEDULE_FILE = TEST_DATA_FOLDER.resolve("duplicateScheduleList.json");

    @Test
    public void toModelType_typicalSchedulesFile_success() throws Exception {
        JacksonScheduleList dataFromFile = JsonUtil.readJsonFile(TYPICAL_SCHEDULES_FILE,
                JacksonScheduleList.class).get();
        ScheduleList scheduleListFromFile = dataFromFile.toModelType();
        ScheduleList typicalSchedulesScheduleList = TypicalSchedules.getTypicalScheduleList();
        assertEquals(scheduleListFromFile, typicalSchedulesScheduleList);
    }

    @Test
    public void toModelType_invalidScheduleFile_throwsIllegalValueException() throws Exception {
        JacksonScheduleList dataFromFile = JsonUtil.readJsonFile(INVALID_SCHEDULE_FILE,
                JacksonScheduleList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateSchedules_throwsIllegalValueException() throws Exception {
        JacksonScheduleList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_SCHEDULE_FILE,
                JacksonScheduleList.class).get();
        assertThrows(IllegalValueException.class, JacksonScheduleList.MESSAGE_DUPLICATE_SCHEDULE,
                dataFromFile::toModelType);
    }

    @Test
    public void constructor_validScheduleList_success() throws Exception {
        JacksonScheduleList dataFromFile = JsonUtil.readJsonFile(TYPICAL_SCHEDULES_FILE,
                JacksonScheduleList.class).get();
        ScheduleList scheduleListFromFile = dataFromFile.toModelType();
        JacksonScheduleList jacksonScheduleList = new JacksonScheduleList(scheduleListFromFile);
        ScheduleList typicalSchedulesScheduleList = TypicalSchedules.getTypicalScheduleList();
        assertEquals(jacksonScheduleList.toModelType(), typicalSchedulesScheduleList);
    }
}
