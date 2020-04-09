package seedu.zerotoone.logic.parser;

import seedu.zerotoone.logic.parser.util.Prefix;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {
    public static final Prefix PREFIX_EXERCISE_NAME = new Prefix("e/");
    public static final Prefix PREFIX_WORKOUT_NAME = new Prefix("w/");
    public static final Prefix PREFIX_NUM_OF_SETS = new Prefix("s/");
    public static final Prefix PREFIX_NUM_OF_REPS = new Prefix("r/");
    public static final Prefix PREFIX_WEIGHT = new Prefix("m/");
    public static final Prefix PREFIX_DATETIME = new Prefix("d/");
    public static final Prefix PREFIX_FREQUENCY = new Prefix("f/");
    public static final Prefix PREFIX_FILEPATH = new Prefix("p/");
    public static final Prefix PREFIX_LOG_START = new Prefix("st/");
    public static final Prefix PREFIX_LOG_END = new Prefix("et/");
}
