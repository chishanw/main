package seedu.zerotoone.logic.commands.util;

import java.util.ArrayList;

/**
 * Represents a command section for each feature.
 */
public class CommandSection {
    private String commandSectionName;
    private ArrayList<String> commandList;

    public CommandSection(String commandSectionName, String... commands) {
        this.commandSectionName = commandSectionName;
        this.commandList = new ArrayList<>();

        for (String command : commands) {
            this.commandList.add(command);
        }
    }

    public String getCommandSectionName() {
        return this.commandSectionName;
    }

    public ArrayList<String> getCommands() {
        return this.commandList;
    }

    @Override
    public String toString() {
        return this.commandSectionName;
    }
}
