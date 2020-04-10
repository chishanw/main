package seedu.zerotoone.model.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class NumRepsTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NumReps(null));
    }

    @Test
    public void constructor_invalidNumReps_throwsIllegalArgumentException() {
        Exception exceptionThrown;
        String expectedMessage = NumReps.MESSAGE_CONSTRAINTS;

        // empty string
        exceptionThrown = assertThrows(IllegalArgumentException.class, () -> new NumReps(""));
        assertEquals(expectedMessage, exceptionThrown.getMessage());

        // spaces only
        exceptionThrown = assertThrows(IllegalArgumentException.class, () -> new NumReps(" "));
        assertEquals(expectedMessage, exceptionThrown.getMessage());

        // non-numeric
        exceptionThrown = assertThrows(IllegalArgumentException.class, () -> new NumReps("asdf"));
        assertEquals(expectedMessage, exceptionThrown.getMessage());

        // starts with zero
        exceptionThrown = assertThrows(IllegalArgumentException.class, () -> new NumReps("01"));
        assertEquals(expectedMessage, exceptionThrown.getMessage());

        // negative number
        exceptionThrown = assertThrows(IllegalArgumentException.class, () -> new NumReps("-1"));
        assertEquals(expectedMessage, exceptionThrown.getMessage());

        // has spaces
        exceptionThrown = assertThrows(IllegalArgumentException.class, () -> new NumReps(" 1 "));
        assertEquals(expectedMessage, exceptionThrown.getMessage());
    }

    @Test
    public void constructor_validNumReps_createsNumReps() {
        assertEquals("100", new NumReps("100").value);

        // large numbers
        assertEquals("124293842033123", new NumReps("124293842033123").value);
    }
}
