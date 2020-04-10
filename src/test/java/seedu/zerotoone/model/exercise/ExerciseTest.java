package seedu.zerotoone.model.exercise;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_EXERCISE_NAME_DEADLIFT;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_NUM_REPS_DEADLIFT;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_WEIGHT_DEADLIFT;
import static seedu.zerotoone.testutil.exercise.TypicalExercises.BENCH_PRESS;
import static seedu.zerotoone.testutil.exercise.TypicalExercises.DEADLIFT;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.testutil.exercise.ExerciseBuilder;

public class ExerciseTest {
    @Test
    public void constructor_nullExerciseName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new Exercise(null, new ArrayList<>()));
    }

    @Test
    public void constructor_nullExerciseSets_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new Exercise(new ExerciseName("Bench Press"), null));
    }

    @Test
    public void getExerciseSets_modifyList_throwsUnsupportedOperationException() {
        Exercise exercise = new ExerciseBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> exercise.getExerciseSets().remove(0));
    }

    @Test
    public void isSameExercise() {
        // same object -> returns true
        assertTrue(BENCH_PRESS.isSameExercise(BENCH_PRESS));

        // null -> returns false
        assertFalse(BENCH_PRESS.isSameExercise(null));

        // same name, same attributes -> returns true
        Exercise editedBenchPress = new ExerciseBuilder(BENCH_PRESS).build();
        assertTrue(BENCH_PRESS.isSameExercise(editedBenchPress));

        // different name -> returns false
        editedBenchPress = new ExerciseBuilder(BENCH_PRESS)
                .withExerciseName(VALID_EXERCISE_NAME_DEADLIFT)
                .build();
        assertFalse(BENCH_PRESS.isSameExercise(editedBenchPress));

        // different exerciseSet -> returns true
        editedBenchPress = new ExerciseBuilder(BENCH_PRESS)
                .withExerciseSet(VALID_WEIGHT_DEADLIFT, VALID_NUM_REPS_DEADLIFT)
                .build();
        assertTrue(BENCH_PRESS.isSameExercise(editedBenchPress));

        // different name, different exerciseSet -> returns false
        editedBenchPress = new ExerciseBuilder(BENCH_PRESS)
                .withExerciseName(VALID_EXERCISE_NAME_DEADLIFT)
                .withExerciseSet(VALID_WEIGHT_DEADLIFT, VALID_NUM_REPS_DEADLIFT)
                .build();
        assertFalse(BENCH_PRESS.isSameExercise(editedBenchPress));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Exercise benchPressCopy = new ExerciseBuilder(BENCH_PRESS).build();
        assertTrue(BENCH_PRESS.equals(benchPressCopy));

        // same object -> returns true
        assertTrue(BENCH_PRESS.equals(BENCH_PRESS));

        // null -> returns false
        assertFalse(BENCH_PRESS.equals(null));

        // different type -> returns false
        assertFalse(BENCH_PRESS.equals(5));

        // different exercise -> returns false
        assertFalse(BENCH_PRESS.equals(DEADLIFT));

        // different name -> returns false
        Exercise editedBenchPress = new ExerciseBuilder(BENCH_PRESS)
                .withExerciseName(VALID_EXERCISE_NAME_DEADLIFT).build();
        assertFalse(BENCH_PRESS.equals(editedBenchPress));

        // different exerciseSet -> returns false
        editedBenchPress = new ExerciseBuilder(BENCH_PRESS)
                .withExerciseSet(VALID_WEIGHT_DEADLIFT, VALID_NUM_REPS_DEADLIFT).build();
        assertFalse(BENCH_PRESS.equals(editedBenchPress));
    }
}
