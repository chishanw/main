package seedu.zerotoone.logic.commands.exercise.set;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zerotoone.testutil.Assert.assertThrows;
import static seedu.zerotoone.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;
import static seedu.zerotoone.testutil.TypicalIndexes.INDEX_THIRD_OBJECT;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_EXERCISE_NAME_BENCH_PRESS;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_NUM_REPS_BENCH_PRESS;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_NUM_REPS_DEADLIFT;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_WEIGHT_BENCH_PRESS;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_WEIGHT_DEADLIFT;
import static seedu.zerotoone.testutil.exercise.TypicalExercises.BENCH_PRESS;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.zerotoone.commons.core.Messages;
import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.model.exercise.Exercise;
import seedu.zerotoone.model.exercise.NumReps;
import seedu.zerotoone.model.exercise.Weight;
import seedu.zerotoone.testutil.ModelStub;
import seedu.zerotoone.testutil.exercise.ExerciseBuilder;


public class AddCommandTest {
    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null,
                new NumReps(VALID_NUM_REPS_BENCH_PRESS), new Weight(VALID_WEIGHT_BENCH_PRESS)));
    }

    @Test
    public void constructor_nullNumReps_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(INDEX_FIRST_OBJECT,
                null, new Weight(VALID_WEIGHT_BENCH_PRESS)));
    }

    @Test
    public void constructor_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(INDEX_FIRST_OBJECT,
                new NumReps(VALID_NUM_REPS_BENCH_PRESS), null));
    }

    @Test
    public void execute_isInSession_throwsCommandException() {
        AddCommand addCommand = new AddCommand(INDEX_FIRST_OBJECT,
                new NumReps(VALID_NUM_REPS_BENCH_PRESS), new Weight(VALID_WEIGHT_BENCH_PRESS));
        ModelStub modelStub = new ModelStubInSession();
        assertThrows(CommandException.class, Command.MESSAGE_SESSION_STARTED, () ->
                addCommand.execute(modelStub));
    }

    @Test
    public void execute_indexOutOfBounds_throwsCommandException() {
        ModelStub modelStub = new ModelStubWithExercise(BENCH_PRESS);
        AddCommand addCommand = new AddCommand(INDEX_THIRD_OBJECT,
                new NumReps(VALID_NUM_REPS_BENCH_PRESS), new Weight(VALID_WEIGHT_BENCH_PRESS));
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_INDEX, () ->
                addCommand.execute(modelStub));
    }

    @Test
    public void execute_exerciseFound_addSetSuccessful() throws Exception {
        Exercise validExercise = new ExerciseBuilder()
                .withExerciseName(VALID_EXERCISE_NAME_BENCH_PRESS).build();
        ModelStub modelStub = new ModelStubWithExercise(validExercise);
        AddCommand addCommand = new AddCommand(INDEX_FIRST_OBJECT,
                new NumReps(VALID_NUM_REPS_BENCH_PRESS), new Weight(VALID_WEIGHT_BENCH_PRESS));

        Exercise expectedExercise = new ExerciseBuilder()
                .withExerciseName(VALID_EXERCISE_NAME_BENCH_PRESS)
                .withExerciseSet(VALID_WEIGHT_BENCH_PRESS, VALID_NUM_REPS_BENCH_PRESS)
                .build();
        String expectedMessage = String.format(AddCommand.MESSAGE_EDIT_EXERCISE_SUCCESS, expectedExercise);
        assertEquals(expectedMessage, addCommand.execute(modelStub).getFeedbackToUser());
        assertEquals(modelStub.getFilteredExerciseList().get(0).getExerciseSets().size(), 1);
    }

    @Test
    public void equals() {
        AddCommand addSetOneCommand = new AddCommand(INDEX_FIRST_OBJECT,
                new NumReps(VALID_NUM_REPS_BENCH_PRESS), new Weight(VALID_WEIGHT_BENCH_PRESS));
        AddCommand addSetTwoCommand = new AddCommand(INDEX_FIRST_OBJECT,
                new NumReps(VALID_NUM_REPS_DEADLIFT), new Weight(VALID_WEIGHT_DEADLIFT));

        // same object -> returns true
        assertTrue(addSetOneCommand.equals(addSetOneCommand));

        // same values -> returns true
        AddCommand addSetOneCommandCopy = new AddCommand(INDEX_FIRST_OBJECT,
                new NumReps(VALID_NUM_REPS_BENCH_PRESS), new Weight(VALID_WEIGHT_BENCH_PRESS));
        assertTrue(addSetOneCommand.equals(addSetOneCommandCopy));

        // different types -> returns false
        assertFalse(addSetOneCommand.equals(1));

        // null -> returns false
        assertFalse(addSetOneCommand.equals(null));

        // different weight and num reps -> returns false
        assertFalse(addSetOneCommand.equals(addSetTwoCommand));
    }

    /**
     * A Model stub that contains a single exercise.
     */
    private class ModelStubWithExercise extends ModelStub {
        private List<Exercise> exercises = new ArrayList<>();

        ModelStubWithExercise(Exercise exercise) {
            requireNonNull(exercise);
            this.exercises.add(exercise);
        }

        @Override
        public boolean isInSession() {
            return false;
        }

        @Override
        public void setExercise(Exercise target, Exercise editedExercise) {
            this.exercises.remove(target);
            this.exercises.add(editedExercise);
        }

        @Override
        public void updateFilteredExerciseList(Predicate<Exercise> predicate) {}

        @Override
        public ObservableList<Exercise> getFilteredExerciseList() {
            return FXCollections.observableArrayList(exercises);
        }
    }

    /**
     * A Model stub that is in session.
     */
    private class ModelStubInSession extends ModelStub {
        @Override
        public boolean isInSession() {
            return true;
        }
    }
}
