package seedu.zerotoone.logic.commands.workout.exercise;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zerotoone.testutil.Assert.assertThrows;
import static seedu.zerotoone.testutil.CommandTestUtil.assertCommandSuccess;
import static seedu.zerotoone.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;
import static seedu.zerotoone.testutil.TypicalIndexes.INDEX_SECOND_OBJECT;
import static seedu.zerotoone.testutil.exercise.ExerciseCommandTestUtil.showExerciseAtIndex;
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
import seedu.zerotoone.testutil.exercise.ExerciseBuilder;
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
    public void execute_invalidWorkoutIndexFilteredList_throwsCommandException() {
        showWorkoutAtIndex(model, INDEX_FIRST_OBJECT);

        Index outOfBoundIndex = INDEX_SECOND_OBJECT;
        // ensures that outOfBoundIndex is still in bounds of exercise list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getWorkoutList().getWorkoutList().size());

        AddCommand addCommand = new AddCommand(outOfBoundIndex, INDEX_FIRST_OBJECT);

        assertCommandFailure(addCommand, model, Messages.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void execute_invalidExerciseIndexFilteredList_throwsCommandException() {
        showExerciseAtIndex(model, INDEX_FIRST_OBJECT);

        Index outOfBoundIndex = INDEX_SECOND_OBJECT;
        // ensures that outOfBoundIndex is still in bounds of exercise list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getWorkoutList().getWorkoutList().size());

        AddCommand addCommand = new AddCommand(INDEX_FIRST_OBJECT, outOfBoundIndex);

        assertCommandFailure(addCommand, model, Messages.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void execute_emptyExercise_throwsCommandException() {
        Exercise emptyExercise = new ExerciseBuilder().withExerciseName("Empty exercise").build();
        model.addExercise(emptyExercise);
        AddCommand addCommand = new AddCommand(INDEX_FIRST_OBJECT,
                Index.fromOneBased(model.getFilteredExerciseList().size()));
        assertCommandFailure(addCommand, model, Messages.MESSAGE_INVALID_WORKOUT_EXERCISE);
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
