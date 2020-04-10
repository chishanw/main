package seedu.zerotoone.logic.commands.exercise;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zerotoone.testutil.Assert.assertThrows;
import static seedu.zerotoone.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;
import static seedu.zerotoone.testutil.TypicalIndexes.INDEX_SECOND_OBJECT;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_EXERCISE_NAME_BENCH_PRESS;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_EXERCISE_NAME_DEADLIFT;
import static seedu.zerotoone.testutil.exercise.TypicalExercises.BENCH_PRESS;
import static seedu.zerotoone.testutil.exercise.TypicalExercises.DEADLIFT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.zerotoone.commons.core.Messages;
import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.CommandResult;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.model.exercise.Exercise;
import seedu.zerotoone.model.exercise.ExerciseName;
import seedu.zerotoone.testutil.ModelStub;
import seedu.zerotoone.testutil.exercise.ExerciseBuilder;


public class EditCommandTest {
    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditCommand(null, new ExerciseName(VALID_EXERCISE_NAME_BENCH_PRESS)));
    }

    @Test
    public void constructor_nullExerciseName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditCommand(INDEX_FIRST_OBJECT, null));
    }

    @Test
    public void execute_isInSession_throwsCommandException() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_OBJECT,
                new ExerciseName(VALID_EXERCISE_NAME_BENCH_PRESS));
        ModelStub modelStub = new ModelStubInSession();
        assertThrows(CommandException.class, Command.MESSAGE_SESSION_STARTED, () ->
                editCommand.execute(modelStub));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        EditCommand editCommand = new EditCommand(INDEX_SECOND_OBJECT,
                new ExerciseName(VALID_EXERCISE_NAME_BENCH_PRESS));
        Exercise validExercise = new ExerciseBuilder().build();
        ModelStub modelStub = new ModelStubOneExerciseEnableEditing(validExercise);

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_INDEX, () ->
                editCommand.execute(modelStub));
    }

    @Test
    public void execute_duplicateExercise_throwsCommandException() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_OBJECT,
                new ExerciseName(VALID_EXERCISE_NAME_BENCH_PRESS));
        Exercise validExercise = new ExerciseBuilder()
                .withExerciseName(VALID_EXERCISE_NAME_BENCH_PRESS)
                .build();
        ModelStub modelStub = new ModelStubOneExerciseEnableEditing(validExercise);
        assertThrows(CommandException.class, EditCommand.MESSAGE_DUPLICATE_EXERCISE, () ->
                editCommand.execute(modelStub));
    }

    @Test
    public void execute_exerciseAcceptedByModel_editSuccessful() throws Exception {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_OBJECT, new ExerciseName(VALID_EXERCISE_NAME_DEADLIFT));
        Exercise validExercise = new ExerciseBuilder()
                .withExerciseName(VALID_EXERCISE_NAME_BENCH_PRESS).build();
        ModelStubOneExerciseEnableEditing modelStub = new ModelStubOneExerciseEnableEditing(validExercise);

        CommandResult commandResult = editCommand.execute(modelStub);
        Exercise exerciseDeadlift = new ExerciseBuilder()
                .withExerciseName(VALID_EXERCISE_NAME_DEADLIFT).build();

        assertEquals(String.format(EditCommand.MESSAGE_EDIT_EXERCISE_SUCCESS,
                VALID_EXERCISE_NAME_DEADLIFT), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(exerciseDeadlift), modelStub.exercises);
    }

    @Test
    public void equals() {
        EditCommand editBenchPressCommand = new EditCommand(INDEX_FIRST_OBJECT, BENCH_PRESS.getExerciseName());
        EditCommand editDeadliftCommand = new EditCommand(INDEX_FIRST_OBJECT, DEADLIFT.getExerciseName());
        EditCommand editBenchPressCommandTwo = new EditCommand(INDEX_SECOND_OBJECT, BENCH_PRESS.getExerciseName());

        // same object -> returns true
        assertTrue(editBenchPressCommand.equals(editBenchPressCommand));

        // same values -> returns true
        EditCommand editBenchPressCommandCopy = new EditCommand(INDEX_FIRST_OBJECT, BENCH_PRESS.getExerciseName());
        assertTrue(editBenchPressCommand.equals(editBenchPressCommandCopy));

        // different types -> returns false
        assertFalse(editBenchPressCommand.equals(1));

        // null -> returns false
        assertFalse(editBenchPressCommand.equals(null));

        // different exercise -> returns false
        assertFalse(editBenchPressCommand.equals(editDeadliftCommand));

        // different index -> returns false
        assertFalse(editBenchPressCommand.equals(editBenchPressCommandTwo));
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

    /**
     * A Model stub that only contains one exercise and allows editing.
     */
    private class ModelStubOneExerciseEnableEditing extends ModelStub {
        public final List<Exercise> exercises = new ArrayList<>();

        ModelStubOneExerciseEnableEditing(Exercise exercise) {
            requireNonNull(exercise);
            this.exercises.add(exercise);
        }

        @Override
        public boolean isInSession() {
            return false;
        }

        @Override
        public boolean hasExercise(Exercise exercise) {
            requireNonNull(exercise);
            return exercises.stream().anyMatch(exercise::isSameExercise);
        }

        @Override
        public void setExercise(Exercise target, Exercise editedExercise) {
            requireNonNull(target);
            requireNonNull(editedExercise);
            exercises.remove(target);
            exercises.add(editedExercise);
        }

        @Override
        public ObservableList<Exercise> getFilteredExerciseList() {
            return FXCollections.observableArrayList(exercises);
        }

        @Override
        public void updateFilteredExerciseList(Predicate<Exercise> predicate) {}
    }

}
