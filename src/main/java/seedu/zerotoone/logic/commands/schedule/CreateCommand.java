package seedu.zerotoone.logic.commands.schedule;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.zerotoone.commons.core.Messages;
import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.CommandResult;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.schedule.DateTime;
import seedu.zerotoone.model.schedule.OneTimeSchedule;
import seedu.zerotoone.model.workout.Workout;

/**
 * STEPH_TODO_JAVADOC
 */
public class CreateCommand extends ScheduleCommand {
    public static final String COMMAND_WORD = "create";
    public static final String MESSAGE_USAGE = "Usage: schedule create WORKOUT_ID d/<dateTime>";
    public static final String MESSAGE_SUCCESS = "New schedule added: %1$s";
    public static final String MESSAGE_DUPLICATE_SCHEDULE = "This schedule already exists";
    public static final String MESSAGE_DATETIME_IN_THE_PAST = "Datetime provided is in the past";

    private final Index workoutId;
    private final DateTime dateTime;

    /**
     * STEPH_TODO_JAVADOC
     */
    public CreateCommand(Index workoutId, DateTime dateTIme) {
        requireNonNull(workoutId);
        requireNonNull(dateTIme);
        this.workoutId = workoutId;
        this.dateTime = dateTIme;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (model.isInSession()) {
            throw new CommandException(Command.MESSAGE_SESSION_STARTED);
        }

        if (!DateTime.isDateEqualOrLaterThanToday(dateTime)) {
            throw new CommandException(MESSAGE_DATETIME_IN_THE_PAST);
        }

        requireNonNull(model);
        List<Workout> lastShownList = model.getFilteredWorkoutList();

        if (workoutId.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        Workout workoutToSchedule = lastShownList.get(workoutId.getZeroBased());
        OneTimeSchedule schedule = new OneTimeSchedule(workoutToSchedule, dateTime);
        if (model.hasSchedule(schedule)) {
            throw new CommandException(MESSAGE_DUPLICATE_SCHEDULE);
        }

        model.addSchedule(schedule);

        return new CommandResult(String.format(MESSAGE_SUCCESS, schedule.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this; // short circuit if same object
    }
}
