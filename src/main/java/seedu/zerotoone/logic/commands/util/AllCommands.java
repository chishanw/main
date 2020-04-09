package seedu.zerotoone.logic.commands.util;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents all the commands in ZeroToOne.
 */
public class AllCommands {
    private ObservableList<CommandSection> commandList;

    public AllCommands() {
        List<CommandSection> commands = new ArrayList<>();
        commands.add(new CommandSection(
            "Session Commands",
            Commands.START,
            Commands.STOP,
            Commands.DONE,
            Commands.SKIP,
            Commands.HELP,
            Commands.EXIT
        ));
        commands.add(new CommandSection(
            "Exercise Commands",
            Commands.EXERCISE_LIST,
            Commands.EXERCISE_CREATE,
            Commands.EXERCISE_EDIT,
            Commands.EXERCISE_DELETE,
            Commands.EXERCISE_FIND,
            Commands.EXERCISE_SET_ADD,
            Commands.EXERCISE_SET_EDIT,
            Commands.EXERCISE_SET_DELETE
        ));
        commands.add(new CommandSection(
            "Workout Commands",
            Commands.WORKOUT_LIST,
            Commands.WORKOUT_CREATE,
            Commands.WORKOUT_EDIT,
            Commands.WORKOUT_DELETE,
            Commands.WORKOUT_FIND,
            Commands.WORKOUT_EXERCISE_ADD,
            Commands.WORKOUT_EXERCISE_EDIT,
            Commands.WORKOUT_EXERCISE_DELETE
        ));
        commands.add(new CommandSection(
            "Schedule Commands",
            Commands.SCHEDULE_LIST,
            Commands.SCHEDULE_CREATE,
            Commands.SCHEDULE_EDIT,
            Commands.SCHEDULE_DELETE,
            Commands.SCHEDULE_RECURRING_CREATE
        ));
        commands.add(new CommandSection(
            "Log Commands",
            Commands.LOG_LIST,
            Commands.LOG_DELETE,
            Commands.LOG_FIND,
            Commands.LOG_DISPLAY
        ));

        commandList = FXCollections.observableList(commands);
    }

    public ObservableList<CommandSection> getCommandList() {
        return this.commandList;
    }
}
