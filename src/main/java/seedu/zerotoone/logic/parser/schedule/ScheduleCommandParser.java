package seedu.zerotoone.logic.parser.schedule;

import static seedu.zerotoone.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.zerotoone.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.zerotoone.logic.commands.AboutCommand;
import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.schedule.CreateCommand;
import seedu.zerotoone.logic.commands.schedule.DeleteCommand;
import seedu.zerotoone.logic.commands.schedule.EditCommand;
import seedu.zerotoone.logic.commands.schedule.ListCommand;
import seedu.zerotoone.logic.parser.exceptions.ParseException;

/**
 * STEPH_TODO_JAVADOC
 */
public class ScheduleCommandParser {

    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * STEPH_TODO_JAVADOC
     *
     * @param input STEPH_TODO_JAVADOC
     * @return STEPH_TODO_JAVADOC
     * @throws ParseException STEPH_TODO_JAVADOC
     */
    public Command parse(String input) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AboutCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {
        case CreateCommand.COMMAND_WORD:
            return new CreateCommandParser().parse(arguments);
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);
        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
