package seedu.zerotoone.logic.commands.workout.exercise;

import static seedu.zerotoone.testutil.CommandTestUtil.assertCommandSuccess;
import static seedu.zerotoone.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;
import static seedu.zerotoone.testutil.exercise.TypicalExercises.getTypicalExerciseList;
import static seedu.zerotoone.testutil.workout.TypicalWorkouts.getTypicalWorkoutList;

import java.util.List;

import org.junit.jupiter.api.Test;

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

    // @Test
    // public void execute_validIndexUnfilteredList_success() {
    //     Workout workoutToEdit = model.getFilteredWorkoutList().get(INDEX_FIRST_OBJECT.getZeroBased());
    //     List<Exercise> workoutExercises = workoutToEdit.getWorkoutExercises();
    //     Exercise exerciseToDelete = workoutExercises.get(INDEX_FIRST_OBJECT.getZeroBased());
    //     Workout editedWorkout = new WorkoutBuilder(workoutToEdit).withWorkoutExerciseList(workoutExercises).build();
    //     DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_OBJECT, INDEX_FIRST_OBJECT);

    //     String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_WORKOUT_EXERCISE_SUCCESS,
    //             exerciseToDelete.getExerciseName(),
    //             workoutToEdit.getWorkoutName());

    //     ModelManager expectedModel = new ModelManager(new UserPrefs(),
    //             model.getExerciseList(),
    //             model.getWorkoutList(),
    //             model.getScheduleList(),
    //             model.getLogList());

    //     expectedModel.setWorkout(workoutToEdit, editedWorkout);

    //     assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    // }
}
