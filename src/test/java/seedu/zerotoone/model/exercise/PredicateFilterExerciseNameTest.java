package seedu.zerotoone.model.exercise;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.testutil.exercise.ExerciseBuilder;

public class PredicateFilterExerciseNameTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "firstPredicate";
        String secondPredicateKeyword = "secondPredicate";

        PredicateFilterExerciseName firstPredicate = new PredicateFilterExerciseName(firstPredicateKeyword);
        PredicateFilterExerciseName secondPredicate = new PredicateFilterExerciseName(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PredicateFilterExerciseName firstPredicateCopy = new PredicateFilterExerciseName(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different exercise -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsSubstring_returnsTrue() {
        // One word substring
        PredicateFilterExerciseName predicate = new PredicateFilterExerciseName("Bench");
        assertTrue(predicate.test(new ExerciseBuilder().withExerciseName("Bench Press").build()));

        // Full word Substring
        predicate = new PredicateFilterExerciseName("Bench Press");
        assertTrue(predicate.test(new ExerciseBuilder().withExerciseName("Bench Press").build()));

        // Mixed-case substrings
        predicate = new PredicateFilterExerciseName("bEnCh");
        assertTrue(predicate.test(new ExerciseBuilder().withExerciseName("Bench Press").build()));

        // Partial substring
        predicate = new PredicateFilterExerciseName("ben");
        assertTrue(predicate.test(new ExerciseBuilder().withExerciseName("Bench Press").build()));
    }

    @Test
    public void test_nameDoesNotContainSubstring_returnsFalse() {
        // Non-matching substring
        PredicateFilterExerciseName predicate = new PredicateFilterExerciseName("Bench");
        assertFalse(predicate.test(new ExerciseBuilder().withExerciseName("Deadlift").build()));
    }
}
