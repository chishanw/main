package seedu.zerotoone.logic.commands.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.zerotoone.testutil.Assert.assertThrows;
import static seedu.zerotoone.testutil.CommandTestUtil.assertCommandSuccess;
import static seedu.zerotoone.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.showExerciseAtIndex;
import static seedu.zerotoone.testutil.exercise.TypicalExercises.BENCH_PRESS;
import static seedu.zerotoone.testutil.exercise.TypicalExercises.DEADLIFT;
import static seedu.zerotoone.testutil.exercise.TypicalExercises.getTypicalExerciseList;
import static seedu.zerotoone.testutil.workout.TypicalWorkouts.getTypicalWorkoutList;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.ModelManager;
import seedu.zerotoone.model.log.LogList;
import seedu.zerotoone.model.schedule.ScheduleList;
import seedu.zerotoone.model.userprefs.UserPrefs;
import seedu.zerotoone.testutil.ModelStub;


public class ListCommandTest {
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
    public void execute_isInSession_throwsCommandException() {
        ListCommand listCommand = new ListCommand();
        ModelStub modelStub = new ModelStubInSession();
        assertThrows(CommandException.class, Command.MESSAGE_SESSION_STARTED, () ->
                listCommand.execute(modelStub));
    }

    @Test
    public void execute_listIsFiltered_allExercisesShown() {
        showExerciseAtIndex(model, INDEX_FIRST_OBJECT);

        ListCommand listCommand = new ListCommand();
        String expectedMessage = ListCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(listCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENCH_PRESS, DEADLIFT), model.getFilteredExerciseList());
    }

    @Test
    public void execute_listIsNotFiltered_allExercisesShown() {
        ListCommand listCommand = new ListCommand();
        String expectedMessage = ListCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(listCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENCH_PRESS, DEADLIFT), model.getFilteredExerciseList());
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
