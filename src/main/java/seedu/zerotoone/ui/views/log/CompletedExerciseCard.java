package seedu.zerotoone.ui.views.log;

import static seedu.zerotoone.ui.util.DateViewUtil.getPrettyTimeDifference;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.zerotoone.model.session.CompletedExercise;
import seedu.zerotoone.model.session.CompletedSet;
import seedu.zerotoone.ui.util.UiPart;

/**
 * An UI component that displays information of a {@code CompletedExercise}.
 */
public class CompletedExerciseCard extends UiPart<Region> {
    private static final String FXML = "log/CompletedExerciseCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on ExerciseList level 4</a>
     */

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label exerciseTitle;
    @FXML
    private VBox exerciseSets;

    public CompletedExerciseCard(CompletedExercise completedExercise, int displayedIndex) {
        super(FXML);
        exerciseTitle.setText(formatTitle(completedExercise, displayedIndex));

        List<CompletedSet> exerciseSetsList = completedExercise.getSets();
        for (int i = 0; i < exerciseSetsList.size(); i++) {
            CompletedSet completedSet = exerciseSetsList.get(i);
            CompletedSetCard completedSetCard =
                new CompletedSetCard(i, completedSet.getNumReps().value, completedSet.getWeight().value,
                    completedSet.isFinished());
            this.exerciseSets.getChildren().add(completedSetCard.getRoot());
        }
    }

    private String formatTitle(CompletedExercise completedExercise, int displayIndex) {
        return String.format("Exercise #%d: %s (%s)", displayIndex, completedExercise.getExerciseName().fullName,
            getPrettyTimeDifference(completedExercise.getStartTime(), completedExercise.getEndTime()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CompletedExerciseCard)) {
            return false;
        }

        // state check
        CompletedExerciseCard card = (CompletedExerciseCard) other;
        return card.exerciseTitle.equals(exerciseTitle);
    }
}
