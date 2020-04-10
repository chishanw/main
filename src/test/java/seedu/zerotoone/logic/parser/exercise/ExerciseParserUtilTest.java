package seedu.zerotoone.logic.parser.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.logic.parser.exceptions.ParseException;
import seedu.zerotoone.model.exercise.ExerciseName;
import seedu.zerotoone.model.exercise.NumReps;
import seedu.zerotoone.model.exercise.Weight;

public class ExerciseParserUtilTest {
    @Test
    public void parseExerciseName_nullExerciseName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                ExerciseParserUtil.parseExerciseName(null));
    }

    @Test
    public void parseExerciseName_invalidExerciseName_throwsParseException() {
        Exception exceptionThrown;

        // Empty String
        exceptionThrown = assertThrows(ParseException.class, () ->
                ExerciseParserUtil.parseExerciseName(""));
        assertEquals(ExerciseName.MESSAGE_CONSTRAINTS, exceptionThrown.getMessage());

        // Start with non-alphabets
        exceptionThrown = assertThrows(ParseException.class, () ->
                ExerciseParserUtil.parseExerciseName("1Situp"));
        assertEquals(ExerciseName.MESSAGE_CONSTRAINTS, exceptionThrown.getMessage());

        // Has Symbols
        exceptionThrown = assertThrows(ParseException.class, () ->
                ExerciseParserUtil.parseExerciseName("Bench&Press"));
        assertEquals(ExerciseName.MESSAGE_CONSTRAINTS, exceptionThrown.getMessage());

        // All Spaces
        exceptionThrown = assertThrows(ParseException.class, () ->
                ExerciseParserUtil.parseExerciseName("        "));
        assertEquals(ExerciseName.MESSAGE_CONSTRAINTS, exceptionThrown.getMessage());

        // Spaces then invalid
        exceptionThrown = assertThrows(ParseException.class, () ->
                ExerciseParserUtil.parseExerciseName("    %invalid%    "));
        assertEquals(ExerciseName.MESSAGE_CONSTRAINTS, exceptionThrown.getMessage());
    }

    @Test
    public void parseExerciseName_validExerciseName_returnExerciseName() throws ParseException {
        ExerciseName expectedExerciseName = new ExerciseName("Bench Press");

        assertEquals(ExerciseParserUtil.parseExerciseName("Bench Press"), expectedExerciseName);
        assertEquals(ExerciseParserUtil.parseExerciseName("   Bench Press   "), expectedExerciseName);
        assertNotEquals(ExerciseParserUtil.parseExerciseName("bench press"), expectedExerciseName);
        assertNotEquals(ExerciseParserUtil.parseExerciseName("BenchPress"), expectedExerciseName);
    }

    @Test
    public void parseNumReps_nullNumReps_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                ExerciseParserUtil.parseNumReps(null));
    }

    @Test
    public void parseNumReps_invalidNumReps_throwsParseException() {
        Exception exceptionThrown;

        // Empty String
        exceptionThrown = assertThrows(ParseException.class, () ->
                ExerciseParserUtil.parseNumReps(""));
        assertEquals(NumReps.MESSAGE_CONSTRAINTS, exceptionThrown.getMessage());

        // Start with zero
        exceptionThrown = assertThrows(ParseException.class, () ->
                ExerciseParserUtil.parseNumReps("01"));
        assertEquals(NumReps.MESSAGE_CONSTRAINTS, exceptionThrown.getMessage());

        // Negative Number
        exceptionThrown = assertThrows(ParseException.class, () ->
                ExerciseParserUtil.parseNumReps("-1"));
        assertEquals(NumReps.MESSAGE_CONSTRAINTS, exceptionThrown.getMessage());

        // Has Symbols
        exceptionThrown = assertThrows(ParseException.class, () ->
                ExerciseParserUtil.parseNumReps("1$"));
        assertEquals(NumReps.MESSAGE_CONSTRAINTS, exceptionThrown.getMessage());

        // All Spaces
        exceptionThrown = assertThrows(ParseException.class, () ->
                ExerciseParserUtil.parseNumReps("        "));
        assertEquals(NumReps.MESSAGE_CONSTRAINTS, exceptionThrown.getMessage());

        // Non-numerical
        exceptionThrown = assertThrows(ParseException.class, () ->
                ExerciseParserUtil.parseNumReps("One"));
        assertEquals(NumReps.MESSAGE_CONSTRAINTS, exceptionThrown.getMessage());
    }

    @Test
    public void parseNumReps_validNumReps_returnNumReps() throws ParseException {
        assertEquals(ExerciseParserUtil.parseNumReps("100"), new NumReps("100"));

        // With Spaces
        assertEquals(ExerciseParserUtil.parseNumReps("    100    "), new NumReps("100"));

        // Large Number
        assertEquals(ExerciseParserUtil.parseNumReps("1000000"), new NumReps("1000000"));
    }

    @Test
    public void parseWeight_nullWeight_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                ExerciseParserUtil.parseWeight(null));
    }

    @Test
    public void parseWeight_invalidWeight_throwsParseException() {
        Exception exceptionThrown;

        // Empty String
        exceptionThrown = assertThrows(ParseException.class, () ->
                ExerciseParserUtil.parseWeight(""));
        assertEquals(Weight.MESSAGE_CONSTRAINTS, exceptionThrown.getMessage());

        // Start with zero
        exceptionThrown = assertThrows(ParseException.class, () ->
                ExerciseParserUtil.parseWeight("01"));
        assertEquals(Weight.MESSAGE_CONSTRAINTS, exceptionThrown.getMessage());

        // Negative Number
        exceptionThrown = assertThrows(ParseException.class, () ->
                ExerciseParserUtil.parseWeight("-1"));
        assertEquals(Weight.MESSAGE_CONSTRAINTS, exceptionThrown.getMessage());

        // More than 3 digits
        exceptionThrown = assertThrows(ParseException.class, () ->
                ExerciseParserUtil.parseWeight("1000"));
        assertEquals(Weight.MESSAGE_CONSTRAINTS, exceptionThrown.getMessage());

        // Has Symbols
        exceptionThrown = assertThrows(ParseException.class, () ->
                ExerciseParserUtil.parseWeight("1$"));
        assertEquals(Weight.MESSAGE_CONSTRAINTS, exceptionThrown.getMessage());

        // All Spaces
        exceptionThrown = assertThrows(ParseException.class, () ->
                ExerciseParserUtil.parseWeight("        "));
        assertEquals(Weight.MESSAGE_CONSTRAINTS, exceptionThrown.getMessage());

        // Non-numerical
        exceptionThrown = assertThrows(ParseException.class, () ->
                ExerciseParserUtil.parseWeight("One"));
        assertEquals(Weight.MESSAGE_CONSTRAINTS, exceptionThrown.getMessage());
    }

    @Test
    public void parseWeight_validWeight_returnWeight() throws ParseException {
        assertEquals(ExerciseParserUtil.parseWeight("100"), new Weight("100"));

        // With Spaces
        assertEquals(ExerciseParserUtil.parseWeight("    100    "), new Weight("100"));

        // Max valud
        assertEquals(ExerciseParserUtil.parseWeight("999"), new Weight("999"));
    }
}
