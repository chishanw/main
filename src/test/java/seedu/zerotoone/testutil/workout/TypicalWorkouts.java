package seedu.zerotoone.testutil.workout;

import static seedu.zerotoone.testutil.workout.WorkoutCommandTestUtil.VALID_EXERCISE_DUMBBELL_CRUNCH;
import static seedu.zerotoone.testutil.workout.WorkoutCommandTestUtil.VALID_EXERCISE_LUNGES;
import static seedu.zerotoone.testutil.workout.WorkoutCommandTestUtil.VALID_WORKOUT_NAME_ABS_WORKOUT;
import static seedu.zerotoone.testutil.workout.WorkoutCommandTestUtil.VALID_WORKOUT_NAME_ARMS_WORKOUT;
import static seedu.zerotoone.testutil.workout.WorkoutCommandTestUtil.VALID_WORKOUT_NAME_LEGS_WORKOUT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.zerotoone.model.workout.Workout;
import seedu.zerotoone.model.workout.WorkoutList;
import seedu.zerotoone.testutil.exercise.TypicalExercises;

/**
 * A utility class containing a list of {@code Workout} objects to be used in tests.
 */
public class TypicalWorkouts {
    // Manually added - Workout's details found in {@code CommandTestUtil}
    public static final WorkoutBuilder ARMS_WORKOUT_BUILDER = new WorkoutBuilder()
            .withWorkoutName(VALID_WORKOUT_NAME_ARMS_WORKOUT)
            .withWorkoutExercise(TypicalExercises.BENCH_PRESS)
            .withWorkoutExercise(TypicalExercises.DEADLIFT);
    public static final Workout ARMS_WORKOUT = ARMS_WORKOUT_BUILDER.build();

    public static final WorkoutBuilder LEGS_WORKOUT_BUILDER = new WorkoutBuilder()
            .withWorkoutName(VALID_WORKOUT_NAME_LEGS_WORKOUT)
            .withWorkoutExercise(VALID_EXERCISE_LUNGES);
    public static final Workout LEGS_WORKOUT = LEGS_WORKOUT_BUILDER.build();

    public static final WorkoutBuilder ABS_WORKOUT_BUILDER = new WorkoutBuilder()
            .withWorkoutName(VALID_WORKOUT_NAME_ABS_WORKOUT)
            .withWorkoutExercise(VALID_EXERCISE_DUMBBELL_CRUNCH);
    public static final Workout ABS_WORKOUT = ABS_WORKOUT_BUILDER.build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalWorkouts() {} // prevents instantiation

    /**
     * Returns an {@code WorkoutList} with all the typical workouts.
     */
    public static WorkoutList getTypicalWorkoutList() {
        WorkoutList el = new WorkoutList();
        List<WorkoutBuilder> typicalWorkoutBuilders = new ArrayList<>(
                Arrays.asList(ARMS_WORKOUT_BUILDER, LEGS_WORKOUT_BUILDER, ABS_WORKOUT_BUILDER));

        for (WorkoutBuilder workoutBuilder : typicalWorkoutBuilders) {
            el.addWorkout(workoutBuilder.build());
        }
        return el;
    }
}
