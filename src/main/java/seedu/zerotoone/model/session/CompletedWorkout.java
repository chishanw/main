//@@author gb3h
package seedu.zerotoone.model.session;

import static seedu.zerotoone.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import seedu.zerotoone.model.workout.WorkoutName;

/**
 * Represents an immutable Log once a session is completed.
 */
public class CompletedWorkout {

    // Identity fields
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final WorkoutName workoutName;
    private final List<CompletedExercise> exercises;

    /**
     * Every field must be present and not null.
     */
    public CompletedWorkout(WorkoutName name, List<CompletedExercise> exercises, LocalDateTime start,
        LocalDateTime end) {
        requireAllNonNull(name, exercises, start, end);
        this.workoutName = name;
        this.startTime = start;
        this.endTime = end;
        this.exercises = exercises;
    }

    public WorkoutName getWorkoutName() {
        return this.workoutName;
    }

    public List<CompletedExercise> getExercises() {
        return Collections.unmodifiableList(exercises);
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }
}
