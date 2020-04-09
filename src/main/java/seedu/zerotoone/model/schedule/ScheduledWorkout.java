package seedu.zerotoone.model.schedule;

import java.util.Comparator;

import seedu.zerotoone.model.workout.Workout;

/**
 * STEPH_TODO_JAVADOC
 */
public class ScheduledWorkout {

    private final Schedule schedule;
    private final Workout workoutToSchedule;
    private final DateTime dateTime;
    private final DateTime dateTimeNow;

    public ScheduledWorkout(Schedule schedule, Workout workoutToSchedule, DateTime dateTime, DateTime dateTimeNow) {
        this.schedule = schedule;
        this.workoutToSchedule = workoutToSchedule;
        this.dateTime = dateTime;
        this.dateTimeNow = dateTimeNow;
    }

    public static Comparator<ScheduledWorkout> getComparator() {
        return new Comparator<ScheduledWorkout>() {
            @Override
            public int compare(ScheduledWorkout s1, ScheduledWorkout s2) {
                return s1.getDateTime().compareTo(s2.getDateTime());
            }
        };
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public String getScheduledWorkoutName() {
        return workoutToSchedule.getWorkoutName().fullName;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public boolean isOutDated() {
        return dateTime.compareTo(dateTimeNow) < 0;
    }

    /**
     * STEPH_TODO: may not even need this
     */
    public boolean isSameScheduledWorkout(ScheduledWorkout otherScheduledWorkout) {
        // if (otherScheduledWorkout == this) {
        //     return true;
        // }
        //
        // return otherScheduledWorkout != null
        //         && otherScheduledWorkout.getScheduledWorkoutName().equals(getScheduledWorkoutName());

        return equals(otherScheduledWorkout);
    }

    /**
     * Returns true if both scheduledWorkouts have the same identity and data fields.
     * This defines a stronger notion of equality between two scheduledWorkouts.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ScheduledWorkout)) {
            return false;
        }

        ScheduledWorkout otherScheduledWorkout = (ScheduledWorkout) other;
        return otherScheduledWorkout.getScheduledWorkoutName().equals(getScheduledWorkoutName())
                && otherScheduledWorkout.getDateTime().equals(getDateTime());
    }
}
