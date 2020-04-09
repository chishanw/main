package seedu.zerotoone.testutil.workout;

import java.util.ArrayList;
import java.util.List;

import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.model.exercise.Exercise;
import seedu.zerotoone.model.workout.Workout;
import seedu.zerotoone.model.workout.WorkoutName;

/**
 * A utility class to help with building Workout objects.
 */
public class WorkoutBuilder {

    public static final String DEFAULT_WORKOUT_NAME = "Arms Workout";

    private WorkoutName workoutName;
    private List<Exercise> workoutExercises;

    /**
     * Constructor for a fresh instance of WorkoutBuilder.
     */
    public WorkoutBuilder() {
        workoutName = new WorkoutName(DEFAULT_WORKOUT_NAME);
        workoutExercises = new ArrayList<>();
    }

    /**
     * Initializes the WorkoutBuilder with the data of {@code workoutToCopy}.
     */
    public WorkoutBuilder(Workout workoutToCopy) {
        workoutName = workoutToCopy.getWorkoutName();
        workoutExercises = workoutToCopy.getWorkoutExercises();
    }

    /**
     * Exercises the {@code WorkoutName} of the {@code Workout} that we are building.
     */
    public WorkoutBuilder withWorkoutName(String workoutName) {
        this.workoutName = new WorkoutName(workoutName);
        return this;
    }

    /**
     * Exercises the {@code workoutExercises} of the {@code Workout} that we are building.
     */
    public WorkoutBuilder withWorkoutExerciseList(List<Exercise> workoutExerciseList) {
        this.workoutExercises = workoutExerciseList;
        return this;
    }

    /**
     * Exercises the {@code workoutExercises} of the {@code Workout} that we are building.
     */
    public WorkoutBuilder withWorkoutExercise(Exercise ... exercises) {
        List<Exercise> workoutExercisesCopy = new ArrayList<>(workoutExercises);
        for (Exercise exercise : exercises) {
            workoutExercisesCopy.add(exercise);
        }
        workoutExercises = workoutExercisesCopy;
        return this;
    }

    public WorkoutBuilder setWorkoutExercise(Index target, Exercise newExercise) {
        List<Exercise> workoutExercisesCopy = new ArrayList<>(workoutExercises);
        workoutExercisesCopy.set(target.getZeroBased(), newExercise);
        workoutExercises = workoutExercisesCopy;
        return this;
    }

    public Workout build() {
        return new Workout(workoutName, workoutExercises);
    }

}
