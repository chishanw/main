package seedu.zerotoone.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.logic.commands.util.Commands;
import seedu.zerotoone.model.Model;

/**
 * Stops an ongoing session if there is one.
 */
public class StopCommand extends Command {
    public static final String COMMAND_WORD = "stop";
    public static final String MESSAGE_USAGE = "Usage: " + Commands.STOP;
    public static final String MESSAGE_SUCCESS = "Stopped workout session: %1$s at ";
    public static final FormatStyle FORMAT_STYLE = FormatStyle.MEDIUM;

    public StopCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.isInSession()) {
            throw new CommandException((MESSAGE_SESSION_NOT_STARTED));
        }
        return executeHelper(model, LocalDateTime.now());
    }

    /**
     * Helper method for execute, such that we keep the method from being too stateful with LocalDateTime objects.
     * This lets us test more extensively.
     * @param model model in use.
     * @param currentDateTime passed in by execute with a call to getDateTimeNow().
     * @return returns the CommandResult as per normal execute method behaviour.
     */
    public CommandResult executeHelper(Model model, LocalDateTime currentDateTime) {
        requireNonNull(model);
        String formatted = currentDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FORMAT_STYLE));
        String outputMessage = String.format(MESSAGE_SUCCESS,
                model.getCurrentWorkout().get().getWorkoutName().toString()) + formatted;

        model.stopSession(currentDateTime);

        return new CommandResult(outputMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StopCommand); // instanceof handles nulls
    }
}
