package seedu.zerotoone.logic.commands.exercise;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.CommandResult;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.logic.commands.util.Commands;
import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.exercise.Exercise;
import seedu.zerotoone.model.exercise.ExerciseName;
import seedu.zerotoone.model.exercise.ExerciseSet;

/**
 * Adds an exercise to the exercise list.
 */
public class CreateCommand extends ExerciseCommand {
    public static final String COMMAND_WORD = "create";
    public static final String MESSAGE_USAGE = "Usage: " + Commands.EXERCISE_CREATE;
    public static final String MESSAGE_SUCCESS = "New exercise added: %1$s";
    public static final String MESSAGE_DUPLICATE_EXERCISE = "This exercise already exists";

    private final ExerciseName exerciseName;
    private final List<ExerciseSet> exerciseSets;

    /**
     * Creates a CreateCommand to add the specified {@code Exercise}
     */
    public CreateCommand(ExerciseName exerciseName) {
        requireNonNull(exerciseName);
        this.exerciseName = exerciseName;
        this.exerciseSets = new ArrayList<>();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.isInSession()) {
            throw new CommandException(Command.MESSAGE_SESSION_STARTED);
        }

        Exercise exercise = new Exercise(this.exerciseName, this.exerciseSets);

        if (model.hasExercise(exercise)) {
            throw new CommandException(MESSAGE_DUPLICATE_EXERCISE);
        }

        model.addExercise(exercise);

        String outputMessage = String.format(MESSAGE_SUCCESS, exercise.getExerciseName().toString());
        return new CommandResult(outputMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CreateCommand // instanceof handles nulls
                && exerciseName.equals(((CreateCommand) other).exerciseName));
    }
}
