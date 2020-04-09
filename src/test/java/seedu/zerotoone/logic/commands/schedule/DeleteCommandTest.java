package seedu.zerotoone.logic.commands.schedule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zerotoone.testutil.CommandTestUtil.assertCommandSuccess;
import static seedu.zerotoone.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;
import static seedu.zerotoone.testutil.TypicalIndexes.INDEX_SECOND_OBJECT;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.assertCommandFailure;
import static seedu.zerotoone.testutil.exercise.TypicalExercises.getTypicalExerciseList;
import static seedu.zerotoone.testutil.schedule.TypicalSchedules.getTypicalScheduleList;
import static seedu.zerotoone.testutil.workout.TypicalWorkouts.getTypicalWorkoutList;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.commons.core.Messages;
import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.ModelManager;
import seedu.zerotoone.model.log.LogList;
import seedu.zerotoone.model.schedule.ScheduledWorkout;
import seedu.zerotoone.model.userprefs.UserPrefs;

class DeleteCommandTest {

    private Model model = new ModelManager(new UserPrefs(),
            getTypicalExerciseList(),
            getTypicalWorkoutList(),
            getTypicalScheduleList(),
            new LogList());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        ScheduledWorkout scheduledWorkoutToDelete =
                model.getSortedScheduledWorkoutList().get(INDEX_FIRST_OBJECT.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_OBJECT);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_SCHEDULED_WORKOUT_SUCCESS,
                scheduledWorkoutToDelete.getScheduledWorkoutName());

        ModelManager expectedModel = new ModelManager(new UserPrefs(),
                model.getExerciseList(),
                model.getWorkoutList(),
                model.getScheduleList(),
                model.getLogList());

        expectedModel.deleteScheduledWorkout(scheduledWorkoutToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getSortedScheduledWorkoutList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_OBJECT);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_OBJECT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_OBJECT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different exercise -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
