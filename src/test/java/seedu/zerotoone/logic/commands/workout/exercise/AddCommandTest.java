package seedu.zerotoone.logic.commands.workout.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.zerotoone.testutil.Assert.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.logic.commands.CommandResult;
import seedu.zerotoone.model.workout.Workout;
import seedu.zerotoone.testutil.workout.ModelStubAcceptingWorkoutAdded;
import seedu.zerotoone.testutil.workout.WorkoutBuilder;

public class AddCommandTest {
    @Test
    public void constructor_nullWorkout_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null, null));
    }

    // @Test
    // public void execute_workoutAcceptedByModel_addSuccessful() throws Exception {
    //     ModelStubAcceptingWorkoutAdded modelStub = new ModelStubAcceptingWorkoutAdded();
    //     CommandResult commandResult = new AddCommand(Index.fromOneBased(1), Index.fromOneBased(1))
    //             .execute(modelStub);
    //     Workout absWorkout = new WorkoutBuilder().withWorkoutName(VALID_WORKOUT_NAME_ABS_WORKOUT).build();

    //     assertEquals(String.format(AddCommand.MESSAGE_SUCCESS,
    //             VALID_WORKOUT_NAME_ABS_WORKOUT), commandResult.getFeedbackToUser());
    //     assertEquals(Arrays.asList(absWorkout), modelStub.workoutsAdded);
    // }
}
