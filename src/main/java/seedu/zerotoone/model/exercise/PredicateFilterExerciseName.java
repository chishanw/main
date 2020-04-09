package seedu.zerotoone.model.exercise;

import java.util.function.Predicate;

import seedu.zerotoone.commons.util.StringUtil;

/**
 * Tests that a {@code Exercise}'s {@code Name} matches any of the keywords given.
 */
public class PredicateFilterExerciseName implements Predicate<Exercise> {
    private final String keyword;

    public PredicateFilterExerciseName(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Exercise exercise) {
        return StringUtil.containsSubstringIgnoreCase(exercise.getExerciseName().fullName, keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PredicateFilterExerciseName // instanceof handles nulls
                && keyword.equals(((PredicateFilterExerciseName) other).keyword)); // state check
    }

}
