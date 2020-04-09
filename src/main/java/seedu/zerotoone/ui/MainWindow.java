package seedu.zerotoone.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.zerotoone.commons.core.GuiSettings;
import seedu.zerotoone.commons.core.LogsCenter;
import seedu.zerotoone.logic.Logic;
import seedu.zerotoone.logic.commands.CommandResult;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.logic.commands.util.AllCommands;
import seedu.zerotoone.logic.parser.exceptions.ParseException;
import seedu.zerotoone.ui.util.UiPart;
import seedu.zerotoone.ui.util.ViewType;
import seedu.zerotoone.ui.views.exercise.ExerciseListPanel;
import seedu.zerotoone.ui.views.help.HelpDisplay;
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
    private HelpDisplay helpDisplay;

    @FXML
    private VBox tabsVBox;
    @FXML
    private StackPane commandBoxPlaceholder;
    @FXML
    private StackPane helpDisplayPlaceholder;
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

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());
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
    void start() {
        primaryStage.show();

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

        helpDisplay = new HelpDisplay(new AllCommands().getCommandList());
        helpDisplayPlaceholder.getChildren().add(helpDisplay.getRoot());

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
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
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
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        logic.showdownTimer();
        statisticsWindow.hide();
        primaryStage.hide();
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
