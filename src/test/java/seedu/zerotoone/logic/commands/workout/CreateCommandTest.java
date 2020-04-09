package seedu.zerotoone.logic.commands.workout;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zerotoone.testutil.Assert.assertThrows;
import static seedu.zerotoone.testutil.workout.TypicalWorkouts.ARMS_WORKOUT;
import static seedu.zerotoone.testutil.workout.TypicalWorkouts.LEGS_WORKOUT;
import static seedu.zerotoone.testutil.workout.WorkoutCommandTestUtil.VALID_WORKOUT_NAME_ABS_WORKOUT;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.zerotoone.commons.core.GuiSettings;
import seedu.zerotoone.logic.commands.CommandResult;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
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
import seedu.zerotoone.model.workout.WorkoutList;
import seedu.zerotoone.model.workout.WorkoutName;
import seedu.zerotoone.testutil.workout.WorkoutBuilder;


public class CreateCommandTest {

    @Test
    public void constructor_nullWorkout_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateCommand(null));
    }

    @Test
    public void execute_workoutAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingWorkoutAdded modelStub = new ModelStubAcceptingWorkoutAdded();
        CommandResult commandResult = new CreateCommand(new WorkoutName(VALID_WORKOUT_NAME_ABS_WORKOUT))
                .execute(modelStub);
        Workout absWorkout = new WorkoutBuilder().withWorkoutName(VALID_WORKOUT_NAME_ABS_WORKOUT).build();

        assertEquals(String.format(CreateCommand.MESSAGE_SUCCESS,
                VALID_WORKOUT_NAME_ABS_WORKOUT), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(absWorkout), modelStub.workoutsAdded);
    }

    @Test
    public void execute_duplicateWorkout_throwsCommandException() {
        Workout validWorkout = new WorkoutBuilder().build();
        CreateCommand createCommand = new CreateCommand(ARMS_WORKOUT.getWorkoutName());
        ModelStub modelStub = new ModelStubWithWorkout(validWorkout);

        assertThrows(
                CommandException.class, CreateCommand.MESSAGE_DUPLICATE_WORKOUT, () ->
                createCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        CreateCommand addLegsWorkoutCommand = new CreateCommand(LEGS_WORKOUT.getWorkoutName());
        CreateCommand addArmsWorkoutCommand = new CreateCommand(ARMS_WORKOUT.getWorkoutName());

        // same object -> returns true
        assertTrue(addLegsWorkoutCommand.equals(addLegsWorkoutCommand));

        // same values -> returns true
        CreateCommand addLegsWorkoutCommandCopy = new CreateCommand(LEGS_WORKOUT.getWorkoutName());
        assertTrue(addLegsWorkoutCommandCopy.equals(addLegsWorkoutCommand));

        // different types -> returns false
        assertFalse(addLegsWorkoutCommand.equals(1));

        // null -> returns false
        assertFalse(addLegsWorkoutCommand.equals(null));

        // different workout -> returns false
        assertFalse(addLegsWorkoutCommand.equals(addArmsWorkoutCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
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

        @Override
        public boolean hasWorkout(Workout workout) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteWorkout(Workout target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteExerciseFromWorkouts(Exercise target) {
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
        // Session
        @Override
        public boolean isInSession() {
            throw new AssertionError("This method should not be called.");
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
        public void populateSortedScheduledWorkoutList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyLogList getLogList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ArrayList<CompletedWorkout> getLogListCopyAsArrayList() {
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
        public Path getLogListFilePath() {
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
        public void deleteLog(int target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setLogListFilePath(Path logListFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStatisticsDateRange(Optional<LocalDateTime> startRange, Optional<LocalDateTime> endRange) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single exercise.
     */
    private class ModelStubWithWorkout extends ModelStub {
        private final Workout workout;

        ModelStubWithWorkout(Workout workout) {
            requireNonNull(workout);
            this.workout = workout;
        }

        @Override
        public boolean isInSession() {
            return false;
        }

        @Override
        public boolean hasWorkout(Workout workout) {
            requireNonNull(workout);
            return this.workout.isSameWorkout(workout);
        }
    }

    /**
     * A Model stub that always accept the exercise being added.
     */
    private class ModelStubAcceptingWorkoutAdded extends ModelStub {
        final ArrayList<Workout> workoutsAdded = new ArrayList<>();

        @Override
        public boolean isInSession() {
            return false;
        }

        @Override
        public boolean hasWorkout(Workout workout) {
            requireNonNull(workout);
            return workoutsAdded.stream().anyMatch(workout::isSameWorkout);
        }

        @Override
        public void addWorkout(Workout workout) {
            requireNonNull(workout);
            workoutsAdded.add(workout);
        }

        @Override
        public ReadOnlyWorkoutList getWorkoutList() {
            return new WorkoutList();
        }
    }

}
