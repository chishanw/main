package seedu.zerotoone.logic.parser.exercise.set;

import static java.util.Objects.requireNonNull;
import static seedu.zerotoone.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.zerotoone.logic.parser.CliSyntax.PREFIX_NUM_OF_REPS;
import static seedu.zerotoone.logic.parser.CliSyntax.PREFIX_WEIGHT;

import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.logic.commands.exercise.set.AddCommand;
import seedu.zerotoone.logic.parser.Parser;
import seedu.zerotoone.logic.parser.exceptions.ParseException;
import seedu.zerotoone.logic.parser.util.ArgumentMultimap;
import seedu.zerotoone.logic.parser.util.ArgumentTokenizer;
import seedu.zerotoone.model.exercise.NumReps;
import seedu.zerotoone.model.exercise.Weight;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);
        if (!ArgumentTokenizer.isOnlyPrefixesPresent(argMultimap, PREFIX_NUM_OF_REPS, PREFIX_WEIGHT)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Index index = SetParserUtil.parseIndex(argMultimap.getPreamble());
        NumReps numReps = SetParserUtil.parseNumReps(argMultimap.getValue(PREFIX_NUM_OF_REPS).get());
        Weight weight = SetParserUtil.parseWeight(argMultimap.getValue(PREFIX_WEIGHT).get());
        return new AddCommand(index, numReps, weight);
    }
}
