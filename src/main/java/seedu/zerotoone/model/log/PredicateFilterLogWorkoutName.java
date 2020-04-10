package seedu.zerotoone.model.log;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.zerotoone.commons.util.StringUtil;
import seedu.zerotoone.model.session.CompletedWorkout;

/**
 * Tests that a {@code CompletedWorkout}'s {@code WorkoutName} matches any of the keywords given.
 */
public class PredicateFilterLogWorkoutName implements Predicate<CompletedWorkout> {
    private final List<String> keywords;

    public PredicateFilterLogWorkoutName(String keyword) {
        this.keywords = Arrays.asList(keyword.split("\\s+"));
    }

    @Override
    public boolean test(CompletedWorkout completedWorkout) {
        return keywords.stream().anyMatch(
            keyword -> StringUtil.containsWordIgnoreCase(completedWorkout.getWorkoutName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PredicateFilterLogWorkoutName // instanceof handles nulls
                && keywords.equals(((PredicateFilterLogWorkoutName) other).keywords)); // state check
    }

}
