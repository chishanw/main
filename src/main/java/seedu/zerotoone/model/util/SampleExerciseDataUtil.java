package seedu.zerotoone.model.util;

import java.util.ArrayList;
import java.util.List;

import seedu.zerotoone.model.exercise.Exercise;
import seedu.zerotoone.model.exercise.ExerciseList;
import seedu.zerotoone.model.exercise.ExerciseName;
import seedu.zerotoone.model.exercise.ExerciseSet;
import seedu.zerotoone.model.exercise.NumReps;
import seedu.zerotoone.model.exercise.ReadOnlyExerciseList;
import seedu.zerotoone.model.exercise.Weight;

/**
 * Contains utility methods for populating {@code ExerciseList} with sample data.
 */
public class SampleExerciseDataUtil {
    public static Exercise[] getSampleExercises() {
        Exercise[] exercises = new Exercise[9];

        // Bench Press
        ExerciseName benchPressName = new ExerciseName("Bench Press");
        List<ExerciseSet> benchPressSets = new ArrayList<>();
        benchPressSets.add(new ExerciseSet(new Weight("60"), new NumReps("5")));
        benchPressSets.add(new ExerciseSet(new Weight("60"), new NumReps("5")));
        benchPressSets.add(new ExerciseSet(new Weight("60"), new NumReps("5")));
        exercises[SampleExerciseIndex.BENCH_PRESS] = new Exercise(benchPressName, benchPressSets);

        // Overhead Press
        ExerciseName overheadPressName = new ExerciseName("Overhead Press");
        List<ExerciseSet> overheadPressSets = new ArrayList<>();
        overheadPressSets.add(new ExerciseSet(new Weight("30"), new NumReps("10")));
        overheadPressSets.add(new ExerciseSet(new Weight("30"), new NumReps("10")));
        overheadPressSets.add(new ExerciseSet(new Weight("30"), new NumReps("10")));
        exercises[SampleExerciseIndex.OVERHEAD_PRESS] = new Exercise(overheadPressName, overheadPressSets);

        // Triceps Pushdown
        ExerciseName tricepsPushdownName = new ExerciseName("Triceps Pushdown");
        List<ExerciseSet> tricepsPushdownSets = new ArrayList<>();
        tricepsPushdownSets.add(new ExerciseSet(new Weight("15"), new NumReps("10")));
        tricepsPushdownSets.add(new ExerciseSet(new Weight("15"), new NumReps("10")));
        tricepsPushdownSets.add(new ExerciseSet(new Weight("15"), new NumReps("10")));
        tricepsPushdownSets.add(new ExerciseSet(new Weight("15"), new NumReps("10")));
        tricepsPushdownSets.add(new ExerciseSet(new Weight("15"), new NumReps("10")));
        exercises[SampleExerciseIndex.TRICEPS_PUSHDOWN] = new Exercise(tricepsPushdownName, tricepsPushdownSets);

        // Squats
        ExerciseName squatsName = new ExerciseName("Squats");
        List<ExerciseSet> squatsSets = new ArrayList<>();
        squatsSets.add(new ExerciseSet(new Weight("60"), new NumReps("8")));
        squatsSets.add(new ExerciseSet(new Weight("60"), new NumReps("8")));
        squatsSets.add(new ExerciseSet(new Weight("60"), new NumReps("8")));
        squatsSets.add(new ExerciseSet(new Weight("60"), new NumReps("8")));
        squatsSets.add(new ExerciseSet(new Weight("60"), new NumReps("8")));
        exercises[SampleExerciseIndex.SQUATS] = new Exercise(squatsName, squatsSets);

        // Leg Press
        ExerciseName legPressName = new ExerciseName("Leg Press");
        List<ExerciseSet> legPressSets = new ArrayList<>();
        legPressSets.add(new ExerciseSet(new Weight("110"), new NumReps("10")));
        legPressSets.add(new ExerciseSet(new Weight("110"), new NumReps("10")));
        legPressSets.add(new ExerciseSet(new Weight("110"), new NumReps("10")));
        exercises[SampleExerciseIndex.LEG_PRESS] = new Exercise(legPressName, legPressSets);

        // Romanian Deadlift
        ExerciseName romanianDeadliftName = new ExerciseName("Romanian Deadlift");
        List<ExerciseSet> romanianDeadliftSets = new ArrayList<>();
        romanianDeadliftSets.add(new ExerciseSet(new Weight("50"), new NumReps("10")));
        romanianDeadliftSets.add(new ExerciseSet(new Weight("50"), new NumReps("10")));
        romanianDeadliftSets.add(new ExerciseSet(new Weight("50"), new NumReps("10")));
        exercises[SampleExerciseIndex.ROMANIAN_DEADLIFT] = new Exercise(romanianDeadliftName, romanianDeadliftSets);

        // Deadlift
        ExerciseName deadliftName = new ExerciseName("Deadlift");
        List<ExerciseSet> deadliftSets = new ArrayList<>();
        deadliftSets.add(new ExerciseSet(new Weight("60"), new NumReps("5")));
        deadliftSets.add(new ExerciseSet(new Weight("60"), new NumReps("5")));
        deadliftSets.add(new ExerciseSet(new Weight("60"), new NumReps("5")));
        deadliftSets.add(new ExerciseSet(new Weight("60"), new NumReps("5")));
        exercises[SampleExerciseIndex.DEADLIFT] = new Exercise(deadliftName, deadliftSets);

        // Cable Row
        ExerciseName cableRowName = new ExerciseName("Cable Row");
        List<ExerciseSet> cableRowSets = new ArrayList<>();
        cableRowSets.add(new ExerciseSet(new Weight("45"), new NumReps("10")));
        cableRowSets.add(new ExerciseSet(new Weight("45"), new NumReps("10")));
        cableRowSets.add(new ExerciseSet(new Weight("45"), new NumReps("10")));
        exercises[SampleExerciseIndex.CABLE_ROW] = new Exercise(cableRowName, cableRowSets);

        // Face Pull
        ExerciseName facePullName = new ExerciseName("Face Pull");
        List<ExerciseSet> facePullSets = new ArrayList<>();
        facePullSets.add(new ExerciseSet(new Weight("20"), new NumReps("15")));
        facePullSets.add(new ExerciseSet(new Weight("20"), new NumReps("15")));
        facePullSets.add(new ExerciseSet(new Weight("20"), new NumReps("15")));
        facePullSets.add(new ExerciseSet(new Weight("20"), new NumReps("15")));
        exercises[SampleExerciseIndex.FACE_PULL] = new Exercise(facePullName, facePullSets);

        return exercises;
    }

    public static ReadOnlyExerciseList getSampleExerciseList() {
        ExerciseList sampleExerciseList = new ExerciseList();
        for (Exercise exercise : getSampleExercises()) {
            sampleExerciseList.addExercise(exercise);
        }
        return sampleExerciseList;
    }

    /**
     * A convenience class to easily navigate within the array returned by getSampleExercises()
     */
    class SampleExerciseIndex {
        static final int BENCH_PRESS = 0;
        static final int OVERHEAD_PRESS = 1;
        static final int TRICEPS_PUSHDOWN = 2;
        static final int SQUATS = 3;
        static final int LEG_PRESS = 4;
        static final int ROMANIAN_DEADLIFT = 5;
        static final int DEADLIFT = 6;
        static final int CABLE_ROW = 7;
        static final int FACE_PULL = 8;
    }
}
