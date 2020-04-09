package seedu.zerotoone.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.zerotoone.commons.core.LogsCenter;
import seedu.zerotoone.commons.exceptions.DataConversionException;
import seedu.zerotoone.model.exercise.ReadOnlyExerciseList;
import seedu.zerotoone.model.log.ReadOnlyLogList;
import seedu.zerotoone.model.schedule.ScheduleList;
import seedu.zerotoone.model.userprefs.ReadOnlyUserPrefs;
import seedu.zerotoone.model.userprefs.UserPrefs;
import seedu.zerotoone.model.workout.ReadOnlyWorkoutList;
import seedu.zerotoone.storage.exercise.ExerciseListStorage;
import seedu.zerotoone.storage.log.LogListStorage;
import seedu.zerotoone.storage.schedule.ScheduleListStorage;
import seedu.zerotoone.storage.userprefs.UserPrefsStorage;
import seedu.zerotoone.storage.workout.WorkoutListStorage;

/**
 * Manages storage of ExerciseList data in local storage.
 */
public class StorageManager implements Storage {
    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private UserPrefsStorage userPrefsStorage;
    private ExerciseListStorage exerciseListStorage;
    private WorkoutListStorage workoutListStorage;
    private ScheduleListStorage scheduleListStorage;
    private LogListStorage logListStorage;

    public StorageManager(UserPrefsStorage userPrefsStorage, ExerciseListStorage exerciseListStorage,
            WorkoutListStorage workoutListStorage, ScheduleListStorage scheduleListStorage,
            LogListStorage logListStorage) {
        super();
        this.userPrefsStorage = userPrefsStorage;
        this.exerciseListStorage = exerciseListStorage;
        this.workoutListStorage = workoutListStorage;
        this.scheduleListStorage = scheduleListStorage;
        this.logListStorage = logListStorage;
    }

    // -----------------------------------------------------------------------------------------
    // Common - User Preferences
    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }

    // -----------------------------------------------------------------------------------------
    // Exercise List
    @Override
    public Path getExerciseListFilePath() {
        return exerciseListStorage.getExerciseListFilePath();
    }

    @Override
    public Optional<ReadOnlyExerciseList> readExerciseList() throws DataConversionException, IOException {
        return readExerciseList(exerciseListStorage.getExerciseListFilePath());
    }

    @Override
    public Optional<ReadOnlyExerciseList> readExerciseList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return exerciseListStorage.readExerciseList(filePath);
    }

    @Override
    public void saveExerciseList(ReadOnlyExerciseList exerciseList) throws IOException {
        saveExerciseList(exerciseList, exerciseListStorage.getExerciseListFilePath());
    }

    @Override
    public void saveExerciseList(ReadOnlyExerciseList exerciseList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        exerciseListStorage.saveExerciseList(exerciseList, filePath);
    }

    // -----------------------------------------------------------------------------------------
    // Workout List
    @Override
    public Path getWorkoutListFilePath() {
        return workoutListStorage.getWorkoutListFilePath();
    }

    @Override
    public Optional<ReadOnlyWorkoutList> readWorkoutList() throws DataConversionException, IOException {
        return readWorkoutList(workoutListStorage.getWorkoutListFilePath());
    }

    @Override
    public Optional<ReadOnlyWorkoutList> readWorkoutList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return workoutListStorage.readWorkoutList(filePath);
    }

    @Override
    public void saveWorkoutList(ReadOnlyWorkoutList workoutList) throws IOException {
        saveWorkoutList(workoutList, workoutListStorage.getWorkoutListFilePath());
    }

    @Override
    public void saveWorkoutList(ReadOnlyWorkoutList workoutList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        workoutListStorage.saveWorkoutList(workoutList, filePath);
    }

    // Schedule List
    @Override
    public Path getScheduleListFilePath() {
        return scheduleListStorage.getScheduleListFilePath();
    }

    @Override
    public Optional<ScheduleList> readScheduleList() throws DataConversionException, IOException {
        return readScheduleList(scheduleListStorage.getScheduleListFilePath());
    }

    @Override
    public Optional<ScheduleList> readScheduleList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return scheduleListStorage.readScheduleList(filePath);
    }

    @Override
    public void saveScheduleList(ScheduleList scheduleList) throws IOException {
        saveScheduleList(scheduleList, scheduleListStorage.getScheduleListFilePath());
    }

    @Override
    public void saveScheduleList(ScheduleList scheduleList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        scheduleListStorage.saveScheduleList(scheduleList, filePath);
    }

    // Session List

    @Override
    public Path getLogListFilePath() {
        return logListStorage.getLogListFilePath();
    }

    @Override
    public Optional<ReadOnlyLogList> readLogList() throws DataConversionException, IOException {
        return readLogList(logListStorage.getLogListFilePath());
    }

    @Override
    public Optional<ReadOnlyLogList> readLogList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return logListStorage.readLogList(filePath);
    }

    @Override
    public void saveLogList(ReadOnlyLogList sessionList) throws IOException {
        saveLogList(sessionList, logListStorage.getLogListFilePath());
    }

    @Override
    public void saveLogList(ReadOnlyLogList sessionList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        logListStorage.saveLogList(sessionList, filePath);
    }
}
