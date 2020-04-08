package seedu.zerotoone.logic.commands.workout.exercise;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zerotoone.testutil.Assert.assertThrows;
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


public class AddCommandTest {
    private Model model = new ModelManager(new UserPrefs(),
            getTypicalExerciseList(),
            getTypicalWorkoutList(),
            new ScheduleList(),
            new LogList());

    @Test
    public void constructor_nullWorkout_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null, null));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Workout workoutToEdit = model.getFilteredWorkoutList().get(INDEX_FIRST_OBJECT.getZeroBased());
        Exercise exerciseToAdd = model.getFilteredExerciseList().get(INDEX_FIRST_OBJECT.getZeroBased());
        Workout editedWorkout = new WorkoutBuilder(workoutToEdit).withWorkoutExercise(exerciseToAdd).build();
        AddCommand addCommand = new AddCommand(INDEX_FIRST_OBJECT, INDEX_FIRST_OBJECT);

        String expectedMessage = String.format(AddCommand.MESSAGE_ADD_WORKOUT_EXERCISE_SUCCESS,
                exerciseToAdd.getExerciseName());

        ModelManager expectedModel = new ModelManager(new UserPrefs(),
                model.getExerciseList(),
                model.getWorkoutList(),
                model.getScheduleList(),
                model.getLogList());

        expectedModel.setWorkout(workoutToEdit, editedWorkout);

        assertCommandSuccess(addCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        AddCommand addCommand = new AddCommand(INDEX_FIRST_OBJECT, INDEX_FIRST_OBJECT);
        AddCommand otherAddCommand = new AddCommand(INDEX_FIRST_OBJECT, INDEX_SECOND_OBJECT);

        // same object -> returns true
        assertTrue(addCommand.equals(addCommand));

        // same values -> returns true
        AddCommand addCommandCopy = new AddCommand(INDEX_FIRST_OBJECT, INDEX_FIRST_OBJECT);
        assertTrue(addCommand.equals(addCommandCopy));

        // different types -> returns false
        assertFalse(addCommand.equals(1));

        // null -> returns false
        assertFalse(addCommand.equals(null));

        // different workout exercise -> returns false
        assertFalse(addCommand.equals(otherAddCommand));
    }
}
