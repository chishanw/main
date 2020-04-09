package seedu.zerotoone.model.schedule;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import seedu.zerotoone.model.workout.Workout;

/**
 * STEPH_TODO_JAVADOC
 */
public class OneTimeSchedule implements Schedule {

    private final Workout workoutToSchedule;
    private final DateTime dateTime;

    public OneTimeSchedule(Workout workoutToSchedule, DateTime dateTime) {
        this.workoutToSchedule = workoutToSchedule;
        this.dateTime = dateTime;
    }

    @Override
    public Workout getWorkoutToSchedule() {
        return workoutToSchedule;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    @Override
    public Optional<List<ScheduledWorkout>> getScheduledWorkout(DateTime now) {
        return Optional.of(
                Collections.singletonList(new ScheduledWorkout(this, workoutToSchedule, dateTime, now)));
    }

    @Override
    public boolean isSameSchedule(Schedule other) {
        return equals(other);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof OneTimeSchedule)) {
            return false;
        }

        OneTimeSchedule otherSchedule = (OneTimeSchedule) other;
        return otherSchedule.getWorkoutToSchedule().equals(getWorkoutToSchedule())
                && otherSchedule.getDateTime().equals(getDateTime());
    }

    @Override
    public String toString() {
        return workoutToSchedule.getWorkoutName() + " at " + dateTime.toString();
    }
}
