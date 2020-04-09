package seedu.zerotoone.logic;

import static seedu.zerotoone.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.zerotoone.testutil.Assert.assertThrows;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.EXERCISE_NAME_DESC_BENCH_PRESS;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.zerotoone.logic.commands.AboutCommand;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.logic.commands.exercise.CreateCommand;
import seedu.zerotoone.logic.commands.exercise.ExerciseCommand;
import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.ModelManager;
import seedu.zerotoone.model.exercise.Exercise;
import seedu.zerotoone.model.exercise.ReadOnlyExerciseList;
import seedu.zerotoone.model.workout.ReadOnlyWorkoutList;
import seedu.zerotoone.storage.StorageManager;
import seedu.zerotoone.storage.exercise.ExerciseListStorageManager;
import seedu.zerotoone.storage.log.LogListStorageManager;
import seedu.zerotoone.storage.schedule.ScheduleListStorageManager;
import seedu.zerotoone.storage.userprefs.UserPrefsStorageManager;
import seedu.zerotoone.storage.workout.WorkoutListStorageManager;
import seedu.zerotoone.testutil.LogicManagerTestUtil;
import seedu.zerotoone.testutil.exercise.ExerciseBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        ExerciseListStorageManager exerciseListStorage =
                new ExerciseListStorageManager(temporaryFolder.resolve("exerciseList.json"));
        WorkoutListStorageManager workoutListStorage =
                new WorkoutListStorageManager(temporaryFolder.resolve("workoutList.json"));
        UserPrefsStorageManager userPrefsStorage =
                new UserPrefsStorageManager(temporaryFolder.resolve("userPrefs.json"));
        ScheduleListStorageManager scheduleListStorage =
                new ScheduleListStorageManager(temporaryFolder.resolve("scheduleList.json"));
        LogListStorageManager sessionListStorage =
            new LogListStorageManager(temporaryFolder.resolve("sessionList.json"));
        StorageManager storage = new StorageManager(userPrefsStorage,
                exerciseListStorage,
                workoutListStorage,
                scheduleListStorage,
                sessionListStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        LogicManagerTestUtil.assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND, logic, model);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String helpCommand = AboutCommand.COMMAND_WORD;
        LogicManagerTestUtil.assertCommandSuccess(helpCommand, AboutCommand.SHOWING_ABOUT_MESSAGE,
                model, logic, new ModelManager());
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonExerciseListIoExceptionThrowingStub
        ExerciseListStorageManager exerciseListStorage =
                new JsonExerciseListIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionExerciseList.json"));
        WorkoutListStorageManager workoutListStorage =
                new JsonWorkoutListIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionWorkoutList.json"));
        UserPrefsStorageManager userPrefsStorage =
                new UserPrefsStorageManager(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        ScheduleListStorageManager scheduleListStorage =
                new ScheduleListStorageManager(temporaryFolder.resolve("ioExceptionScheduleList.json"));
        LogListStorageManager sessionListStorage =
            new LogListStorageManager(temporaryFolder.resolve("ioExceptionSessionList.json"));
        StorageManager storage = new StorageManager(userPrefsStorage,
                exerciseListStorage,
                workoutListStorage,
                scheduleListStorage,
                sessionListStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String exerciseCreateCommand = ExerciseCommand.COMMAND_WORD + " "
                + CreateCommand.COMMAND_WORD + EXERCISE_NAME_DESC_BENCH_PRESS;

        Exercise expectedExercise = new ExerciseBuilder().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addExercise(expectedExercise);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;

        LogicManagerTestUtil.assertCommandFailure(exerciseCreateCommand, CommandException.class, expectedMessage,
                expectedModel, logic, model);
    }

    @Test
    public void getFilteredExerciseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredExerciseList().remove(0));
    }

    @Test
    public void getFilteredWorkoutList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredWorkoutList().remove(0));
    }

    @Test
    void getSortedScheduledWorkoutList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getSortedScheduledWorkoutList().remove(0));
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonExerciseListIoExceptionThrowingStub extends ExerciseListStorageManager {
        private JsonExerciseListIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveExerciseList(ReadOnlyExerciseList exerciseList, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonWorkoutListIoExceptionThrowingStub extends WorkoutListStorageManager {
        private JsonWorkoutListIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveWorkoutList(ReadOnlyWorkoutList workoutList, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
