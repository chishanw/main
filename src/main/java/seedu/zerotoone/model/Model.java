package seedu.zerotoone.model;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.zerotoone.commons.core.GuiSettings;
import seedu.zerotoone.model.exercise.Exercise;
import seedu.zerotoone.model.exercise.ReadOnlyExerciseList;
import seedu.zerotoone.model.log.ReadOnlyLogList;
import seedu.zerotoone.model.schedule.SchedulerModel;
import seedu.zerotoone.model.session.CompletedSet;
import seedu.zerotoone.model.session.CompletedWorkout;
import seedu.zerotoone.model.session.OngoingWorkout;
import seedu.zerotoone.model.session.ReadOnlyCompletedSetList;
import seedu.zerotoone.model.session.ReadOnlyOngoingSetList;
import seedu.zerotoone.model.session.ReadOnlyTimerList;
import seedu.zerotoone.model.userprefs.ReadOnlyUserPrefs;
import seedu.zerotoone.model.workout.Workout;
import seedu.zerotoone.model.workout.WorkoutModel;

/**
 * The API of the Model component.
 */
public interface Model extends WorkoutModel, SchedulerModel {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Exercise> PREDICATE_SHOW_ALL_EXERCISES = unused -> true;
    Predicate<CompletedWorkout> PREDICATE_SHOW_ALL_LOGS = unused -> true;

    // -----------------------------------------------------------------------------------------
    // Common - User Preferences
    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    // -----------------------------------------------------------------------------------------
    // Exercise List
    /**
     * Returns the user prefs' exercise list file path.
     */
    Path getExerciseListFilePath();

    /**
     * Sets the user prefs' exercise list file path.
     */
    void setExerciseListFilePath(Path exerciseListFilePath);

    /**
     * Replaces exercise list data with the data in {@code exerciseList}.
     */
    void setExerciseList(ReadOnlyExerciseList exerciseList);

    /** Returns the ExerciseList */
    ReadOnlyExerciseList getExerciseList();

    /**
     * Returns true if a exercise with the same identity as {@code exercise} exists in the exercise list.
     */
    boolean hasExercise(Exercise exercise);

    /**
     * Deletes the given exercise.
     * The exercise must exist in the exercise list.
     */
    void deleteExercise(Exercise target);

    /**
     * Adds the given exercise.
     * {@code exercise} must not already exist in the exercise list.
     */
    void addExercise(Exercise exercise);

    /**
     * Replaces the given exercise {@code target} with {@code editedExercise}.
     * {@code target} must exist in the exercise list.
     * The exercise identity of {@code editedExercise} must not be the same as another
     * existing exercise in the exercise list.
     */
    void setExercise(Exercise target, Exercise editedExercise);

    /**
     * Returns an unmodifiable view of the list of {@code Exercise} backed by the internal list of
     * {@code versionedExerciseList}
     */
    ObservableList<Exercise> getFilteredExerciseList();

    /**
     * Updates the filter of the filtered exercise list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredExerciseList(Predicate<Exercise> predicate);

    // -----------------------------------------------------------------------------------------
    // Session
    /**
     * Returns true if a workout has currently started.
     */
    boolean isInSession();

    OngoingWorkout startSession(Workout workoutToStart, LocalDateTime currentDateTime);

    void stopSession(LocalDateTime currentDateTime);

    ReadOnlyOngoingSetList getOngoingSetList();

    ReadOnlyCompletedSetList getLastSet();

    ReadOnlyTimerList getTimerList();

    // todo write java docs

    Optional<OngoingWorkout> getCurrentWorkout();

    CompletedSet skip();

    CompletedSet done();

    Boolean hasExerciseLeft();

    // -----------------------------------------------------------------------------------------
    // Log
    ReadOnlyLogList getLogList();

    ArrayList<CompletedWorkout> getLogListCopyAsArrayList();

    void deleteLog(int target);

    ObservableList<CompletedWorkout> getFilteredLogList();

    void updateFilteredLogList(Predicate<CompletedWorkout> predicate);

    Path getLogListFilePath();

    Optional<LocalDateTime> getStatisticsStartDateRange();

    Optional<LocalDateTime> getStatisticsEndDateRange();

    void setLogListFilePath(Path logListFilePath);


    void setStatisticsDateRange(Optional<LocalDateTime> startRange, Optional<LocalDateTime> endRange);

    void shutdownTimer();
}
