package seedu.zerotoone.model.log;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * The type Statistics data.
 */
public class StatisticsData {

    private boolean isEmpty;
    private Integer totalWorkoutCount;
    private Duration totalTime;
    private Duration averageTimePerDay;
    private LocalDateTime startRange;
    private LocalDateTime endRange;

    /**
     * Instantiates a new Statistics data.
     */
    public StatisticsData() {
        isEmpty = true;
        totalWorkoutCount = 0;
        totalTime = Duration.ZERO;
        averageTimePerDay = Duration.ZERO;
    }

    /**
     * Instantiates a new Statistics data.
     *
     * @param startRange        the start range
     * @param endRange          the end range
     * @param totalWorkoutCount the total workout count
     * @param totalTime         the total time
     * @param averageTimePerDay the average time per day
     */
    public StatisticsData(LocalDateTime startRange, LocalDateTime endRange, Integer totalWorkoutCount,
                          Duration totalTime, Duration averageTimePerDay) {
        isEmpty = false;
        this.totalWorkoutCount = totalWorkoutCount;
        this.totalTime = totalTime;
        this.averageTimePerDay = averageTimePerDay;
        this.startRange = startRange;
        this.endRange = endRange;
    }

    /**
     * Gets start range.
     *
     * @return the start range
     */
    public LocalDateTime getStartRange() {
        return startRange;
    }

    /**
     * Gets end range.
     *
     * @return the end range
     */
    public LocalDateTime getEndRange() {
        return endRange;
    }

    /**
     * Gets total workout count.
     *
     * @return the total workout count
     */
    public int getTotalWorkoutCount() {
        return totalWorkoutCount;
    }

    /**
     * Gets total time.
     *
     * @return the total time
     */
    public Duration getTotalTime() {
        return totalTime;
    }

    /**
     * Gets average time per day.
     *
     * @return the average time per day
     */
    public Duration getAverageTimePerDay() {
        return averageTimePerDay;
    }
}
