package seedu.zerotoone.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;

import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.logic.commands.util.Commands;
import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.session.CompletedSet;

/**
 * Completes the next up exerciseQueue in the session.
 */
public class SkipCommand extends Command {
    public static final String COMMAND_WORD = "skip";
    public static final String MESSAGE_USAGE = "Usage: " + Commands.SKIP;
    public static final String MESSAGE_SUCCESS = "Skipped set:%1$s";
    public static final String MESSAGE_SKIPPED_LAST = "You have skipped the last set, "
            + "your workout session is done and saved!";

    public SkipCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.isInSession()) {
            throw new CommandException(MESSAGE_SESSION_NOT_STARTED);
        }

        LocalDateTime currentDateTime = LocalDateTime.now();

        CompletedSet set = model.skip();

        String outputMessage = String.format(MESSAGE_SUCCESS, set.toString());

        if (!model.hasExerciseLeft()) {
            model.stopSession(currentDateTime);
            outputMessage = outputMessage + "\n" + MESSAGE_SKIPPED_LAST;
        }

        return new CommandResult(outputMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SkipCommand); // instanceof handles nulls
    }
}
