package seedu.zerotoone.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;

import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.logic.commands.util.Commands;
import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.session.CompletedSet;

/**
 * Completes the next up set in the session.
 */
public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    public static final String MESSAGE_USAGE = "Usage: " + Commands.DONE;
    public static final String MESSAGE_SUCCESS = "Completed set:%1$s";
    public static final String MESSAGE_NONE_LEFT = "You have finished the last set, "
            + "your workout session is done and saved!";

    public DoneCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.isInSession()) {
            throw new CommandException(MESSAGE_SESSION_NOT_STARTED);
        }

        LocalDateTime currentDateTime = LocalDateTime.now();

        CompletedSet set = model.done();

        String outputMessage = String.format(MESSAGE_SUCCESS, set.toString());

        if (!model.hasExerciseLeft()) {
            model.stopSession(currentDateTime);
            outputMessage = outputMessage + "\n" + MESSAGE_NONE_LEFT;
        }

        return new CommandResult(outputMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DoneCommand); // instanceof handles nulls
    }
}
