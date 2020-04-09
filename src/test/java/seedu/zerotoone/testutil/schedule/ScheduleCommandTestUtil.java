package seedu.zerotoone.testutil.schedule;

import static seedu.zerotoone.logic.parser.CliSyntax.PREFIX_DATETIME;

import seedu.zerotoone.testutil.CommandTestUtil;

/**
 * Contains helper methods for testing commands.
 */
public class ScheduleCommandTestUtil extends CommandTestUtil {

    public static final String VALID_DATETIME_JUNE = "2020-06-01 13:00";
    public static final String VALID_DATETIME_JULY = "2020-07-01 13:00";
    public static final String VALID_DATETIME_IN_THE_PAST = "2000-06-01 13:00";

    public static final String DATETIME_DESC_JUNE = " " + PREFIX_DATETIME + VALID_DATETIME_JUNE;
    public static final String DATETIME_DESC_JULY = " " + PREFIX_DATETIME + VALID_DATETIME_JULY;
    public static final String DATETIME_DESC_IN_THE_PAST = " " + PREFIX_DATETIME + VALID_DATETIME_IN_THE_PAST;

    public static final String INVALID_DATETIME = "2020-06-01 1300";

    public static final String INVALID_DATETIME_DESC = " " + PREFIX_DATETIME + INVALID_DATETIME;
}
