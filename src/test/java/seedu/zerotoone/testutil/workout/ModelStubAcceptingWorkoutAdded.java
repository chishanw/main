package seedu.zerotoone.testutil.workout;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.zerotoone.model.workout.ReadOnlyWorkoutList;
import seedu.zerotoone.model.workout.Workout;
import seedu.zerotoone.model.workout.WorkoutList;
import seedu.zerotoone.testutil.ModelStub;

/**
 * A Model stub that always accept the exercise being added.
 */
public class ModelStubAcceptingWorkoutAdded extends ModelStub {
    final ArrayList<Workout> workoutsAdded = new ArrayList<>();

    @Override
    public boolean hasWorkout(Workout workout) {
        requireNonNull(workout);
        return workoutsAdded.stream().anyMatch(workout::isSameWorkout);
    }

    @Override
    public void addWorkout(Workout workout) {
        requireNonNull(workout);
        workoutsAdded.add(workout);
    }

    @Override
    public ReadOnlyWorkoutList getWorkoutList() {
        return new WorkoutList();
    }
}