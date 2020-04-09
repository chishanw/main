package seedu.zerotoone.logic.parser;

import static seedu.zerotoone.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.zerotoone.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.zerotoone.commons.util.Pair;
import seedu.zerotoone.logic.commands.AboutCommand;
import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.DoneCommand;
import seedu.zerotoone.logic.commands.ExitCommand;
import seedu.zerotoone.logic.commands.SkipCommand;
import seedu.zerotoone.logic.commands.StartCommand;
import seedu.zerotoone.logic.commands.StopCommand;
import seedu.zerotoone.logic.commands.exercise.ExerciseCommand;
import seedu.zerotoone.logic.commands.log.LogCommand;
import seedu.zerotoone.logic.commands.schedule.ScheduleCommand;
import seedu.zerotoone.logic.commands.workout.WorkoutCommand;
import seedu.zerotoone.logic.parser.exceptions.ParseException;
import seedu.zerotoone.logic.parser.exercise.ExerciseCommandParser;
import seedu.zerotoone.logic.parser.log.LogCommandParser;
import seedu.zerotoone.logic.parser.schedule.ScheduleCommandParser;
import seedu.zerotoone.logic.parser.session.DoneCommandParser;
import seedu.zerotoone.logic.parser.session.SkipCommandParser;
import seedu.zerotoone.logic.parser.session.StartCommandParser;
import seedu.zerotoone.logic.parser.session.StopCommandParser;
import seedu.zerotoone.logic.parser.workout.WorkoutCommandParser;
import seedu.zerotoone.ui.util.ViewType;

/**
 * Parses user input.
 */
public class ParserManager {

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
        Pair<String, String> separatedInput = separateCommandAndArguments(input);
        String commandWord = separatedInput.getFirstObject();
        String arguments = separatedInput.getSecondObject();

        switch (commandWord) {
        case StartCommand.COMMAND_WORD:
            return new StartCommandParser().parse(arguments);
        case StopCommand.COMMAND_WORD:
            return new StopCommandParser().parse(arguments);
        case DoneCommand.COMMAND_WORD:
            return new DoneCommandParser().parse(arguments);
        case SkipCommand.COMMAND_WORD:
            return new SkipCommandParser().parse(arguments);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case AboutCommand.COMMAND_WORD:
            return new AboutCommand();
        case ExerciseCommand.COMMAND_WORD:
            return new ExerciseCommandParser().parse(arguments);
        case WorkoutCommand.COMMAND_WORD:
            return new WorkoutCommandParser().parse(arguments);
        case ScheduleCommand.COMMAND_WORD:
            return new ScheduleCommandParser().parse(arguments);
        case LogCommand.COMMAND_WORD:
            return new LogCommandParser().parse(arguments);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Parses view type for switching UI views.
     * @param input full user input string.
     * @return the view type.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public ViewType parseViewType(String input) throws ParseException {
        Pair<String, String> separatedInput = separateCommandAndArguments(input);
        String commandWord = separatedInput.getFirstObject();

        switch (commandWord) {
        case StartCommand.COMMAND_WORD:
            return ViewType.SESSION_VIEW;
        case StopCommand.COMMAND_WORD:
            return ViewType.SESSION_VIEW;
        case DoneCommand.COMMAND_WORD:
            return ViewType.SESSION_VIEW;
        case SkipCommand.COMMAND_WORD:
            return ViewType.SESSION_VIEW;
        case ExitCommand.COMMAND_WORD:
            return ViewType.SESSION_VIEW;
        case AboutCommand.COMMAND_WORD:
            return ViewType.SESSION_VIEW;
        case ExerciseCommand.COMMAND_WORD:
            return ViewType.EXERCISE_VIEW;
        case WorkoutCommand.COMMAND_WORD:
            return ViewType.WORKOUT_VIEW;
        case ScheduleCommand.COMMAND_WORD:
            return ViewType.SCHEDULE_VIEW;
        case LogCommand.COMMAND_WORD:
            return ViewType.LOG_VIEW;
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * A convenience method that separates the command and arguments of an input.
     * @param input The user input.
     * @return A pair contaning the command word and arguments.
     * @throws ParseException If the input does not match the stipulated
     * command format.
     */
    private Pair<String, String> separateCommandAndArguments(String input) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AboutCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        return new Pair<String, String>(commandWord, arguments);
    }
}
