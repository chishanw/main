package seedu.zerotoone.testutil.schedule;

import seedu.zerotoone.model.schedule.DateTime;
import seedu.zerotoone.model.schedule.OneTimeSchedule;
import seedu.zerotoone.model.workout.Workout;
import seedu.zerotoone.testutil.workout.WorkoutBuilder;

/**
 * A utility class to help with building Schedule objects.
 */
public class OneTimeScheduleBuilder {

    public static final String DEFAULT_DATETIME = "2020-09-01 13:00";

    private Workout workoutToSchedule;
    private DateTime dateTime;

    public OneTimeScheduleBuilder() {
        workoutToSchedule = new WorkoutBuilder().build();
        dateTime = new DateTime(DEFAULT_DATETIME);
    }

    /**
     * Initializes the OneTimeScheduleBuilder with the data of {@code scheduleToCopy}.
     */
    public OneTimeScheduleBuilder(OneTimeSchedule scheduleToCopy) {
        workoutToSchedule = scheduleToCopy.getWorkoutToSchedule();
        dateTime = scheduleToCopy.getDateTime();
    }

    /**
     * Sets the {@code Workout} of the {@code OneTimeSchedule} that we are building.
     */
    public OneTimeScheduleBuilder withWorkout(Workout workout) {
        this.workoutToSchedule = workout;
        return this;
    }

    /**
     * Sets the {@code DateTime} of the {@code OneTimeSchedule} that we are building.
     */
    public OneTimeScheduleBuilder withDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public OneTimeSchedule build() {
        return new OneTimeSchedule(workoutToSchedule, dateTime);
    }
}
