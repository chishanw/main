package seedu.zerotoone.model.exercise;

import static java.util.Objects.requireNonNull;
import static seedu.zerotoone.commons.util.AppUtil.checkArgument;

/**
 * Represents a Set's number of repetitions in ZeroToOne.
 * Guarantees: immutable; is valid as declared in {@link #isValidNumReps(String)}
 */
public class NumReps {

    public static final String MESSAGE_CONSTRAINTS = "Number of repetitions should be a non-negative number.";

    /*
     * The first character of the number of repetitions must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[1-9][0-9]*";

    public final String value;

    /**
     * Constructs an {@code NumReps}.
     *
     * @param numReps A valid number of repetitions.
     */
    public NumReps(String numReps) {
        requireNonNull(numReps);
        checkArgument(isValidNumReps(numReps), MESSAGE_CONSTRAINTS);
        value = numReps;
    }

    /**
     * Returns true if a given string is a valid number of repetitions.
     */
    public static boolean isValidNumReps(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NumReps // instanceof handles nulls
                && value.equals(((NumReps) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
