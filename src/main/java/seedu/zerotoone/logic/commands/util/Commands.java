package seedu.zerotoone.logic.commands.util;

/**
 * Container for all ZeroToOne commands.
 */
public class Commands {
    public static final String ABOUT = "about";
    public static final String START = "start WORKOUT_ID";
    public static final String STOP = "stop";
    public static final String DONE = "done";
    public static final String SKIP = "skip";
    public static final String EXIT = "exit";

    public static final String EXERCISE_LIST = "exercise list";
    public static final String EXERCISE_CREATE = "exercise create e/<exercise_name>";
    public static final String EXERCISE_EDIT = "exercise edit EXERCISE_ID e/<exercise_name>";
    public static final String EXERCISE_DELETE = "exercise delete EXERCISE_ID";
    public static final String EXERCISE_FIND = "exercise find e/<exercise_name>";
    public static final String EXERCISE_SET_ADD = "exercise set add EXERCISE_ID r/<num_reps> m/<weight>";
    public static final String EXERCISE_SET_EDIT = "exercise set edit EXERCISE_ID SET_ID r/<num_reps> m/<weight>";
    public static final String EXERCISE_SET_DELETE = "exercise set delete EXERCISE_ID SET_ID";

    public static final String WORKOUT_LIST = "workout list";
    public static final String WORKOUT_CREATE = "workout create w/<workout_name>";
    public static final String WORKOUT_EDIT = "workout edit WORKOUT_ID w/<workout_name>";
    public static final String WORKOUT_DELETE = "workout delete WORKOUT_ID";
    public static final String WORKOUT_FIND = "workout find w/<workout_name>";
    public static final String WORKOUT_EXERCISE_ADD = "workout exercise add WORKOUT_ID EXERCISE_ID";
    public static final String WORKOUT_EXERCISE_EDIT = "workout exercise edit WORKOUT_ID EXERCISE_ID NEW_EXERCISE_ID";
    public static final String WORKOUT_EXERCISE_DELETE = "workout exercise delete WORKOUT_ID EXERCISE_ID";

    public static final String SCHEDULE_LIST = "schedule list";
    public static final String SCHEDULE_CREATE = "schedule create WORKOUT_ID d/<datetime>";
    public static final String SCHEDULE_EDIT = "schedule edit SCHEDULED_WORKOUT_ID d/<datetime>";
    public static final String SCHEDULE_DELETE = "schedule delete SCHEDULED_WORKOUT_ID";
    public static final String SCHEDULE_RECURRING_CREATE =
            "schedule recurring create WORKOUT_ID d/<datetime> f/<frequency>";

    public static final String LOG_LIST = "log list";
    public static final String LOG_DELETE = "log delete LOG_ID";
    public static final String LOG_FIND =
            "log find [st/<datetime>] [et/<datetime>] [e/<exercise_name>] [w/<workout_name>]";
    public static final String LOG_DISPLAY = "log display";

}
