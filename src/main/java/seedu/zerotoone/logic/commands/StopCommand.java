package seedu.zerotoone.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.FormatStyle;
import java.util.List;

import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.exercise.Exercise;

/**
 * Deletes a exercise identified using it's displayed index from the exercise list.
 */
public class StopCommand extends Command {
    public static final String COMMAND_WORD = "stop";
    public static final String MESSAGE_USAGE = "Usage: stop";
    public static final String MESSAGE_STOP_SESSION_SUCCESS = "Successfully completed session";
    public static final String MESSAGE_NOT_STARTED = "There is no session in progress!";
    private final FormatStyle formatStyle = FormatStyle.MEDIUM;

    public StopCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Exercise> lastShownList = model.getFilteredExerciseList();

        if (!model.isInSession()) {
            throw new CommandException((MESSAGE_NOT_STARTED));
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        model.stopSession(currentDateTime);

        return new CommandResult(MESSAGE_STOP_SESSION_SUCCESS);
    }
}
