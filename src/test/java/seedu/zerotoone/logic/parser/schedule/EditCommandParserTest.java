package seedu.zerotoone.logic.parser.schedule;

import static seedu.zerotoone.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.zerotoone.logic.parser.util.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.zerotoone.testutil.CommandParserTestUtil.assertParseFailure;
import static seedu.zerotoone.testutil.CommandParserTestUtil.assertParseSuccess;
import static seedu.zerotoone.testutil.TypicalIndexes.INDEX_SECOND_OBJECT;
import static seedu.zerotoone.testutil.schedule.ScheduleCommandTestUtil.DATETIME_DESC_JUNE;
import static seedu.zerotoone.testutil.schedule.ScheduleCommandTestUtil.VALID_DATETIME_JUNE;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.logic.commands.schedule.EditCommand;
import seedu.zerotoone.model.schedule.DateTime;

class EditCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);
    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + DATETIME_DESC_JUNE, MESSAGE_INVALID_INDEX);

        // zero index
        assertParseFailure(parser, "0" + DATETIME_DESC_JUNE, MESSAGE_INVALID_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    void parse_allFieldsPresent_success() {
        Index targetIndex = INDEX_SECOND_OBJECT;
        String userInput = targetIndex.getOneBased() + DATETIME_DESC_JUNE;
        EditCommand expectedCommand = new EditCommand(targetIndex,
                new DateTime(VALID_DATETIME_JUNE));
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
