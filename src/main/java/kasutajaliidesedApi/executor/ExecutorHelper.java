package kasutajaliidesedApi.executor;

import org.joda.time.LocalDateTime;

import static org.joda.time.LocalDateTime.now;

public class ExecutorHelper {

    /**
     * @param hourOfDayToExecute
     * @return the initial delay in milliseconds.
     */
    public static long getInitialDelay(int hourOfDayToExecute) {
        LocalDateTime todayScheduleTime = now() //
                .withHourOfDay(hourOfDayToExecute) //
                .withMinuteOfHour(0) //
                .withSecondOfMinute(0) //
                .withMillisOfSecond(0);

        LocalDateTime firstExecution = todayScheduleTime;

        if (todayScheduleTime.isBefore(now())) {
            firstExecution = todayScheduleTime.plusDays(1);
        }

        long initialDelay = firstExecution.toDate().getTime() - System.currentTimeMillis();
        return initialDelay;
    }
}
