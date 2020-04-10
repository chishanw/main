package seedu.zerotoone.logic.parser.workout;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.zerotoone.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.zerotoone.logic.parser.CliSyntax.PREFIX_WORKOUT_NAME;
import static seedu.zerotoone.testutil.CommandParserTestUtil.assertParseFailure;
import static seedu.zerotoone.testutil.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.logic.commands.workout.FindCommand;
import seedu.zerotoone.logic.parser.util.ArgumentMultimap;
import seedu.zerotoone.logic.parser.util.ArgumentTokenizer;
import seedu.zerotoone.model.workout.WorkoutName;

public class FindCommandParserTest {
    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String invalidFeedback = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE);

        // no workout prefix
        assertParseFailure(parser, "workout name", invalidFeedback);

        // no preamble before prefix
        assertParseFailure(parser, "w/workout name", invalidFeedback);
    }

    @Test
    public void parse_validArgs_success() {
        // typical valid args
        String validArgs = " w/workout";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(validArgs, PREFIX_WORKOUT_NAME);
        WorkoutName workoutName = new WorkoutName(argMultimap.getValue(PREFIX_WORKOUT_NAME).get());
        FindCommand findCommand = new FindCommand(workoutName);
        assertParseSuccess(parser, validArgs, findCommand);

        // multiple words after prefix
        String otherValidArgs = " w/workout name";
        ArgumentMultimap otherArgMultimap = ArgumentTokenizer.tokenize(otherValidArgs, PREFIX_WORKOUT_NAME);
        WorkoutName otherWorkoutName = new WorkoutName(otherArgMultimap.getValue(PREFIX_WORKOUT_NAME).get());
        FindCommand otherFindCommand = new FindCommand(otherWorkoutName);
        assertParseSuccess(parser, otherValidArgs, otherFindCommand);
    }
}
