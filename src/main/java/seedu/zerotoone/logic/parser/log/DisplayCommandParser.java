package seedu.zerotoone.logic.parser.log;

import static java.util.Objects.requireNonNull;
import static seedu.zerotoone.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.zerotoone.logic.parser.CliSyntax.PREFIX_LOG_END;
import static seedu.zerotoone.logic.parser.CliSyntax.PREFIX_LOG_START;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import seedu.zerotoone.logic.commands.log.DisplayCommand;
import seedu.zerotoone.logic.commands.log.FindCommand;
import seedu.zerotoone.logic.parser.Parser;
import seedu.zerotoone.logic.parser.exceptions.ParseException;
import seedu.zerotoone.logic.parser.util.ArgumentMultimap;
import seedu.zerotoone.logic.parser.util.ArgumentTokenizer;

/**
 * Parses input arguments and creates a new DisplayCommand object
 */
public class DisplayCommandParser implements Parser<DisplayCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns an FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DisplayCommand parse(String args) throws ParseException {
        requireNonNull(args);
        Optional<LocalDateTime> startTimeOptional = Optional.empty();
        Optional<LocalDateTime> endTimeOptional = Optional.empty();
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_LOG_START, PREFIX_LOG_END);

        if (argMultimap.getValue(PREFIX_LOG_START).isPresent()) {
            try {
                startTimeOptional =
                    Optional.of(LogParserUtil.parseDateTime(argMultimap.getValue(PREFIX_LOG_START).get()));
            } catch (DateTimeParseException e) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
        }

        if (argMultimap.getValue(PREFIX_LOG_END).isPresent()) {
            try {
                endTimeOptional =
                    Optional.of(LogParserUtil.parseDateTime(argMultimap.getValue(PREFIX_LOG_END).get()));
            } catch (DateTimeParseException e) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
        }

        return new DisplayCommand(startTimeOptional, endTimeOptional);
    }
}
