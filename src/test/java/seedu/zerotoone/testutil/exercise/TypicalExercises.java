package seedu.zerotoone.testutil.exercise;

import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_EXERCISE_NAME_BENCH_PRESS;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_EXERCISE_NAME_DEADLIFT;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_EXERCISE_NAME_OVERHEAD_PRESS;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_NUM_REPS_BENCH_PRESS;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_NUM_REPS_DEADLIFT;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_NUM_REPS_OVERHEAD_PRESS;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_WEIGHT_BENCH_PRESS;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_WEIGHT_DEADLIFT;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_WEIGHT_OVERHEAD_PRESS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.zerotoone.model.exercise.Exercise;
import seedu.zerotoone.model.exercise.ExerciseList;

/**
 * A utility class containing a list of {@code Exercise} objects to be used in tests.
 */
public class TypicalExercises {
    // Manually added - Exercise's details found in {@code CommandTestUtil}
    public static final ExerciseBuilder BENCH_PRESS_BUILDER = new ExerciseBuilder()
            .withExerciseName(VALID_EXERCISE_NAME_BENCH_PRESS)
            .withExerciseSet(VALID_WEIGHT_BENCH_PRESS, VALID_NUM_REPS_BENCH_PRESS);
    public static final Exercise BENCH_PRESS = BENCH_PRESS_BUILDER.build();

    public static final ExerciseBuilder DEADLIFT_BUILDER = new ExerciseBuilder()
            .withExerciseName(VALID_EXERCISE_NAME_DEADLIFT)
            .withExerciseSet(VALID_WEIGHT_DEADLIFT, VALID_NUM_REPS_DEADLIFT);
    public static final Exercise DEADLIFT = DEADLIFT_BUILDER.build();

    public static final ExerciseBuilder OVERHEAD_PRESS_BUILDER = new ExerciseBuilder()
            .withExerciseName(VALID_EXERCISE_NAME_OVERHEAD_PRESS)
            .withExerciseSet(VALID_WEIGHT_OVERHEAD_PRESS, VALID_NUM_REPS_OVERHEAD_PRESS);
    public static final Exercise OVERHEAD_PRESS = OVERHEAD_PRESS_BUILDER.build();


    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalExercises() {} // prevents instantiation

    /**
     * Returns an {@code ExerciseList} with all the typical exercises.
     */
    public static ExerciseList getTypicalExerciseList() {
        ExerciseList el = new ExerciseList();
        List<ExerciseBuilder> typicalExerciseBuilders = new ArrayList<>(
                Arrays.asList(BENCH_PRESS_BUILDER, DEADLIFT_BUILDER));

        for (ExerciseBuilder exerciseBuilder : typicalExerciseBuilders) {
            el.addExercise(exerciseBuilder.build());
        }
        return el;
    }
}
