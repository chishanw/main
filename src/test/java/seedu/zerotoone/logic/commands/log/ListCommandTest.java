package seedu.zerotoone.logic.commands.log;

import static seedu.zerotoone.logic.commands.log.ListCommand.MESSAGE_SUCCESS;
import static seedu.zerotoone.model.Model.PREDICATE_SHOW_ALL_LOGS;
import static seedu.zerotoone.testutil.Assert.assertThrows;
import static seedu.zerotoone.testutil.CommandTestUtil.assertCommandSuccess;
import static seedu.zerotoone.testutil.log.LogCommandTestUtil.VALID_WORKOUT_NAME_BENCH_PRESS;
import static seedu.zerotoone.testutil.log.TypicalLogs.getTypicalLogList;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.CommandResult;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.ModelManager;
import seedu.zerotoone.model.exercise.ExerciseList;
import seedu.zerotoone.model.schedule.ScheduleList;
import seedu.zerotoone.model.userprefs.UserPrefs;
import seedu.zerotoone.model.workout.WorkoutList;
import seedu.zerotoone.model.workout.WorkoutName;
import seedu.zerotoone.testutil.ModelStub;

class ListCommandTest {

    private Model model = new ModelManager(new UserPrefs(),
        new ExerciseList(),
        new WorkoutList(),
        new ScheduleList(),
        getTypicalLogList());

    @Test
    public void execute_isInSession_throwsCommandException() {
        FindCommand findCommand = new FindCommand(Optional.empty(), Optional.empty(),
            Optional.of(new WorkoutName(VALID_WORKOUT_NAME_BENCH_PRESS)));
        ModelStub modelStub = new ListCommandTest.ModelStubInSession();
        assertThrows(CommandException.class, Command.MESSAGE_SESSION_STARTED, () ->
            findCommand.execute(modelStub));
    }


    @Test
    void execute() {
        ModelManager expectedModel = new ModelManager(new UserPrefs(),
            model.getExerciseList(),
            model.getWorkoutList(),
            model.getScheduleList(),
            model.getLogList());
        expectedModel.updateFilteredLogList(PREDICATE_SHOW_ALL_LOGS);
        seedu.zerotoone.logic.commands.log.ListCommand listCommand = new ListCommand();
        CommandResult expectedResult = new CommandResult(MESSAGE_SUCCESS);
        assertCommandSuccess(listCommand, model, expectedResult, expectedModel);
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
