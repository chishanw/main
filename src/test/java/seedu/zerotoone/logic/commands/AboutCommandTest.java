package seedu.zerotoone.logic.commands;

import static seedu.zerotoone.logic.commands.AboutCommand.SHOWING_ABOUT_MESSAGE;
import static seedu.zerotoone.testutil.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.ModelManager;

public class AboutCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_ABOUT_MESSAGE, true, false, false);
        assertCommandSuccess(new AboutCommand(), model, expectedCommandResult, expectedModel);
    }
}
