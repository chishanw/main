package seedu.zerotoone.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.zerotoone.commons.core.GuiSettings;
import seedu.zerotoone.logic.commands.CommandResult;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.logic.parser.exceptions.ParseException;
import seedu.zerotoone.model.exercise.Exercise;
import seedu.zerotoone.model.exercise.ReadOnlyExerciseList;
import seedu.zerotoone.model.schedule.ScheduledWorkout;
import seedu.zerotoone.model.session.CompletedSet;
import seedu.zerotoone.model.session.CompletedWorkout;
import seedu.zerotoone.model.session.OngoingSet;
import seedu.zerotoone.ui.util.ViewType;

/**
 * API of the Logic component
 */
public interface Logic extends WorkoutLogic, StatisticsLogic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Retrieves the view type of the command.
     * @param commandText The command as entered by the user.
     * @return the view type.
     * @throws ParseException If an error occurs during parsing.
     */
    ViewType getViewType(String commandText) throws ParseException;

    // -----------------------------------------------------------------------------------------
    // Common
    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    // -----------------------------------------------------------------------------------------
    // Exercise List
    /**
     * Returns the ExerciseList.
     *
     * @see seedu.zerotoone.model.Model#getExerciseList()
     */
    ReadOnlyExerciseList getExerciseList();

    /** Returns an unmodifiable view of the filtered list of exercises */
    ObservableList<Exercise> getFilteredExerciseList();

    /**
     * Returns the user prefs' exercise list file path.
     */
    Path getExerciseListFilePath();

    // -----------------------------------------------------------------------------------------
    // Session List
    /**
     * Returns the SessionList.
     *
     * @see seedu.zerotoone.model.Model#getLogList()
     */
    ObservableList<CompletedWorkout> getLogList();

    /**
     * Returns the OngoingSessionList.
     *
     * @see seedu.zerotoone.model.Model#getOngoingSetList()
     */
    ObservableList<OngoingSet> getOngoingSetList();

    ObservableList<CompletedSet> getLastSet();

    ObservableList<Integer> getTimerList();

    /** Returns an unmodifiable view of the filtered list of workouts.
     */
    ObservableList<CompletedWorkout> getFilteredLogList();

    /**
     * Returns the user prefs' session list file path.
     */
    Path getLogListFilePath();

    // -----------------------------------------------------------------------------------------
    // Schedule List
    ObservableList<ScheduledWorkout> getSortedScheduledWorkoutList();

    void showdownTimer();
}
