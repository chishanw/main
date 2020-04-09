package seedu.zerotoone.logic.commands.schedule;

import static seedu.zerotoone.testutil.CommandTestUtil.assertCommandSuccess;
import static seedu.zerotoone.testutil.exercise.TypicalExercises.getTypicalExerciseList;
import static seedu.zerotoone.testutil.schedule.TypicalSchedules.getTypicalScheduleList;
import static seedu.zerotoone.testutil.workout.TypicalWorkouts.getTypicalWorkoutList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.ModelManager;
import seedu.zerotoone.model.log.LogList;
import seedu.zerotoone.model.userprefs.UserPrefs;

class ListCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new UserPrefs(),
                getTypicalExerciseList(),
                getTypicalWorkoutList(),
                getTypicalScheduleList(),
                new LogList());

        expectedModel = new ModelManager(new UserPrefs(),
                model.getExerciseList(),
                model.getWorkoutList(),
                model.getScheduleList(),
                model.getLogList());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
