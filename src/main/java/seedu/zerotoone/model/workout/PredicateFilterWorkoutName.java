package seedu.zerotoone.model.workout;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.zerotoone.commons.util.StringUtil;

/**
 * Tests that a {@code Workout}'s {@code Name} matches any of the keywords given.
 */
public class PredicateFilterWorkoutName implements Predicate<Workout> {
    private final List<String> keywords;

    public PredicateFilterWorkoutName(String keyword) {
        this.keywords = Arrays.asList(keyword.split("\\s+"));
    }

    @Override
    public boolean test(Workout exercise) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsSubstringIgnoreCase(exercise.getWorkoutName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PredicateFilterWorkoutName // instanceof handles nulls
                && keywords.equals(((PredicateFilterWorkoutName) other).keywords)); // state check
    }

}
