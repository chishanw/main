package seedu.zerotoone.logic.commands.exercise.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zerotoone.testutil.Assert.assertThrows;
import static seedu.zerotoone.testutil.CommandTestUtil.assertCommandSuccess;
import static seedu.zerotoone.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;
import static seedu.zerotoone.testutil.TypicalIndexes.INDEX_SECOND_OBJECT;
import static seedu.zerotoone.testutil.TypicalIndexes.INDEX_THIRD_OBJECT;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_NUM_REPS_BENCH_PRESS;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_NUM_REPS_DEADLIFT;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_WEIGHT_BENCH_PRESS;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_WEIGHT_DEADLIFT;
import static seedu.zerotoone.testutil.exercise.TypicalExercises.getTypicalExerciseList;
import static seedu.zerotoone.testutil.workout.TypicalWorkouts.getTypicalWorkoutList;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.commons.core.Messages;
import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.ModelManager;
import seedu.zerotoone.model.exercise.Exercise;
import seedu.zerotoone.model.exercise.ExerciseSet;
import seedu.zerotoone.model.exercise.NumReps;
import seedu.zerotoone.model.exercise.Weight;
import seedu.zerotoone.model.log.LogList;
import seedu.zerotoone.model.schedule.ScheduleList;
import seedu.zerotoone.model.userprefs.UserPrefs;
import seedu.zerotoone.testutil.ModelStub;


public class EditCommandTest {
    private Model model = new ModelManager(new UserPrefs(),
            getTypicalExerciseList(),
            getTypicalWorkoutList(),
            new ScheduleList(),
            new LogList());

    private Model expectedModel = new ModelManager(new UserPrefs(),
            getTypicalExerciseList(),
            getTypicalWorkoutList(),
            new ScheduleList(),
            new LogList());

    @Test
    public void constructor_nullExerciseId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditCommand(null, INDEX_FIRST_OBJECT, new NumReps(VALID_NUM_REPS_BENCH_PRESS),
                new Weight(VALID_WEIGHT_BENCH_PRESS)));
    }

    @Test
    public void constructor_nullSetId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditCommand(INDEX_FIRST_OBJECT, null, new NumReps(VALID_NUM_REPS_BENCH_PRESS),
                new Weight(VALID_WEIGHT_BENCH_PRESS)));
    }

    @Test
    public void constructor_nullNumReps_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditCommand(INDEX_FIRST_OBJECT, INDEX_FIRST_OBJECT, null,
                new Weight(VALID_WEIGHT_BENCH_PRESS)));
    }

    @Test
    public void constructor_nullWeight_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditCommand(INDEX_FIRST_OBJECT, INDEX_FIRST_OBJECT,
                new NumReps(VALID_NUM_REPS_BENCH_PRESS), null));
    }

    @Test
    public void execute_isInSession_throwsCommandException() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_OBJECT, INDEX_FIRST_OBJECT,
                new NumReps(VALID_NUM_REPS_BENCH_PRESS), new Weight(VALID_WEIGHT_BENCH_PRESS));
        ModelStub modelStub = new ModelStubInSession();
        assertThrows(CommandException.class, Command.MESSAGE_SESSION_STARTED, () ->
                editCommand.execute(modelStub));
    }

    @Test
    public void execute_invalidExerciseId_throwsCommandException() {
        EditCommand editCommand = new EditCommand(INDEX_THIRD_OBJECT, INDEX_FIRST_OBJECT,
                new NumReps(VALID_NUM_REPS_BENCH_PRESS), new Weight(VALID_WEIGHT_BENCH_PRESS));
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_INDEX, () ->
                editCommand.execute(model));
    }

    @Test
    public void execute_invalidSetId_throwsCommandException() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_OBJECT, INDEX_THIRD_OBJECT,
                new NumReps(VALID_NUM_REPS_BENCH_PRESS), new Weight(VALID_WEIGHT_BENCH_PRESS));
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_INDEX, () ->
                editCommand.execute(model));
    }

    @Test
    public void execute_exerciseAndSetFound_editSetSuccessful() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_OBJECT, INDEX_FIRST_OBJECT,
                new NumReps(VALID_NUM_REPS_BENCH_PRESS), new Weight(VALID_WEIGHT_BENCH_PRESS));

        Exercise exerciseToBeEdited = expectedModel.getFilteredExerciseList().get(0);
        List<ExerciseSet> expectedExerciseSets = new ArrayList<>(exerciseToBeEdited.getExerciseSets());
        expectedExerciseSets.remove(0);
        expectedExerciseSets.add(0, new ExerciseSet(new Weight(VALID_WEIGHT_BENCH_PRESS),
                new NumReps(VALID_NUM_REPS_BENCH_PRESS)));
        Exercise expectedExercise = new Exercise(exerciseToBeEdited.getExerciseName(), expectedExerciseSets);
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EXERCISE_SUCCESS,
                expectedExercise.getExerciseName());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
        assertEquals(model.getFilteredExerciseList().get(0), expectedExercise);
    }

    @Test
    public void equals() {
        EditCommand editCommandOneOne = new EditCommand(INDEX_FIRST_OBJECT, INDEX_FIRST_OBJECT,
                new NumReps(VALID_NUM_REPS_BENCH_PRESS), new Weight(VALID_WEIGHT_BENCH_PRESS));
        EditCommand editCommandOneTwo = new EditCommand(INDEX_FIRST_OBJECT, INDEX_SECOND_OBJECT,
                new NumReps(VALID_NUM_REPS_BENCH_PRESS), new Weight(VALID_WEIGHT_BENCH_PRESS));
        EditCommand editCommandTwoOne = new EditCommand(INDEX_SECOND_OBJECT, INDEX_FIRST_OBJECT,
                new NumReps(VALID_NUM_REPS_BENCH_PRESS), new Weight(VALID_WEIGHT_BENCH_PRESS));
        EditCommand editCommandOneOneNumReps = new EditCommand(INDEX_FIRST_OBJECT, INDEX_FIRST_OBJECT,
                new NumReps(VALID_NUM_REPS_DEADLIFT), new Weight(VALID_WEIGHT_BENCH_PRESS));
        EditCommand editCommandOneOneWeight = new EditCommand(INDEX_FIRST_OBJECT, INDEX_FIRST_OBJECT,
                new NumReps(VALID_NUM_REPS_BENCH_PRESS), new Weight(VALID_WEIGHT_DEADLIFT));

        // same object -> returns true
        assertTrue(editCommandOneOne.equals(editCommandOneOne));

        // same values -> returns true
        EditCommand editCommandOneOneCopy = new EditCommand(INDEX_FIRST_OBJECT, INDEX_FIRST_OBJECT,
                new NumReps(VALID_NUM_REPS_BENCH_PRESS), new Weight(VALID_WEIGHT_BENCH_PRESS));
        assertTrue(editCommandOneOne.equals(editCommandOneOneCopy));

        // different types -> returns false
        assertFalse(editCommandOneOne.equals(1));

        // null -> returns false
        assertFalse(editCommandOneOne.equals(null));

        // different exercise id -> returns false
        assertFalse(editCommandOneOne.equals(editCommandTwoOne));

        // different set id -> returns false
        assertFalse(editCommandOneOne.equals(editCommandOneTwo));

        // different num reps -> returns false
        assertFalse(editCommandOneOne.equals(editCommandOneOneNumReps));

        // different weight -> returns false
        assertFalse(editCommandOneOne.equals(editCommandOneOneWeight));
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
