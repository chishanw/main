package seedu.zerotoone.logic.commands.exercise;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zerotoone.testutil.Assert.assertThrows;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_EXERCISE_NAME_BENCH_PRESS;
import static seedu.zerotoone.testutil.exercise.TypicalExercises.BENCH_PRESS;
import static seedu.zerotoone.testutil.exercise.TypicalExercises.DEADLIFT;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.logic.commands.CommandResult;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.model.exercise.Exercise;
import seedu.zerotoone.model.exercise.ExerciseList;
import seedu.zerotoone.model.exercise.ExerciseName;
import seedu.zerotoone.model.exercise.ReadOnlyExerciseList;
import seedu.zerotoone.testutil.ModelStub;
import seedu.zerotoone.testutil.exercise.ExerciseBuilder;


public class CreateCommandTest {

    @Test
    public void constructor_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateCommand(null));
    }

    @Test
    public void execute_exerciseAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingExerciseAdded modelStub = new ModelStubAcceptingExerciseAdded();
        CommandResult commandResult = new CreateCommand(new ExerciseName(VALID_EXERCISE_NAME_BENCH_PRESS))
                .execute(modelStub);
        Exercise exerciseBenchPress = new ExerciseBuilder().withExerciseName(VALID_EXERCISE_NAME_BENCH_PRESS).build();

        assertEquals(String.format(CreateCommand.MESSAGE_SUCCESS,
                VALID_EXERCISE_NAME_BENCH_PRESS), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(exerciseBenchPress), modelStub.exercisesAdded);
    }

    @Test
    public void execute_duplicateExercise_throwsCommandException() {
        Exercise validExercise = new ExerciseBuilder().build();
        CreateCommand createCommand = new CreateCommand(BENCH_PRESS.getExerciseName());
        ModelStub modelStub = new ModelStubWithExercise(validExercise);

        assertThrows(
                CommandException.class, CreateCommand.MESSAGE_DUPLICATE_EXERCISE, () ->
                createCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        CreateCommand addBenchPressCommand = new CreateCommand(BENCH_PRESS.getExerciseName());
        CreateCommand addDeadliftCommand = new CreateCommand(DEADLIFT.getExerciseName());

        // same object -> returns true
        assertTrue(addBenchPressCommand.equals(addBenchPressCommand));

        // same values -> returns true
        CreateCommand addBenchPressCommandCopy = new CreateCommand(BENCH_PRESS.getExerciseName());
        assertTrue(addBenchPressCommand.equals(addBenchPressCommandCopy));

        // different types -> returns false
        assertFalse(addBenchPressCommand.equals(1));

        // null -> returns false
        assertFalse(addBenchPressCommand.equals(null));

        // different exercise -> returns false
        assertFalse(addBenchPressCommand.equals(addDeadliftCommand));
    }

    /**
     * A Model stub that contains a single exercise.
     */
    private class ModelStubWithExercise extends ModelStub {
        private final Exercise exercise;

        ModelStubWithExercise(Exercise exercise) {
            requireNonNull(exercise);
            this.exercise = exercise;
        }

        @Override
        public boolean isInSession() {
            return false;
        }

        @Override
        public boolean hasExercise(Exercise exercise) {
            requireNonNull(exercise);
            return this.exercise.isSameExercise(exercise);
        }
    }

    /**
     * A Model stub that always accept the exercise being added.
     */
    private class ModelStubAcceptingExerciseAdded extends ModelStub {
        final ArrayList<Exercise> exercisesAdded = new ArrayList<>();

        @Override
        public boolean isInSession() {
            return false;
        }

        @Override
        public boolean hasExercise(Exercise exercise) {
            requireNonNull(exercise);
            return exercisesAdded.stream().anyMatch(exercise::isSameExercise);
        }

        @Override
        public void addExercise(Exercise exercise) {
            requireNonNull(exercise);
            exercisesAdded.add(exercise);
        }

        @Override
        public ReadOnlyExerciseList getExerciseList() {
            return new ExerciseList();
        }
    }

}
