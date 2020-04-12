package seedu.zerotoone.logic.commands;

import seedu.zerotoone.logic.commands.util.Commands;
import seedu.zerotoone.model.Model;

/**
 * Format full about instructions for every command for display.
 */
public class AboutCommand extends Command {

    public static final String COMMAND_WORD = "about";
    public static final String MESSAGE_USAGE = "Usage: " + Commands.ABOUT;
    public static final String SHOWING_ABOUT_MESSAGE =
            "Here is some info about this app, and a list of all the commands!";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_ABOUT_MESSAGE, true, false, false);
    }
}
