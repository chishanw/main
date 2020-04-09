package seedu.zerotoone.model.workout;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.zerotoone.model.exercise.Exercise;

/**
 * Wraps all data at the exercise list level
 * Duplicates are not allowed (by .isSameWorkout comparison)
 */
public class WorkoutList implements ReadOnlyWorkoutList {

    private final UniqueWorkoutList workouts;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        workouts = new UniqueWorkoutList();
    }

    public WorkoutList() {}

    /**
     * Creates an WorkoutList using the Workouts in the {@code toBeCopied}
     */
    public WorkoutList(ReadOnlyWorkoutList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the exercise list with {@code workouts}.
     * {@code workouts} must not contain duplicate workouts.
     */
    public void setWorkouts(List<Workout> workouts) {
        this.workouts.setWorkouts(workouts);
    }

    /**
     * Resets the existing data of this {@code WorkoutList} with {@code newData}.
     */
    public void resetData(ReadOnlyWorkoutList newData) {
        requireNonNull(newData);

        setWorkouts(newData.getWorkoutList());
    }

    //// exercise-level operations

    /**
     * Returns true if a exercise with the same identity as {@code exercise} exists in the exercise list.
     */
    public boolean hasWorkout(Workout exercise) {
        requireNonNull(exercise);
        return workouts.contains(exercise);
    }

    /**
     * Adds a exercise to the exercise list.
     * The exercise must not already exist in the exercise list.
     */
    public void addWorkout(Workout p) {
        workouts.add(p);
    }

    /**
     * Replaces the given exercise {@code target} in the list with {@code editedWorkout}.
     * {@code target} must exist in the exercise list.
     * The exercise identity of {@code editedWorkout} must not be the same as another existing
     * exercise in the exercise list.
     */
    public void setWorkout(Workout target, Workout editedWorkout) {
        requireNonNull(editedWorkout);

        workouts.setWorkout(target, editedWorkout);
    }

    /**
     * Removes {@code key} from this {@code WorkoutList}.
     * {@code key} must exist in the exercise list.
     */
    public void removeWorkout(Workout key) {
        workouts.remove(key);
    }

    /**
     * Removes {@code exercise} from all {@code Workout} in {@code WorkoutList}.
     */
    public void removeExerciseFromWorkouts(Exercise exercise) {
        for (Workout workout : workouts.asUnmodifiableObservableList()) {
            workout.deleteExercise(exercise);
        }
    }

    //// util methods

    @Override
    public String toString() {
        return workouts.asUnmodifiableObservableList().size() + " workouts";
        // TODO: refine later
    }

    @Override
    public ObservableList<Workout> getWorkoutList() {
        return workouts.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof WorkoutList // instanceof handles nulls
                && workouts.equals(((WorkoutList) other).workouts));
    }

    @Override
    public int hashCode() {
        return workouts.hashCode();
    }
}
