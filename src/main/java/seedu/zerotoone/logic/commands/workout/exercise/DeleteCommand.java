package seedu.zerotoone.logic.commands.workout.exercise;

import static java.util.Objects.requireNonNull;
import static seedu.zerotoone.model.workout.WorkoutModel.PREDICATE_SHOW_ALL_WORKOUTS;

import java.util.ArrayList;
import java.util.List;

import seedu.zerotoone.commons.core.Messages;
import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.CommandResult;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.exercise.Exercise;
import seedu.zerotoone.model.workout.Workout;

/**
 * Deletes a workout exercise identified using its displayed index from the workout exercise list.
 */
public class DeleteCommand extends WorkoutExerciseCommand {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = "Usage: workout exercise delete WORKOUT_ID EXERCISE_ID";
    public static final String MESSAGE_DELETE_WORKOUT_EXERCISE_SUCCESS = "Deleted workout exercise %1$s from %1$s";

    private final Index workoutId;
    private final Index exerciseId;

    /**
     * @param workoutId of the workout in the filtered workout list to edit
     * @param exerciseId of the exercise in the workout exercise list to delete
     */
    public DeleteCommand(Index workoutId, Index exerciseId) {
        requireNonNull(workoutId);
        requireNonNull(exerciseId);

        this.workoutId = workoutId;
        this.exerciseId = exerciseId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.isInSession()) {
            throw new CommandException(Command.MESSAGE_SESSION_STARTED);
        }

        List<Workout> lastShownWorkoutList = model.getFilteredWorkoutList();

        if (workoutId.getZeroBased() >= lastShownWorkoutList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        Workout workoutToEdit = lastShownWorkoutList.get(workoutId.getZeroBased());

        List<Exercise> updatedWorkoutExercises = new ArrayList<>(workoutToEdit.getWorkoutExercises());
        Exercise exerciseToDelete = updatedWorkoutExercises.get(exerciseId.getZeroBased());
        updatedWorkoutExercises.remove(exerciseId.getZeroBased());

        Workout editedWorkout = new Workout(workoutToEdit.getWorkoutName(), updatedWorkoutExercises);

        model.setWorkout(workoutToEdit, editedWorkout);
        model.updateFilteredWorkoutList(PREDICATE_SHOW_ALL_WORKOUTS);

        String outputMessage = String.format(
                MESSAGE_DELETE_WORKOUT_EXERCISE_SUCCESS,
                exerciseToDelete.getExerciseName(),
                editedWorkout.getWorkoutName());
        return new CommandResult(outputMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && workoutId.equals(((DeleteCommand) other).workoutId)
                && exerciseId.equals(((DeleteCommand) other).exerciseId)); // state check
    }
}
