package seedu.zerotoone.storage.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.zerotoone.testutil.Assert.assertThrows;
import static seedu.zerotoone.testutil.schedule.TypicalSchedules.SCHEDULE_AT_FIRST_JUNE;
import static seedu.zerotoone.testutil.schedule.TypicalSchedules.getTypicalScheduleList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.zerotoone.commons.exceptions.DataConversionException;
import seedu.zerotoone.model.schedule.ScheduleList;

class ScheduleListStorageManagerTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "schedule",
            "ScheduleListStorageManagerTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readScheduleList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readScheduleList(null));
    }

    private Optional<ScheduleList> readScheduleList(String filePath) throws Exception {
        return new ScheduleListStorageManager(Paths.get(filePath))
                .readScheduleList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readScheduleList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readScheduleList("notJsonFormatScheduleList.json"));
    }

    @Test
    public void readScheduleList_invalidScheduleList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readScheduleList("invalidScheduleList.json"));
    }

    @Test
    public void readScheduleList_invalidAndValidScheduleList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readScheduleList("invalidAndValidScheduleList.json"));
    }

    @Test
    public void readAndSaveScheduleList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempScheduleList.json");
        ScheduleList original = getTypicalScheduleList();
        ScheduleListStorageManager jsonScheduleListStorage = new ScheduleListStorageManager(filePath);

        // Save in new file and read back
        jsonScheduleListStorage.saveScheduleList(original, filePath);
        ScheduleList readBack = jsonScheduleListStorage.readScheduleList(filePath).get();
        assertEquals(original, new ScheduleList(readBack));

        // Modify data, overwrite exiting file, and read back
        original.removeSchedule(SCHEDULE_AT_FIRST_JUNE);
        jsonScheduleListStorage.saveScheduleList(original, filePath);
        readBack = jsonScheduleListStorage.readScheduleList(filePath).get();
        assertEquals(original, new ScheduleList(readBack));

        // Save and read without specifying file path
        original.addSchedule(SCHEDULE_AT_FIRST_JUNE);
        jsonScheduleListStorage.saveScheduleList(original); // file path not specified
        readBack = jsonScheduleListStorage.readScheduleList().get(); // file path not specified
        assertEquals(original, new ScheduleList(readBack));

    }

    @Test
    public void saveScheduleList_nullScheduleList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveScheduleList(null, "SomeFile.json"));
    }

    /**
     * Saves {@code scheduleList} at the specified {@code filePath}.
     */
    private void saveScheduleList(ScheduleList scheduleList, String filePath) {
        try {
            new ScheduleListStorageManager(Paths.get(filePath))
                    .saveScheduleList(scheduleList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveScheduleList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveScheduleList(new ScheduleList(), null));
    }
}
