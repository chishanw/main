package seedu.zerotoone.model;

import static java.util.Objects.requireNonNull;
import static seedu.zerotoone.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.zerotoone.commons.core.LogsCenter;
import seedu.zerotoone.model.exercise.Exercise;
import seedu.zerotoone.model.exercise.ExerciseList;
import seedu.zerotoone.model.exercise.ReadOnlyExerciseList;
import seedu.zerotoone.model.log.LogList;
import seedu.zerotoone.model.log.ReadOnlyLogList;
import seedu.zerotoone.model.schedule.Schedule;
import seedu.zerotoone.model.schedule.ScheduleList;
import seedu.zerotoone.model.schedule.ScheduledWorkout;
import seedu.zerotoone.model.schedule.Scheduler;
import seedu.zerotoone.model.session.CompletedSet;
import seedu.zerotoone.model.session.CompletedSetList;
import seedu.zerotoone.model.session.CompletedWorkout;
import seedu.zerotoone.model.session.OngoingSetList;
import seedu.zerotoone.model.session.OngoingWorkout;
import seedu.zerotoone.model.session.ReadOnlyCompletedSetList;
import seedu.zerotoone.model.session.ReadOnlyOngoingSetList;
import seedu.zerotoone.model.session.ReadOnlyTimerList;
import seedu.zerotoone.model.session.TimerList;
import seedu.zerotoone.model.session.exceptions.NoCurrentSessionException;
import seedu.zerotoone.model.userprefs.ReadOnlyUserPrefs;
import seedu.zerotoone.model.userprefs.UserPrefs;
import seedu.zerotoone.model.workout.ReadOnlyWorkoutList;
import seedu.zerotoone.model.workout.Workout;
import seedu.zerotoone.model.workout.WorkoutList;
import seedu.zerotoone.model.workout.WorkoutName;


/**
 * Represents the in-memory model of the exercise list data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private static final long UPDATE_INTERVAL = 100; // in ms

    private final UserPrefs userPrefs;

    // Exercise
    private final ExerciseList exerciseList;
    private final FilteredList<Exercise> filteredExercises;

    // Workout
    private final WorkoutList workoutList;
    private final FilteredList<Workout> filteredWorkouts;

    // Session
    private Optional<OngoingWorkout> currentWorkout;
    private final OngoingSetList ongoingSetList;
    private final CompletedSetList lastSet;
    private final TimerList timerList;
    private Timer timer;
    private long start;

    // Schedule
    private final Scheduler scheduler;

    // Log
    private final LogList logList;
    private final FilteredList<CompletedWorkout> filteredLogList;
    private Optional<LocalDateTime> statisticsStartRange;
    private Optional<LocalDateTime> statisticsEndStartRange;

    /**
     * Initializes a ModelManager with the given exerciseList and userPrefs.
     */
    public ModelManager(ReadOnlyUserPrefs userPrefs,
                        ReadOnlyExerciseList exerciseList,
                        ReadOnlyWorkoutList workoutList,
                        ScheduleList scheduleList,
                        ReadOnlyLogList logList) {
        super();
        requireAllNonNull(userPrefs,
                exerciseList,
                workoutList,
                scheduleList,
                logList);

        logger.fine("Initializing with user prefs " + userPrefs);

        this.userPrefs = new UserPrefs(userPrefs);

        this.exerciseList = new ExerciseList(exerciseList);
        filteredExercises = new FilteredList<>(this.exerciseList.getExerciseList());

        this.workoutList = new WorkoutList(workoutList);
        filteredWorkouts = new FilteredList<>(this.workoutList.getWorkoutList());

        this.scheduler = new Scheduler(scheduleList);

        // Init session variables
        this.currentWorkout = Optional.empty();
        this.ongoingSetList = new OngoingSetList();
        this.lastSet = new CompletedSetList();

        // Init timer stuff
        this.timerList = new TimerList();
        this.start = 0;
        this.timer = new Timer();
        List<Integer> data = new LinkedList<>();
        data.add(0);
        timerList.setSessionList(data);

        this.logList = new LogList(logList);
        filteredLogList = new FilteredList<>(this.logList.getLogList());

        statisticsStartRange = Optional.empty();
        statisticsEndStartRange = Optional.empty();
    }

    public ModelManager() {
        this(new UserPrefs(), new ExerciseList(), new WorkoutList(), new ScheduleList(), new LogList());
    }

    // -----------------------------------------------------------------------------------------
    // Common - User Preferences
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    // -----------------------------------------------------------------------------------------
    // Exercise List
    @Override
    public Path getExerciseListFilePath() {
        return userPrefs.getExerciseListFilePath();
    }

    @Override
    public void setExerciseListFilePath(Path exerciseListFilePath) {
        requireNonNull(exerciseListFilePath);
        userPrefs.setExerciseListFilePath(exerciseListFilePath);
    }

    @Override
    public void setExerciseList(ReadOnlyExerciseList exerciseList) {
        this.exerciseList.resetData(exerciseList);
    }

    @Override
    public ReadOnlyExerciseList getExerciseList() {
        return exerciseList;
    }

    @Override
    public boolean hasExercise(Exercise exercise) {
        requireNonNull(exercise);
        return exerciseList.hasExercise(exercise);
    }

    @Override
    public void deleteExercise(Exercise target) {
        exerciseList.removeExercise(target);
    }

    @Override
    public void addExercise(Exercise exercise) {
        exerciseList.addExercise(exercise);
        updateFilteredExerciseList(PREDICATE_SHOW_ALL_EXERCISES);
    }

    @Override
    public void setExercise(Exercise target, Exercise editedExercise) {
        requireAllNonNull(target, editedExercise);
        exerciseList.setExercise(target, editedExercise);
    }

    @Override
    public ObservableList<Exercise> getFilteredExerciseList() {
        return filteredExercises;
    }

    @Override
    public void updateFilteredExerciseList(Predicate<Exercise> predicate) {
        requireNonNull(predicate);
        filteredExercises.setPredicate(PREDICATE_SHOW_NONE);
        filteredExercises.setPredicate(predicate);
    }

    // -----------------------------------------------------------------------------------------
    // Log List

    @Override
    public Path getLogListFilePath() {
        return userPrefs.getLogListFilePath();
    }

    @Override
    public void deleteLog(int targetId) {
        logList.removeCompletedWorkout(targetId);
    }

    @Override
    public void setLogListFilePath(Path logListFilePath) {
        requireNonNull(logListFilePath);
        userPrefs.setLogListFilePath(logListFilePath);
    }

    @Override
    public ObservableList<CompletedWorkout> getFilteredLogList() {
        return filteredLogList;
    }


    @Override
    public void updateFilteredLogList(Predicate<CompletedWorkout> predicate) {
        requireNonNull(predicate);
        filteredLogList.setPredicate(PREDICATE_SHOW_NONE);
        filteredLogList.setPredicate(predicate);
    }

    @Override
    public void setStatisticsDateRange(Optional<LocalDateTime> startRange, Optional<LocalDateTime> endRange) {

        this.statisticsStartRange = startRange;
        this.statisticsEndStartRange = endRange;

    }

    @Override
    public ReadOnlyLogList getLogList() {
        return logList;
    }

    @Override
    public ArrayList<CompletedWorkout> getLogListCopyAsArrayList() {
        return new ArrayList<>(this.getLogList().getLogList());
    }

    @Override
    public Optional<LocalDateTime> getStatisticsStartDateRange() {
        return statisticsStartRange;
    }

    @Override
    public Optional<LocalDateTime> getStatisticsEndDateRange() {
        return statisticsEndStartRange;
    }

    // -----------------------------------------------------------------------------------------
    // Session List

    @Override
    public boolean isInSession() {
        return this.currentWorkout.isPresent();
    }

    @Override
    public OngoingWorkout startSession(Workout workoutToStart, LocalDateTime currentDateTime) {
        OngoingWorkout ongoingWorkout = new OngoingWorkout(workoutToStart, currentDateTime);
        this.currentWorkout = Optional.of(ongoingWorkout);
        this.ongoingSetList.setSessionList(ongoingWorkout.getRemainingSets());
        this.start = System.currentTimeMillis();

        TimerTask runner = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        long diff = System.currentTimeMillis() - start;
                        List<Integer> data = new LinkedList<>();
                        data.add(Math.toIntExact(diff));
                        timerList.setSessionList(data);
                    }
                });
            }
        };

        this.timer.schedule(runner, 0, UPDATE_INTERVAL);
        return ongoingWorkout;
    }

    @Override
    public void stopSession(LocalDateTime currentDateTime) {
        OngoingWorkout ongoingWorkout = this.currentWorkout.orElseThrow(NoCurrentSessionException::new);
        CompletedWorkout workout = ongoingWorkout.finish(currentDateTime);
        this.logList.addCompletedWorkout(workout);
        this.currentWorkout = Optional.empty();

        // Gui stuff
        this.ongoingSetList.resetData(new OngoingSetList());
        this.lastSet.resetData(new CompletedSetList());

        // Timer stuff
        this.start = 0;
        this.timer.cancel();
        this.timer.purge();
        this.timer = new Timer();
        List<Integer> data = new LinkedList<>();
        data.add(0);
        timerList.setSessionList(data);
    }

    @Override
    public CompletedSet skip() {
        CompletedSet set = this.currentWorkout.orElseThrow(NoCurrentSessionException::new).skip();
        this.ongoingSetList.setSessionList(this.currentWorkout.get().getRemainingSets());
        List<CompletedSet> sets = new LinkedList<>();
        sets.add(set);
        this.lastSet.setSessionList(sets);

        // Timer stuff
        this.start = System.currentTimeMillis();
        return set;
    }

    @Override
    public CompletedSet done() {
        CompletedSet set = this.currentWorkout.orElseThrow(NoCurrentSessionException::new).done();
        this.ongoingSetList.setSessionList(this.currentWorkout.get().getRemainingSets());
        List<CompletedSet> sets = new LinkedList<>();
        sets.add(set);
        this.lastSet.setSessionList(sets);

        // Timer stuff
        this.start = System.currentTimeMillis();
        return set;
    }

    @Override
    public Boolean hasExerciseLeft() {
        return this.currentWorkout.orElseThrow(NoCurrentSessionException::new).hasExerciseLeft();
    }

    @Override
    public Optional<OngoingWorkout> getCurrentWorkout() {
        return Optional.ofNullable(this.currentWorkout.orElse(null));
    }

    @Override
    public ReadOnlyOngoingSetList getOngoingSetList() {
        return this.ongoingSetList;
    }

    @Override
    public ReadOnlyCompletedSetList getLastSet() {
        return this.lastSet;
    }

    @Override
    public ReadOnlyTimerList getTimerList() {
        return this.timerList;
    }

    @Override
    public void shutdownTimer() {
        this.timer.cancel();
        this.timer.purge();
    }
    // -----------------------------------------------------------------------------------------
    // Schedule
    @Override
    public ScheduleList getScheduleList() {
        return scheduler.getScheduleList();
    }

    @Override
    public void populateSortedScheduledWorkoutList() {
        scheduler.populateSortedScheduledWorkoutList();
    }

    @Override
    public void deleteWorkoutNameFromSchedule(WorkoutName workoutNameToDelete) {
        scheduler.deleteWorkoutNameFromSchedule(workoutNameToDelete);
    }

    public void editWorkoutNameInSchedule(WorkoutName workoutNameToEdit, WorkoutName editedWorkoutName) {
        scheduler.editWorkoutNameInSchedule(workoutNameToEdit, editedWorkoutName);
    }

    @Override
    public boolean hasSchedule(Schedule schedule) {
        return scheduler.hasSchedule(schedule);
    }

    @Override
    public void addSchedule(Schedule schedule) {
        scheduler.addSchedule(schedule);
    }

    @Override
    public void setSchedule(Schedule scheduleToEdit, Schedule editedSchedule) {
        scheduler.setSchedule(scheduleToEdit, editedSchedule);
    }

    @Override
    public void deleteScheduledWorkout(ScheduledWorkout scheduledWorkoutToDelete) {
        scheduler.deleteScheduledWorkout(scheduledWorkoutToDelete);
    }

    @Override
    public ObservableList<ScheduledWorkout> getSortedScheduledWorkoutList() {
        return scheduler.getSortedScheduledWorkoutList();
    }

    // -----------------------------------------------------------------------------------------
    // Workout List
    @Override
    public Path getWorkoutListFilePath() {
        return userPrefs.getWorkoutListFilePath();
    }

    @Override
    public void setWorkoutListFilePath(Path workoutListFilePath) {
        requireNonNull(workoutListFilePath);
        userPrefs.setWorkoutListFilePath(workoutListFilePath);
    }

    @Override
    public void setWorkoutList(ReadOnlyWorkoutList workoutList) {
        this.workoutList.resetData(workoutList);
    }

    @Override
    public ReadOnlyWorkoutList getWorkoutList() {
        return workoutList;
    }

    @Override
    public boolean hasWorkout(Workout workout) {
        requireNonNull(workout);
        return workoutList.hasWorkout(workout);
    }

    @Override
    public void deleteWorkout(Workout target) {
        workoutList.removeWorkout(target);
    }

    @Override
    public void deleteExerciseFromWorkouts(Exercise exercise) {
        workoutList.removeExerciseFromWorkouts(exercise);
    }

    @Override
    public void setExerciseInWorkouts(Exercise target, Exercise editedExercise) {
        workoutList.setExerciseInWorkouts(target, editedExercise);
    }

    @Override
    public void addWorkout(Workout workout) {
        workoutList.addWorkout(workout);
        updateFilteredWorkoutList(PREDICATE_SHOW_ALL_WORKOUTS);
    }

    @Override
    public void setWorkout(Workout target, Workout editedWorkout) {
        requireAllNonNull(target, editedWorkout);
        workoutList.setWorkout(target, editedWorkout);
    }

    @Override
    public ObservableList<Workout> getFilteredWorkoutList() {
        return filteredWorkouts;
    }

    @Override
    public void updateFilteredWorkoutList(Predicate<Workout> predicate) {
        requireNonNull(predicate);
        filteredWorkouts.setPredicate(PREDICATE_SHOW_NONE);
        filteredWorkouts.setPredicate(predicate);
    }

    // -----------------------------------------------------------------------------------------
    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return exerciseList.equals(other.exerciseList)
                && userPrefs.equals(other.userPrefs)
                && filteredExercises.equals(other.filteredExercises)
                && logList.equals(other.logList)
                && scheduler.equals(other.scheduler);
    }
}
