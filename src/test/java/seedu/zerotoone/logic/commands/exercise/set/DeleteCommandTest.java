package seedu.zerotoone.logic.commands.exercise.set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zerotoone.testutil.Assert.assertThrows;
import static seedu.zerotoone.testutil.CommandTestUtil.assertCommandSuccess;
import static seedu.zerotoone.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;
import static seedu.zerotoone.testutil.TypicalIndexes.INDEX_SECOND_OBJECT;
import static seedu.zerotoone.testutil.TypicalIndexes.INDEX_THIRD_OBJECT;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_EXERCISE_NAME_BENCH_PRESS;
import static seedu.zerotoone.testutil.exercise.TypicalExercises.getTypicalExerciseList;
import static seedu.zerotoone.testutil.workout.TypicalWorkouts.getTypicalWorkoutList;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.commons.core.Messages;
import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.ModelManager;
import seedu.zerotoone.model.exercise.Exercise;
import seedu.zerotoone.model.exercise.ExerciseName;
import seedu.zerotoone.model.log.LogList;
import seedu.zerotoone.model.schedule.ScheduleList;
import seedu.zerotoone.model.userprefs.UserPrefs;
import seedu.zerotoone.testutil.ModelStub;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {
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
        assertThrows(NullPointerException.class, () -> new DeleteCommand(null, INDEX_FIRST_OBJECT));
    }

    @Test
    public void constructor_nullSetId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteCommand(INDEX_FIRST_OBJECT, null));
    }

    @Test
    public void execute_isInSession_throwsCommandException() {
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_OBJECT, INDEX_FIRST_OBJECT);
        ModelStub modelStub = new ModelStubInSession();
        assertThrows(CommandException.class, Command.MESSAGE_SESSION_STARTED, () ->
                deleteCommand.execute(modelStub));
    }

    @Test
    public void execute_invalidExerciseId_throwsCommandException() {
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_THIRD_OBJECT, INDEX_FIRST_OBJECT);
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_INDEX, () ->
                deleteCommand.execute(model));
    }

    @Test
    public void execute_invalidSetId_throwsCommandException() {
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_OBJECT, INDEX_THIRD_OBJECT);
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_INDEX, () ->
                deleteCommand.execute(model));
    }

    @Test
    public void execute_validParams_deleteSuccessful() {
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_OBJECT, INDEX_FIRST_OBJECT);

        Exercise exerciseToBeEdited = expectedModel.getFilteredExerciseList().get(0);
        Exercise expectedExercise = new Exercise(
                new ExerciseName(VALID_EXERCISE_NAME_BENCH_PRESS), new ArrayList<>());
        expectedModel.setExercise(exerciseToBeEdited, expectedExercise);
        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_EXERCISE_SET_SUCCESS, expectedExercise);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        DeleteCommand deleteCommandOneOne = new DeleteCommand(INDEX_FIRST_OBJECT, INDEX_FIRST_OBJECT);
        DeleteCommand deleteComamndOneTwo = new DeleteCommand(INDEX_FIRST_OBJECT, INDEX_SECOND_OBJECT);
        DeleteCommand deleteComamndTwoOne = new DeleteCommand(INDEX_SECOND_OBJECT, INDEX_FIRST_OBJECT);

        // same object -> returns true
        assertTrue(deleteCommandOneOne.equals(deleteCommandOneOne));

        // same values -> returns true
        DeleteCommand deleteCommandOneOneCopy = new DeleteCommand(INDEX_FIRST_OBJECT, INDEX_FIRST_OBJECT);
        assertTrue(deleteCommandOneOne.equals(deleteCommandOneOneCopy));

        // different types -> returns false
        assertFalse(deleteCommandOneOne.equals(1));

        // null -> returns false
        assertFalse(deleteCommandOneOne.equals(null));

        // different set id -> returns false
        assertFalse(deleteCommandOneOne.equals(deleteComamndOneTwo));

        // different exercise id -> returns false
        assertFalse(deleteCommandOneOne.equals(deleteComamndTwoOne));
    }

    private class ModelStubInSession extends ModelStub {
        @Override
        public boolean isInSession() {
            return true;
        }
    }
}
