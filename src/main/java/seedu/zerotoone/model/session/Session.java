package seedu.zerotoone.model.session;

import static seedu.zerotoone.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import seedu.zerotoone.model.exercise.Exercise;
import seedu.zerotoone.model.exercise.ExerciseName;
import seedu.zerotoone.model.exercise.ExerciseSet;


/**
 * Represents a single Session.
 */
public class Session {

    // Identity fields
    private final LocalDateTime startTime;
    private Optional<LocalDateTime> endTime;
    private final ExerciseName exerciseName;
    private final Queue<ExerciseSet> exerciseQueue = new LinkedList<>();
    private final List<ExerciseSet> doneExercises = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public Session(Exercise exercise, LocalDateTime startTime) {
        requireAllNonNull(exercise);
        this.exerciseName = exercise.getExerciseName();
        this.exerciseQueue.addAll(exercise.getExerciseSets());
        this.startTime = startTime;
        this.endTime = Optional.empty();
    }

    public void finish(LocalDateTime endTime) {
        this.endTime = Optional.of(endTime);
    }
}
