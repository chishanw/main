package seedu.zerotoone.logic.parser.exercise;

import static seedu.zerotoone.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.zerotoone.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.zerotoone.logic.commands.AboutCommand;
import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.exercise.CreateCommand;
import seedu.zerotoone.logic.commands.exercise.DeleteCommand;
import seedu.zerotoone.logic.commands.exercise.EditCommand;
import seedu.zerotoone.logic.commands.exercise.FindCommand;
import seedu.zerotoone.logic.commands.exercise.ListCommand;
import seedu.zerotoone.logic.commands.exercise.set.SetCommand;
import seedu.zerotoone.logic.parser.exceptions.ParseException;
import seedu.zerotoone.logic.parser.exercise.set.SetCommandParser;

/**
 * Parses user input.
 */
public class ExerciseCommandParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param input full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
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
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);
        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);
        case SetCommand.COMMAND_WORD:
            return new SetCommandParser().parse(arguments);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
