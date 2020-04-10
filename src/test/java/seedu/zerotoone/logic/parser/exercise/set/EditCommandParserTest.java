package seedu.zerotoone.logic.parser.exercise.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.commons.core.Messages;
import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.exercise.set.EditCommand;
import seedu.zerotoone.logic.parser.exceptions.ParseException;
import seedu.zerotoone.model.exercise.NumReps;
import seedu.zerotoone.model.exercise.Weight;

public class EditCommandParserTest {
    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_nullArguments_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_invalidArguments_throwParseException() {
        Exception exceptionThrown;
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                EditCommand.MESSAGE_USAGE);

        // Empty String
        exceptionThrown = assertThrows(ParseException.class, () ->
                parser.parse(""));
        assertEquals(expectedMessage, exceptionThrown.getMessage());

        // Missing All Prefix
        exceptionThrown = assertThrows(ParseException.class, () ->
                parser.parse("1 2 this"));
        assertEquals(expectedMessage, exceptionThrown.getMessage());

        // Missing One Prefix
        exceptionThrown = assertThrows(ParseException.class, () ->
                parser.parse("1 2 r/3"));
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
    public void parse_validArguments_returnsEditCommand() throws ParseException {
        Command expectedCommand = new EditCommand(Index.fromOneBased(1),
                Index.fromOneBased(2), new NumReps("3"), new Weight("4"));

        assertEquals(expectedCommand, parser.parse("1 2 r/3 m/4"));

        // Has Spaces Inside
        assertEquals(expectedCommand, parser.parse("1 2 r/ 3  m/ 4 "));
    }
}
