package seedu.zerotoone.testutil.schedule;

import static seedu.zerotoone.testutil.schedule.TypicalSchedules.getTypicalSchedules;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import seedu.zerotoone.model.schedule.DateTime;
import seedu.zerotoone.model.schedule.Schedule;
import seedu.zerotoone.model.schedule.ScheduledWorkout;
import seedu.zerotoone.model.schedule.ScheduledWorkoutList;

/**
 * A utility class containing a list of {@code ScheduledWorkout} objects to be used in tests.
 */
public class TypicalScheduledWorkouts {

    private TypicalScheduledWorkouts() {} // prevents instantiation

    /**
     * Returns an {@code ScheduledWorkoutList} with all the typical schedules.
     */
    public static ScheduledWorkoutList getTypicalScheduledWorkoutList() {
        ScheduledWorkoutList scheduledWorkoutList = new ScheduledWorkoutList();
        scheduledWorkoutList.setScheduledWorkouts(getTypicalScheduledWorkouts());
        return scheduledWorkoutList;
    }

    public static List<ScheduledWorkout> getTypicalScheduledWorkouts() {
        DateTime now = DateTime.now();
        return getTypicalSchedules().stream()
                .map((Schedule schedule) -> schedule.getScheduledWorkout(DateTime.now()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
