package seedu.zerotoone.logic.commands.exercise.set;

import static java.util.Objects.requireNonNull;
import static seedu.zerotoone.model.Model.PREDICATE_SHOW_ALL_EXERCISES;
import static seedu.zerotoone.model.Model.PREDICATE_SHOW_ALL_WORKOUTS;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import seedu.zerotoone.commons.core.LogsCenter;
import seedu.zerotoone.commons.core.Messages;
import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.CommandResult;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.logic.commands.util.Commands;
import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.exercise.Exercise;
import seedu.zerotoone.model.exercise.ExerciseSet;
import seedu.zerotoone.model.exercise.NumReps;
import seedu.zerotoone.model.exercise.Weight;

/**
 * Edits the details of an existing exercise in the exercise list.
 */
public class AddCommand extends SetCommand {
    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = "Usage: " + Commands.EXERCISE_SET_ADD;
    public static final String MESSAGE_EDIT_EXERCISE_SUCCESS = "Added exercise set: %1$s";

    private final Index exerciseId;
    private final NumReps numReps;
    private final Weight weight;
    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * @param index of the exercise in the filtered exercise list to edit
     * @param exerciseSet details to edit the exercise with
     */
    public AddCommand(Index exerciseId, NumReps numReps, Weight weight) {
        requireNonNull(exerciseId);
        requireNonNull(numReps);
        requireNonNull(weight);

        this.exerciseId = exerciseId;
        this.numReps = numReps;
        this.weight = weight;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        logger.fine(String.format("Executing %s with %s, %s and %s",
                getClass().getSimpleName(), exerciseId, numReps, weight));

        requireNonNull(model);
        if (model.isInSession()) {
            throw new CommandException(Command.MESSAGE_SESSION_STARTED);
        }

        List<Exercise> lastShownList = model.getFilteredExerciseList();
        if (exerciseId.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        Exercise exerciseToEdit = lastShownList.get(exerciseId.getZeroBased());
        List<ExerciseSet> updatedExerciseSets = new ArrayList<>(exerciseToEdit.getExerciseSets());

        ExerciseSet exerciseSetToAdd = new ExerciseSet(weight, numReps);
        updatedExerciseSets.add(exerciseSetToAdd);
        Exercise editedExercise = new Exercise(exerciseToEdit.getExerciseName(), updatedExerciseSets);

        model.setExercise(exerciseToEdit, editedExercise);
        model.setExerciseInWorkouts(exerciseToEdit, editedExercise);
        model.updateFilteredExerciseList(PREDICATE_SHOW_ALL_EXERCISES);
        model.updateFilteredWorkoutList(PREDICATE_SHOW_ALL_WORKOUTS);

        String outputMessage = String.format(MESSAGE_EDIT_EXERCISE_SUCCESS, editedExercise);
        return new CommandResult(outputMessage);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof AddCommand)) {
            return false;
        }

        // state check
        AddCommand otherCommand = (AddCommand) other;
        return exerciseId.equals(otherCommand.exerciseId)
                && numReps.equals(otherCommand.numReps)
                && weight.equals(otherCommand.weight);
    }
}
