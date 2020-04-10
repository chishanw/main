package seedu.zerotoone.testutil.log;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import seedu.zerotoone.model.exercise.ExerciseName;
import seedu.zerotoone.model.exercise.NumReps;
import seedu.zerotoone.model.exercise.Weight;
import seedu.zerotoone.model.session.CompletedExercise;
import seedu.zerotoone.model.session.CompletedSet;

/**
 * A utility class to help with building CompletedExercise objects.
 */
public class CompletedExerciseBuilder {
    private static final Long DEFAULT_EXERCISE_TIME_IN_MINUTES = 10L;
    /**
     * The constant DEFAULT_EXERCISE_NAME.
     */
    private static final String DEFAULT_EXERCISE_NAME = "Bench Press";

    private ExerciseName exerciseName;
    private List<CompletedSet> exerciseSets;

    /**
     * Instantiates a new Completed exercise builder.
     */
    public CompletedExerciseBuilder() {
        exerciseName = new ExerciseName(DEFAULT_EXERCISE_NAME);
        exerciseSets = new ArrayList<>();
    }

    /**
     * Initializes the CompletedExerciseBuilder with the data of {@code exerciseToCopy}.
     *
     * @param exerciseToCopy the exercise to copy
     */
    public CompletedExerciseBuilder(CompletedExercise exerciseToCopy) {
        exerciseName = exerciseToCopy.getExerciseName();
        exerciseSets = exerciseToCopy.getSets();
    }

    /**
     * Sets the {@code ExerciseName} of the {@code CompletedExercise} that we are building.
     *
     * @param exerciseName the exercise name
     * @return the completed exercise builder
     */
    public CompletedExerciseBuilder withExerciseName(String exerciseName) {
        this.exerciseName = new ExerciseName(exerciseName);
        return this;
    }

    /**
     * Sets the {@code completedSet} of the {@code CompletedExercise} that we are building.
     *
     * @param weight  the weight
     * @param numReps the num reps
     * @return the completed exercise builder
     */
    public CompletedExerciseBuilder withExerciseSet(String weight, String numReps) {
        List<CompletedSet> exerciseSetsCopy = new ArrayList<>(exerciseSets);
        exerciseSetsCopy.add(new CompletedSet(new Weight(weight), new NumReps(numReps), true));
        exerciseSets = exerciseSetsCopy;
        return this;
    }

    /**
     * Build completed exercise.
     *
     * @return the completed exercise
     */
    public CompletedExercise build() {
        return new CompletedExercise(exerciseName, exerciseSets, LocalDateTime.now(),
            LocalDateTime.now().plusMinutes(DEFAULT_EXERCISE_TIME_IN_MINUTES));
    }

}
