package seedu.zerotoone.testutil.log;

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
import seedu.zerotoone.model.log.LogList;
import seedu.zerotoone.model.session.CompletedWorkout;
import seedu.zerotoone.testutil.CommandTestUtil;

/**
 * Contains helper methods for testing commands.
 */
public class LogCommandTestUtil extends CommandTestUtil {

    public static final String VALID_WORKOUT_NAME_BENCH_PRESS = "Bench Press";
    public static final String VALID_WORKOUT_NAME_DEADLIFT = "Deadlift";
    public static final String VALID_WORKOUT_NAME_OVERHEAD_PRESS = "Overhead Press";
    public static final String VALID_NUM_REPS_BENCH_PRESS = "10";
    public static final String VALID_NUM_REPS_DEADLIFT = "5";
    public static final String VALID_NUM_REPS_OVERHEAD_PRESS = "10";
    public static final String VALID_WEIGHT_BENCH_PRESS = "60";
    public static final String VALID_WEIGHT_DEADLIFT = "65";
    public static final String VALID_WEIGHT_OVERHEAD_PRESS = "30";

    public static final String WORKOUT_NAME_DESC_BENCH_PRESS = " " + PREFIX_WORKOUT_NAME
            + VALID_WORKOUT_NAME_BENCH_PRESS;
    public static final String WORKOUT_NAME_DESC_DEADLIFT = " " + PREFIX_WORKOUT_NAME
            + VALID_WORKOUT_NAME_DEADLIFT;
    public static final String WORKOUT_NAME_DESC_OVERHEAD_PRESS = " " + PREFIX_WORKOUT_NAME
            + VALID_WORKOUT_NAME_OVERHEAD_PRESS;
    public static final String NUM_REPS_DESC_BENCH_PRESS = " " + PREFIX_NUM_OF_REPS
            + VALID_NUM_REPS_BENCH_PRESS;
    public static final String NUM_REPS_DESC_DEADLIFT = " " + PREFIX_NUM_OF_REPS
            + VALID_NUM_REPS_DEADLIFT;
    public static final String NUM_REPS_DESC_OVERHEAD_PRESS = " " + PREFIX_NUM_OF_REPS
            + VALID_NUM_REPS_OVERHEAD_PRESS;
    public static final String WEIGHT_DESC_BENCH_PRESS = " " + PREFIX_WEIGHT
            + VALID_WEIGHT_BENCH_PRESS;
    public static final String WEIGHT_DESC_DEADLIFT = " " + PREFIX_WEIGHT
            + VALID_WEIGHT_DEADLIFT;
    public static final String WEIGHT_DESC_OVERHEAD_PRESS = " " + PREFIX_WEIGHT
            + VALID_WEIGHT_OVERHEAD_PRESS;

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
        LogList expectedLogList = new LogList(actualModel.getLogList());
        List<CompletedWorkout> expectedFilteredList = new ArrayList<>(actualModel.getFilteredLogList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedLogList, actualModel.getLogList());
        assertEquals(expectedFilteredList, actualModel.getFilteredLogList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the workout at the given {@code targetIndex} in the
     * {@code model}'s workout list.
     */
    public static void showLogAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredLogList().size());

        CompletedWorkout log = model.getFilteredLogList().get(targetIndex.getZeroBased());
        model.updateFilteredLogList(l -> l.equals(log));

        assertEquals(1, model.getFilteredLogList().size());
    }
}
