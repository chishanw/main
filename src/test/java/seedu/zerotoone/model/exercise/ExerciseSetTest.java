package seedu.zerotoone.model.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ExerciseSetTest {
    @Test
    public void constructor_nullWeight_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new ExerciseSet(null, new NumReps("1")));
    }

    @Test
    public void constructor_nullNumReps_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new ExerciseSet(new Weight("1"), null));
    }

    @Test
    public void getWeight() {
        ExerciseSet setOneOne = new ExerciseSet(
                new Weight("1"), new NumReps("1"));
        Weight expectedWeight = new Weight("1");
        assertEquals(expectedWeight, setOneOne.getWeight());
    }

    @Test
    public void getNumReps() {
        ExerciseSet setOneOne = new ExerciseSet(
                new Weight("1"), new NumReps("1"));
        NumReps expectedNumReps = new NumReps("1");
        assertEquals(expectedNumReps, setOneOne.getNumReps());
    }

    @Test
    public void equals() {
        ExerciseSet setOneOne = new ExerciseSet(
                new Weight("1"), new NumReps("1"));
        ExerciseSet setTwoOne = new ExerciseSet(
                new Weight("2"), new NumReps("1"));
        ExerciseSet setOneTwo = new ExerciseSet(
                new Weight("1"), new NumReps("2"));

        // same obj
        assertTrue(setOneOne.equals(setOneOne));

        // same value
        ExerciseSet setOneOneCopy = new ExerciseSet(
                new Weight("1"), new NumReps("1"));
        assertTrue(setOneOne.equals(setOneOneCopy));

        // null value
        assertFalse(setOneOne.equals(null));

        // invalid type
        assertFalse(setOneOne.equals(1));

        // different value -> returns false
        assertFalse(setOneOne.equals(setOneTwo));
        assertFalse(setOneOne.equals(setTwoOne));
    }
}
