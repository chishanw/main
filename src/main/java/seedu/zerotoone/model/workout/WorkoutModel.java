package seedu.zerotoone.model.workout;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.zerotoone.model.exercise.Exercise;

/**
 * Model for Workout.
 */
public interface WorkoutModel {
    Predicate<Workout> PREDICATE_SHOW_ALL_WORKOUTS = unused -> true;

    // -----------------------------------------------------------------------------------------
    // Workout List
    /**
     * Returns the user prefs' workout list file path.
     */
    Path getWorkoutListFilePath();

    /**
     * Sets the user prefs' workout list file path.
     */
    void setWorkoutListFilePath(Path workoutListFilePath);

    /**
     * Replaces workout list data with the data in {@code workoutList}.
     */
    void setWorkoutList(ReadOnlyWorkoutList workoutList);

    /** Returns the WorkoutList */
    ReadOnlyWorkoutList getWorkoutList();

    /**
     * Returns true if a exercise with the same identity as {@code workout} exists in the workout list.
     */
    boolean hasWorkout(Workout workout);

    /**
     * Deletes the given workout.
     * The workout must exist in the workout list.
     */
    void deleteWorkout(Workout target);

    /**
     * Deletes the {@code exericse} from all workouts in {@code WorkoutList}.
     * @param exercise The exercise to be deleted.
     */
    void deleteExerciseFromWorkouts(Exercise exercise);

    /**
     * Adds the given workout.
     * {@code workout} must not already exist in the workout list.
     */
    void addWorkout(Workout workout);

    /**
     * Replaces the given workout {@code target} with {@code editedWorkout}.
     * {@code target} must exist in the workout list.
     * The workout identity of {@code editedWorkout} must not be the same as another
     * existing workout in the workout list.
     */
    void setWorkout(Workout target, Workout editedExercise);

    /**
     * Returns an unmodifiable view of the list of {@code Workout} backed by the internal list of
     * {@code versionedWorkoutList}
     */
    ObservableList<Workout> getFilteredWorkoutList();

    /**
     * Updates the filter of the filtered workout list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredWorkoutList(Predicate<Workout> predicate);
}
