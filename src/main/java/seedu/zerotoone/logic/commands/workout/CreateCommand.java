package seedu.zerotoone.logic.commands.workout;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.CommandResult;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.logic.commands.util.Commands;
import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.exercise.Exercise;
import seedu.zerotoone.model.workout.Workout;
import seedu.zerotoone.model.workout.WorkoutName;

/**
 * Adds an workout to the workout list.
 */
public class CreateCommand extends WorkoutCommand {
    public static final String COMMAND_WORD = "create";
    public static final String MESSAGE_USAGE = "Usage: " + Commands.WORKOUT_CREATE;
    public static final String MESSAGE_SUCCESS = "New workout successfully added: %1$s";
    public static final String MESSAGE_DUPLICATE_WORKOUT = "Sorry, this workout already exists. Try again!";

    private final WorkoutName workoutName;
    private final List<Exercise> workoutExercises;

    /**
     * Creates a CreateCommand to add the specified {@code Workout}
     */
    public CreateCommand(WorkoutName workoutName) {
        requireNonNull(workoutName);
        this.workoutName = workoutName;
        this.workoutExercises = new ArrayList<>();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.isInSession()) {
            throw new CommandException(Command.MESSAGE_SESSION_STARTED);
        }

        Workout workout = new Workout(this.workoutName, this.workoutExercises);

        if (model.hasWorkout(workout)) {
            throw new CommandException(MESSAGE_DUPLICATE_WORKOUT);
        }

        model.addWorkout(workout);

        String outputMessage = String.format(MESSAGE_SUCCESS, workout.getWorkoutName().toString());
        return new CommandResult(outputMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CreateCommand // instanceof handles nulls
                && workoutName.equals(((CreateCommand) other).workoutName));
    }
}
