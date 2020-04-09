package seedu.zerotoone.logic.parser.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zerotoone.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.zerotoone.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.zerotoone.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.zerotoone.testutil.Assert.assertThrows;
import static seedu.zerotoone.testutil.TypicalIndexes.INDEX_SECOND_OBJECT;
import static seedu.zerotoone.testutil.schedule.ScheduleCommandTestUtil.VALID_DATETIME_JUNE;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.logic.commands.AboutCommand;
import seedu.zerotoone.logic.commands.schedule.CreateCommand;
import seedu.zerotoone.logic.commands.schedule.DeleteCommand;
import seedu.zerotoone.logic.commands.schedule.EditCommand;
import seedu.zerotoone.logic.commands.schedule.ListCommand;
import seedu.zerotoone.logic.parser.exceptions.ParseException;
import seedu.zerotoone.model.schedule.DateTime;

class ScheduleCommandParserTest {
    private final ScheduleCommandParser parser = new ScheduleCommandParser();

    @Test
    public void parse_create() throws Exception {
        Index index = INDEX_SECOND_OBJECT;
        DateTime dateTime = new DateTime(VALID_DATETIME_JUNE);
        String input =
                CreateCommand.COMMAND_WORD + " " + index.getOneBased() + " " + PREFIX_DATETIME + dateTime.toString();

        CreateCommand command = (CreateCommand) parser.parse(input);
        assertEquals(new CreateCommand(index, dateTime), command);
    }

    @Test
    public void parse_delete() throws Exception {
        Index index = INDEX_SECOND_OBJECT;
        String input = DeleteCommand.COMMAND_WORD + " " + index.getOneBased();

        DeleteCommand command = (DeleteCommand) parser.parse(input);
        assertEquals(new DeleteCommand(index), command);
    }

    @Test
    public void parse_edit() throws Exception {
        Index index = INDEX_SECOND_OBJECT;
        DateTime dateTime = new DateTime(VALID_DATETIME_JUNE);
        String input =
                EditCommand.COMMAND_WORD + " " + index.getOneBased() + " " + PREFIX_DATETIME + dateTime.toString();

        EditCommand command = (EditCommand) parser.parse(input);
        assertEquals(new EditCommand(index, dateTime), command);
    }

    @Test
    public void parse_list() throws Exception {
        assertTrue(parser.parse(ListCommand.COMMAND_WORD) instanceof ListCommand);
    }


    @Test
    public void parse_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AboutCommand.MESSAGE_USAGE), () -> parser.parse(""));
    }

    @Test
    public void parse_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parse("unknownCommand"));
    }
}
