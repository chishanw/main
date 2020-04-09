package seedu.zerotoone.logic.commands.workout;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zerotoone.logic.commands.workout.FindCommand.MESSAGE_WORKOUTS_LISTED_OVERVIEW;
import static seedu.zerotoone.testutil.Assert.assertThrows;
import static seedu.zerotoone.testutil.CommandTestUtil.assertCommandSuccess;
import static seedu.zerotoone.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;
import static seedu.zerotoone.testutil.exercise.TypicalExercises.getTypicalExerciseList;
import static seedu.zerotoone.testutil.workout.TypicalWorkouts.getTypicalWorkoutList;
import static seedu.zerotoone.testutil.workout.WorkoutCommandTestUtil.VALID_WORKOUT_NAME_ABS_WORKOUT;
import static seedu.zerotoone.testutil.workout.WorkoutCommandTestUtil.VALID_WORKOUT_NAME_ARMS_WORKOUT;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.logic.commands.CommandResult;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.ModelManager;
import seedu.zerotoone.model.log.LogList;
import seedu.zerotoone.model.schedule.ScheduleList;
import seedu.zerotoone.model.userprefs.UserPrefs;
import seedu.zerotoone.model.workout.PredicateFilterWorkoutName;
import seedu.zerotoone.model.workout.Workout;
import seedu.zerotoone.model.workout.WorkoutName;

public class FindCommandTest {
    private Model model = new ModelManager(new UserPrefs(),
            getTypicalExerciseList(),
            getTypicalWorkoutList(),
            new ScheduleList(),
            new LogList());

    @Test
    public void constructor_nullWorkoutName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FindCommand(null));
    }

    @Test
    public void execute_validWorkoutName_success() {
        Workout workout = model.getFilteredWorkoutList().get(INDEX_FIRST_OBJECT.getZeroBased());
        WorkoutName workoutName = workout.getWorkoutName();
        FindCommand findCommand = new FindCommand(workoutName);

        ModelManager expectedModel = new ModelManager(new UserPrefs(),
            model.getExerciseList(),
            model.getWorkoutList(),
            model.getScheduleList(),
            model.getLogList());

        expectedModel.updateFilteredWorkoutList(new PredicateFilterWorkoutName(workoutName.fullName));
        CommandResult expectedResult = new CommandResult(
                String.format(MESSAGE_WORKOUTS_LISTED_OVERVIEW, expectedModel.getFilteredWorkoutList().size()));

        assertCommandSuccess(findCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_inSession_throwsCommandException() {
        Workout workoutToStart = model.getFilteredWorkoutList().get(INDEX_FIRST_OBJECT.getZeroBased());
        LocalDateTime currentDateTime = LocalDateTime.now();
        model.startSession(workoutToStart, currentDateTime);
        assertThrows(CommandException.class, () -> new FindCommand(workoutToStart.getWorkoutName()).execute(model));
    }

    @Test
    public void equals() {
        FindCommand findCommand = new FindCommand(new WorkoutName(VALID_WORKOUT_NAME_ARMS_WORKOUT));
        FindCommand otherFindCommand = new FindCommand(new WorkoutName(VALID_WORKOUT_NAME_ABS_WORKOUT));

        // same object -> returns true
        assertTrue(findCommand.equals(findCommand));

        // same values -> returns true
        FindCommand findCommandCopy = new FindCommand(new WorkoutName(VALID_WORKOUT_NAME_ARMS_WORKOUT));
        assertTrue(findCommand.equals(findCommandCopy));

        // different types -> returns false
        assertFalse(findCommand.equals(1));

        // null -> returns false
        assertFalse(findCommand.equals(null));

        // different workout -> returns false
        assertFalse(findCommand.equals(otherFindCommand));
    }
}
