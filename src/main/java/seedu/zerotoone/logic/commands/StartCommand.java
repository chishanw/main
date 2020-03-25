package seedu.zerotoone.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

import seedu.zerotoone.commons.core.Messages;
import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.exercise.Exercise;

/**
 * Deletes a exercise identified using it's displayed index from the exercise list.
 */
public class StartCommand extends Command {
    public static final String COMMAND_WORD = "start";
    public static final String MESSAGE_USAGE = "Usage: start EXERCISE_ID";
    public static final String MESSAGE_START_EXERCISE_SUCCESS = "Started Exercise: %1$s at ";
    public static final String MESSAGE_IN_SESSION = "There is a workout session already in progress!";
    private final Index exerciseId;
    private final FormatStyle formatStyle = FormatStyle.MEDIUM;

    public StartCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.exerciseId = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Exercise> lastShownList = model.getFilteredExerciseList();

        if (exerciseId.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        Exercise exerciseToStart = lastShownList.get(exerciseId.getZeroBased());

        if (model.isInSession()) {
            throw new CommandException((MESSAGE_IN_SESSION));
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        model.startSession(exerciseToStart, currentDateTime);

        String formatted = currentDateTime.format(DateTimeFormatter.ofLocalizedDateTime(this.formatStyle));

        String outputMessage = String.format(MESSAGE_START_EXERCISE_SUCCESS,
                exerciseToStart.getExerciseName().toString()) + formatted;
        return new CommandResult(outputMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StartCommand // instanceof handles nulls
                && exerciseId.equals(((StartCommand) other).exerciseId)); // state check
    }
}
