package seedu.zerotoone.testutil.log;

import static seedu.zerotoone.testutil.workout.WorkoutCommandTestUtil.VALID_WORKOUT_NAME_ABS_WORKOUT;
import static seedu.zerotoone.testutil.workout.WorkoutCommandTestUtil.VALID_WORKOUT_NAME_ARMS_WORKOUT;
import static seedu.zerotoone.testutil.workout.WorkoutCommandTestUtil.VALID_WORKOUT_NAME_LEGS_WORKOUT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.zerotoone.model.log.LogList;
import seedu.zerotoone.model.session.CompletedWorkout;

/**
 * The type Typical logs.
 */
public class TypicalLogs {
    /**
     * The constant ARMS_WORKOUT_BUILDER.
     */
    public static final LogBuilder ARMS_WORKOUT_BUILDER = new LogBuilder()
            .withWorkoutName(VALID_WORKOUT_NAME_ARMS_WORKOUT)
            .withWorkoutExercise(TypicalCompletedExercises.BENCH_PRESS)
            .withWorkoutExercise(TypicalCompletedExercises.DEADLIFT);
    /**
     * The constant ARMS_WORKOUT.
     */
    public static final CompletedWorkout ARMS_WORKOUT = ARMS_WORKOUT_BUILDER.build();

    /**
     * The constant LEGS_WORKOUT_BUILDER.
     */
    public static final LogBuilder LEGS_WORKOUT_BUILDER = new LogBuilder()
            .withWorkoutName(VALID_WORKOUT_NAME_LEGS_WORKOUT);
    /**
     * The constant LEGS_WORKOUT.
     */
    public static final CompletedWorkout LEGS_WORKOUT = LEGS_WORKOUT_BUILDER.build();

    /**
     * The constant ABS_WORKOUT_BUILDER.
     */
    public static final LogBuilder ABS_WORKOUT_BUILDER = new LogBuilder()
            .withWorkoutName(VALID_WORKOUT_NAME_ABS_WORKOUT);

    /**
     * The constant ABS_WORKOUT.
     */
    public static final CompletedWorkout ABS_WORKOUT = ABS_WORKOUT_BUILDER.build();

    private TypicalLogs() {} // prevents instantiation

    /**
     * Returns an {@code LogList} with all the typical completed workouts.
     *
     * @return the typical log list
     */
    public static LogList getTypicalLogList() {
        LogList el = new LogList();
        List<LogBuilder> typicalLogBuilders = new ArrayList<>(
                Arrays.asList(ARMS_WORKOUT_BUILDER, LEGS_WORKOUT_BUILDER, ABS_WORKOUT_BUILDER));

        for (LogBuilder logBuilder : typicalLogBuilders) {
            el.addCompletedWorkout(logBuilder.build());
        }
        return el;
    }
}
