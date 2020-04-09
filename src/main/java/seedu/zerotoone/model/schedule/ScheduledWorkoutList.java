package seedu.zerotoone.model.schedule;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;

/**
 * Wraps all data at the scheduled workout list level
 * Duplicates are not allowed (by .isSameScheduledWorkout comparison)
 */
public class ScheduledWorkoutList implements ReadOnlyScheduledWorkoutList {

    private final UniqueScheduledWorkoutList scheduledWorkouts;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        scheduledWorkouts = new UniqueScheduledWorkoutList();
    }

    public ScheduledWorkoutList() {}


    //// list overwrite operations

    /**
     * Replaces the contents of the scheduledWorkout list with {@code scheduledWorkouts}.
     * {@code scheduledWorkouts} must not contain duplicate scheduledWorkouts.
     */
    public void setScheduledWorkouts(List<ScheduledWorkout> scheduledWorkouts) {
        this.scheduledWorkouts.setScheduledWorkouts(scheduledWorkouts);
    }

    /**
     * Resets the existing data of this {@code ScheduledWorkoutList} with {@code newData}.
     */
    public void resetData(ReadOnlyScheduledWorkoutList newData) {
        requireNonNull(newData);

        setScheduledWorkouts(newData.getScheduledWorkoutList());
    }

    //// util methods

    @Override
    public String toString() {
        return scheduledWorkouts.asUnmodifiableObservableList().size() + " scheduled workouts";
        // TODO: refine later
    }

    @Override
    public ObservableList<ScheduledWorkout> getScheduledWorkoutList() {
        return scheduledWorkouts.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ScheduledWorkoutList // instanceof handles nulls
                && scheduledWorkouts.equals(((ScheduledWorkoutList) other).scheduledWorkouts));
    }

    @Override
    public int hashCode() {
        return scheduledWorkouts.hashCode();
    }
}
