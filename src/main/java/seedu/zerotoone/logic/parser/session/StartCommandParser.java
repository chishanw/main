package seedu.zerotoone.logic.parser.session;

import static java.util.Objects.requireNonNull;
import static seedu.zerotoone.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.logic.commands.StartCommand;
import seedu.zerotoone.logic.parser.Parser;
import seedu.zerotoone.logic.parser.exceptions.ParseException;
import seedu.zerotoone.logic.parser.exercise.ExerciseParserUtil;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class StartCommandParser implements Parser<StartCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public StartCommand parse(String args) throws ParseException {
        requireNonNull(args);
        if (args.equals("")) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, StartCommand.MESSAGE_USAGE));
        }

        Index index = ExerciseParserUtil.parseIndex(args);
        return new StartCommand(index);
    }
}
