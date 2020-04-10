package seedu.zerotoone.logic.parser.workout.exercise;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.zerotoone.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.zerotoone.testutil.CommandParserTestUtil.assertParseFailure;
import static seedu.zerotoone.testutil.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.logic.commands.workout.exercise.DeleteCommand;

public class DeleteCommandParserTest {
    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_invalidFormat_throwsParseException() {
        // 3 integers provided instead of 2
        String userInput = "1 2 3";
        String invalidFeedback = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, invalidFeedback);
    }

    @Test
    public void parse_validFormat_success() {
        String userInput = "1 2";
        DeleteCommand expectedCommand = new DeleteCommand(Index.fromOneBased(1), Index.fromOneBased(2));
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
