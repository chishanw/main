package seedu.zerotoone.logic.commands.log;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static seedu.zerotoone.testutil.Assert.assertThrows;
import static seedu.zerotoone.testutil.CommandTestUtil.assertCommandSuccess;
import static seedu.zerotoone.testutil.log.LogCommandTestUtil.VALID_WORKOUT_NAME_BENCH_PRESS;
import static seedu.zerotoone.testutil.log.TypicalLogs.getTypicalLogList;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.ModelManager;
import seedu.zerotoone.model.exercise.ExerciseList;
import seedu.zerotoone.model.schedule.ScheduleList;
import seedu.zerotoone.model.userprefs.UserPrefs;
import seedu.zerotoone.model.workout.WorkoutList;
import seedu.zerotoone.model.workout.WorkoutName;
import seedu.zerotoone.testutil.ModelStub;

class DisplayCommandTest {

    private Model model = new ModelManager(new UserPrefs(),
        new ExerciseList(), new WorkoutList(),
        new ScheduleList(),
        getTypicalLogList());

    @Test
    public void execute_isInSession_throwsCommandException() {
        FindCommand findCommand = new FindCommand(Optional.empty(), Optional.empty(),
            Optional.of(new WorkoutName(VALID_WORKOUT_NAME_BENCH_PRESS)));
        ModelStub modelStub = new DisplayCommandTest.ModelStubInSession();
        assertThrows(CommandException.class, Command.MESSAGE_SESSION_STARTED, () ->
            findCommand.execute(modelStub));
    }

    @Test
    void constructor_empty_success() {
        assertDoesNotThrow(() -> new DisplayCommand(Optional.empty(), Optional.empty()));
    }

    @Test
    void execute() {
        DisplayCommand displayCommand = new DisplayCommand(Optional.of(LocalDateTime.now()),
            Optional.of(LocalDateTime.now()));
        String expectedMessage = DisplayCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(displayCommand, model, expectedMessage, model);
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
