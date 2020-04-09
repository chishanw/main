package seedu.zerotoone.model.workout;

import java.util.function.Predicate;

import seedu.zerotoone.commons.util.StringUtil;

/**
 * Tests that a {@code Workout}'s {@code Name} matches any of the keywords given.
 */
public class PredicateFilterWorkoutName implements Predicate<Workout> {
    private final String keyword;

    public PredicateFilterWorkoutName(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Workout workout) {
        return StringUtil.containsSubstringIgnoreCase(workout.getWorkoutName().fullName, keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PredicateFilterWorkoutName // instanceof handles nulls
                && keyword.equals(((PredicateFilterWorkoutName) other).keyword)); // state check
    }

}
