package seedu.zerotoone.logic.parser.exercise.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.commons.core.Messages;
import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.logic.commands.AboutCommand;
import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.exercise.set.AddCommand;
import seedu.zerotoone.logic.commands.exercise.set.DeleteCommand;
import seedu.zerotoone.logic.commands.exercise.set.EditCommand;
import seedu.zerotoone.logic.parser.exceptions.ParseException;
import seedu.zerotoone.model.exercise.NumReps;
import seedu.zerotoone.model.exercise.Weight;

public class SetCommandParserTest {
    private SetCommandParser parser = new SetCommandParser();

    @Test
    public void parse_invalidInput_throwsParseException() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AboutCommand.MESSAGE_USAGE);

        Exception exceptionThrown = assertThrows(ParseException.class, () -> parser.parse(""));
        assertEquals(expectedMessage, exceptionThrown.getMessage());
    }

    @Test
    public void parse_unknownCommand_throwsParseException() {
        String expectedMessage = Messages.MESSAGE_UNKNOWN_COMMAND;
        Exception exceptionThrown = assertThrows(ParseException.class, () -> parser.parse("unKnownComand"));
        assertEquals(expectedMessage, exceptionThrown.getMessage());
    }

    @Test
    public void parse_validAddCommand_returnsAddCommand() throws ParseException {
        Command expectedCommand = new AddCommand(Index.fromOneBased(1),
                new NumReps("2"), new Weight("3"));

        String validInput = "add 1 r/2 m/3";
        Command actualCommand = parser.parse(validInput);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parse_validEditCommand_returnsEditCommand() throws ParseException {
        Command expectedCommand = new EditCommand(Index.fromOneBased(1),
                Index.fromOneBased(2), new NumReps("3"), new Weight("4"));

        String validInput = "edit 1 2 r/3 m/4";
        Command actualCommand = parser.parse(validInput);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parse_validDeleteCommand_returnsDeleteCommand() throws ParseException {
        Command expectedCommand = new DeleteCommand(Index.fromOneBased(1), Index.fromOneBased(2));

        String validInput = "delete 1 2";
        Command actualCommand = parser.parse(validInput);
        assertEquals(expectedCommand, actualCommand);
    }
}
