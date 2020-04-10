package seedu.zerotoone.logic.parser.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.commons.core.Messages;
import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.logic.commands.AboutCommand;
import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.exercise.CreateCommand;
import seedu.zerotoone.logic.commands.exercise.DeleteCommand;
import seedu.zerotoone.logic.commands.exercise.EditCommand;
import seedu.zerotoone.logic.commands.exercise.FindCommand;
import seedu.zerotoone.logic.commands.exercise.ListCommand;
import seedu.zerotoone.logic.commands.exercise.set.AddCommand;
import seedu.zerotoone.logic.commands.exercise.set.SetCommand;
import seedu.zerotoone.logic.parser.exceptions.ParseException;
import seedu.zerotoone.model.exercise.ExerciseName;
import seedu.zerotoone.model.exercise.NumReps;
import seedu.zerotoone.model.exercise.Weight;

public class ExerciseCommandParserTest {
    private ExerciseCommandParser parser = new ExerciseCommandParser();

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
    public void parse_validCreateCommand_returnsCreateCommand() throws ParseException {
        Command expectedCommand = new CreateCommand(new ExerciseName("Bench Press"));

        String validInput = "create e/Bench Press";
        Command actualCommand = parser.parse(validInput);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parse_validListCommand_returnsListCommand() throws ParseException {
        Command expectedCommand = new ListCommand();

        String validInput = "list";
        Command actualCommand = parser.parse(validInput);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parse_validEditCommand_returnsEditCommand() throws ParseException {
        Command expectedCommand = new EditCommand(Index.fromOneBased(1), new ExerciseName("Bench Press"));

        String validInput = "edit 1 e/Bench Press";
        Command actualCommand = parser.parse(validInput);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parse_validDeleteCommand_returnsDeleteCommand() throws ParseException {
        Command expectedCommand = new DeleteCommand(Index.fromOneBased(1));

        String validInput = "delete 1";
        Command actualCommand = parser.parse(validInput);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parse_validFindCommand_returnsFindCommand() throws ParseException {
        Command expectedCommand = new FindCommand(new ExerciseName("press"));

        String validInput = "find e/press";
        Command actualCommand = parser.parse(validInput);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parse_validSetCommand_returnsSetCommand() throws ParseException {
        SetCommand expectedCommand = new AddCommand(Index.fromOneBased(1),
                new NumReps("10"), new Weight("20"));

        String validInput = "set add 1 r/10 m/20";
        Command actualCommand = parser.parse(validInput);
        assertEquals(expectedCommand, actualCommand);
    }
}
