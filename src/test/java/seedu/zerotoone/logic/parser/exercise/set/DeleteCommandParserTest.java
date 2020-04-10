package seedu.zerotoone.logic.parser.exercise.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.commons.core.Messages;
import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.exercise.set.DeleteCommand;
import seedu.zerotoone.logic.parser.exceptions.ParseException;
import seedu.zerotoone.logic.parser.util.ParserUtil;

public class DeleteCommandParserTest {
    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_nullArguments_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_invalidArguments_throwParseException() {
        Exception exceptionThrown;
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.MESSAGE_USAGE);

        // Empty String
        exceptionThrown = assertThrows(ParseException.class, () ->
                parser.parse(""));
        assertEquals(expectedMessage, exceptionThrown.getMessage());

        // Missing Set Id
        exceptionThrown = assertThrows(ParseException.class, () ->
                parser.parse("1"));
        assertEquals(expectedMessage, exceptionThrown.getMessage());
    }

    @Test
    public void parse_invalidIndex_throwParseException() {
        Exception exceptionThrown;
        String expectedMessage = ParserUtil.MESSAGE_INVALID_INDEX;

        // Negative Set Id
        exceptionThrown = assertThrows(ParseException.class, () ->
                parser.parse("1 -1"));
        assertEquals(expectedMessage, exceptionThrown.getMessage());

        // Negative Exercise Id
        exceptionThrown = assertThrows(ParseException.class, () ->
                parser.parse("-1 1"));
        assertEquals(expectedMessage, exceptionThrown.getMessage());

        // Non Numeric Input
        exceptionThrown = assertThrows(ParseException.class, () ->
                parser.parse("1 a"));
        assertEquals(expectedMessage, exceptionThrown.getMessage());
    }

    @Test
    public void parse_validArguments_returnsDeleteCommand() throws ParseException {
        Command expectedCommand = new DeleteCommand(Index.fromOneBased(1),
                Index.fromOneBased(2));

        assertEquals(expectedCommand, parser.parse("1 2"));

        // Has Spaces Inside
        assertEquals(expectedCommand, parser.parse("1     2"));
    }
}
