package seedu.zerotoone.storage.exercise.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.zerotoone.storage.exercise.util.JacksonExercise.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.zerotoone.testutil.Assert.assertThrows;
import static seedu.zerotoone.testutil.exercise.TypicalExercises.BENCH_PRESS;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.commons.exceptions.IllegalValueException;
import seedu.zerotoone.model.exercise.ExerciseName;
import seedu.zerotoone.model.exercise.ExerciseSet;

public class JacksonExerciseTest {
    private static final String INVALID_EXERCISE_NAME = "R@chel";

    private static final List<JacksonExerciseSet> VALID_EXERCISE_SETS = BENCH_PRESS.getExerciseSets().stream()
            .map(JacksonExerciseSet::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validExercise_returnsExercise() throws Exception {
        JacksonExercise exercise = new JacksonExercise(BENCH_PRESS);
        assertEquals(BENCH_PRESS, exercise.toModelType());
    }

    @Test
    public void toModelType_validExerciseDetails_returnsExercise() throws Exception {
        List<JacksonExerciseSet> exerciseSets = new ArrayList<>();
        for (ExerciseSet exerciseSet : BENCH_PRESS.getExerciseSets()) {
            exerciseSets.add(new JacksonExerciseSet(exerciseSet));
        }
        JacksonExercise exercise = new JacksonExercise(
                BENCH_PRESS.getExerciseName().fullName, exerciseSets);
        assertEquals(BENCH_PRESS, exercise.toModelType());
    }

    @Test
    public void toModelType_invalidExerciseName_throwsIllegalValueException() {
        JacksonExercise exercise = new JacksonExercise(INVALID_EXERCISE_NAME, VALID_EXERCISE_SETS);
        String expectedMessage = ExerciseName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    }

    @Test
    public void toModelType_nullExerciseName_throwsIllegalValueException() {
        JacksonExercise exercise = new JacksonExercise(null, VALID_EXERCISE_SETS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ExerciseName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    }
}
