package seedu.zerotoone.logic.parser.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.commons.core.Messages;
import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.exercise.FindCommand;
import seedu.zerotoone.logic.parser.exceptions.ParseException;
import seedu.zerotoone.model.exercise.ExerciseName;

public class FindCommandParserTest {
    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_nullArguments_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_invalidArguments_throwsParseException() {
        Exception exceptionThrown;
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE);

        // Empty String
        exceptionThrown = assertThrows(ParseException.class, () ->
                parser.parse(""));
        assertEquals(expectedMessage, exceptionThrown.getMessage());

        // Missing Prefix
        exceptionThrown = assertThrows(ParseException.class, () ->
                parser.parse("this"));
        assertEquals(expectedMessage, exceptionThrown.getMessage());

        // Invalid Prefix
        exceptionThrown = assertThrows(ParseException.class, () ->
                parser.parse(" f/something"));
        assertEquals(expectedMessage, exceptionThrown.getMessage());

        // Non Empty Preamble
        exceptionThrown = assertThrows(ParseException.class, () ->
                parser.parse("preamble e/search"));
        assertEquals(expectedMessage, exceptionThrown.getMessage());
    }

    @Test
    public void parse_validArguments_returnsFindCommand() throws ParseException {
        Command exceptedCommand = new FindCommand(new ExerciseName("Bench Press"));

        assertEquals(exceptedCommand, parser.parse(" e/Bench Press"));

        // Has Spaces Inside
        assertEquals(exceptedCommand, parser.parse(" e/   Bench Press   "));
    }
}
