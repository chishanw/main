package seedu.zerotoone.ui.views.help;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.zerotoone.ui.util.UiPart;

/**
 * Card that displays each command.
 */
public class CommandCard extends UiPart<Region> {
    private static final String FXML = "help/CommandCard.fxml";

    @FXML
    private HBox commandCard;

    @FXML
    private Label command;

    public CommandCard(String command) {
        super(FXML);
        this.command.setText(command);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandCard)) {
            return false;
        }

        // state check
        CommandCard card = (CommandCard) other;
        return command.getText().equals(card.command.getText());
    }
}
