package seedu.zerotoone.logic.commands.workout;

import static java.util.Objects.requireNonNull;
import static seedu.zerotoone.model.Model.PREDICATE_SHOW_ALL_WORKOUTS;

import java.util.List;

import seedu.zerotoone.commons.core.Messages;
import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.CommandResult;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.workout.Workout;
import seedu.zerotoone.model.workout.WorkoutName;

/**
 * Edits the details of an existing workout in the workout list.
 */
public class EditCommand extends WorkoutCommand {
    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_USAGE = "Usage: workout edit WORKOUT_ID e/<workout_name>";
    public static final String MESSAGE_EDIT_WORKOUT_SUCCESS = "Edited workout: %1$s";
    public static final String MESSAGE_DUPLICATE_WORKOUT = "This workout already exists.";

    private final Index workoutId;
    private final WorkoutName workoutName;

    /**
     * @param workoutId of the workout in the filtered workout list to edit
     * @param workoutName details to edit the workout with
     */
    public EditCommand(Index workoutId, WorkoutName workoutName) {
        requireNonNull(workoutId);
        requireNonNull(workoutName);

        this.workoutId = workoutId;
        this.workoutName = workoutName;
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

        Workout workoutToEdit = lastShownList.get(workoutId.getZeroBased());
        WorkoutName updatedWorkoutName;
        if (this.workoutName != null) {
            updatedWorkoutName = new WorkoutName(this.workoutName.fullName);
        } else {
            updatedWorkoutName = new WorkoutName(workoutToEdit.getWorkoutName().fullName);
        }

        Workout editedWorkout = new Workout(updatedWorkoutName, workoutToEdit.getWorkoutExercises());
        if (model.hasWorkout(editedWorkout)) {
            throw new CommandException(MESSAGE_DUPLICATE_WORKOUT);
        }

        model.setWorkout(workoutToEdit, editedWorkout);
        model.updateFilteredWorkoutList(PREDICATE_SHOW_ALL_WORKOUTS);

        String outputMessage = String.format(MESSAGE_EDIT_WORKOUT_SUCCESS,
                editedWorkout.getWorkoutName().toString());
        return new CommandResult(outputMessage);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand otherCommand = (EditCommand) other;
        return workoutId.equals(otherCommand.workoutId)
                && workoutName.equals(otherCommand.workoutName);
    }
}
