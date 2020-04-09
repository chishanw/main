package seedu.zerotoone.model.util;

import seedu.zerotoone.model.schedule.DateTime;
import seedu.zerotoone.model.schedule.OneTimeSchedule;
import seedu.zerotoone.model.schedule.Schedule;
import seedu.zerotoone.model.schedule.ScheduleList;
import seedu.zerotoone.model.util.SampleWorkoutDataUtil.SampleWorkoutIndex;
import seedu.zerotoone.model.workout.Workout;

/**
 * Contains utility methods for populating {@code ScheduleList} with sample data.
 */
public class SampleScheduleDataUtil {
    public static Schedule[] getSampleSchedules() {
        Schedule[] schedules = new Schedule[3];
        Workout[] workouts = SampleWorkoutDataUtil.getSampleWorkouts();

        // Push Day
        DateTime pushDayDateTime = new DateTime("2020-05-11 13:00");
        schedules[0] = new OneTimeSchedule(workouts[SampleWorkoutIndex.PUSH_DAY], pushDayDateTime);

        // Pull Day
        DateTime pullDayDateTime = new DateTime("2020-05-05 23:00");
        schedules[1] = new OneTimeSchedule(workouts[SampleWorkoutIndex.PULL_DAY], pullDayDateTime);

        // Legs Day
        DateTime legsDayDateTime = new DateTime("2021-05-15 12:00");
        schedules[2] = new OneTimeSchedule(workouts[SampleWorkoutIndex.LEGS_DAY], legsDayDateTime);
        return schedules;
    }

    public static ScheduleList getSampleScheduleList() {
        ScheduleList sampleScheduleList = new ScheduleList();
        for (Schedule schedule : getSampleSchedules()) {
            sampleScheduleList.addSchedule(schedule);
        }
        return sampleScheduleList;
    }

    /**
     * A convenience class to easily navigate within the array returned by getSampleSchedules()
     */
    class SampleScheduleIndex {
        static final int PUSH_DAY = 0;
        static final int PULL_DAY = 1;
        static final int LEGS_DAY = 2;
    }
}
