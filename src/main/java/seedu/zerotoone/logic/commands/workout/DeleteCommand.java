package seedu.zerotoone.logic.commands.workout;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.zerotoone.commons.core.Messages;
import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.CommandResult;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.logic.commands.util.Commands;
import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.workout.Workout;

/**
 * Deletes a workout identified using it's displayed index from the workout list.
 */
public class DeleteCommand extends WorkoutCommand {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = "Usage: " + Commands.WORKOUT_DELETE;
    public static final String MESSAGE_DELETE_WORKOUT_SUCCESS = "Successfully deleted workout: %1$s";
    private final Index workoutId;

    public DeleteCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.workoutId = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.isInSession()) {
            throw new CommandException(Command.MESSAGE_SESSION_STARTED);
        }

        List<Workout> lastShownList = model.getFilteredWorkoutList();

        if (workoutId.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        Workout workoutToDelete = lastShownList.get(workoutId.getZeroBased());
        model.deleteWorkout(workoutToDelete);
        model.deleteWorkoutNameFromSchedule(workoutToDelete.getWorkoutName());

        String outputMessage = String.format(MESSAGE_DELETE_WORKOUT_SUCCESS,
                workoutToDelete.getWorkoutName().toString());
        return new CommandResult(outputMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && workoutId.equals(((DeleteCommand) other).workoutId)); // state check
    }
}
