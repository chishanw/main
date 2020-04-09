package seedu.zerotoone.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.zerotoone.testutil.exercise.TypicalExercises.getTypicalExerciseList;
import static seedu.zerotoone.testutil.schedule.TypicalSchedules.getTypicalScheduleList;
import static seedu.zerotoone.testutil.workout.TypicalWorkouts.getTypicalWorkoutList;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.zerotoone.commons.core.GuiSettings;
import seedu.zerotoone.model.exercise.ExerciseList;
import seedu.zerotoone.model.exercise.ReadOnlyExerciseList;
import seedu.zerotoone.model.schedule.ScheduleList;
import seedu.zerotoone.model.userprefs.UserPrefs;
import seedu.zerotoone.model.workout.ReadOnlyWorkoutList;
import seedu.zerotoone.model.workout.WorkoutList;
import seedu.zerotoone.storage.exercise.ExerciseListStorageManager;
import seedu.zerotoone.storage.log.LogListStorageManager;
import seedu.zerotoone.storage.schedule.ScheduleListStorageManager;
import seedu.zerotoone.storage.userprefs.UserPrefsStorageManager;
import seedu.zerotoone.storage.workout.WorkoutListStorageManager;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        ExerciseListStorageManager exerciseListStorage = new ExerciseListStorageManager(
                getTempFilePath("exerciselist"));
        WorkoutListStorageManager workoutListStorage = new WorkoutListStorageManager(
                getTempFilePath("workoutlist"));
        ScheduleListStorageManager scheduleListStorage = new ScheduleListStorageManager(
                getTempFilePath("schedulelist"));
        LogListStorageManager sessionListStorage = new LogListStorageManager(
            getTempFilePath("loglist"));
        UserPrefsStorageManager userPrefsStorage = new UserPrefsStorageManager(getTempFilePath("prefs"));

        storageManager = new StorageManager(userPrefsStorage,
                exerciseListStorage,
                workoutListStorage,
                scheduleListStorage,
                sessionListStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    // -----------------------------------------------------------------------------------------
    // Exercise List
    @Test
    public void exerciseListReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonExerciseListStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonExerciseListStorageTest} class.
         */
        ExerciseList original = getTypicalExerciseList();
        storageManager.saveExerciseList(original);
        ReadOnlyExerciseList retrieved = storageManager.readExerciseList().get();
        assertEquals(original, new ExerciseList(retrieved));
    }

    @Test
    public void getExerciseListFilePath() {
        assertNotNull(storageManager.getExerciseListFilePath());
    }

    // -----------------------------------------------------------------------------------------
    // Workout List
    @Test
    public void workoutListReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonWorkoutListStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonWorkoutListStorageTest} class.
         */
        WorkoutList original = getTypicalWorkoutList();
        storageManager.saveWorkoutList(original);
        ReadOnlyWorkoutList retrieved = storageManager.readWorkoutList().get();
        assertEquals(original, new WorkoutList(retrieved));
    }

    @Test
    public void getWorkoutListFilePath() {
        assertNotNull(storageManager.getWorkoutListFilePath());
    }

    // -----------------------------------------------------------------------------------------
    // Schedule List
    @Test
    public void scheduleListReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonScheduleListStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonScheduleListStorageTest} class.
         */
        ScheduleList original = getTypicalScheduleList();
        storageManager.saveScheduleList(original);
        ScheduleList retrieved = storageManager.readScheduleList().get();
        assertEquals(original, new ScheduleList(retrieved));
    }

    @Test
    public void getScheduleListFilePath() {
        assertNotNull(storageManager.getScheduleListFilePath());
    }
}
