package seedu.zerotoone.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seedu.zerotoone.commons.core.LogsCenter;
import seedu.zerotoone.model.log.StatisticsData;
import seedu.zerotoone.model.session.CompletedWorkout;
import seedu.zerotoone.ui.util.DateViewUtil;
import seedu.zerotoone.ui.util.UiPart;

/**
 * Controller for a help page
 */
public class StatisticsWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(StatisticsWindow.class);
    private static final String FXML = "StatisticsWindow.fxml";

    @FXML
    private Label totalWorkoutCount;
    @FXML
    private Label totalTime;
    @FXML
    private Label averageTimePerDay;
    @FXML
    private Text statisticsSubTitle;

    @FXML
    private VBox lineChartContainer;

    /**
     * Creates a new ReportWindow.
     *
     * @param root Stage to use as the root of the ReportWindow.
     */
    public StatisticsWindow(Stage root) {
        super(FXML, root);
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

        totalWorkoutCount.setText(statisticsData.getTotalWorkoutCount() + " workouts");

        totalTime.setText(DateViewUtil.getPrettyDuration(statisticsData.getTotalTime()));
        averageTimePerDay.setText(DateViewUtil.getPrettyDuration(statisticsData.getAverageTimePerDay()));


        statisticsSubTitle.setText(
            String.format("Statistics generated from %s to %s.",
                DateViewUtil.getPrettyDateTime(statisticsData.getStartRange()),
                DateViewUtil.getPrettyDateTime(statisticsData.getEndRange())));


        // Populate Line Chart

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Day number");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Workout Time in minutes");

        LineChart lineChart = new LineChart(xAxis, yAxis);

        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName("ZeroToOne Progress");

        int dayCount = 1;
        for (CompletedWorkout workout : statisticsData.getWorkouts()) {
            dataSeries.getData().add(new XYChart.Data(dayCount++,
                DateViewUtil.getDurationInMinutes(workout.getStartTime(), workout.getEndTime())));
        }

        lineChart.getData().add(dataSeries);
        lineChartContainer.getChildren().add(lineChart);
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
