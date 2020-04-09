package seedu.zerotoone.model.exercise;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zerotoone.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ExerciseNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ExerciseName(null));
    }

    @Test
    public void constructor_invalidExerciseName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new ExerciseName(""));
    }

    @Test
    public void isValidExerciseName() {
        // null Exercise Name
        assertThrows(NullPointerException.class, () -> ExerciseName.isValidExerciseName(null));

        // invalid Exercise Name
        assertFalse(ExerciseName.isValidExerciseName("")); // empty string
        assertFalse(ExerciseName.isValidExerciseName(" ")); // spaces only
        assertFalse(ExerciseName.isValidExerciseName("^")); // only non-alphanumeric characters
        assertFalse(ExerciseName.isValidExerciseName("deadlift*")); // contains non-alphanumeric characters
        assertFalse(ExerciseName.isValidExerciseName("2 directional"
                + "elbow circles")); // starts with non [a-zA-Z] characters

        // valid Exercise Name
        assertTrue(ExerciseName.isValidExerciseName("jumping jacks")); // alphabets only
        assertTrue(ExerciseName.isValidExerciseName("Bench Press")); // with capital letters
        assertTrue(ExerciseName.isValidExerciseName("Chest supported and seated cable row")); // long names
    }
}
