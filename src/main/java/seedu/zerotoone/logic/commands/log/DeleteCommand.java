package seedu.zerotoone.logic.commands.log;

import static java.util.Objects.requireNonNull;
import static seedu.zerotoone.commons.util.DateUtil.getPrettyDateTimeString;

import java.util.List;

import seedu.zerotoone.commons.core.Messages;
import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.CommandResult;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.session.CompletedWorkout;

/**
 * Deletes a Log identified using it's displayed index from the log list.
 */
public class DeleteCommand extends LogCommand {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = "Usage: log delete LOG_ID";
    public static final String MESSAGE_DELETE_LOG_SUCCESS = "Deleted log: %1$s on %2$s";
    private final Index logId;

    public DeleteCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.logId = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.isInSession()) {
            throw new CommandException(Command.MESSAGE_SESSION_STARTED);
        }

        List<CompletedWorkout> lastShownList = model.getFilteredLogList();

        if (logId.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        CompletedWorkout completedWorkoutToDelete = lastShownList.get(logId.getZeroBased());
        model.deleteLog(logId.getZeroBased());

        String outputMessage = String.format(MESSAGE_DELETE_LOG_SUCCESS,
            completedWorkoutToDelete.getWorkoutName().toString(),
            getPrettyDateTimeString(completedWorkoutToDelete.getStartTime()));
        return new CommandResult(outputMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && logId.equals(((DeleteCommand) other).logId)); // state check
    }
}
