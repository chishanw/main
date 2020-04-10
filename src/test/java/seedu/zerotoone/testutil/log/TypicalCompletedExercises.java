package seedu.zerotoone.testutil.log;

import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_EXERCISE_NAME_BENCH_PRESS;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_EXERCISE_NAME_DEADLIFT;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_EXERCISE_NAME_OVERHEAD_PRESS;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_NUM_REPS_BENCH_PRESS;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_NUM_REPS_DEADLIFT;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_NUM_REPS_OVERHEAD_PRESS;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_WEIGHT_BENCH_PRESS;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_WEIGHT_DEADLIFT;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_WEIGHT_OVERHEAD_PRESS;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import seedu.zerotoone.model.exercise.Exercise;
import seedu.zerotoone.model.session.CompletedExercise;
import seedu.zerotoone.model.session.CompletedSet;
import seedu.zerotoone.testutil.exercise.TypicalExercises;

/**
 * A utility class containing a list of {@code CompletedExercise} objects to be used in tests.
 */
public class TypicalCompletedExercises {
    public static final Long DEFAULT_EXERCISE_TIME_IN_MINUTES = 10L;

    public static final CompletedExerciseBuilder BENCH_PRESS_BUILDER = new CompletedExerciseBuilder()
        .withExerciseName(VALID_EXERCISE_NAME_BENCH_PRESS)
        .withExerciseSet(VALID_WEIGHT_BENCH_PRESS, VALID_NUM_REPS_BENCH_PRESS);


    public static final CompletedExerciseBuilder DEADLIFT_BUILDER = new CompletedExerciseBuilder()
        .withExerciseName(VALID_EXERCISE_NAME_DEADLIFT)
        .withExerciseSet(VALID_WEIGHT_DEADLIFT, VALID_NUM_REPS_DEADLIFT);


    public static final CompletedExerciseBuilder OVERHEAD_PRESS_BUILDER = new CompletedExerciseBuilder()
        .withExerciseName(VALID_EXERCISE_NAME_OVERHEAD_PRESS)
        .withExerciseSet(VALID_WEIGHT_OVERHEAD_PRESS, VALID_NUM_REPS_OVERHEAD_PRESS);

    public static final CompletedExercise OVERHEAD_PRESS = OVERHEAD_PRESS_BUILDER.build();

    public static final CompletedExercise BENCH_PRESS = BENCH_PRESS_BUILDER.build();

    public static final CompletedExercise DEADLIFT = DEADLIFT_BUILDER.build();

    private TypicalCompletedExercises() {}

    /**
     * Returns an {@code CompletedExerciseList} with all the typical exercises.
     */
    public static List<CompletedExercise> getTypicalCompletedExerciseList() {
        List<CompletedExercise> completedExercises = new ArrayList<>();
        Exercise benchPress = TypicalExercises.BENCH_PRESS;

        LocalDateTime now = LocalDateTime.now();

        List<CompletedSet> sets = benchPress.getExerciseSets().stream()
            .map(set -> new CompletedSet(set.weight, set.numReps, true)).collect(Collectors.toList());

        completedExercises.add(new CompletedExercise(benchPress.getExerciseName(), sets,
            now, now.plusMinutes(DEFAULT_EXERCISE_TIME_IN_MINUTES)));

        return completedExercises;
    }
}
