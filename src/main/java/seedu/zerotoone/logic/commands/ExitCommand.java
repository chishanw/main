package seedu.zerotoone.logic.commands;

import seedu.zerotoone.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT =
            "Thank you for using ZeroToOne! Your data has been saved successfully.\n"
            + "Hope to see you again soon!";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, false, true);
    }

}
