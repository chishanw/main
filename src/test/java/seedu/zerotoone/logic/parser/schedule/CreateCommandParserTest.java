package seedu.zerotoone.logic.parser.schedule;

import static seedu.zerotoone.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.zerotoone.logic.parser.util.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.zerotoone.testutil.CommandParserTestUtil.assertParseFailure;
import static seedu.zerotoone.testutil.CommandParserTestUtil.assertParseSuccess;
import static seedu.zerotoone.testutil.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.zerotoone.testutil.TypicalIndexes.INDEX_SECOND_OBJECT;
import static seedu.zerotoone.testutil.schedule.ScheduleCommandTestUtil.DATETIME_DESC_JUNE;
import static seedu.zerotoone.testutil.schedule.ScheduleCommandTestUtil.INVALID_DATETIME_DESC;
import static seedu.zerotoone.testutil.schedule.ScheduleCommandTestUtil.VALID_DATETIME_JUNE;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.logic.commands.schedule.CreateCommand;
import seedu.zerotoone.model.schedule.DateTime;

class CreateCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateCommand.MESSAGE_USAGE);
    private CreateCommandParser parser = new CreateCommandParser();

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
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, "1" + INVALID_DATETIME_DESC, DateTime.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + DATETIME_DESC_JUNE, MESSAGE_INVALID_INDEX);
    }

    @Test
    void parse_allFieldsPresent_success() {
        Index targetIndex = INDEX_SECOND_OBJECT;
        String userInput = targetIndex.getOneBased() + DATETIME_DESC_JUNE;
        CreateCommand expectedCommand = new CreateCommand(targetIndex,
                new DateTime(VALID_DATETIME_JUNE));
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
