package seedu.zerotoone.logic.parser.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.zerotoone.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.zerotoone.logic.parser.CliSyntax.PREFIX_DATETIME;

import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.logic.commands.schedule.CreateCommand;
import seedu.zerotoone.logic.parser.Parser;
import seedu.zerotoone.logic.parser.exceptions.ParseException;
import seedu.zerotoone.logic.parser.util.ArgumentMultimap;
import seedu.zerotoone.logic.parser.util.ArgumentTokenizer;
import seedu.zerotoone.model.schedule.DateTime;

/**
 * STEPH_TODO_JAVADOC
 */
public class CreateCommandParser implements Parser<CreateCommand> {

    /**
     * STEPH_TODO_JAVADOC
     *
     * @param args STEPH_TODO_JAVADOC
     * @return STEPH_TODO_JAVADOC
     * @throws ParseException STEPH_TODO_JAVADOC
     */
    public CreateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);
        if (!ArgumentTokenizer.isOnlyPrefixesPresent(argMultimap, PREFIX_DATETIME)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateCommand.MESSAGE_USAGE));
        }

        Index index = ScheduleParserUtil.parseIndex(argMultimap.getPreamble());
        DateTime dateTime = ScheduleParserUtil.parseDateTime(argMultimap.getValue(PREFIX_DATETIME).get());
        return new CreateCommand(index, dateTime);
    }
}
