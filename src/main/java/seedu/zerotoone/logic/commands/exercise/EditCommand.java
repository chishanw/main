package seedu.zerotoone.logic.commands.exercise;

import static java.util.Objects.requireNonNull;
import static seedu.zerotoone.model.Model.PREDICATE_SHOW_ALL_EXERCISES;
import static seedu.zerotoone.model.Model.PREDICATE_SHOW_ALL_WORKOUTS;

import java.util.List;

import seedu.zerotoone.commons.core.Messages;
import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.CommandResult;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.logic.commands.util.Commands;
import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.exercise.Exercise;
import seedu.zerotoone.model.exercise.ExerciseName;

/**
 * Edits the details of an existing exercise in the exercise list.
 */
public class EditCommand extends ExerciseCommand {
    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_USAGE = "Usage: " + Commands.EXERCISE_EDIT;
    public static final String MESSAGE_EDIT_EXERCISE_SUCCESS = "Edited exercise: %1$s";
    public static final String MESSAGE_DUPLICATE_EXERCISE = "This exercise already exists.";

    private final Index exerciseId;
    private final ExerciseName exerciseName;

    /**
     * @param exerciseId of the exercise in the filtered exercise list to edit
     * @param exerciseName details to edit the exercise with
     */
    public EditCommand(Index exerciseId, ExerciseName exerciseName) {
        requireNonNull(exerciseId);
        requireNonNull(exerciseName);

        this.exerciseId = exerciseId;
        this.exerciseName = exerciseName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.isInSession()) {
            throw new CommandException(Command.MESSAGE_SESSION_STARTED);
        }

        List<Exercise> lastShownList = model.getFilteredExerciseList();
        if (exerciseId.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        Exercise exerciseToEdit = lastShownList.get(exerciseId.getZeroBased());
        ExerciseName updatedExerciseName;
        if (this.exerciseName != null) {
            updatedExerciseName = new ExerciseName(this.exerciseName.fullName);
        } else {
            updatedExerciseName = new ExerciseName(exerciseToEdit.getExerciseName().fullName);
        }

        Exercise editedExercise = new Exercise(updatedExerciseName, exerciseToEdit.getExerciseSets());
        if (model.hasExercise(editedExercise)) {
            throw new CommandException(MESSAGE_DUPLICATE_EXERCISE);
        }

        model.setExercise(exerciseToEdit, editedExercise);
        model.setExerciseInWorkouts(exerciseToEdit, editedExercise);
        model.updateFilteredExerciseList(PREDICATE_SHOW_ALL_EXERCISES);
        model.updateFilteredWorkoutList(PREDICATE_SHOW_ALL_WORKOUTS);

        String outputMessage = String.format(MESSAGE_EDIT_EXERCISE_SUCCESS,
                editedExercise.getExerciseName().toString());
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
        return exerciseId.equals(otherCommand.exerciseId)
                && exerciseName.equals(otherCommand.exerciseName);
    }
}
