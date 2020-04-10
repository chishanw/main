package seedu.zerotoone.testutil.log;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.model.exercise.Exercise;
import seedu.zerotoone.model.session.CompletedExercise;
import seedu.zerotoone.model.session.CompletedWorkout;
import seedu.zerotoone.model.workout.WorkoutName;

/**
 * The type Log builder.
 */
public class LogBuilder {

    /**
     * The constant DEFAULT_WORKOUT_NAME.
     */
    public static final String DEFAULT_WORKOUT_NAME = "Legs Workout";
    /**
     * The constant DEFAULT_WORKOUT_LENGTH.
     */
    public static final Long DEFAULT_WORKOUT_LENGTH = 2L;

    private WorkoutName workoutName;
    private List<CompletedExercise> workoutExercises;


    /**
     * Instantiates a new Log builder.
     */
    public LogBuilder() {
        workoutName = new WorkoutName(DEFAULT_WORKOUT_NAME);
        workoutExercises = new ArrayList<>();
    }


    /**
     * Instantiates a new Log builder.
     *
     * @param workoutToCopy the workout to copy
     */
    public LogBuilder(CompletedWorkout workoutToCopy) {
        workoutName = workoutToCopy.getWorkoutName();
        workoutExercises = workoutToCopy.getExercises();
    }


    /**
     * With workout name log builder.
     *
     * @param workoutName the workout name
     * @return the log builder
     */
    public LogBuilder withWorkoutName(String workoutName) {
        this.workoutName = new WorkoutName(workoutName);
        return this;
    }


    /**
     * With workout exercise list log builder.
     *
     * @param workoutExerciseList the workout exercise list
     * @return the log builder
     */
    public LogBuilder withWorkoutExerciseList(List<CompletedExercise> workoutExerciseList) {
        this.workoutExercises = workoutExerciseList;
        return this;
    }

    /**
     * With workout exercise log builder.
     *
     * @param exercises the exercises
     * @return the log builder
     */
    public LogBuilder withWorkoutExercise(CompletedExercise ... exercises) {
        List<CompletedExercise> workoutExercisesCopy = new ArrayList<>(workoutExercises);
        for (CompletedExercise exercise : exercises) {
            workoutExercisesCopy.add(exercise);
        }
        workoutExercises = workoutExercisesCopy;
        return this;
    }

    /**
     * Sets workout exercise.
     *
     * @param target      the target
     * @param newExercise the new exercise
     * @return the workout exercise
     */
    public LogBuilder setWorkoutExercise(Index target, CompletedExercise newExercise) {
        List<CompletedExercise> workoutExercisesCopy = new ArrayList<>(workoutExercises);
        workoutExercisesCopy.set(target.getZeroBased(), newExercise);
        workoutExercises = workoutExercisesCopy;
        return this;
    }

    /**
     * Delete workout exercise log builder.
     *
     * @param exerciseToDelete the exercise to delete
     * @return the log builder
     */
    public LogBuilder deleteWorkoutExercise(Exercise exerciseToDelete) {
        List<CompletedExercise> workoutExercisesCopy = new ArrayList<>(workoutExercises);
        workoutExercisesCopy.remove(exerciseToDelete);
        workoutExercises = workoutExercisesCopy;
        return this;
    }

    /**
     * Build completed workout.
     *
     * @return the completed workout
     */
    public CompletedWorkout build() {
        return new CompletedWorkout(workoutName, workoutExercises,
            LocalDateTime.now(), LocalDateTime.now().plusHours(DEFAULT_WORKOUT_LENGTH));
    }

}
