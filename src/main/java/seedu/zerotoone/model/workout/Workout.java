package seedu.zerotoone.model.workout;

import static seedu.zerotoone.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import seedu.zerotoone.model.exercise.Exercise;

/**
 * Represents a Workout in the workout list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Workout {

    // Identity fields
    private final WorkoutName workoutName;
    private final List<Exercise> workoutExercises = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public Workout(WorkoutName workoutName, List<Exercise> workoutExercises) {
        requireAllNonNull(workoutName, workoutExercises);
        this.workoutName = workoutName;
        this.workoutExercises.addAll(workoutExercises);
    }

    public WorkoutName getWorkoutName() {
        return workoutName;
    }

    public List<Exercise> getWorkoutExercises() {
        return Collections.unmodifiableList(workoutExercises);
    }

    /**
     * Returns true if both workouts of the same workoutName.
     * This defines a weaker notion of equality between two workouts.
     */
    public boolean isSameWorkout(Workout otherWorkout) {
        if (otherWorkout == this) {
            return true;
        }

        return otherWorkout != null
                && otherWorkout.getWorkoutName().equals(getWorkoutName());
    }

    /**
     * Deletes an exercise from the workout.
     * @param exericse The exercise to be deleted.
     */
    public void deleteExercise(Exercise exericse) {
        while (workoutExercises.contains(exericse)) {
            workoutExercises.remove(exericse);
        }
    }

    /**
     * Returns true if both workouts have the same identity and data fields.
     * This defines a stronger notion of equality between two workouts.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Workout)) {
            return false;
        }

        Workout otherWorkout = (Workout) other;
        return otherWorkout.getWorkoutName().equals(getWorkoutName())
                && otherWorkout.getWorkoutExercises().equals(getWorkoutExercises());
    }

    @Override
    public int hashCode() {
        return Objects.hash(workoutName, workoutExercises);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getWorkoutName())
                .append(" Workout set: ")
                .append(getWorkoutExercises().toString());
        return builder.toString();
    }

}
