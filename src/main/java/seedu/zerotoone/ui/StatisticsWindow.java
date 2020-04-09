package seedu.zerotoone.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.zerotoone.commons.core.LogsCenter;
import seedu.zerotoone.model.log.StatisticsData;
import seedu.zerotoone.ui.util.DateViewUtil;
import seedu.zerotoone.ui.util.UiPart;

/**
 * Controller for a help page
 */
public class StatisticsWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(StatisticsWindow.class);
    private static final String FXML = "StatisticsWindow.fxml";

    @FXML
    private Label message;

    @FXML
    private Label totalWorkoutCount;
    @FXML
    private Label totalTime;
    @FXML
    private Label averageTimePerDay;
    @FXML
    private Label range;

    /**
     * Creates a new ReportWindow.
     *
     * @param root Stage to use as the root of the ReportWindow.
     */
    public StatisticsWindow(Stage root) {
        super(FXML, root);
        message.setText("DISPLAY SOME MEANINGFUL STATS HERE");
    }

    /**
     * Creates a new HelpWindow.
     */
    public StatisticsWindow() {
        this(new Stage());
    }

    /**
     * Shows the report window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show(StatisticsData statisticsData) {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();

        totalWorkoutCount.setText(String.format("Total Workout Count -> %d", statisticsData.getTotalWorkoutCount()));

        totalTime.setText(String.format("Total Time Spent -> %s",
            DateViewUtil.getPrettyDuration(statisticsData.getTotalTime())));
        averageTimePerDay.setText(String.format("Average Time Per Day -> %s",
            DateViewUtil.getPrettyDuration(statisticsData.getAverageTimePerDay())));


        range.setText(DateViewUtil.getPrettyDateRangeDateTime(
            statisticsData.getStartRange(), statisticsData.getEndRange()));
    }

    /**
     * Returns true if the report window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the report window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
