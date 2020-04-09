package seedu.zerotoone.logic.commands.schedule;

import static java.util.Objects.requireNonNull;

import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.CommandResult;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.model.Model;

/**
 * STEPH_TODO_JAVADOC
 */
public class ListCommand extends ScheduleCommand {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_SUCCESS = "Listed all scheduled workouts";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.isInSession()) {
            throw new CommandException(Command.MESSAGE_SESSION_STARTED);
        }

        model.populateSortedScheduledWorkoutList();

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
