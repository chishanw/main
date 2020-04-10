package seedu.zerotoone.logic.parser.workout.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.logic.commands.workout.exercise.AddCommand;
import seedu.zerotoone.logic.commands.workout.exercise.DeleteCommand;
import seedu.zerotoone.logic.commands.workout.exercise.EditCommand;
import seedu.zerotoone.logic.parser.exceptions.ParseException;

public class WorkoutExerciseCommandParserTest {
    private WorkoutExerciseCommandParser parser = new WorkoutExerciseCommandParser();

    @Test
    public void parse_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_invalidFormat_throwsParseException() {
        // user input that does not match basic command format
        String invalidCommand = "I want to do something with a workout exercise.";
        assertThrows(ParseException.class, () -> parser.parse(invalidCommand));
    }

    @Test
    public void parse_unknownCommand_throwsParseException() {
        // user input of an unknown command
        String unknownCommand = "workout exercise change w/workout name";
        assertThrows(ParseException.class, () -> parser.parse(unknownCommand));
    }

    @Test
    public void parse_validAddCommand_success() throws ParseException {
        String userInput = "add 1 1";
        AddCommand expectedCommand = new AddCommand(Index.fromOneBased(1), Index.fromOneBased(1));
        assertEquals(parser.parse(userInput), expectedCommand);
    }

    @Test
    public void parse_validEditCommand_success() throws ParseException {
        String userInput = "edit 1 1 2";
        EditCommand expectedCommand =
                new EditCommand(Index.fromOneBased(1), Index.fromOneBased(1), Index.fromOneBased(2));
        assertEquals(parser.parse(userInput), expectedCommand);
    }

    @Test
    public void parse_validDeleteCommand_success() throws ParseException {
        String userInput = "delete 1 1";
        DeleteCommand expectedCommand = new DeleteCommand(Index.fromOneBased(1), Index.fromOneBased(1));
        assertEquals(parser.parse(userInput), expectedCommand);
    }
}
