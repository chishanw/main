package seedu.zerotoone.logic.commands.workout.exercise;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zerotoone.testutil.Assert.assertThrows;
import static seedu.zerotoone.testutil.CommandTestUtil.assertCommandSuccess;
import static seedu.zerotoone.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;
import static seedu.zerotoone.testutil.TypicalIndexes.INDEX_SECOND_OBJECT;
import static seedu.zerotoone.testutil.exercise.TypicalExercises.getTypicalExerciseList;
import static seedu.zerotoone.testutil.workout.TypicalWorkouts.getTypicalWorkoutList;
import static seedu.zerotoone.testutil.workout.WorkoutCommandTestUtil.assertCommandFailure;
import static seedu.zerotoone.testutil.workout.WorkoutCommandTestUtil.showWorkoutAtIndex;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.commons.core.Messages;
import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.ModelManager;
import seedu.zerotoone.model.exercise.Exercise;
import seedu.zerotoone.model.log.LogList;
import seedu.zerotoone.model.schedule.ScheduleList;
import seedu.zerotoone.model.userprefs.UserPrefs;
import seedu.zerotoone.model.workout.Workout;
import seedu.zerotoone.testutil.workout.WorkoutBuilder;

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
    public void execute_invalidWorkoutIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredWorkoutList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex, INDEX_FIRST_OBJECT);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void execute_invalidExerciseIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getWorkoutList().getWorkoutList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_OBJECT, outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void execute_validExerciseIndexFilteredList_success() {
        Workout workoutToEdit = model.getFilteredWorkoutList().get(INDEX_FIRST_OBJECT.getZeroBased());
        Workout editedWorkout = new WorkoutBuilder(workoutToEdit).build();
        Exercise exerciseToDelete = workoutToEdit.getWorkoutExercises().get(INDEX_FIRST_OBJECT.getZeroBased());
        editedWorkout.deleteExercise(exerciseToDelete);

        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_OBJECT, INDEX_FIRST_OBJECT);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_WORKOUT_EXERCISE_SUCCESS,
                exerciseToDelete.getExerciseName());

        ModelManager expectedModel = new ModelManager(new UserPrefs(),
                model.getExerciseList(),
                model.getWorkoutList(),
                model.getScheduleList(),
                model.getLogList());

        expectedModel.setWorkout(workoutToEdit, editedWorkout);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidWorkoutIndexFilteredList_throwsCommandException() {
        showWorkoutAtIndex(model, INDEX_FIRST_OBJECT);

        Index outOfBoundIndex = INDEX_SECOND_OBJECT;
        // ensures that outOfBoundIndex is still in bounds of exercise list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getWorkoutList().getWorkoutList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex, INDEX_FIRST_OBJECT);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_OBJECT, INDEX_FIRST_OBJECT);
        DeleteCommand otherDeleteCommand = new DeleteCommand(INDEX_FIRST_OBJECT, INDEX_SECOND_OBJECT);

        // same object -> returns true
        assertTrue(deleteCommand.equals(deleteCommand));

        // same values -> returns true
        DeleteCommand deleteCommandCopy = new DeleteCommand(INDEX_FIRST_OBJECT, INDEX_FIRST_OBJECT);
        assertTrue(deleteCommand.equals(deleteCommandCopy));

        // different types -> returns false
        assertFalse(deleteCommand.equals(1));

        // null -> returns false
        assertFalse(deleteCommand.equals(null));

        // different workout -> returns false
        assertFalse(deleteCommand.equals(otherDeleteCommand));
    }
}
