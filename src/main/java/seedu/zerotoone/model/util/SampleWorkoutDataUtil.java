package seedu.zerotoone.model.util;

import java.util.ArrayList;
import java.util.List;

import seedu.zerotoone.model.exercise.Exercise;
import seedu.zerotoone.model.util.SampleExerciseDataUtil.SampleExerciseIndex;
import seedu.zerotoone.model.workout.ReadOnlyWorkoutList;
import seedu.zerotoone.model.workout.Workout;
import seedu.zerotoone.model.workout.WorkoutList;
import seedu.zerotoone.model.workout.WorkoutName;

/**
 * Contains utility methods for populating {@code WorkoutList} with sample data.
 */
public class SampleWorkoutDataUtil {
    public static Workout[] getSampleWorkouts() {
        Workout[] workouts = new Workout[3];
        Exercise[] exercises = SampleExerciseDataUtil.getSampleExercises();

        // Push Day
        WorkoutName pushDayName = new WorkoutName("Push Day");
        List<Exercise> pushDayExercises = new ArrayList<>();
        pushDayExercises.add(exercises[SampleExerciseIndex.BENCH_PRESS]);
        pushDayExercises.add(exercises[SampleExerciseIndex.OVERHEAD_PRESS]);
        pushDayExercises.add(exercises[SampleExerciseIndex.TRICEPS_PUSHDOWN]);
        workouts[SampleWorkoutIndex.PUSH_DAY] = new Workout(pushDayName, pushDayExercises);

        // Pull Day
        WorkoutName pullDayName = new WorkoutName("Pull Day");
        List<Exercise> pullDayExercises = new ArrayList<>();
        pullDayExercises.add(exercises[SampleExerciseIndex.DEADLIFT]);
        pullDayExercises.add(exercises[SampleExerciseIndex.CABLE_ROW]);
        pullDayExercises.add(exercises[SampleExerciseIndex.FACE_PULL]);
        workouts[SampleWorkoutIndex.PULL_DAY] = new Workout(pullDayName, pullDayExercises);

        // Legs Day
        WorkoutName legsDayName = new WorkoutName("Legs Day");
        List<Exercise> legsDayExercises = new ArrayList<>();
        legsDayExercises.add(exercises[SampleExerciseIndex.SQUATS]);
        legsDayExercises.add(exercises[SampleExerciseIndex.LEG_PRESS]);
        legsDayExercises.add(exercises[SampleExerciseIndex.ROMANIAN_DEADLIFT]);
        workouts[SampleWorkoutIndex.LEGS_DAY] = new Workout(legsDayName, legsDayExercises);
        return workouts;
    }

    public static ReadOnlyWorkoutList getSampleWorkoutList() {
        WorkoutList sampleWorkoutList = new WorkoutList();
        for (Workout workout : getSampleWorkouts()) {
            sampleWorkoutList.addWorkout(workout);
        }
        return sampleWorkoutList;
    }

    /**
     * A convenience class to easily navigate within the array returned by getSampleWorkouts()
     */
    class SampleWorkoutIndex {
        static final int PUSH_DAY = 0;
        static final int PULL_DAY = 1;
        static final int LEGS_DAY = 2;
    }
}
