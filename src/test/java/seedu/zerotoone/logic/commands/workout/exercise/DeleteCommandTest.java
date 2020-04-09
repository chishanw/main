package seedu.zerotoone.logic.commands.workout.exercise;

import static seedu.zerotoone.testutil.CommandTestUtil.assertCommandSuccess;
import static seedu.zerotoone.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;
import static seedu.zerotoone.testutil.TypicalIndexes.INDEX_SECOND_OBJECT;
import static seedu.zerotoone.testutil.exercise.TypicalExercises.getTypicalExerciseList;
import static seedu.zerotoone.testutil.workout.TypicalWorkouts.getTypicalWorkoutList;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.ModelManager;
import seedu.zerotoone.model.exercise.Exercise;
import seedu.zerotoone.model.log.LogList;
import seedu.zerotoone.model.schedule.ScheduleList;
import seedu.zerotoone.model.userprefs.UserPrefs;
import seedu.zerotoone.model.workout.Workout;
import seedu.zerotoone.testutil.workout.WorkoutBuilder;
import static seedu.zerotoone.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteCommandTest {
    private Model model = new ModelManager(new UserPrefs(),
            getTypicalExerciseList(),
            getTypicalWorkoutList(),
            new ScheduleList(),
            new LogList());

    @Test
    public void constructor_nullWorkout_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteCommand(null, null));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Workout workoutToEdit = model.getFilteredWorkoutList().get(INDEX_FIRST_OBJECT.getZeroBased());
        Exercise exerciseToDelete = workoutToEdit.getWorkoutExercises().get(INDEX_FIRST_OBJECT.getZeroBased());

        Workout editedWorkout = new WorkoutBuilder(workoutToEdit).build();
        editedWorkout.deleteExercise(exerciseToDelete);

        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_OBJECT, INDEX_FIRST_OBJECT);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_WORKOUT_EXERCISE_SUCCESS,
                exerciseToDelete.getExerciseName(),
                workoutToEdit.getWorkoutName());

        ModelManager expectedModel = new ModelManager(new UserPrefs(),
                model.getExerciseList(),
                model.getWorkoutList(),
                model.getScheduleList(),
                model.getLogList());

        expectedModel.setWorkout(workoutToEdit, editedWorkout);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        DeleteCommand DeleteCommand = new DeleteCommand(INDEX_FIRST_OBJECT, INDEX_FIRST_OBJECT);
        DeleteCommand otherDeleteCommand = new DeleteCommand(INDEX_FIRST_OBJECT, INDEX_SECOND_OBJECT);

        // same object -> returns true
        assertTrue(DeleteCommand.equals(DeleteCommand));

        // same values -> returns true
        DeleteCommand DeleteCommandCopy = new DeleteCommand(INDEX_FIRST_OBJECT, INDEX_FIRST_OBJECT);
        assertTrue(DeleteCommand.equals(DeleteCommandCopy));

        // different types -> returns false
        assertFalse(DeleteCommand.equals(1));

        // null -> returns false
        assertFalse(DeleteCommand.equals(null));

        // different workout -> returns false
        assertFalse(DeleteCommand.equals(otherDeleteCommand));
    }
}
