package seedu.zerotoone.logic.parser.exercise;

import static java.util.Objects.requireNonNull;
import static seedu.zerotoone.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.zerotoone.logic.parser.CliSyntax.PREFIX_EXERCISE_NAME;

import seedu.zerotoone.logic.commands.exercise.FindCommand;
import seedu.zerotoone.logic.parser.Parser;
import seedu.zerotoone.logic.parser.exceptions.ParseException;
import seedu.zerotoone.logic.parser.util.ArgumentMultimap;
import seedu.zerotoone.logic.parser.util.ArgumentTokenizer;
import seedu.zerotoone.model.exercise.ExerciseName;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns an FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_EXERCISE_NAME);
        if (!ArgumentTokenizer.isOnlyPrefixesPresent(argMultimap, PREFIX_EXERCISE_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ExerciseName exerciseName = new ExerciseName(argMultimap.getValue(PREFIX_EXERCISE_NAME).get());
        return new FindCommand(exerciseName);
    }
}
