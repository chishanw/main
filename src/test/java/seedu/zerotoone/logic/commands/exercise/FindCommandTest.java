package seedu.zerotoone.logic.commands.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zerotoone.testutil.Assert.assertThrows;
import static seedu.zerotoone.testutil.CommandTestUtil.assertCommandSuccess;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_EXERCISE_NAME_BENCH_PRESS;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_EXERCISE_NAME_DEADLIFT;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.VALID_EXERCISE_NAME_OVERHEAD_PRESS;
import static seedu.zerotoone.testutil.exercise.TypicalExercises.BENCH_PRESS;
import static seedu.zerotoone.testutil.exercise.TypicalExercises.getTypicalExerciseList;
import static seedu.zerotoone.testutil.workout.TypicalWorkouts.getTypicalWorkoutList;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.ModelManager;
import seedu.zerotoone.model.exercise.ExerciseName;
import seedu.zerotoone.model.exercise.PredicateFilterExerciseName;
import seedu.zerotoone.model.log.LogList;
import seedu.zerotoone.model.schedule.ScheduleList;
import seedu.zerotoone.model.userprefs.UserPrefs;
import seedu.zerotoone.testutil.ModelStub;


public class FindCommandTest {
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
    public void constructor_nullExerciseName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new FindCommand(null));
    }

    @Test
    public void execute_isInSession_throwsCommandException() {
        FindCommand findCommand = new FindCommand(
                new ExerciseName(VALID_EXERCISE_NAME_BENCH_PRESS));
        ModelStub modelStub = new ModelStubInSession();
        assertThrows(CommandException.class, Command.MESSAGE_SESSION_STARTED, () ->
                findCommand.execute(modelStub));
    }

    @Test
    public void execute_exerciseNotAdded_noExercisesFound() {
        FindCommand findCommand = new FindCommand(
                new ExerciseName(VALID_EXERCISE_NAME_OVERHEAD_PRESS));

        PredicateFilterExerciseName expectedPredicate = new PredicateFilterExerciseName(
                VALID_EXERCISE_NAME_OVERHEAD_PRESS);
        String expectedMessage = String.format(FindCommand.MESSAGE_EXERCISES_LISTED_OVERVIEW, 0);
        expectedModel.updateFilteredExerciseList(expectedPredicate);

        assertCommandSuccess(findCommand, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredExerciseList());
    }

    @Test
    public void execute_exerciseAdded_oneExerciseFound() {
        FindCommand findCommand = new FindCommand(
                new ExerciseName(VALID_EXERCISE_NAME_BENCH_PRESS));

        PredicateFilterExerciseName expectedPredicate = new PredicateFilterExerciseName(
                VALID_EXERCISE_NAME_BENCH_PRESS);
        String expectedMessage = String.format(FindCommand.MESSAGE_EXERCISES_LISTED_OVERVIEW, 1);
        expectedModel.updateFilteredExerciseList(expectedPredicate);

        assertCommandSuccess(findCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENCH_PRESS), model.getFilteredExerciseList());
    }

    @Test
    public void equals() {
        FindCommand findBenchPressCommand = new FindCommand(new ExerciseName(VALID_EXERCISE_NAME_BENCH_PRESS));
        FindCommand findDeadliftCommand = new FindCommand(new ExerciseName(VALID_EXERCISE_NAME_DEADLIFT));

        // same object -> returns true
        assertTrue(findBenchPressCommand.equals(findBenchPressCommand));

        // same values -> returns true
        FindCommand findBenchPressCommandCopy = new FindCommand(new ExerciseName(VALID_EXERCISE_NAME_BENCH_PRESS));
        assertTrue(findBenchPressCommand.equals(findBenchPressCommandCopy));

        // different types -> returns false
        assertFalse(findBenchPressCommand.equals(1));

        // null -> returns false
        assertFalse(findBenchPressCommand.equals(null));

        // different exercise -> returns false
        assertFalse(findBenchPressCommand.equals(findDeadliftCommand));
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
