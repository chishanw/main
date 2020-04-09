package seedu.zerotoone.testutil.workout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zerotoone.logic.parser.CliSyntax.PREFIX_NUM_OF_REPS;
import static seedu.zerotoone.logic.parser.CliSyntax.PREFIX_WEIGHT;
import static seedu.zerotoone.logic.parser.CliSyntax.PREFIX_WORKOUT_NAME;
import static seedu.zerotoone.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.exercise.Exercise;
import seedu.zerotoone.model.workout.PredicateFilterWorkoutName;
import seedu.zerotoone.model.workout.Workout;
import seedu.zerotoone.model.workout.WorkoutList;
import seedu.zerotoone.testutil.CommandTestUtil;
import seedu.zerotoone.testutil.exercise.ExerciseBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class WorkoutCommandTestUtil extends CommandTestUtil {

    public static final String VALID_WORKOUT_NAME_ARMS_WORKOUT = "Arms Workout";
    public static final String VALID_WORKOUT_NAME_LEGS_WORKOUT = "Legs Workout";
    public static final String VALID_WORKOUT_NAME_ABS_WORKOUT = "Abs Workout";

    public static final Exercise VALID_EXERCISE_LUNGES = new ExerciseBuilder()
                    .withExerciseName("Lunges")
                    .withExerciseSet("30", "4")
                    .build();

    public static final Exercise VALID_EXERCISE_DUMBBELL_CRUNCH = new ExerciseBuilder()
                    .withExerciseName("Dumbbell Crunch")
                    .withExerciseSet("20", "4")
                    .build();

    public static final String WORKOUT_NAME_DESC_ARMS_WORKOUT = " " + PREFIX_WORKOUT_NAME
            + VALID_WORKOUT_NAME_ARMS_WORKOUT;
    public static final String WORKOUT_NAME_DESC_LEGS_WORKOUT = " " + PREFIX_WORKOUT_NAME
            + VALID_WORKOUT_NAME_LEGS_WORKOUT;
    public static final String WORKOUT_NAME_DESC_ABS_WORKOUT = " " + PREFIX_WORKOUT_NAME
            + VALID_WORKOUT_NAME_ABS_WORKOUT;

    public static final String INVALID_WORKOUT_NAME_DESC =
            " " + PREFIX_WORKOUT_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_NUM_REPS_DESC =
            " " + PREFIX_NUM_OF_REPS + "911a"; // 'a' not allowed in repetitions
    public static final String INVALID_WEIGHT_DESC =
            " " + PREFIX_WEIGHT + "911a"; // 'a' not allowed in weight

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the workout list, filtered workout list and selected workout in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        WorkoutList expectedWorkoutList = new WorkoutList(actualModel.getWorkoutList());
        List<Workout> expectedFilteredList = new ArrayList<>(actualModel.getFilteredWorkoutList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedWorkoutList, actualModel.getWorkoutList());
        assertEquals(expectedFilteredList, actualModel.getFilteredWorkoutList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the workout at the given {@code targetIndex} in the
     * {@code model}'s workout list.
     */
    public static void showWorkoutAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredWorkoutList().size());

        Workout workout = model.getFilteredWorkoutList().get(targetIndex.getZeroBased());
        final String workoutName = workout.getWorkoutName().fullName;
        model.updateFilteredWorkoutList(new PredicateFilterWorkoutName(workoutName));
        assertEquals(1, model.getFilteredWorkoutList().size());
    }
}
