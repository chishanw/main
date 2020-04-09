package seedu.zerotoone.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zerotoone.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.zerotoone.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.zerotoone.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.logic.commands.AboutCommand;
import seedu.zerotoone.logic.commands.ExitCommand;
import seedu.zerotoone.logic.commands.exercise.ExerciseCommand;
import seedu.zerotoone.logic.parser.exceptions.ParseException;

public class ParserManagerTest {

    private final ParserManager parser = new ParserManager();

    @Test
    public void parse_exit() throws Exception {
        assertTrue(parser.parse(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parse(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parse_help() throws Exception {
        assertTrue(parser.parse(AboutCommand.COMMAND_WORD) instanceof AboutCommand);
        assertTrue(parser.parse(AboutCommand.COMMAND_WORD + " 3") instanceof AboutCommand);
    }

    @Test
    public void parse_exercise() throws Exception {
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AboutCommand.MESSAGE_USAGE), () ->
                parser.parse(ExerciseCommand.COMMAND_WORD));
    }

    @Test
    public void parse_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AboutCommand.MESSAGE_USAGE), () ->
                parser.parse(""));
    }

    @Test
    public void parse_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () ->
                parser.parse("unknownCommand"));
    }
}
