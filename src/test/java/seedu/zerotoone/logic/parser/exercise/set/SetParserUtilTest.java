package seedu.zerotoone.logic.parser.exercise.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.logic.parser.exceptions.ParseException;
import seedu.zerotoone.model.exercise.NumReps;

public class SetParserUtilTest {
    @Test
    public void parseNumReps_nullNumReps_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                SetParserUtil.parseNumReps(null));
    }

    @Test
    public void parseNumReps_invalidNumReps_throwsParseException() {
        Exception exceptionThrown;

        // Empty String
        exceptionThrown = assertThrows(ParseException.class, () ->
                SetParserUtil.parseNumReps(""));
        assertEquals(NumReps.MESSAGE_CONSTRAINTS, exceptionThrown.getMessage());

        // Start with 0
        exceptionThrown = assertThrows(ParseException.class, () ->
                SetParserUtil.parseNumReps("01"));
        assertEquals(NumReps.MESSAGE_CONSTRAINTS, exceptionThrown.getMessage());

        // Has Symbols
        exceptionThrown = assertThrows(ParseException.class, () ->
                SetParserUtil.parseNumReps("1*"));
        assertEquals(NumReps.MESSAGE_CONSTRAINTS, exceptionThrown.getMessage());

        // All Spaces
        exceptionThrown = assertThrows(ParseException.class, () ->
                SetParserUtil.parseNumReps("        "));
        assertEquals(NumReps.MESSAGE_CONSTRAINTS, exceptionThrown.getMessage());

        // Negative number
        exceptionThrown = assertThrows(ParseException.class, () ->
                SetParserUtil.parseNumReps("-1"));
        assertEquals(NumReps.MESSAGE_CONSTRAINTS, exceptionThrown.getMessage());
    }

    @Test
    public void parseNumReps_validNumReps_returnNumReps() throws ParseException {
        NumReps expectedNumReps = new NumReps("1");

        assertEquals(SetParserUtil.parseNumReps("1"), expectedNumReps);
        assertEquals(SetParserUtil.parseNumReps("   1   "), expectedNumReps);
        assertNotEquals(SetParserUtil.parseNumReps("2"), expectedNumReps);
    }
}
