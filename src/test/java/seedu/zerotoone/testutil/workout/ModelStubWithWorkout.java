package seedu.zerotoone.testutil.workout;

import static java.util.Objects.requireNonNull;

import seedu.zerotoone.model.workout.Workout;
import seedu.zerotoone.testutil.ModelStub;

/**
 * Model stub with workout.
 */
public class ModelStubWithWorkout extends ModelStub {
    private final Workout workout;

    ModelStubWithWorkout(Workout workout) {
        requireNonNull(workout);
        this.workout = workout;
    }

    @Override
    public boolean hasWorkout(Workout workout) {
        requireNonNull(workout);
        return this.workout.isSameWorkout(workout);
    }
}
