package seedu.zerotoone.model;

import static java.util.Objects.requireNonNull;
import static seedu.zerotoone.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.zerotoone.commons.core.GuiSettings;
import seedu.zerotoone.commons.core.LogsCenter;
import seedu.zerotoone.model.exercise.Exercise;
import seedu.zerotoone.model.exercise.ExerciseList;
import seedu.zerotoone.model.exercise.ReadOnlyExerciseList;
import seedu.zerotoone.model.session.Session;
import seedu.zerotoone.model.userprefs.ReadOnlyUserPrefs;
import seedu.zerotoone.model.userprefs.UserPrefs;

/**
 * Represents the in-memory model of the exercise list data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final UserPrefs userPrefs;
    private final ExerciseList exerciseList;
    private final FilteredList<Exercise> filteredExercises;
    private Optional<Session> currentSession;

    /**
     * Initializes a ModelManager with the given exerciseList and userPrefs.
     */
    public ModelManager(ReadOnlyUserPrefs userPrefs, ReadOnlyExerciseList exerciseList) {
        super();
        requireAllNonNull(exerciseList, userPrefs);
        logger.fine("Initializing with user prefs " + userPrefs);

        this.exerciseList = new ExerciseList(exerciseList);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredExercises = new FilteredList<>(this.exerciseList.getExerciseList());
        this.currentSession = Optional.empty();
    }

    public ModelManager() {
        this(new UserPrefs(), new ExerciseList());
    }

    // -----------------------------------------------------------------------------------------
    // Common - User Preferences
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    // -----------------------------------------------------------------------------------------
    // Exercise List
    @Override
    public Path getExerciseListFilePath() {
        return userPrefs.getExerciseListFilePath();
    }

    @Override
    public void setExerciseListFilePath(Path exerciseListFilePath) {
        requireNonNull(exerciseListFilePath);
        userPrefs.setExerciseListFilePath(exerciseListFilePath);
    }

    @Override
    public void setExerciseList(ReadOnlyExerciseList exerciseList) {
        this.exerciseList.resetData(exerciseList);
    }

    @Override
    public ReadOnlyExerciseList getExerciseList() {
        return exerciseList;
    }

    @Override
    public boolean hasExercise(Exercise exercise) {
        requireNonNull(exercise);
        return exerciseList.hasExercise(exercise);
    }

    @Override
    public void deleteExercise(Exercise target) {
        exerciseList.removeExercise(target);
    }

    @Override
    public void addExercise(Exercise exercise) {
        exerciseList.addExercise(exercise);
        updateFilteredExerciseList(PREDICATE_SHOW_ALL_EXERCISES);
    }

    @Override
    public void setExercise(Exercise target, Exercise editedExercise) {
        requireAllNonNull(target, editedExercise);
        exerciseList.setExercise(target, editedExercise);
    }

    @Override
    public ObservableList<Exercise> getFilteredExerciseList() {
        return filteredExercises;
    }

    @Override
    public void updateFilteredExerciseList(Predicate<Exercise> predicate) {
        requireNonNull(predicate);
        filteredExercises.setPredicate(predicate);
    }

    @Override
    public boolean isInSession() {
        return this.currentSession.isPresent();
    }

    @Override
    public void startSession(Exercise exerciseToStart, LocalDateTime currentDateTime) {
        Session session = new Session(exerciseToStart, currentDateTime);
        this.currentSession = Optional.of(session);
    }

    @Override
    public void stopSession(LocalDateTime currentDateTime) {
        Session completedSession = this.currentSession.get();
        completedSession.finish(currentDateTime);
        // do smth like save completed workout
        this.currentSession = Optional.empty();
    }

    // -----------------------------------------------------------------------------------------
    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return exerciseList.equals(other.exerciseList)
                && userPrefs.equals(other.userPrefs)
                && filteredExercises.equals(other.filteredExercises);
    }
}
