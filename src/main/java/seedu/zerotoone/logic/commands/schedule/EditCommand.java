package seedu.zerotoone.logic.commands.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.zerotoone.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.zerotoone.commons.core.Messages;
import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.CommandResult;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.schedule.DateTime;
import seedu.zerotoone.model.schedule.OneTimeSchedule;
import seedu.zerotoone.model.schedule.Schedule;
import seedu.zerotoone.model.schedule.ScheduledWorkout;

/**
 * Edits the details of an existing schedule in the schedule list.
 */
public class EditCommand extends ScheduleCommand {
    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_USAGE = "Usage: schedule edit SCHEDULED_WORKOUT_ID d/<datetime>";
    public static final String MESSAGE_EDIT_SCHEDULE_SUCCESS = "Edited schedule: %1$s";
    public static final String MESSAGE_DUPLICATE_SCHEDULE = "This schedule already exists.";
    private static final String MESSAGE_DATETIME_IN_THE_PAST = "Datetime provided is in the past";

    private final Index scheduledWorkoutId;
    private final DateTime dateTime;

    /**
     * @param scheduledWorkoutId of the schedule in the filtered schedule list to edit
     * @param dateTime details to edit the schedule with
     */
    public EditCommand(Index scheduledWorkoutId, DateTime dateTime) {
        requireAllNonNull(scheduledWorkoutId, dateTime);

        this.scheduledWorkoutId = scheduledWorkoutId;
        this.dateTime = dateTime;
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
        List<ScheduledWorkout> lastShownList = model.getSortedScheduledWorkoutList();
        if (scheduledWorkoutId.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        ScheduledWorkout selectedScheduledWorkout = lastShownList.get(scheduledWorkoutId.getZeroBased());
        Schedule scheduleToEdit = selectedScheduledWorkout.getSchedule();
        // STEPH_TODO: differentiate OneTimeSchedule from RecurringSchedule
        Schedule editedSchedule = new OneTimeSchedule(scheduleToEdit.getWorkoutToSchedule(), dateTime);

        if (!scheduleToEdit.isSameSchedule(editedSchedule) && model.hasSchedule(editedSchedule)) {
            throw new CommandException(MESSAGE_DUPLICATE_SCHEDULE);
        }

        model.setSchedule(scheduleToEdit, editedSchedule);
        return new CommandResult(String.format(
                MESSAGE_EDIT_SCHEDULE_SUCCESS,
                editedSchedule.toString()));
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
        return scheduledWorkoutId.equals(otherCommand.scheduledWorkoutId)
                && dateTime.equals(otherCommand.dateTime);
    }
}
