package seedu.zerotoone.logic.parser.workout.exercise;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.zerotoone.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.zerotoone.testutil.CommandParserTestUtil.assertParseFailure;
import static seedu.zerotoone.testutil.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.logic.commands.workout.exercise.EditCommand;

public class EditCommandParserTest {
    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_invalidFormat_throwsParseException() {
        // 4 integers provided instead of 3
        String userInput = "1 2 3 4";
        String invalidFeedback = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, invalidFeedback);
    }

    @Test
    public void parse_validFormat_success() {
        String userInput = "1 2 3";
        EditCommand expectedCommand =
                new EditCommand(Index.fromOneBased(1), Index.fromOneBased(2), Index.fromOneBased(3));
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
