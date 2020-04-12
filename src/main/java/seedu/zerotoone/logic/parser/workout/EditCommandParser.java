package seedu.zerotoone.logic.parser.workout;

import static java.util.Objects.requireNonNull;
import static seedu.zerotoone.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.zerotoone.logic.parser.CliSyntax.PREFIX_WORKOUT_NAME;

import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.logic.commands.workout.EditCommand;
import seedu.zerotoone.logic.parser.Parser;
import seedu.zerotoone.logic.parser.exceptions.ParseException;
import seedu.zerotoone.logic.parser.util.ArgumentMultimap;
import seedu.zerotoone.logic.parser.util.ArgumentTokenizer;
import seedu.zerotoone.model.workout.WorkoutName;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);
        if (!ArgumentTokenizer.isOnlyPrefixesPresent(argMultimap, PREFIX_WORKOUT_NAME)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        Index index = WorkoutParserUtil.parseIndex(argMultimap.getPreamble());
        WorkoutName workoutName = WorkoutParserUtil.parseWorkoutName(
                argMultimap.getValue(PREFIX_WORKOUT_NAME).get());

        return new EditCommand(index, workoutName);
    }
}
