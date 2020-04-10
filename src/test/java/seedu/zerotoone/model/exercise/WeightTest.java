package seedu.zerotoone.model.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class WeightTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Weight(null));
    }

    @Test
    public void constructor_invalidWeight_throwsIllegalArgumentException() {
        Exception exceptionThrown;
        String expectedMessage = Weight.MESSAGE_CONSTRAINTS;

        // empty string
        exceptionThrown = assertThrows(IllegalArgumentException.class, () -> new Weight(""));
        assertEquals(expectedMessage, exceptionThrown.getMessage());

        // spaces only
        exceptionThrown = assertThrows(IllegalArgumentException.class, () -> new Weight(" "));
        assertEquals(expectedMessage, exceptionThrown.getMessage());

        // non-numeric
        exceptionThrown = assertThrows(IllegalArgumentException.class, () -> new Weight("asdf"));
        assertEquals(expectedMessage, exceptionThrown.getMessage());

        // starts with zero
        exceptionThrown = assertThrows(IllegalArgumentException.class, () -> new Weight("01"));
        assertEquals(expectedMessage, exceptionThrown.getMessage());

        // more than 3 digits
        exceptionThrown = assertThrows(IllegalArgumentException.class, () -> new Weight("1000"));
        assertEquals(expectedMessage, exceptionThrown.getMessage());

        // negative number
        exceptionThrown = assertThrows(IllegalArgumentException.class, () -> new Weight("-1"));
        assertEquals(expectedMessage, exceptionThrown.getMessage());

        // has spaces
        exceptionThrown = assertThrows(IllegalArgumentException.class, () -> new Weight(" 1 "));
        assertEquals(expectedMessage, exceptionThrown.getMessage());
    }

    @Test
    public void constructor_validWeight_createsWeight() {
        assertEquals("100", new Weight("100").value);

        // max value
        assertEquals("999", new Weight("999").value);
    }
}
