package seedu.zerotoone.ui;

import java.util.logging.Logger;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import seedu.zerotoone.commons.core.LogsCenter;
import seedu.zerotoone.logic.Logic;
import seedu.zerotoone.logic.commands.CommandResult;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.logic.commands.util.AllCommands;
import seedu.zerotoone.logic.parser.exceptions.ParseException;
import seedu.zerotoone.ui.util.UiPart;
import seedu.zerotoone.ui.util.ViewType;
import seedu.zerotoone.ui.views.about.AboutDisplay;
import seedu.zerotoone.ui.views.exercise.ExerciseListPanel;
import seedu.zerotoone.ui.views.home.HomePanel;
import seedu.zerotoone.ui.views.log.LogListPanel;
import seedu.zerotoone.ui.views.schedule.ScheduledWorkoutListPanel;
import seedu.zerotoone.ui.views.workout.WorkoutListPanel;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {
    private static final String FXML = "MainWindow.fxml";
    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private HomePanel homePanel;
    private ExerciseListPanel exerciseListPanel;
    private WorkoutListPanel workoutListPanel;
    private ScheduledWorkoutListPanel scheduledWorkoutListPanel;
    private LogListPanel logListPanel;
    private ResultDisplay resultDisplay;
    private StatisticsWindow statisticsWindow;
    private AboutDisplay aboutDisplay;

    @FXML
    private VBox tabsVBox;
    @FXML
    private StackPane commandBoxPlaceholder;
    @FXML
    private StackPane aboutDisplayPlaceholder;
    @FXML
    private StackPane resultDisplayPlaceholder;
    @FXML
    private TabPane tabPanePlaceHolder;
    @FXML
    private StackPane homeContentPlaceholder;
    @FXML
    private StackPane exerciseContentPlaceholder;
    @FXML
    private StackPane workoutContentPlaceholder;
    @FXML
    private StackPane scheduleContentPlaceholder;
    @FXML
    private StackPane logContentPlaceholder;

    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);
        this.primaryStage = primaryStage;
        this.logic = logic;

        statisticsWindow = new StatisticsWindow();
        tabPanePlaceHolder.widthProperty().addListener((observable, oldValue, newValue) -> {
            tabPanePlaceHolder.setTabMinWidth(newValue.doubleValue() / 6 - 6);
            tabPanePlaceHolder.setTabMinWidth(newValue.doubleValue() / 6 - 6);
        });
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Initialises the MainWindow
     */
    void init() {
        // Fills up all the placeholders of this window.
        homePanel = new HomePanel(logic.getOngoingSetList(), logic.getLastSet(), logic.getTimerList());
        homeContentPlaceholder.getChildren().add(homePanel.getRoot());

        exerciseListPanel = new ExerciseListPanel(logic.getFilteredExerciseList());
        exerciseContentPlaceholder.getChildren().add(exerciseListPanel.getRoot());

        workoutListPanel = new WorkoutListPanel(logic.getFilteredWorkoutList());
        workoutContentPlaceholder.getChildren().add(workoutListPanel.getRoot());

        scheduledWorkoutListPanel = new ScheduledWorkoutListPanel(logic.getSortedScheduledWorkoutList());
        scheduleContentPlaceholder.getChildren().add(scheduledWorkoutListPanel.getRoot());

        logListPanel = new LogListPanel(logic.getFilteredLogList());
        logContentPlaceholder.getChildren().add(logListPanel.getRoot());

        aboutDisplay = new AboutDisplay(new AllCommands().getCommandList());
        aboutDisplayPlaceholder.getChildren().add(aboutDisplay.getRoot());

        tabPanePlaceHolder.setMinWidth(530);
        tabPanePlaceHolder.setMinHeight(200);

        VBox.setVgrow(tabPanePlaceHolder, Priority.ALWAYS);

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        // Focus on command box
        commandBox.requestFocus();
    }

    /**
     * Starts the MainWindow
     */
    void start() {
        // Scale Width and Height based on Screen Resolution
        Rectangle2D screenBoundary = Screen.getPrimary().getVisualBounds();
        primaryStage.setWidth(screenBoundary.getWidth() / 1.5);
        primaryStage.setHeight(screenBoundary.getWidth() / 2);

        // Center Window
        primaryStage.setX((screenBoundary.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((screenBoundary.getHeight() - primaryStage.getHeight()) / 2);

        primaryStage.show();

        // Focus user context onto command box at startup
        commandBoxPlaceholder.setFocusTraversable(true);
    }

    /**
     * Opens the report window or focuses on it if it's already opened.
     */
    @FXML
    public void handleReport() {
        if (!statisticsWindow.isShowing()) {
            statisticsWindow.show(logic.generateStatistics());
        } else {
            statisticsWindow.focus();
        }
    }

    /**
     * Shows a view of the app information such as a list of all commands.
     */
    @FXML
    public void handleAbout() {
        tabPanePlaceHolder.getSelectionModel().select(5);
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        logic.showdownTimer();
        statisticsWindow.hide();
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> primaryStage.hide());
        pause.play();
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.zerotoone.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            this.switchViews(commandText);
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowAbout()) {
                handleAbout();
            }

            if (commandResult.isShowReport()) {
                handleReport();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

    /**
     * Switches the UI views based on the command.
     * @param commandText the full user input string.
     * @throws ParseException if the user input does not conform to command format.
     */
    private void switchViews(String commandText) throws ParseException {
        ViewType viewType = logic.getViewType(commandText);

        switch (viewType) {
        case SESSION_VIEW:
            tabPanePlaceHolder.getSelectionModel().select(0);
            break;
        case EXERCISE_VIEW:
            tabPanePlaceHolder.getSelectionModel().select(1);
            break;
        case WORKOUT_VIEW:
            tabPanePlaceHolder.getSelectionModel().select(2);
            break;
        case SCHEDULE_VIEW:
            tabPanePlaceHolder.getSelectionModel().select(3);
            break;
        case LOG_VIEW:
            tabPanePlaceHolder.getSelectionModel().select(4);
            break;
        default:
            tabPanePlaceHolder.getSelectionModel().select(0);
            break;
        }
    }
}
