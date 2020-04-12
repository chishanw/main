package seedu.zerotoone.logic.commands.schedule;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.zerotoone.commons.core.Messages;
import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.CommandResult;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.logic.commands.util.Commands;
import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.schedule.ScheduledWorkout;

/**
 * Deletes a scheduled workout identified using it's displayed index from the scheduled workout list.
 */
public class DeleteCommand extends ScheduleCommand {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = "Usage: " + Commands.SCHEDULE_DELETE;
    public static final String MESSAGE_DELETE_SCHEDULED_WORKOUT_SUCCESS = "Deleted scheduled workout: %1$s";
    private final Index scheduledWorkoutId;

    public DeleteCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.scheduledWorkoutId = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.isInSession()) {
            throw new CommandException(Command.MESSAGE_SESSION_STARTED);
        }

        List<ScheduledWorkout> lastShownList = model.getSortedScheduledWorkoutList();

        if (scheduledWorkoutId.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        ScheduledWorkout scheduledWorkoutToDelete = lastShownList.get(scheduledWorkoutId.getZeroBased());
        model.deleteScheduledWorkout(scheduledWorkoutToDelete);

        String outputMessage = String.format(MESSAGE_DELETE_SCHEDULED_WORKOUT_SUCCESS,
                scheduledWorkoutToDelete.getScheduledWorkoutName());
        return new CommandResult(outputMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && scheduledWorkoutId.equals(((DeleteCommand) other).scheduledWorkoutId)); // state check
    }
}
