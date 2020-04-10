package seedu.zerotoone.logic.parser.exercise.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.commons.core.Messages;
import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.exercise.set.AddCommand;
import seedu.zerotoone.logic.parser.exceptions.ParseException;
import seedu.zerotoone.model.exercise.NumReps;
import seedu.zerotoone.model.exercise.Weight;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_nullArguments_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_invalidArguments_throwParseException() {
        Exception exceptionThrown;
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_USAGE);

        // Empty String
        exceptionThrown = assertThrows(ParseException.class, () ->
                parser.parse(""));
        assertEquals(expectedMessage, exceptionThrown.getMessage());

        // Missing Prefix
        exceptionThrown = assertThrows(ParseException.class, () ->
                parser.parse("this"));
        assertEquals(expectedMessage, exceptionThrown.getMessage());

        // Missing One Prefix
        exceptionThrown = assertThrows(ParseException.class, () ->
                parser.parse("1 r/10"));
        assertEquals(expectedMessage, exceptionThrown.getMessage());

        // Invalid Prefix
        exceptionThrown = assertThrows(ParseException.class, () ->
                parser.parse("1 f/something"));
        assertEquals(expectedMessage, exceptionThrown.getMessage());

        // Empty Preamble
        exceptionThrown = assertThrows(ParseException.class, () ->
                parser.parse(" r/10 m/20"));
        assertEquals(expectedMessage, exceptionThrown.getMessage());
    }

    @Test
    public void parse_validArguments_returnsAddCommand() throws ParseException {
        Command expectedCommand = new AddCommand(Index.fromOneBased(1),
                new NumReps("2"), new Weight("3"));

        assertEquals(expectedCommand, parser.parse("1 r/2 m/3"));

        // Has Spaces Inside
        assertEquals(expectedCommand, parser.parse("1 r/ 2  m/ 3 "));
    }
}
