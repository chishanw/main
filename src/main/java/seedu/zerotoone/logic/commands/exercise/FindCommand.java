package seedu.zerotoone.logic.commands.exercise;

import static java.util.Objects.requireNonNull;

import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.CommandResult;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.exercise.ExerciseName;
import seedu.zerotoone.model.exercise.PredicateFilterExerciseName;

/**
 * Finds and lists all exercises in exercise list whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends ExerciseCommand {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = "Usage: exercise find e/<exercise_name>";
    public static final String MESSAGE_EXERCISES_LISTED_OVERVIEW = "%1$d exercises listed!";

    private final ExerciseName exerciseName;
    public FindCommand(ExerciseName exerciseName) {
        requireNonNull(exerciseName);
        this.exerciseName = exerciseName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.isInSession()) {
            throw new CommandException(Command.MESSAGE_SESSION_STARTED);
        }

        PredicateFilterExerciseName predicate = new PredicateFilterExerciseName(exerciseName.fullName);

        model.updateFilteredExerciseList(predicate);

        String outputMessage = String.format(MESSAGE_EXERCISES_LISTED_OVERVIEW,
                model.getFilteredExerciseList().size());
        return new CommandResult(outputMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && exerciseName.equals(((FindCommand) other).exerciseName)); // state check
    }
}
