package seedu.zerotoone.testutil;
import java.util.ArrayList;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.zerotoone.commons.core.GuiSettings;
import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.exercise.Exercise;
import seedu.zerotoone.model.exercise.ReadOnlyExerciseList;
import seedu.zerotoone.model.log.ReadOnlyLogList;
import seedu.zerotoone.model.schedule.Schedule;
import seedu.zerotoone.model.schedule.ScheduleList;
import seedu.zerotoone.model.schedule.ScheduledWorkout;
import seedu.zerotoone.model.session.CompletedSet;
import seedu.zerotoone.model.session.CompletedWorkout;
import seedu.zerotoone.model.session.OngoingWorkout;
import seedu.zerotoone.model.session.ReadOnlyCompletedSetList;
import seedu.zerotoone.model.session.ReadOnlyOngoingSetList;
import seedu.zerotoone.model.session.ReadOnlyTimerList;
import seedu.zerotoone.model.userprefs.ReadOnlyUserPrefs;
import seedu.zerotoone.model.workout.ReadOnlyWorkoutList;
import seedu.zerotoone.model.workout.Workout;

public class ModelStub implements Model {
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    // -----------------------------------------------------------------------------------------
    // Session
    @Override
    public boolean isInSession() {
        return false;
    }

    @Override
    public OngoingWorkout startSession(Workout workout, LocalDateTime currentDateTime) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void stopSession(LocalDateTime currentDateTime) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Optional<OngoingWorkout> getCurrentWorkout() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public CompletedSet skip() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public CompletedSet done() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Boolean hasExerciseLeft() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyCompletedSetList getLastSet() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyTimerList getTimerList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void shutdownTimer() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getExerciseListFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setExerciseListFilePath(Path exerciseListFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addExercise(Exercise exercise) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setExerciseList(ReadOnlyExerciseList newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyExerciseList getExerciseList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasExercise(Exercise exercise) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteExercise(Exercise target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setExercise(Exercise target, Exercise editedExercise) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Exercise> getFilteredExerciseList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredExerciseList(Predicate<Exercise> predicate) {
        throw new AssertionError("This method should not be called.");
    }


    // -----------------------------------------------------------------------------------------
    // Workout
    @Override
    public Path getWorkoutListFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setWorkoutListFilePath(Path workoutListFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addWorkout(Workout workout) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setWorkoutList(ReadOnlyWorkoutList newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyWorkoutList getWorkoutList() {
        throw new AssertionError("This method should not be called.");
    }

    public boolean hasWorkout(Workout workout) {
        throw new AssertionError("This method should not be called.");
    }

    public void deleteWorkout(Workout target) {
        throw new AssertionError("This method should not be called.");
    }

    public void deleteExerciseFromWorkouts(Exercise exercise) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setWorkout(Workout target, Workout editedWorkout) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Workout> getFilteredWorkoutList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredWorkoutList(Predicate<Workout> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyOngoingSetList getOngoingSetList() {
        throw new AssertionError("This method should not be called.");
    }

    // -----------------------------------------------------------------------------------------
    // Schedule
    @Override
    public boolean hasSchedule(Schedule schedule) {
        throw new AssertionError("This method should not be called.");
    }

    public void addSchedule(Schedule schedule) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setSchedule(Schedule scheduleToEdit, Schedule editedSchedule) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteScheduledWorkout(ScheduledWorkout scheduledWorkoutToDelete) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<ScheduledWorkout> getSortedScheduledWorkoutList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ScheduleList getScheduleList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ArrayList<CompletedWorkout> getLogListCopyAsArrayList() {
        throw new AssertionError("This method should not be called.");
    }


    @Override
    public Optional<LocalDateTime> getStatisticsStartDateRange() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Optional<LocalDateTime> getStatisticsEndDateRange() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void populateSortedScheduledWorkoutList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyLogList getLogList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<CompletedWorkout> getFilteredLogList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredLogList(Predicate<CompletedWorkout> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setStatisticsDateRange(Optional<LocalDateTime> startRange, Optional<LocalDateTime> endRange) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getLogListFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteLog(int target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setLogListFilePath(Path logListFilePath) {
        throw new AssertionError("This method should not be called.");
    }
}