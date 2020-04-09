package seedu.zerotoone.logic;

import seedu.zerotoone.model.log.StatisticsData;

/**
 * The interface Statistics logic.
 */
public interface StatisticsLogic {
    /**
     * Generate statistics statistics data.
     *
     * @return the statistics data
     */
    StatisticsData generateStatistics();
}
