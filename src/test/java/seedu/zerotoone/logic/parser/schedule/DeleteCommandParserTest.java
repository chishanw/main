package seedu.zerotoone.logic.parser.schedule;

import static seedu.zerotoone.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.zerotoone.logic.parser.util.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.zerotoone.testutil.CommandParserTestUtil.assertParseFailure;
import static seedu.zerotoone.testutil.CommandParserTestUtil.assertParseSuccess;
import static seedu.zerotoone.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.logic.commands.schedule.DeleteCommand;


class DeleteCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE);
    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteCommand(INDEX_FIRST_OBJECT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }
}
