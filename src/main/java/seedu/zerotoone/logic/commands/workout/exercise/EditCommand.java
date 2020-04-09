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
 * Edits a specified exercise in a workout.
 */
public class EditCommand extends WorkoutExerciseCommand {
    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_USAGE = "Usage: workout exercise edit WORKOUT_ID EXERCISE_ID NEW_EXERCISE_ID";
    public static final String MESSAGE_EDIT_WORKOUT_SUCCESS = "Edited exercise in workout: %1$s";

    private Index workoutId;
    private Index exerciseId;
    private Index newExerciseId;

    /**
     * @param workoutId of the workout in the filtered workout list to edit
     * @param exerciseId of the exercise in the workout exercise list to edit
     * @param newExerciseId of the new exercise in the filtered exercise list to add
     */
    public EditCommand(Index workoutId, Index exerciseId, Index newExerciseId) {
        requireNonNull(workoutId);
        requireNonNull(exerciseId);
        requireNonNull(newExerciseId);

        this.workoutId = workoutId;
        this.exerciseId = exerciseId;
        this.newExerciseId = newExerciseId;
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

        List<Exercise> lastShownExerciseList = model.getFilteredExerciseList();
        if (newExerciseId.getZeroBased() >= lastShownExerciseList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        Workout workoutToEdit = lastShownWorkoutList.get(workoutId.getZeroBased());

        List<Exercise> updatedWorkoutExercises = new ArrayList<>(workoutToEdit.getWorkoutExercises());
        if (exerciseId.getZeroBased() >= updatedWorkoutExercises.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        Exercise updatedWorkoutExercise = lastShownExerciseList.get(newExerciseId.getZeroBased());
        updatedWorkoutExercises.remove(this.exerciseId.getZeroBased());
        updatedWorkoutExercises.add(this.exerciseId.getZeroBased(), updatedWorkoutExercise);

        Workout editedWorkout = new Workout(workoutToEdit.getWorkoutName(), updatedWorkoutExercises);

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
                && exerciseId.equals(otherCommand.exerciseId)
                && newExerciseId.equals(otherCommand.newExerciseId);
    }
}
