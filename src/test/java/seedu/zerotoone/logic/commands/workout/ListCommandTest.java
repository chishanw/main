package seedu.zerotoone.logic.commands.workout;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zerotoone.logic.commands.workout.ListCommand.MESSAGE_SUCCESS;
import static seedu.zerotoone.model.workout.WorkoutModel.PREDICATE_SHOW_ALL_WORKOUTS;
import static seedu.zerotoone.testutil.Assert.assertThrows;
import static seedu.zerotoone.testutil.CommandTestUtil.assertCommandSuccess;
import static seedu.zerotoone.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;
import static seedu.zerotoone.testutil.exercise.TypicalExercises.getTypicalExerciseList;
import static seedu.zerotoone.testutil.workout.TypicalWorkouts.getTypicalWorkoutList;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.logic.commands.CommandResult;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.ModelManager;
import seedu.zerotoone.model.log.LogList;
import seedu.zerotoone.model.schedule.ScheduleList;
import seedu.zerotoone.model.userprefs.UserPrefs;
import seedu.zerotoone.model.workout.Workout;

public class ListCommandTest {
    private Model model = new ModelManager(new UserPrefs(),
            getTypicalExerciseList(),
            getTypicalWorkoutList(),
            new ScheduleList(),
            new LogList());

    @Test
    public void execute_inSession_throwsCommandException() {
        Workout workoutToStart = model.getFilteredWorkoutList().get(INDEX_FIRST_OBJECT.getZeroBased());
        LocalDateTime currentDateTime = LocalDateTime.now();
        model.startSession(workoutToStart, currentDateTime);
        assertThrows(CommandException.class, () -> new FindCommand(workoutToStart.getWorkoutName()).execute(model));
    }

    @Test
    public void execute_validListCommand_success() {
        ModelManager expectedModel = new ModelManager(new UserPrefs(),
                model.getExerciseList(),
                model.getWorkoutList(),
                model.getScheduleList(),
                model.getLogList());
        expectedModel.updateFilteredWorkoutList(PREDICATE_SHOW_ALL_WORKOUTS);
        ListCommand listCommand = new ListCommand();
        CommandResult expectedResult = new CommandResult(MESSAGE_SUCCESS);
        assertCommandSuccess(listCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void equals() {
        ListCommand listCommand = new ListCommand();

        // same object -> returns true
        assertTrue(listCommand.equals(listCommand));

        // same values -> returns true
        ListCommand listCommandCopy = new ListCommand();
        assertTrue(listCommand.equals(listCommandCopy));

        // different types -> returns false
        assertFalse(listCommand.equals(1));

        // null -> returns false
        assertFalse(listCommand.equals(null));
    }
}
