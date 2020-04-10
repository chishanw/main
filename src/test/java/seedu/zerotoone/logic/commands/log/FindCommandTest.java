package seedu.zerotoone.logic.commands.log;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.zerotoone.testutil.Assert.assertThrows;
import static seedu.zerotoone.testutil.log.LogCommandTestUtil.VALID_WORKOUT_NAME_BENCH_PRESS;
import static seedu.zerotoone.testutil.log.LogCommandTestUtil.VALID_WORKOUT_NAME_DEADLIFT;
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

class FindCommandTest {

    private Model model = new ModelManager(new UserPrefs(),
        new ExerciseList(), new WorkoutList(),
        new ScheduleList(),
        getTypicalLogList());

    @Test
    public void execute_isInSession_throwsCommandException() {
        FindCommand findCommand = new FindCommand(Optional.empty(), Optional.empty(),
            Optional.of(new WorkoutName(VALID_WORKOUT_NAME_BENCH_PRESS)));
        ModelStub modelStub = new FindCommandTest.ModelStubInSession();
        assertThrows(CommandException.class, Command.MESSAGE_SESSION_STARTED, () ->
            findCommand.execute(modelStub));
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
            new FindCommand(null, null, null));
    }

    @Test
    public void constructor_emptyWorkoutName_success() {
        FindCommand findCommand = new FindCommand(Optional.of(LocalDateTime.now()), Optional.of(LocalDateTime.now()),
            Optional.empty());

        assertEquals(model.getLogList().getLogList().size(), model.getFilteredLogList().size());
    }

    @Test
    public void constructor_emptyStartTime_success() {
        FindCommand findCommand = new FindCommand(Optional.empty(), Optional.of(LocalDateTime.now()),
            Optional.of(new WorkoutName("Working Name")));
        assertEquals(model.getLogList().getLogList().size(), model.getFilteredLogList().size());
    }

    @Test
    public void constructor_emptyEndTime_success() {
        FindCommand findCommand = new FindCommand(Optional.of(LocalDateTime.now()), Optional.empty(),
            Optional.of(new WorkoutName("Working Name")));
        assertEquals(model.getLogList().getLogList().size(), model.getFilteredLogList().size());
    }

    @Test
    public void constructor_allEmpty_success() {
        FindCommand findCommand = new FindCommand(Optional.empty(), Optional.empty(),
            Optional.empty());
        assertEquals(model.getLogList().getLogList().size(), model.getFilteredLogList().size());
    }

    @Test
    public void equals() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusMinutes(1);
        LocalDateTime start2 = start.plusMinutes(2);
        LocalDateTime end2 = start.plusMinutes(3);

        FindCommand findBenchPressCommand = new FindCommand(Optional.of(start), Optional.of(end),
            Optional.of(new WorkoutName(VALID_WORKOUT_NAME_BENCH_PRESS)));
        FindCommand findDeadliftCommand = new FindCommand(Optional.of(start2), Optional.of(end2),
            Optional.of(new WorkoutName(VALID_WORKOUT_NAME_DEADLIFT)));

        // same object -> returns true
        assertEquals(findBenchPressCommand, findBenchPressCommand);

        // same values -> returns true
        FindCommand findBenchPressCommandCopy = new FindCommand(Optional.of(start), Optional.of(end),
            Optional.of(new WorkoutName(VALID_WORKOUT_NAME_BENCH_PRESS)));
        assertEquals(findBenchPressCommand, findBenchPressCommandCopy);

        // different types -> returns false
        assertNotEquals(1, findBenchPressCommand);

        // null -> returns false
        assertNotEquals(null, findBenchPressCommand);

        // different exercise -> returns false
        assertNotEquals(findBenchPressCommand, findDeadliftCommand);
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
