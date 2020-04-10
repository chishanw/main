package seedu.zerotoone.storage.exercise.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.commons.exceptions.IllegalValueException;
import seedu.zerotoone.model.exercise.ExerciseSet;
import seedu.zerotoone.model.exercise.NumReps;
import seedu.zerotoone.model.exercise.Weight;

public class JacksonExerciseSetTest {
    @Test
    public void toModelType_nullWeight_throwIllegalValueException() {
        Exception exceptionThrown = assertThrows(IllegalValueException.class, () ->
                new JacksonExerciseSet(null, "20").toModelType());
        String expectedMessage = String.format(JacksonExerciseSet.MISSING_FIELD_MESSAGE_FORMAT,
                Weight.class.getSimpleName());
        assertEquals(exceptionThrown.getMessage(), expectedMessage);
    }

    @Test
    public void toModelType_invalidWeight_throwIllegalValueException() {
        Exception exceptionThrown = assertThrows(IllegalValueException.class, () ->
                new JacksonExerciseSet("01", "20").toModelType());
        String expectedMessage = Weight.MESSAGE_CONSTRAINTS;
        assertEquals(exceptionThrown.getMessage(), expectedMessage);
    }

    @Test
    public void toModelType_nullNumReps_throwIllegalValueException() {
        Exception exceptionThrown = assertThrows(IllegalValueException.class, () ->
                new JacksonExerciseSet("10", null).toModelType());
        String expectedMessage = String.format(JacksonExerciseSet.MISSING_FIELD_MESSAGE_FORMAT,
                NumReps.class.getSimpleName());
        assertEquals(exceptionThrown.getMessage(), expectedMessage);
    }

    @Test
    public void toModelType_invalidNumReps_throwIllegalValueException() {
        Exception exceptionThrown = assertThrows(IllegalValueException.class, () ->
                new JacksonExerciseSet("10", "01").toModelType());
        String expectedMessage = NumReps.MESSAGE_CONSTRAINTS;
        assertEquals(exceptionThrown.getMessage(), expectedMessage);
    }

    @Test
    public void toModelType_validExerciseSetDetails_returnsExerciseSet() throws IllegalValueException {
        ExerciseSet expectedExerciseSet = new ExerciseSet(new Weight("10"),
                new NumReps("20"));
        assertEquals(expectedExerciseSet,
                new JacksonExerciseSet("10", "20").toModelType());
    }

    @Test
    public void toModelType_validExerciseSet_returnsExerciseSet() throws IllegalValueException {
        ExerciseSet expectedExerciseSet = new ExerciseSet(new Weight("10"),
                new NumReps("20"));
        assertEquals(expectedExerciseSet,
                new JacksonExerciseSet(expectedExerciseSet).toModelType());
    }
}
