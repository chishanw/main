package seedu.zerotoone.logic.parser.workout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.logic.commands.workout.CreateCommand;
import seedu.zerotoone.logic.commands.workout.DeleteCommand;
import seedu.zerotoone.logic.commands.workout.EditCommand;
import seedu.zerotoone.logic.commands.workout.FindCommand;
import seedu.zerotoone.logic.commands.workout.ListCommand;
import seedu.zerotoone.logic.commands.workout.exercise.AddCommand;
import seedu.zerotoone.logic.commands.workout.exercise.WorkoutExerciseCommand;
import seedu.zerotoone.logic.parser.exceptions.ParseException;
import seedu.zerotoone.model.workout.WorkoutName;

public class WorkoutCommandParserTest {
    private WorkoutCommandParser parser = new WorkoutCommandParser();

    @Test
    public void parse_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_invalidFormat_throwsParseException() {
        // user input that does not match basic command format
        String invalidCommand = "I want to find a workout.";
        assertThrows(ParseException.class, () -> parser.parse(invalidCommand));
    }

    @Test
    public void parse_unknownCommand_throwsParseException() {
        // user input of an unknown command
        String unknownCommand = "exercise workout create w/workout name";
        assertThrows(ParseException.class, () -> parser.parse(unknownCommand));
    }

    @Test
    public void parse_validCreateCommand_success() throws ParseException {
        String userInput = "create w/workout";
        CreateCommand expectedCommand = new CreateCommand(new WorkoutName("workout"));
        assertEquals(parser.parse(userInput), expectedCommand);
    }

    @Test
    public void parse_validListCommand_success() throws ParseException {
        String userInput = "list";
        ListCommand expectedCommand = new ListCommand();
        assertEquals(parser.parse(userInput), expectedCommand);
    }

    @Test
    public void parse_validEditCommand_success() throws ParseException {
        String userInput = "edit 1 w/workout";
        EditCommand expectedCommand = new EditCommand(Index.fromOneBased(1), new WorkoutName("workout"));
        assertEquals(parser.parse(userInput), expectedCommand);
    }

    @Test
    public void parse_validDeleteCommand_success() throws ParseException {
        String userInput = "delete 1";
        DeleteCommand expectedCommand = new DeleteCommand(Index.fromOneBased(1));
        assertEquals(parser.parse(userInput), expectedCommand);
    }

    @Test
    public void parse_validFindCommand_success() throws ParseException {
        String userInput = "find w/workout";
        FindCommand expectedCommand = new FindCommand(new WorkoutName("workout"));
        assertEquals(parser.parse(userInput), expectedCommand);
    }

    @Test
    public void parse_validWorkoutExerciseCommand_success() throws ParseException {
        String userInput = "exercise add 1 1";
        WorkoutExerciseCommand expectedCommand = new AddCommand(Index.fromOneBased(1), Index.fromOneBased(1));
        assertEquals(parser.parse(userInput), expectedCommand);
    }
}
