package seedu.zerotoone.model.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zerotoone.testutil.Assert.assertThrows;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_NUM_REPS_BENCH_PRESS;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_WEIGHT_BENCH_PRESS;
import static seedu.zerotoone.testutil.exercise.TypicalExercises.BENCH_PRESS;
import static seedu.zerotoone.testutil.exercise.TypicalExercises.getTypicalExerciseList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.zerotoone.model.exercise.exceptions.DuplicateExerciseException;
import seedu.zerotoone.testutil.exercise.ExerciseBuilder;

public class ExerciseListTest {
    private final ExerciseList exerciseList = new ExerciseList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), exerciseList.getExerciseList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> exerciseList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyExerciseList_replacesData() {
        ExerciseList newData = getTypicalExerciseList();
        exerciseList.resetData(newData);
        assertEquals(newData, exerciseList);
    }

    @Test
    public void resetData_withDuplicateExercises_throwsDuplicateExerciseException() {
        // Two exercises with the same identity fields
        Exercise editedBenchPress = new ExerciseBuilder(BENCH_PRESS)
                .withExerciseSet(VALID_WEIGHT_BENCH_PRESS, VALID_NUM_REPS_BENCH_PRESS).build();
        List<Exercise> newExercises = Arrays.asList(BENCH_PRESS, editedBenchPress);
        ExerciseListStub newData = new ExerciseListStub(newExercises);

        assertThrows(DuplicateExerciseException.class, () -> exerciseList.resetData(newData));
    }

    @Test
    public void hasExercise_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> exerciseList.hasExercise(null));
    }

    @Test
    public void hasExercise_exerciseNotInExerciseList_returnsFalse() {
        assertFalse(exerciseList.hasExercise(BENCH_PRESS));
    }

    @Test
    public void hasExercise_exerciseInExerciseList_returnsTrue() {
        exerciseList.addExercise(BENCH_PRESS);
        assertTrue(exerciseList.hasExercise(BENCH_PRESS));
    }

    @Test
    public void hasExercise_exerciseWithSameIdentityFieldsInExerciseList_returnsTrue() {
        exerciseList.addExercise(BENCH_PRESS);
        Exercise editedBenchPress = new ExerciseBuilder(BENCH_PRESS)
                .withExerciseSet(VALID_WEIGHT_BENCH_PRESS, VALID_NUM_REPS_BENCH_PRESS).build();
        assertTrue(exerciseList.hasExercise(editedBenchPress));
    }

    @Test
    public void getExerciseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> exerciseList.getExerciseList().remove(0));
    }

    @Test
    public void equals() {
        ExerciseList emptyList = new ExerciseList();
        ExerciseList filledList = getTypicalExerciseList();

        // same obj
        assertTrue(emptyList.equals(emptyList));

        // same value
        ExerciseList emptyListCopy = new ExerciseList();
        assertTrue(emptyList.equals(emptyListCopy));

        // null value
        assertFalse(emptyList.equals(null));

        // invalid type
        assertFalse(emptyList.equals(1));

        // different value inside list -> returns false
        assertFalse(emptyList.equals(filledList));
    }

    /**
     * A stub ReadOnlyExerciseList whose exercises list can violate interface constraints.
     */
    private static class ExerciseListStub implements ReadOnlyExerciseList {
        private final ObservableList<Exercise> exercises = FXCollections.observableArrayList();

        ExerciseListStub(Collection<Exercise> exercises) {
            this.exercises.setAll(exercises);
        }

        @Override
        public ObservableList<Exercise> getExerciseList() {
            return exercises;
        }
    }
}
