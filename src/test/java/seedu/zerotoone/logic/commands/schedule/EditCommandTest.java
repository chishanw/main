package seedu.zerotoone.logic.commands.schedule;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zerotoone.testutil.Assert.assertThrows;
import static seedu.zerotoone.testutil.CommandTestUtil.assertCommandSuccess;
import static seedu.zerotoone.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;
import static seedu.zerotoone.testutil.TypicalIndexes.INDEX_SECOND_OBJECT;
import static seedu.zerotoone.testutil.exercise.TypicalExercises.getTypicalExerciseList;
import static seedu.zerotoone.testutil.schedule.ScheduleCommandTestUtil.VALID_DATETIME_IN_THE_PAST;
import static seedu.zerotoone.testutil.schedule.ScheduleCommandTestUtil.VALID_DATETIME_JULY;
import static seedu.zerotoone.testutil.schedule.ScheduleCommandTestUtil.VALID_DATETIME_JUNE;
import static seedu.zerotoone.testutil.workout.TypicalWorkouts.getTypicalWorkoutList;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.zerotoone.commons.core.GuiSettings;
import seedu.zerotoone.commons.core.Messages;
import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.ModelManager;
import seedu.zerotoone.model.exercise.Exercise;
import seedu.zerotoone.model.exercise.ReadOnlyExerciseList;
import seedu.zerotoone.model.log.LogList;
import seedu.zerotoone.model.log.ReadOnlyLogList;
import seedu.zerotoone.model.schedule.DateTime;
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
import seedu.zerotoone.model.userprefs.UserPrefs;
import seedu.zerotoone.model.workout.ReadOnlyWorkoutList;
import seedu.zerotoone.model.workout.Workout;
import seedu.zerotoone.model.workout.WorkoutList;
import seedu.zerotoone.testutil.schedule.OneTimeScheduleBuilder;

class EditCommandTest {

    private Model model = new ModelManager(new UserPrefs(),
            getTypicalExerciseList(),
            getTypicalWorkoutList(),
            new ScheduleList(),
            new LogList());

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditCommand(null, null));
    }

    @Test
    public void execute_modelInSession_throwsCommandException() {
        DateTime dateTime = new DateTime(VALID_DATETIME_JUNE);
        EditCommand editCommand = new EditCommand(INDEX_FIRST_OBJECT, dateTime);
        Model model = new ModelStubInSession();

        assertThrows(CommandException.class,
                Command.MESSAGE_SESSION_STARTED, () -> editCommand.execute(model));
    }

    @Test
    public void execute_scheduleAcceptedByModel_editSuccessful() {
        Workout workout = model.getFilteredWorkoutList().get(0);

        DateTime juneDateTime = new DateTime(VALID_DATETIME_JUNE);
        Schedule juneSchedule = new OneTimeScheduleBuilder().withWorkout(workout).withDateTime(juneDateTime).build();
        Model modelWithJuneSchedule = new ModelManager(model.getUserPrefs(),
                model.getExerciseList(),
                model.getWorkoutList(),
                new ScheduleList(model.getScheduleList()),
                model.getLogList());
        modelWithJuneSchedule.addSchedule(juneSchedule);

        DateTime julyDateTime = new DateTime(VALID_DATETIME_JULY); // TODO
        Schedule julySchedule = new OneTimeScheduleBuilder().withWorkout(workout).withDateTime(julyDateTime).build();
        Model modelWithJulySchedule = new ModelManager(model.getUserPrefs(),
                model.getExerciseList(),
                model.getWorkoutList(),
                new ScheduleList(model.getScheduleList()),
                model.getLogList());
        modelWithJulySchedule.addSchedule(julySchedule);


        EditCommand editCommand = new EditCommand(INDEX_FIRST_OBJECT, julyDateTime);
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_SCHEDULE_SUCCESS, julySchedule);

        assertCommandSuccess(editCommand, modelWithJuneSchedule, expectedMessage, modelWithJulySchedule);
    }

    @Test
    public void execute_datetimeInThePast_throwsCommandException() {
        DateTime dateTime = new DateTime(VALID_DATETIME_IN_THE_PAST);
        EditCommand editCommand = new EditCommand(INDEX_FIRST_OBJECT, dateTime);

        assertThrows(CommandException.class,
                EditCommand.MESSAGE_DATETIME_IN_THE_PAST, () -> editCommand.execute(model));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        DateTime dateTime = new DateTime(VALID_DATETIME_JUNE);
        EditCommand editCommand = new EditCommand(INDEX_FIRST_OBJECT, dateTime);

        Model modelWithNoWorkout = new ModelManager(model.getUserPrefs(),
                model.getExerciseList(),
                new WorkoutList(),
                model.getScheduleList(),
                model.getLogList());

        assertThrows(CommandException.class,
                Messages.MESSAGE_INVALID_INDEX, () -> editCommand.execute(modelWithNoWorkout));
    }

    @Test
    public void execute_duplicateSchedule_throwsCommandException() {
        Workout workout = model.getFilteredWorkoutList().get(0);

        DateTime juneDateTime = new DateTime(VALID_DATETIME_JUNE);
        Schedule juneSchedule = new OneTimeScheduleBuilder().withWorkout(workout).withDateTime(juneDateTime).build();

        DateTime julyDateTime = new DateTime(VALID_DATETIME_JULY);
        Schedule julySchedule = new OneTimeScheduleBuilder().withWorkout(workout).withDateTime(julyDateTime).build();
        Model modelWithJulySchedule = new ModelManager(model.getUserPrefs(),
                model.getExerciseList(),
                model.getWorkoutList(),
                new ScheduleList(model.getScheduleList()),
                model.getLogList());

        modelWithJulySchedule.addSchedule(juneSchedule);
        modelWithJulySchedule.addSchedule(julySchedule);

        EditCommand editCommand = new EditCommand(INDEX_FIRST_OBJECT, julyDateTime);

        assertThrows(CommandException.class,
                EditCommand.MESSAGE_DUPLICATE_SCHEDULE, () -> editCommand.execute(modelWithJulySchedule));
    }

    @Test
    public void equals() {
        DateTime dateTime = new DateTime(VALID_DATETIME_JUNE);
        EditCommand addFirstWorkoutCommand = new EditCommand(INDEX_FIRST_OBJECT, dateTime);
        EditCommand addSecondWorkoutCommand = new EditCommand(INDEX_SECOND_OBJECT, dateTime);

        // same object -> returns true
        assertTrue(addFirstWorkoutCommand.equals(addFirstWorkoutCommand));

        // same values -> returns true
        EditCommand addFirstWorkoutCommandCopy = new EditCommand(INDEX_FIRST_OBJECT, dateTime);
        assertTrue(addFirstWorkoutCommand.equals(addFirstWorkoutCommandCopy));

        // different types -> returns false
        assertFalse(addFirstWorkoutCommand.equals(1));

        // null -> returns false
        assertFalse(addFirstWorkoutCommand.equals(null));

        // different index -> returns false
        assertFalse(addFirstWorkoutCommand.equals(addSecondWorkoutCommand));
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

        public boolean hasWorkout(Workout workout) {
            throw new AssertionError("This method should not be called.");
        }

        public void deleteWorkout(Workout target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
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

        // -----------------------------------------------------------------------------------------
        // Log
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
            return Optional.empty();
        }

        @Override
        public Optional<LocalDateTime> getStatisticsEndDateRange() {
            return Optional.empty();
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
     * A Model stub that contains a single schedule.
     */
    private class ModelStubWithSchedule extends EditCommandTest.ModelStub {
        private final Schedule schedule;

        ModelStubWithSchedule(Schedule schedule) {
            requireNonNull(schedule);
            this.schedule = schedule;
        }

        @Override
        public boolean hasSchedule(Schedule schedule) {
            requireNonNull(schedule);
            return this.schedule.isSameSchedule(schedule);
        }

        // @Override
        // public ObservableList<ScheduledWorkout> getSortedScheduledWorkoutList() {
        //     return model.getFilteredWorkoutList();
        // }
    }

    /**
     * A Model stub that is always in session.
     */
    private class ModelStubInSession extends ModelStub {
        @Override
        public boolean isInSession() {
            return true;
        }
    }
}
