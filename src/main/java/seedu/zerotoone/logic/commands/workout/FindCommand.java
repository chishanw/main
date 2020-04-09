package seedu.zerotoone.logic.commands.workout;

import static java.util.Objects.requireNonNull;

import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.CommandResult;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.workout.PredicateFilterWorkoutName;
import seedu.zerotoone.model.workout.WorkoutName;

/**
 * Finds and lists all workouts in workout list whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends WorkoutCommand {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = "Usage: workout find e/<workout_name>";
    public static final String MESSAGE_WORKOUTS_LISTED_OVERVIEW = "%1$d workouts listed!";

    private final WorkoutName workoutName;
    public FindCommand(WorkoutName workoutName) {
        requireNonNull(workoutName);
        this.workoutName = workoutName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.isInSession()) {
            throw new CommandException(Command.MESSAGE_SESSION_STARTED);
        }
        PredicateFilterWorkoutName predicate = new PredicateFilterWorkoutName(workoutName.fullName);

        model.updateFilteredWorkoutList(predicate);

        String outputMessage = String.format(MESSAGE_WORKOUTS_LISTED_OVERVIEW,
                model.getFilteredWorkoutList().size());
        return new CommandResult(outputMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && workoutName.equals(((FindCommand) other).workoutName)); // state check
    }
}
