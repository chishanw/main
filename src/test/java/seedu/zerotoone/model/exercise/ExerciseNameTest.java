package seedu.zerotoone.model.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ExerciseNameTest {
    @Test
    public void constructor_nullValue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ExerciseName(null));
    }

    @Test
    public void constructor_invalidExerciseName_throwsIllegalArgumentException() {
        Exception exceptionThrown;
        String expectedMessage = ExerciseName.MESSAGE_CONSTRAINTS;

        // empty string
        exceptionThrown = assertThrows(IllegalArgumentException.class, () -> new ExerciseName(""));
        assertEquals(expectedMessage, exceptionThrown.getMessage());

        // spaces only
        exceptionThrown = assertThrows(IllegalArgumentException.class, () -> new ExerciseName(" "));
        assertEquals(expectedMessage, exceptionThrown.getMessage());

        // starts with non-alphabet
        exceptionThrown = assertThrows(IllegalArgumentException.class, () -> new ExerciseName("1exercise"));
        assertEquals(expectedMessage, exceptionThrown.getMessage());

        // has symbols
        exceptionThrown = assertThrows(IllegalArgumentException.class, () -> new ExerciseName("bench&press"));
        assertEquals(expectedMessage, exceptionThrown.getMessage());

        // has spaces
        exceptionThrown = assertThrows(IllegalArgumentException.class, () -> new ExerciseName(" exercise "));
        assertEquals(expectedMessage, exceptionThrown.getMessage());
    }

    @Test
    public void constructor_validExerciseName_createsExerciseName() {
        assertEquals("bench press", new ExerciseName("bench press").fullName);

        // has numbers
        assertEquals("P90X", new ExerciseName("P90X").fullName);

        // has numbers
        assertEquals("some very long exercise name",
                new ExerciseName("some very long exercise name").fullName);
    }

    @Test
    public void equals() {
        ExerciseName benchPress = new ExerciseName("Bench Press");
        ExerciseName deadlift = new ExerciseName("Deadlift");

        // same obj
        assertTrue(benchPress.equals(benchPress));

        // same value
        ExerciseName benchPressCopy = new ExerciseName("Bench Press");
        assertTrue(benchPress.equals(benchPressCopy));

        // null value
        assertFalse(benchPress.equals(null));

        // invalid type
        assertFalse(benchPress.equals(1));

        // different value -> returns false
        assertFalse(benchPress.equals(deadlift));
    }
}
