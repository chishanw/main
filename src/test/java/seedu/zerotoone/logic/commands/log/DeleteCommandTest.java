package seedu.zerotoone.logic.commands.log;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zerotoone.commons.util.DateUtil.getPrettyDateTimeString;
import static seedu.zerotoone.testutil.CommandTestUtil.assertCommandSuccess;
import static seedu.zerotoone.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;
import static seedu.zerotoone.testutil.TypicalIndexes.INDEX_SECOND_OBJECT;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.assertCommandFailure;
import static seedu.zerotoone.testutil.log.LogCommandTestUtil.showLogAtIndex;
import static seedu.zerotoone.testutil.log.TypicalLogs.getTypicalLogList;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.commons.core.Messages;
import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.ModelManager;
import seedu.zerotoone.model.exercise.ExerciseList;
import seedu.zerotoone.model.schedule.ScheduleList;
import seedu.zerotoone.model.session.CompletedWorkout;
import seedu.zerotoone.model.userprefs.UserPrefs;
import seedu.zerotoone.model.workout.WorkoutList;

class DeleteCommandTest {
    private Model model = new ModelManager(new UserPrefs(),
        new ExerciseList(),
        new WorkoutList(),
        new ScheduleList(),
        getTypicalLogList());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        CompletedWorkout completedWorkoutToDelete = model.getFilteredLogList().get(INDEX_FIRST_OBJECT.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_OBJECT);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_LOG_SUCCESS,
            completedWorkoutToDelete.getWorkoutName().toString(),
            getPrettyDateTimeString(completedWorkoutToDelete.getStartTime()));

        ModelManager expectedModel = new ModelManager(new UserPrefs(),
            model.getExerciseList(),
            model.getWorkoutList(),
            model.getScheduleList(),
            model.getLogList());

        expectedModel.deleteLog(INDEX_FIRST_OBJECT.getZeroBased());

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getLogList().getLogList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showLogAtIndex(model, INDEX_FIRST_OBJECT);

        Index outOfBoundIndex = INDEX_SECOND_OBJECT;
        // ensures that outOfBoundIndex is still in bounds of log list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getLogList().getLogList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_OBJECT);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_OBJECT);

        // same object -> returns true
        assertEquals(deleteFirstCommand, deleteFirstCommand);

        // different types -> returns false
        assertNotEquals(1, deleteFirstCommand);

        // null -> returns false
        assertNotEquals(null, deleteFirstCommand);

        // different exercise -> returns false
        assertNotEquals(deleteFirstCommand, deleteSecondCommand);
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoLog(Model model) {
        model.updateFilteredExerciseList(p -> false);

        assertTrue(model.getFilteredExerciseList().isEmpty());
    }
}
