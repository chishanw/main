package seedu.zerotoone.logic.commands.workout;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zerotoone.logic.commands.workout.EditCommand.MESSAGE_DUPLICATE_WORKOUT;
import static seedu.zerotoone.testutil.Assert.assertThrows;
import static seedu.zerotoone.testutil.CommandTestUtil.assertCommandSuccess;
import static seedu.zerotoone.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;
import static seedu.zerotoone.testutil.TypicalIndexes.INDEX_SECOND_OBJECT;
import static seedu.zerotoone.testutil.exercise.TypicalExercises.getTypicalExerciseList;
import static seedu.zerotoone.testutil.workout.TypicalWorkouts.ARMS_WORKOUT;
import static seedu.zerotoone.testutil.workout.TypicalWorkouts.LEGS_WORKOUT;
import static seedu.zerotoone.testutil.workout.TypicalWorkouts.getTypicalWorkoutList;
import static seedu.zerotoone.testutil.workout.WorkoutCommandTestUtil.assertCommandFailure;
import static seedu.zerotoone.testutil.workout.WorkoutCommandTestUtil.showWorkoutAtIndex;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.commons.core.Messages;
import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.ModelManager;
import seedu.zerotoone.model.log.LogList;
import seedu.zerotoone.model.schedule.ScheduleList;
import seedu.zerotoone.model.userprefs.UserPrefs;
import seedu.zerotoone.model.workout.Workout;
import seedu.zerotoone.model.workout.WorkoutName;
import seedu.zerotoone.testutil.workout.WorkoutBuilder;

public class EditCommandTest {
    private Model model = new ModelManager(new UserPrefs(),
            getTypicalExerciseList(),
            getTypicalWorkoutList(),
            new ScheduleList(),
            new LogList());

    @Test
    public void constructor_nullWorkout_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditCommand(null, null));
    }
    @Test
    public void execute_validIndexUnfilteredList_success() {
        Workout workoutToEdit = model.getFilteredWorkoutList().get(INDEX_FIRST_OBJECT.getZeroBased());
        Workout editedWorkout = new WorkoutBuilder(workoutToEdit).withWorkoutName("Strength Training").build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_OBJECT, new WorkoutName("Strength Training"));

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_WORKOUT_SUCCESS,
                editedWorkout.getWorkoutName());

        ModelManager expectedModel = new ModelManager(new UserPrefs(),
                model.getExerciseList(),
                model.getWorkoutList(),
                model.getScheduleList(),
                model.getLogList());

        expectedModel.setWorkout(workoutToEdit, editedWorkout);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showWorkoutAtIndex(model, INDEX_FIRST_OBJECT);

        Index outOfBoundIndex = INDEX_SECOND_OBJECT;
        // ensures that outOfBoundIndex is still in bounds of exercise list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getWorkoutList().getWorkoutList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex, new WorkoutName("Sample workout name"));

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void execute_duplicateWorkout_throwsCommandException() {
        Workout workout = model.getFilteredWorkoutList().get(INDEX_FIRST_OBJECT.getZeroBased());

        EditCommand editCommand = new EditCommand(INDEX_FIRST_OBJECT, workout.getWorkoutName());

        assertCommandFailure(editCommand, model, MESSAGE_DUPLICATE_WORKOUT);
    }

    @Test
    public void equals() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_OBJECT, LEGS_WORKOUT.getWorkoutName());
        EditCommand otherEditCommand = new EditCommand(INDEX_SECOND_OBJECT, ARMS_WORKOUT.getWorkoutName());

        // same object -> returns true
        assertTrue(editCommand.equals(editCommand));

        // same values -> returns true
        EditCommand editCommandCopy = new EditCommand(INDEX_FIRST_OBJECT, LEGS_WORKOUT.getWorkoutName());
        assertTrue(editCommand.equals(editCommandCopy));

        // different types -> returns false
        assertFalse(editCommand.equals(1));

        // null -> returns false
        assertFalse(editCommand.equals(null));

        // different workout -> returns false
        assertFalse(editCommand.equals(otherEditCommand));
    }
}
