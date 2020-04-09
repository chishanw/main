package seedu.zerotoone.ui.views.help;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.zerotoone.logic.commands.util.CommandSection;
import seedu.zerotoone.ui.util.UiPart;

/**
 * Card to display command sections.
 */
public class CommandSectionCard extends UiPart<Region> {
    private static final String FXML = "help/CommandSectionCard.fxml";

    @FXML
    private HBox helpSectionCard;
    @FXML
    private Label sectionName;
    @FXML
    private VBox sectionCommands;

    public CommandSectionCard(CommandSection commandSection) {
        super(FXML);
        sectionName.setText(commandSection.getCommandSectionName());

        List<String> commands = commandSection.getCommands();
        for (int i = 0; i < commands.size(); i++) {
            String command = commands.get(i);
            CommandCard commandCard =
                    new CommandCard(command);
            this.sectionCommands.getChildren().add(commandCard.getRoot());
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandSectionCard)) {
            return false;
        }

        // state check
        CommandSectionCard card = (CommandSectionCard) other;
        return sectionName.getText().equals(card.sectionName.getText());
    }

}
